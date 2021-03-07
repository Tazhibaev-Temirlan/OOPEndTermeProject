package kz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Order {

    @Id//primary key for order table
    @GeneratedValue//after all order we generate id for order table
    private Integer id;

    private Integer customer_id;
    private String product_id;

    public Order(){}//another empty constructor because 'Hibernate' first creates objects and then fills them

    public Order(Integer customer_id, String product_id){//our main constructor for order table where we have all columns
        this.customer_id=customer_id;
        this.product_id=product_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
