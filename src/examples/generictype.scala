package examples

object generictype {

    abstract class Stack[A] {
        def push(x: A): Stack[A] = new NonEmptyStack[A](x, this)
        def isEmpty: Boolean
        def top: A
        def pop: Stack[A]
    }
    class EmptyStack[A] extends Stack[A] {
        def isEmpty = true
        def top = error("EmptyStack.top")
        def pop = error("EmptyStack.pop")
    }
    class NonEmptyStack[A](elem: A, rest: Stack[A]) extends Stack[A] {
        def isEmpty = false
        def top = elem
        def pop = rest
    }

    val s1 = new EmptyStack[String].push("abc")
    val s2 = new EmptyStack[String].push("abx").push(s1.top)
    println(isPrefix(s1, s2)) //此处可省略[String]，scala会自动检测

    def isPrefix[A](p: Stack[A], s: Stack[A]): Boolean = {
        p.isEmpty ||
            p.top == s.top && isPrefix[A](p.pop, s.pop)
    }

    def main(args: Array[String]) {
        val x = new EmptyStack[Int]
        val y = x.push(1).push(2)
        println(y.pop.top)
    }

    trait Ordered[A] {
        def compare(that: A): Int
        def <(that: A): Boolean = (this compare that) < 0
        def >(that: A): Boolean = (this compare that) > 0
        def <=(that: A): Boolean = (this compare that) <= 0
        def >=(that: A): Boolean = (this compare that) >= 0
        def compareTo(that: A): Int = compare(that)
    }
    //表示A必须为Ordered的子类
    trait Set[A <: Ordered[A]] {
        def incl(x: A): Set[A]
        def contains(x: A): Boolean
    }

    class EmptySet[A <: Ordered[A]] extends Set[A] {
        def contains(x: A): Boolean = false
        def incl(x: A): Set[A] = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
    }
    class NonEmptySet[A <: Ordered[A]](elem: A, left: Set[A], right: Set[A]) extends Set[A] {
        def contains(x: A): Boolean =
            if (x < elem) left contains x
            else if (x > elem) right contains x else true
        def incl(x: A): Set[A] =
            if (x < elem) new NonEmptySet(elem, left incl x, right)
            else if (x > elem) new NonEmptySet(elem, left, right incl x) else this
    }
}