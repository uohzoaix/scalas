package examples

import actors._, Actor._

object SharedDataStyle {
    case class Add(number: Int)
    case class GetResult(sender: Actor)

    class AddActor extends Actor {
        override def act(): Unit = process(0)
        def process(value: Int): Unit = {
            //value一开始为0，然后等待actor发出消息
            //addActor ! Add(1):actor发出Add(1)开始接收返回process(0+1),value=1接着又开始等待
            //addActor ! Add(2):同上，value=1+2=3
            //addActor ! Add(2):同上，value=3+3=6
            //addActor ! GetResult(self):actor发出GetResult(self)则self将value=6发出，返回process(6)
            println(value)
            reactWithin(500) {
                case Add(number) => process(value + number)
                case GetResult(a) =>
                    a ! value; process(value)
                case _ => process(value)
            }
        }
    }

    def main(args: Array[String]): Unit = {
        val addActor = new AddActor
        addActor.start()//会调用act()方法
        addActor ! Add(1)
        addActor ! Add(2)
        addActor ! Add(3)
        addActor ! GetResult(self)
        //等待actor发来的消息消息
        receiveWithin(1000) {
            case result => println("Total is " + result)
        }
    }
}