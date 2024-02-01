import java.util.Comparator;

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        double salary1 = emp1.getYearlySalary();
        double salary2 = emp2.getYearlySalary();

        // Compare salaries in descending order
        return Double.compare(salary2, salary1);
    }
}