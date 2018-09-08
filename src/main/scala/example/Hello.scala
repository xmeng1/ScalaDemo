package example

case class Person(name: String,
                  isMale: Boolean,
                  children: Person*)

object Hello extends Greeting with App {
    /*
        val name = "reader"
       println(s"Hello, $name")
       val x = f"${math.Pi}%.5f"
       println(x)
       var a = name indexOf ('r')
       var b = name indexOf('r', 3)
       var c = name.indexOf('r)
    * */

    val lara = Person("Lara", isMale = false)
    val bob = Person("Bob", isMale = true)
    val julie = Person("Julie", false, lara, bob)
    val persons = List(lara, bob, julie)


    var temp1 = persons filter (p => !p.isMale)
    var temp11 = temp1 flatMap (k => k.name)
    var temp12 = temp1 flatMap (k => k.children)
    var temp2 = temp1 flatMap (p => p.children map (c => (p.name, c.name)))

    println(temp1)
    println(temp11)
    println(temp12)
    println(temp2)

    val temp3 = persons withFilter (p => !p.isMale) flatMap (p => p.children map (c => (p.name, c.name)))
    println(temp3)

    val temp4 = for {
        p <- persons
        if !p.isMale
        c <- p.children
    } yield (p.name, c.name)
    println(temp4)

    val temp5 = for {
        p <- persons // a generator
        n = p.name // a definition
        if n startsWith "To" // a filter
    } yield n
    println(temp5)

    val temp6 = for (
        x <- List(1, 2);
        y <- List("one", "two")
    ) yield (x, y)
    println(temp6)

    val temp6Equivalence = List(1, 2) flatMap (p => { List("one", "two") map ( k => (p,k))})

    println(temp6Equivalence)

    val temp7 = for (
        x <- List(1, 2);
        y <- List("one", "two");
        z <- List("a", "b")
    ) yield (x, y, z)
    println(temp7)

    val temp7Equivalence = List(1, 2) flatMap (p => { List("one", "two") flatMap  ( k => { List("a", "b") map (l => (p,k,l))})})

    println(temp7Equivalence)

    val states = List("Alabama", "Alaska", "Virginia", "Wyoming")
    val temp8 = for {
        s <- states
        c <- s
    } yield s"$c-${c.toUpper}"
    // 结果值: List("A-A", "l-L", "a-A", "b-B", ...)
    val temp8Equivalence = states flatMap (_.toSeq map (c => s"$c-${c.toUpper}"))
    val temp8Equivalence2 = states flatMap (p => p map (c => s"$c-${c.toUpper}"))
    // 结果值: List("A-A", "l-L", "a-A", "b-B", ...)
    println(temp8)
    println(temp8Equivalence)
    println(temp8Equivalence2)


    val temp9 = for {
        s <- states
        c <- s
        if c.isLower
    } yield s"$c-${c.toUpper}"
    // 结果值: List("l-L", "a-A", "b-B", ...)
    val temp9Equivalence = states flatMap (_.toSeq withFilter (_.isLower) map (c => s"$c-${c.toUpper}"))
    val temp9Equivalence2 = states flatMap (p => p withFilter (k => k.isLower) map (c => s"$c-${c.toUpper}"))
    val temp9Equivalence3 = states flatMap (p => p filter (k => k.isLower) map (c => s"$c-${c.toUpper}"))
    println(temp9)
    println(temp9Equivalence)
    println(temp9Equivalence2)
    println(temp9Equivalence3)
}

trait Greeting {
    lazy val greeting: String = "hello"
}
