package com.cg.jpastart.entities;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Client {

	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	static {
		factory = Persistence.createEntityManagerFactory("JPA-PU");
	}
	
	public static EntityManager getEntityManager() {
		if(em==null || !em.isOpen()) {
			em = factory.createEntityManager();
		}
		return em;
	}
	
	public static void main(String[] args) {
		
		
		em = getEntityManager();
		em.getTransaction().begin();
		Student student = new Student();
		student.setName("aadhish");
		Addr homeAddress = new Addr();
		homeAddress.setStreet("MG Road2");
		homeAddress.setCity("Pune2");
		homeAddress.setState("Maharashtra2");
		homeAddress.setZipCode("411 0217");
		
		//inject address into student
/*		student.setAddress(homeAddress);
*/		
		homeAddress.setStudent(student);
		//persist only student, no need to persist Address explicitly
		em.persist(student);
		em.getTransaction().commit();
		
		System.out.println("Added one student with address to database.");
		em.close();
		factory.close();
	}
}
