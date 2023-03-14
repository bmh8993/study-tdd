package chap07

class MemoryUserRepository : UserRepository {

    val users: MutableMap<String, User> = mutableMapOf()

    override fun save(user: User) {
        users[user.id] = user
    }

    override fun findById(id: String): User? {
        return users[id]
    }
}