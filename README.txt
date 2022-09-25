# Getting Started

# For running the frontend code after making sure dependencies in package.json are installed:
cd ../frontend
yarn start

# For backend, using IntelliJ IDEA:
- Install PostgreSQL server
- Add PostgreSQL bin (C:\Program Files\PostgreSQL\12\bin) directory path to the PATH environment
- Open command prompt, run the command: psql -U userName
- Enter your password when asked
- Create your database with the command: CREATE DATABASE postgres WITH ENCODING 'UTF8'
Note: You can use any name for the database (instead of postgres)
- In the IntelliJ IDEA app click on the Database section which is on the right hand side.
- After the section opens, click at the plus sign on top to add a data source and find PostgreSQL in Data Source
- After entering your username and password for the database and making sure you change the USER and PASSWORD inputs in application.properties,
you can now use the database with the backend app.
- In addition, to ensure import.sql is used to fill the table, you can add a database script by editing configurations in IntelliJ IDEA.

