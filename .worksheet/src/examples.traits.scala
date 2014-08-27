package examples

import scala.collection.JavaConverters._

object traits {
  trait ForEachAble[A] {
    def iterator: java.util.Iterator[A]
    def foreach(f: A => Unit) = {
      val iter = iterator
      while (iter.hasNext)
        f(iter.next)
    }
  }

  trait JsonAble[A] {
    def iterator: java.util.Iterator[A]
    def toJson(): String = {
      val iter = iterator
      var result = "{"
      var i = 0
      while (iter.hasNext) {
        result += i + ":" + iter.next() + ";"
        i = i + 1
      }
      result += "}"
      return result
    }
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(599); 

  val scalaList = List(1, 2, 3);System.out.println("""scalaList  : List[Int] = """ + $show(scalaList ));$skip(85); 
  val list = new java.util.ArrayList[Int]() with ForEachAble[Int] with JsonAble[Int];System.out.println("""list  : java.util.ArrayList[Int] with examples.traits.ForEachAble[Int] with examples.traits.JsonAble[Int] = """ + $show(list ));$skip(33); 
  scalaList.foreach(list.add(_));$skip(56); 

  println("For each: "); list.foreach(x => println(x));$skip(33); 
  println("Json:" + list.toJson)}
}
