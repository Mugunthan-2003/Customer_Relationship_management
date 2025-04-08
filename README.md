Customer Relationship Management (CRM) Application

About

This project is a Customer Relationship Management (CRM) application built using Spring Boot. It provides a backend solution for managing customer data, interactions, products, and sales information. 
The application includes features for creating, reading, updating, and deleting (CRUD) operations on various entities, with role-based access control implemented using Spring Security.

Features
-   Customer Management: Manage customer details such as name, email, phone number, and address.
-   Employee Management**: Manage employee information and roles within the organization.
-   Interaction Tracking: Keep track of interactions with customers, including the type, date, and notes.
-   Product Catalog: Maintain a catalog of products with details like name, category, and price.
-   Sales Management: Track sales transactions, including customer, product, date, amount, and order status.
-   Role-Based Access Control: Secure the application with different roles (ADMIN, SALES, PRODUCTION, PUBLIC) and permissions using Spring Security.

Technologies Used
-   Spring Boot
-   Spring Data JPA
-   Spring Security
-   MySQL 
-   Maven

Prerequisites
Before you begin, ensure you have met the following requirements:
-   Java Development Kit (JDK) 11 or higher
-   Maven
-   MySQL 
-   Git

Steps to Clone and Run the Application

Follow these steps to clone and run the application on your system:
1. Clone the Repository
Open your terminal and navigate to the directory where you want to clone the repository. Then, run the following command:

git clone https://github.com/Mugunthan-2003/Customer_Relationship_management.git

2. Navigate to the Project Directory
Change your current directory to the cloned project directory:
cd Customer_Relationship_management

3. Configure the Database
Update the `application.properties` file with your database configurations. You can find this file in the `src/main/resources/` directory.

spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name

spring.datasource.username=your_database_username

spring.datasource.password=your_database_password

spring.jpa.hibernate.ddl-auto=update


Replace `your_database_name`, `your_database_username`, and `your_database_password` with your actual database credentials.

4. Run the Application
You can run the application using Maven. Execute the following command in the project directory:

mvn spring-boot:run

6. Access the Application
Once the application is running, you can access it through your web browser or API client. The default port is `8080`, so the application URL would be:

http://localhost:8080
