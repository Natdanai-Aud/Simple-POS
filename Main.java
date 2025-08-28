public class Main {
    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();

        // โหลดสินค้า
        repo.loadFromFile();

        // เพิ่มสินค้าใหม่
        try {
            repo.AddProduct(new Product("D001", "Milk Tea", 40));
            repo.AddProduct(new Product("D002", "Coffee", 50));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

        // แสดงทั้งหมด
        for(Product p : repo.getAllProducts()){
            System.out.println(p.getProductID() + " - " + p.getProductName() + " : " + p.getPrice());
        }

        // บันทึกไฟล์
        repo.saveToFile();
    }
}
