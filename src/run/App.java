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
                    System.out.println("An nut bat ky de quay lai");
                    InputMethods.pressAnyKey();
                    break;
                // Them moi danh muc
                case 2:
                    while (true) {
                        DanhMuc newDm = new DanhMuc();
                        System.out.println("Nhap id danh muc muon them");
                        String newId = InputMethods.getString();
                        DanhMuc dm = dmService.findById(newId);
                        if(dm != null) {
                            System.err.println("Danh muc nay da ton tai, vui long nhap danh muc khac");
                        } else {
                            newDm.setId(newId);
                            System.out.println("Nhap ten danh muc : ");
                            String str = InputMethods.getString();
                            newDm.setName(str);
                            if (dmService.add(newDm)) {
                                System.out.println(" Them danh muc thanh cong!");
                            } else {
                                System.err.println(" Them danh muc that bai!");
                            }
                            System.out.println("Ban muon tiep tuc them danh muc khong ? y/n");
                            char out = InputMethods.getChar();
                            if (out == 'n') {
                                break;
                            }
                        }
                    }
                    break;

                // cap nhat danh muc
                case 3:
                    dmService.displayAll();
                    while (true) {
                        System.out.println("Nhap id danh muc muon cap nhat");
                        String updateId = InputMethods.getString();
                        DanhMuc updateDm = dmService.findById(updateId);
                        if (updateDm != null) {
                            System.out.println("Danh muc hien tai la : " + updateDm.getName() + ".Cap nhat danh muc se la ? ");
                            String update = InputMethods.getString();
                            updateDm.setName(update);
                            if (dmService.save(updateDm)) {
                                System.out.println("Cap nhat danh muc thanh cong");
                                proService.changeDanhMuc(updateDm);
                                dmService.displayAll();
                            } else {
                                System.err.println("Cap nhat khong thanh cong!");
                            }
                            System.out.println("Ban muon tiep tuc cap nhat danh muc khong ? y/n");
                            char out = InputMethods.getChar();
                            if (out == 'n') {
                                break;
                            }
                        } else {
                            System.err.println("Danh muc khong ton tai!");
                        }


                    }
                    break;
                // xoa danh muc
                case 4:
                    dmService.displayAll();
                    while (true) {
                        System.out.println("Nhap id danh muc muon xoa :");
                        String deleteId = InputMethods.getString();
                        if (proService.haveProduct(deleteId)) {
                            System.err.println("Danh muc co san pham nen khong the xoa! Hay nhap danh muc khac!");
                        } else {
                            while (true) {
                                System.out.println("Ban co chac chan muon xoa khong ? y/n");
                                char confirm = InputMethods.getChar();
                                if (confirm == 'y') {
                                    if (dmService.delete(deleteId)) {
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
                    System.out.println("Nhan nut bat ky den thoat");
                    InputMethods.pressAnyKey();
                    break;

                // them moi san pham
                case 2:
                    while (true) {
                        Product newProduct = new Product();
                        DanhMuc addDanhMuc = new DanhMuc();
                        dmService.displayAll();
                        while (true) {
                            System.out.print("Nhap danh muc cho san pham theo Id:");
                            String idDanhMuc = InputMethods.getString();
                            addDanhMuc = dmService.findById(idDanhMuc);
                            if (addDanhMuc != null) {
                                break;
                            } else {
                                System.err.println("Danh muc khong ton tai!");
                            }
                        }
                        String id;
                        while (true) {
                            System.out.print("Nhap Id san pham : ");
                            id = InputMethods.getString();
                            if (proService.findById(id) != null) {
                                System.err.println("Id san pham da ton tai, vui long nhap id khac!");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Nhap ten san pham : ");
                        String name = InputMethods.getString();

                        System.out.print("Nhap gia san pham ($): ");
                        double gia = InputMethods.getDouble();

                        System.out.print("Nhap so luong san pham : ");
                        int soLuong = InputMethods.getInteger();
                        System.out.println();
                        Product newPro = new Product(id, addDanhMuc, name, gia, soLuong);
                        if (proService.add(newPro)) {
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
                        System.out.println("danh muc hien tai :" + "id  "
                                + updateProduct.getDanhMuc().getId() + " | "
                                + updateProduct.getDanhMuc().getName() );
                        System.out.println("Danh muc sua doi id la ? ");
                        String changeId = InputMethods.getString();
                        DanhMuc dm = dmService.findById(changeId);
                        updateProduct.setDanhMuc(dm);
                        System.out.println("gia hien tai : " + updateProduct.getGia() + "$");
                        double newGia = InputMethods.getDouble();
                        updateProduct.setGia(newGia);
                        System.out.println("So luong hien tai : " + updateProduct.getSoLuong());
                        int newSoLuong = InputMethods.getInteger();
                        updateProduct.setSoLuong(newSoLuong);
                        if (proService.save(updateProduct)) {
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
                        System.out.print("Nhap Id san pham muon xoa :");
                        deleteId = InputMethods.getString();
                        if (proService.findById(deleteId) == null) {
                            System.err.println("Id san pham khong ton tai, vui long nhap id khac!");
                        } else {
                            System.out.println("Ban co chac chan muon xoa khong ? y/n");
                            char confirm = InputMethods.getChar();
                            if (confirm == 'y') {
                                if (proService.delete(deleteId)) {
                                    System.out.println("====> Xoa thanh cong!");
                                } else {
                                    System.err.println("Xoa khong thanh cong!");
                                }
                            }
                        }
                        System.out.println("Ban co muon tiep tuc xoa san pham khong ? y/n");
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
                        if (!proService.findByName(searchName)) {
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
