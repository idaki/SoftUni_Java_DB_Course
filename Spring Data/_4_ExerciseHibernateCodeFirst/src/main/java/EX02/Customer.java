package EX02;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="customer")
public class Customer   {
    @Id
    private int id;
    @Column (nullable = false)
    private String name;
    @Column (nullable = false)
    private String email;
    @Column (nullable = false)
    private String  creditCardNumber;
    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    private Set<Sale> sales;
}
//•	product (id, name, quantity, price)
//•	customer (id, name, email, credit_card_number)
//•	store_location (id, location_name)
//•	sale (id, product_id, customer_id, store_location_id, date)