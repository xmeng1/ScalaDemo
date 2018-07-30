package example

/**
  *
  * <p>Date:    30/07/18
  *
  * @author mengxin
  * @version 1.0
  */
class Rational(n: Int, d: Int) {
    val numerator: Int = n
    val denominator: Int = d

    require(d != 0)

    // must invoke the another constructor of the same class

    def this(n: Int) = this(n, 1)

    override def toString: String = n + "/" + d

    def add(that: Rational): Rational =
        new Rational(numerator * that.denominator + that.numerator * denominator, denominator * that.denominator)

    // cannot use the d and n
    //    def add2(that: Rational): Rational =
    //        new Rational(numer * that.n + that.numer * denom, denom * that.denom)

    def lessThan(that: Rational) =
        this.numerator * that.denominator < that.numerator * this.denominator

    def max(that: Rational) =
        if (this.lessThan(that)) that else this
}


object test extends App {
    var x = new Rational(1, 2)
    val y = new Rational(2, 3)
    var z = x add y
    println(x)
    println(y)
    println(z)
}