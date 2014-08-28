package examples

object generic {
  def withClose[A <: { def close(): Unit }, B](closeAble: A)(f: A => B): B =
    try {
      f(closeAble)
    } finally {
      closeAble.close()
    }                                             //> withClose: [A <: AnyRef{def close(): Unit}, B](closeAble: A)(f: A => B)B
  class Connection {
    def close() = println("close Connection")
  }
  val conn: Connection = new Connection()         //> conn  : examples.generic.Connection = examples.generic$$anonfun$main$1$Conne
                                                  //| ction$1@833e745
  val msg = withClose(conn) { conn =>
    {
      println("do something with Connection")
      123456
    }
  }                                               //> do something with Connection
                                                  //| close Connection
                                                  //| msg  : Int = 123456

  println(msg)                                    //> 123456

}