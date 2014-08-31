package examples

object actorprinciple {
    import akka.actor.{ Actor, Props, ActorSystem }
    import akka.testkit.CallingThreadDispatcher

    implicit val system = ActorSystem()

    class EchoServer(name: String) extends Actor {
        def receive = {
            case msg => println("server" + name + " echo " + msg +
                " by " + Thread.currentThread().getName())
        }
    }

    def main(args: Array[String]) {
        val echoServers = (1 to 10).map(x =>
            system.actorOf(Props(new EchoServer(x.toString))
                .withDispatcher(CallingThreadDispatcher.Id)))
        (1 to 10).foreach(msg =>
            echoServers(scala.util.Random.nextInt(10)) ! msg.toString)

        system.shutdown
    }

}