# drest

drest is a rest server interface for providing genric, configurable and extendable server architecture catering to below main modules:
1. User Management and Auth
2. Rest Service interfaces
3. Service interfaces
4. Data interfaces

# steps
1. git clone https://github.com/gravity-dev/drest.git
2. install apache-maven > 3.2
3. Import root of the cloned project as maven project
4. Build the project
5. Install Mysqlserver > 5.6
6. Use com.drest.storage.mysql\scripts and execute ddl.sql and insert.sql
7. Start the Application.java in debug mode
8. Install postman and import collection from docs/moi.postman_collection.json
