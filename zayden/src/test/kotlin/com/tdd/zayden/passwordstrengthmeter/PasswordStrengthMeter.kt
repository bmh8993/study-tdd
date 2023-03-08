package com.tdd.zayden.passwordstrengthmeter

class PasswordStrengthMeter {
    fun meter(s: String?): PasswordStrength {
        if (s.isNullOrBlank()) return PasswordStrength.INVALID
        if (s.length < 8) return PasswordStrength.NORMAL

        val containsNum = meetsContainingNumberCriteria(s)
        if (!containsNum) return PasswordStrength.NORMAL

        val containsUpp = meetsContainingUpperCaseCriteria(s)
        if (!containsUpp) return PasswordStrength.NORMAL

        return PasswordStrength.STRONG
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
