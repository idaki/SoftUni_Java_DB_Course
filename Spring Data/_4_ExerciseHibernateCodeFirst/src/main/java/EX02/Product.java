package EX02;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @Column
    private String name;
    @Column
    private int quantity;
    @Column
    private double price;
    @OneToMany(targetEntity = Sale.class, mappedBy = "product")
    private Set<Sale> sales;

}
//•	product (id, name, quantity, price)
//•	customer (id, name, email, credit_card_number)
//•	store_location (id, location_name)
//•	sale (id, product_id, customer_id, store_location_id, date)