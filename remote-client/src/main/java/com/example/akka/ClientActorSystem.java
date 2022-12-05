package com.example.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class ClientActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ClientApp", ConfigFactory.load().getConfig("ClientApp"));
        actorSystem.actorOf(Props.create(ServerActor.class), "MyClientActor");

        // 声明server端actor
        ActorRef remoteActor = actorSystem.actorFor("akka.tcp://ServerApp@127.0.0.1:2551/user/serverActor");

        ActorRef clientActor = actorSystem.actorOf(ClientActor.props(remoteActor), "clientActor");
        clientActor.tell("date", ActorRef.noSender());
        clientActor.tell("touch 1.txt", ActorRef.noSender());

    }
}
