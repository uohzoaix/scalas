package akkajavaexample;

import akka.actor.ActorRef 
import akka.actor.Actors 
import akka.actor.UntypedActor 
import akka.actor.UntypedActorFactory 
import akka.dispatch.Future 
import akka.dispatch.Futures 
import java.util.concurrent.Callable


public class HelloMaster extends UntypedActor { 
    @Override 
    void onReceive(Object o) { 
        println "Master接收到Work消息：" + o 
        def clientChannel = getContext().channel()    //客户端链接Channel 
        //启动worker actor 
        ActorRef worker = Actors.actorOf(new UntypedActorFactory() { 
            public UntypedActor create() { 
                return new HelloWorker(); 
            } 
        }).start();

        //这里实现真正的并发 
        Future f1 = Futures.future(new Callable() { 
            Object call() { 
                def result = worker.sendRequestReply(o)            //将消息发给worker actor，让Worker处理业务，同时得到返回结果
                worker.stop() 
                println "Worker Return----" + result 
                clientChannel.sendOneWay(result)                //将结果返回给客户端 
                return result 
            } 
        })

        println "Future call over" 
    }

    public static void main(String[] args) {    //启动Master进程，绑定IP、端口和服务 
        Actors.remote().start("127.0.0.1", 9999).register( 
                "hello-service", 
                Actors.actorOf(HelloMaster.class)); 
    } 
}
