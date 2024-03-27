package abstractinterface.businessimp;

import abstractinterface.business.IEmployee;

import java.util.Scanner;

public class Employee implements IEmployee, Comparable<Employee>
{
    private String id;
    private String name;
    private int year;
    private float rate;
    private float commission;
    private float salary;
    private boolean status;

    public Employee()
    {
    }

    public Employee(String id, String name, int year, float rate, float commission, float salary, boolean status)
    {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rate = rate;
        this.commission = commission;
        this.salary = salary;
        this.status = status;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public float getRate()
    {
        return rate;
    }

    public void setRate(float rate)
    {
        this.rate = rate;
    }

    public float getCommission()
    {
        return commission;
    }

    public void setCommission(float commission)
    {
        this.commission = commission;
    }

    public float getSalary()
    {
        return salary;
    }

    public void setSalary(float salary)
    {
        this.salary = salary;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner)
    {
        this.id = getInputId(scanner);//Mã nhân viên
        this.name = getInputName(scanner);//Tên nhân viên
        this.year = getInputYear(scanner);//Năm sinh
        this.rate = getInputRate(scanner);//Hệ số lương
        this.commission = getInputCommission(scanner);//Hoa hồng
        this.status = getInputStatus(scanner);//Trạng thái làm việc hay nghỉ việc
        this.salary = calculateSalary();//Tự tính, người dùng không nhập được
    }

    @Override
    public void displayData()
    {
        System.out.printf("Mã nhân viên: %s - Tên nhân viên: %s - Năm sinh: %d - " +
                        "Hệ số lương: %f - Hoa hồng mỗi tháng: %f - Lương thực nhận: %f - Trạng thái: %s",
                this.id, this.name, this.year, this.rate, this.commission, this.salary,
                this.status ? "Đang làm việc" : "Nghỉ việc");
    }

    public float calculateSalary()
    {
        return (this.rate * BASIC_SALARY) + this.commission;
    }

    private String getInputId(Scanner scanner)
    {
        while (true)
        {
            System.out.println("Nhập mã id của nhân viên");
            String inputId = scanner.nextLine();
            if (inputId.isBlank())
                System.out.println("Mã nhân viên không được để trống");
            else return inputId;
        }
    }

    private String getInputName(Scanner scanner)
    {
        while (true)
        {
            System.out.println("Nhập tên của nhân viên");
            String inputNamme = scanner.nextLine();
            if (inputNamme.isBlank())
                System.out.println("Tên không được để trống");
            else return inputNamme;
        }
    }

    private int getInputYear(Scanner scanner)
    {
        System.out.println("Nhập năm sinh của nhân viên");
        return Integer.parseInt(scanner.nextLine());
    }

    private float getInputRate(Scanner scanner)
    {
        while (true)
        {
            System.out.println("Nhập hệ số lương của nhân viên");
            float inputRate = Float.parseFloat(scanner.nextLine());
            if (inputRate > 1)
                return inputRate;
            else System.out.println("Hệ số lương phải lớn hơn 1");
        }

    }

    private float getInputCommission(Scanner scanner)
    {
        System.out.println("Nhập hoa hồng của nhân viên");
        return Float.parseFloat(scanner.nextLine());
    }

    private boolean getInputStatus(Scanner scanner)
    {
        while (true)
        {
            System.out.println("Nhập trạng thái của nhân viên: true - Đang làm việc, false - Nghỉ việc");
            String inputStatus = scanner.nextLine();
            if (inputStatus.equals("true") || inputStatus.equals("false"))
            {
                return Boolean.parseBoolean(inputStatus);
            } else System.out.println("Vui lòng nhập đúng true hoặc false");
        }
    }

    @Override
    public int compareTo(Employee otherEm)
    {
        return Float.compare(this.salary, otherEm.salary);
    }
}
