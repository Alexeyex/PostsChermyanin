fun main() {
    WallService.add(Post(text = "Всем привет"))
    WallService.add(Post(text = "Всем прив"))
    WallService.update(Post(id = 2, text = "Всем привееееееееееееееееееееет"))
    WallService.posts.forEach(System.out::println)
}