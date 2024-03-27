package abstractinterface.business;

import java.util.Scanner;

public interface IEmployee
{
    int BASIC_SALARY = 1300000;

    void inputData(Scanner scanner);//Input object info

    void displayData();//display object info
}
