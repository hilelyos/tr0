abstract class Employee {
    // instance variables
    private String firstName;
    private String lastName;
    private int id;

    // constructor
    public Employee(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Employee() {
        this.firstName = "Plony";
        this.lastName = "Almony";
        this.id = 0;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    // setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    //toString
    @Override
    public String toString() {
        return "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id;
    }

    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id;
    }

    //abstract method
    public abstract float earnings();
}


class HourlyEmployee extends Employee
{
    private int hours;
    private float wage;


    public HourlyEmployee(String firstName, String lastName, int id, float wage, int hours) {
        super(firstName, lastName, id);
        this.wage = wage;
        this.hours = hours;
    }

    public HourlyEmployee() {
        super();
        this.wage = 0;
        this.hours = 0;
    }

    public float getWage() {
        return wage;
    }

    public int getHours() {
        return hours;
    }

    public void setWage(float wage) {
        if (wage < 0) {
            throw new IllegalArgumentException("Wage must be greater than 0");
        }

        this.wage = wage;
    }

    public void setHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours must be greater than 0");
        }

        this.hours = hours;
    }



    @Override
    public String toString() {
        return super.toString() +
                ", wage=" + String.format("%.2f", wage) +
                ", hours=" + hours;
    }

    @Override
    public float earnings() {
        return wage * hours;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        HourlyEmployee hourlyEmployee = (HourlyEmployee) obj;
        return super.equals(obj) && wage == hourlyEmployee.wage && hours == hourlyEmployee.hours;
    }
}

class CommissionEmployee extends Employee
{
    private float grossSales;
    private int commission;

    public CommissionEmployee(String firstName, String lastName, int id, float grossSales, int commission) {
        super(firstName, lastName, id);
        this.grossSales = grossSales;
        this.commission = commission;
    }

    public CommissionEmployee() {
        super();
        this.grossSales = 0;
        this.commission = 0;
    }

    // getters
    public float getGrossSales() {
        return grossSales;
    }

    public int getCommission() {
        return commission;
    }

    // setters
    public void setGrossSales(float grossSales) {
        if (grossSales < 0) {
            throw new IllegalArgumentException("Gross sales must be greater than 0");
        }

        this.grossSales = grossSales;
    }

    public void setCommission(int commission) {
        if (commission < 0) {
            throw new IllegalArgumentException("Commission must be greater than 0");
        }

        this.commission = commission;
    }


   //toString
    @Override
    public String toString() {
        return super.toString() +
                ", grossSales=" + String.format("%.2f", grossSales) +
                ", commission=" + commission;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CommissionEmployee commissionEmployee = (CommissionEmployee) obj;
        return super.equals(obj) && grossSales == commissionEmployee.grossSales && commission == commissionEmployee.commission;
    }

    @Override
    public float earnings() {
        return (grossSales * commission) / 100;
    }

}

class BasePlusCommissionEmployee extends CommissionEmployee
{
    private float baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, int id, float grossSales, int commission, float baseSalary) {
        super(firstName, lastName, id, grossSales, commission);
        this.baseSalary = baseSalary;
    }

    public BasePlusCommissionEmployee() {
        super();
        this.baseSalary = 0;
    }

    // getter
    public float getBaseSalary() {
        return baseSalary;
    }

    // setter
    public void setBaseSalary(float baseSalary) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary must be greater than 0");
        }

        this.baseSalary = baseSalary;
    }

    //toString
    @Override
    public String toString() {
        return super.toString() +
                ", baseSalary=" + String.format("%.2f", baseSalary);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BasePlusCommissionEmployee basePlusCommissionEmployee = (BasePlusCommissionEmployee) obj;
        return super.equals(obj) && baseSalary == basePlusCommissionEmployee.baseSalary;
    }

    @Override
    public float earnings() {
        return super.earnings() + baseSalary;
    }
}

class payroll
{
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new HourlyEmployee("Yair", "Goldshtein", 458473789, 30, 40);
        employees[1] = new CommissionEmployee("Guy", "Yehezkel", 457803579, 5000, 10);
        employees[2] = new BasePlusCommissionEmployee("Hilel Yosef", "Orenshtein", 874848785, 10000, 20, 500);

        for (Employee employee : employees) {
            float earnings = employee.earnings();
            if (employee.getClass() == BasePlusCommissionEmployee.class) {
                BasePlusCommissionEmployee basePlusCommissionEmployee = (BasePlusCommissionEmployee) employee;
                basePlusCommissionEmployee.setBaseSalary(basePlusCommissionEmployee.getBaseSalary() * 1.10f);
                earnings = basePlusCommissionEmployee.earnings();
            }

            System.out.println(employee);
            System.out.println("Earnings: " + earnings);
            System.out.println();
        }
    }
}