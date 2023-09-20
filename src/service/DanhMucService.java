package service;

import model.DanhMuc;
import model.Product;

public class DanhMucService {
    public static DanhMuc[] danhMuc = new DanhMuc[100];
    static {
        danhMuc[0] = new DanhMuc("T-shirt");
        danhMuc[1] = new DanhMuc("Figure");
    }

    // in danh sach danh muc
    public void displayAll() {
        System.out.println("=========== Danh sach danh muc =======");
        int i = 1;
        for (DanhMuc dm : danhMuc
             ) {
            if(dm != null) {
                if(i % 5 == 0) {
                    System.out.println();
                }
                System.out.printf("%s    ",dm.getName());
                i++;
            }
        }
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    // them danh muc
    public boolean add(DanhMuc dm) {
            for (int i = 0; i < danhMuc.length; i++) {
                if(danhMuc[i] == null){
                    danhMuc[i] = dm;
                    return true;
                }
            }
            return false;

    }

    // Cap nhat danh muc
    public DanhMuc update(String prev, String next) {
        int index = findByName(prev);
        if(index > -1){
            danhMuc[index].setName(next);
            return danhMuc[index];
        }
        return null;
    }

    // kiem tra ton tai danh muc chua qua name
    public int findByName(String str) {
        for (int i = 0; i < danhMuc.length; i++) {
            if(danhMuc[i] != null && danhMuc[i].getName().equalsIgnoreCase(str)){
                return i;
            }
        }
        return -1;
    }

    // Xoa danh muc
    public boolean daleteDanhMuc(String name) {
        int index = findByName(name);
        if(index > -1) {
            danhMuc[index] = null;
            return true;
        }
        return false;
    }


}
