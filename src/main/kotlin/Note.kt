data class Note(
    val id: Int,
    var title: String,
    var text: String,
    var privacy: Int,
    var comments: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val id: Int,
    val noteId: Int,
    var message: String,
    var isDelete: Boolean = false
)