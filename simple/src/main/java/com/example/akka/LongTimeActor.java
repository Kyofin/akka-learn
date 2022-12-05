package com.example.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class LongTimeActor extends AbstractActor {

    public static class ShortTimeTaskInfo {
        private int taskId;
        private String json;

        public ShortTimeTaskInfo(int taskId, String json) {
            this.taskId = taskId;
            this.json = json;
        }

        public int getTaskId() {
            return taskId;
        }

        public String getJson() {
            return json;
        }
    }

    public static class LongTimeTaskInfo {
        private int taskId;
        private String json;

        public LongTimeTaskInfo(int taskId, String json) {
            this.taskId = taskId;
            this.json = json;
        }

        public int getTaskId() {
            return taskId;
        }

        public String getJson() {
            return json;
        }
    }


    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void postStop() {
        log.info("Stopping actor {}", this);
    }


    public Receive createReceive() {
        return receiveBuilder()
                .match(LongTimeTaskInfo.class, p -> {
                    System.out.println("开始长时间任务Id:"+p.taskId+" ->  The address of this actor is: " + getSelf());
                    Thread.sleep(10000);
                    System.out.println("长时间任务Id:"+p.taskId+"完成..." );
                }).match(ShortTimeTaskInfo.class, p -> {
                    System.out.println("开始短时间任务Id:"+p.taskId+" ->  The address of this actor is: " + getSelf());
                    System.out.println("短时间任务Id:"+p.taskId+"完成...");
                })
                .build();
    }
}