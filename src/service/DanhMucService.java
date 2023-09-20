package service;

import model.DanhMuc;
import model.Product;

public class DanhMucService implements IService<DanhMuc, String>{
    public static DanhMuc[] danhMuc = new DanhMuc[100];
    static {
        danhMuc[0] = new DanhMuc("T123","T-shirt");
        danhMuc[1] = new DanhMuc("F245","Figure");
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
                System.out.printf("%s : %s   ",dm.getId(),dm.getName());
                i++;
            }
        }
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }



    // kiem tra ton tai danh muc chua qua name
    public int findIndexById(String id) {
        for (int i = 0; i < danhMuc.length; i++) {
            if(danhMuc[i] != null && danhMuc[i].getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }

    // them danh muc
    @Override
    public boolean add(DanhMuc dm) {
        for (int i = 0; i < danhMuc.length; i++) {
            if(danhMuc[i] == null){
                danhMuc[i] = dm;
                return true;
            }
        }
        return false;
    }

    @Override
    public DanhMuc[] getAll() {
        return danhMuc;
    }

    // tim kiem theo id
    public DanhMuc findById(String id){
        for (int i = 0; i < danhMuc.length; i++) {
            if(danhMuc[i]!=null && danhMuc[i].getId().equals(id)){
                return danhMuc[i];
            }
        }
        return null;
    }

    // Cap nhat danh muc
    @Override
    public boolean save(DanhMuc update) {
        for (int i = 0; i < danhMuc.length; i++) {
            if(danhMuc[i] != null && danhMuc[i].getId().equals(update.getId())){
                danhMuc[i].setName(update.getName());
                return true;
            }
        }
        return false;
    }

    // Xoa danh muc
    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        if(index > -1) {
            danhMuc[index] = null;
            return true;
        }
        return false;
    }


}
