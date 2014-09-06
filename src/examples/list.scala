package examples

object list {

    def isort(xs: List[Int]): List[Int] =
        if (xs.isEmpty) Nil
        else insert1(xs.head, isort(xs.tail))
    //第一种方法
    def insert1(head: Int, tail: List[Int]): List[Int] = {
        if (tail.size == 0)
            List(head)
        else {
            if (head <= tail.head)
                head :: tail
            else
                tail.head :: insert(head, tail.tail)
        }
    }
    //第二种方法
    def insert(x: Int, xs: List[Int]): List[Int] = xs match {
        case List() => List(x)
        case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
    }

    def msort[A](less: (A, A) => Boolean)(xs: List[A]): List[A] = {
        def merge(xs1: List[A], xs2: List[A]): List[A] =
            if (xs1.isEmpty) xs2
            else if (xs2.isEmpty) xs1
            else if (less(xs1.head, xs2.head)) {
                println("lessxs1", xs1)
                println("lessxs2", xs2)
                xs1.head :: merge(xs1.tail, xs2)
            } else {
                println("morexs1", xs1)
                println("morexs2", xs2)
                xs2.head :: merge(xs1, xs2.tail)
            }
        val n = xs.length / 2
        if (n == 0) xs
        else merge(msort(less)(xs take n), msort(less)(xs drop n))
    }

    def squareList(xs: List[Int]): List[Int] = xs match {
        case List() => xs
        case y :: ys => y * y :: squareList(ys)
    }
    def squareList1(xs: List[Int]): List[Int] =
        xs map (x => x * x)
    
    def main(args: Array[String]) {
        val nums: List[Int] = List(10, 8, 4, 89, 100, 98, 6, 2, 3, 1)
        println(isort(nums))
        println(nums.length)
        msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3))
        println(squareList1(nums))

        def isPrime(n: Int): Boolean =
            n % 2 != 0

        println(List.range(1, 10)
            .flatMap(i => List.range(1, i).map(x => (i, x))).filter(pair => isPrime(pair._1 + pair._2)))
    }
}