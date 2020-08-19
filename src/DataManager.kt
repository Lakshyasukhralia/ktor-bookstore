package com.learning

import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredMemberProperties

class DataManager {

    val log = LoggerFactory.getLogger(DataManager::class.java)
    var books = ArrayList<Book>()

    fun giveId(): String {
        return books.size.toString()
    }

    init {
        books.add(Book(giveId(), "Harry Potter", "J.R. Rowling", 1200f))
        books.add(Book(giveId(), "Star Trek", "R.R. Simmons", 1650f))
    }

    fun addBook(book: Book): Book {
        books.add(book)
        return book
    }

    fun updateBook(id: String, book: Book): Book? {
        val foundBook = books.find {
            it.id == id
        }

        foundBook?.title = book.title
        foundBook?.author = book.author
        foundBook?.price = book.price

        return foundBook
    }

    fun deleteBook(bookId: String?): String? {
        val foundBook = books.find {
            it.id == bookId
        }

        books.remove(foundBook)

        return bookId
    }

    fun allBooks(): ArrayList<Book> {
        return books
    }

    fun sortedBooks(sortBy: String, asc: Boolean): List<Book> {
        val member = Book::class.declaredMemberProperties.find { it.name.equals(sortBy) }
        if(member == null){
            log.info("The field to sort does not exist")
            return allBooks()
        }

        return if(asc){
            allBooks().sortedBy { member.get(it).toString() }
        }else{
            allBooks().sortedByDescending { member.get(it).toString() }
        }
    }

}