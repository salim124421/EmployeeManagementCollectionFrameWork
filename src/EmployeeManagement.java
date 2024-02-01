import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
public class EmployeeManagement {
    public static void main(String[] args) {
        // Example: Creating employee details with input data
        List<Employee> employees = new ArrayList<>();

        try {

            // Employee1
            Map<String, Salary> salaryDetails1 = new HashMap<>();
            for (int i = 1; i <= 12; i++) {
                LocalDate date = LocalDate.of(2024, 1, i);
                salaryDetails1.put(String.valueOf(i), new Salary(String.valueOf(i), date, 60000, 12000, 10000, 6000));
            }
            Address address1 = new Address("1", "City1", "State1", "Country1", "Local Address1");
            Employee employee1 = new Employee("1", "salim", LocalDate.of(1990, 1, 1), salaryDetails1, address1);
            if (employee1 != null) {
                employees.add(employee1);
            }

            // Employee2
            Map<String, Salary> salaryDetails2 = new HashMap<>();
            for (int i = 1; i <= 12; i++) {
                LocalDate date = LocalDate.of(2024, 1, i);
                salaryDetails2.put(String.valueOf(i), new Salary(String.valueOf(i), date, 55000, 11000, 9000, 5000));
            }
            Address address2 = new Address("3", "City2", "State2", "Country2", "Local Address2");
            Employee employee2 = new Employee("2", "kashif", LocalDate.of(1995, 5, 15), salaryDetails2, address2);
            if (employee2 != null) {
                employees.add(employee2);
            }

            // Employee3
            Map<String, Salary> salaryDetails3 = new HashMap<>();
            for (int i = 1; i <= 12; i++) {
                LocalDate date = LocalDate.of(2024, 1, i);
                salaryDetails3.put(String.valueOf(i), new Salary(String.valueOf(i), date, 55000, 11000, 9000, 5000));
            }
            Address address3 = new Address("3", "ABC", "State3", "Country3", "Local ABc");
            Employee employee3 = new Employee("3", "Zeeshan", LocalDate.of(1996, 5, 15), salaryDetails3, address3);
            if (employee3 != null) {
                employees.add(employee3);
            }

            // Operations on the list of employees
            // (unchanged code)
        } catch (DateTimeException | NumberFormatException e) {
            System.err.println("An error occurred while creating employee details: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("A NullPointerException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        // 1. Find sum of salary who live in the same city.
        Map<String, Double> sumSalaryByCity = new HashMap<>();
        for (Employee emp : employees) {
            String city = emp.getAddress().getCity().toLowerCase();
            double yearlySalary = emp.getYearlySalary();
            if (sumSalaryByCity.containsKey(city)) {
                sumSalaryByCity.put(city, sumSalaryByCity.get(city) + yearlySalary);
            } else {
                sumSalaryByCity.put(city, yearlySalary);
            }
        }
        System.out.println("1. Sum of salary by city: " + sumSalaryByCity);

        //2.Employees grouped by yearly salary:
        Map<Double, List<Employee>> employeesByYearlySalary = new HashMap<>();
        for (Employee emp : employees) {
            double yearlySalary = emp.getYearlySalary();
            if (employeesByYearlySalary.containsKey(yearlySalary)) {
                employeesByYearlySalary.get(yearlySalary).add(emp);
            } else {
                List<Employee> empList = new ArrayList<>();
                empList.add(emp);
                employeesByYearlySalary.put(yearlySalary, empList);
            }
        }
        System.out.println("2. Employees grouped by yearly salary:");
        //for (Map.Entry<Double, List<Employee>> entry : employeesByYearlySalary.entrySet()) {
        for (Map.Entry<Double, List<Employee>> entry : employeesByYearlySalary.entrySet()) {
            double yearlySalary = entry.getKey();
            List<Employee> employeesWithSameSalary = entry.getValue();
            System.out.println("   Yearly Salary: " + yearlySalary);
            for (Employee emp : employeesWithSameSalary) {
                System.out.println("      " + emp.getName());
            }
        }
//            double yearlySalary = entry.getKey();
//            List<Employee> employeesWithSameSalary = entry.getValue();
//            System.out.println("   Yearly Salary: " + yearlySalary);
//            for (Employee emp : employeesWithSameSalary) {
//                System.out.println("      " + emp.getName());

// 3. Calculate and Print Average Yearly Salary by State
        Map<String, Double> totalSalaryByState = new HashMap<>();
        Map<String, Integer> countByStat = new HashMap<>();
        for (Employee emp : employees) {
            String state = emp.getAddress().getState();
            double yearlySalary = emp.getYearlySalary();
            // Update the sum of salaries and count for the state
            totalSalaryByState.put(state, totalSalaryByState.getOrDefault(state, 0.0) + yearlySalary);
            countByStat.put(state, countByStat.getOrDefault(state, 0) + 1);
        }
        System.out.println("3. Average Yearly Salary by State:");
        for (String state : totalSalaryByState.keySet()) {
            double totalSalary = totalSalaryByState.get(state);
            int employeeCount = countByStat.get(state);
            double averageSalary = totalSalary / employeeCount;

            System.out.println("   State: " + state + ", Average Yearly Salary: " + averageSalary);
        }

        // 4.Number of Employee who have same address
        Map<String, Integer> countByAddress = new HashMap<>();
        for (Employee emp : employees) {
            String addressKey = String.valueOf(emp.getAddress()).toLowerCase();
            // Retrieve the current count for the addressKey
            int currentCount = countByAddress.getOrDefault(addressKey, 0);
            // Increment the count
            int newCount = currentCount + 1;
            // Update the map with the new count
            countByAddress.put(addressKey, newCount);
        }
        System.out.println("4.");
        for (Map.Entry<String, Integer> entry : countByAddress.entrySet()) {
            String addressKey = entry.getKey();
            int employeeCount = entry.getValue();
            System.out.println("Number of employees at address " + addressKey + ": " + employeeCount);
        }

        // 5. Find the employees TDS/TAX from salary yearly.
        Map<String, Double> empNameToYearlySalary = new HashMap<>();
        for (Employee emp : employees) {
            empNameToYearlySalary.put(emp.getName(), emp.getYearlySalary());
        }
        Map<String, Double> tdsByEmployee = new HashMap<>();
        for (Employee emp : employees) {
            double yearlySalary = emp.getYearlySalary();
            double tds = (yearlySalary * 0.30);
            tdsByEmployee.put(emp.getName(), tds);
        }
        System.out.println("5. TDS by employee:");
        for (Map.Entry<String, Double> entry : tdsByEmployee.entrySet()) {
            String employeeName = entry.getKey();
            double tds = entry.getValue();
            System.out.println("   Employee: " + employeeName + ", Yearly Salary: " + empNameToYearlySalary.get(employeeName) + ", TDS: " + tds);
        }

        // 8. Find how many employees live in the same country
        Map<String, Integer> countByCountry = new HashMap<>();
        for (Employee emp : employees) {
            String country = emp.getAddress().getCountry().toLowerCase();
            if (countByCountry.containsKey(country)) {
                countByCountry.put(country, countByCountry.get(country) + 1);
            } else {
                countByCountry.put(country, 1);
            }
        }
        System.out.println("8. Number of employees in the same country: " + countByCountry);

        // 9. Find the employee sum(salary) range: 20k to 1 lakh (monthly)
        double sumInRange=0;
        for (Employee emp : employees) {
            double yearlySalary = emp.getYearlySalary();
            double monthlySalary = yearlySalary / 12.0;
            if (monthlySalary >= 20000 && monthlySalary <= 100000) {
                sumInRange += monthlySalary;
            }
        }
        System.out.println("9. Sum of monthly salary in the range 20k to 1 lakh: " + sumInRange);

        // 10. Find sum of salary and employee count by state
        Map<String, Double> sumSalaryByState = new HashMap<>();
        Map<String, Integer> countByState = new HashMap<>();
        for (Employee emp : employees) {
            String state = emp.getAddress().getState();
            double yearlySalary = emp.getYearlySalary();
            sumSalaryByState.put(state, sumSalaryByState.getOrDefault(state, 0.0) + yearlySalary);
            countByState.put(state, countByState.getOrDefault(state, 0) + 1);
        }
        System.out.println("10. Sum of salary by state: " + sumSalaryByState);
        System.out.println("    Number of employees by state: " + countByState);

     //6.Sort by salary
        System.out.println("6. Sort By salary yearly");
        Comparator<Employee> salaryComparator = new SalaryComparator();
        employees.sort(salaryComparator);
        for (Employee emp : employees) {
            System.out.println("   Employee: " + emp.getName() + ", Yearly Salary: " + emp.getYearlySalary());
        }

      //sort by Address
        AddressComparator addressComparator = new AddressComparator();
        employees.sort(addressComparator);
        System.out.println("7. Employees sorted by address:");
        for (Employee emp : employees) {
            System.out.println("   " + emp.getEmployeeNameAndAddress());
        }
    }
}
