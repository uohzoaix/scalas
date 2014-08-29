package examples

object function {
    val list = List(1, 2, 3, 4)                   //> list  : List[Int] = List(1, 2, 3, 4)

    def containsOdd(list: List[Int]): Boolean = {
        for (i <- list) {
            if (i % 2 == 1)
                return true;
        }
        return false;
    }                                             //> containsOdd: (list: List[Int])Boolean

    println("list contains Odd ? " + list.exists(_ % 2 == 1))
                                                  //> list contains Odd ? true

}