package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.BankAccount;

public class Bank_dao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");

	public void save(BankAccount account) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(account);
		entityTransaction.commit();
	}

	public List<BankAccount> fetchAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select x from BankAccount x").getResultList();
	}

	public BankAccount find(long acno) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(BankAccount.class, acno);
	}

	public void update(BankAccount account) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(account);
		entityTransaction.commit();
	}
}
