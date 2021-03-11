package kz;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import kz.database.DatabaseConnector;
import kz.entity.Customer;
import kz.entity.Order;

import java.io.File;

public class App {
    public final static void main(String[] args){
        DatabaseConnector connector = new DatabaseConnector("apparel");/*we connect to the database via 'persistence unit name'
         this is the name of the list configurations of our database
         then the service 'Hibernate' connect us to the database through advance the prescribed metadata/characteristics
        */
        String Token ="";//out token
        TelegramBot bot= new TelegramBot(Token);//we create our bot with our token

        bot.setUpdatesListener(updates -> {//we check all updates from bot
            updates.forEach(System.out::println);//output all updates && notification to consol
            updates.forEach(update-> {//we divide all updates from the telegram bot into separate 'update' and already interact with them
                        connector.startTransaction();//we start transaction
                        Customer customer = connector.getCustomerService().findById(update.message().from().id());//through the operator find() we are looking for the entity class, the search was carried out through the primary key id
                        if (customer == null) { // checking if a user exists in MongoDB
                            bot.execute(new SendMessage(update.message().chat().id(), "You need to send your name, surname and phone number in one sentence separated by a space."));
                            connector.getCustomerService().save(new Customer(update.message().from().id(), null,null,null));// we save new Customer id and set to other values of constructor null parametor
                        } else if (customer.getName() == null) { //after we collect the first name, last name and number from the user
                        String[] entityC = update.message().text().split(" ");//next message contains user name surname and phone number we decide it to String words array
                        customer.setName(entityC[0]);//first word set Name
                        customer.setSurname(entityC[1]);//second word set Surname
                        customer.setPhone(entityC[2]);//third word set phone Number
                        connector.getCustomerService().save(customer);//we save all changes from that condition
                        bot.execute(new SendMessage(update.message().chat().id(),
                                "You are recorded in the database in the future to receive recommendations, pass the '/start' command,\n" +
                                        "for more info about commands pass the '/help' command"));
                        }
                        else if (customer !=null && update.message().text().contains("/start")){//and our main condition for displaying a recommendation after bot activation and registration
                            bot.execute(new SendPhoto(update.message().chat().id(), new File(
                                    "/Users/Temirlan/Desktop/2 Trimester/JAVA/Final Project/Maven/src/main/resources/Jpg/1.jpg")));
                            bot.execute(new SendMessage(update.message().chat().id(),
                                    "Sweatshirt: 'Armani Exchange'"+"\n"+"id: WH21367\n"+"Quantity in stock: 20\n"+"Cost: 27 000tg 5̶3̶9̶9̶0̶t̶g̶"));

                            bot.execute(new SendPhoto(update.message().chat().id(), new File(
                                    "/Users/Temirlan/Desktop/2 Trimester/JAVA/Final Project/Maven/src/main/resources/Jpg/2.jpg")));
                            bot.execute(new SendMessage(update.message().chat().id(),
                                    "Hoodie: 'The North Face'"+"\n"+"id: QU20595\n"+"Quantity in stock: 10\n"+"Cost: 16 500tg 20̶ ̶0̶0̶0̶t̶g̶"));

                            bot.execute(new SendPhoto(update.message().chat().id(), new File(
                                    "/Users/Temirlan/Desktop/2 Trimester/JAVA/Final Project/Maven/src/main/resources/Jpg/3.jpeg")));
                            bot.execute(new SendMessage(update.message().chat().id(),
                                    "Sweatshirt: 'Tommy Hilfiger'"+"\n"+"id: WT20013\n"+"Quantity in stock: 10\n"+"Cost: 20 000tg 3̶2̶ ̶9̶9̶0̶t̶g̶"));

                            bot.execute(new SendPhoto(update.message().chat().id(), new File(
                                    "/Users/Temirlan/Desktop/2 Trimester/JAVA/Final Project/Maven/src/main/resources/Jpg/4.jpg")));
                            bot.execute(new SendMessage(update.message().chat().id(),
                                    "Sneakers: 'ASICS Tarther Og'\n"+"id: AS20035\n"+"Quantity in stock: 10\n"+"Cost: 20 000tg 4̶0̶ ̶9̶9̶0̶t̶g̶̶"));

                            bot.execute(new SendPhoto(update.message().chat().id(), new File(
                                    "/Users/Temirlan/Desktop/2 Trimester/JAVA/Final Project/Maven/src/main/resources/Jpg/5.jpg")));
                            bot.execute(new SendMessage(update.message().chat().id(),
                                    "Sneakers: 'Lacoste'\n"+"id: LK20074\n"+"Quantity in stock: 10\n"+"Cost: 46 890tg 6̶6̶ ̶9̶9̶0̶t̶g̶"));

                            bot.execute(new SendMessage(update.message().chat().id(), "Please, send me your chosen product id after command /buy in one sentence separated by space"));
                        }else if (update.message().text().contains("/buy") && update.message().text().length()>5) {//here we write the conditions for the purchase
                            String[] entityO = update.message().text().split(" ");//here we create an array of words in order to split sentence into separate words
                                Order order = new Order();//we create new order
                                order.setProduct_id(entityO[1]);//we set id product id
                                customer.addOrder(order);//we add product id to customer order list
                                connector.getOrderService().save(order);//we confirm all changes for order_table in dbms
                                connector.getCustomerService().save(customer);//we confirm all changes for customer_table in dbms
                                bot.execute(new SendMessage(update.message().chat().id(), "We got your order."));

                        }else if (update.message().text().contains("/change1") && update.message().text().length()>9) {//next comes the name change condition
                            String[] entityC = update.message().text().split(" ");//we again split sentence to words
                            customer.setName(entityC[1]);//change name in dbms to chosen
                            connector.getCustomerService().save(customer);//confirm all changes for customer_table in dbms
                            bot.execute(new SendMessage(update.message().chat().id(), "We change your name."));
                        }else if (update.message().text().contains("/change2") && update.message().text().length()>9) {//next comes the surname change condition
                            String[] entityC = update.message().text().split(" ");//another one
                            customer.setSurname(entityC[1]);//change surname in dbms to chosen
                            connector.getCustomerService().save(customer);//confirm all changes for customer_table in dbms
                            bot.execute(new SendMessage(update.message().chat().id(), "We change your surname."));
                        }else if (update.message().text().contains("/change3") && update.message().text().length()>9) {//next comes the phone number change condition
                            String[] entityC = update.message().text().split(" ");// another one split sentence to words
                                customer.setPhone(entityC[1]);//change phone number in dbms to chosen
                                connector.getCustomerService().save(customer);//confirm all changes in customer_table
                                bot.execute(new SendMessage(update.message().chat().id(), "We change your number."));
                        }else if (update.message().text().contains("/info") && update.message().text().length()>6) {//just display information about the profile
                            bot.execute(new SendMessage(update.message().chat().id(),//before that, we used the findById method all changes are made by operating on this part
                                    // and for output you only need to specify the user id through which we work
                                    customer.getName()+" "+customer.getSurname()+" "+ customer.getPhone()));//id we have this chat id through which the telegram user interacts
                        }else if (update.message().text().contains("/help") && update.message().text().length()>6) {//just display information about chat commands
                            bot.execute(new SendMessage(update.message().chat().id(), "Hello, this is a list of commands to change first name last name phone number in the database of our store.\n" +
                                    "If you want see your profile write command '/info'\n" +
                                    "If you want change your name to correct you need write '/change1' + (your correct name)\n" +
                                    "If you want change your surname to correct you need write '/change2' + (your correct surname)\n" +
                                    "If you want change your phone number to correct you need write '/change3' + (your correct phone number)\n"));
                        }
                        connector.endTransaction();//after all changes we save it and close connection to dbms
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;//read all notifications from the telegram
        });
    }
}
