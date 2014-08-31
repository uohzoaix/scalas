package akkajavaexample;

import akka.actor.UntypedActor


class HelloWorker extends UntypedActor {    //Worker是一个Actor，需要实现onReceive方法 
    @Override 
    void onReceive(Object o) { 
        println "Worker 收到消息----" + o 
        if (o instanceof String) { 
            String result = doWork(o)        //调用真实的处理方法 
            getContext().replyUnsafe(result)//将结果返回给Master 
        } 
    } 
    //Worker处理其实很简单，仅仅将参数字符串改造一下而已。只不过使其sleep了20秒，让它变得“耗时较长” 
    String doWork(String str) { 
        Thread.sleep(1000 * 20) 
        return "result----" + str + " 。" 
    } 
}


