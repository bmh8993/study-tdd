package chap02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordStrengthMeterTest {

    private val meter = PasswordStrengthMeter()

    private fun assertStrength(password: String?, expStr: PasswordStrength) {
        val result = meter.meter(password)
        assertEquals(result, expStr)
    }

    @Test
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("a12!@ABC", PasswordStrength.STRONG)
        assertStrength("abc1!Add", PasswordStrength.STRONG)
    }

    @Test
    fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("a12!@A", PasswordStrength.NORMAL)
        assertStrength("B1A@a", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL)
    }

    @Test
    fun nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID)
        assertStrength("", PasswordStrength.INVALID)
    }

    @Test
    fun meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abcdefghi", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABCDE", PasswordStrength.WEAK)
    }

    @Test
    fun meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK)
    }
}