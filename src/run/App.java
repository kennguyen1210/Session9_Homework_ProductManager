package run;

import model.DanhMuc;
import model.Product;
import service.DanhMucService;
import service.ProductService;
import util.InputMethods;

public class App {
    public static DanhMucService dmService = new DanhMucService();
    public static ProductService proService = new ProductService();

    public static void main(String[] args) {
        // menu chinh
        while (true) {
            System.out.println("=========== *** MENU *** ===========");
            System.out.println("1. Quan li danh muc.");
            System.out.println("2. Quan li san pham.");
            System.out.println("3. Thoat.");
            System.out.println("Nhap lua chon cua ban :");
            byte choise = InputMethods.getByte();
            switch (choise) {
                case 1:
                    danhMuc();
                    break;
                case 2:
                    sanPham();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lua chon chua hop ly");
            }
        }
    }

    // quan ly danh muc
    public static void danhMuc() {
        // menu danh muc
        boolean check = true;
        while (check) {

            System.out.println(">>>>>>>> Quan Li Danh Muc <<<<<<<");
            System.out.println("1. Hien thi danh sach danh muc");
            System.out.println("2. Them moi danh muc ");
            System.out.println("3. Cap nhat danh muc ");
            System.out.println("4. Xoa danh muc ");
            System.out.println("5. Quay lai");
            System.out.println("Nhap lua chon cua ban :");
            byte choise = InputMethods.getByte();
            switch (choise) {
                // hien thi danh sach danh muc
                case 1:
                    dmService.displayAll();
                    break;
                // Them moi danh muc
                case 2:
                    while (true) {
                        DanhMuc newDm = new DanhMuc();
                        System.out.println("Nhap danh muc muon them");
                        String str = InputMethods.getString();
                        if (dmService.findByName(str) > -1) {
                            System.err.println("Danh muc nay da ton tai, vui long nhap danh muc khac");
                        } else {
                            newDm.setName(str);
                            if (dmService.add(newDm)) {
                                System.out.println(" Them danh muc thanh cong!");
                            } else {
                                System.err.println(" Them danh muc that bai!");
                            }
                        }
                        System.out.println("Ban muon tiep tuc them danh muc khong ? y/n");
                        char out = InputMethods.getChar();
                        if (out == 'n') {
                            break;
                        }
                    }
                    break;
                // cap nhat danh muc
                case 3:
                    dmService.displayAll();
                    while (true) {
                        System.out.println("Nhap danh muc muon cap nhat");
                        String prev = InputMethods.getString();
                        if (dmService.findByName(prev) > -1) {
                            System.out.println("Danh muc cap nhat se la ? ");
                            String next = InputMethods.getString();
                            DanhMuc preDm = new DanhMuc(prev);
                            DanhMuc newDm = dmService.update(prev, next);
                            if (newDm != null) {
                                System.out.println("Cap nhat danh muc thanh cong");
                                proService.changeDanhMuc(preDm, newDm);
                                dmService.displayAll();
                            }
                        } else {
                            System.err.println("Danh muc khong ton tai!");
                        }
                        System.out.println("Ban muon tiep tuc cap nhat danh muc khong ? y/n");
                        char out = InputMethods.getChar();
                        if (out == 'n') {
                            break;
                        }

                    }
                    break;
                // xoa danh muc
                case 4:
                    while (true) {
                        System.out.println(" Nhap danh muc muon xoa :");
                        String deleteDanhMuc = InputMethods.getString();
                        if (proService.haveProduct(deleteDanhMuc)) {
                            System.err.println("Danh muc co san pham nen khong the xoa! Hay nhap danh muc khac!");
                        } else {
                            while (true) {
                                System.out.println("Ban co chac chan muon xoa khong ? y/n");
                                char confirm = InputMethods.getChar();
                                if (confirm == 'y') {
                                    if (dmService.daleteDanhMuc(deleteDanhMuc)) {
                                        System.out.println("Xoa thanh cong");
                                        break;
                                    }
                                }
                                if (confirm == 'n') {
                                    break;
                                }
                            }
                        }

                        System.out.println("Ban muon tiep tuc xoa danh muc khong ? y/n");
                        char out = InputMethods.getChar();
                        if (out == 'n') {
                            break;
                        }

                    }
                    break;
                case 5:
                    check = false;
                    break;
                default:
                    System.err.println("Lua chon chua hop le! Vui long chon lai!");
            }
        }
    }

    // quan ly san pham
    public static void sanPham() {
        boolean check = true;
        while (check) {
            // Menu quan ly san pham
            System.out.println(">>>>>>>> Quan Li San Pham <<<<<<<");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them moi san pham ");
            System.out.println("3. Cap nhat san pham ");
            System.out.println("4. Xoa san pham ");
            System.out.println("5. Tim kiem san pham theo ten ");
            System.out.println("6. Quay lai");
            System.out.println("Nhap lua chon cua ban :");
            byte choise = InputMethods.getByte();
            switch (choise) {
                // hien thi danh sach san pham
                case 1:
                    if (!proService.displayAll()) {
                        System.out.println("Hien tai chua co san pham nao! Vui long them san pham");
                    }
                    break;

                // them moi san pham
                case 2:
                    while (true) {
                        Product newProduct = new Product();
                        DanhMuc addDanhMuc = new DanhMuc();
                        dmService.displayAll();
                        while (true) {
                            System.out.println("Nhap danh muc cho san pham :");
                            String danhMuc = InputMethods.getString();
                            if (dmService.findByName(danhMuc) > -1) {
                                addDanhMuc.setName(danhMuc);
                                break;
                            } else {
                                System.err.println("Danh muc khong ton tai!");
                            }
                        }
                        String id;
                        while (true) {
                            System.out.print("\nNhap Id san pham : ");
                            id = InputMethods.getString();
                            if (proService.findById(id) != null) {
                                System.err.println("Id san pham da ton tai, vui long nhap id khac!");
                            } else {
                                break;
                            }
                        }

                        System.out.print("\nNhap ten san pham : ");
                        String name = InputMethods.getString();

                        System.out.print("\nNhap gia san pham ($): ");
                        double gia = InputMethods.getDouble();

                        System.out.print("\nNhap so luong san pham : ");
                        int soLuong = InputMethods.getInteger();
                        System.out.println();
                        Product newPro = new Product(id, addDanhMuc, name, gia, soLuong);
                        if (proService.addPro(newPro)) {
                            System.out.println("====> *** Them moi san pham thanh cong!");
                        } else {
                            System.err.println("Them moi that bai");
                        }

                        System.out.println("Ban muon tiep tuc them moi san pham khong ? y/n");
                        char out = InputMethods.getChar();
                        if (out == 'n') {
                            break;
                        }
                    }
                    break;

                // update san pham
                case 3:
                    Product updateProduct;
                    while (true) {
                        while (true) {
                            System.out.println("Nhap id san pham muon cap nhat : ");
                            String editProId = InputMethods.getString();
                            updateProduct = proService.findById(editProId);
                            if (updateProduct != null) {
                                break;
                            } else {
                                System.err.println("Id san pham khong ton tai! Vui long nhap id khac!");
                            }
                        }
                        System.out.println("ten hien tai : " + updateProduct.getTenSanPham());
                        String newName = InputMethods.getString();
                        updateProduct.setTenSanPham(newName);
                        System.out.println("danh muc hien tai la : " + updateProduct.getDanhMuc().getName() + ". Nhap danh muc sua doi :");
                        String dm = InputMethods.getString();
                        DanhMuc newDm = new DanhMuc(dm);
                        updateProduct.setDanhMuc(newDm);
                        System.out.println("gia hien tai : " + updateProduct.getGia());
                        double newGia = InputMethods.getDouble();
                        updateProduct.setGia(newGia);
                        System.out.println("So luong hien tai : " + updateProduct.getSoLuong());
                        int newSoLuong = InputMethods.getInteger();
                        updateProduct.setSoLuong(newSoLuong);
                        if (proService.editProduct(updateProduct)) {
                            System.out.println("========> Cap nhat thanh cong!");
                        } else {
                            System.err.println("Cap nhat that bai");
                        }
                        System.out.println("Ban co muon tiep tuc cap nhat san pham khong ? y/n");
                        char exit = InputMethods.getChar();
                        if (exit == 'n') {
                            break;
                        }
                    }
                    break;
                // xoa san pham
                case 4:
                    String deleteId;
                    while (true) {
                        System.out.print("\nNhap Id san pham muon xoa :");
                        deleteId = InputMethods.getString();
                        if (proService.findById(deleteId) == null) {
                            System.err.println("Id san pham khong ton tai, vui long nhap id khac!");
                        } else {
                            System.out.println("Ban co chac chan muon xoa khong ? y/n");
                            char confirm = InputMethods.getChar();
                            if (confirm == 'y') {
                                if (proService.deleteProduct(deleteId)) {
                                    System.out.println("====> Xoa thanh cong!");
                                } else {
                                    System.err.println("Xoa khong thanh cong!");
                                }
                            }
                        }
                        System.out.println("Ban co muon tiep tuc cap nhat san pham khong ? y/n");
                        char exit = InputMethods.getChar();
                        if (exit == 'n') {
                            break;
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.print("Nhap ten san pham muon tim kiem : ");
                        String searchName = InputMethods.getString();
                        if(!proService.findByName(searchName)){
                            System.err.println("Khong tim thay san pham phu hop");
                        }
                        System.out.println();
                        System.out.println("Ban co muon tiep tuc tim san pham khong ? y/n");
                        char exit = InputMethods.getChar();
                        if (exit == 'n') {
                            break;
                        }
                    }
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    System.err.println("Lua chon chua lop ly! vui long chon lai!");
            }
        }

    }
}
