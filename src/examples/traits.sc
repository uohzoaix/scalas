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
  }

  val scalaList = List(1, 2, 3)                   //> scalaList  : List[Int] = List(1, 2, 3)
  val list = new java.util.ArrayList[Int]() with ForEachAble[Int] with JsonAble[Int]
                                                  //> list  : java.util.ArrayList[Int] with examples.traits.ForEachAble[Int] with 
                                                  //| examples.traits.JsonAble[Int] = []
  scalaList.foreach(list.add(_))

  println("For each: "); list.foreach(x => println(x))
                                                  //> For each: 
                                                  //| 1
                                                  //| 2
                                                  //| 3
  println("Json:" + list.toJson)                  //> Json:{0:1;1:2;2:3;}
}