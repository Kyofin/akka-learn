package com.example.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ChildActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    /**
     * 会等待当前msg执行完才执行stop，而后续没处理的msg就不会处理了
     */
    @Override
    public void postStop() {
        log.info("Stopping actor {}", this);
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,e->{
                    log.info("task:【"+e+"】正在执行....");
                    Thread.sleep(15000);
                    log.info("task:【"+e+"】执行完成....");

                })
                .build();
    }
}
