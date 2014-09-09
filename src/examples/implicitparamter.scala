package examples

object implicitparamter {

    abstract class SemiGroup[A] {
        def add(x: A, y: A): A
    }
    abstract class Monoid[A] extends SemiGroup[A] {
        def unit: A
    }
    object stringMonoid extends Monoid[String] {
        def add(x: String, y: String): String = x.concat(y)
        def unit: String = ""
    }
    object intMonoid extends Monoid[Int] {
        def add(x: Int, y: Int): Int = x + y
        def unit: Int = 0
    }
    def sum[A](xs: List[A])(m: Monoid[A]): A =
        if (xs.isEmpty) m.unit
        else m.add(xs.head, sum(xs.tail)(m))
    //一个方法只允许出现一个implicit关键字，并且只能出现在最后一个参数中。
    //implicit也允许用来修饰object
    def sum1[A](xs: List[A])(implicit m: Monoid[A]): A =
        if (xs.isEmpty) m.unit
        else m.add(xs.head, sum1(xs.tail))
    def main(args: Array[String]) {
        println(sum(List("a", "bc", "def"))(stringMonoid))
        println(sum(List(1, 2, 3))(intMonoid))
    }

}