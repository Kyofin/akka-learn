package com.example.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ClientActor  extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef remoteRef;

    public ClientActor(ActorRef remoteRef) {
        this.remoteRef = remoteRef;
    }

    public static Props props(ActorRef remoteRef) {
        return Props.create(ClientActor.class, remoteRef);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,cmd->{
                    log.info("client 准备发送命令："+cmd);
                    remoteRef.tell("sh -xc " + cmd, getSelf());
                })
                .build();
    }
}
