package examples
import io.Source
import java.io.FileNotFoundException

object wordcount {
		//第一种
    val file = List("warn 2013 msg", "warn 2013 msg",
        "error 2013 msg", "warn 2013 msg msg")    //> file  : List[String] = List(warn 2013 msg, warn 2013 msg, error 2013 msg, wa
                                                  //| rn 2013 msg msg)

    def wordcount(str: String): Int = {
        println(str)
        val strs = str.split(" ")
        str.split(" ").count("msg" == _)
    }                                             //> wordcount: (str: String)Int

    val num = file.map(wordcount).reduceLeft(_ + _)
                                                  //> warn 2013 msg
                                                  //| warn 2013 msg
                                                  //| error 2013 msg
                                                  //| warn 2013 msg msg
                                                  //| num  : Int = 5

    println("wordcount:" + num)                   //> wordcount:5
    
    //第二种
    val words = try {
      file.map(_.toLowerCase.trim.split(" "))
    } catch {
      case e: FileNotFoundException =>
        sys.error("No file named %s found")
    }                                             //> words  : List[Array[String]] = List(Array(warn, 2013, msg), Array(warn, 2013
                                                  //| , msg), Array(error, 2013, msg), Array(warn, 2013, msg, msg))

		val counts=
				for(word<-words)
						yield word.groupBy(identity).mapValues(_.size)
                                                  //> counts  : List[scala.collection.immutable.Map[String,Int]] = List(Map(warn -
                                                  //| > 1, 2013 -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1, msg -> 1), Map(2013 -> 
                                                  //| 1, error -> 1, msg -> 1), Map(warn -> 1, 2013 -> 1, msg -> 2))
						//yield word.reduceLeft(_+_)

    //val counts = words.groupBy(identity).mapValues(_.size)

    println("Number of instances found of:")      //> Number of instances found of:
    for(x<-counts)
    		for((word,count)<-x)
    				println("%s\t%d".format(word,count))
                                                  //> warn	1
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