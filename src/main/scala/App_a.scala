object App_a {

  def main(args: Array[String]): Unit = {

    println("Hello, Scala!")

    println("Введите строку: ")
    var s: String = scala.io.StdIn.readLine()

    manipulationString(s)

    //println(reverse(s))
    //println(LowerCase(s))
    //println(replace(s))

    //val s1: String = replace(s)
    //println(addStr(s1))


  }
  // разворачиваем строку
  //def reverse(s: String): String = {
  //    return((for (i <- s.length until 0 by -1) yield s(i - 1)).mkString)
  //  return(s.reverse)
  //}

  // перевод строки в нижний регистр
  //def LowerCase(s: String): String = {
  //  return (s.toLowerCase())
  //}

  //добавляем строку "and goodbye python!"
  //def addStr(s: String): String = {
  //  val s2:String = "and goodbye python!"
  //  return (s.concat(s2))
  //}

  // заменияем ! пробелом
  //def replace(s: String): String = {
  //  return (s.replaceAll("!", " "))
  //}

  def manipulationString(s: String): Unit = {

    val s1: String = "and goodbye python!"

    println(s.reverse)

    println(s.toLowerCase())

    println(s.replaceAll("!", " "))

    var s2: String = s.replace("!", " ")

    println(s2.concat(s1))
  }

}

