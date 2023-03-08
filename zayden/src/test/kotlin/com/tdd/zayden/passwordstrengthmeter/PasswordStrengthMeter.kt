package com.tdd.zayden.passwordstrengthmeter

class PasswordStrengthMeter {
    fun meter(s: String?): PasswordStrength {
        if (s.isNullOrBlank()) return PasswordStrength.INVALID

        val lengthEnough = s.length >= 8
        val containsNum = meetsContainingNumberCriteria(s)
        val containsUpp = meetsContainingUpperCaseCriteria(s)

        if (lengthEnough && !containsNum && !containsUpp) return PasswordStrength.WEAK
        if (!lengthEnough && containsNum && !containsUpp) return PasswordStrength.WEAK

        if (!lengthEnough) return PasswordStrength.NORMAL
        if (!containsNum) return PasswordStrength.NORMAL
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
