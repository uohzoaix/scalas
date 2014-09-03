package examples

object rational {

    class Rational(n: Int, d: Int) {
        private def gcd(x: Int, y: Int): Int = {
            if (x == 0) y
            else if (x < 0) gcd(-x, y) else if (y < 0) -gcd(x, -y) else gcd(y % x, x)
        }
        private val g = gcd(n, d)
        val numer: Int = n / g
        val denom: Int = d / g
        //调用此种方法会返回一个新的Rational实例
        def +(that: Rational) =
            new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
        def -(that: Rational) =
            new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
        def *(that: Rational) =
            new Rational(numer * that.numer, denom * that.denom)
        def /(that: Rational) =
            new Rational(numer * that.denom, denom * that.numer)
        //重载toString方法
        //override def toString = "" + numer + "/" + denom
        //无参方法，每次调用都会new一个Rational实例
        def square = new Rational(numer*numer, denom*denom)
    }

    def main(args: Array[String]) {
        var i = 1
        var x = new Rational(0, 1)
        while (i <= 10) {
            x += new Rational(1, i)
            i += 1
        }
        println("" + x.numer + "/" + x.denom)
        println(x.square)//examples.rational$Rational@400ba6f6
        println(x.square)//examples.rational$Rational@3cfe8153
    }
}