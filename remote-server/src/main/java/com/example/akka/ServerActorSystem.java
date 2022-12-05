package com.example.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class ServerActorSystem  {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ServerApp", ConfigFactory.load().getConfig("ServerApp"));
        actorSystem.actorOf(Props.create(ServerActor.class), "serverActor");


    }
}
