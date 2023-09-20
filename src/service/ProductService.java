package service;

import model.DanhMuc;
import model.Product;
import util.InputMethods;

public class ProductService {
    public static Product[] products = new Product[100];

    // hien thi san pham
    public boolean displayAll() {
        boolean check = true;
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null) {
                products[i].display();
                check = false;
            }
        }
        return !check;
    }

    // them moi san pham
    public boolean addPro(Product addProduct){
        for (int i = 0; i < products.length; i++) {
            if(products[i] == null){
                products[i] = addProduct;
                return true;
            }
        }
        return false;
    }
    // cap nhat san pham thong qua id
    public boolean editProduct(Product updatePro) {
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getId().equalsIgnoreCase(updatePro.getId())) {
                products[i] = updatePro;
                return true;
            }
        }
        return false;
    }
    // tim kiem san pham theo ten
    public boolean findByName(String name) {
        boolean check = true;
        for (Product pro: products
             ) {
            if(pro != null && pro.getTenSanPham().contains(name)){
                pro.display();
                check = false;
            }
        }
        return !check;
    }

    // tim kiem san pham theo id
    public Product findById(String id){
        for (Product pro: products
        ) {
            if(pro != null && pro.getId().equals(id)){
                return pro;
            }
        }
        return null;
    }
    // cap nhat thong tin san pham theo danh muc
    public void changeDanhMuc(DanhMuc prev, DanhMuc next){
        for (Product pro: products
             ) {
            if(pro != null && pro.getDanhMuc().getName().equalsIgnoreCase(prev.getName())){
                pro.setDanhMuc(next);
            }
        }

    }

    // kiem tra xem danh muc co san pham hay khong
    public boolean haveProduct(String name){
        boolean check = true;
        for (Product pro: products
        ) {
            if(pro != null && pro.getDanhMuc().getName().equalsIgnoreCase(name)){
                check = false;
            }
        }
        return !check;
    }

    // xoa san pham theo id
    public boolean deleteProduct(String id){
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getId().equalsIgnoreCase(id)){
                products[i] = null;
                return true;
            }
        }
        return false;
    }
}
