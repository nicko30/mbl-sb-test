package demo.data;

public class Product {
    private static Double totalProductPrice;

    public static Double getTotalProductPrice() {
        return totalProductPrice;
    }

    public static void setTotalProductPrice(Double totalProductPrice) {
        Product.totalProductPrice = totalProductPrice;
    }
}
