package com.altafjava.orderby.manytomany;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Student student1 = new Student();
		student1.setName("David");
		Student student2 = new Student();
		student2.setName("Zahid");
		Student student3 = new Student();
		student3.setName("Ajay");
		Course course = new Course();
		course.setCourseName("Java");
		course.setStudents(List.of(student1, student2, student3));
//		Transaction transaction = session.beginTransaction();
//		session.persist(course);
//		transaction.commit();

		Course course2 = session.get(Course.class, 1);
		if (course2 == null) {
			System.out.println(course2);
		} else {
			System.out.println(course2);
			List<Student> students = course2.getStudents();
			for (Student student : students) {
				System.out.println(student);
//				System.out.println("CRS->STD: " + student.getCourses());
			}
		}
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
