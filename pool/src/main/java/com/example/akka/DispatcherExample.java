package com.example.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class DispatcherExample {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("dispatcher-example");
        // 模拟系统有很多actor在运作
        for (int i = 0; i < 20; i++) {
            ActorRef actor;
            if (i % 2 == 0) {
                actor = system.actorOf(Props.create(BlockingActor.class), "actor-" + i);
            } else {
                actor = system.actorOf(Props.create(BlockingActor.class).withDispatcher("my-haha-dispatcher"), "actor-" + i);
            }
            actor.tell("tell...", ActorRef.noSender());
        }
    }
}
