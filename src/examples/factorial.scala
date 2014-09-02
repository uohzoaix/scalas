package examples

object factorial {
    //normal-recursion
    def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)
    //tail-recursion
    def factorial1(n: Int, m: Int): Int = if (n == 0) m else factorial1(n - 1, n * m)

    def recsum(n: Int): Int = if (n == 1) n else n + recsum(n - 1)
    def recsum1(n: Int, m: Int): Int = if (n == 1) m else recsum1(n - 1, n + m)

    def main(args: Array[String]) {
        println(factorial1(5, 1))
        println(recsum1(5, 1))
    }
}