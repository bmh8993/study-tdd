package com.tdd.zayden.passwordstrengthmeter

class PasswordStrengthMeter {
    fun meter(s: String): PasswordStrength {
        if (s.length < 8) {
            return PasswordStrength.NORMAL
        }
        return PasswordStrength.STRONG
    }
}
