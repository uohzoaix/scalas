package examples

import actors._, Actor._, java.util._
object ConcurrentTest {

    val actors = new ArrayList[Actor]
    val length = 1000000
    var startTime = System.nanoTime

    def main(args: Array[String]): Unit = {
        for (i <- 0 to length)
            actors.add(actor {
                info("react: " + i + " actor created")
                //receiveMessage//当使用Receive方法接收消息时，由于Receive会在结束任务前一直持有线程，而Scala在后台默认只给Receive方法启动256个线程
                reactMessage //React接收器由于不需要长期持有线程，空闲即释放线程
            })
        actors.get(0) ! (0, 0)
    }

    def info(msg: String) = println(msg + " received by " +
        Thread.currentThread)

    def receiveMessage {
        var continue = true
        while (continue) {
            receive {
                case (id: Int, direction: Int) =>
                    sendMessage(id: Int, direction: Int)
                case "finish" =>
                    continue = false
                    val endTime = System.nanoTime
                    println("Finish, spend time:" +
                        (endTime - startTime) / 1000000000.0 + " secs")
                case _ => println("input error")
            }
        }
    }

    def reactMessage {
        var continue = true
        loopWhile(continue) {
            react {
                case (id: Int, direction: Int) =>
                    sendMessage(id: Int, direction: Int)
                case "finish" =>
                    continue = false
                    val endTime = System.nanoTime
                    println("Finish, spend time:" +
                        (endTime - startTime) / 1000000000.0 + " secs")
                case _ => println("input error")
            }
        }
    }

    //direction=0->sendLatter;direction=1->sendFormer
    def sendMessage(id: Int, direction: Int) {
        if (direction == 0 && id != length) {
            info("Actor" + id + " send message to the Actor" + (id + 1))
            actors.get(id + 1) ! (id + 1, 0)
        } else if (id != 0 && direction == 1) {
            info("Actor" + id + " send message to the Actor" + (id - 1))
            actors.get(id - 1) ! (id - 1, 1)
        } else if (direction == 0 && id == length) {
            actors.get(length) ! (length, 1)
        } else if (id == 0 && direction == 1) {
            actors.get(0) ! "finish"
        }
    }
}