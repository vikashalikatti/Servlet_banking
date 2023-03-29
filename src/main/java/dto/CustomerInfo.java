package dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class CustomerInfo {
	
	@Id
	@SequenceGenerator(initialValue = 12110111,allocationSize = 1,sequenceName = "Cust_id",name="Cust_id")
	@GeneratedValue(generator = "Cust_id")
	int customer_id;
	String name;
	String password;
	String email;
	long mobile;
	String gender;
	Date date;
	
	@OneToMany
	List<BankAccount> accounts;
}
