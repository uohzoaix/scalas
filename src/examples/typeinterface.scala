package examples

object typeinterface {

    sealed abstract class Type {}
    case class Tyvar(a: String) extends Type {
        override def toString = a
    }
    case class Arrow(t1: Type, t2: Type) extends Type {
        override def toString = "(" + t1 + "->" + t2 + ")"
    }
    case class Tycon(k: String, ts: List[Type]) extends Type {
        override def toString =
            k + (if (ts.isEmpty) "" else ts.mkString("[", ",", "]"))
    }
    object typeInfer {
        private var n: Int = 0
        def newTyvar(): Type = { n += 1; Tyvar("a" + n) }
        case class TypeScheme(tyvars: List[Tyvar], tpe: Type) {
            def newInstance: Type = {
                (emptySubst /: tyvars)((s, tv) => s.extend(tv, newTyvar()))(tpe)
            }
        }
        type Env = List[(String, TypeScheme)]
        abstract class Subst extends Function1[Type, Type] {
            def lookup(x: Tyvar): Type
            def apply(t: Type): Type = t match {
                case tv @ Tyvar(a) => val u = lookup(tv); if (t == u) t else apply(u) case Arrow(t1, t2) => Arrow(apply(t1), apply(t2))
                case Tycon(k, ts) => Tycon(k, ts map apply)
            }
            def apply(env: Env): Env = env.map({
                case (x, TypeScheme(tyvars, tpe)) => // assumes tyvars don’t occur in this substitution
                    (x, TypeScheme(tyvars, apply(tpe)))
            })
            def extend(x: Tyvar, t: Type) = new Subst {
                def lookup(y: Tyvar): Type = if (x == y) t else Subst.this.lookup(y)
            }
        }
        val emptySubst = new Subst { def lookup(t: Tyvar): Type = t }
    }

}