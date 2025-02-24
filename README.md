# multipledatabases-poc
**Multiple Database Connections in a Single Spring Boot Application**
In this Proof of Concept (POC), we demonstrate how to configure and connect to multiple databases (e.g., MySQL and PostgreSQL) from a single Spring Boot application. This approach allows us to manage data across different databases with ease, using Spring Data JPA and Hibernate.

**Key Highlights:**

**Multiple DataSources Configuration:** We configure separate DataSource beans for each database, ensuring that each DataSource has its own connection properties (URL, username, password, etc.).

**Custom EntityManagerFactory**: To handle interactions with different databases, custom EntityManagerFactory beans are created. These factories are configured to scan different packages for entities corresponding to each database.

**JpaTransactionManager**: Each database gets its own JpaTransactionManager, allowing for isolated transactions across databases.

**Persistence Units**: We define different persistence units for each database, ensuring that Spring Boot correctly maps entities to the right database schema and provides isolation of transaction contexts.

**Automatic Table Creation**: Using the **hibernate.hbm2ddl.auto** property, we can configure automatic creation, updating, or validation of the database schema based on the entity classes, streamlining development.

**Flexibility in Database Operations**: By leveraging multiple configurations, the application can interact with different databases for different modules, such as using MySQL for user data and PostgreSQL for transactional data.

**Benefits**:

**Seamless Multi-database Operations**: Integrating multiple databases without the need for separate services.
**Ease of Configuration**: Spring Boot's abstraction simplifies the configuration of multiple data sources and their interaction.
**Isolated Transactions**: Transactional operations on each database are managed independently, ensuring consistency.
**Use Cases**:

Applications requiring access to different types of data stores (e.g., a mix of relational and NoSQL databases).
Legacy systems that need to interact with different databases while migrating to a unified system.
