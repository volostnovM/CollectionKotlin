object NoteService {
    var notesList: MutableList<Note> = mutableListOf()
    private var idNote = 0

    fun clear() {
        notesList = mutableListOf()
        idNote = 0
    }

    fun add(title: String, text: String, privacy: Int) {
        idNote++
        val newNote = Note(idNote, title, text, privacy)
        notesList.add(newNote)
    }

    fun createComment(noteId: Int, message: String) {
        var isFind = false

        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                isFind = true
                if (noteFind.comments.isEmpty()) {
                    val newComment = Comment(0, noteId, message, false)
                    noteFind.comments.add(newComment)
                } else {
                    val newComment = Comment(noteFind.comments.last().id + 1, noteId, message, false)
                    noteFind.comments.add(newComment)
                }
            }
        }

        if (!isFind) {
            throw NoteNotFoundException("Упс, такой заметки в списке нет...")
        }
    }

    fun delete(noteId: Int) {
        val iterator = notesList.iterator()
        while (iterator.hasNext()) {
            if (noteId == iterator.next().id) {
                iterator.remove()
            }
        }
    }

    fun deleteComment(noteId: Int, commentId: Int) {
        var isFind = false
        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                isFind = true
                for (commentFind in noteFind.comments) {
                    if (commentId == commentFind.id && !commentFind.isDelete) {
                        commentFind.isDelete = true
                    }
                }
            }
        }

        if (!isFind) {
            throw NoteNotFoundException("Упс, такой заметки в списке нет...")
        }
    }

    fun edit(noteId: Int, title: String, text: String, privacy: Int) {
        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                noteFind.text = text
                noteFind.title = title
                noteFind.privacy = privacy
            }
        }
    }

    fun editComment(noteId: Int, commentId: Int, message: String) {
        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                for (commentFind in noteFind.comments) {
                    if (commentId == commentFind.id && !commentFind.isDelete) {
                        commentFind.message = message
                    }
                }
            }
        }
    }

    fun getAllNotes(): MutableList<Note> {
        return notesList
    }

    fun <T> getDataById (id: Int, collectionData: MutableList<T>?): T? {
        if (collectionData == null) {
            return null
        }
        for (data in collectionData) {
            if (data is Note && id == data.id) {
                return data
            } else if(data is Comment && id == data.id && !data.isDelete) {
                return data
            }
        }
        return null
    }

    fun getById(noteId: Int): Note? {
        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                return noteFind
            }
        }
        return null
    }

    fun getComments(noteId: Int): MutableList<Comment>? {
        val comments = mutableListOf<Comment>()
        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                for (comment in noteFind.comments) {
                    if (!comment.isDelete) {
                        comments.add(comment)
                    }
                }
                return comments
            }
        }
        return null
    }

    fun restoreComment(noteId: Int, commentId: Int) {
        for (noteFind in notesList) {
            if (noteId == noteFind.id) {
                for (comment in noteFind.comments) {
                    if (comment.id == commentId && comment.isDelete) {
                        comment.isDelete = false
                    }
                }
            }
        }
    }
}