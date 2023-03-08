package com.tdd.zayden.passwordstrengthmeter

class PasswordStrengthMeter {
    fun meter(s: String?): PasswordStrength {
        if (s.isNullOrBlank()) return PasswordStrength.INVALID

        val metCount = getMetCriteriaCount(s)

        if (metCount <= 1) return PasswordStrength.WEAK
        if (metCount == 2) return PasswordStrength.NORMAL

        return PasswordStrength.STRONG
    }

    private fun getMetCriteriaCount(s: String): Int {
        var metCount = 0

        if (s.length >= 8) metCount++
        if (meetsContainingNumberCriteria(s)) metCount++
        if (meetsContainingUpperCaseCriteria(s)) metCount++

        return metCount
    }

    private fun meetsContainingUpperCaseCriteria(s: String): Boolean {
//        var containsUpperCase = false
//        for (ch: Char in s.toCharArray()) {
//            if (Character.isUpperCase(ch)) {
//                containsUpperCase = true
//                break
//            }
//        }
//
//        return containsUpperCase
        return s.any { it.isUpperCase() }
    }

    private fun meetsContainingNumberCriteria(s: String): Boolean {
//        var containsNum = false
//
//        for (ch: Char in s.toCharArray()) {
//            if (ch in '0'..'9') {
//                containsNum = true
//                break
//            }
//        }
//
//        return containsNum
        return s.any { it.isDigit() }
    }
}
