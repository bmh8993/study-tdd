package chap07

import chap07.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class UserRegisterTest {
    lateinit var userRegister: UserRegister
    lateinit var stubPasswordChecker: WeakPasswordChecker
    lateinit var fakeRepository: UserRepository

    @BeforeEach
    fun setUp() {
        stubPasswordChecker = StubWeakPasswordChecker()
        fakeRepository = MemoryUserRepository()
        userRegister = UserRegister(stubPasswordChecker, fakeRepository)
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    fun weakPassword() {
        stubPasswordChecker.setWeak(true)

        assertThrows<WeakPasswordException> {
            userRegister.register("id", "pw", "email")
        }
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    fun dupIdExists() {
        val user = User("id", "pw1", "email@email.com")
        fakeRepository.save(user)
        assertThrows<DupIdException> {
            userRegister.register("id", "pw2", "email")
        }
    }

    @DisplayName("같은 ID가 없으면 가입 성공")
    @Test
    fun noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email")

        val savedUser = fakeRepository.findById("id")
        assertEquals("id", savedUser?.id)
        assertEquals("email", savedUser?.email)
    }
}