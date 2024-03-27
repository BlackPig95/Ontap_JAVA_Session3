package abstractinterface.presentation;

import abstractinterface.businessimp.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeManagement
{
    private static final Employee[] employeeList = new Employee[100];
    private static final Scanner scanner = new Scanner(System.in);
    private static int employeeCount = 0;

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("********************MENU*********************\n" +
                    "1. Nhập thông tin cho n nhân viên\n" +
                    "2. Hiển thị thông tin nhân viên\n" +
                    "3. Tính lương cho các nhân viên\n" +
                    "4. Tìm kiếm nhân viên theo tên nhân viên\n" +
                    "5. Cập nhật thông tin nhân viên\n" +
                    "6. Xóa nhân viên theo mã nhân viên\n" +
                    "7. Sắp xếp nhân viên theo lương tăng dần (Comparable)\n" +
                    "8. Sắp xếp nhân viên theo tên nhân viên giảm dần (Comparator)\n" +
                    "9. Sắp xếp nhân vên theo năm sinh tăng dần (Comparator)\n" +
                    "10. Thoát");
            System.out.println("Nhập thao tác muốn thực hiện theo các số tương ứng");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice)
            {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    showEmployeeInfo();
                    break;
                case 3:
                    calculateEmployeeSalary();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    updateEmployeeInfo();
                    break;
                case 6:
                    deleteEmployeeById();
                    break;
                case 7:
                    sortAscendingSalary();
                    break;
                case 8:
                    sortDescendingName();
                    break;
                case 9:
                    sortAscendingYear();
                    break;
                case 10:
                    return;
            }
        }
    }

    private static void addEmployee()
    {
        System.out.print("Bạn muốn thêm bao nhiêu nhân viên? ");
        int n = Integer.parseInt(EmployeeManagement.scanner.nextLine());
        for (int i = 0; i < n; i++)
        {
            Employee newEmployee = new Employee();
            newEmployee.inputData(EmployeeManagement.scanner);
            employeeList[employeeCount] = newEmployee;
            employeeCount++;
            System.out.println("Thêm nhân viên thành công");
        }
    }

    private static void showEmployeeInfo()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        for (int i = 0; i < employeeCount; i++)
        {
            employeeList[i].displayData();
            System.out.println();
        }
    }

    private static void calculateEmployeeSalary()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        int salaryIndex = getIndexById();
        if (salaryIndex != -1)
        {
            System.out.println("Lương nhân viên này là :" + employeeList[salaryIndex].calculateSalary());
        } else System.out.println("Không tìm thấy nhân viên");
    }

    private static void searchByName()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        System.out.println("Nhập tên nhân viên cần tìm");
        System.out.println("Danh sách nhân viên được tìm thấy:");
        String searchName = scanner.nextLine();
        for (int i = 0; i < employeeCount; i++)
        {
            if (employeeList[i].getName().contains(searchName))
            {
                System.out.println("Tìm thấy nhân viên: " + employeeList[i].getName());
            }
        }
    }

    private static void updateEmployeeInfo()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        int updateIndex = getIndexById();
        if (updateIndex != -1)
        {
            while (true)
            {
                System.out.println("Mời lựa chọn cách nhập thông tin mới cho nhân viên");
                System.out.println("1. Cập nhật mã nhân viên");
                System.out.println("2. Cập nhật tên nhân viên");
                System.out.println("3. Cập nhật năm sinh");
                System.out.println("4. Cập nhật hệ số lương");
                System.out.println("5. Cập nhật hoa hồng");
                System.out.println("6. Cập nhật trạng thái");
                System.out.println("7. Cập nhật tất cả");
                System.out.println("0. Thoát");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice)
                {
                    case 1:
                        System.out.println("Mời nhập mã nhân viên mới");
                        employeeList[updateIndex].setId(scanner.nextLine());
                        System.out.println("Cập nhật thành công mã nhân viên");
                        break;
                    case 2:
                        System.out.println("Mời nhập tên nhân viên mới");
                        employeeList[updateIndex].setName(scanner.nextLine());
                        System.out.println("Cập nhật thành công tên nhân viên");
                        break;
                    case 3:
                        System.out.println("Mời nhập năm sinh mới");
                        employeeList[updateIndex].setYear(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Cập nhật thành công năm sinh nhân viên");
                        break;
                    case 4:
                        System.out.println("Mời nhập hệ số lương mới");
                        employeeList[updateIndex].setRate(Float.parseFloat(scanner.nextLine()));
                        System.out.println("Cập nhật thành công hệ số lương của nhân viên");
                        break;
                    case 5:
                        System.out.println("Mời nhập mức hoa hồng mới");
                        employeeList[updateIndex].setCommission(Float.parseFloat(scanner.nextLine()));
                        System.out.println("Cập nhật thành công mức hoa hồng nhân viên được nhận");
                        break;
                    case 6:
                        employeeList[updateIndex].setStatus(!employeeList[updateIndex].isStatus());
                        System.out.println("Trạng thái đã cập nhật: " +
                                (employeeList[updateIndex].isStatus() ? "Đang làm việc" : "Nghỉ việc"));
                        break;
                    case 7:
                        System.out.println("Mời nhập các thông tin mới");
                        employeeList[updateIndex].inputData(scanner);
                    case 0:
                        return;
                }
            }

        }
    }

    private static void deleteEmployeeById()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        int deleteIndex = getIndexById();
        if (deleteIndex != -1)
        {
            for (int i = deleteIndex; i < employeeCount - 1; i++)
            {
                employeeList[i] = employeeList[i + 1];
            }
            employeeList[employeeCount - 1] = null;
            employeeCount--;
            System.out.println("Xóa nhân viên thành công");
        } else System.out.println("Không tìm thấy nhân viên");
    }

    private static void sortAscendingSalary()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
//        Arrays.sort(employeeList); Phải dùng với mảng full phần tử, nếu không sẽ bị null pointer
        System.out.println("Sắp xếp thành công, thông tin sau sắp xếp: ");
        showEmployeeInfo();
    }

    private static void sortDescendingName()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        Arrays.sort(employeeList, Comparator.nullsLast((employee1, employee2) -> employee2.getName().compareTo(employee1.getName())));
        System.out.println("Sắp xếp thành công, thứ tự mới như sau:");
        showEmployeeInfo();
    }

    private static void sortAscendingYear()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return;
        }
        Arrays.sort(employeeList, Comparator.nullsLast((e1, e2) -> Integer.compare(e1.getYear(), e2.getYear())));
        System.out.println("Sắp xếp thành công, thứ tự mới như sau:");
        showEmployeeInfo();
    }

    private static int getIndexById()
    {
        if (employeeCount == 0)
        {
            System.out.println("Hiện không có nhân viên nào");
            return -1;
        }
        System.out.println("Nhập mã nhân viên cần tìm");
        String searchId = scanner.nextLine();
        for (int i = 0; i < employeeCount; i++)
        {
            if (employeeList[i].getId().equals(searchId))
                return i;
        }
        return -1;
    }


}
