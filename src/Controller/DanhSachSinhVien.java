package Controller;

import Model.SinhVien;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachSinhVien {
    private ArrayList<SinhVien> listSinhVien;

    public DanhSachSinhVien() {
        this.listSinhVien = new ArrayList<SinhVien>();
    }

    public ArrayList<SinhVien> getListSinhVien() {
        return listSinhVien;
    }

    public void setListSinhVien(ArrayList<SinhVien> listSinhVien) {
        this.listSinhVien = listSinhVien;
    }

    public boolean themSinhVien(SinhVien sv) {
        for (int i = 0; i < this.listSinhVien.size(); i++) {
            if (this.listSinhVien.get(i).equalID(sv)) {
                System.out.println("Trùng mã sinh viên");
                return false;
            }
        }
        this.listSinhVien.add(sv);
        return true;
    }

    public boolean xoaSinhVien(String id) {
        for (int i = 0; i < listSinhVien.size(); i++) {
            if (listSinhVien.get(i).getMaSv().equalsIgnoreCase(id)) {
                listSinhVien.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateSinhVien(String id, String hoten, String maLop, double diemLT, double diemTH) {
        for (int i = 0; i < listSinhVien.size(); i++) {
            if (listSinhVien.get(i).getMaSv().equalsIgnoreCase(id)) {
                listSinhVien.get(i).setHoTen(hoten);
                listSinhVien.get(i).setMaLop(maLop);
                listSinhVien.get(i).setDiemLt(diemLT);
                listSinhVien.get(i).setDiemTh(diemTH);
                return true;
            }
        }
        return false;
    }

    public SinhVien getSinhVien(int i) {
        SinhVien sv = null;
        if (i < 0 || i > this.listSinhVien.size()) {
            return null;
        } else {
            sv = this.listSinhVien.get(i);
        }
        return sv;
    }

    public int size() {
        return this.listSinhVien.size();
    }

    public void luuDanhSach() {
        try {
            PrintWriter print = new PrintWriter("sinhvien.txt");
            for (SinhVien sv :
                    listSinhVien) {
                print.println(sv.dataFile());
            }
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SinhVien> docFile() {
        ArrayList<SinhVien> svs = new ArrayList<SinhVien>();
        try {
            Scanner scanner = new Scanner(Paths.get("sinhvien.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                SinhVien sv = createSinhVienFromLine(line);
                svs.add(sv);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return svs;
    }

    private SinhVien createSinhVienFromLine(String line) {
        String[] datas = line.split("\\;");
        SinhVien sv = new SinhVien(datas[0], datas[1], datas[2], Double.parseDouble(datas[3]), Double.parseDouble(datas[4]));
        return sv;
    }
}
