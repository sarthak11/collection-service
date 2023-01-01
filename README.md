# collection-service

The purpose of this microservice is to manage fees/payment related data on the platform.

## Description

* The main API's of this microservice is transaction/collectFee/{studentId} API which saves fees payment data (note: here card number is taken in encoded format in the request)
* The other main API of this microservice is feeReceipt/fetch/{studentId} which displays all fee receipts for a student by id

## Getting Started

### Dependencies

* Springboot - 3.0.2
* JAVA - 19
* H2 - 1.4.193
* Lombok - 1.18.24

### Executing program

In the root directory of the project just do

```
mvn spring-boot:run
```
This will make the application up on port 8081

### Postman Collection

```
https://api.postman.com/collections/21016888-5f88590a-b0af-4442-9de5-bacae3df6281?access_key=PMAT-01GNP3N0ZC6P52XX1WPDN45W20
```

### Schema

* CardType

|  Column                                |  Type   |  Description
| --------------------------------------|----------|----------------------------------------------
| id                                    | Long     | Primary key
| name                                  | String   | VISA/MASTERCARD etc.
| created_date                          | Datetime | Created date

* CardDetails

| Column       | Type     |  Description
|--------------|----------|----------------------------------------------
| id           | Long     | Primary key
| card_type_id | Long     | Foreign key referencing to CardType
| number       | String   | Stores encoded card number
| expiry_month | String   | Expiry month of the card
| expiry_year  | String   | Expiry year of the card
| created_date | Datetime | Created date

* PaymentType

|  Column                                |  Type   |  Description
| --------------------------------------|----------|----------------------------------------------
| id                                    | Long     | Primary key
| name                                  | String   | CASH/CARD/NETBANKING etc.
| created_date                          | Datetime | Created date

* TutionFee

| Column       | Type     |  Description
|--------------|----------|----------------------------------------------
| id           | Long     | Primary key
| grade        | int      | Class grade
| fee          | double   | Fee
| created_date | Datetime | Created date

* Transaction

| Column           | Type     |  Description
|------------------|----------|----------------------------------------------
| id               | Long     | Primary key
| student_id       | Long     | Student id
| total_amount     | Double   | Total transaction value in AED
| reference_number | String   | Unique reference number for every transaction
| payment_type_id  | Long     | Foreign key referencing to PaymentType
| card_details_id  | Long     | Foreign key referencing to CardDetails
| created_date     | Datetime | Created date

* TutionTransactionMapping

| Column          | Type     |  Description
|-----------------|----------|----------------------------------------------
| id              | Long     | Primary key
| tution_fee_id   | Long     | Foreign key referencing to TutionFee
| transaction_id  | Long     | Foreign key referencing to Transaction
| quantity        | int      | Quantity
| created_date    | Datetime | Created date

## Authors
* Sarthak Soni - sarthaksoni987@gmail.com