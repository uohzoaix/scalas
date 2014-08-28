package examples

object fabonacci {
    def fibonacci(in: Any): Int = in match {
        case 0 => 0
        case 1 => 1
        case n: Int if n > 1 => fibonacci(n - 1) + fibonacci(n - 2)
        case _ => 0
    }                                             //> fibonacci: (in: Any)Int

    println(fibonacci(3))                         //> 2
    println(fibonacci(-3))                        //> 0
    //println(fibonacci("3"))

}