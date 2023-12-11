# Setting up the application:

## Database
From project root move to the sensor-db directory.

Build the Docker image with:

``docker build -t sensor-db .``

Run the image with:

``docker run --name sensor-db -p 5432:5432 -d sensor-db``

## Backend
From project root directory move to sensor-backend directory.

Build the backend with:

``mvn clean install``

Launch the backend with:

``mvn spring-boot:run``

## Frontend
From project root directory move to sensor-frontend directory.

Install dependencies:

``npm install``

Launch frontend app:

``npm run start``
