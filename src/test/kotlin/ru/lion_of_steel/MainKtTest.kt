package ru.lion_of_steel

import org.junit.Assert.assertEquals
import org.junit.Test


class MainKtTest {
    //Возможно излишество столько авто-тестов, но для практики сойдет.
    // Авто-тесты на функцию calculationCommission
    @Test
    fun calculationCommissionMasterCard() {
        val typeCard = CARD_TYPE_MASTERCARD
        val amount = 10_000
        val historyTransfer = 10_000
        val commissionExpected = 0.0
        val result = calculationCommission(typeCard, amount, historyTransfer)

        assertEquals(commissionExpected, result, 0.000001)
    }

    @Test
    fun calculationCommissionVisa() {
        val typeCard = CARD_TYPE_VISA
        val amount = 10_000
        val historyTransfer = 10_000
        val commissionExpected = 75.0
        val result = calculationCommission(typeCard, amount, historyTransfer)

        assertEquals(commissionExpected, result, 0.000001)
    }

    @Test
    fun calculationCommissionMir() {
        val typeCard = CARD_TYPE_MIR
        val amount = 10_000
        val commissionExpected = 0.0
        val result = calculationCommission(typeCard, amount)

        assertEquals(commissionExpected, result, 0.000001)
    }

    @Test
    fun calculationCommissionInvalidCardType() {
        val typeCard = "INVALID_CARD_TYPE"
        val amount = 10_000
        val historyTransfer = 10_000
        val result = calculationCommission(typeCard, amount, historyTransfer)

        assertEquals(INVALID_CARD_TYPE, result, 0.000001)
    }

    // Авто-тесты МастерКард на лимиты:

    @Test
    fun calculationCommissionMasterCardLimitDay() {
        val typeCard = CARD_TYPE_MASTERCARD
        val amount = 151_000
        val historyTransfer = 10_000
        val result = calculationCommission(typeCard, amount, historyTransfer)

        assertEquals(EXCEEDING_LIMIT_DAY, result, 0.000001)
    }

    @Test
    fun calculationCommissionMasterCardLimitMonth() {
        val typeCard = CARD_TYPE_MASTERCARD
        val amount = 601_000
        val historyTransfer = 10_000
        val result = calculationCommission(typeCard, amount, historyTransfer)

        assertEquals(EXCEEDING_LIMIT_MONTH, result, 0.000001)
    }
    // Авто-тест на функции карты мир, превышение лимита без комиссии:

    @Test
    fun calculationCommissionMasterCardLimitNoCommission() {
        val typeCard = CARD_TYPE_MASTERCARD
        val amount = 140_000
        val expectedCommission = 410.0
        val result = calculationCommission(typeCard, amount)

        assertEquals(expectedCommission, result, 0.000001)
    }

    // Авто-тесты на лимиты Visa:
    @Test
    fun calculationCommissionVisaLimitDay() {
        val typeCard = CARD_TYPE_VISA
        val amount = 141_000
        val result = calculationCommission(typeCard, amount)

        assertEquals(EXCEEDING_LIMIT_DAY, result, 0.000001)
    }

    @Test
    fun calculationCommissionVisaLimitMonth() {
        val typeCard = CARD_TYPE_VISA
        val amount = 600_000
        val result = calculationCommission(typeCard, amount)

        assertEquals(EXCEEDING_LIMIT_MONTH, result, 0.000001)
    }

    // Авто-тесты на лимиты Мир:

    @Test
    fun calculationCommissionMirLimitDay() {
        val typeCard = CARD_TYPE_MIR
        val amount = 141_000
        val result = calculationCommission(typeCard, amount)

        assertEquals(EXCEEDING_LIMIT_DAY, result, 0.000001)
    }
    @Test
    fun calculationCommissionMirLimitMonth() {
        val typeCard = CARD_TYPE_MIR
        val amount = 600_000
        val result = calculationCommission(typeCard, amount)

        assertEquals(EXCEEDING_LIMIT_MONTH, result, 0.000001)
    }


}