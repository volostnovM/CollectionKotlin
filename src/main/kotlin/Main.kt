fun main() {
    NoteService.add("Первая заметка", "Не забудь купить хлеб", 0)
    NoteService.add("Вторая заметка", "Не забудь купить масло", 0)
    println(NoteService.getAllNotes())

    NoteService.createComment(1, "2 буханки")
    NoteService.createComment(1, "ржаной")
    println(NoteService.getAllNotes())

    NoteService.delete(2)
    println(NoteService.getAllNotes())

    NoteService.deleteComment(1, 1)
    println(NoteService.getAllNotes())

    NoteService.edit(1, "Обновленная первая заметка", "купить молоко", 0)
    println(NoteService.getAllNotes())

    NoteService.restoreComment(1, 1)
    println(NoteService.getAllNotes())

    NoteService.editComment(1, 0, "взять 2 бутылки")
    NoteService.editComment(1, 1, "3.2%")
    println(NoteService.getAllNotes())

    NoteService.add("Третья заметка", "Купи гречку", 0)
    println(NoteService.getById(3))

    println(NoteService.getComments(1))
}