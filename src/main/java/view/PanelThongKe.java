package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.QuenMatKhauService;
import service.ThongKeService;
import service.impl.QuenMatKhauServiceImpl;
import service.impl.ThongKeServiceImpl;

/**
 *
 * @author Đức Hiệu
 */
public class PanelThongKe extends javax.swing.JPanel {

    /**
     * Creates new form PanelThongKe
     */
    private DefaultTableModel dtmSP = new DefaultTableModel();
    private DefaultTableModel dtmDT = new DefaultTableModel();
    private DefaultComboBoxModel dcbmThangNam;
    private DefaultComboBoxModel dcbmThangNamDoanhThu;
    private DefaultComboBoxModel dcbmThangBieuDo;
    private DefaultComboBoxModel dcbmNamBieuDo;
    private ThongKeService serviceThongKe = new ThongKeServiceImpl();
    private QuenMatKhauService serviceQuenMK = new QuenMatKhauServiceImpl();
    private List<ThongKeSanPhamRespone> listSP = new ArrayList<>();
    private List<ThongKeDoanhThuRespone> listDT = new ArrayList<>();
    private Date date = new Date();
    private DateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    private int nam = 2022;
    private int thaang;

    public PanelThongKe() throws ParseException {
        initComponents();
        String hearDT[] = {"Mã HĐ", "Mã NV", "Tên NV", "Hình thức", "Tiền mặt", "Tiền CK", "Tổng tiền"};
        dtmDT.setColumnIdentifiers(hearDT);
        tbDoanhThu.setModel(dtmDT);
        String hearSP[] = {"Mã SP", "Tên SP", "Giá", "Số lượng đã bán", "Doanh thu"};
        dtmSP.setColumnIdentifiers(hearSP);
        tbSanPham.setModel(dtmSP);

        nam = Integer.valueOf(dateFor.format(date).substring(6, 10));
        thaang = Integer.valueOf(dateFor.format(date).substring(3, 5));
        showDataSP(dateFor.format(date));
        showDataDoanhThu(dateFor.format(date));
        start();
    }

    private void start() {
        lbTongSP.setText(String.valueOf(serviceThongKe.spKinhDoanhAll()));
        lbSanPhamDangKinhDoanh.setText(String.valueOf(serviceThongKe.spKinhDoanh(0)));
        lbSanPhamNgungKinhDoanh.setText(String.valueOf(serviceThongKe.spKinhDoanh(1)));
        cbbLoaiThoiGian.setSelectedIndex(0);
        lbChonNgayBatDauSP.setText("Chọn ngày :");
        txtStartTKSP.setVisible(true);
        txtEndTKSP.setVisible(false);
        cbbThangNam.setVisible(false);
        lbChonNgayKetThucSP.setVisible(false);

        cbbLoaiDoanhThu.setSelectedIndex(0);
        lbChonNgayBDDT.setText("Chọn ngày :");
        lbChonNgayBDDT.setVisible(true);
        cbbLoaiDoanhThu.setSelectedIndex(0);
        lbChonNgayKTDT.setVisible(false);
        cbbThangDoanhThu.setVisible(false);
        txtEndTKDT.setVisible(false);

        loadThangBieuDo();

    }

    public void exPortHoaDon() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Doanh thu");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            if (cbbLoaiDoanhThu.getSelectedIndex() == 0) {
                listDT = serviceThongKe.getAllDoanhThu(dateFor.parse(dateFor.format(getDate(txtStartTKDT.getDateStringOrEmptyString()))));
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu hóa đơn : " + dateFor.parse(dateFor.format(getDate(txtStartTKDT.getDateStringOrEmptyString()))));
            } else if (cbbLoaiDoanhThu.getSelectedIndex() == 1) {
                listDT = serviceThongKe.getAllDoanhThuKhoangNgay(dateFor.parse(dateFor.format(getDate(txtStartTKDT.getDateStringOrEmptyString()))),
                        dateFor.parse(dateFor.format(getDate(txtEndTKDT.getDateStringOrEmptyString()))));
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu hóa đơn từ : " + dateFor.parse(dateFor.format(getDate(txtStartTKDT.getDateStringOrEmptyString()))) + " tới "
                        + dateFor.parse(dateFor.format(getDate(txtEndTKDT.getDateStringOrEmptyString()))));
            } else if (cbbLoaiDoanhThu.getSelectedIndex() == 2) {
                listDT = serviceThongKe.getAllDoanhThuMonth((int) cbbThangDoanhThu.getSelectedItem(), nam);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu hóa đơn tháng : " + (int) cbbThangDoanhThu.getSelectedItem() + "/" + nam);
            } else {
                listDT = serviceThongKe.getAllDoanhThuYear((int) cbbThangDoanhThu.getSelectedItem());
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu hóa đơn năm : " + (int) cbbThangDoanhThu.getSelectedItem());
            }
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã HD");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã NV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tên NV");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Hình thức");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tiền mặt");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tiền CK");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Tổng tiền");
            for (int i = 0; i < listDT.size(); i++) {
                ThongKeDoanhThuRespone tk = listDT.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(tk.getMaHD());
                row.createCell(2).setCellValue(tk.getMaNV());
                row.createCell(3).setCellValue(tk.getTenNV());
                row.createCell(4).setCellValue(tk.getHinhThuc() == 0 ? "Tiền mặt" : tk.getHinhThuc() == 1 ? "Chuyển khoản" : "Cả tiền mặt và chuyển khoản");
                row.createCell(5).setCellValue(tk.dinhDangTienVN(tk.getTienKhachTra()));
                row.createCell(6).setCellValue(tk.dinhDangTienVN(tk.getTienCK()));
                row.createCell(7).setCellValue(tk.dinhDangTienVN(tk.getTongTien()));
            }
            FileOutputStream out = new FileOutputStream(new File("D:/tkdt.xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Export hóa đơn thành công(Ổ D)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Export hóa đơn thất bại");
        }
    }

    public void exPortSanPham() {
        try {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Sản phẩm");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            if (cbbLoaiThoiGian.getSelectedIndex() == 0) {
                listSP = serviceThongKe.getAllSanPham(dateFor.parse(dateFor.format(getDate(txtStartTKSP.getDateStringOrEmptyString()))));
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu sản phẩm : " + dateFor.parse(dateFor.format(getDate(txtStartTKSP.getDateStringOrEmptyString()))));
            } else if (cbbLoaiThoiGian.getSelectedIndex() == 1) {
                listSP = serviceThongKe.getAllSanPhamKhoangNgay(dateFor.parse(dateFor.format(getDate(txtStartTKSP.getDateStringOrEmptyString()))),
                        dateFor.parse(dateFor.format(getDate(txtEndTKSP.getDateStringOrEmptyString()))));
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu sản phẩm : " + dateFor.parse(dateFor.format(getDate(txtStartTKSP.getDateStringOrEmptyString()))) + " tới "
                        + dateFor.parse(dateFor.format(getDate(txtEndTKSP.getDateStringOrEmptyString()))));
            } else if (cbbLoaiThoiGian.getSelectedIndex() == 2) {
                listSP = serviceThongKe.getAllSanPhamMonth((int) cbbThangNam.getSelectedItem(), nam);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu sản phẩm tháng : " + (int) cbbThangNam.getSelectedItem() + "/" + nam);
            } else {
                listSP = serviceThongKe.getAllSanPhamYear((int) cbbThangNam.getSelectedItem());
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Danh sách doanh thu sản phẩm năm : " + (int) cbbThangNam.getSelectedItem());
            }

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã SP");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên SP");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giá");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số lượng đã bán");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Doanh thu");
            for (int i = 0; i < listSP.size(); i++) {
                ThongKeSanPhamRespone tk = listSP.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(tk.getMaSP());
                row.createCell(2).setCellValue(tk.getTenSP());
                row.createCell(3).setCellValue(tk.getGia().doubleValue());
                row.createCell(4).setCellValue(tk.getCount());
                row.createCell(5).setCellValue(tk.getGia().doubleValue() * tk.getCount());
            }
            FileOutputStream out = new FileOutputStream(new File("D:/tksp.xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Export sản phẩm thành công(Ổ D)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Export sản phẩm thất bại");
        }
    }

    public Date getDate(String ngay) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(ngay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String showDate(Date date) {

        String ngay = "";
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM dd, yyyy");
            ngay = sdf2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ngay;
    }

    private void showDataDoanhThu(String ngay) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThu(dateFor.parse(ngay));
        listDT.forEach(p -> dtmDT.addRow(p.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuDay(dateFor.parse(ngay)) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuDay(dateFor.parse(ngay)))));
            } else {
                lbTongDoanhThu.setText("0 đ");
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

    private void showDataDoanhThuKhoang(String ngayBD, String ngayKT) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThuKhoangNgay(dateFor.parse(ngayBD), dateFor.parse(ngayKT));
        listDT.forEach(p -> dtmDT.addRow(p.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuKhoangNgay(dateFor.parse(ngayBD), dateFor.parse(ngayKT)) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuKhoangNgay(dateFor.parse(ngayBD), dateFor.parse(ngayKT)))));
            } else {
                lbTongDoanhThu.setText("0 đ");
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

    private void showDataDoanhThuMonth(int thangg, int namm) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThuMonth(thangg, namm);
        listDT.forEach(p -> dtmDT.addRow(p.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuMonth(thangg, namm) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuMonth(thangg, namm))));
            } else {
                lbTongDoanhThu.setText("0 đ");
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

    private void showDataDoanhThuYear(int n) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThuYear(n);
        listDT.forEach(p -> dtmDT.addRow(p.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuYear(n) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuYear(n))));
            } else {
                lbTongDoanhThu.setText("0 đ");
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

    private void showDataSP(String ngay) throws ParseException {
        dtmSP.setRowCount(0);
        listSP = serviceThongKe.getAllSanPham(dateFor.parse(ngay));
        listSP.forEach(s -> dtmSP.addRow(s.toDataRow()));
    }

    private void showDataSPKhoangNgay(String ngayBD, String ngayKT) throws ParseException {
        dtmSP.setRowCount(0);
        listSP = serviceThongKe.getAllSanPhamKhoangNgay(dateFor.parse(ngayBD), dateFor.parse(ngayKT));
        listSP.forEach(s -> dtmSP.addRow(s.toDataRow()));
    }

    private void showDataSPMonth(int thang, int nam) throws ParseException {
        dtmSP.setRowCount(0);
        listSP = serviceThongKe.getAllSanPhamMonth(thang, nam);
        listSP.forEach(s -> dtmSP.addRow(s.toDataRow()));
    }

    private void showDataSPYear(int n) throws ParseException {
        dtmSP.setRowCount(0);
        listSP = serviceThongKe.getAllSanPhamYear(n);
        listSP.forEach(s -> dtmSP.addRow(s.toDataRow()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        panelSanPham = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbThangNam = new javax.swing.JComboBox<>();
        cbbLoaiThoiGian = new javax.swing.JComboBox<>();
        btnLocSP = new javax.swing.JButton();
        btnBoLocSP = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbSanPhamDangKinhDoanh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbSanPhamNgungKinhDoanh = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbTongSP = new javax.swing.JLabel();
        txtEndTKSP = new com.github.lgooddatepicker.components.DatePicker();
        lbChonNgayBatDauSP = new javax.swing.JLabel();
        lbChonNgayKetThucSP = new javax.swing.JLabel();
        txtStartTKSP = new com.github.lgooddatepicker.components.DatePicker();
        panelDoanhThuNgay = new javax.swing.JPanel();
        btnBoLocDT = new javax.swing.JButton();
        btnLocDT = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbbLoaiDoanhThu = new javax.swing.JComboBox<>();
        lbChonNgayBDDT = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        txtEndTKDT = new com.github.lgooddatepicker.components.DatePicker();
        lbChonNgayKTDT = new javax.swing.JLabel();
        txtStartTKDT = new com.github.lgooddatepicker.components.DatePicker();
        cbbThangDoanhThu = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lbTongDoanhThu = new javax.swing.JLabel();
        btnMai = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnExport = new javax.swing.JButton();
        radioSP = new javax.swing.JRadioButton();
        radioHD = new javax.swing.JRadioButton();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel7 = new javax.swing.JPanel();
        panelHienBieuDo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbChonBD = new javax.swing.JLabel();
        cbbBieuDoChonThang = new javax.swing.JComboBox<>();
        btnLocBieuDo = new javax.swing.JButton();
        btnBoLocBieuDo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbbLoaiThoiGianBD = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1300, 860));
        setMinimumSize(new java.awt.Dimension(1300, 860));
        setPreferredSize(new java.awt.Dimension(1300, 860));

        jLabel4.setText("Thống kê");
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(163, 54, 54));

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(1300, 860));
        jPanel4.setMinimumSize(new java.awt.Dimension(1300, 860));

        panelSanPham.setBackground(new java.awt.Color(255, 255, 255));
        panelSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Đã bán"
            }
        ));
        jScrollPane2.setViewportView(tbSanPham);

        jLabel6.setText("Loại thời gian :");
        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbThangNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbThangNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbThangNam.setMinimumSize(new java.awt.Dimension(64, 22));
        cbbThangNam.setPreferredSize(new java.awt.Dimension(64, 22));

        cbbLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Báo cáo theo ngày", "Báo cáo theo khoảng ngày", "Báo cáo theo tháng", "Báo cáo theo năm" }));
        cbbLoaiThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLoaiThoiGian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiThoiGianItemStateChanged(evt);
            }
        });

        btnLocSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loc.png"))); // NOI18N
        btnLocSP.setText("Lọc");
        btnLocSP.setBackground(new java.awt.Color(41, 183, 212));
        btnLocSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLocSP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocSPMouseMoved(evt);
            }
        });
        btnLocSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocSPMouseExited(evt);
            }
        });
        btnLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocSPActionPerformed(evt);
            }
        });

        btnBoLocSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boloc.png"))); // NOI18N
        btnBoLocSP.setText("Bỏ lọc");
        btnBoLocSP.setBackground(new java.awt.Color(41, 183, 212));
        btnBoLocSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLocSP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBoLocSPMouseMoved(evt);
            }
        });
        btnBoLocSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBoLocSPMouseExited(evt);
            }
        });
        btnBoLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocSPActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(213, 72, 72));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbSanPhamDangKinhDoanh.setText("0");
        lbSanPhamDangKinhDoanh.setBackground(new java.awt.Color(226, 100, 100));
        lbSanPhamDangKinhDoanh.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbSanPhamDangKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Số SP chưa bán");
        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSanPhamDangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSanPhamDangKinhDoanh)
                .addGap(17, 17, 17))
        );

        jPanel2.setBackground(new java.awt.Color(238, 136, 86));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(139, 101));

        jLabel2.setText("Số SP đã bán");
        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        lbSanPhamNgungKinhDoanh.setText("0");
        lbSanPhamNgungKinhDoanh.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbSanPhamNgungKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbSanPhamNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSanPhamNgungKinhDoanh)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(51, 133, 182));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setPreferredSize(new java.awt.Dimension(139, 101));

        jLabel8.setText("Tổng số SP");
        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        lbTongSP.setText("0");
        lbTongSP.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTongSP.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(16, 16, 16))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(lbTongSP)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        lbChonNgayBatDauSP.setText("Chọn ngày BĐ : ");
        lbChonNgayBatDauSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lbChonNgayKetThucSP.setText("Chọn ngày KT : ");
        lbChonNgayKetThucSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSanPhamLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createSequentialGroup()
                                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbChonNgayBatDauSP)
                                    .addComponent(lbChonNgayKetThucSP)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                                        .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtEndTKSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbThangNam, 0, 181, Short.MAX_VALUE)
                                            .addComponent(txtStartTKSP, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnLocSP)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBoLocSP)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStartTKSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbChonNgayBatDauSP)
                    .addComponent(cbbThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBoLocSP)
                    .addComponent(btnLocSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEndTKSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbChonNgayKetThucSP))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        panelDoanhThuNgay.setBackground(new java.awt.Color(255, 255, 255));
        panelDoanhThuNgay.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        panelDoanhThuNgay.setMaximumSize(new java.awt.Dimension(0, 0));

        btnBoLocDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boloc.png"))); // NOI18N
        btnBoLocDT.setText("Bỏ lọc");
        btnBoLocDT.setBackground(new java.awt.Color(41, 183, 212));
        btnBoLocDT.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLocDT.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBoLocDTMouseMoved(evt);
            }
        });
        btnBoLocDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBoLocDTMouseExited(evt);
            }
        });
        btnBoLocDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocDTActionPerformed(evt);
            }
        });

        btnLocDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loc.png"))); // NOI18N
        btnLocDT.setText("Lọc");
        btnLocDT.setBackground(new java.awt.Color(41, 183, 212));
        btnLocDT.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLocDT.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocDTMouseMoved(evt);
            }
        });
        btnLocDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocDTMouseExited(evt);
            }
        });
        btnLocDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocDTActionPerformed(evt);
            }
        });

        jLabel7.setText("Loại thời gian :");
        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbLoaiDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Báo cáo theo ngày", "Báo cáo theo khoảng ngày", "Báo cáo theo tháng", "Báo cáo theo năm" }));
        cbbLoaiDoanhThu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLoaiDoanhThu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiDoanhThuItemStateChanged(evt);
            }
        });

        lbChonNgayBDDT.setText("Chọn ngày :");
        lbChonNgayBDDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã NV", "Tên NV", "Hình thức", "Tiền mặt", "Tiền CK", "Tổng tiền"
            }
        ));
        jScrollPane3.setViewportView(tbDoanhThu);

        lbChonNgayKTDT.setText("Chọn ngày KT :");
        lbChonNgayKTDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbThangDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbThangDoanhThu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbThangDoanhThu.setMinimumSize(new java.awt.Dimension(64, 22));
        cbbThangDoanhThu.setPreferredSize(new java.awt.Dimension(64, 22));

        jPanel5.setBackground(new java.awt.Color(66, 121, 77));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Tổng doanh thu ngày :");
        jLabel11.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        lbTongDoanhThu.setText("0");
        lbTongDoanhThu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTongDoanhThu)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mailtk.png"))); // NOI18N
        btnMai.setBorderPainted(false);
        btnMai.setContentAreaFilled(false);
        btnMai.setToolTipText("Gửi mail doanh thu");
        btnMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDoanhThuNgayLayout = new javax.swing.GroupLayout(panelDoanhThuNgay);
        panelDoanhThuNgay.setLayout(panelDoanhThuNgayLayout);
        panelDoanhThuNgayLayout.setHorizontalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                        .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbChonNgayKTDT)
                            .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbChonNgayBDDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndTKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoanhThuNgayLayout.createSequentialGroup()
                                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                                        .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbThangDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStartTKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                        .addComponent(btnLocDT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBoLocDT))
                                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                                        .addComponent(cbbLoaiDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnMai)))
                                .addGap(13, 13, 13))))
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoanhThuNgayLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDoanhThuNgayLayout.setVerticalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoanhThuNgayLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cbbLoaiDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbChonNgayBDDT)
                    .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtStartTKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbThangDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBoLocDT)
                        .addComponent(btnLocDT)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbChonNgayKTDT)
                    .addComponent(txtEndTKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnExport.setText("Export(Excel)");
        btnExport.setBackground(new java.awt.Color(41, 183, 212));
        btnExport.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnExport.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnExportMouseMoved(evt);
            }
        });
        btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportMouseExited(evt);
            }
        });
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioSP);
        radioSP.setSelected(true);
        radioSP.setText("Sản phẩm");
        radioSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        buttonGroup1.add(radioHD);
        radioHD.setText("Hóa đơn");
        radioHD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnExport))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(radioSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioHD)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioSP)
                    .addComponent(radioHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(panelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(142, 142, 142))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống kê", jPanel4);

        jToolBar1.setRollover(true);

        jPanel7.setPreferredSize(new java.awt.Dimension(1235, 690));

        javax.swing.GroupLayout panelHienBieuDoLayout = new javax.swing.GroupLayout(panelHienBieuDo);
        panelHienBieuDo.setLayout(panelHienBieuDoLayout);
        panelHienBieuDoLayout.setHorizontalGroup(
            panelHienBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHienBieuDoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(91, 91, 91))
        );
        panelHienBieuDoLayout.setVerticalGroup(
            panelHienBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHienBieuDoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addContainerGap(574, Short.MAX_VALUE))
        );

        jLabel9.setText("Biểu đồ doanh thu");
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        lbChonBD.setText("Chọn tháng :");
        lbChonBD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbBieuDoChonThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbBieuDoChonThang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnLocBieuDo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loc.png"))); // NOI18N
        btnLocBieuDo.setText("Lọc");
        btnLocBieuDo.setBackground(new java.awt.Color(41, 183, 212));
        btnLocBieuDo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLocBieuDo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocBieuDoMouseMoved(evt);
            }
        });
        btnLocBieuDo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocBieuDoMouseExited(evt);
            }
        });
        btnLocBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocBieuDoActionPerformed(evt);
            }
        });

        btnBoLocBieuDo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boloc.png"))); // NOI18N
        btnBoLocBieuDo.setText("Bỏ lọc");
        btnBoLocBieuDo.setBackground(new java.awt.Color(41, 183, 212));
        btnBoLocBieuDo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLocBieuDo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBoLocBieuDoMouseMoved(evt);
            }
        });
        btnBoLocBieuDo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBoLocBieuDoMouseExited(evt);
            }
        });
        btnBoLocBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocBieuDoActionPerformed(evt);
            }
        });

        jLabel3.setText("Loại thời gian :");
        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbLoaiThoiGianBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Năm" }));
        cbbLoaiThoiGianBD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLoaiThoiGianBD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiThoiGianBDItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHienBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbLoaiThoiGianBD, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(lbChonBD, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbBieuDoChonThang, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnLocBieuDo)
                                .addGap(32, 32, 32)
                                .addComponent(btnBoLocBieuDo))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 514, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBoLocBieuDo)
                        .addComponent(btnLocBieuDo))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbLoaiThoiGianBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbChonBD)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbBieuDoChonThang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(panelHienBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel7);

        jTabbedPane1.addTab("Biểu đồ", jToolBar1);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(506, 506, 506)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLocDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocDTActionPerformed
        switch (cbbLoaiDoanhThu.getSelectedIndex()) {
            case 0:
                if (!txtStartTKDT.getDateStringOrEmptyString().trim().isEmpty()) {
                    try {
                        showDataDoanhThu(dateFor.format(getDate(txtStartTKDT.getDateStringOrEmptyString())));
                        if (listDT.size() > 0) {
                            JOptionPane.showMessageDialog(this, "Lọc thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");

                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelThongKe.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
                }
                break;
            case 1:
                if (!txtStartTKDT.getDateStringOrEmptyString().trim().isEmpty() && !txtEndTKDT.getDateStringOrEmptyString().trim().isEmpty()) {
                    try {
                        showDataDoanhThuKhoang(dateFor.format(getDate(txtStartTKDT.getDateStringOrEmptyString())), dateFor.format(getDate(txtEndTKDT.getDateStringOrEmptyString())));
                        if (listDT.size() > 0) {
                            JOptionPane.showMessageDialog(this, "Lọc thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");

                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelThongKe.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
                }
                break;
            case 2:
                try {
                int namm = Integer.valueOf(dateFor.format(new Date()).substring(6, 10));
                showDataDoanhThuMonth((int) cbbThangDoanhThu.getSelectedItem(), nam);
                if (listDT.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");

                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            break;
            default:
                try {
                showDataDoanhThuYear((int) cbbThangDoanhThu.getSelectedItem());
                if (listDT.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");

                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
    }//GEN-LAST:event_btnLocDTActionPerformed

    private void btnLocDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocDTMouseExited
        btnLocDT.setBackground(new Color(41, 183, 212));
        btnLocDT.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLocDTMouseExited

    private void btnLocDTMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocDTMouseMoved
        btnLocDT.setBackground(new Color(33, 70, 131));
        btnLocDT.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocDTMouseMoved

    private void btnBoLocDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocDTActionPerformed
        try {
            showDataDoanhThu(dateFor.format(date));
            cbbLoaiDoanhThu.setSelectedIndex(0);
            txtStartTKDT.setDateToToday();

        } catch (ParseException ex) {
            Logger.getLogger(PanelThongKe.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBoLocDTActionPerformed

    private void btnBoLocDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocDTMouseExited
        btnBoLocDT.setBackground(new Color(41, 183, 212));
        btnBoLocDT.setForeground(Color.BLACK);
        serviceThongKe.bieuDoDoanhThuYear(1, nam, panelHienBieuDo);
    }//GEN-LAST:event_btnBoLocDTMouseExited

    private void btnBoLocDTMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocDTMouseMoved
        btnBoLocDT.setBackground(new Color(33, 70, 131));
        btnBoLocDT.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocDTMouseMoved

    private void btnBoLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocSPActionPerformed
        try {
            cbbLoaiThoiGian.setSelectedIndex(0);
            txtStartTKSP.setDateToToday();
            lbChonNgayKetThucSP.setVisible(false);
            showDataSP(dateFor.format(date));

        } catch (ParseException ex) {
            Logger.getLogger(PanelThongKe.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBoLocSPActionPerformed

    private void btnBoLocSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocSPMouseExited
        btnBoLocSP.setBackground(new Color(41, 183, 212));
        btnBoLocSP.setForeground(Color.BLACK);
        serviceThongKe.bieuDoDoanhThuYear(1, nam, panelHienBieuDo);
    }//GEN-LAST:event_btnBoLocSPMouseExited

    private void btnBoLocSPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocSPMouseMoved
        btnBoLocSP.setBackground(new Color(33, 70, 131));
        btnBoLocSP.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocSPMouseMoved

    private void btnLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocSPActionPerformed
        switch (cbbLoaiThoiGian.getSelectedIndex()) {
            case 0:
                if (!txtStartTKSP.getDateStringOrEmptyString().isEmpty()) {
                    try {
                        showDataSP(dateFor.format(getDate(txtStartTKSP.getDateStringOrEmptyString())));
                        if (listSP.size() > 0) {
                            JOptionPane.showMessageDialog(this, "Lọc thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");

                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelThongKe.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
                }
                break;
            case 1:
                if (!txtStartTKSP.getDateStringOrEmptyString().isEmpty() && !txtEndTKSP.getDateStringOrEmptyString().isEmpty()) {
                    try {
                        showDataSPKhoangNgay(dateFor.format(getDate(txtStartTKSP.getDateStringOrEmptyString())), dateFor.format(getDate(txtEndTKSP.getDateStringOrEmptyString())));
                        if (listSP.size() > 0) {
                            JOptionPane.showMessageDialog(this, "Lọc thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");

                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelThongKe.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
                }
                break;
            case 2:
            try {
                int nam = Integer.parseInt(dateFor.format(new Date()).substring(6, 10));
                showDataSPMonth((int) cbbThangNam.getSelectedItem(), nam);
                if (listSP.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");

                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            break;
            default:
            try {
                showDataSPYear((int) cbbThangNam.getSelectedItem());
                if (listSP.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");

                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
    }//GEN-LAST:event_btnLocSPActionPerformed

    private void btnLocSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocSPMouseExited
        btnLocSP.setBackground(new Color(41, 183, 212));
        btnLocSP.setForeground(Color.BLACK);
        serviceThongKe.bieuDoDoanhThuYear(1, nam, panelHienBieuDo);
    }//GEN-LAST:event_btnLocSPMouseExited

    private void btnLocSPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocSPMouseMoved
        btnLocSP.setBackground(new Color(33, 70, 131));
        btnLocSP.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocSPMouseMoved

    private void cbbLoaiThoiGianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiThoiGianItemStateChanged
        Date date = new Date();
        if (cbbLoaiThoiGian.getSelectedIndex() == 0) {
            lbChonNgayBatDauSP.setText("Chọn ngày :");
            txtStartTKSP.setVisible(true);
            cbbThangNam.setVisible(false);
            lbChonNgayKetThucSP.setVisible(false);
            txtEndTKSP.setVisible(false);
        } else if (cbbLoaiThoiGian.getSelectedIndex() == 1) {
            cbbThangNam.setVisible(false);
            lbChonNgayBatDauSP.setText("Chọn ngày BĐ:");
            lbChonNgayKetThucSP.setVisible(true);
            lbChonNgayKetThucSP.setText("Chọn ngày KT :");
            txtStartTKSP.setVisible(true);
            txtEndTKSP.setVisible(true);
        } else if (cbbLoaiThoiGian.getSelectedIndex() == 2) {
            dcbmThangNam = new DefaultComboBoxModel();
            lbChonNgayBatDauSP.setText("Chọn tháng :");
            lbChonNgayKetThucSP.setVisible(false);
            cbbThangNam.setVisible(true);
            txtStartTKSP.setVisible(false);
            txtEndTKSP.setVisible(false);
            for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
                dcbmThangNam.addElement(i);
            }
            cbbThangNam.setModel(dcbmThangNam);
        } else {
            dcbmThangNam = new DefaultComboBoxModel();
            lbChonNgayBatDauSP.setText("Chọn năm :");
            lbChonNgayKetThucSP.setVisible(false);
            cbbThangNam.setVisible(true);
            txtStartTKSP.setVisible(false);
            txtEndTKSP.setVisible(false);
            int nam = Integer.parseInt(dateFor.format(new Date()).substring(6, 10));
            if (serviceThongKe.namBatDau() != 0) {
                for (int i = serviceThongKe.namBatDau(); i <= nam; i++) {
                    dcbmThangNam.addElement(i);
                }
            } else {
                for (int i = 2020; i <= nam; i++) {
                    dcbmThangNam.addElement(i);
                }
            }
            cbbThangNam.setModel(dcbmThangNam);
        }
    }//GEN-LAST:event_cbbLoaiThoiGianItemStateChanged

    private void btnLocBieuDoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocBieuDoMouseMoved
        btnLocBieuDo.setBackground(new Color(33, 70, 131));
        btnLocBieuDo.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocBieuDoMouseMoved

    private void btnLocBieuDoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocBieuDoMouseExited
        btnLocBieuDo.setBackground(new Color(41, 183, 212));
        btnLocBieuDo.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLocBieuDoMouseExited

    private void btnLocBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocBieuDoActionPerformed
        if (cbbLoaiThoiGianBD.getSelectedIndex() == 0) {// 0 la thang
            serviceThongKe.bieuDoDoanhThuMonth(1, (int) cbbBieuDoChonThang.getSelectedItem(), 2022, panelHienBieuDo);
        } else {// 1 la nam
            serviceThongKe.bieuDoDoanhThuYear(1, (int) cbbBieuDoChonThang.getSelectedItem(), panelHienBieuDo);
        }
    }//GEN-LAST:event_btnLocBieuDoActionPerformed

    private void btnBoLocBieuDoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocBieuDoMouseMoved
        btnBoLocBieuDo.setBackground(new Color(33, 70, 131));
        btnBoLocBieuDo.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocBieuDoMouseMoved

    private void btnBoLocBieuDoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocBieuDoMouseExited
        btnBoLocBieuDo.setBackground(new Color(41, 183, 212));
        btnBoLocBieuDo.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBoLocBieuDoMouseExited

    private void btnBoLocBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocBieuDoActionPerformed
        cbbLoaiThoiGian.setSelectedIndex(1);
        serviceThongKe.bieuDoDoanhThuYear(1, nam, panelHienBieuDo);
        cbbBieuDoChonThang.setSelectedItem(String.valueOf(nam));
    }//GEN-LAST:event_btnBoLocBieuDoActionPerformed

    private void cbbLoaiDoanhThuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiDoanhThuItemStateChanged
        Date date = new Date();
        if (cbbLoaiDoanhThu.getSelectedIndex() == 0) {
            lbChonNgayBDDT.setText("Chọn ngày :");
            lbChonNgayKTDT.setVisible(false);
            txtStartTKDT.setVisible(true);
            txtEndTKDT.setVisible(false);
            cbbThangDoanhThu.setVisible(false);
        } else if (cbbLoaiDoanhThu.getSelectedIndex() == 1) {
            cbbThangDoanhThu.setVisible(false);
            lbChonNgayBDDT.setText("Chọn ngày BĐ:");
            lbChonNgayKTDT.setVisible(true);
            lbChonNgayKTDT.setText("Chọn ngày KT :");
            txtStartTKDT.setVisible(true);
            txtEndTKDT.setVisible(true);
        } else if (cbbLoaiDoanhThu.getSelectedIndex() == 2) {
            dcbmThangNamDoanhThu = new DefaultComboBoxModel();
            lbChonNgayBDDT.setText("Chọn tháng :");
            lbChonNgayKTDT.setVisible(false);
            cbbThangDoanhThu.setVisible(true);
            txtStartTKDT.setVisible(false);
            txtEndTKDT.setVisible(false);
            for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
                dcbmThangNamDoanhThu.addElement(i);
            }
            cbbThangDoanhThu.setModel(dcbmThangNamDoanhThu);
        } else {
            dcbmThangNamDoanhThu = new DefaultComboBoxModel();
            lbChonNgayBDDT.setText("Chọn năm :");
            lbChonNgayKTDT.setVisible(false);
            cbbThangDoanhThu.setVisible(true);
            txtStartTKDT.setVisible(false);
            txtEndTKDT.setVisible(false);
            int namm = Integer.parseInt(dateFor.format(new Date()).substring(6, 10));
            if (serviceThongKe.namBatDauDoanhThu() != 0) {
                for (int i = serviceThongKe.namBatDauDoanhThu(); i <= namm; i++) {
                    dcbmThangNamDoanhThu.addElement(i);
                }
            } else {
                for (int i = 2020; i <= namm; i++) {
                    dcbmThangNamDoanhThu.addElement(i);
                }
            }
            cbbThangDoanhThu.setModel(dcbmThangNamDoanhThu);
        }
    }//GEN-LAST:event_cbbLoaiDoanhThuItemStateChanged

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        if (radioHD.isSelected()) {
            if (listDT.size() > 0) {
                exPortHoaDon();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng lọc hóa đơn trước để export");
            }
        } else {
            if (listSP.size() > 0) {
                exPortSanPham();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng lọc sản phẩm trước để export");
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnExportMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseMoved
        btnExport.setBackground(new Color(33, 70, 131));
        btnExport.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnExportMouseMoved

    private void btnExportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseExited
        btnExport.setBackground(new Color(41, 183, 212));
        btnExport.setForeground(Color.BLACK);

    }//GEN-LAST:event_btnExportMouseExited

    private void cbbLoaiThoiGianBDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiThoiGianBDItemStateChanged
        if (cbbLoaiThoiGianBD.getSelectedIndex() == 0) {
            dcbmThangBieuDo = new DefaultComboBoxModel();
            lbChonBD.setText("Chọn tháng :");
            for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
                dcbmThangBieuDo.addElement(i);
            }
            cbbBieuDoChonThang.setModel(dcbmThangBieuDo);
        } else {
            dcbmThangBieuDo = new DefaultComboBoxModel();
            lbChonBD.setText("Chọn năm :");
            int nam = Integer.parseInt(dateFor.format(new Date()).substring(6, 10));
            if (serviceThongKe.namBatDauDoanhThu() != 0) {
                for (int i = serviceThongKe.namBatDauDoanhThu(); i <= nam; i++) {
                    dcbmThangBieuDo.addElement(i);
                }
            } else {
                for (int i = 2020; i <= nam; i++) {
                    dcbmThangBieuDo.addElement(i);
                }
            }
            cbbBieuDoChonThang.setModel(dcbmThangBieuDo);
        }
    }//GEN-LAST:event_cbbLoaiThoiGianBDItemStateChanged

    private void btnMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaiActionPerformed
        String email = JOptionPane.showInputDialog("Vui lòng nhập mail nhân viên/quản lí nhận: ");
        if (!email.isEmpty() || email == null) {
            if (serviceQuenMK.getOne(email) == "Tài khoản chính xác") {
                final String username = "laptopgroup3@gmail.com";
                final String password = "lveekscgavporrkq";
                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true"); //TLS
                Session session = Session.getInstance(prop,
                        new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
                try {
                    Message message = new MimeMessage(session);
                    if (listDT.size() > 0) {
                        JOptionPane.showMessageDialog(this, "Đã gửi doanh thu tới tài khoản của nhân viên");
                        message.setFrom(new InternetAddress("laptopgroup3@gmail.com"));
                        message.setRecipients(
                                Message.RecipientType.TO,
                                InternetAddress.parse(email)
                        );
                        message.setSubject("Doanh thu");
                        message.setText("Your revenue is : " + lbTongDoanhThu.getText().substring(0, lbTongDoanhThu.getText().length() - 1) + "VND.");
                        Transport.send(message);

                    } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng lọc hóa đơn có sẵn");
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản sai");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản không được để trống hoặc sai định dạng");
        }
    }//GEN-LAST:event_btnMaiActionPerformed
    private void loadThangBieuDo() {
        dcbmThangBieuDo = new DefaultComboBoxModel();
        for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
            dcbmThangBieuDo.addElement(i);
        }
        cbbBieuDoChonThang.setModel(dcbmThangBieuDo);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoLocBieuDo;
    private javax.swing.JButton btnBoLocDT;
    private javax.swing.JButton btnBoLocSP;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnLocBieuDo;
    private javax.swing.JButton btnLocDT;
    private javax.swing.JButton btnLocSP;
    private javax.swing.JButton btnMai;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbBieuDoChonThang;
    private javax.swing.JComboBox<String> cbbLoaiDoanhThu;
    private javax.swing.JComboBox<String> cbbLoaiThoiGian;
    private javax.swing.JComboBox<String> cbbLoaiThoiGianBD;
    private javax.swing.JComboBox<String> cbbThangDoanhThu;
    private javax.swing.JComboBox<String> cbbThangNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbChonBD;
    private javax.swing.JLabel lbChonNgayBDDT;
    private javax.swing.JLabel lbChonNgayBatDauSP;
    private javax.swing.JLabel lbChonNgayKTDT;
    private javax.swing.JLabel lbChonNgayKetThucSP;
    private javax.swing.JLabel lbSanPhamDangKinhDoanh;
    private javax.swing.JLabel lbSanPhamNgungKinhDoanh;
    private javax.swing.JLabel lbTongDoanhThu;
    private javax.swing.JLabel lbTongSP;
    private javax.swing.JPanel panelDoanhThuNgay;
    private javax.swing.JPanel panelHienBieuDo;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JRadioButton radioHD;
    private javax.swing.JRadioButton radioSP;
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JTable tbSanPham;
    private com.github.lgooddatepicker.components.DatePicker txtEndTKDT;
    private com.github.lgooddatepicker.components.DatePicker txtEndTKSP;
    private com.github.lgooddatepicker.components.DatePicker txtStartTKDT;
    private com.github.lgooddatepicker.components.DatePicker txtStartTKSP;
    // End of variables declaration//GEN-END:variables

}
