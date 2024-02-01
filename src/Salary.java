import java.time.LocalDate;

class Salary {
    private final String id;
    private LocalDate date;
    private  double basic;
    private  double hra;
    private  double da;
    private  double epf;

    public Salary(String id, LocalDate date, double basic, double hra, double da, double epf) {
        this.id = id;
        this.date = date;
        this.basic = basic;
        this.hra = hra;
        this.da = da;
        this.epf = epf;
    }

    public double getYearlySalary() {
        return basic + hra + da - epf;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", basic=" + basic +
                ", hra=" + hra +
                ", da=" + da +
                ", epf=" + epf +
                '}';
    }
}
