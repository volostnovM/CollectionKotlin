import org.junit.Assert.*
import org.junit.Before

class NoteServiceTest {

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @org.junit.Test
    fun add() {
        val checkNote = Note(1, "Первая заметка", "Не забудь купить хлеб", 0)
        NoteService.add("Первая заметка", "Не забудь купить хлеб", 0)
        val result = NoteService.getById(1)

        assertEquals(checkNote, result)
    }

    @org.junit.Test
    fun createComment() {
        val checkComment = Comment(0, 1, "test msg", false)
        val commentList: MutableList<Comment> = mutableListOf(checkComment)

        NoteService.add("Первая заметка", "Не забудь купить хлеб", 0)
        NoteService.createComment(1, "test msg")
        val result = NoteService.getComments(1)

        assertEquals(commentList, result)
    }

    @org.junit.Test(expected = NoteNotFoundException::class)
    fun createCommentNotFindNote() {
        NoteService.add("Первая заметка", "Не забудь купить хлеб", 0)
        NoteService.createComment(10, "test msg")
    }

    @org.junit.Test
    fun delete() {
        NoteService.add("Первая заметка", "Не забудь купить хлеб", 0)
        NoteService.delete(1)
        val result = NoteService.notesList.size

        assertEquals(0, result)
    }

    @org.junit.Test
    fun notDeleteNote() {
        NoteService.add("Первая заметка", "Не забудь купить хлеб", 0)
        NoteService.delete(0)
        val result = NoteService.notesList.size

        assertEquals(1, result)
    }
}