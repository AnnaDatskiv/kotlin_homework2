import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.jvm.Throws

sealed class Calc(open val a: Int,open val b: Int) {
    data class Plus(override val a: Int,override val b: Int) : Calc(a, b)
    data class Minus(override val a: Int,override val b: Int) : Calc(a, b)
    data class Divide(override val a: Int,override val b: Int) : Calc(a, b)
    data class Multiply(override val a: Int,override val b: Int) : Calc(a, b)
}

data class Result(val operation: Calc, val result: Float)

suspend fun calculate(list: List<Calc>, resultCallBack: (List<Result>) -> Unit) {
    val resultList = ArrayList<Result>()
    list.forEach {
        coroutineScope {
            try {
                checkRange(it.a, it.b)

                when (it) {
                    is Calc.Divide -> {
                        val c = async {it.a.toFloat() / it.b}
                        val result = Result(it, c.await())
                        resultList.add(result)
                    }

                    is Calc.Minus -> {
                        val c = async {it.a - it.b}
                        val result = Result(it, c.await().toFloat())
                        resultList.add(result)
                    }


                    is Calc.Multiply -> {
                        val c = async {it.a * it.b}
                        val result = Result(it, c.await().toFloat())
                        resultList.add(result)
                    }


                    is Calc.Plus -> {
                        val c = async {it.a + it.b}
                        val result = Result(it, c.await().toFloat())
                        resultList.add(result)
                    }
                }
            } catch (e: IllegalArgumentException) {
                println(e.message.toString())
            }
        }
    }
    resultCallBack(resultList)
}

@Throws(IllegalArgumentException::class)
fun checkRange(a: Int, b: Int) {
    if (a !in 1..10 || b !in 1..10)
        throw IllegalArgumentException("а=$a b=$b. Оба значения должны быть в пределах от 1 до 10")
}








//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.lang.IllegalArgumentException
//import java.util.*
//import kotlin.jvm.Throws
//
//sealed class Calc(open val a: Int,open val b: Int) {
//    data class Plus(override val a: Int,override val b: Int) : Calc(a, b)
//    data class Minus(override val a: Int,override val b: Int) : Calc(a, b)
//    data class Divide(override val a: Int,override val b: Int) : Calc(a, b)
//    data class Multiply(override val a: Int,override val b: Int) : Calc(a, b)
//}
//
//data class Result(val operation: Calc, val result: Float)
//
//suspend fun calculate(list: List<Calc>, resultCallBack: (List<Result>) -> Unit) {
//    val resultList = ArrayList<Result>()
//    list.forEach {
//        withContext(Dispatchers.IO) {
//            try {
//                checkRange(it.a, it.b)
//
//                when (it) {
//                    is Calc.Divide -> {
//                        val c = it.a.toFloat() / it.b
//                        val result = Result(it, c)
//                        resultList.add(result)
//                    }
//
//                    is Calc.Minus -> {
//                        val c = it.a - it.b
//                        val result = Result(it, c.toFloat())
//                        resultList.add(result)
//                    }
//
//
//                    is Calc.Multiply -> {
//                        val c = it.a * it.b
//                        val result = Result(it, c.toFloat())
//                        resultList.add(result)
//                    }
//
//
//                    is Calc.Plus -> {
//                        val c = it.a + it.b
//                        val result = Result(it, c.toFloat())
//                        resultList.add(result)
//                    }
//                }
//            } catch (e: IllegalArgumentException) {
//                println(e.message.toString())
//            }
//        }
//    }
//        resultCallBack(resultList)
//    }
//
//    @Throws(IllegalArgumentException::class)
//    fun checkRange(a: Int, b: Int) {
//        if (a !in 1..10 || b !in 1..10)
//            throw IllegalArgumentException("а=$a b=$b. Оба значения должны быть в пределах от 1 до 10")
//    }