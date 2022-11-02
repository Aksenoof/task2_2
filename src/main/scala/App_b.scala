import scala.collection.immutable
import scala.util.control.Breaks._

object App_b {
  def main(args: Array[String]): Unit = {

    // список зарплат по отделу
    //val salaryList: List[Double] = List(100, 150, 200, 80, 120, 75)
    println("Введите список зарплат по отделу через запятую: ")
    var salaryListInput = scala.io.StdIn.readLine()

    var salaryList: List[Double] = salaryListAdapt(salaryListInput)

    //ввод данных для расчета оклада сотрудника
    println("Введите 1 если нужно расчитать оклад и зарплату сотрудника, 0 если нет")
    var answerСalcSalary = scala.io.StdIn.readLine().toInt

    var salaryFull: Double = 0
    var salary: Double = 0

      if (answerСalcSalary == 1){
      println("Введите годовой доход сотрудника до вычета налогов: ")
      var yearSalary = scala.io.StdIn.readLine().toDouble

      println("Введите премию сотрудника в %: ")
      var bonus = scala.io.StdIn.readLine().toDouble

      println("Введите компенсацию питания сотрудника: ")
      var foodBonus = scala.io.StdIn.readLine().toDouble

      // ввод данных для расчета полной зарплаты с учетом бонуса за результат работы
      println("Введите сумму за результаты работы: ")
      var bonusPerformance = scala.io.StdIn.readLine().toDouble

      println("Введите 1 если результат работы положительный, 0 если отрицательный: ")
      var answerWorkResult = scala.io.StdIn.readLine().toInt

      // оклад сотрудника
      salary = floor(salaryСalculation(yearSalary, bonus, foodBonus))
      println(f"Оклад сотрудника: $salary")

      // зарплата сотрудника с учетом результатов работы
      salaryFull= floor(salaryTotal(salary, bonusPerformance, answerWorkResult))
      println(f"Зарплата сотрудника с учетом результатов работы: $salaryFull")

      // добавляем зарплату введеного сотрудника
      salaryList = salaryList :+ salaryFull
    }

    // добавление зарплаты новых сотрудников в общий список
    println("Введите 1 если нужно добавить зарплату сотрудника, 0 если нет")
    var answerSalaryNew = scala.io.StdIn.readLine().toInt

    var salaryListTemp: List[Double] = Nil
    var salaryNew: Double = 0

    // создаем список зарплат новых сотрудников
    while (answerSalaryNew == 1) {
      println("Введите зарплату сотрудника")
      salaryNew = scala.io.StdIn.readDouble()

      salaryListTemp = salaryListTemp :+ salaryNew

      println("Введите 1 если нужно добавить зарплату сотрудника, 0 если нет")
      answerSalaryNew = scala.io.StdIn.readInt()
    }
    // добавляем спиок зарплат новых сотрудников
    salaryList = salaryList ::: salaryListTemp

    // общий список зарплат по отделу
    val salaryListDepartment = salaryList
    println("Список зарплат сотрудников: " + salaryListDepartment)

    // средняя зарплата по отделу
    val averageSalary: Double = floor(average(salaryListDepartment))
    println(f"Средний зарплата по отделу: $averageSalary")

    // отклонение зарплаты от среднего если расчитывалось зарплата сотрудника
    if (answerСalcSalary == 1) {
      deviation(salary, averageSalary)
    }

    // масимальная зарплата по отделу
    val maxSalaryDepartment = salaryListDepartment.max
    println(f"Максимальный зарплата по отделу: $maxSalaryDepartment")

    // минимальная зарплата по отделу
    val minSalaryDepartment = salaryListDepartment.min
    println(f"Минимальный зарплата по отделу: $minSalaryDepartment")

    // сортировка списка зарплат по отделу по возрастанию
    val salaryListDepartmentSorted = salaryListDepartment.sorted
    println ("Отсортированный по возрастанию список зарплат: " + salaryListDepartmentSorted)

    // добаление зарплаты нового сотрудника в список по индексу
    println("Введите 1 если нужно добавить нового сотрудника, 0 если нет")
    var answerAddWorker = scala.io.StdIn.readInt().toInt

    var salaryListDep: List[Double] = Nil

    if (answerAddWorker == 1) {
      println("Введите зарплату нового сотрудника:")
      val salaryAddWorker = scala.io.StdIn.readDouble().toDouble

      salaryListDep = salaryInsert(salaryListDepartmentSorted, salaryAddWorker)
      println("Список зарпалат сотрудников: " + salaryListDep)
    } else {
      salaryListDep = salaryListDepartmentSorted
    }

    // список индексов сотрудников Middle
    println("Введите 1 если нужно вывести список индексов сотрудников Middle , 0 если нет")
    var answerMiddle = scala.io.StdIn.readInt().toInt

    var minSalaryMiddle: Double = 0
    var maxSalaryMiddle: Double = 0

    if (answerMiddle == 1) {
      println("Введите min зарплату для middle")
      minSalaryMiddle = scala.io.StdIn.readDouble()

      println("Введите max зарплату для middle")
      maxSalaryMiddle = scala.io.StdIn.readDouble()

      println("Список индексов сотрудников Middle: " + indexMiddle(salaryListDep, minSalaryMiddle, maxSalaryMiddle))
    }

    // индексация зарплат на уровень инфляции
    println("Введите 1 если нужно индексировать зарплаты на уровень инфляции, 0 если нет")
    val answerIndexing: Int = scala.io.StdIn.readInt()

    var inflation: Double = 0

    if (answerIndexing == 1) {
      println("Введите уровень инфляции в %:")
      inflation = scala.io.StdIn.readDouble()

      println("Список проиндексированных зарплат по инфляции: " + salaryIndexedInflation(salaryListDepartmentSorted, inflation))
    }

    //индексация зарплат на уровень рынка
    println("Введите 1 если нужно индексировать зарплаты на уровень рынка, 0 если нет")
    val answerIndexing1: Int = scala.io.StdIn.readInt()

    if (answerIndexing1 == 1) {
      println("Введите среднюю зарплату junior:")
      val salaryMeanJunior: Double = scala.io.StdIn.readDouble()

      println("Введите среднюю зарплату middle:")
      val salaryMeanMiddle: Double = scala.io.StdIn.readDouble()

      println("Введите среднюю зарплату senior")
      val salaryMeanSenior: Double = scala.io.StdIn.readDouble()

      if (answerMiddle != 1) {
        println("Введите min зарплату для middle")
        minSalaryMiddle = scala.io.StdIn.readDouble()

        println("Введите max зарплату для middle")
        maxSalaryMiddle = scala.io.StdIn.readDouble()
      }
      println("Список проиндексированных зарплат по уровню рынка" + salaryIndexedLevel(salaryListDepartmentSorted, minSalaryMiddle, maxSalaryMiddle,salaryMeanJunior,salaryMeanMiddle,salaryMeanSenior))
    }

  }

  //--------- ФУНКЦИИ------------------------------
  // подготовка списка зарплат отдела
   def salaryListAdapt(x: String): List[Double] = {

     var listTemp = x.replace("(", " ").replace(")", " ")

     var listTemp2 = listTemp.split(",")

     var listTemp3: List[Double] = Nil

     var sTemp: Double = 0

     for (i <- listTemp2) {
       listTemp3 = listTemp3 :+ i.toDouble
     }
     return(listTemp3)
   }

  // расчет оклада сотрудника
  def salaryСalculation(a: Double, a1: Double, a2: Double): Double = ((a * (a1 / 100)) + a + a2) / 12

  // расчет средней зарплаты по отделу
  def average(x: List[Double]): Double = x.sum / x.length

  // округление
  def floor(x: Double): Double = java.lang.Math.floor(x)

  // расчет отклонения зарплаты сотрудника от средней зарплаты по отделу
  def deviation(x: Double, y: Double ): Unit = {

    val percent: Double = ((x * 100) / y) - 100

    val absPercent: Double = java.lang.Math.abs(percent)

    if (percent > 0) {
      println(f"Зарплата сотрудника больше средней по отделу на $absPercent%1.2f" + "%")
    } else if (percent == 0) {
      println("Зарплата сотрудника равена средней зарплате по отделу")
    } else if (percent < 0) {
      println(f"Зарплата сотрудника меньше средней зарплаты по отделу на $absPercent%1.2f" + "%")
    }
  }

  // расчет зарплаты сотрудника с учетом результатов работы
  def salaryTotal(x: Double, y: Double, z: Int): Double = {

    if (z == 1) {
      return(x + y)
    } else {
      return(x - y)
    }
  }

  // получение списка индексов middle сотрудников
  def indexMiddle(x: List[Double], y: Double, z: Double): List[Int] = {

    var sTemp = x

    var indexList: List[Int] = Nil

    for (i <- sTemp ) {
      if (y <= i && i <= z) {
        indexList = indexList :+ sTemp.indexOf(i)
        sTemp = sTemp.updated(sTemp.indexOf(i), 0)
      }
    }
    return(indexList)
  }

  // индексация зарплат по инфляции
  def salaryIndexedInflation(x: List[Double], y: Double): List[Double] = {

    var salaryIndexedList: List[Double] = Nil

    for (i <- x) {
      var s: Double  = i + (i * y / 100)
      salaryIndexedList = salaryIndexedList :+ s
    }
    return(salaryIndexedList)
  }

  // добаление зарплаты нового сотрудника в список по индексу
  def salaryInsert(x: List[Double], y: Double): List[Double] = {

    var ListNew: List[Double] = x

    breakable {
      for (i <- ListNew) {
        if (y <= i) {
          ListNew = ListNew.take(ListNew.indexOf(i)) ++ List(y) ++ ListNew.drop(ListNew.indexOf(i))
          //val (front, back) = ListNew.splitAt(ListNew.indexOf(i))
          //ListNew = front ++ List(y) ++ back
          break
        }
      }
    }
    return(ListNew)
  }

  // индексация зарплат на уровень рынка
   def salaryIndexedLevel(x: List[Double], y: Double, z: Double, s1: Double, s2: Double, s3: Double): List[Double] = {

     var salaryListLevel: List[Double] = List()

     for (i <- x) {
       if (y > i) {
         salaryListLevel = salaryListLevel :+ calcSalary(i, s1)
       } else if (y <= i && i <= z) {
         salaryListLevel = salaryListLevel :+ calcSalary(i, s2)
       } else if (z < i) {
         salaryListLevel = salaryListLevel :+ calcSalary(i, s3)
       }
     }
     return (salaryListLevel)
   }

  // расчет зарплаты по средней рыночной зарплате
  def calcSalary(x: Double, y: Double): Double = {

    var percentDev, salaryTemp: Double = 0

    percentDev = 100 - ((x * 100) / y)

    if (percentDev > 0) {
      salaryTemp = floor(x + (x * percentDev / 100))
    } else if (percentDev <= 0) {
      salaryTemp = x
    }
    return(salaryTemp)
  }

}
