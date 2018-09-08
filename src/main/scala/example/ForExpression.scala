package example

case class Person(name: String,
                  isMale: Boolean,
                  children: Person*)

object Hello extends Greeting with App {
    val map = Map("one" -> 1, "two" -> 2)
    val list1 = for {
        (key, value) <- map // 本行和下一行将会被翻译成什么语句呢？
        i10 = value + 10
    } yield i10
    // 执行结果值: list1: scala.collection.immutable.Iterable[Int] = List(11, 12)

    // 翻译后的语句:
    val list2 = for {
        (i, i10) <- for {
            x1 @ (key, value) <- map
        } yield {
            val x2 @ i10 = value + 10
            (x1, x2)
        }
    } yield i10
    // 执行结果: list2: scala.collection.immutable.Iterable[Int] = List(11, 12)
}

trait Greeting {
    lazy val greeting: String = "hello"
}
