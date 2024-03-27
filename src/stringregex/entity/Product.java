package stringregex.entity;

import java.util.Scanner;

public class Product
{
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String description;
    private boolean status;

    public Product()
    {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String description, boolean status)
    {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public float getImportPrice()
    {
        return importPrice;
    }

    public void setImportPrice(float importPrice)
    {
        this.importPrice = importPrice;
    }

    public float getExportPrice()
    {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice)
    {
        this.exportPrice = exportPrice;
    }

    public float getProfit()
    {
        return profit;
    }

    public void setProfit(float profit)
    {
        this.profit = profit;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] productList, int productCount)
    {
        this.productId = getInputId(scanner, productList, productCount);//Đặt Id
        this.productName = getInputName(scanner, productList, productCount);//Đặt tên
        this.importPrice = getInputImportPrice(scanner);//Giá nhập
        this.exportPrice = getInputExportPrice(scanner);//Giá xuất
        this.profit = calProfit();//Lợi nhuận
        this.quantity = getInputQuantity(scanner);//Số lượng sản phẩm
        System.out.println("Nhập vào mô tả sản phẩm");
        this.description = scanner.nextLine();//Mô tả không có yêu cầu nên không cần tách hàm
        getInputStatus(scanner);//Trạng thái sản phẩm
    }

    public void displayData()
    {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá nhập: %.1f - Giá bán: %.1f\n",
                this.productId, this.productName, this.importPrice, this.exportPrice);
        System.out.printf("Lợi nhuận khi bán: %.1f - Số lượng hiện có: %d - Trạng thái: %s\n"
                , this.profit, this.quantity, this.status ? "Đang bán" : "Không bán");
        System.out.println("Mô tả sản phẩm: " + this.description);
    }

    public float calProfit()
    {
        return this.exportPrice - this.importPrice;
    }

    private String getInputId(Scanner scanner, Product[] productList, int productCount)
    {
        while (true)
        {
            String regex = "^P.{3}$";
            System.out.println("Nhập mã sản phẩm, bắt đầu bằng P và có tổng cộng 4 ký tự");
            String inputId = scanner.nextLine();
            if (!inputId.matches(regex))
            {   //Sai định dạng thì không cần check đến bên dưới
                System.out.println(CONSTANT.REGEX_MESSAGE);
                continue;
            }
            if (productCount == 0)//Nếu chưa có phần tử nào trong mảng thì không cần kiểm tra trùng lặp
                return inputId;
            boolean idExisted = false;
            for (int i = 0; i < productCount; i++)
            {
                if (productList[i].productId.equals(inputId))
                {
                    System.out.println("Mã sản phẩm đã tồn tại. " + CONSTANT.INPUT_AGAIN);
                    idExisted = true;
                    break;
                }
            }
            if (!idExisted)
            {
                return inputId;
            }
        }
    }

    private String getInputName(Scanner scanner, Product[] productList, int productCount)
    {
        while (true)
        {
            String regex = ".{6,50}";
            System.out.println("Nhập tên sản phẩm, bao gồm từ 6-50 ký tự");
            String inputName = scanner.nextLine();
            if (!inputName.matches(regex))
            {
                System.out.println(CONSTANT.REGEX_MESSAGE);
                continue;
            }
            if (productCount == 0)
                return inputName;
            boolean nameExisted = false;
            for (int i = 0; i < productCount; i++)
            {
                if (productList[i].productName.equals(inputName))
                {
                    System.out.println("Tên sản phẩm đã tồn tại. " + CONSTANT.INPUT_AGAIN);
                    nameExisted = true;
                    break;
                }
            }
            if (!nameExisted)
                return inputName;
        }
    }

    private float getInputImportPrice(Scanner scanner)
    {
        while (true)
        {
            System.out.print("Giá nhập vào của sản phẩm này là: ");
            float importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice <= 0)
            {
                System.out.println("Giá nhập sản phẩm phải lớn hơn 0. " + CONSTANT.INPUT_AGAIN);
                continue;
            }
            return importPrice;
        }
    }

    private float getInputExportPrice(Scanner scanner)
    {
        while (true)
        {
            System.out.print("Giá bán sản phẩm này là: ");
            float exportPrice = Float.parseFloat(scanner.nextLine());
            if (exportPrice / this.importPrice < 1.2)
            {
                System.out.println("Giá bán ra phải lớn hơn 20% so với giá nhập vào. " + CONSTANT.INPUT_AGAIN);
                continue;
            }
            return exportPrice;
        }
    }

    private int getInputQuantity(Scanner scanner)
    {
        while (true)
        {
            System.out.println("Nhập vào số lượng của sản phẩm");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > 0)
            {
                return quantity;
            } else
            {
                System.out.println("Số lượng sản phẩm phải lớn hơn 0. " + CONSTANT.INPUT_AGAIN);
            }
        }
    }

    private void getInputStatus(Scanner scanner)
    {
        while (true)
        {
            System.out.println("Nhập vào trạng thái của sản phẩm này: true - Đang bán, false - Không bán");
            String inputStatus = scanner.nextLine();
            if (inputStatus.equals("true") || inputStatus.equals("false"))
            {
                this.status = Boolean.parseBoolean(inputStatus);
                return;
            } else
            {
                System.out.println("Vui lòng nhập đúng true hoặc false");
            }
        }
    }
}
