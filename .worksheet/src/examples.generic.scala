package examples

object generic {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(186); 
  def withClose[A <: { def close(): Unit }, B](closeAble: A)(f: A => B): B =
    try {
      f(closeAble)
    } finally {
      closeAble.close()
    }
  class Connection {
    def close() = println("close Connection")
  };System.out.println("""withClose: [A <: AnyRef{def close(): Unit}, B](closeAble: A)(f: A => B)B""");$skip(113); 
  val conn: Connection = new Connection();System.out.println("""conn  : examples.generic.Connection = """ + $show(conn ));$skip(113); 
  val msg = withClose(conn) { conn =>
    {
      println("do something with Connection")
      123456
    }
  };System.out.println("""msg  : Int = """ + $show(msg ));$skip(16); 

  println(msg)}

}
