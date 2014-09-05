package examples

object covariant {

    //val x = new Array[String](1)
    //val y: Array[Any] = x //出错：元素类型必须为String,虽然String是Any的子类，如果这样将x赋值给y,那么y就可以存放任何类型了
    //y(0) = new Rational(1, 2)

    //Array的元素是A的子类，所以update方法不能将A传递进去
    trait Array[+A] {
        def apply(index: Int): A
        //def update(index: Int, elem: A) //错误：covariant type parameter A appears in contravariant position.

        //下面的不会报错：B被限制在只能为A的超类中
        def update1[B >: A](index: Int, elem: B)
    }

    abstract class Stack[+A] {
        def push[B >: A](x: B): Stack[B] = new NonEmptyStack(x, this)
        def isEmpty: Boolean
        def top: A
        def pop: Stack[A]
    }
    object EmptyStack extends Stack[Nothing] {
        def isEmpty = true
        def top = error("EmptyStack.top")
        def pop = error("EmptyStack.pop")
    }
    class NonEmptyStack[+A](elem: A, rest: Stack[A]) extends Stack[A] {
        def isEmpty = false
        def top = elem
        def pop = rest
    }
}