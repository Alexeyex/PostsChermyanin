object WallService {
    var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += if (posts.isEmpty()) {
            post.copy(id = 1)
        } else post.copy(id = posts.last().id+1)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for((index, somePost) in posts.withIndex()) {
            if (somePost.id == post.id) {
                posts[index] = post.copy(ownerId = somePost.ownerId, date = somePost.date)
                return true
            }
        }
        return false
    }
}