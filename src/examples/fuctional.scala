package examples

object fuctional {

    def main(args: Array[String]) {
        //Function1表示接受一个参数的函数
        val plus1: Function1[String, AnyRef] = new Function1[String, AnyRef] {
            def apply(x: String): AnyRef = new Tuple1(1)
        }
        println(plus1.apply("2"))

        val plus2: Function1[Int, Int] = {
            class Local extends Function1[Int, Int] {
                def apply(x: Int): Int = x + 1
            }
            new Local: Function1[Int, Int]
        }
        plus2.apply(2)
    }
}