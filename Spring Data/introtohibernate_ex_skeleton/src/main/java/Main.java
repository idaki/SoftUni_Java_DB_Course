import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        entityManager.getTransaction().begin();
//        ex_2(entityManager);
//        entityManager.getTransaction().commit();

//        ex_3(entityManager);
//       ex_4(entityManager);
//     ex_5(entityManager);
        ex_6(entityManager);
    }

    private static void ex_6(EntityManager entityManager) {
    }


    private static void ex_2(EntityManager entityManager) throws IOException {

        List<Town> towns = entityManager.createQuery("FROM Town WHERE LENGTH(name)>5", Town.class)
                .getResultList();
        towns.forEach(town -> {
            town.setName(town.getName().toUpperCase());
            entityManager.persist(town);
        });
        System.out.println();
    }

    private static void ex_3(EntityManager entityManager) throws IOException {
        String[] input = READER.readLine().split("\\s+");
        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE firstName = :first_name AND last_name =:last_name", Employee.class)
                .setParameter("first_name", input[0])
                .setParameter("last_name", input[1])
                .getResultList();

        if (employees.size() > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void ex_4(EntityManager entityManager) {
        List<Employee> employees = entityManager.createQuery("SELECT e from Employee e WHERE e.salary>50000", Employee.class)
                .getResultList();

        employees.forEach(e -> System.out.println(e.getFirstName()));

    }

    private static void ex_5(EntityManager entityManager) {
        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e JOIN e.department d  WHERE d.id = 6 ORDER BY e.salary ASC, d.id ", Employee.class)
                .getResultList();
        System.out.println();
        resultList.forEach(e -> System.out.printf("%s %s from %s - $%.2f%n"
                , e.getFirstName()
                , e.getLastName()
                , e.getDepartment().getName()
                , e.getSalary()));
    }
}
