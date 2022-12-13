/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import custommodel.SanPhamViewKMResponse;
import domainmodel.KhuyenMai;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.impl.KhuyenMaiServiceImpl;

/**
 *
 * @author dinhv
 */
public class PanelKhuyenMai extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private KhuyenMaiServiceImpl khuyenMaiServiceImpl;
    private List<KhuyenMai> listKhuyenMais;
    private List<SanPhamViewKMResponse> listSanPhamResponses;
    private List<SanPhamViewKMResponse> listSanPhamResponsesReal;

    /**
     * Creates new form ViewKhuyenMai
     */
    public PanelKhuyenMai() {
        initComponents();
        khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        khuyenMaiServiceImpl.updateTrangThaiKM();
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText(), cboLocTrangThai.getSelectedIndex() == 0 ? 2 : (cboLocTrangThai.getSelectedIndex() == 1) ? 1 : 0);
        listSanPhamResponses = khuyenMaiServiceImpl.getAllSanPham(txtTimKiemSanPham.getText());
        listSanPhamResponsesReal = listSanPhamResponses;
        txtNgayBatDau.getSettings().setAllowKeyboardEditing(false);
        txtNgayKetThuc.getSettings().setAllowKeyboardEditing(false);
        fillToTableKhuyenMai();
        fillToTableSanPham();
        setColor();
    }

    public void setColor() {
        txtTimKiemKhuyenMai.setBackground(new Color(0, 0, 0, 0));
        txtTimKiemSanPham.setBackground(new Color(0, 0, 0, 0));
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        jPanel3.setBackground(Color.WHITE);
        this.setBackground(new Color(0, 0, 0, 0));
    }

    public Date getDate(String ngay) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if (ngay != "") {
                date = formatter.parse(ngay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String dinhDangSoToTalbe(BigDecimal so) {
        Locale locale = new Locale("en", "EN");
        String pattern = "###,###.##";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat
                .getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(so);
    }

    public String dinhDangSoToTxt(BigDecimal so) {
        Locale locale = new Locale("en", "EN");
        String pattern = "######.##";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat
                .getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(so);
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

    public void insertKhuyenMai() {
        KhuyenMai km = new KhuyenMai();
        km.setMa(khuyenMaiServiceImpl.genMaHD() + "");
        km.setTenKM(txtTenKM.getText());
        km.setLoaiKM(cboLoaiKM.getSelectedIndex() == 0 ? 0 : 1); //Tiền là số 1 % chỉ là số 0
        km.setNgayBD(getDate(txtNgayBatDau.getDateStringOrEmptyString()));
        km.setNgayKT(getDate(txtNgayKetThuc.getDateStringOrEmptyString()));
        km.setTrangThai(cboTrangThai.getSelectedIndex() == 0 ? 1 : 0); //Đang hoạt động là 1 không hoạt động là 0
        try {
            km.setGiaTriKM(new BigDecimal(txtGiaTri.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá trị chỉ được nhập từ các số từ 0->9");
            return;
        }
        km.setCreatedDate(new Date());
        km.setLastModifiedDate(new Date());
        JOptionPane.showMessageDialog(this, khuyenMaiServiceImpl.insertOrUpdateKhuyenMai(km));
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText(), cboLocTrangThai.getSelectedIndex() == 0 ? 2 : (cboLocTrangThai.getSelectedIndex() == 1) ? 1 : 0);
        if (khuyenMaiServiceImpl.insertOrUpdateKhuyenMai(km).equalsIgnoreCase("Thêm thành công")) {
            fillToTableKhuyenMai();
            fillToTableSanPham();
            if (cboLocTrangThai.getSelectedIndex() == 0 || cboLocTrangThai.getSelectedIndex() == 1) {
                tblKhuyenMai.setRowSelectionInterval(0, 0);
            }
        }
    }

    public void showData() {
        int rowKM = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = listKhuyenMais.get(rowKM);
        txtTenKM.setText(km.getTenKM());
        cboLoaiKM.setSelectedIndex(km.getLoaiKM() == 0 ? 0 : 1);
        txtGiaTri.setText(dinhDangSoToTxt(km.getGiaTriKM()));
        txtNgayBatDau.setText(showDate(km.getNgayBD()));
        txtNgayKetThuc.setText(showDate(km.getNgayKT()));
        cboTrangThai.setSelectedIndex(km.getTrangThai() == 0 ? 1 : 0);
        listSanPhamResponses = khuyenMaiServiceImpl.getAllSanPhamKhuyenMai(km);
        listSanPhamResponsesReal = listSanPhamResponses;
        fillToTableSanPham();
    }

    public void updateKM() {
        int rowKM = tblKhuyenMai.getSelectedRow();
        if (rowKM < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khuyến mại");
            return;
        }
        KhuyenMai km = listKhuyenMais.get(rowKM);
        km.setTenKM(txtTenKM.getText());
        km.setLoaiKM(cboLoaiKM.getSelectedIndex() == 0 ? 0 : 1); //Tiền là số 1 % chỉ là số 0
        km.setGiaTriKM(new BigDecimal(txtGiaTri.getText()));
        km.setNgayBD(getDate(txtNgayBatDau.getDateStringOrEmptyString()));
        km.setNgayKT(getDate(txtNgayKetThuc.getDateStringOrEmptyString()));
        km.setTrangThai(cboTrangThai.getSelectedIndex() == 0 ? 1 : 0); //Đang hoạt động là 1 không hoạt động là 0
        km.setLastModifiedDate(new Date());
        khuyenMaiServiceImpl.insertOrUpdateKhuyenMai(km);
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText(), cboLocTrangThai.getSelectedIndex() == 0 ? 2 : (cboLocTrangThai.getSelectedIndex() == 1) ? 1 : 0);
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        fillToTableKhuyenMai();
    }

    public void fillToTableKhuyenMai() {
        tableModel = (DefaultTableModel) tblKhuyenMai.getModel();
        tableModel.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (KhuyenMai km : listKhuyenMais) {
            tableModel.addRow(new Object[]{
                "KM" + km.getMa(), km.getTenKM(), km.getLoaiKM() == 1 ? "Tiền" : "%", dinhDangSoToTalbe(km.getGiaTriKM()),
                formatter.format(km.getNgayBD()), formatter.format(km.getNgayKT()), km.getTrangThai() == 0 ? "Ngừng áp dụng" : "Đang áp dụng"
            });
        }
    }

    public void fillToTableSanPham() {
        tableModel = (DefaultTableModel) tblSanPham.getModel();
        tableModel.setRowCount(0);
        int rowKhuyenMai = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = null;
        if (rowKhuyenMai != -1) {
            km = listKhuyenMais.get(rowKhuyenMai);
        }
        for (SanPhamViewKMResponse sp : listSanPhamResponses) {
            tableModel.addRow(new Object[]{
                sp.getMa(), sp.getTen(), sp.getCpu(), sp.getHang(), sp.getRam(),
                sp.getCard(), sp.getOCung(), sp.getGia(), sp.getSoLuong(), rowKhuyenMai == -1 ? false : khuyenMaiServiceImpl.kiemTraKM(sp, km)});
        }
    }

    public void insertSanPhamKhuyenMai() {
        int rowKhuyenMai = tblKhuyenMai.getSelectedRow();
        if (rowKhuyenMai < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khuyến mại");
            return;
        }
        List<SanPhamViewKMResponse> listSPTrue = new ArrayList<>();
        List<SanPhamViewKMResponse> listSPFales = new ArrayList<>();
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            Boolean t = (Boolean) tblSanPham.getValueAt(i, 9);
            if (t != null) { //TH mở lần đầu mà k chọn gì nó sẽ trả về null nên so sánh với true thì nó sai
                if (t == true) {
                    listSPTrue.add(listSanPhamResponsesReal.get(i));
                } else {
                    listSPFales.add(listSanPhamResponsesReal.get(i));
                }
            }
        }
        KhuyenMai km = listKhuyenMais.get(rowKhuyenMai);
        khuyenMaiServiceImpl.deleteSanPhamKhuyenMaiByMa(listSPFales, km);
        khuyenMaiServiceImpl.insertSanPhamKhuyenMai(listSPTrue, km);
        JOptionPane.showMessageDialog(this, "Áp dụng thành công");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGiaTri = new javax.swing.JTextField();
        txtTenKM = new javax.swing.JTextField();
        cboLoaiKM = new javax.swing.JComboBox<>();
        txtNgayBatDau = new com.github.lgooddatepicker.components.DatePicker();
        txtNgayKetThuc = new com.github.lgooddatepicker.components.DatePicker();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtTimKiemKhuyenMai = new javax.swing.JTextField();
        cboLocTrangThai = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cboAll = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1300, 850));
        setMinimumSize(new java.awt.Dimension(1300, 850));
        setPreferredSize(new java.awt.Dimension(1300, 850));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel1.setText("Tên khuyến mại");
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        jLabel2.setText("Loại khuyến mại");
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        jLabel3.setText("Giá trị");
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        jLabel5.setText("Ngày bắt đầu");
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        jLabel6.setText("Ngày kết thúc");
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        cboLoaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "%", "Tiền" }));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBackground(new java.awt.Color(40, 184, 213));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBackground(new java.awt.Color(40, 184, 213));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel9.setText("Trạng thái");
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang áp dụng", "Ngừng áp dụng" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaTri, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboLoaiKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách khuyến Mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 204))); // NOI18N

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Loại KM", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        jLabel7.setText("Tìm khuyến mại:");
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        txtTimKiemKhuyenMai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemKhuyenMai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemKhuyenMaiCaretUpdate(evt);
            }
        });

        cboLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang áp dụng", "Ngừng áp dụng" }));
        cboLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTrangThaiActionPerformed(evt);
            }
        });

        jLabel11.setText("Lọc trạng thái");
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTimKiemKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Khuyến Mại");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel10.setText("Tìm sản phẩm:");
        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        txtTimKiemSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemSanPham.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSanPhamCaretUpdate(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "CPU", "Hãng", "Ram", "Card", "Ổ Cứng", "Giá", "Số lượng", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        jButton1.setText("Áp dụng khuyến mại");
        jButton1.setBackground(new java.awt.Color(40, 184, 213));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboAll.setText("Tất cả");
        cboAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboAll)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboAll))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sale.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel12)))
                        .addGap(34, 34, 34)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insertKhuyenMai();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        showData();
        cboAll.setSelected(false);
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        updateKM();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertSanPhamKhuyenMai();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemKhuyenMaiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemKhuyenMaiCaretUpdate
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText(), cboLocTrangThai.getSelectedIndex() == 0 ? 2 : (cboLocTrangThai.getSelectedIndex() == 1) ? 1 : 0);
        fillToTableKhuyenMai();
    }//GEN-LAST:event_txtTimKiemKhuyenMaiCaretUpdate

    private void txtTimKiemSanPhamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamCaretUpdate
        listSanPhamResponses = khuyenMaiServiceImpl.getAllSanPham(txtTimKiemSanPham.getText());
        listSanPhamResponsesReal = listSanPhamResponses;
        fillToTableSanPham();
    }//GEN-LAST:event_txtTimKiemSanPhamCaretUpdate

    private void cboAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAllActionPerformed
        if (cboAll.isSelected()) {
            for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                tblSanPham.setValueAt(true, i, 9);
            }
        } else {
            for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                tblSanPham.setValueAt(false, i, 9);
            }
        }

    }//GEN-LAST:event_cboAllActionPerformed

    private void cboLocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocTrangThaiActionPerformed
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText(), cboLocTrangThai.getSelectedIndex() == 0 ? 2 : (cboLocTrangThai.getSelectedIndex() == 1) ? 1 : 0);
        fillToTableKhuyenMai();
    }//GEN-LAST:event_cboLocTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JCheckBox cboAll;
    private javax.swing.JComboBox<String> cboLoaiKM;
    private javax.swing.JComboBox<String> cboLocTrangThai;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaTri;
    private com.github.lgooddatepicker.components.DatePicker txtNgayBatDau;
    private com.github.lgooddatepicker.components.DatePicker txtNgayKetThuc;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTimKiemKhuyenMai;
    private javax.swing.JTextField txtTimKiemSanPham;
    // End of variables declaration//GEN-END:variables
}
