# EventManagementSystem

Created RESTful service that manages event data based on a user's geographical location and a specified date.

Technology Stack:

Java SpringBoot :

* RESTful API Development: Java Spring Boot provides a robust framework for building RESTful APIs quickly and efficiently. It offers features like Spring Web MVC, which simplifies the development of API endpoints.
* Dependency Injection: Spring Boot's dependency injection mechanism allows for easy integration with external APIs and services.
* Spring Data: Spring Data provides convenient abstractions for data access, making it easier to work with databases.

MySQL :

*Relational Database: Since the dataset provided is structured and relates to events with various attributes, a relational database like MySQL is suitable for storing and querying this data efficiently.

* ACID Compliance: MySQL ensures data integrity and consistency by adhering to ACID (Atomicity, Consistency, Isolation, Durability) properties.
* 
* Scalability: MySQL supports horizontal and vertical scalability, allowing the system to handle increased loads and data volumes as the application grows.

Challenges :

* When I started this project, the initial challenge I faced was understanding the logic required. It took me half of the day to grasp it. Then, I began by implementing the functionality to calculate the distance and fetch weather data.
* The next challenge arose when I couldn't extract JSON data properly within the Spring Boot framework. I encountered issues with detecting dependencies, particularly with ObjectMapper. Despite numerous attempts and injecting multiple dependencies with different versions, I couldn't resolve it initially. However, eventually, I managed to overcome this hurdle by carefully installing the required dependencies and successfully utilized ObjectMapper in the project.
* The third challenge was that I inadvertently converted the date into a string, which made it difficult to show data for the next 15 days. To fix this, I had to come up with several ideas and try them out until I found a solution that worked.
