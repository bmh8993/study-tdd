package com.tdd.zayden.passwordstrengthmeter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PasswordStrengthMeterTest {

    /**
     * 1. 존재하지 않는 타입에 대한 컴파일 에러 발생
     * 2. 컴파일 에러 없애기. 단, 테스트를 통과시킬 만큼의 테스트만 작성한다.
     */
    @Test
    fun meetsAllCriteria_Then_Strong() {
        val meter: PasswordStrengthMeter = PasswordStrengthMeter()
        val result: PasswordStrength = meter.meter("ab12!@AB")

        assertEquals(PasswordStrength.STRONG, result)

        val result2 = meter.meter("abc1!Add")
        assertEquals(PasswordStrength.STRONG, result2)
    }
}
