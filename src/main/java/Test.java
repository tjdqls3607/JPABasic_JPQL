import config.MyPersistenceUnitInfo;

import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// JPQL
// select 수행, insert X, updateO , delete O
// transaction begin, commit 사용X
public class Test {

	public static void main(String[] args) {

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql","true");
		props.put("hibernate.hbm2ddl.auto","update");

		EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
				new MyPersistenceUnitInfo(), props
		); // my persistence unit
		EntityManager em = emf.createEntityManager();

//		em.getTransaction().begin();

		// #1 normal query
//		{
//			// SQL : Select * from employee
//			String jpql = "select e from Employee e";	// Entity 를 이용한 query 형식
//			Query query = em.createQuery(jpql);
//			List<Employee> list = query.getResultList();
//
//			for (Employee employee : list) {
//				System.out.println(employee);
//			}
//		}

		// #2 typed query
//		{
//			// SQL : Select * from employee
//			String jpql = "select e from Employee e";	// Entity 를 이용한 query 형식
//			TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
//			List<Employee> list = query.getResultList();
//
//			for (Employee employee : list) {
//				System.out.println(employee);
//			}
//		}

		// #3 parameter
		{
			// SQL : "Select * from employee where id = 3"
			String jpql = "select e from Employee e where id >= :id";	// Entity 를 이용한 query 형식
			TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
			query.setParameter("id", 2);
			List<Employee> list = query.getResultList();

			for (Employee employee : list) {
				System.out.println(employee);
			}
		}

//		em.getTransaction().commit();  // 이 시점에 테이블에 반영한다.

		em.close();
	}

}