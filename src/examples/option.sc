package examples

object option {
    def getProperty(name: String): Option[String] = {
        val value = System.getProperty(name)
        if (value != null) Some(value) else None
    }                                             //> getProperty: (name: String)Option[String]

    val osName = getProperty("os.name")           //> osName  : Option[String] = Some(Mac OS X)

    osName match {
        case Some(value) => println(value)
        case _ => println("none")
    }                                             //> Mac OS X

    println(osName.getOrElse("none"))             //> Mac OS X

    osName.foreach(print _)

}