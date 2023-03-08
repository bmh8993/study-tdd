package chap02

enum class PasswordStrength {
    INVALID,
    STRONG,
    NORMAL,
    WEAK
}

class PasswordStrengthMeter {
    fun meter(s: String?): PasswordStrength {
        if (s.isNullOrBlank()) return PasswordStrength.INVALID

        return when (getMetCriteriaCounts(s)) {
            0, 1 -> PasswordStrength.WEAK
            2 -> PasswordStrength.NORMAL
            else -> PasswordStrength.STRONG
        }
    }

    private fun getMetCriteriaCounts(s: String): Int {
        val meetConditions =
            listOf(s.length >= 8, meetsContainingNumberCriteria(s), meetsContainingUppercaseCriteria(s))
        return meetConditions.count { it }
    }

    private fun meetsContainingNumberCriteria(s: String) =
        s.firstOrNull { it.isDigit() } != null

    private fun meetsContainingUppercaseCriteria(s: String) =
        s.firstOrNull { it.isUpperCase() } != null
}