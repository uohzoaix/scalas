package examples

object abstractclass {

    trait IntSet {
        def incl(x: Int): IntSet
        def contains(x: Int): Boolean
        def isEmpty: Boolean
        def excl(x: Int): IntSet
    }
    class EmptySet extends IntSet {
        def isEmpty: Boolean = true
        def contains(x: Int): Boolean = false
        def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
        def excl(x: Int): IntSet = new EmptySet
    }

    class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
        def isEmpty: Boolean =
            left.isEmpty && right.isEmpty
        def contains(x: Int): Boolean =
            if (x < elem) left contains x
            else if (x > elem) right contains x else true
        def incl(x: Int): IntSet =
            if (x < elem) new NonEmptySet(elem, left incl x, right)
            else if (x > elem) new NonEmptySet(elem, left, right incl x) else this
        def excl(x: Int): IntSet =
            if (x < elem) new NonEmptySet(elem, left excl x, right)
            else if (x > elem) new NonEmptySet(elem, left, right excl x)
            else new EmptySet
    }
}