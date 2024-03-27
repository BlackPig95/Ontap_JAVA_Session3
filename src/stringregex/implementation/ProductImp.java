package stringregex.implementation;

import abstractinterface.businessimp.Employee;
import stringregex.entity.CONSTANT;
import stringregex.entity.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductImp
{
    private final static Scanner scanner = new Scanner(System.in);
    private final static Product[] productList = new Product[100];
    private static int productCount = 0;

    public static void main(String[] args)
    {
        ProductImp productImp = new ProductImp();
//        Product product1 = new Product("1", "SP1", 3, 8, 5, 10, " ", true);
//        Product product2 = new Product("2", "SP2", 4, 10, 6, 50, " ", false);
//        Product product3 = new Product("3", "SP3", 5, 12, 7, 20, " ", true);
//        Product product4 = new Product("4", "SP4", 6, 13, 7, 33, " ", false);
//        Product product5 = new Product("5", "SP5", 7, 15, 8, 24, " ", true);
//        productList[0] = product3;
//        productList[1] = product1;
//        productList[2] = product5;
//        productList[3] = product4;
//        productList[4] = product2;
//        productCount = 5;
        while (true)
        {
            System.out.printf("***********************MENU**************************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Tính lợi nhuận các sản phẩm\n" +
                    "4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần\n" +
                    "5. Thống kê các sản phẩm theo giá\n" +
                    "6. Tìm các sản phẩm theo tên sản phẩm\n" +
                    "7. Thêm số lượng sản phẩm\n" +
                    "8. Bán sản phẩm\n" +
                    "9. Cập nhật trạng thái sản phẩm\n" +
                    "10. Thoát\n");
            System.out.println("Mời nhập thao tác muốn thực hiện theo các số ở trên");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice)
            {
                case 1:
                    productImp.addProduct();
                    break;
                case 2:
                    productImp.displayAllProduct();
                    break;
                case 3:
                    productImp.calculateAllProductProfit();
                    break;
                case 4:
                    productImp.sortDescendingProfit();
                    break;
                case 5:
                    productImp.enumerateInPriceRange();
                    break;
                case 6:
                    productImp.findProductByName();
                    break;
                case 7:
                    productImp.addProductQuantity();
                    break;
                case 8:
                    productImp.sellProduct();
                    break;
                case 9:
                    productImp.updateProductStatus();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Lựa chọn không khả dụng. " + CONSTANT.INPUT_AGAIN);
                    break;
            }
        }
    }

    private void addProduct()
    {
        System.out.println("Nhập số lượng sản phẩm muốn thêm mới");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++)
        {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1));
            Product newProduct = new Product();
            newProduct.inputData(scanner, productList, productCount);
            productList[productCount] = newProduct;
            System.out.println("Thêm sản phẩm thành công");
            productCount++;
        }
    }

    private void displayAllProduct()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return;
        }
        for (int i = 0; i < productCount; i++)
        {
            productList[i].displayData();
            System.out.println("-------------------------------------------------------------------");
        }
    }

    private void calculateAllProductProfit()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return;
        }
        for (int i = 0; i < productCount; i++)
        {
            System.out.printf("Sản phẩm %s có mức lợi nhuận là %f\n",
                    productList[i].getProductName(), productList[i].calProfit());
        }
    }

    private void sortDescendingProfit()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return;
        }
        for (int i = 1; i < productCount; i++)
        {
            int j = i;
            Product tempProduct = productList[i];
            while (j > 0 && tempProduct.getProfit() > productList[j - 1].getProfit())
            {
                productList[j] = productList[j - 1];
                j--;
            }
            productList[j] = tempProduct;
        }
        System.out.println("Danh sách đã sắp xếp:");
        for (int i = 0; i < productCount; i++)
        {
            System.out.print(productList[i].getProductName() + "-" + productList[i].getProfit() + " | ");
        }
        System.out.println();
        //Cách 2
//        Arrays.sort(productList, Comparator.nullsLast((product1, product2) -> Float.compare(product2.getProfit(), product1.getProfit())));
        //Cách 3 không ảnh hưởng mảng cũ
//        Product[] sortedArray = Arrays.stream(productList).
//                sorted(Comparator.nullsLast((p1, p2) -> Float.compare(p2.getProfit(), p1.getProfit()))).toList().toArray(new Product[0]);
    }

    private void enumerateInPriceRange() //list = enumerate
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return;
        }
        System.out.println("Nhập giới hạn dưới của khoảng giá bán muốn kiểm tra");
        float lowerBound = Float.parseFloat(scanner.nextLine());
        float upperBound;
        while (true)
        {
            System.out.println("Nhập giới hạn trên của khoảng giá bán muốn kiểm tra");
            upperBound = Float.parseFloat(scanner.nextLine());
            if (upperBound <= lowerBound)
            {
                System.out.println("Giới hạn trên phải lớn hơn giới hạn dưới");
            } else break;
        }
        System.out.println("Danh sách các sản phẩm trong khoảng giá:");
        boolean productFound = false;
        for (int i = 0; i < productCount; i++)
        {
            if (productList[i].getExportPrice() >= lowerBound && productList[i].getExportPrice() <= upperBound)
            {
                System.out.print(productList[i].getProductName() + " - Giá bán: "
                        + productList[i].getExportPrice() + " ");
                productFound = true;
            }
        }
        if (!productFound)
        {
            System.out.print("Không tìm thấy sản phẩm nào trong khoảng giá này");
        }
        System.out.println();
    }

    private void findProductByName()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return;
        }
        System.out.println("Nhập tên sản phẩm muốn tìm");
        String searchName = scanner.nextLine();
        System.out.println("Danh sách sản phẩm có tên giống với mô tả:");
        boolean productFound = false;
        for (int i = 0; i < productCount; i++)
        {
            if (productList[i].getProductName().contains(searchName))
            {
                productFound = true;
                System.out.print(productList[i].getProductName() + " - ");
            }
        }
        if (!productFound)
        {
            System.out.print("Không tìm thấy sản phẩm nào");
        }
        System.out.println();
    }

    private void addProductQuantity()
    {
        int addIndex = searchIndexById();
        if (addIndex == -1)
        {
            System.out.println("Không tìm được sản phẩm");
            return;
        }
        System.out.println("Mời nhập số lượng muốn thêm vào sản phẩm này");
        int quantityToAdd = Integer.parseInt(scanner.nextLine());
        productList[addIndex].setQuantity(productList[addIndex].getQuantity() + quantityToAdd);
        System.out.println("Thêm sản phẩm thành công, số lượng sản phẩm mới là " + productList[addIndex].getQuantity());
    }

    private int searchIndexById()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return -1;
        }
        System.out.println("Nhập mã sản phẩm muốn tìm kiếm");
        String searchId = scanner.nextLine();
        for (int i = 0; i < productCount; i++)
        {
            if (productList[i].getProductId().equals(searchId))
            {
                return i;
            }
        }
        return -1;
    }

    private void sellProduct()
    {
        int sellIndex = searchIndexByName();
        if (sellIndex == -1)
        {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        if (!productList[sellIndex].isStatus())//Status = false => Không bán
        {
            System.out.println("Hiện không bán sản phẩm này");
            return;
        }
        while (true)
        {
            System.out.println("Nhập số lượng sản phẩm muốn bán");
            int quantityToMinus = Integer.parseInt(scanner.nextLine());
            if (quantityToMinus > productList[sellIndex].getQuantity())
            {
                System.out.println("Số lượng sản phẩm trong kho không đủ, vui lòng bán với số lượng nhỏ hơn");
            } else
            {
                productList[sellIndex].setQuantity(productList[sellIndex].getQuantity() - quantityToMinus);
                System.out.println("Đã bán thành công, số lượng sản phẩm mới là "
                        + productList[sellIndex].getQuantity());
                break;
            }
        }
    }

    private int searchIndexByName()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return -1;
        }
        System.out.println("Nhập chính xác tên sản phẩm cần tìm");
        String searchName = scanner.nextLine();
        for (int i = 0; i < productCount; i++)
        {
            if (productList[i].getProductName().equals(searchName))
                return i;
        }
        return -1;
    }

    private void updateProductStatus()
    {
        if (productCount == 0)
        {
            System.out.println(CONSTANT.NO_PRODUCT);
            return;
        }
        int updateIndex = searchIndexById();
        if (updateIndex == -1)
        {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        productList[updateIndex].setStatus(!productList[updateIndex].isStatus());
        System.out.println("Cập nhật thành công, trạng thái mới là :"
                + (productList[updateIndex].isStatus() ? "Đang bán" : "Không bán"));
    }
}
