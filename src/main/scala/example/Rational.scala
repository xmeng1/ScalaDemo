package example

/**
  *
  * <p>Date:    30/07/18
  *
  * @author mengxin
  * @version 1.0
  */
class Rational (n: Int, d:Int) {
    require(d != 0)

    override def toString: String = n + "/" + d
}


object test extends App {
    var x = new Rational(1,2)
    println(x)
}