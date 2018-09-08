package example

case class Person(name: String,
                  isMale: Boolean,
                  children: Person*)

object ForExpression extends Greeting with App {
    val map = Map("one" -> 1, "two" -> 2)
    val list1 = for {
        (key, value) <- map // 本行和下一行将会被翻译成什么语句呢？
        i10 = value + 10
    } yield i10
    // 执行结果值: list1: scala.collection.immutable.Iterable[Int] = List(11, 12)

    // 翻译后的语句:
    val list2 = for {
        (i, i10) <- for {
            x1@(key, value) <- map
        } yield {
            val x2@i10 = value + 10
            (x1, x2)
        }
    } yield i10
    // 执行结果: list2: scala.collection.immutable.Iterable[Int] = List(11, 12)

    val list2Inter = for {
        x1@(key, value) <- map
    } yield {
        val x2@i10 = value + 10
        (x1, x2)
    }

    val z@(x, y) = 1 -> 2
    println("z: " + z)


    val list2InterEqual = map map { p => {
        val (x, y) = p
        (x, y) -> (y + 10)
    }
    }

    val list2InterEqual2 = map map { p => {
        val (x, y) = p
        ((x, y), y + 10)
    }
    }

    val list2Equal = map map { p => {
        val (x, y) = p
        (x, y) -> (y + 10)
    }
    } map { k => {
        val (m, n) = k
        n
    }
    }

    val list2Equal2 = map map { p => {
        val (_, y) = p
        y + 10
    }
    }

    println(map)
    println(list1)
    println(list2)
    println(list2Inter)
    println(list2InterEqual)
    println(list2InterEqual2)
    println(list2Equal)
    println(list2Equal2)
}

trait Greeting {
    lazy val greeting: String = "hello"
}
