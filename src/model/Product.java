package model;

public class Product {
    // thuoc tinh
    private String id;
    private DanhMuc danhMuc;
    private String tenSanPham;

    private double gia;

    private int soLuong;

    // constructor khong tham so

    public Product() {
    }
    // constructor co tham so

    public Product(String id, DanhMuc danhMuc, String tenSanPham, double gia, int soLuong) {
        this.id = id;
        this.danhMuc = danhMuc;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    // getter setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // in ra san pham
    public void display() {
        System.out.println("================");
        System.out.println(" ID : " + this.id);
        System.out.println(" Ten san pham : " + this.tenSanPham);
        System.out.println(" Danh muc : "+  this.danhMuc.getName());
        System.out.println(" Gia : " + this.gia + "$");
        System.out.println(" So luong : " + this.soLuong);
    }

}
