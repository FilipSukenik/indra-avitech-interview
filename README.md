# Indra Avitech interview

### Requirements
Create program in Java language that will process commands from FIFO queue using Producer –
Consumer pattern.

#### Supported commands are the following:
- Add  - adds a user into a database
- PrintAll – prints all users into standard output
- DeleteAll – deletes all users from database

User is defined as database table SUSERS with columns (USER_ID, USER_GUID, USER_NAME)

Demonstrate program on the following sequence (using main method or test):
1. Add (1, &quot;a1&quot;, &quot;Robert&quot;)
2. Add (2, &quot;a2&quot;, &quot;Martin&quot;)
3. PrintAll
4. DeleteAll
5. PrintAll

Show your ability to unit test code on at least one class.
Goal of this exercise is to show Java language and JDK know-how, OOP principles, clean code
understanding, concurrent programming knowledge, unit testing experience.
Please do not use Spring framework in this exercise. Embedded database is sufficient.

### Assumptions
1. Both consumer and producer are within the same execution environment, hence, they can exchange java objects directly
without need of serialization and deserialization mechanism.
2. All commands and model (User) are immutable objects.
3. No other implementations of UserDao and PrintService is needed, so extracting methods to interfaces is not needed.
4. Code is provided as java library without any main function.
5. Methods are small and self-explanatory, hence, no need for javadoc.

### Implementation

#### Decomposition
There are 3 concepts that are separated to 3 different components:
1. Database - using hikari to provide database connection.
2. Communication - using shared message bus which is . 
3. Printing
Each component serves single purpose and all it's features are encapsulated.

#### Design patterns
1. Command - used to send immutable commands between producer and consumer as whole objects.
2. Data access object - provides interface to communicate with underlying database technology.
3. Dependency injection - no usage of static classes and singletons, rather provide dependencies as constructor parameters, 
that can be replaced (mocked) by different implementation.

#### Testing
Higher level tests (acceptance and integration) were created before real implementation using ATDD. I used Red, Green, Refactor approach
for testing. When running tests with coverage inside IDE it reports 100% coverage.