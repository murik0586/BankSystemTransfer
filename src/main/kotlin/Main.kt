import kotlin.math.max

const val MIN_COMMISSION_VISA = 35.0 //решил перевести в константы, все таки эти значения неизменяемые.
const val COMMISSION_VISA: Double = 0.0075

const val LIMIT_NO_COMMISSION_MONTH_MASTERCARD = 75_000//ЛИМИТ до какой суммы нет комиссии для мастерКард
const val MAX_TRANSFER_CARD_DAY = 150_000 //Максимальный перевод(вместе с историей переводов) в день
const val MAX_TRANSFER_CARD_MONTH = 600_000 //Максимальный перевод вместе с историей переводов в месяц

//ОШИБКИ
const val EXCEEDING_LIMIT_DAY = -1.0
const val EXCEEDING_LIMIT_MONTH = -2.0
const val INVALID_CARD_TYPE = -3.0

const val CARD_TYPE_MASTERCARD = "MasterCard"
const val CARD_TYPE_VISA = "Visa"
const val CARD_TYPE_MIR = "Мир"

fun main() {
    val amount = 150_001//Сумма

    when (val result: Double = calculationCommission(CARD_TYPE_MIR, amount)) {
        EXCEEDING_LIMIT_DAY -> println("Превышен лимит перевода за день")
        EXCEEDING_LIMIT_MONTH -> println("Превышен лимит перевода за месяц")
        INVALID_CARD_TYPE -> println("Неверный тип карты")
        else -> {
            println("Сумма которую вы хотите перевести (вместе с комиссией) ${amount + result}")
            println("Из них комиссии: $result рублей")
        }
    }
}

fun calculationCommission(cardType: String, amount: Int, transferHistoryMonth: Int = 10_000): Double {
    val totalAmount = amount + transferHistoryMonth //Делаем так только для того, чтобы историю считать было легче
    return when (cardType) {
        CARD_TYPE_MASTERCARD -> calculateMasterCardCommission(totalAmount, amount)
        CARD_TYPE_VISA -> calculateVisaCommission(totalAmount, amount)
        CARD_TYPE_MIR -> calculateMirCommission(totalAmount)
        else -> INVALID_CARD_TYPE

    }
}

fun calculateMirCommission(totalAmount: Int): Double {
    return when {
        totalAmount > MAX_TRANSFER_CARD_MONTH -> EXCEEDING_LIMIT_MONTH
        totalAmount > MAX_TRANSFER_CARD_DAY -> EXCEEDING_LIMIT_DAY
        else -> 0.0
    }
}

fun calculateVisaCommission(totalAmount: Int, amount: Int): Double {
    return when {
        totalAmount > MAX_TRANSFER_CARD_MONTH -> EXCEEDING_LIMIT_MONTH
        totalAmount > MAX_TRANSFER_CARD_DAY -> EXCEEDING_LIMIT_DAY
        else -> max(MIN_COMMISSION_VISA, amount * COMMISSION_VISA)
    }
}

fun calculateMasterCardCommission(totalAmount: Int, amount: Int): Double {
    return when {
        totalAmount > MAX_TRANSFER_CARD_MONTH -> EXCEEDING_LIMIT_MONTH
        totalAmount > MAX_TRANSFER_CARD_DAY -> EXCEEDING_LIMIT_DAY
        totalAmount > LIMIT_NO_COMMISSION_MONTH_MASTERCARD -> ((amount - LIMIT_NO_COMMISSION_MONTH_MASTERCARD) * 0.006) + 20
        else -> 0.0
    }
}
