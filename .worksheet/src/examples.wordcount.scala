package examples
import io.Source
import java.io.FileNotFoundException
import scala.collection.mutable.Map
object wordcount {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(236); 
    //第一种
    val file = List("warn 2013 msg", "warn 2013 msg",
        "error 2013 msg", "warn 2013 msg msg");System.out.println("""file  : List[String] = """ + $show(file ));$skip(276); 

    def wordcount(str: String): Map[String, Int] = {
        val strs = str.split(" ")
        val result = Map[String, Int]()
        for (s <- strs) {
            result += (s -> strs.count(s == _))
        }
        result
        //str.split(" ").count("msg" == _)
    };System.out.println("""wordcount: (str: String)scala.collection.mutable.Map[String,Int]""");$skip(35); 

    val num = file.map(wordcount);System.out.println("""num  : List[scala.collection.mutable.Map[String,Int]] = """ + $show(num ));$skip(33); 

    println("wordcount:" + num);$skip(190); 

    //第二种
    val words = try {
        file.map(_.toLowerCase.trim.split(" "))
    } catch {
        case e: FileNotFoundException =>
            sys.error("No file named %s found")
    };System.out.println("""words  : List[Array[String]] = """ + $show(words ));$skip(105); 

    val counts =
        for (word <- words)
            yield word.groupBy(identity).mapValues(_.size);System.out.println("""counts  : List[scala.collection.immutable.Map[String,Int]] = """ + $show(counts ));$skip(141); 
    //yield word.reduceLeft(_+_)

    //val counts = words.groupBy(identity).mapValues(_.size)

    println("Number of instances found of:");$skip(105); 
    for (x <- counts)
        for ((word, count) <- x)
            println("%s\t%d".format(word, count))}

}
