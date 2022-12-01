package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
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
    private DefaultComboBoxModel dcbmThangBieuDo;
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
        String hearDT[] = {"Mã HĐ", "Mã NV", "Tên NV", "Hình thức", "Tiền mặt", "Tiền CK", "Tổng tiền"};
        dtmDT.setColumnIdentifiers(hearDT);
        tbDoanhThu.setModel(dtmDT);
        String hearSP[] = {"Mã SP", "Tên SP", "Giá", "Số lượng đã bán", "Doanh thu"};
        dtmSP.setColumnIdentifiers(hearSP);
        tbSanPham.setModel(dtmSP);
        // tạo date, hiển thị và tắt 
        nam = Integer.valueOf(dateFor.format(date).substring(6, 10));
        showDataSP(dateFor.format(date));
        showDataDoanhThu(dateFor.format(date));
        start();
    }

    // setup các lb và cbb
    private void start() {
        lbTongSP.setText(String.valueOf(serviceThongKe.spKinhDoanhAll()));
        lbSanPhamDangKinhDoanh.setText(String.valueOf(serviceThongKe.spKinhDoanh(0)));
        lbSanPhamNgungKinhDoanh.setText(String.valueOf(serviceThongKe.spKinhDoanh(1)));
        cbbLoaiThoiGian.setSelectedIndex(0);
        cbbLoaiBieuDo.setSelectedIndex(0);
        lbNhapNgayThangNam.setText("Nhập ngày :");
        txtNgayThangNam.setVisible(true);
        cbbThangNam.setVisible(false);
        cbbLoaiDoanhThu.setSelectedIndex(0);
        lbNhapNgayThangNam.setText("Nhập ngày :");
        txtDoanhThuNhapNgay.setVisible(true);
        cbbThangDoanhThu.setVisible(false);
        loadThangBieuDo();
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

        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        panelSanPham = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbLoaiThoiGian = new javax.swing.JComboBox<>();
        lbNhapNgayThangNam = new javax.swing.JLabel();
        txtNgayThangNam = new javax.swing.JTextField();
        btnLocSP = new javax.swing.JButton();
        btnBoLocSP = new javax.swing.JButton();
        cbbThangNam = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lbSanPhamDangKinhDoanh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbSanPhamNgungKinhDoanh = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbTongSP = new javax.swing.JLabel();
        panelDoanhThuNgay = new javax.swing.JPanel();
        btnBoLocDT = new javax.swing.JButton();
        btnLocDT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbTongDoanhThu = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbLoaiDoanhThu = new javax.swing.JComboBox<>();
        lbNhapNgayDoanhThu = new javax.swing.JLabel();
        cbbThangDoanhThu = new javax.swing.JComboBox<>();
        txtDoanhThuNhapNgay = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        btnBieuDoTron1 = new javax.swing.JButton();
        btnBieuDoCot1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel7 = new javax.swing.JPanel();
        panelHienBieuDo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbbLoaiBieuDo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbbBieuDoChonThang = new javax.swing.JComboBox<>();
        btnLocBieuDo = new javax.swing.JButton();
        btnBoLocBieuDo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1300, 860));
        setMinimumSize(new java.awt.Dimension(1300, 860));
        setPreferredSize(new java.awt.Dimension(1300, 860));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(163, 54, 54));
        jLabel4.setText("Thống kê");

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
        lbNhapNgayThangNam.setText("Nhập ngày : ");

        txtNgayThangNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNgayThangNam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(40, 184, 213)));

        btnLocSP.setBackground(new java.awt.Color(41, 183, 212));
        btnLocSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLocSP.setIcon(new ImageIcon("src/main/img/loc.png"));
        btnLocSP.setText("Lọc");
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

        btnBoLocSP.setBackground(new java.awt.Color(41, 183, 212));
        btnBoLocSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLocSP.setIcon(new ImageIcon("src/main/img/boloc.png"));
        btnBoLocSP.setText("Bỏ lọc");
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
        jLabel1.setText("Số SP chưa bán");

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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Số SP đã bán");

        lbSanPhamNgungKinhDoanh.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbSanPhamNgungKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        lbSanPhamNgungKinhDoanh.setText("0");

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

        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tổng số SP");

        lbTongSP.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTongSP.setForeground(new java.awt.Color(255, 255, 255));
        lbTongSP.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTongSP)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNhapNgayThangNam))
                        .addGap(18, 18, 18)
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSanPhamLayout.createSequentialGroup()
                                .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelSanPhamLayout.createSequentialGroup()
                                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLocSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBoLocSP)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayThangNam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLocSP)
                        .addComponent(btnBoLocSP))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNhapNgayThangNam)
                        .addComponent(cbbThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        panelDoanhThuNgay.setBackground(new java.awt.Color(255, 255, 255));
        panelDoanhThuNgay.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        panelDoanhThuNgay.setMaximumSize(new java.awt.Dimension(0, 0));

        btnBoLocDT.setBackground(new java.awt.Color(41, 183, 212));
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

        btnLocDT.setBackground(new java.awt.Color(41, 183, 212));
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
                .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        lbNhapNgayDoanhThu.setText("Nhập ngày :");

        cbbThangDoanhThu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbThangDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtDoanhThuNhapNgay.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDoanhThuNhapNgay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(40, 184, 213)));

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

        javax.swing.GroupLayout panelDoanhThuNgayLayout = new javax.swing.GroupLayout(panelDoanhThuNgay);
        panelDoanhThuNgay.setLayout(panelDoanhThuNgayLayout);
        panelDoanhThuNgayLayout.setHorizontalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoanhThuNgayLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                        .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNhapNgayDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                                .addComponent(cbbLoaiDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDoanhThuNhapNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbThangDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                                .addComponent(btnLocDT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBoLocDT)))))
                .addContainerGap())
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
                    .addComponent(cbbThangDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoanhThuNhapNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocDT)
                    .addComponent(btnBoLocDT)
                    .addComponent(lbNhapNgayDoanhThu))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        btnBieuDoTron1.setText("Tron");

        btnBieuDoCot1.setText("Cot");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBieuDoTron1)
                .addGap(73, 73, 73)
                .addComponent(btnBieuDoCot1)
                .addGap(433, 433, 433))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBieuDoTron1)
                    .addComponent(btnBieuDoCot1))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống kê", jPanel4);

        jToolBar1.setRollover(true);

        javax.swing.GroupLayout panelHienBieuDoLayout = new javax.swing.GroupLayout(panelHienBieuDo);
        panelHienBieuDo.setLayout(panelHienBieuDoLayout);
        panelHienBieuDoLayout.setHorizontalGroup(
            panelHienBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHienBieuDoLayout.createSequentialGroup()
                .addContainerGap(1067, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(91, 91, 91))
        );
        panelHienBieuDoLayout.setVerticalGroup(
            panelHienBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHienBieuDoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addContainerGap(531, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Biểu đồ :");

        cbbLoaiBieuDo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLoaiBieuDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng sản phẩm", "Doanh thu" }));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Chọn tháng :");

        cbbBieuDoChonThang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbBieuDoChonThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLocBieuDo.setBackground(new java.awt.Color(41, 183, 212));
        btnLocBieuDo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLocBieuDo.setIcon(new ImageIcon("src/main/img/loc.png"));
        btnLocBieuDo.setText("Lọc");
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

        btnBoLocBieuDo.setBackground(new java.awt.Color(41, 183, 212));
        btnBoLocBieuDo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnBoLocBieuDo.setIcon(new ImageIcon("src/main/img/boloc.png"));
        btnBoLocBieuDo.setText("Bỏ lọc");
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(panelHienBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbLoaiBieuDo, 0, 199, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbBieuDoChonThang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(67, 67, 67)
                        .addComponent(btnLocBieuDo)
                        .addGap(29, 29, 29)
                        .addComponent(btnBoLocBieuDo)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel9))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLocBieuDo)
                            .addComponent(btnBoLocBieuDo)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(cbbLoaiBieuDo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cbbBieuDoChonThang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHienBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel7);

        jTabbedPane1.addTab("Biểu đồ", jToolBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbLoaiDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiDoanhThuActionPerformed
        switch (cbbLoaiDoanhThu.getSelectedIndex()) {
            case 0:
                lbNhapNgayDoanhThu.setText("Nhập ngày :");
                txtDoanhThuNhapNgay.setVisible(true);
                cbbThangDoanhThu.setVisible(false);
                break;
            case 1:
                dcbmThangNamDoanhThu = new DefaultComboBoxModel();
                lbNhapNgayDoanhThu.setText("Chọn tháng :");
                txtDoanhThuNhapNgay.setVisible(false);
                cbbThangDoanhThu.setVisible(true);
                for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
                    dcbmThangNamDoanhThu.addElement(i);
                }
                cbbThangDoanhThu.setModel(dcbmThangNamDoanhThu);
                break;
            default:
                dcbmThangNamDoanhThu = new DefaultComboBoxModel();
                lbNhapNgayDoanhThu.setText("Chọn năm :");
                txtDoanhThuNhapNgay.setVisible(false);
                cbbThangDoanhThu.setVisible(true);
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
                cbbThangDoanhThu.setModel(dcbmThangNamDoanhThu);
                break;
        }
    }//GEN-LAST:event_cbbLoaiDoanhThuActionPerformed

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
                    Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
            }
        } else if (cbbLoaiDoanhThu.getSelectedIndex() == 1) {
            try {
                int namm = Integer.valueOf(dateFor.format(new Date()).substring(6, 10));
                showDataDoanhThuMonth((int) cbbThangDoanhThu.getSelectedItem(), nam);
                if (listDT.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");
                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                showDataDoanhThuYear((int) cbbThangDoanhThu.getSelectedItem());
                if (listDT.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");
                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }

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
            txtDoanhThuNhapNgay.setText("");
        } catch (ParseException ex) {
            Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBoLocDTActionPerformed

    private void btnBoLocDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocDTMouseExited
        btnBoLocDT.setBackground(new Color(41, 183, 212));
        btnBoLocDT.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBoLocDTMouseExited

    private void btnBoLocDTMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocDTMouseMoved
        btnBoLocDT.setBackground(new Color(33, 70, 131));
        btnBoLocDT.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocDTMouseMoved

    private void btnBoLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocSPActionPerformed
        try {
            cbbLoaiThoiGian.setSelectedIndex(0);
            txtNgayThangNam.setText("");
            showDataSP(dateFor.format(date));
        } catch (ParseException ex) {
            Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBoLocSPActionPerformed

    private void btnBoLocSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocSPMouseExited
        btnBoLocSP.setBackground(new Color(41, 183, 212));
        btnBoLocSP.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBoLocSPMouseExited

    private void btnBoLocSPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoLocSPMouseMoved
        btnBoLocSP.setBackground(new Color(33, 70, 131));
        btnBoLocSP.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnBoLocSPMouseMoved

    private void btnLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocSPActionPerformed
        switch (cbbLoaiThoiGian.getSelectedIndex()) {
            case 0:
                if (!txtNgayThangNam.getText().isEmpty() && validateNgay(txtNgayThangNam.getText().trim())) {
                    try {
                        showDataSP(txtNgayThangNam.getText());
                        if (listSP.size() > 0) {
                            JOptionPane.showMessageDialog(this, "Lọc thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ngày không được để trống hoặc sai định dạng");
                }
                break;
            case 1:
            try {
                int nam = Integer.parseInt(dateFor.format(new Date()).substring(6, 10));
                showDataSPMonth((int) cbbThangNam.getSelectedItem(), nam);
                if (listSP.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Lọc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm nào");
                }
            } catch (ParseException ex) {
                Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
    }//GEN-LAST:event_btnLocSPActionPerformed

    private void btnLocSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocSPMouseExited
        btnLocSP.setBackground(new Color(41, 183, 212));
        btnLocSP.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLocSPMouseExited

    private void btnLocSPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocSPMouseMoved
        btnLocSP.setBackground(new Color(33, 70, 131));
        btnLocSP.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocSPMouseMoved

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

    private void btnLocBieuDoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocBieuDoMouseMoved
        btnLocBieuDo.setBackground(new Color(33, 70, 131));
        btnLocBieuDo.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLocBieuDoMouseMoved

    private void btnLocBieuDoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocBieuDoMouseExited
        btnLocBieuDo.setBackground(new Color(41, 183, 212));
        btnLocBieuDo.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnLocBieuDoMouseExited

    private void btnLocBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocBieuDoActionPerformed
        if (cbbLoaiBieuDo.getSelectedIndex() == 0) {
            serviceThongKe.getBieuDoSPMonth((int) cbbBieuDoChonThang.getSelectedItem(), nam, panelHienBieuDo);
        } else {
            serviceThongKe.getBieuDoDTMonth((int) cbbBieuDoChonThang.getSelectedItem(), nam, panelHienBieuDo);
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
        serviceThongKe.getBieuDoSPMonth(Integer.parseInt(dateFor.format(new Date()).substring(3, 5)), nam, panelHienBieuDo);
    }//GEN-LAST:event_btnBoLocBieuDoActionPerformed
    private void loadThangBieuDo() {
        dcbmThangBieuDo = new DefaultComboBoxModel();
        for (int i = 1; i <= Integer.parseInt(dateFor.format(new Date()).substring(3, 5)); i++) {
            dcbmThangBieuDo.addElement(i);
        }
        cbbBieuDoChonThang.setModel(dcbmThangBieuDo);

    }

    private boolean validateNgay(String ngay) {
        if (ngay.trim().matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|"
                + "(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)"
                + "?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|"
                + "[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
                + "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            return true;
        } else {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBieuDoCot1;
    private javax.swing.JButton btnBieuDoTron1;
    private javax.swing.JButton btnBoLocBieuDo;
    private javax.swing.JButton btnBoLocDT;
    private javax.swing.JButton btnBoLocSP;
    private javax.swing.JButton btnLocBieuDo;
    private javax.swing.JButton btnLocDT;
    private javax.swing.JButton btnLocSP;
    private javax.swing.JComboBox<String> cbbBieuDoChonThang;
    private javax.swing.JComboBox<String> cbbLoaiBieuDo;
    private javax.swing.JComboBox<String> cbbLoaiDoanhThu;
    private javax.swing.JComboBox<String> cbbLoaiThoiGian;
    private javax.swing.JComboBox<String> cbbThangDoanhThu;
    private javax.swing.JComboBox<String> cbbThangNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbNhapNgayDoanhThu;
    private javax.swing.JLabel lbNhapNgayThangNam;
    private javax.swing.JLabel lbSanPhamDangKinhDoanh;
    private javax.swing.JLabel lbSanPhamNgungKinhDoanh;
    private javax.swing.JLabel lbTongDoanhThu;
    private javax.swing.JLabel lbTongSP;
    private javax.swing.JPanel panelDoanhThuNgay;
    private javax.swing.JPanel panelHienBieuDo;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtDoanhThuNhapNgay;
    private javax.swing.JTextField txtNgayThangNam;
    // End of variables declaration//GEN-END:variables

}
