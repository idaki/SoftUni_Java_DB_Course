package EX02;

import jakarta.persistence.*;

import java.util.Set;
@Entity
@Table(name="store_location")
public class StoreLocation {
    @Id
    private int id;
    @Column(name="location_name")
    private String locationName;
    @Column
    private String email;
    @OneToMany(targetEntity = Sale.class, mappedBy = "storeLocation")
    private Set<Sale>sales;
}
//•	product (id, name, quantity, price)
//•	customer (id, name, email, credit_card_number)
//•	store_location (id, location_name)
//•	sale (id, product_id, customer_id, store_location_id, date)