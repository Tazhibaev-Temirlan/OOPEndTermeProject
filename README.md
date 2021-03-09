# OOPEndTermeProject
Project Description from: Tazhibaev Temirlan, Abdrakhmanov Bakdaulet, Sarybayev Amankeldy.
Project name: Apparel bot

We have created a telegram bot that can write to the buyer's database, send recommendations (goods with a discount), can record orders for a specific buyer (the one that activated the bot's chat), it also has a function to change the name, the last name and phone number, send already completed information about customer to correct it.
Our project consists of 6 classes and one interface. 
The main class, two entity class and three class for interact with database. 
The "Databaseconnector" class implements the database connection and also binds our entity classes to separate classes to implement the interface.
Methods in the interface are the most necessary, search for data by id, as well as save changes in the database.
The main class have main method for connection with telegram bot and check updates.
While tracking updates, we have created a number of conditions under which there is an interaction with the database.
