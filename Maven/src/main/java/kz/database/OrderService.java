package kz.database;

import kz.entity.Order;

public class OrderService {//service to get connection in dbms with the 'order' entity who implements methods and realise them
    private DatabaseConnector parentConnector;

    public OrderService(DatabaseConnector parentConnector){//to get connection to this entity/table
        this.parentConnector=parentConnector;
    }

    public Order findById(Object primaryKey){//first interface method
        return this.parentConnector.getManager().find(Order.class, primaryKey);
    }

    public void save(Order object){//second interface method
        this.parentConnector.getManager().persist(object);
    }
}
