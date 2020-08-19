package com.learning

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.books() {

    val dataManager = DataManager()

    route("/book") {

        get("/"){
            call.respond(dataManager.allBooks())
        }

        post("/{id}"){
            val id = call.parameters["id"].toString()
            val book = call.receive(Book::class)
            val updatedBook = dataManager.updateBook(id,book)
            if (updatedBook != null) {
                call.respond(updatedBook)
            }
        }

        put("/"){
            val book = call.receive(Book::class)
            val addedBook = dataManager.addBook(book)
            call.respond(addedBook)
        }

        delete("/{id}"){
            val id = call.parameters["id"].toString()
            val deletedBookId = dataManager.deleteBook(id)
            call.respondText{"Deleted book with id : $deletedBookId"}
        }
    }
}