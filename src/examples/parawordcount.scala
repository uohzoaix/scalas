package examples

object parawordcount {
    def main(args: Array[String]) {
        val file = List("warn 2013 msg", "warn 2012 msg",
            "error 2013 msg", "warn 2013 msg")

        def wordcount(str: String): Int = str.split(" ").count("msg" == _)

        val num = file.par.map(wordcount).par.reduceLeft(_ + _)

        println("wordcount:" + num)

    }
}