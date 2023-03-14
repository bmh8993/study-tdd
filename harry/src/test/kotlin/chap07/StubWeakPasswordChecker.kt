package chap07

class StubWeakPasswordChecker : WeakPasswordChecker {
    private var weak: Boolean = false

    override fun setWeak(weak: Boolean) {
        this.weak = weak
    }

    override fun checkPasswordWeak(pw: String): Boolean {
        return weak
    }
}