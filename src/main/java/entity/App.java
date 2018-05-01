package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {

	public static void main(String[] args) throws SQLException, IOException {
		
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("mysql");
		EntityManager em = entityManagerFactory.createEntityManager();
				
		em.getTransaction().begin();
		
		country(em);
		city(em);
		user(em);
		
//Select 1	ok	
//		em.createQuery("SELECT u FROM User u", User.class).getResultList().forEach(System.out::println);

//Select 2  ok
//		em.createQuery("SELECT c FROM Country c ORDER BY c.name DESC", Country.class).getResultList().forEach(System.out::println);

//Select 3  ok
//		em.createQuery("SELECT c FROM City c ORDER BY c.name ASC", City.class).getResultList().forEach(System.out::println);

//Select 4  ok
//		em.createQuery("SELECT u FROM User u ORDER BY u.fullname DESC", User.class).getResultList().forEach(System.out::println);

//Select 5  ok
//		em.createQuery("SELECT c FROM Country c WHERE (c.name LIKE 'A%' OR c.name LIKE 'a%')",  Country.class)
//			.getResultList().forEach(System.out::println);

//Select 6  ok
//		em.createQuery("SELECT c FROM City c WHERE (c.name LIKE '%n_' OR c.name LIKE '%r_')", City.class)
//			.getResultList().forEach(System.out::println);

//Select 7  ok
//		System.out.println(em.createQuery("SELECT u FROM User u WHERE age = (SELECT min(age) FROM User)", User.class)
//				.getSingleResult());

//Select 8 
//		long count = em.createQuery("SELECT count(*) FROM User", Long.class).getSingleResult();
//		long summ = em.createQuery("SELECT sum(age) FROM User", Long.class).getSingleResult();
//		System.out.println("average age " +  "user = " + summ/count);

//Select 9	ok
		
//		em.createQuery("SELECT u FROM User u Join u.city c WHERE c.id=u.city.id", User.class)
//		.getResultList().forEach(c -> {
//			System.out.print(c.toString());
//			System.out.println(c.getCity());
//		});
		
//Select 10	****
//		em.createQuery("SELECT u FROM User u Join u.city c WHERE c.id=u.city.id AND u.id!=?1 ORDER BY c.id", User.class)
//			.setParameter(1, Arrays.asList(2, 5, 9, 12, 13, 16))
//			.getResultList().forEach(c -> {
//			System.out.print(c.getId());
//			System.out.print(c.toString());
//			System.out.println(c.getCity());
//		});

		
//Select 11	****
//		em.createQuery("SELECT u FROM User u JOIN u.city c WHERE c.id=u.city.id JOIN c.country cc WHERE cc.id = c.country.id", User.class)
//			.getResultList().forEach(c -> {
//			System.out.print(c.toString());
//			System.out.print(c.getCity());
//		});
		
		
		em.getTransaction().commit();
		em.close();
		entityManagerFactory.close();
		
	}
	
	static void user(EntityManager em) throws SQLException, IOException {
		List<City> city = em.createQuery("SELECT c FROM City c", City.class).getResultList();
        File f = new File("full_name.txt");
        if (f.exists()) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            try {
                String line;
                int i= 1;
                while ((line = br.readLine()) != null) {
        	        User users = new User();
        	        users.setFullname(line);
        	        users.setAge(15+i);
        	        i++;
        	        users.setCity(city.get(new Random().nextInt(city.size())));
        	        em.persist(users);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fr.close();
                br.close();
            }

        } else {
            System.out.println("File not found....");
            return;
        }
		
	} 
	static void city(EntityManager em) throws SQLException, IOException {
		
		List<Country> country = em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
        File f = new File("city.txt");
        if (f.exists()) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            try {
                String line;
                while ((line = br.readLine()) != null) {
        	        City city = new City();
        	        city.setName(line);
        	        city.setCountry(country.get(new Random().nextInt(country.size())));
        	        em.persist(city);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fr.close();
                br.close();
            }

        } else {
            System.out.println("File not found....");
            return;
        }
		
	} 

	static void country(EntityManager em) throws SQLException, IOException {

        File f = new File("country.txt");
        if (f.exists()) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            try {
                String line;
                while ((line = br.readLine()) != null) {
        	        Country country = new Country();
        	        country.setName(line);
        	        em.persist(country);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fr.close();
                br.close();
            }

        } else {
            System.out.println("File not found....");
            return;
        }
		
	} 
}
