import kotlinx.coroutines.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {


    val inputList = ArrayList<Calc>()
    val myPlus = Calc.Plus(0, 5)
    val myMinus = Calc.Minus(4, 9)
    val myDivide = Calc.Divide(6, 1)
    val myMultiply = Calc.Multiply(4, 7)

    inputList.add(myPlus)
    inputList.add(myMinus)
    inputList.add(myDivide)
    inputList.add(myMultiply)

    runBlocking {
        calculate(inputList) { resultList ->
            println(resultList.toString())
        }
    }
}



