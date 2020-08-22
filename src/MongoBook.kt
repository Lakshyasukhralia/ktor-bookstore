package com.learning

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

class MongoBook(id: ObjectId?,title : String,author : String ,price : Float) {
    @BsonId
    var id: ObjectId?
    @BsonProperty(value = "title")
    var title: String
    var author: String
    var price: Float

    constructor() : this(null, "not_set","not_set",0.0f){}
    init{
        this.id = id
        this.title = title
        this.author = author
        this.price = price
    }
}