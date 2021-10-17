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
                val c=it.a*it.b
                val result = Result(it,c.toFloat())
                resultList.add(result)
            }
            is Calc.Minus -> TODO()
            is Calc.Multiply -> TODO()
            is Calc.Plus -> TODO()
        }


    }
    resultCallBack(resultList)

}

//       // if (a in 0..10) {
//            percent = "$number%"
//        } else
//            throw IllegalArgumentException(
//                "Значение должно быть в пределах от 0 до 10: " + "$number"
//            )
//
//
//    }

