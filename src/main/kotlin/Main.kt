import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    runBlocking {
        }

        val inputList = ArrayList<Calc>()
            val myPlus = Calc.Plus(3, 5)
            val myMinus = Calc.Minus(4, 9)
            val myDivide = Calc.Divide(6, 1)
            val myMultiply = Calc.Multiply(4, 7)

            inputList.add(myPlus)
            inputList.add(myMinus)
            inputList.add(myDivide)
            inputList.add(myMultiply)


            try {
                calculate(inputList) { resultList ->
                    println(resultList.toString())
                }
            } catch (e: IllegalArgumentException) {
                println(e.message.toString())
            }
        }


