package example

object Hello extends Greeting with App {
  val name = "reader"
    println(s"Hello, $name")
    val x =  f"${math.Pi}%.5f"
    println(x)
    var a = name indexOf('r')
    var b = name indexOf('r', 3)
    var c = name.indexOf('r)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
