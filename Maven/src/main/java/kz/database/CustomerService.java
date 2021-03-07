package kz.database;

import kz.entity.Customer;

public class CustomerService implements DatabaseService<Customer> {//service to get connection in dbms with the 'customer' entity who implements methods and realise them
    private DatabaseConnector parentConnector;

    public CustomerService(DatabaseConnector parentConnector){//to get connection to this entity/table
        this.parentConnector=parentConnector;
    }

    public Customer findById(Object primaryKey){//first interface method
        return this.parentConnector.getManager().find(Customer.class, primaryKey);
    }
    public void save(Customer object){//second interface method
        this.parentConnector.getManager().persist(object);
    }
}
