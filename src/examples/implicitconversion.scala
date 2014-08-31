package examples

//隐式转换根据参数和返回值类型进行判断使用哪一个转换函数
object implicitconversion {
    import java.text.SimpleDateFormat

    //参数是string类型会选择该转换函数
    implicit def strToDate(str: String) =
        new SimpleDateFormat("yyyy-MM-dd").parse(str)
    
    //参数是int类型的会选择该转换函数
    implicit def strToDate1(str: Int) =
        new SimpleDateFormat("yyyy").parse(str+"")

    def main(args: Array[String]) {
        println("2013-01-01 unix time: " + "2013-02-01".getTime() / 1000l)
        println("2013-01-01 unix time: " + 2013.getTime() / 1000l)
    }
}