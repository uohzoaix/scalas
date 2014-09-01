package examples

/*
 * scala默认是call-by-value，即先计算方法内的参数（按从左到右顺序计算），然后调用方法
 * 可以通过将参数改为=>形式变为call-by-name方式，即先调用方法，再计算参数
 */
object callbynameorvalue {
    def main(args: Array[String]) {
        def loop: Int = loop
        //def constOne(x: Int, y: Int) = 1
        //def constOne(x: => Int, y: => Int) = 1
        //def constOne(x: => Int, y: Int) = 1
        def constOne(x: Int, y: => Int) = 1 //x是call-by-value,y是call-by-name
        println(constOne(1, loop)) //1，先调用constOne(1, loop)就返回1了
        println(constOne(loop, 1)) //无限循环，先调用constOne(loop, 1)返回loop
    }
}