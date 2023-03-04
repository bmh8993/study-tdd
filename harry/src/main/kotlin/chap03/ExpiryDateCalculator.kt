package chap03

import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {

    fun calculateExpiryDate(payDate: PayData): LocalDate {
        val addedMonths = calculatorAddedMonths(payDate.payAmount).toLong()

        return if (payDate.firstBillingDate != null)
            expiryDateUsingFirstBillingDate(payDate, addedMonths)
        else payDate.billingDate.plusMonths(addedMonths)
    }

    private fun expiryDateUsingFirstBillingDate(payDate: PayData, addedMonths: Long): LocalDate {
        val candidateExp = payDate.billingDate.plusMonths(addedMonths)
        val dayOfFirstBilling = payDate.firstBillingDate!!.dayOfMonth

        return if (isSameDayOfMonth(payDate.firstBillingDate, candidateExp)) {
            candidateExp
        } else {
            val dayLenOfCandiMon = lastDayOfMonth(candidateExp)
            if (dayLenOfCandiMon < payDate.firstBillingDate.dayOfMonth) {
                candidateExp.withDayOfMonth(dayLenOfCandiMon)
            } else candidateExp.withDayOfMonth(dayOfFirstBilling)
        }
    }

    private fun calculatorAddedMonths(payAmount: Int): Int {
        val months = (payAmount / 100000) * 12
        val remaining = (payAmount % 100000) / 10000

        return months + remaining
    }

    private fun isSameDayOfMonth(date1: LocalDate, date2: LocalDate) =
        date1.dayOfMonth == date2.dayOfMonth

    private fun lastDayOfMonth(date: LocalDate) = YearMonth.from(date).lengthOfMonth()

}