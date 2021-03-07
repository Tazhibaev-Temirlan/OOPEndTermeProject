package kz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity//entity class work with persistence
public class Customer {//entity for dbms

    @Id
    private Integer id;//id

    private String name;
    private String surname;
    private String phone;
    @OneToMany//we use one-to-many relationship because one customer can make several order
    private List<Order> orderList;//list of order have relationship with order class

    public Customer(){}//empty constructor because 'Hibernate' first creates objects and then fills them

    public Customer(Integer id, String name, String surname, String phone) {//our main constructor for customer table where we have all columns
        orderList=new ArrayList<>();//we create new array list for every customer if they have order
        this.id = id;//id
        this.name  = name;//name
        this.surname= surname;//surname
        this.phone =phone;//phone number
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) { this.orderList.add(order); }//renamed setter statement for order list
}