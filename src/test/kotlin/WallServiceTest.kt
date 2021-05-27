import org.junit.Assert.*

class WallServiceTest {

    @org.junit.Test
    fun add() {
        val service = WallService
        val expected = service.getLastPostId() + 1
        service.add(post = Post())
        assertEquals(expected, service.getLastPostId())
    }

    @org.junit.Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(text = "Текст 1"))
        service.add(Post(text = "Текст 2"))
        service.add(Post(text = "Текст 3"))

        val updatePost = Post(3, text = "Текст 3 обновлен")
        val result = service.update(updatePost)
        assertTrue(result)

    }

    @org.junit.Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(text = "Текст 1"))
        service.add(Post(text = "Текст 2"))
        service.add(Post(text = "Текст 3"))

        val updatePost = Post(5, text = "Текст 3 обновлен")
        val result = service.update(updatePost)
        assertTrue(!result)
    }

}