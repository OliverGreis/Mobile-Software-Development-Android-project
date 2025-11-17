# Transaction Backend (Kotlin + Spring Boot)

This transaction-backend folder provides the complete REST API logic required for managing **transactions** inside the Android application.

The module runs independently and exposes endpoints fully compatible with typical mobile app requirements such as:
- creating transactions
- fetching transactions by group or user
- adding participants to a transaction
- persistent storage
- deletion / reset operations


## Features Implemented

### ✔ Full REST Transaction API
- Create a transaction  
- List all transactions  
- Get transaction by ID  
- Filter transactions by group ID  
- Filter transactions by username  
- Add a user to an existing transaction  
- Delete a single transaction  
- Delete all transactions

### Persistence
- Data is saved into `transactions.json`  
- Backend reloads the data on startup  
- Restarting the server does **not** delete any data  
- Manual reset is available via DELETE endpoints


## Running the Backend
Implement these on terminal:
- cd transaction-backend
- ./gradlew bootRun
Backend runs on:
- http://localhost:8080
- For Android Emulator: http://10.0.2.2:8080


## Endpoints Overview

### Create Transaction
- POST /api/transactions
- ```json
{
  "amount": 100,
  "groupId": 1,
  "createdBy": "onat",
  "participants": ["onat", "ali"],
  "description": "Market"
}

### Get All Transactions
- GET /api/transactions

### Get by ID
- GET /api/transactions/{id}

### Get by Group
- GET /api/transactions/group/{groupId}

### Get by User 
- GET /api/transactions/user/{username}

### Add User to Transaction
- POST /api/transactions/{id}/users
- {
  "username": "mehmet"
}

### Delete Transactions
- DELETE /api/transactions/{id}

### Delete All Transactions
- DELETE /api/transactions








