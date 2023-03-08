package com.tdd.zayden.passwordstrengthmeter

class PasswordStrengthMeter {
    fun meter(s: String?): PasswordStrength {
        if (s.isNullOrBlank()) return PasswordStrength.INVALID
        if (s.length < 8) return PasswordStrength.NORMAL

        val containsNum = meetsContainingNumberCriteria(s)
        if (!containsNum) return PasswordStrength.NORMAL

        return PasswordStrength.STRONG
    }

    private fun meetsContainingNumberCriteria(s: String): Boolean {
        var containsNum = false

        for (ch: Char in s.toCharArray()) {
            if (ch in '0'..'9') {
                containsNum = true
                break
            }
        }

        return containsNum
    }
}
