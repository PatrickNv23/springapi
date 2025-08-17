# ‍💻 SpringAPI

SpringAPI is a learning project built with **Spring Boot** that simulates a microservices-style API but in a **monolithic setup for simplicity**.  
It covers **authentication, authorization, customer management, email verification, password reset, file upload, payments and CRUD operations**, while applying best practices in **Spring Security, JPA, and Clean Architecture principles**.

---

## ✨ Features
- ✅ Bean Validations
- ✅ Pagination and sorting
- ✅ Result pattern
- ✅ Exception handling
- ✅ Customer registration and login with JWT authentication
- ✅ Role-based authorization (Admin, Customer)
- ✅ Email verification for new accounts
- ✅ Password reset functionality
- ✅ CRUD operations
- ✅ PostgreSQL database integration
- ✅ File upload (basic and storage service)
- ✅ Payments

---

## 📡 API Endpoints

### 🔑 Authentication
- **POST** `/api/auth/register` → Register a new customer
- **POST** `/api/auth/login` → Login and get JWT tokens (access + refresh)
- **POST** `/api/auth/refresh-token` → Refresh expired access token

### 📧 Email Verification
- **POST** `/api/auth/verify-email` → Verify email with token sent by email

### 🔒 Password Reset
- **POST** `/api/auth/request-password-reset` → Request password reset (sends email)
- **POST** `/api/auth/reset-password` → Reset password with token

### 👤 Customers
- **GET** `/api/customers` → Get all customers (Admin only)
- **GET** `/api/customers/{id}` → Get customer by ID
- **PUT** `/api/customers/{id}` → Update customer
- **DELETE** `/api/customers/{id}` → Delete customer

### 🎸 Guitar
- **GET** `/api/guitar` → Get all products
- **GET** `/api/guitar/{id}` → Get product by ID
- **POST** `/api/guitar` → Create product
- **PUT** `/api/guitar/{id}` → Update product
- **DELETE** `/api/guitar/{id}` → Delete product

---

## 👨‍💻 Author
**Patrick Alberto Navarro Vásquez**

- 🌐 Portfolio: [patricknv23](https://patricknv23.netlify.app/)
- 🎥 YouTube: [@panvdev](https://www.youtube.com/@panvdev4234)
- 💼 LinkedIn: [patrick-navarro-vasquez](https://www.linkedin.com/in/patrick-navarro-79104b245/)  
