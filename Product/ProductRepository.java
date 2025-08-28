import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductRepository {
    private ArrayList <Product> products = new ArrayList<>();

    private void checkRep(){
        if(products == null){
            throw new RuntimeException("Error : Product is null");
        }
        for(int i = 0; i < products.size();i++){
            for(int j = i+1;j < products.size();j++){
                if(products.get(i).equals(products.get(j))){
                    throw new RuntimeException("Error : The product is already in product.");
                }
            }
        }
    }

    public ProductRepository(){
        checkRep();
    }

    public void AddProduct(Product product){
        if(product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }

    public void RemoveProduct(String ProductID){
        for (Product p : products) {
            products.remove(p.getProductID().equals(ProductID));
        }
        checkRep();
    }

      public ArrayList<Product> getAllProducts(){
        return products;
    }

    // บันทึกสินค้าเป็น CSV
    public void saveToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("./File & Image/ProductCatalog.csv"))) {
            // เขียน header
            writer.println("ID,Name,Price");
            for(Product p : products){
                writer.println(p.getProductID() + "," + p.getProductName() + "," + p.getPrice());
            }
            System.out.println("Saved " + products.size());
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // โหลดสินค้า CSV
    public void loadFromFile(){
        products.clear(); // ล้าง list ก่อน
        try (BufferedReader br = new BufferedReader(new FileReader("./File & Image/ProductCatalog.csv"))) {
            String line = br.readLine(); // ข้าม header
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 4){
                    String id = data[0];
                    String name = data[1];
                    String category = data[2];
                    double price = Double.parseDouble(data[3]);
                    products.add(new Product(id, name, price));
                }
            }
            System.out.println("Loaded " + products.size());
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

}