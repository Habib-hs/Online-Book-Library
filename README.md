# Online-Book-Library

Online Book Library is a Spring Boot Security app with two roles: Customer and Admin. Customer can browse and search the book, while Admins can manage the library

## Features

- User registration and login with JWT authentication
- Password encryption using BCrypt
- Role-based authorization with Spring Security
- Customized access denied handling
- Do some Crud Operation of Book

## Technologies

- Spring Boot 3.0
- Spring Security
- JSON Web Tokens (JWT)
- BCrypt
- Gradle
- MySql
  \*Hibernath

## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

- JDK 17+
- Gradle 3+

To build and run the project, follow these steps:

- Clone the repository: `git clone https://github.com/mhabib1234/Online-Book-Library.git`
- Navigate to the project directory: cd Online-Book-Library
- Add database "security-module" to MySql
- Build the project: gradle clean install
- Run the project: gradle bootRun or you can run via your preferred IDE's RUN/PLAY button

-> The application will be available at http://localhost:9090.

## Some Screenshot of the project

User Can register providing necessary Data

![reg1](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/b5c69e50-6f8b-42b2-989f-27d963b9cc59)

If a registered user try to register twice will provide error.

![reg2](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/a404a624-c40c-4273-9b1a-4ab0f133c500)

Can log in providing email and password and will get a jwt token as response.

![log1](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/b6df1764-90d3-4bf7-9f4a-f5dc2f82b069)

If Email or password is not valid.

![log4](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/27cf8345-49cf-4692-996b-504e09af2708)

To access protected url, we need to send the jwt token to the header.

![bearer5](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/30f61aa0-5d12-4a80-9115-ee61ca9ee509)

User do not have the access to create,update and delete a book.

Create:
![customer-create-11](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/8b9602ee-ab85-48d7-b448-f9724ffde94a)
Update:
![cus-update-12](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/23b631c6-1117-4bfa-96bd-47400e377d63)
Delete:
![cus-delete-13](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/d30f6ce3-1f21-485a-97dc-dfd73d87d4ba)

User do have the access to see all the books, particular book and different kinds of search. Note that, Admin can do the same task too.
![cus-singlebook-14](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/7b545c4d-0b3b-45d5-9698-441b6cc5c882)
![cus-allbook-15](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/0bb8f6c6-53f1-4e72-8501-f0d0d41a0aab)
![cus-authorsearch-16](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/58390edf-9864-4eec-85c0-725b3e190f9b)
![cus-17](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/78e43982-a073-4988-80ec-cb5fa0fc8d83)

As user do not have the access to create,update and delete a book. here I provide jwt token of a admin now we can do all the task as a Admin.
![adm-21](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/720d9ab9-1592-47e3-a5e3-ab891e404de0)
![adm-update-22](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/891cf095-09e6-43c9-9ea6-4e3b2b72e1c1)
![adm-delete-23](https://github.com/mhabib1234/Online-Book-Library/assets/131146437/46076d89-04ec-413f-a851-cba81b924680)
