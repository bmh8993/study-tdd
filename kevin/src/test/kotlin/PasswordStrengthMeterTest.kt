import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordStrengthMeterTest {

    private val meter = PasswordStrengthMeter()
    private fun assertStrength(password: String?, expStr: PasswordStrength) {
        val result = meter.meter(password)
        assertEquals(expStr, result)
    }


    @Test
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@ABAA", PasswordStrength.STRONG)
        assertStrength("ab134!@AAAA" , PasswordStrength.STRONG)
    }

    @Test
    fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        val result = meter.meter("ab12!@A")
        assertEquals(PasswordStrength.NORMAL, result)
        val result2 = meter.meter("Ab12!c")
        assertEquals(PasswordStrength.NORMAL, result2)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        val result = meter.meter("ab!@ABqwer")
        assertEquals(PasswordStrength.NORMAL, result)
    }

    @Test
    fun nullInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID )
    }

    @Test
    fun meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABFEZ", PasswordStrength.WEAK)
    }

    @Test
    fun meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK)
    }

}