import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class WallServiceTest {

    @org.junit.Test
    fun add() {
        val service = WallService()
        val expected = service.getLastPostId() + 1
        service.add(post = Post())
        assertEquals(expected, service.getLastPostId())
    }

    @org.junit.Test
    fun updateExisting() {
        val service = WallService()
        service.add(Post(text = "Текст 1"))
        service.add(Post(text = "Текст 2"))
        service.add(Post(text = "Текст 3"))

        val updatePost = Post(3, text = "Текст 3 обновлен")
        val result = service.update(updatePost)
        assertTrue(result)

    }

    @org.junit.Test
    fun updateNotExisting() {
        val service = WallService()
        service.add(Post(text = "Текст 1"))
        service.add(Post(text = "Текст 2"))
        service.add(Post(text = "Текст 3"))

        val updatePost = Post(5, text = "Текст 3 обновлен")
        val result = service.update(updatePost)
        assertTrue(!result)
    }

    @org.junit.Test
    fun createComment() {
        val service = WallService()
        val post = Post(text = "Comment")
        service.add(post)

        val comment = Comment(
            replyToComment = service.getLastPostId(), message = "comment",
            ownerId = 1, postId = 1,
            attachments = arrayOf(CommentAttachment("att", 1, 1))
        )

        service.createComment(comment)
        val result = service.findPostById(service.getLastPostId())?.let { service.getPostComments(it) }


        assertEquals("comment", result)
    }

    @org.junit.Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService()
        val post = Post(text = "Comment")
        service.add(post)

        val comment = Comment(
            replyToComment = service.getLastPostId(), message = "comment",
            ownerId = 1, postId = 2,
            attachments = arrayOf(CommentAttachment("att", 1, 1))
        )
        service.createComment(comment)
    }
}