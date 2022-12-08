package com.example.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.BalancingPool;

public class BlockingActorPoolExample {
  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("blocking-actor-pool-example");

    // create a pool of 5 actors to handle blocking requests
    ActorRef pool = system.actorOf(new BalancingPool(5).props(
        Props.create(BlockingActor.class)));

    // send messages to the actor pool
    for (int i = 0; i < 10; i++) {
      pool.tell("blocking request " + i, ActorRef.noSender());
    }
    System.out.println("已完成block request发送....");

    // terminate the actor system
    system.terminate();
  }
}
