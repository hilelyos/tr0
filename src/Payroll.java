//employee class
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
    public String getFirstName() {//get first name
        return firstName;
    }

    public String getLastName() {//get last name
        return lastName;
    }

    public int getId() {//get id
        return id;
    }

    // setters
    public void setFirstName(String firstName) {//set first name
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {//set last name
        this.lastName = lastName;
    }

    public void setId(int id) {//set id
        if (id < 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

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
        if (obj == null || getClass() != obj.getClass()) return false;//check if obj is null or not the same class
        Employee employee = (Employee) obj;
        return id == employee.id;
    }

    //abstract method
    public abstract float earnings();
}

// HourlyEmployee class
class HourlyEmployee extends Employee
{
    private int hours;
    private float wage;

//constructor
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

    // getters
    public float getWage() {
        return wage;
    }

    public int getHours() {
        return hours;
    }

    // setters
    public void setWage(float wage) {//set wage
        if (wage < 0) {//check if wage is less than 0
            throw new IllegalArgumentException("Wage must be greater than 0");
        }

        this.wage = wage;
    }

    public void setHours(int hours) {//set hours
        if (hours < 0) {//check if hours is less than 0
            throw new IllegalArgumentException("Hours must be greater than 0");
        }

        this.hours = hours;
    }


//toString
    @Override
    public String toString() {
        return super.toString() +
                ", wage=" + String.format("%.2f", wage) +
                ", hours=" + hours;
    }

    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;//check if obj is null or not the same class
        HourlyEmployee hourlyEmployee = (HourlyEmployee) obj;
        return super.equals(obj) && wage == hourlyEmployee.wage && hours == hourlyEmployee.hours;
    }

    //earnings
    @Override
    public float earnings() {
        return wage * hours;
    }
}

// CommissionEmployee class
class CommissionEmployee extends Employee
{
    private float grossSales;
    private int commission;

    //constructor
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
    public void setGrossSales(float grossSales) {//set gross sales
        if (grossSales < 0) {//check if gross sales is less than 0
            throw new IllegalArgumentException("Gross sales must be greater than 0");
        }

        this.grossSales = grossSales;
    }

    public void setCommission(int commission) {//set commission
        if (commission < 0) {//check if commission is less than 0
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

    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;//check if obj is null or not the same class
        CommissionEmployee commissionEmployee = (CommissionEmployee) obj;
        return super.equals(obj) && grossSales == commissionEmployee.grossSales && commission == commissionEmployee.commission;
    }

    //earnings
    @Override
    public float earnings() {
        return (grossSales * commission) / 100;
    }

}

// BasePlusCommissionEmployee class
class BasePlusCommissionEmployee extends CommissionEmployee
{
    private float baseSalary;

    //constructor
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
    public void setBaseSalary(float baseSalary) {//set base salary
        if (baseSalary < 0) {//check if base salary is less than 0
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

    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false; //check if obj is null or not the same class
        BasePlusCommissionEmployee basePlusCommissionEmployee = (BasePlusCommissionEmployee) obj;
        return super.equals(obj) && baseSalary == basePlusCommissionEmployee.baseSalary;
    }

    //earnings
    @Override
    public float earnings() {
        return super.earnings() + baseSalary;
    }
}

//test the previous classes in the "main" function
class payroll
{
    public static void main(String[] args) {//main function
        Employee[] employees = new Employee[3];
        employees[0] = new HourlyEmployee("Yair", "Goldshtein", 458473789, 30, 40);
        employees[1] = new CommissionEmployee("Guy", "Yehezkel", 457803579, 5000, 10);
        employees[2] = new BasePlusCommissionEmployee("Hilel Yosef", "Orenshtein", 874848785, 10000, 20, 500);

        for (Employee employee : employees) {//for each employee
            float earnings = employee.earnings();
            if (employee.getClass() == BasePlusCommissionEmployee.class) {//check if the employee is BasePlusCommissionEmployee, and if so, increase the base salary by 10%
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