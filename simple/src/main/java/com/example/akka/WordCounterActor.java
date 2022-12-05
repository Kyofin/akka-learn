package com.example.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WordCounterActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static final class CountWords {
        String line;
        Boolean timeout;

        public CountWords(String line, Boolean timeout) {
            this.line = line;
            this.timeout = timeout;
        }

        public CountWords(String line) {
            this.line = line;
        }
    }

    @Override
    public void preStart() {
        log.info("Starting WordCounterActor {}", this);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CountWords.class, r -> {
                    try {
                        log.info("Received CountWords message from " + getSender());
                        if (r.timeout != null && r.timeout.equals(true)) {
                            Thread.sleep(10000);
                        }
                        int numberOfWords = countWordsFromLine(r.line);
                        getSender().tell(numberOfWords, getSelf());
                    } catch (Exception ex) {
                        getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
                        throw ex;
                    }
                })
                .build();
    }

    private int countWordsFromLine(String line) throws Exception {

        if (line == null) {
            throw new IllegalArgumentException("The text to process can't be null!");
        }

        int numberOfWords = 0;
        String[] words = line.split(" ");
        for (String possibleWord : words) {
            if (possibleWord.trim().length() > 0) {
                numberOfWords++;
            }
        }
        return numberOfWords;
    }
}