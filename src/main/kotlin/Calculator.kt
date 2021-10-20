import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.jvm.Throws

sealed class Calc(val a: Int, val b: Int) {
    class Plus(a: Int, b: Int) : Calc(a, b)
    class Minus(a: Int, b: Int) : Calc(a, b)
    class Divide(a: Int, b: Int) : Calc(a, b)
    class Multiply(a: Int, b: Int) : Calc(a, b)
}

data class Result(val operation: Calc, val result: Float)

suspend fun calculate(list: List<Calc>, resultCallBack: (List<Result>) -> Unit) {
    val resultList = ArrayList<Result>()
    list.forEach {
        withContext(Dispatchers.IO) {
            when (it) {
                is Calc.Divide -> {
                    try {
                        checkRange(it.a, it.b)
                        val c = it.a.toFloat() / it.b
                        val result = Result(it, c)
                        resultList.add(result)
                    } catch (e: IllegalArgumentException) {
                        println(e.message.toString())
                    }
                }

                is Calc.Minus -> {
                    try {
                        checkRange(it.a, it.b)
                        val c = it.a - it.b
                        val result = Result(it, c.toFloat())
                        resultList.add(result)
                    } catch (e: IllegalArgumentException) {
                        println(e.message.toString())
                    }
                }


                is Calc.Multiply -> {
                    try {
                        checkRange(it.a, it.b)
                        val c = it.a * it.b
                        val result = Result(it, c.toFloat())
                        resultList.add(result)
                    } catch (e: IllegalArgumentException) {
                        println(e.message.toString())
                    }
                }


                is Calc.Plus -> {
                    try {
                        checkRange(it.a, it.b)
                        val c = it.a + it.b
                        val result = Result(it, c.toFloat())
                        resultList.add(result)
                    } catch (e: IllegalArgumentException) {
                        println(e.message.toString())
                    }
                }
            }
        }
    }
    resultCallBack(resultList)
}

@Throws(IllegalArgumentException::class)
fun checkRange(a: Int, b: Int) {
    if (a !in 1..10 || b !in 1..10) {
        throw IllegalArgumentException("Значения должны быть в пределах от 1 до 10")
    }
}