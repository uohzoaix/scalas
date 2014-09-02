package examples

object currsum {

    def sum(f: Int => Int)(a: Int, b: Int): Int = {
        def iter(a: Int, result: Int): Int = {
            if (a > b) result
            else iter(a + 1, result + f(a))
        }
        iter(a, 0)
    }
    def sum1(f: Int => Int): (Int, Int) => Int = {
        def sumF(a: Int, b: Int): Int =
            if (a > b) 0 else f(a) + sumF(a + 1, b)
        sumF
    }
    def main(args: Array[String]) {
        println(sum(x => x)(1, 10))
        println(sum1(x => x)(1, 10))
        println(sum(x => x * x)(1, 10))
    }
}