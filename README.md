# Product Catalog API
A REST API built with Java and Spring Boot for managing products and retrieving product information from a database.

The API allows users to:
* Add new products
* Retrieve a list of products
* Retrieve detailed information about a specific product

Each product contains:
* A name
* A product type
* One or more associated colours

The project uses Spring Data JPA for persistence and an H2 in-memory database for local development and testing.

## Technology Stack
* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Maven
* VS Code


## Project Structure
```text
src/main/java/com/github/glorialiu1/product_catalog_api

controller      -> REST API endpoints
service         -> business logic
repository      -> database access
entity          -> JPA entities
dto             -> API request/response models
```

## Database Model
The application models the following entities:
#### Product
* id
* name
* productType
* colours
#### ProductType
* id
* name
#### Colour
* id
* name

### Relationships
* A Product belongs to one ProductType
* A Product can have multiple Colours
* A colour can belong to multiple Products
    * This many-to-many relationship is stored in a join table: ```product_colour```

## Prerequisites
Before running the project locally, ensure you have installed:
* Java 17
* Git
* Maven (optional, the project includes the Maven wrapper)
* VSCode (recommended)

Optional VSCode extensions:
* Java Extension Pack
* Spring Boot Extension Pack
* REST Client (for running HTTP requests inside the editor)

## Running the Application
Clone the repository:
```bash
git clone https://github.com/glorialiu1/product-catalog-api.git
cd product-catalog-api
```
Start the application using the Maven wrapper:
```bash
./mvnw spring-boot:run
```
On Windows:
```bash
mvnw spring-boot:run
```
Once started, the API will be available at:
```http://localhost:8080```

## H2 Database Console
The application uses an H2 in-memory database.
You can inspect the database at:
```http://localhost:8080/h2-console```

Use the following settings:
```markdown
JDBC URL: jdbc:h2:mem:productdb
Username: sa
Password: (empty)
```
## API Endpoints
### Create Product
POST ```/api/products```

Example request:
```json
{
    "name": "SÖDERHAMN",
    "productTypeId": 1,
    "colourIds": [1, 2]
}
```

Example response:
```json
{
    "id": 1,
    "name": "SÖDERHAMN"
}
```

### List Products
GET ```/api/products```

Response:
```json
[
    {
        "id": 1,
        "name": "SÖDERHAMN"
    }
]
```

### Get Product Details
GET ```/api/products/{id}```

Example:
```text
GET /api/products/1
```

Response:
```json
{
    "id": 1,
    "name": "SÖDERHAMN",
    "productType": "Sofa",
    "colours": ["Blue", "Ruby"]
}
```
## Testing the API
Requests can be tested using:
* curl
* Postman
* VSCode REST Client

Example using curl:
```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{"name":"SÖDERHAMN","productTypeId":1,"colourIds":[1,2]}'
```
Alternatively, the repository includes a ```requests.http``` file that can be executed directly in VSCode using the REST Client extension.

## Notes
* The H2 database runs in-memory, so all data is reset when the application stops.
* The project uses H2 for simplicity and zero external setup. The application uses standard JPA and SQL, and can be switched to PostgreSQL with minimal configuration changes.
* Sample ```ProductType``` and ```Colour``` data are inserted automatically when the application starts.
