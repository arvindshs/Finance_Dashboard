# Finance Dashboard Backend

## Overview

This project is a backend system for a **Finance Dashboard Application** designed to manage financial records and provide analytics through secure APIs.
It demonstrates backend development concepts such as **API design, authentication, role-based access control, data persistence, and financial data aggregation**.

The backend allows multiple users with different roles to interact with financial records while ensuring that access is controlled and data is processed efficiently.

---

## Features

### User and Role Management

The system supports multiple user roles with different levels of access.

**Roles Implemented**

* **Admin**

  * Create and manage users
  * Create, update, and delete financial records
  * Access dashboard analytics

* **Analyst**

  * View financial records
  * Access dashboard insights

* **Viewer**

  * View dashboard summary information only

Users also have a **status field** to indicate whether they are active or inactive.

---

### Financial Records Management

The backend supports CRUD operations for financial transactions.

Each transaction record includes:

* Amount
* Record Type (Income / Expense)
* Category
* Date
* Notes / Description

Supported operations:

* Create transaction
* View transactions
* Update transaction
* Delete transaction

---

### Dashboard Summary APIs

The backend provides aggregated financial data for dashboard visualization.

Examples of available analytics:

* Total Income
* Total Expenses
* Net Balance
* Category-based totals
* Recent financial activity

These APIs demonstrate backend aggregation and business logic implementation.

---

### Authentication and Access Control

The system uses **JWT (JSON Web Token)** authentication.

Security features include:

* User login with credentials
* Token generation
* Protected API endpoints
* Role-based authorization for API access

This ensures that only authorized users can access specific resources.

---

## Tech Stack

**Backend Framework**

* Java
* Spring Boot

**Security**

* Spring Security
* JWT Authentication

**Database**

* Spring Data JPA
* Relational Database MySQL 

**Build Tool**

* Maven

---


## API Endpoints

### Authentication

```
POST /auth/login
```

### Users

```
POST /users
GET /users
PUT /users/{id}
DELETE /users/{id}
```

### Transactions

```
POST /transactions/{userid}
GET /transactions/all
PUT /transactions/update/{id}
DELETE /transactions/delete/{id}
```

### Dashboard

```
GET /dashboard/ammountsummary
GET /dashboard/categorywise
GET /dashboard/recent
GET /dashboard/monthlywise
```

---

## Setup Instructions

### 1. Clone the repository

```
git clone https://github.com/yourusername/finance-dashboard-backend.git
```

### 2. Navigate to the project directory

```
cd finance-dashboard-backend
```

### 3. Configure the database

Update database configuration in:

```
src/main/resources/application.properties
```

Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 4. Run the application

Using Maven:

```
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

## Assumptions

Some assumptions made during development:

* Users must authenticate before accessing protected APIs.
* Role-based permissions control what actions each user can perform.
* Financial records are stored centrally and used for dashboard analytics.
* The focus of the project is backend design rather than frontend integration.

---

## Possible Future Improvements

Potential enhancements include:

* Pagination for transaction APIs
* Filtering transactions by category, type, or date
* Search functionality
* Unit and integration tests
* API documentation using Swagger / OpenAPI
* Soft delete for records
* Rate limiting and improved security policies

---

## Purpose of the Project

This project was built as part of a **backend development assessment** to demonstrate:

* Backend architecture and API design
* Data modeling using JPA
* Authentication and authorization
* Business logic implementation
* Clean and maintainable backend structure

The focus is on **clear backend engineering practices rather than production-level complexity**.
