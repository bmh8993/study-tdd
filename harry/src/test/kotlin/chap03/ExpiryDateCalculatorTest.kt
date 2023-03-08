package chap03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class ExpiryDateCalculatorTest {

    @Test
    fun 만원_납부하면_한달_뒤가_만료일이_됨() {
        val payData = PayData(billingDate = LocalDate.of(2019, 3, 1), payAmount = 10_000)
        assertExpiryDate(payData, LocalDate.of(2019, 4, 1))

        val payData2 = PayData(billingDate = LocalDate.of(2019, 5, 5), payAmount = 10_000)
        assertExpiryDate(payData2, LocalDate.of(2019, 6, 5))
    }

    @Test
    fun 납부일과_한달_뒤_일자가_같지_않음() {
        val payData = PayData(billingDate = LocalDate.of(2019, 1, 31), payAmount = 10_000)
        assertExpiryDate(payData, LocalDate.of(2019, 2, 28))
    }

    @Test
    fun 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        val payData = PayData(LocalDate.of(2019, 1, 31), LocalDate.of(2019, 2, 28), 10_000)
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31))

        val payData2 = PayData(LocalDate.of(2019, 1, 30), LocalDate.of(2019, 2, 28), 10_000)
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30))

        val payData3 = PayData(LocalDate.of(2019, 5, 31), LocalDate.of(2019, 6, 30), 10_000)
        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31))
    }

    @Test
    fun 이만원_이상_납부하면_비례해서_만료일_게산() {
        val payData = PayData(billingDate = LocalDate.of(2019, 3, 1), payAmount = 20_000)
        assertExpiryDate(payData, LocalDate.of(2019, 5, 1))

        val payData2 = PayData(billingDate = LocalDate.of(2019, 3, 1), payAmount = 50_000)
        assertExpiryDate(payData2, LocalDate.of(2019, 8, 1))
    }

    @Test
    fun 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        val payData = PayData(LocalDate.of(2019, 1, 31), LocalDate.of(2019, 2, 28), 20_000)
        assertExpiryDate(payData, LocalDate.of(2019, 4, 30))
    }

    @Test
    fun 십만원을_납부하면_1년_제공() {
        val payData = PayData(billingDate = LocalDate.of(2019, 1, 28), payAmount = 100_000)
        assertExpiryDate(payData, LocalDate.of(2020, 1, 28))
    }

    @Test
    fun 십삼만원_납부시_1년_3개월_제공() {
        val payData = PayData(billingDate = LocalDate.of(2019, 1, 28), payAmount = 130_000)
        assertExpiryDate(payData, LocalDate.of(2020, 4, 28))
    }

    @Test
    fun 이십삼만원_납부시_2년_3개월_제공() {
        val payData = PayData(billingDate = LocalDate.of(2019, 1, 28), payAmount = 230_000)
        assertExpiryDate(payData, LocalDate.of(2021, 4, 28))
    }

    private fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        val cal = ExpiryDateCalculator()
        val realExpiryDate = cal.calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}