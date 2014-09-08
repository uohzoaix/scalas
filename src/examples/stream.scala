package examples

object stream {

    //streams定义的tail只在需要使用的时候才会加载
    def range(start: Int, end: Int): Stream[Int] = if (start >= end) Stream.empty
    else Stream.cons(start, range(start + 1, end))
}