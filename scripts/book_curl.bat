curl localhost:8080/book/
curl -X DELETE localhost:8080/book/10
curl -X PUT -d '{"id":"10","title":"Avengers","author":"Stan Lee","price":850.0}' -H "Content-Type: application/json" localhost:8080/book/
curl -X POST -d '{"id":"10","title":"Avengers","author":"Stan Lee","price":850.0}' -H "Content-Type: application/json" localhost:8080/book/
curl "localhost:8080/book/list?sortBy=id&asc=false"
curl -u "shopper1:password" "localhost:8080/book/list?sortBy=id&asc=false"
mongo "mongodb+srv://cluster0.nyawk.mongodb.net/book_store" --username lakshya
https://book-store-ktor.herokuapp.com/
curl https://book-store-ktor.herokuapp.com/book/