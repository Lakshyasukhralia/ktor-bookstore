package com.learning

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@Location("/book/list")
data class BookListLocation(val sortBy: String, val asc: Boolean)

fun Route.books() {

    val dataManager = DataManager()

    authenticate("bookStoreAuth") {
        get<BookListLocation>() {
            call.respond(dataManager.sortedBooks(it.sortBy, it.asc))
        }
    }

    route("/book") {

        get("/") {
            call.respond(dataManager.allBooks())
        }

        post("/{id}") {
            val id = call.parameters["id"].toString()
            val book = call.receive(Book::class)
            val updatedBook = dataManager.updateBook(id, book)
            if (updatedBook != null) {
                call.respond(updatedBook)
            }
        }

        put("/") {
            val book = call.receive(Book::class)
            val addedBook = dataManager.addBook(book)
            call.respond(addedBook)
        }

        delete("/{id}") {
            val id = call.parameters["id"].toString()
            val deletedBookId = dataManager.deleteBook(id)
            call.respondText { "Deleted book with id : $deletedBookId" }
        }
    }
}