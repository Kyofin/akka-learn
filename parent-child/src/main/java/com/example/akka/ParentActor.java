package com.example.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ParentActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void postStop() {
        log.info("Stopping actor {}", this);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,s->{
                    log.info("工作流实例:【"+s+"】正在执行...");
                    ActorRef task1 = getContext().actorOf(Props.create(ChildActor.class), "taskActor");
                    task1.tell("配置渲染",ActorRef.noSender());
                    task1.tell("zkServer启动",ActorRef.noSender());
                })
                .build();
    }
}
