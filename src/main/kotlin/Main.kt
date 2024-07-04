const val MIN_COMMISSION_VISA = 35 //решил перевести в константы, все таки эти значения неизменяемые.
const val COMMISSION_VISA = 0.0075

const val LIMIT_NO_COMMISSION_MONTH_MASTERCARD = 75_000//ЛИМИТ до какой суммы нет комиссии для мастерКард
const val MAX_TRANSFER_CARD_DAY = 150_000 //Максимальный перевод(вместе с историей переводов) в день
const val MAX_TRANSFER_CARD_MONTH = 600_000 //Максимальный перевод вместе с историей переводов в месяц
fun main() {
    val cardType = "Visa"//отвечает за тип карты
    val amount = 140_000//Сумма

    val result: Double = calculationCommission(cardType, amount)

    println("Сумма которую вы хотите перевести (вместе с комиссией) ${amount + result}")
    println("Из них комиссии: $result рублей")

}

fun calculationCommission(cardType: String, amount: Int, transferHistoryMonth: Int = 10_000): Double {
    val finalAmount = amount + transferHistoryMonth //Делаем так только для того, чтобы историю считать было легче
    return when (cardType) {
        "MasterCard" -> {
            when (finalAmount) {
                in (LIMIT_NO_COMMISSION_MONTH_MASTERCARD + 1)..<MAX_TRANSFER_CARD_DAY -> { //если сумма больше лимита, но меньше перевода макс за день! вычитаем лимит
                    return ((finalAmount - LIMIT_NO_COMMISSION_MONTH_MASTERCARD) * 0.006) + 20
                }

                in (MAX_TRANSFER_CARD_DAY + 1)..<MAX_TRANSFER_CARD_MONTH -> {
                    println("Превышен лимит перевода за день")
                    0.0//комиссии нет, если лимит превышен, значит комиссия 0.0
                }

                else -> {
                    println("Превышен лимит перевода за месяц")
                    0.0
                }
            }
        }

        "Visa" -> {

            when {
                finalAmount in (MAX_TRANSFER_CARD_DAY + 1)..<MAX_TRANSFER_CARD_MONTH -> {
                    println("Превышен лимит перевода за день")
                    0.0
                }

                finalAmount > MAX_TRANSFER_CARD_MONTH -> {
                    println("Превышен лимит перевода за месяц")
                    0.0
                }


                else -> {
                    val commission = amount * COMMISSION_VISA
                    if (commission >= MIN_COMMISSION_VISA) {
                        return commission
                    } else {
                        return MIN_COMMISSION_VISA.toDouble()
                    }
                }
            }
        }

        "Мир" -> {
            when {
                finalAmount > MAX_TRANSFER_CARD_DAY -> {
                    if (finalAmount in (MAX_TRANSFER_CARD_DAY + 1)..<MAX_TRANSFER_CARD_MONTH) {
                        println("Превышен лимит перевода за день")
                        return 0.0
                    } else println("Превышен лимит перевода за месяц")
                    0.0
                }

                else -> 0.0

            }
        }

        else -> 0.0

    }

}
