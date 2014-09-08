package examples

object iterators {

    trait Iterator[+A] {
        def hasNext: Boolean
        def next: A
    }

    def append[B >: A](that: Iterator[B]): Iterator[B] = new Iterator[B] {
        def hasNext = Iterator.this.hasNext || that.hasNext
        def next = if (Iterator.this.hasNext) Iterator.this.next else that.next
    }

    def map[B](f: A => B): Iterator[B] = new Iterator[B] {
        def hasNext = Iterator.this.hasNext
        def next = f(Iterator.this.next)
    }

    def flatMap[B](f: A => Iterator[B]): Iterator[B] = new Iterator[B] {
        private var cur: Iterator[B] = Iterator.empty
        def hasNext: Boolean =
            if (cur.hasNext) true
            else if (Iterator.this.hasNext) { cur = f(Iterator.this.next); hasNext } else false
        def next: B =
            if (cur.hasNext) cur.next
            else if (Iterator.this.hasNext) { cur = f(Iterator.this.next); next } else error("next on empty iterator")
    }

    def filter(p: A => Boolean) = new BufferedIterator[A] {
        private val source =
            Iterator.this.buffered
        private def skip =
            { while (source.hasNext && !p(source.head)) { source.next } }
        def hasNext: Boolean =
            { skip; source.hasNext }
        def next: A =
            { skip; source.next }
        def head: A =
            { skip; source.head }
    }
}