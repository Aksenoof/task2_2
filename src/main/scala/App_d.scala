import scala.annotation.tailrec

object App_d {
  def main(args: Array[String]): Unit = {

    println(exp(23))

    println(expTailRec(2, 23))

  }

  // вычисляет значение степени двойки с помощью обычной рекурсии
  def exp(n: Int): Int = {
    if (n == 1) 2
    else 2 * exp(n-1)
  }

  // вычисляет значение степени двойки с помощью хвостовой рекурсии
  // усовершенствоовал x в степени y
  def expTailRec(m: Int, n: Int): Long = {

    @tailrec def exp1(x: Long, n: Int): Long = {
      if (n == 1)
        x
      else
        exp1(x * m, n - 1)
    }

    exp1(m, n)
  }

}
