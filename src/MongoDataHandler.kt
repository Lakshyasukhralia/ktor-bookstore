package com.learning

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider

class MongoDataHandler {

    val database: MongoDatabase
    val bookCollection: MongoCollection<MongoBook>

    init {

        val pojoCodecRegistry: CodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build())
        val codecRegistry: CodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            pojoCodecRegistry)

        val connectionString = "mongodb+srv://lakshya:haryana97@cluster0.nyawk.mongodb.net/book_store?retryWrites=true&w=majority"

        val clientSettings = MongoClientSettings.builder()
            .codecRegistry(codecRegistry)
            .applyConnectionString(ConnectionString(connectionString))
            .build()


        val mongoClient = MongoClients.create(
            clientSettings
        )

        database = mongoClient.getDatabase("book_store")

        bookCollection = database.getCollection("book_lib", MongoBook::class.java)
        initBookStore()
    }

    fun initBookStore() {
        bookCollection.insertOne(MongoBook(null, "Star Wars", "Lucas", 1960f))
    }

    fun allBooks(): List<MongoBook>{
        val mongoResult = bookCollection.find()
        mongoResult.forEach{
            print("book: $it")
        }
        return mongoResult.toList()
    }

}