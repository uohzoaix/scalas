package examples

object ClassExample {

  def main(args: Array[String]) {
    val obama: Persion = new Persion("Barack", "Obama")
    println("Persion: " + obama)
    println("firstName: " + obama.firstName)
    println("lastName: " + obama.lastName)
    obama.age_=(51)
    println("age: " + obama.age)
  }

  def withEat(eatAble: { def eat(str: => String): Unit },
    op: { def eat(str: => String): Unit } => Unit) {
    op(eatAble)
    eatAble.eat("apple")
  }

  val stu: Student = new Student("aaa")
  withEat(stu, stu =>
    println("do something with Connection"))
}

class Persion(val firstName: String, val lastName: String) {

  private var _age = 0
  def age = _age
  def age_=(newAge: Int) = _age = newAge

  def fullName() = this.getClass().getName() + " " + firstName + " " + lastName

  def eat(str: => String) = {
    println(fullName + " is eating " + str)
  }

  override def toString() = fullName()
}

class Student(val name: String) {
  def eat(str: => String) = {
    println(this.getClass().getName() + " is eating " + str)
  }
}