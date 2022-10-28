package com.altafjava.manytoone;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.manytoone.entity.Project;
import com.altafjava.manytoone.entity.Student;
import com.altafjava.onetomany.foreign.entity.Employee;
import com.altafjava.util.HibernateUtil;

public class ManyToOneTest {
	public static void main(String[] args) throws InterruptedException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Project project = new Project();
		project.setProjectName("HMS");
		Student student1 = new Student();
		student1.setName("Ajay");
		student1.setProject(project);
		session.persist(student1);
		Student student2 = new Student();
		student2.setName("David");
		student2.setProject(project);
		session.persist(student2);
		transaction.commit();
		Project project2 = session.get(Project.class, 1);
		System.out.println(project2);
		List<Student> students = project2.getStudents();
		if (students != null) {
			for (Student student : students) {
				System.out.println("proj->emp: " + student);
			}
		} else {
			System.out.println("proj->emp: " + students);
		}
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
