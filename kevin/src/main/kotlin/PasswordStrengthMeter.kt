class PasswordStrengthMeter {
    fun meter(s: String?):PasswordStrength? {
        if (s.isNullOrEmpty())
            return PasswordStrength.INVALID
        val metCount = getMetCriteriaCounts(s)
        if (metCount <= 1) return PasswordStrength.WEAK
        if (metCount == 2) return PasswordStrength.NORMAL
        return PasswordStrength.STRONG
    }

    private fun getMetCriteriaCounts(s: String): Int {
        var metCount = 0
        if (s.length > 8) metCount++
        if (meetsContainingNumberCriteria(s)) metCount++
        if (meetsContainingUppercaseCriteria(s)) metCount++
        return metCount
    }

    private fun meetsContainingNumberCriteria(s:String): Boolean {
        s.forEach {
            if (it in '0'..'9') {
                return true
            }
        }
        return false
    }

    private fun meetsContainingUppercaseCriteria(s: String): Boolean {
        s.forEach {
            if(it.isUpperCase())
                return true
        }
        return false
    }
}