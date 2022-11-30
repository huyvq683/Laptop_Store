/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import custommodel.SanPhamViewKMResponse;
import domainmodel.KhuyenMai;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    /**
     * Creates new form ViewKhuyenMai
     */
    public PanelKhuyenMai() {
        initComponents();
        khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText());
        listSanPhamResponses = khuyenMaiServiceImpl.getAllSanPham(txtTimKiemSanPham.getText());
        txtNgayBatDau.getSettings().setAllowKeyboardEditing(false);
        txtNgayKetThuc.getSettings().setAllowKeyboardEditing(false);
        fillToTableKhuyenMai();
        fillToTableSanPham();
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

    public void insertKhuyenMai() {
        try {
            KhuyenMai km = new KhuyenMai();
            km.setMa(khuyenMaiServiceImpl.genMaHD() + "");
            km.setTenKM(txtTenKM.getText());
            km.setLoaiKM(cboLoaiKM.getSelectedIndex() == 0 ? 0 : 1); //Tiền là số 1 % chỉ là số 0
            km.setGiaTriKM(txtGiaTri.getText());
            km.setNgayBD(getDate(txtNgayBatDau.getDateStringOrEmptyString()));
            km.setNgayKT(getDate(txtNgayKetThuc.getDateStringOrEmptyString()));
            km.setTrangThai(cboTrangThai.getSelectedIndex() == 0 ? 1 : 0); //Đang hoạt động là 1 không hoạt động là 0
            km.setCreatedDate(new Date());
            km.setLastModifiedDate(new Date());
            JOptionPane.showMessageDialog(this, khuyenMaiServiceImpl.insertOrUpdateKhuyenMai(km));
            listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText());
            fillToTableKhuyenMai();
        } catch (Exception e) {
        }
    }

    public void showData() {
        int rowKM = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = listKhuyenMais.get(rowKM);
        txtTenKM.setText(km.getTenKM());
        cboLoaiKM.setSelectedIndex(km.getLoaiKM() == 0 ? 0 : 1);
        txtGiaTri.setText(km.getGiaTriKM());
        txtNgayBatDau.setText(showDate(km.getNgayBD()));
        txtNgayKetThuc.setText(showDate(km.getNgayKT()));
        cboTrangThai.setSelectedIndex(km.getTrangThai() == 0 ? 1 : 0);
    }

    public void updateKM() {
        int rowKM = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = listKhuyenMais.get(rowKM);
        km.setTenKM(txtTenKM.getText());
        km.setLoaiKM(cboLoaiKM.getSelectedIndex() == 0 ? 0 : 1); //Tiền là số 1 % chỉ là số 0
        km.setGiaTriKM(txtGiaTri.getText());
        km.setNgayBD(getDate(txtNgayBatDau.getDateStringOrEmptyString()));
        km.setNgayKT(getDate(txtNgayKetThuc.getDateStringOrEmptyString()));
        km.setTrangThai(cboTrangThai.getSelectedIndex() == 0 ? 1 : 0); //Đang hoạt động là 1 không hoạt động là 0
        km.setLastModifiedDate(new Date());
        khuyenMaiServiceImpl.insertOrUpdateKhuyenMai(km);
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText());
        fillToTableKhuyenMai();
    }

    public void fillToTableKhuyenMai() {
        tableModel = (DefaultTableModel) tblKhuyenMai.getModel();
        tableModel.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (KhuyenMai km : listKhuyenMais) {
            tableModel.addRow(new Object[]{
                "KM" + km.getMa(), km.getTenKM(), km.getLoaiKM() == 1 ? "Tiền" : "%", km.getGiaTriKM(),
                formatter.format(km.getNgayBD()), formatter.format(km.getNgayKT()), km.getTrangThai() == 1 ? "Đang áp dụng" : "Ngừng áp dụng", Boolean.TYPE
            });
        }
    }

    public void fillToTableSanPham() {
        tableModel = (DefaultTableModel) tblSanPham.getModel();
        tableModel.setRowCount(0);
        for (SanPhamViewKMResponse sp : listSanPhamResponses) {
            tableModel.addRow(new Object[]{
                sp.getMa(), sp.getTen(), sp.getCpu(), sp.getHang(), sp.getRam(),
                sp.getCard(), sp.getOCung(), sp.getGia(), sp.getSoLuong(),});
        }
    }

    public void insertSanPhamKhuyenMai() {
        List<String> listMaSP = new ArrayList<>();
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            Boolean t = (Boolean) tblSanPham.getValueAt(i, 9);
            if (t != null) { //TH mở lần đầu mà k chọn gì nó sẽ trả về null nên so sánh với true thì nó sai
                if (t == true) {
                    listMaSP.add(String.valueOf(tblSanPham.getValueAt(i, 0)));
                }
            }
        }
        int rowKhuyenMai = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = listKhuyenMais.get(rowKhuyenMai);
        if (listMaSP.isEmpty()) {
            khuyenMaiServiceImpl.deleteSanPhamKhuyenMaiByMa(km.getMa());
        } else {
            khuyenMaiServiceImpl.insertSanPhamKhuyenMai(listMaSP, km);
        }
        JOptionPane.showMessageDialog(this, "Áp dụng thành công");
    }

    public void insertKm() {

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGiaTri = new javax.swing.JTextField();
        txtTenKM = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboLoaiKM = new javax.swing.JComboBox<>();
        txtNgayBatDau = new com.github.lgooddatepicker.components.DatePicker();
        txtNgayKetThuc = new com.github.lgooddatepicker.components.DatePicker();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtTimKiemKhuyenMai = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1254, 850));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Tên khuyến mại");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setText("Loại khuyến mại");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setText("Giá trị");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setText("Trạng thái");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setText("Ngày bắt đầu");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setText("Ngày kết thúc");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang áp dụng", "Ngừng áp dụng" }));

        cboLoaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "%", "Tiền" }));

        btnThem.setText("Thêm");
        btnThem.setBackground(new java.awt.Color(40, 184, 213));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBackground(new java.awt.Color(40, 184, 213));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel12.setText("Khuyến Mại");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCapNhat))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtGiaTri, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboLoaiKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel12)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel12)
                .addGap(113, 113, 113)
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
                    .addComponent(jLabel4)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(242, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Loại KM", "Giá Trị", "Ngày Bắ Đầu", "Ngày Kết thúc", "Trạng Thái"
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

        jLabel7.setText("Tìm khuyến mại");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtTimKiemKhuyenMai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemKhuyenMaiCaretUpdate(evt);
            }
        });

        jLabel10.setText("Tìm sản phẩm");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

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

        jButton1.setText("Áp dụng khuyến mại");
        jButton1.setBackground(new java.awt.Color(40, 184, 213));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTimKiemKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(217, 217, 217)))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insertKhuyenMai();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        showData();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        updateKM();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertSanPhamKhuyenMai();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemKhuyenMaiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemKhuyenMaiCaretUpdate
        listKhuyenMais = khuyenMaiServiceImpl.getAllKhuyenMai(txtTimKiemKhuyenMai.getText());
        fillToTableKhuyenMai();
    }//GEN-LAST:event_txtTimKiemKhuyenMaiCaretUpdate

    private void txtTimKiemSanPhamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamCaretUpdate
        listSanPhamResponses = khuyenMaiServiceImpl.getAllSanPham(txtTimKiemSanPham.getText());
        fillToTableSanPham();
    }//GEN-LAST:event_txtTimKiemSanPhamCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboLoaiKM;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
