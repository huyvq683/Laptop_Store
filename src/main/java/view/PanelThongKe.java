/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import custommodel.ThongKeDoanhThuRespone;
import custommodel.ThongKeSanPhamRespone;
import java.awt.Color;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.ThongKeService;
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
    private DefaultComboBoxModel dcbmThangNam;// bên sản phẩm
    private DefaultComboBoxModel dcbmThangNamDoanhThu; // bên doanh thu
    private ThongKeService serviceThongKe = new ThongKeServiceImpl();
    private List<ThongKeSanPhamRespone> listSP = new ArrayList<>();
    private List<ThongKeDoanhThuRespone> listDT = new ArrayList<>();
    private Date date = new Date();
    private DateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
    // ép size tiền định dạng VietNam && tạo 1 NumberFormat để định dạng tiền tệ theo tiêu chuẩn của Việt Nam
    // đơn vị tiền tệ của Việt Nam là đồng
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    // tạo ra năm hiện tại để lọc sản phẩm theo năm
    private int nam = 2022;

    public PanelThongKe() throws ParseException {
        initComponents();
        tbDoanhThu.setModel(dtmDT);
        String hearDT[] = {"Mã HĐ", "Mã NV", "Tên NV", "Hình thức", "Tiền mặt", "Tiền chuyển khoản", "Tổng tiền"};
        dtmDT.setColumnIdentifiers(hearDT);
        tbSanPham.setModel(dtmSP);
        String hearSP[] = {"Mã SP", "Tên SP", "Giá", "Số lượng đã bán", "Doanh thu"};
        dtmSP.setColumnIdentifiers(hearSP);
        // tạo date, hiển thị và tắt 
        nam = Integer.valueOf(dateFor.format(date).substring(6, 10));
        System.out.println(nam);
        showDataSP(dateFor.format(date));
        // setup các lb và cbb
        lbSanPhamDangKinhDoanh.setText(String.valueOf(serviceThongKe.spKinhDoanh(0)));
        lbSanPhamNgungKinhDoanh.setText(String.valueOf(serviceThongKe.spKinhDoanh(1)));
        cbbLoaiThoiGian.setSelectedIndex(0);
        lbNhapNgayThangNam.setText("Nhập ngày :");
        txtNgayThangNam.setVisible(true);
        cbbThangNam.setVisible(false);
        Date dateee = new Date();

        showDataDoanhThu(dateFor.format(dateee));
    }

    private void showDataDoanhThu(String ngay) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThu(dateFor.parse(ngay));
        listDT.forEach(s -> dtmDT.addRow(s.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuYear(nam) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuDay(ngay))));
            } else {
                lbTongDoanhThu.setText("0 đ");
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

    private void showDataDoanhThuMonth(int thang, int nam) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThuMonth(thang, nam);
        listDT.forEach(s -> dtmDT.addRow(s.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuYear(nam) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuMonth(thang, nam))));
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

    private void showDataDoanhThuYear(int n) throws ParseException {
        dtmDT.setRowCount(0);
        listDT = serviceThongKe.getAllDoanhThuYear(n);
        listDT.forEach(s -> dtmDT.addRow(s.toDataRow()));
        if (listDT.size() > 0) {
            if (serviceThongKe.getDoanhThuYear(nam) != null) {
                lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThuYear(n))));
            }
        } else {
            lbTongDoanhThu.setText("0 đ");
        }
    }

//    private void showDataDoanhThuSortTang() throws ParseException {
//        dtmDT.setRowCount(0);
//        listDT = serviceThongKe.getAllDoanhThuSortTang(dateFor.parse(dateFor.format(date)));
//        listDT.forEach(s -> dtmDT.addRow(s.toDataRow()));
//        lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThu())));
//    }
//
//    private void showDataDoanhThuSortGiam() throws ParseException {
//        dtmDT.setRowCount(0);
//        listDT = serviceThongKe.getAllDoanhThuSortGiam(dateFor.parse(dateFor.format(date)));
//        listDT.forEach(s -> dtmDT.addRow(s.toDataRow()));
//        lbTongDoanhThu.setText(currencyVN.format(Double.valueOf(serviceThongKe.getDoanhThu())));
//    }
    private void showDataSP(String ngay) throws ParseException {
        dtmSP.setRowCount(0);
        listSP = serviceThongKe.getAllSanPham(dateFor.parse(ngay));
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

        panelSanPham = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbLoaiThoiGian = new javax.swing.JComboBox<>();
        lbNhapNgayThangNam = new javax.swing.JLabel();
        txtNgayThangNam = new javax.swing.JTextField();
        btnLoc = new javax.swing.JButton();
        btnBoLoc = new javax.swing.JButton();
        cbbThangNam = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lbSanPhamDangKinhDoanh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbSanPhamNgungKinhDoanh = new javax.swing.JLabel();
        panelDoanhThuNgay = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        btnBoLocDT = new javax.swing.JButton();
        btnLocDT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbTongDoanhThu = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbLoaiDoanhThu = new javax.swing.JComboBox<>();
        lbNhapNgayDoanhThu = new javax.swing.JLabel();
        cbbThangNamDoanhThu = new javax.swing.JComboBox<>();
        txtDoanhThuNhapNgay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(98, 191, 215));
        setMaximumSize(new java.awt.Dimension(1300, 860));
        setMinimumSize(new java.awt.Dimension(1300, 860));
        setPreferredSize(new java.awt.Dimension(1300, 860));

        panelSanPham.setBackground(new java.awt.Color(98, 191, 215));
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

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Loại thời gian :");

        cbbLoaiThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Báo cáo theo ngày", "Báo cáo theo tháng", "Báo cáo theo năm" }));
        cbbLoaiThoiGian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiThoiGianItemStateChanged(evt);
            }
        });

        lbNhapNgayThangNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbNhapNgayThangNam.setText("Nhập ngày tháng năm : ");

        txtNgayThangNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNgayThangNam.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnLoc.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLoc.setIcon(new ImageIcon("src/main/img/loc.png"));
        btnLoc.setText("Lọc");
        btnLoc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocMouseMoved(evt);
            }
        });
        btnLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocMouseExited(evt);
            }
        });
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnBoLoc.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLoc.setIcon(new ImageIcon("src/main/img/boloc.png"));
        btnBoLoc.setText("Bỏ lọc");
        btnBoLoc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBoLocMouseMoved(evt);
            }
        });
        btnBoLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBoLocMouseExited(evt);
            }
        });
        btnBoLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocActionPerformed(evt);
            }
        });

        cbbThangNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbThangNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbThangNam.setMinimumSize(new java.awt.Dimension(64, 22));
        cbbThangNam.setPreferredSize(new java.awt.Dimension(64, 22));

        jPanel1.setBackground(new java.awt.Color(213, 72, 72));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbSanPhamDangKinhDoanh.setBackground(new java.awt.Color(226, 100, 100));
        lbSanPhamDangKinhDoanh.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbSanPhamDangKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        lbSanPhamDangKinhDoanh.setText("0");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Số sản phẩm đang kinh doanh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSanPhamDangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Số sản phẩm ngừng kinh doanh");

        lbSanPhamNgungKinhDoanh.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbSanPhamNgungKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        lbSanPhamNgungKinhDoanh.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbSanPhamNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSanPhamNgungKinhDoanh)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSanPhamLayout.createSequentialGroup()
                        .addComponent(lbNhapNgayThangNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBoLoc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSanPhamLayout.createSequentialGroup()
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSanPhamLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayThangNam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoc)
                        .addComponent(btnBoLoc))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNhapNgayThangNam)
                        .addComponent(cbbThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDoanhThuNgay.setBackground(new java.awt.Color(98, 191, 215));
        panelDoanhThuNgay.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        panelDoanhThuNgay.setMaximumSize(new java.awt.Dimension(0, 0));

        tbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Tên nhân viên", "Hình thức", "Tiền mặt", "Tiền chuyển khoản", "Tổng tiền"
            }
        ));
        tbDoanhThu.setPreferredSize(new java.awt.Dimension(400, 80));
        jScrollPane1.setViewportView(tbDoanhThu);

        btnBoLocDT.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLocDT.setIcon(new ImageIcon("src/main/img/boloc.png"));
        btnBoLocDT.setText("Bỏ lọc");
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

        btnLocDT.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLocDT.setIcon(new ImageIcon("src/main/img/loc.png"));
        btnLocDT.setText("Lọc");
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

        jPanel3.setBackground(new java.awt.Color(66, 121, 77));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tổng doanh thu ngày :");

        lbTongDoanhThu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDoanhThu.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTongDoanhThu)
                    .addComponent(jLabel3))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Loại thời gian :");

        cbbLoaiDoanhThu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLoaiDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Báo cáo theo ngày", "Báo cáo theo tháng", "Báo cáo theo năm" }));
        cbbLoaiDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiDoanhThuActionPerformed(evt);
            }
        });

        lbNhapNgayDoanhThu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbNhapNgayDoanhThu.setText("Nhập ngày tháng năm :");

        cbbThangNamDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelDoanhThuNgayLayout = new javax.swing.GroupLayout(panelDoanhThuNgay);
        panelDoanhThuNgay.setLayout(panelDoanhThuNgayLayout);
        panelDoanhThuNgayLayout.setHorizontalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                        .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cbbLoaiDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                                .addComponent(lbNhapNgayDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDoanhThuNhapNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbThangNamDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLocDT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBoLocDT)))
                        .addContainerGap())
                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelDoanhThuNgayLayout.setVerticalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoanhThuNgayLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbbLoaiDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbThangNamDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoanhThuNhapNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocDT)
                    .addComponent(btnBoLocDT)
                    .addComponent(lbNhapNgayDoanhThu))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(163, 54, 54));
        jLabel4.setText("Thống kê");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(panelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbLoaiThoiGianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiThoiGianItemStateChanged
        Date date = new Date();
        if (cbbLoaiThoiGian.getSelectedIndex() == 0) {
            lbNhapNgayThangNam.setText("Nhập ngày :");
            txtNgayThangNam.setVisible(true);
            cbbThangNam.setVisible(false);
        } else if (cbbLoaiThoiGian.getSelectedIndex() == 1) {
            dcbmThangNam = new DefaultComboBoxModel();
            lbNhapNgayThangNam.setText("Chọn tháng :");
            txtNgayThangNam.setVisible(false);
            cbbThangNam.setVisible(true);
            for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
                dcbmThangNam.addElement(i);
            }
            cbbThangNam.setModel(dcbmThangNam);
        } else {
            dcbmThangNam = new DefaultComboBoxModel();
            lbNhapNgayThangNam.setText("Chọn năm :");
            txtNgayThangNam.setVisible(false);
            cbbThangNam.setVisible(true);
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
    private boolean validateNgay(String ngay) {
        if (ngay.trim().matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            return true;
        } else {
            return false;
        }
    }
    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        if (cbbLoaiThoiGian.getSelectedIndex() == 0) {
            if (!txtNgayThangNam.getText().isEmpty() && validateNgay(txtNgayThangNam.getText().trim())) {
                try {
                    showDataSP(txtNgayThangNam.getText());
                    if (listSP.size() > 0) {
                        JOptionPane.showMessageDialog(this, "Lọc thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");
                    }
                } catch (ParseException ex) {
                    //Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
            }
        } else if (cbbLoaiThoiGian.getSelectedIndex() == 1) {
            try {
                showDataSPMonth((int) cbbThangNam.getSelectedItem(), nam);
                if (listSP.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");
                }
            } catch (ParseException ex) {
                //Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                showDataSPYear((int) cbbThangNam.getSelectedItem());
                if (listSP.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");
                }
            } catch (ParseException ex) {
                //Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnBoLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocActionPerformed
        try {
            cbbLoaiThoiGian.setSelectedIndex(0);
            showDataSP(dateFor.format(date));
        } catch (ParseException ex) {
            Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBoLocActionPerformed

    private void btnLocDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocDTActionPerformed
        if (cbbLoaiDoanhThu.getSelectedIndex() == 0) {
            if (!txtDoanhThuNhapNgay.getText().isEmpty() && validateNgay(txtDoanhThuNhapNgay.getText().trim())) {
                try {
                    showDataDoanhThu(txtDoanhThuNhapNgay.getText());
                    if (listDT.size() > 0) {
                        JOptionPane.showMessageDialog(this, "Lọc thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");
                    }
                } catch (ParseException ex) {
                    //Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
            }
        } else if (cbbLoaiDoanhThu.getSelectedIndex() == 1) {
            try {
                Date datee = new Date();
                int namm = Integer.valueOf(dateFor.format(datee).substring(6, 10));
                showDataDoanhThuMonth((int) cbbThangNamDoanhThu.getSelectedItem(), namm);
                if (listDT.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");
                }
            } catch (ParseException ex) {
                //Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                showDataDoanhThuYear((int) cbbThangNamDoanhThu.getSelectedItem());
                if (listDT.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");
                }
            } catch (ParseException ex) {
                //Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLocDTActionPerformed

    private void btnBoLocDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocDTActionPerformed
        try {
            showDataDoanhThu(dateFor.format(date));
        } catch (ParseException ex) {
            Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBoLocDTActionPerformed
// thay đổi màu sắc khi di chuột tới
    private void btnLocMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocMouseMoved
        btnLoc.setBackground(new Color(33, 70, 131));
        btnLoc.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocMouseMoved

    private void btnBoLocMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocMouseMoved
        btnBoLoc.setBackground(new Color(33, 70, 131));
        btnBoLoc.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocMouseMoved

    private void btnLocDTMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocDTMouseMoved
        btnLocDT.setBackground(new Color(33, 70, 131));
        btnLocDT.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocDTMouseMoved

    private void btnBoLocDTMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocDTMouseMoved
        btnBoLocDT.setBackground(new Color(33, 70, 131));
        btnBoLocDT.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocDTMouseMoved
// thay đổi màu sắc khi bỏ chuột ra
    private void btnLocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocMouseExited
        btnLoc.setBackground(new Color(255, 255, 255));
        btnLoc.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLocMouseExited

    private void btnBoLocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocMouseExited
        btnBoLoc.setBackground(new Color(255, 255, 255));
        btnBoLoc.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBoLocMouseExited

    private void btnLocDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocDTMouseExited
        btnLocDT.setBackground(new Color(255, 255, 255));
        btnLocDT.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLocDTMouseExited

    private void btnBoLocDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocDTMouseExited
        btnBoLocDT.setBackground(new Color(255, 255, 255));
        btnBoLocDT.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBoLocDTMouseExited

    private void cbbLoaiDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiDoanhThuActionPerformed
        Date date = new Date();
        if (cbbLoaiDoanhThu.getSelectedIndex() == 0) {
            lbNhapNgayDoanhThu.setText("Nhập ngày :");
            txtDoanhThuNhapNgay.setVisible(true);
            cbbThangNamDoanhThu.setVisible(false);
        } else if (cbbLoaiDoanhThu.getSelectedIndex() == 1) {
            dcbmThangNamDoanhThu = new DefaultComboBoxModel();
            lbNhapNgayDoanhThu.setText("Chọn tháng :");
            txtDoanhThuNhapNgay.setVisible(false);
            cbbThangNamDoanhThu.setVisible(true);
            for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
                dcbmThangNamDoanhThu.addElement(i);
            }
            cbbThangNamDoanhThu.setModel(dcbmThangNamDoanhThu);
        } else {
            dcbmThangNamDoanhThu = new DefaultComboBoxModel();
            lbNhapNgayDoanhThu.setText("Chọn năm :");
            txtDoanhThuNhapNgay.setVisible(false);
            cbbThangNamDoanhThu.setVisible(true);
            int nam = Integer.parseInt(dateFor.format(new Date()).substring(6, 10));
            if (serviceThongKe.namBatDauDoanhThu() != 0) {
                for (int i = serviceThongKe.namBatDauDoanhThu(); i <= nam; i++) {
                    dcbmThangNamDoanhThu.addElement(i);
                }
            } else {
                for (int i = 2020; i <= nam; i++) {
                    dcbmThangNamDoanhThu.addElement(i);
                }
            }
            cbbThangNamDoanhThu.setModel(dcbmThangNamDoanhThu);
        }
    }//GEN-LAST:event_cbbLoaiDoanhThuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoLoc;
    private javax.swing.JButton btnBoLocDT;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnLocDT;
    private javax.swing.JComboBox<String> cbbLoaiDoanhThu;
    private javax.swing.JComboBox<String> cbbLoaiThoiGian;
    private javax.swing.JComboBox<String> cbbThangNam;
    private javax.swing.JComboBox<String> cbbThangNamDoanhThu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNhapNgayDoanhThu;
    private javax.swing.JLabel lbNhapNgayThangNam;
    private javax.swing.JLabel lbSanPhamDangKinhDoanh;
    private javax.swing.JLabel lbSanPhamNgungKinhDoanh;
    private javax.swing.JLabel lbTongDoanhThu;
    private javax.swing.JPanel panelDoanhThuNgay;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtDoanhThuNhapNgay;
    private javax.swing.JTextField txtNgayThangNam;
    // End of variables declaration//GEN-END:variables

    private boolean Year(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
