import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Employee {
    private final String id;
    private final String name;
    private final LocalDate dob;
    private final Map<String, Salary> salary;
    private  Address address;
    public Employee(String id, String name, LocalDate dob, Map<String, Salary> salary, Address address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.salary = new HashMap<>(salary);
        this.address = address;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDate getDob() {
        return dob;
    }
    public Map<String, Salary> getSalary() {
        return new HashMap<>(salary);
    }
    public Address getAddress() {
        return address;
    }

    public double getYearlySalary() {
        double yearlySalary = 0;
        for (Salary sal : salary.values()) {
            yearlySalary += sal.getYearlySalary();
        }
        return yearlySalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", address=" + address +
                '}';
    }
    public static Comparator<Employee> ADDRESS_COMPARATOR = Comparator.comparing(emp -> emp.getAddress().toString());
    // public static Comparator<Employee> SALARY_COMPARATOR = Comparator.comparingDouble(Employee::getYearlySalary);
    public String getEmployeeNameAndAddress() {
        return "Employee: " + getName() + ", Address: " + getAddress();
    }
}
