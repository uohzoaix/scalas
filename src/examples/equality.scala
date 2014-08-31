package examples

object equality {
    class Person(val name: String) {
        override def equals(other: Any) = other match {
            case that: Person => name.equals(that.name)
            case _ => false
        }
    }

    def main(args: Array[String]) {
        println(new Person("Black") == new Person("Black"))
    }
}