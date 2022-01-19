# Cova Quiz App

This rest api contains requests necessary for a user to signup/login and make other requests as listed below in the wizer library application.

It contains the following requests

- Register a user
- Login a user
- Add a category
- Update a category
- Get all categories
- Add a book
- Update a book
- A a book to a category
- Add a book to favorite
- Get all books

## Build

    mvn clean install -DskipTests=true

## Run the app

    mvn spring-boot:run

## Run the tests

    mvn test

# REST API

The REST API to the quiz app is described below.

## Signup 

Api endpoint to *Register* a new user
> A successful registration will return HTTP 200 Status and automatically login the user

> A Json Web Token (JWT) that can be used for other authenticated requests is generated as well

> An error message is shown if a username or email has already been taken by another user

### Request

`POST /api/v1/auth/signup`

    curl --location --request POST 'http://localhost:7585/api/auth/signup' \
    --data-raw '{
    "username": "aduke",
    "firstname": "Oluwadamilola",
    "lastname": "Alausa",
    "gender": "Female",
    "email": "oreoluwa@gmail.com",
    "password": "123456"
    }'

### Response

    {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvcmVvbHV3YUBnbWFpbC5jb20iLCJpYXQiOjE2MjQ0MTU3ODksImV4cCI6MTYyNDQzMzc4OX0.OYxiO7maYj76LoHa7-JvG0gziHuly_jFC9Q2vdWUM0KG7Y1MCVL2TyMZZNgxcXIT_M6KhVRykshx5uT88C1V3w",
    "type": "Bearer",
    "email": "oreoluwa@gmail.com",
    "roles": [
    "ROLE_USER"
    ]
    }

## Login as a returning user

Api endpoint to *Login* a registered user into the application
> A successful login will return HTTP 200 Status

> A Json Web Token (JWT) is generated and is used for other authenticated requests

> Request for a non existing user will return HTTP 401 status

### Request

`POST /api/v1/auth/login`

    curl --location --request POST 'http://localhost:7585/api/auth/login' \
    --data-raw '{
    "email": "adedotunalausa@gmail.com",
    "password": "123456"
    }'

### Response

    {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGVkb3R1bmFsYXVzYUBnbWFpbC5jb20iLCJpYXQiOjE2MjQ0MTUxODIsImV4cCI6MTYyNDQzMzE4Mn0.9yccm3fX5M6-0kqv9wKP2xAVHE3PBrWfv7uhxrVxlNa7h0tEyFcusblRGgJctleOzAhxIkOs06ky-YKBVUJVaA",
    "type": "Bearer",
    "email": "adedotunalausa@gmail.com",
    "roles": [
    "ROLE_USER"
    ]
    }

## Get quiz questions

Api endpoint that gets the list of questions available in the database
> A successful request will return HTTP 200 Status

> The questions and their options are returned as well

### Request

`GET /api/v1/quiz`

    curl --location --request GET 'http://localhost:7585/api/quiz'

### Response

    {
        "questions": [
            {
                "id": 5,
                "createdAt": "2021-06-23T02:50:04.511+00:00",
                "updatedAt": "2021-06-23T02:50:04.511+00:00",
                "title": "How do you create a variable with the numeric value 5",
                "optionA": "num x = 5",
                "optionB": "float x = 5",
                "optionC": "int x = 5"
            },
            {
                "id": 1,
                "createdAt": "2021-06-23T02:50:04.445+00:00",
                "updatedAt": "2021-06-23T02:50:04.445+00:00",
                "title": "What is a correct syntax to output \"Hello World\" in Java?",
                "optionA": "echo \"Hello World\"",
                "optionB": "printf(\"Hello World\")",
                "optionC": "System.out.println(\"Hello World\")"
            },
            {
                "id": 8,
                "createdAt": "2021-06-23T02:50:04.518+00:00",
                "updatedAt": "2021-06-23T02:50:04.518+00:00",
                "title": "Which operator is used to add together two values?",
                "optionA": "&&",
                "optionB": ".add()",
                "optionC": "+"
            },
            {
                "id": 7,
                "createdAt": "2021-06-23T02:50:04.516+00:00",
                "updatedAt": "2021-06-23T02:50:04.516+00:00",
                "title": "Which method can be used to find the length of a string?",
                "optionA": "length()",
                "optionB": "getSize()",
                "optionC": "size()"
            }
        ]
    }

## Submit answers and get result

Api endpoint that sends the aswers selected for each question to the server
> A successful post request will return HTTP 200 Status

> The username, email, number of questions answered and the number of correct answers are returned

### Request

`POST /api/v1/quiz/submit`

    curl --location --request POST 'http://localhost:7585/api/v1/quiz/submit' \
        --data-raw '[
            {
                "questionId": 2,
                "answer": 2
            },
            {
                "questionId": 1,
                "answer": 3
            },
            {
                "questionId": 5,
                "answer": 3
            },
            {
                "questionId": 4,
                "answer": 1
            },
            {
                "questionId": 8,
                "answer": 3
            },
            {
                "questionId": 9,
                "answer": 2
            },
            {
                "questionId": 3,
                "answer": 2
            }
        ]'

### Response

    {
        "username": "demarxes",
        "email": "adedotunalausa@gmail.com",
        "totalQuestionsAnswered": 7,
        "totalCorrectAnswers": 7
    }
