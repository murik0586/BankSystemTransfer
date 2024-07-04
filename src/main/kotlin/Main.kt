
const val MIN_COMMISSION_VISA_AND_MIR = 35 //решил перевести в константы, все таки эти значения неизменяемые.
const val COMMISSION_VISA_MIR = 0.0075

fun main() {

    println("Введите сумму перевода")
    val amount: Int = readln().toInt()//или вписать без редлайн - напрямую сумму
    val finalCommissionVisaAndMir: Double = amount * COMMISSION_VISA_MIR

    val result: Double = if (finalCommissionVisaAndMir > MIN_COMMISSION_VISA_AND_MIR) finalCommissionVisaAndMir else MIN_COMMISSION_VISA_AND_MIR.toDouble() //if в качестве выражения.
    println("комиссия при переводе: $result")


}