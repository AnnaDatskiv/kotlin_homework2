import java.lang.IllegalArgumentException
import java.util.*

sealed class Calc (val a:Int, val b:Int) {
    class Plus(a: Int, b: Int) : Calc(a,b)
    class Minus(a: Int, b: Int) : Calc(a,b)
    class Divide(a: Int, b: Int) : Calc(a,b)
    class Multiply(a: Int, b: Int) : Calc(a,b)
}

data class Result(val operation:Calc, val result:Float)

fun calculate(list: List<Calc>,resultCallBack:(List<Result>)->Unit){
    val resultList = ArrayList<Result>()
    list.forEach {
        when(it){
            is Calc.Divide -> {
                if (it.a in 1..10 && it.b in 1..10){
                val c=it.a.toFloat()/it.b
                val result = Result(it,c)
                resultList.add(result)
            }
                else
                    throw IllegalArgumentException("Значения должны быть в пределах от 1 до 10")
            }

            is Calc.Minus -> {
                if (it.a in 1..10 && it.b in 1..10){
                val c=it.a-it.b
                val result = Result(it,c.toFloat())
                resultList.add(result)
            }
                else
                    throw IllegalArgumentException("Значения должны быть в пределах от 1 до 10")
            }


            is Calc.Multiply -> {
                    if (it.a in 1..10 && it.b in 1..10){
                val c=it.a*it.b
                val result = Result(it,c.toFloat())
                resultList.add(result)
            }
                    else
                        throw IllegalArgumentException("Значения должны быть в пределах от 1 до 10")
            }


            is Calc.Plus -> {
                    if (it.a in 1..10 && it.b in 1..10){
                val c=it.a+it.b
                val result = Result(it,c.toFloat())
                resultList.add(result)
            }
               else
                    throw IllegalArgumentException("Значения должны быть в пределах от 1 до 10")
            }
        }


    }
    resultCallBack(resultList)

}


