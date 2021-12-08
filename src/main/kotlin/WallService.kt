class PostNotFoundException(message: String) : RuntimeException(message)
class WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun createComment(comment: Comment) {
        for (anyPost in posts) {
            if (anyPost.id == comment.postId) {
                comments += comment
            } else throw PostNotFoundException("there is no such post")

        }
    }

    fun getPostComments(post: Post): String {
        var commentsString = ""
        for (comment in comments) {
            if (comment.replyToComment == post.id) {
                commentsString += comment.message
            }
        }
        return commentsString
    }

    fun add(post: Post): Post {
        posts += if (posts.isEmpty()) {
            post.copy(id = 1)
        } else post.copy(id = posts.last().id + 1)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, somePost) in posts.withIndex()) {
            if (somePost.id == post.id) {
                posts[index] = post.copy(ownerId = somePost.ownerId, date = somePost.date)
                return true
            }
        }
        return false
    }

    fun getLastPostId(): Int = if (posts.isEmpty()) 0 else posts.last().id

    fun findPostById(id: Int): Post? {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == id) {
                return posts[index]
            }
        }
        return null
    }
}