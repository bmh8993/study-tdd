package chap07

class UserRegister constructor(
    private val passwordChecker: WeakPasswordChecker,
    private val userRepository: UserRepository
) {
    fun register(id: String, pw: String, email: String) {
        if (passwordChecker.checkPasswordWeak(pw))
            throw WeakPasswordException()

        val user = userRepository.findById(id)
        if (user != null) {
            throw DupIdException()
        }

        userRepository.save(User(id, pw, email))

    }
}