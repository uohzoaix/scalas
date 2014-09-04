package examples

object caseclass {

    abstract class Expr
    //1.case class默认会加上构造方法，如def Number(n: Int)=new Number(n)
    //2.case class默认会重写AnyRef的toString,equals,hashCode方法
    //3.case class默认会为参数生成访问器，如def n:Int函数返回n的值
    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    //class Sum(e1: Expr, e2: Expr) extends Expr

    def eval(e: Expr): Int = e match {
        case Number(n) => n
        case Sum(l, r) => eval(l) + eval(r)
    }
    //def main(args: Array[String]) {
    //如果将case去掉，则println(new Sum(new Sum(Number(1), Number(2)), Number(3)))打印examples.caseclass$Sum@2f2faeca
    //如果加上case,则println(Sum(Sum(Number(1), Number(2)), Number(3)))打印Sum(Sum(Number(1), Number(2)), Number(3))
    //且如果两个case class由相同的参数构造那么equals方法返回true
    println(new Sum(new Sum(Number(1), Number(2)), Number(3)))
    println(eval(Sum(Sum(Number(1), Number(2)), Number(3))))
    //}

    abstract class IntTree
    case object EmptyTree extends IntTree
    case class Node(elem: Int, left: IntTree, right: IntTree) extends IntTree
    def contains(t: IntTree, v: Int): Boolean = t match {
        case EmptyTree => false
        case Node(elem, left, right) =>
            if (elem == v) true
            else if (v < elem) contains(left, v)
            else contains(right, v)
    }
    def insert(t: IntTree, v: Int): IntTree = t match {
        case EmptyTree => Node(v, EmptyTree, EmptyTree)
        case Node(elem, left, right) =>
            if (elem > v) Node(elem, insert(left, v), right)
            else if (elem < v) Node(elem, left, insert(right, v))
            else t
    }

    def main(args: Array[String]) {
        //println(contains(EmptyTree, 1))
        //println(contains(Node(2, Node(1, EmptyTree, EmptyTree), Node(2, EmptyTree, EmptyTree)), 3))
        println(insert(Node(2, Node(1, EmptyTree, EmptyTree), Node(2, EmptyTree, EmptyTree)), 3))
    }
}