import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//      ex_6(entityManager);
    // ex_7(entityManager);

        ex_8(entityManager);
    }




    private static void ex_7(EntityManager entityManager) {
       entityManager.getTransaction().begin();

        List<Address> addresses = entityManager
                .createQuery("FROM Address ORDER BY size(employees) DESC,town.id ", Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses.forEach(e -> System.out.printf("%s, %s - %s employees%n",
                e.getText(),
                e.getTown().getName(),
                e.getEmployees().size()));

       entityManager.getTransaction().commit();

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

    private static void ex_6(EntityManager entityManager) throws IOException {
        String input = READER.readLine();
        Town town = entityManager.createQuery("FROM Town WHERE id=32", Town.class)
                .getSingleResult();

        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        Employee employee = entityManager.createQuery("FROM Employee e WHERE e.lastName =?1", Employee.class)
                .setParameter(1, input)
                .getSingleResult();

        entityManager.getTransaction().begin();

        entityManager.persist(address);
        employee.setAddress(address);
        entityManager.getTransaction().commit();
    }
    private static void ex_8(EntityManager entityManager) throws IOException {
        entityManager.getTransaction().begin();
        int employeeId = Integer.parseInt(READER.readLine());

        Employee employee = entityManager
                .createQuery("FROM Employee WHERE id = :employeeId", Employee.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
        System.out.printf("%s %s - %s%n\t%s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getProjects().stream()
                        .map(Project::getName)
                        .sorted()
                        .collect(Collectors.joining(System.lineSeparator() + "\t")));

        entityManager.getTransaction().commit();
    }
+`



}
