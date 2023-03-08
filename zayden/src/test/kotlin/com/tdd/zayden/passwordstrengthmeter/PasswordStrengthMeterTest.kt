package com.tdd.zayden.passwordstrengthmeter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * # 가장 쉽거나 가장 예외적인 상황을 선택한다.
 * - 이번 케이스는 모든 규칙을 충족하는 경우, 모든 조건을 충족하지 않는 경우로 나눠보자.
 * - 모든 규칙을 충족하는 경우를 떠올려보면 '강함'을 테스트하면 모든 조건을 충족하는 것으로 볼 수 있다.
 * - 반면, 모든 조건을 충족하지 않는다는 것은 '약함, 보통, 강함'을 만들어내는 모든 조건을 작성해야한다고 할 수 있다.
 * - *모든 규칙을 충족하는 경우로 진행하자.*
 *
 * # 코드정리
 * - 코드 정리 후 테스트가 깨지지 않는지 확인한다.
 */
class PasswordStrengthMeterTest {

    private val meter = PasswordStrengthMeter()

    private fun assertStrength(password: String?, expectedStrength: PasswordStrength) {
        val result = meter.meter(password)
        assertEquals(expectedStrength, result)
    }

    /**
     * ### 모든 규칙을 충족하는 경우
     *
     * 1. 존재하지 않는 타입에 대한 컴파일 에러 발생
     * 2. 컴파일 에러 없애기. 단, 테스트를 통과시킬 만큼의 테스트만 작성한다.
     */
    @Test
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG)
        assertStrength("abc1!Add", PasswordStrength.STRONG)
    }

    /**
     * ### 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
     *
     * 1. NORMAL에 대한 컴파일 에러 발생
     * 2. NORMAL 추가
     * 3. meter의 return값은 STRONG임.
     * 4. meter에 조건을 추가하여 NORMAL을 리턴하도록 수정
     */
    @Test
    fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL)
        assertStrength("Ab12!c", PasswordStrength.NORMAL)
    }

    /**
     * ### 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
     *
     * 1. 메서드 추가. 테스트 시작. STRONG <> NORMAL
     * 2. 테스트 통과를 위한 코드 추가.
     * 3. 리팩터링
     */
    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL)
    }

    /**
     * ### 값이 없는 경우
     * 1. NPE는 원하는 상황이 아님. null에 대해서도 알맞게 동작해야함
     * 2. assertStrenth에 null, PasswordStrength.INVALID전달 > 컴파일 에러 발생 > INVALID 추가
     */
    @Test
    fun nullInPut_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID)
    }

    @Test
    fun emptyInPut_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID)
    }

    /**
     * ### 대문자를 포함하지 않고 나머지 조건을 충족하는 경우
     */
    @Test
    fun meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL)
    }
}
