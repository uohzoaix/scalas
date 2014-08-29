package examples

object pipeline {
    val file = List("warn 2013 msg", "warn 2012 msg",
        "error 2013 msg", "warn 2013 msg")        //> file  : List[String] = List(warn 2013 msg, warn 2012 msg, error 2013 msg, wa
                                                  //| rn 2013 msg)

		//第一filter返回warn 2013 msg，warn 2012 msg，warn 2013 msg，第二个filter返回warn 2013 msg，warn 2013 msg
		//下划线代表每一项的内容
    println("cat file | grep 'warn' | grep '2013' | wc : "
        + file.filter(_.contains("warn")).filter(_.contains("2013")).size)
                                                  //> cat file | grep 'warn' | grep '2013' | wc : 2

}