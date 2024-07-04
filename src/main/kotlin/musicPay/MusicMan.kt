package musicPay

const val START_ONE_DISCOUNT = 1_001
const val ONE_DISCOUNT = 100
const val START_TWO_DISCOUNT = 10_001

fun main() {
    val amount = 15000
    val regularCustomer = true

    println("Итоговая сумма с учетом скидки: ${calculationTotalAmount(amount, regularCustomer)} рублей")
}

fun calculationTotalAmount(amount: Int, regularCustomer: Boolean): Double {
    var finalAmount = amount.toDouble()
    if (finalAmount >= (START_ONE_DISCOUNT) && amount <= START_TWO_DISCOUNT) {
        finalAmount -= ONE_DISCOUNT

    } else if (finalAmount >= START_TWO_DISCOUNT) {
        finalAmount -= (finalAmount / ONE_DISCOUNT * 5)
    }
    if (regularCustomer) {
        finalAmount -= (finalAmount / ONE_DISCOUNT)
    }
    return finalAmount

}