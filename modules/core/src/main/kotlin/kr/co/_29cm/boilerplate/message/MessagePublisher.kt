package kr.co._29cm.boilerplate.message

interface MessagePublisher<T: Message> {
    fun publish(key: String, message: T)
}