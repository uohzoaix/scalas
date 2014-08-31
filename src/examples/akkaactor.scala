package examples

object akkaactor {
    import akka.actor.{ Actor, ActorSystem, Props }

    val system = ActorSystem()

    class EchoServer extends Actor {
        def receive = {
            case msg: String => println("echo " + msg)
        }
    }

    def main(args: Array[String]) {
        val echoServer = system.actorOf(Props[EchoServer])
        echoServer ! "hi"

        system.shutdown
    }

}