import scala.collection.immutable.Set

object App_c {
  def main(args: Array[String]): Unit = {

    // список зарплат по отделу
    val salaryList: List[Double] = List(100, 150, 200, 80, 120, 75)

    // список сотрудников отдела
    val  fullNameList: List[String] = List("Ким Илья", "Иванов Виктор", "Степанов Дмитрий", "Петров Николай", "Сидоров Василий", "Зинин Игнат")

    // создаем изменяемый массив Map
    val m = new scala.collection.mutable.HashMap[String, Double]

    val range: Int = salaryList.length

    for (i <- 0 until range) {
      m += (fullNameList(i) -> salaryList(i))
    }
    println(m)

    // вывод сотрудника с min зарплатой
    val minResult: (String, Double) = m.min
    println("Минимальная зарплата: " + minResult._1)

    // вывод сотрудника с max зарплатой
    val maxResult: (String, Double) = m.max
    println("Максимальная зарплата: " + maxResult._1)

    // удаляем имя сотрудника
    val m1 = new scala.collection.mutable.HashMap[String, Double]

    for((k, v) <- m) {
      val result1 = k.split(" ", 2)
      val result2 = result1(0)
      m1 += (result2 -> v)
    }
    println(m1)

    //переводим в нижний регистр фамилию
    val m2 = new scala.collection.mutable.HashMap[String, Double]

    for ((k, v) <- m1) {
      val result1 = k.toLowerCase()
      m2 += (result1 -> v)
    }
    println(m2)

    //удаление гласных и разворот оставшихся символов
    val m3 = new scala.collection.mutable.HashMap[String, Double]

    for ((k, v) <- m2) {
      val result1 = checkVowels(k)
      val result2 = result1.reverse
      m3 += (result2 -> v)
    }
    println(m3)
  }

  // удаляем гласные из фамилии
  def checkVowels(str: String): String = {
    val isVowel = Set('а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я')

    if (str.isEmpty) ""
    else (if (!isVowel(str.head)) str.head else "") + checkVowels(str.tail)
  }

}
