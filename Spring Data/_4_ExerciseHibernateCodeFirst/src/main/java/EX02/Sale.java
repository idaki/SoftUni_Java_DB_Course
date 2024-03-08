package EX02;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table (name="sale")
public class Sale {
    @Id
    private int id;
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
   // private StoreLocation storeLocation;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @ManyToOne(targetEntity = StoreLocation.class)
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private StoreLocation storeLocation;
    private Date date;
}
//•	product (id, name, quantity, price)
//•	customer (id, name, email, credit_card_number)
//•	store_location (id, location_name)
//•	sale (id, product_id, customer_id, store_location_id, date)\
///o	Product product
//o	Customer customer
//o	StoreLocation storeLocation
//o	Date date