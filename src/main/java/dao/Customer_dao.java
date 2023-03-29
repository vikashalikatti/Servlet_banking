package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.CustomerInfo;

public class Customer_dao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public List<CustomerInfo> check(String email) {
		return entityManager.createQuery("select x from CustomerInfo x where email = ?1").setParameter(1, email)
				.getResultList();

	}

	public List<CustomerInfo> check(long mobile) {
		return entityManager.createQuery("select x from CustomerInfo x where mobile = ?1").setParameter(1, mobile)
				.getResultList();

	}

	public void save(CustomerInfo customerInfo) {
		entityTransaction.begin();
		entityManager.persist(customerInfo);
		entityTransaction.commit();
	}
	
	public CustomerInfo login(int user_id) {
		return entityManager.find(CustomerInfo.class, user_id); 
	}
	
	public void update(CustomerInfo customerInfo) {
		entityTransaction.begin();
		entityManager.merge(customerInfo);
		entityTransaction.commit();
	}

}
