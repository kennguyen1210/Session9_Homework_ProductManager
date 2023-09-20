package service;

import model.DanhMuc;
import model.Product;
import util.InputMethods;

public class ProductService implements IService<Product, String>{
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

    @Override
    public Product[] getAll() {
        return products;
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

    //update san pham
    @Override
    public boolean save(Product updatePro) {
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getId().equalsIgnoreCase(updatePro.getId())) {
                products[i] = updatePro;
                return true;
            }
        }
        return false;
    }

    // xoa san pham theo id
    @Override
    public boolean delete(String id) {
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null && products[i].getId().equalsIgnoreCase(id)){
                products[i] = null;
                return true;
            }
        }
        return false;
    }


    // them moi san pham
    @Override
    public boolean add(Product addProduct) {
        for (int i = 0; i < products.length; i++) {
            if(products[i] == null){
                products[i] = addProduct;
                return true;
            }
        }
        return false;
    }

    // cap nhat thong tin san pham theo danh muc
    public void changeDanhMuc(DanhMuc update){
        for (Product pro: products
             ) {
            if(pro != null && pro.getDanhMuc().getId().equalsIgnoreCase(update.getId())){
                pro.setDanhMuc(update);
            }
        }

    }

    // kiem tra xem danh muc co san pham hay khong
    public boolean haveProduct(String id){
        boolean check = true;
        for (Product pro: products
        ) {
            if(pro != null && pro.getDanhMuc().getId().equalsIgnoreCase(id)){
                check = false;
            }
        }
        return !check;
    }



}
