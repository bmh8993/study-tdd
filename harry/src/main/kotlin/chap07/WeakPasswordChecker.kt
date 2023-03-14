package chap07

interface WeakPasswordChecker {
    fun setWeak(weak: Boolean)
    fun checkPasswordWeak(pw: String): Boolean
}