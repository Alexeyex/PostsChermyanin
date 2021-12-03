
import attachment.*
import java.time.LocalDateTime
import java.time.ZoneOffset

object WallServiceMain {
    val service = WallService()
}
fun main() {

    val attachment: Attachment = AttachmentLink(

        attachmentContent = Link(
            url = "www.netology.ru", title = "Курсы языка Kotlin", "", "",
            product = LinkProduct(
                LinkProduct.Price(60_000, LinkProduct.Currency(1, "RUB (рубль)"), text = "цена"),
            )
        )
    )
    WallServiceMain.service.add(
        Post(
            date = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
            text = "Добро пожаловать на наш курс",
            attachments = arrayOf(attachment)
        )
    )

    WallServiceMain.service.findPostById(WallServiceMain.service.getLastPostId())?.let { postPrint(it) }

}


fun postPrint(post: Post) {
    println(post.text)
    for ((index, att) in post.attachments?.withIndex()!!) {
        when (att) {
            is AttachmentLink -> {
                println(att.attachmentContent.title)
                println("Приходите к нам ${att.attachmentContent.url}")
                println(
                    "Цена составляет ${att.attachmentContent.product?.price?.amount} " +
                            "${att.attachmentContent.product?.price?.currency?.name} !"
                )
            }
            is AttachmentVideo -> println("Смотрите")
            is AttachmentDoc -> println("Читайте")
            is AttachmentPhoto -> println("Смотрите")
            is AttachmentAudio -> println("Слушайте")
        }
    }
    println("copyright ${post.copyright}")
}