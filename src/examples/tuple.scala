package examples

object tuple {

    def main(args: Array[String]) {
        //(x / y, x % y)相当于new Tuple2[Int, Int](x / y, x % y)
        //def divmod(x: Int, y: Int): (Int, Int) = (x / y, x % y)
        def divmod(x: Int, y: Int) = new Tuple2(x / y, x % y)
        val xy = divmod(10, 3)
        println(xy.productElement(0))
        println("quotient: " + xy._1 + ", rest: " + xy._2)
    }
}