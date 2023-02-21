import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorTest {
    @Test
    fun plus() {
        val result = Calculator.plus(1,2)
        assertEquals(3, result)
        assertEquals(5, Calculator.plus(4, 1))
    }
}