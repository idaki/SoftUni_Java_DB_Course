package EX1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "wizard_deposit")

public class WizardDeposit {

    @Id
    private int id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;
    @Column(length = 1000)
    private String notes;
    @Column(nullable = false)
    private int age;
    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;
    @Column(name = "magic_wand_size ", length = 215)
    private int magicWandCSize;
    @Column(name = "deposit_group", length = 20)
    private String depositGroup;
    @Column( name="deposit_start_date")
    private LocalDate depositStartDate;
    @Column(name= "deposit_amount")
    private BigDecimal depositAmount;

    @Column (name="deposit_interest")
    private double depositInterest;
    @Column (name="deposit_charge")
    private double depositCharge;

    @Column( name="deposit_expiration_date")
    private LocalDate depositExpirationDate;
    @Column( name="is_deposit_expired")
    private Boolean isDepositExpired;


}
