package com.learning

data class Book(var id : String, var title : String, var author : String , var price : Float)
data class ShoppingCart(var id : String, var userId : String, var items : ArrayList<Book>)
data class ShoppingItem(var bookId : String, var qty : Int)
data class User(var id : String, var name : String, var userName : String , var password : String)