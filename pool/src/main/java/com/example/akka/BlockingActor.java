package com.example.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class BlockingActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,s->{
                   log.info(this+",处理阻塞的任务....");
                    Thread.sleep(10000);
                   log.info(this+",完成阻塞的任务....");
                })
                .build();
    }
}
