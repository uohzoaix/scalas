package examples

object quicksort {
    //第一种
    def sort(xs: Array[Int]) {
        def swap(i: Int, j: Int) {
            val t = xs(i); xs(i) = xs(j); xs(j) = t
        }
        def sort1(l: Int, r: Int) {
            val pivot = xs((l + r) / 2)
            var i = l; var j = r
            while (i <= j) {
                while (xs(i) < pivot) i += 1
                while (xs(j) > pivot) j -= 1
                if (i <= j) {
                    swap(i, j)
                    i += 1
                    j -= 1
                }
            }
            if (l < j) sort1(l, j)
            if (j < r) sort1(i, r)
        }
        sort1(0, xs.length - 1)
    }

    //第二种
    def sort2(xs: Array[Int]): Array[Int] = {
        if (xs.length <= 1) xs
        else {
            val pivot = xs(xs.length / 2)
            Array.concat(
                sort2(xs filter (pivot >)),
                xs filter (pivot ==),
                sort2(xs filter (pivot <)))
        }
    }

    def main(args: Array[String]) {
        val arr = new Array[Int](3)
        arr(0) = 2
        arr(1) = 3
        arr(2) = 1
        sort(arr)
        println(arr)
    }
}