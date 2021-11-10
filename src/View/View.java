package View;

import Controller.DanhSachSinhVien;
import Model.SinhVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class View extends JFrame {
    private JPanel pnLeft;
    private JPanel pnRight;

    private JTextField txtMasv;
    private JTextField txtHoTen;
    private JComboBox cbMaLop;
    private JTextField txtDiemLT;
    private JTextField txtDiemTH;
    private JTextField txtDiemTB;
    private JTextField txtKetQua;

    private DanhSachSinhVien listSinhVien;

    private JTable table;
    private DefaultTableModel tableModel;

    public View() {
        listSinhVien = new DanhSachSinhVien();
        setTitle("Chương trình quản lí sinh viên");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildGUI();
        setVisible(true);
        docFile();
    }

    private void docFile() {
        listSinhVien.setListSinhVien(listSinhVien.docFile());
        JOptionPane.showMessageDialog(null, "Đã đọc file sinhvien.txt");
        for (int i = 0; i < listSinhVien.size(); i++) {
            String[] data = {listSinhVien.getSinhVien(i).getMaSv(), listSinhVien.getSinhVien(i).getHoTen(), listSinhVien.getSinhVien(i).getMaLop(), Double.toString(listSinhVien.getSinhVien(i).getDiemLt())
                    , Double.toString(listSinhVien.getSinhVien(i).getDiemTh()), Double.toString(listSinhVien.getSinhVien(i).diemTB()), listSinhVien.getSinhVien(i).ketQua()};
            tableModel.addRow(data);
        }
    }

    private void buildGUI() {
        pnLeft = new JPanel(new GridLayout(0, 1));
        pnLeft.add(new JLabel("THÔNG TIN SINH VIÊN", JLabel.CENTER));

        pnLeft.add(new JLabel("Mã sinh viên:", JLabel.LEFT));
        pnLeft.add(txtMasv = new JTextField(20));
        pnLeft.add(new JLabel("Họ và tên:", JLabel.LEFT));
        pnLeft.add(txtHoTen = new JTextField(20));
        pnLeft.add(new JLabel("Mã lớp:", JLabel.LEFT));
        pnLeft.add(cbMaLop = new JComboBox<String>());
        String[] malop = {"CS", "MT", "CNTT", "XDDD", "KT", "KD"};
        for (int i = 0; i < malop.length; i++) {
            cbMaLop.addItem(malop[i]);
        }
        pnLeft.add(new JLabel("Điểm lí thuyết:", JLabel.LEFT));
        pnLeft.add(txtDiemLT = new JTextField(20));
        pnLeft.add(new JLabel("Điểm thực hành:", JLabel.LEFT));
        pnLeft.add(txtDiemTH = new JTextField(20));
        pnLeft.add(new JLabel("Điểm trung bình:", JLabel.LEFT));
        pnLeft.add(txtDiemTB = new JTextField(20));
        txtDiemTB.setEditable(false);
        pnLeft.add(new JLabel("Kết quả:", JLabel.LEFT));
        pnLeft.add(txtKetQua = new JTextField(20));
        txtKetQua.setEditable(false);

        JButton btnKetqua = new JButton("Kết quả");
        btnKetqua.addActionListener(new BtnKetQuaOnClick());
        JButton btnThem = new JButton("Thêm");
        btnThem.addActionListener(new BtnThemObClick());
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new BtnClearOnClick());
        JButton btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(new BtnLuuOnClick());
        JButton btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(new BtnXoaOnClick());
        JButton btnSua = new JButton("Sửa");
        btnSua.addActionListener(new BtnSuaOnClick());
        JButton btnTim = new JButton("Tìm");
        btnTim.addActionListener(new BtnTimOnClick());

        JPanel pnBtnTop = new JPanel();
        pnBtnTop.add(new JPanel().add(btnKetqua));
        pnBtnTop.add(new JPanel().add(btnThem));
        pnBtnTop.add(new JPanel().add(btnClear));

        JPanel pnBtnBot = new JPanel();
        pnBtnBot.add(new JPanel().add(btnLuu));
        pnBtnBot.add(new JPanel().add(btnXoa));
        pnBtnBot.add(new JPanel().add(btnSua));
        pnBtnBot.add(new JPanel().add(btnTim));

        pnLeft.add(pnBtnTop);
        pnLeft.add(pnBtnBot);

        pnRight = new JPanel(new GridLayout(1, 1));
        String[] header = {"Mã sv", "Họ tên", "Lớp", "Lý thuyết", "Thực hành", "Trung bình", "Kết quả"};
        pnRight.add(new JScrollPane(table = new JTable(tableModel = new DefaultTableModel(header, 0))));
        table.addMouseListener(new MouseListenerSelect());
        add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight));
    }


    public static void main(String[] args) {
        new View();
    }

    private class BtnKetQuaOnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtMasv.getText() == ""
                    || txtHoTen.getText() == ""
                    || isSo(txtDiemLT.getText())
                    || isSo(txtDiemTH.getText())) {
                JOptionPane.showMessageDialog(null,
                        "Không được bỏ trống các trường!!");
            } else {
                SinhVien newSinhVien = new SinhVien(
                        txtMasv.getText(), txtHoTen.getText(),
                        cbMaLop.getActionCommand(),
                        Double.parseDouble(txtDiemLT.getText()),
                        Double.parseDouble(txtDiemTH.getText()));
                txtDiemTB.setText(Double.toString(newSinhVien.diemTB()));
                txtKetQua.setText(newSinhVien.ketQua());
            }
        }

        public boolean isSo(String str) {
            boolean result = true;
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    result = false;
                }
            }
            return result;
        }
    }

    private class BtnThemObClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtMasv.getText() == ""
                    || txtHoTen.getText() == ""
                    || isSo(txtDiemLT.getText())
                    || isSo(txtDiemTH.getText())) {
                JOptionPane.showMessageDialog(null,
                        "Không được bỏ trống các trường!!");
            } else {
                SinhVien newSinhVien = new SinhVien(
                        txtMasv.getText(), txtHoTen.getText(),
                        cbMaLop.getSelectedItem().toString(),
                        Double.parseDouble(txtDiemLT.getText()),
                        Double.parseDouble(txtDiemTH.getText()));
                txtDiemTB.setText(Double.toString(newSinhVien.diemTB()));
                txtKetQua.setText(newSinhVien.ketQua());
                if (!listSinhVien.themSinhVien(newSinhVien)) {
                    JOptionPane.showMessageDialog(null, "Trùng mã số !!!");
                    return;
                } else {
                    String[] data = {newSinhVien.getMaSv(), newSinhVien.getHoTen(), newSinhVien.getMaLop(), Double.toString(newSinhVien.getDiemLt())
                            , Double.toString(newSinhVien.getDiemTh()), Double.toString(newSinhVien.diemTB()), newSinhVien.ketQua()};
                    tableModel.addRow(data);
                    txtMasv.setText("");
                    txtHoTen.setText("");
                    cbMaLop.setSelectedItem("CS");
                    txtDiemLT.setText("");
                    txtDiemTH.setText("");
                    txtDiemTB.setText("");
                    txtKetQua.setText("");
                }
            }
        }

        public boolean isSo(String str) {
            boolean result = true;
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    result = false;
                }
            }
            return result;
        }
    }

    private class BtnClearOnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            txtMasv.setText("");
            txtHoTen.setText("");
            cbMaLop.setSelectedItem("CS");
            txtDiemLT.setText("");
            txtDiemTH.setText("");
            txtDiemTB.setText("");
            txtKetQua.setText("");
        }
    }

    private class MouseListenerSelect implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = table.getSelectedRow();
            SinhVien sv = listSinhVien.getSinhVien(index);

            txtMasv.setText(sv.getMaSv());
            txtHoTen.setText(sv.getHoTen());
            cbMaLop.setSelectedItem(sv.getMaLop());
            txtDiemLT.setText(Double.toString(sv.getDiemLt()));
            txtDiemTH.setText(Double.toString(sv.getDiemTh()));
            txtDiemTB.setText(Double.toString(sv.diemTB()));
            txtKetQua.setText(sv.ketQua());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class BtnTimOnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = JOptionPane.showInputDialog(null, "Nhập mã của sinh viên cần tìm:");
            boolean r = false;
            for (int i = 0; i < listSinhVien.size(); i++) {
                if (listSinhVien.getSinhVien(i).getMaSv().equalsIgnoreCase(id)) {
                    txtMasv.setText(listSinhVien.getSinhVien(i).getMaSv());
                    txtHoTen.setText(listSinhVien.getSinhVien(i).getHoTen());
                    cbMaLop.setSelectedItem(listSinhVien.getSinhVien(i).getMaLop());
                    txtDiemLT.setText(Double.toString(listSinhVien.getSinhVien(i).getDiemLt()));
                    txtDiemTH.setText(Double.toString(listSinhVien.getSinhVien(i).getDiemTh()));
                    txtDiemTB.setText(Double.toString(listSinhVien.getSinhVien(i).diemTB()));
                    txtKetQua.setText(listSinhVien.getSinhVien(i).ketQua());
                    r = true;
                }
            }
            if (r == false) {
                JOptionPane.showMessageDialog(null, "Không tồn tại sinh viên có mã này!!");
            }
        }
    }

    private class BtnLuuOnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (listSinhVien.size() == 0) {
                JOptionPane.showMessageDialog(null, "Danh sách sinh viên trống !");
                return;
            }
            listSinhVien.luuDanhSach();
            JOptionPane.showMessageDialog(null, "Đã lưu danh sách !");
        }
    }

    private class BtnXoaOnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = JOptionPane.showInputDialog(null, "Nhập id sinh viên cần xóa:");
            boolean r = false;
            boolean xoa = false;
            int index = 0;
            for (int i = 0; i < listSinhVien.size(); i++) {
                if (listSinhVien.getSinhVien(i).getMaSv().equalsIgnoreCase(id)) {
                    r = true;
                    index = i;
                }
            }
            if (r == true) {
                int choise = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sinh viên này khỏi danh sách?");
                if (choise == JOptionPane.YES_OPTION) {
                    xoa = listSinhVien.xoaSinhVien(id);
                    tableModel.removeRow(index);
                    File file = new File("sinhvien.txt");
                    file.delete();
                    listSinhVien.luuDanhSach();
                    JOptionPane.showMessageDialog(null, "Đã xóa thành công !");
                }
            }
            if (xoa == false) {
                JOptionPane.showMessageDialog(null, "Không xóa thành công !!");
            }
        }
    }

    private class BtnSuaOnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = JOptionPane.showInputDialog(null, "Nhập id sinh viên cần sửa:");
            String hoten = JOptionPane.showInputDialog(null, "Nhập tên sinh viên cần sửa:");
            String maLop = JOptionPane.showInputDialog(null, "Nhập mã lớp sinh viên cần sửa:");
            double diemLt = Double.parseDouble(JOptionPane.showInputDialog(null, "Nhập điểm lí thuyết sinh viên cần sửa:"));
            double diemTH = Double.parseDouble(JOptionPane.showInputDialog(null, "Nhập điểm thực hành sinh viên cần sửa:"));
            if (listSinhVien.updateSinhVien(id, hoten, maLop, diemLt, diemTH) == true) {
                JOptionPane.showMessageDialog(null, "Update thành công !");
                for (int i = 0; i < listSinhVien.size(); i++) {
                    if (listSinhVien.getSinhVien(i).getMaSv().equalsIgnoreCase(id)) {
                        tableModel.removeRow(i);
                        SinhVien nsv = new SinhVien(id, hoten, maLop, diemLt, diemTH);
                        String[] datas = {nsv.getMaSv(), nsv.getHoTen(), nsv.getMaLop(),
                                Double.toString(nsv.getDiemLt()), Double.toString(nsv.getDiemTh()),
                                Double.toString(nsv.diemTB()), nsv.ketQua()};
                        tableModel.insertRow(i, datas);
                    }
                }
                File file = new File("sinhvien.txt");
                file.delete();
                listSinhVien.luuDanhSach();
            } else {
                JOptionPane.showMessageDialog(null, "Không update thành công!");
            }
        }
    }
}
