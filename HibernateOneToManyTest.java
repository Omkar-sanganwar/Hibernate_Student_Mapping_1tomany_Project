package com.nov21;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateOneToManyTest {
	public static void main(String[] args) {
		// Load Hibernate Configuration
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg3.xml").buildSessionFactory();

		// Open a Session
		Session session = sessionFactory.getCurrentSession();

		// Begin a Transaction
		Transaction transaction = session.beginTransaction();

		try {
			// Create Students
			Student student = new Student();
			student.setName("Omkar");
			student.setMarks(85);

			Student student1 = new Student();
			student1.setName("Pranav");
			student1.setMarks(80);

			Student student2 = new Student(); // Correct this line
			student2.setName("Soham"); // Set name for student2
			student2.setMarks(80); // Set marks for student2

			Student student3 = new Student(); // Correct this line
			student3.setName("om"); // Set name for student2
			student3.setMarks(80); // Set marks for student2

			// Create Address List for Student 1
			Address address1 = new Address();
			address1.setCity("Pune");
			address1.setState("Maharashtra");
			address1.setPin(411001);
			address1.setStudent(student);

			Address address2 = new Address();
			address2.setCity("Mumbai");
			address2.setState("Maharashtra");
			address2.setPin(400001);
			address2.setStudent(student);

			ArrayList<Address> addresses1 = new ArrayList<>();
			addresses1.add(address1);
			addresses1.add(address2);

			// Set Addresses to Student 1
			student.setAddresses(addresses1);
//////////////////////////////////////////////////////////////////////////////////////////////////
			// Create Address List for Student 2
			Address address3 = new Address();
			address3.setCity("Nagpur");
			address3.setState("Maharashtra");
			address3.setPin(440001);
			address3.setStudent(student1);

			Address address4 = new Address();
			address4.setCity("Kolhapur");
			address4.setState("Maharashtra");
			address4.setPin(416003);
			address4.setStudent(student1);

			ArrayList<Address> addresses2 = new ArrayList<>();
			addresses2.add(address3);
			addresses2.add(address4);

			// Set Addresses to Student 2
			student1.setAddresses(addresses2);
///////////////////////////////////////////////////////////////////////////////////////////////////
			// Create Address List for Student 3
			Address address5 = new Address();
			address5.setCity("Nagpur");
			address5.setState("Maharashtra");
			address5.setPin(440001);
			address5.setStudent(student2);

			Address address6 = new Address();
			address6.setCity("Kolhapur");
			address6.setState("Maharashtra");
			address6.setPin(416003);
			address6.setStudent(student2);

			ArrayList<Address> addresses3 = new ArrayList<>();
			addresses3.add(address5);
			addresses3.add(address6);

			// Set Addresses to Student 3
			student2.setAddresses(addresses3);

///////////////////////////////////////////////////////////////////////////////////////////////////////////
			Address address7 = new Address();
			address7.setCity("Naanded");
			address7.setState("Maharashtra");
			address7.setPin(431604);
			address7.setStudent(student3);

			Address address8 = new Address();
			address8.setCity("Pune");
			address8.setState("Maharashtra");
			address8.setPin(431227);
			address8.setStudent(student3);

			ArrayList<Address> addresses4 = new ArrayList<>();
			addresses4.add(address7);
			addresses4.add(address8);

			// Set Addresses to Student 3
			student3.setAddresses(addresses4);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

			// Save Students (Cascade saves addresses too)
			session.save(student);
			session.save(student1);
			session.save(student2);
			session.save(student3);
			// Commit Transaction
			transaction.commit();
			System.out.println("Data saved successfully!");

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// Close the SessionFactory
			sessionFactory.close();
		}
	}
}
