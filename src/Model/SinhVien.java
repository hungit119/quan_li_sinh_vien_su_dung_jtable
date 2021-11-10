package Model;

public class SinhVien {
    private String maSv;
    private String hoTen;
    private String maLop;
    private double diemLt;
    private double diemTh;

    public SinhVien(String maSv, String hoTen, String maLop, double diemLt, double diemTh) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.maLop = maLop;
        this.diemLt = diemLt;
        this.diemTh = diemTh;
    }

    public SinhVien(String maSv) {
        this.maSv = maSv;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public double getDiemLt() {
        return diemLt;
    }

    public void setDiemLt(double diemLt) {
        this.diemLt = diemLt;
    }

    public double getDiemTh() {
        return diemTh;
    }

    public void setDiemTh(double diemTh) {
        this.diemTh = diemTh;
    }

    public boolean equalID(SinhVien sv) {
        return this.getMaSv().equalsIgnoreCase(sv.getMaSv());
    }

    public double diemTB() {
        return (this.diemLt + this.diemTh) / 2;
    }

    public String ketQua() {
        if (this.diemTB() >= 5) {
            return "Đậu";
        } else {
            return "Rớt";
        }
    }

    public String dataFile() {
        return maSv + ";" + hoTen + ";" + maLop + ";" + diemLt + ";" + diemTh;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSv='" + maSv + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", maLop='" + maLop + '\'' +
                ", diemLt=" + diemLt +
                ", diemTh=" + diemTh +
                '}';
    }
}
