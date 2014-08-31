package akkajavaexample;

import akka.actor.ActorRef 
import static akka.actor.Actors.remote


public class HelloClient implements Runnable { 
    int seq 
    String serviceName

    HelloClient(int seq, String serviceName) { 
        this.seq = seq 
        this.serviceName = serviceName 
    }

    void run() { 
        ActorRef actor = remote().actorFor(serviceName, "127.0.0.1", 9999); 
        String str = "Hello--" + seq 
        println "请求-----${str}" 
        Object res = actor.sendRequestReply(str) 
        println "返回-----${res}" 
    }

    public static void main(String[] args) { 
        for (int i = 0; i &lt; 5; i++) { 
            Thread thread = new Thread(new HelloClient(i, "hello-service")) 
            thread.start()        //同时启动5个客户端请求Master 
        } 
    } 
}
