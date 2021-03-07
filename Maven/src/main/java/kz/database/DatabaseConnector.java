package kz.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnector {//main class for starting all services
    private final EntityManagerFactory managerFactory;
    private EntityManager manager;
    private final CustomerService customerService;
    private final OrderService orderService;

    public DatabaseConnector(String name){//constructor for starting all service with our entity class
        managerFactory= Persistence.createEntityManagerFactory(name);//with manager factory we starting work with entity to create statements/changes
        customerService=new CustomerService(this);//our first entity class
        orderService = new OrderService(this);//second entity class
    }
    public void startTransaction(){//check to start a transaction
        if (!isManagerOpened()){//if entity manager null condition
            manager = createEntityManager();//here we create an entity manager to fill or change data in the table
            manager.getTransaction().begin();//we start transaction
        }
    }
    public void endTransaction() throws RuntimeException{//we check to end our transaction
        if (isManagerOpened()){//work if connection not null
            manager.getTransaction().commit();//saving all changes in dbms
            manager.close();//close connection to dbms
        } else {//if we have trouble with connection
            throw new RuntimeException("Start transaction again");//it displays an error
        }
    }
    public boolean isManagerOpened(){//just an operator to check the start of the database change manager
        return (manager!=null && manager.isOpen());//manager start work if manager not null and manager open
    }//boolean operator for checking dbms manager work
    private EntityManager createEntityManager(){//method to create operator to fill or change entity in table
        return managerFactory.createEntityManager();
    }
    public EntityManager getManager(){//method for search entities to implement the 'databaseservice' interface
        return manager;
    }

    public CustomerService getCustomerService(){//method for work with first entity and to interact with interface
        return customerService;
    }
    public OrderService getOrderService() {//method for work with second entity and to interact with interface
        return orderService;
    }
}
