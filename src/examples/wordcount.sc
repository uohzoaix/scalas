package examples
import io.Source
import java.io.FileNotFoundException
import scala.collection.mutable.Map
object wordcount {
    //第一种
    val file = List("warn 2013 msg", "warn 2013 msg",
        "error 2013 msg", "warn 2013 msg msg")    //> file  : List[String] = List(warn 2013 msg, warn 2013 msg, error 2013 msg, wa
                                                  //| rn 2013 msg msg)

    def wordcount(str: String): Map[String, Int] = {
        val strs = str.split(" ")
        val result = Map[String, Int]()
        for (s <- strs) {
            result += (s -> strs.count(s == _))
        }
        result
        //str.split(" ").count("msg" == _)
    }                                             //> wordcount: (str: String)scala.collection.mutable.Map[String,Int]

    val num = file.map(wordcount)                 //> num  : List[scala.collection.mutable.Map[String,Int]] = List(Map(warn -> 1, 
                                                  //| 2013 -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1, msg -> 1), Map(2013 -> 1, er
                                                  //| ror -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1, msg -> 2))

    println("wordcount:" + num)                   //> wordcount:List(Map(warn -> 1, 2013 -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1
                                                  //| , msg -> 1), Map(2013 -> 1, error -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1,
                                                  //|  msg -> 2))

    //第二种
    val words = try {
        file.map(_.toLowerCase.trim.split(" "))
    } catch {
        case e: FileNotFoundException =>
            sys.error("No file named %s found")
    }                                             //> words  : List[Array[String]] = List(Array(warn, 2013, msg), Array(warn, 2013
                                                  //| , msg), Array(error, 2013, msg), Array(warn, 2013, msg, msg))

    val counts =
        for (word <- words)
            yield word.groupBy(identity).mapValues(_.size)
                                                  //> counts  : List[scala.collection.immutable.Map[String,Int]] = List(Map(warn -
                                                  //| > 1, 2013 -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1, msg -> 1), Map(2013 -> 
                                                  //| 1, error -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1, msg -> 2))
    //yield word.reduceLeft(_+_)

    //val counts = words.groupBy(identity).mapValues(_.size)

    println("Number of instances found of:")      //> Number of instances found of:
    for (x <- counts)
        for ((word, count) <- x)
            println("%s\t%d".format(word, count)) //> warn	1
                                                  //| 2013	1
                                                  //| msg	1
                                                  //| warn	1
                                                  //| 2013	1
                                                  //| msg	1
                                                  //| 2013	1
                                                  //| error	1
                                                  //| msg	1
                                                  //| warn	1
                                                  //| 2013	1
                                                  //| msg	2

}