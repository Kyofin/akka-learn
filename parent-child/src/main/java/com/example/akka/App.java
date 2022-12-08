package com.example.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ActorSystem actorSystem = ActorSystem.create("parent-child-app");
        ActorRef actorRef = actorSystem.actorOf(Props.create(ParentActor.class), "workflowActor");
        actorRef.tell("zk服务安装",ActorRef.noSender());
        Thread.sleep(7000);
        // 关闭parent actor
        System.out.println("关闭parent actor");
        actorRef.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }
}
