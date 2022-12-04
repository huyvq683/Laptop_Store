/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import custommodel.ChiTietSPResponse;
import custommodel.HoaDonChiTietResponse;
import custommodel.HoaDonResponse;
import custommodel.KhachHangReponse;
import domainmodel.ChiTietSP;
import domainmodel.HoaDon;
import domainmodel.KhachHang;
import domainmodel.NhanVien;
import domainmodel.TenTKNV;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.ChiTietSPService;
import service.HoaDonChiTietService;
import service.HoaDonService;
import service.KhachHangService;
import service.NhanVienService;
import service.SerialDaBanService;
import service.impl.ChiTietSPServiceImql;
import service.impl.HoaDonChiTietSeviceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SerialDaBanServiceImpl;

/**
 *
 * @author FPT
 */
public class PanelBanHang extends javax.swing.JPanel {

    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private List<HoaDonResponse> listHoaDon = hoaDonService.getAll(TenTKNV.tenNV);
    private DefaultTableModel dtmGioHang = new DefaultTableModel();
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietSeviceImpl();
    private List<HoaDonChiTietResponse> listHoaDonChiTiet = new ArrayList<>();
    private DefaultTableModel dtmSanPham = new DefaultTableModel();
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImql();
    private List<ChiTietSPResponse> listChiTietSP = chiTietSPService.getAll();
    private NhanVienService nhanVienService = new NhanVienServiceImpl();
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private SerialDaBanService serialDaBanService = new SerialDaBanServiceImpl();
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<KhachHangReponse> listKH = khachHangService.getListKH();
    private DefaultTableModel dtmSerial = new DefaultTableModel();
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form ViewBanHang
     */
    public PanelBanHang() {
        initComponents();
        tbHoaDon.setModel(dtmHoaDon);
        String[] headeroaDon = {"STT", "Mã HĐ", "Ngày tạo", "Tên NV", "Tình trạng"};
        dtmHoaDon.setColumnIdentifiers(headeroaDon);
        tbGioHang.setModel(dtmGioHang);
        String[] headerGioHang = {"STT", "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền"};
        dtmGioHang.setColumnIdentifiers(headerGioHang);
        tbSanPham.setModel(dtmSanPham);
        String[] header = {"STT", "Mã", "Tên", "CPU", "Ram", "Card", "Ổ cứng", "Hãng", "Gia", "Số lượng"};
        dtmSanPham.setColumnIdentifiers(header);
        dtmSerial = (DefaultTableModel) tbSerial.getModel();
        showDataHoaDonTable(listHoaDon);
        showDataTableSanPham(listChiTietSP);
        enabled();
        dtm = (DefaultTableModel) tbHienThi.getModel();
        showDataTable(listKH);
    }

    private void enabled() {
        btnThanhToan.setEnabled(false);
        txtMaHD.setEditable(false);
        txtNgayTao.setEditable(false);
        txtGiamGia.setEditable(false);
        txtTenNV.setEditable(false);
        txtTongTien.setEditable(false);
        txtTienTraLai.setEditable(false);
    }

    private void showDataHoaDonTable(List<HoaDonResponse> lists) {
        dtmHoaDon.setRowCount(0);
        int stt = 0;
        for (HoaDonResponse hoaDonResponse : lists) {
            stt++;
            dtmHoaDon.addRow(hoaDonResponse.toDataRow(stt));
        }
    }

    private void showDataTableGioHang(UUID id) {
        listHoaDonChiTiet = hoaDonChiTietService.getAll(id);
        dtmGioHang.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : listHoaDonChiTiet) {
            stt++;
            dtmGioHang.addRow(hoaDonChiTietResponse.toDataRow(stt));
        }
    }

    private void showDataTableSanPham(List<ChiTietSPResponse> lists) {
        dtmSanPham.setRowCount(0);
        int stt = 0;
        for (ChiTietSPResponse chiTietSPResponse : lists) {
            stt++;
            dtmSanPham.addRow(chiTietSPResponse.toDataRow(stt));
        }
    }

    private void showDataTable(List<KhachHangReponse> lists) {
        dtm.setRowCount(0);
        lists.forEach(s -> dtm.addRow(s.toDataRow()));
    }

    private void showDataTableCTSP(List<ChiTietSPResponse> lists) {
        dtmSerial.setRowCount(0);
        lists.forEach(s -> dtmSerial.addRow(s.toDataRowSerial()));
    }

    private HoaDonChiTietResponse getData() {
        int rowSP = tbSanPham.getSelectedRow();
        ChiTietSPResponse chiTietSPResponse = listChiTietSP.get(rowSP);
        int rowHD = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
        HoaDonChiTietResponse hoaDonChiTietResponse = new HoaDonChiTietResponse();
        hoaDonChiTietResponse.setIdCTSP(chiTietSPResponse.getId());
        hoaDonChiTietResponse.setIdHD(hoaDonResponse.getId());
        hoaDonChiTietResponse.setMa(chiTietSPResponse.getMa());
        hoaDonChiTietResponse.setTen(chiTietSPResponse.getTen());
        hoaDonChiTietResponse.setGia(chiTietSPResponse.getGia());
        return hoaDonChiTietResponse;
    }

    private void chonSP() {
        int rowSP = tbSanPham.getSelectedRow();
        ChiTietSPResponse chiTietSPResponse = listChiTietSP.get(rowSP);
        
        String cPU = chiTietSPResponse.getCPU();
        String hang = chiTietSPResponse.getHang();
        String ram = chiTietSPResponse.getRam();
        String card = chiTietSPResponse.getCard();
        String oCung = chiTietSPResponse.getOCung();
        BigDecimal gia = chiTietSPResponse.getGia();
        
        listChiTietSP = chiTietSPService.getAllCTSP(cPU, card, gia, hang, oCung, ram);
//        showDataTableCTSP(listChiTietSP);
        ViewSerial.setVisible(true);
        ViewSerial.setLocationRelativeTo(null);
    }

    private void addHoaDonCT() {
//        HoaDonChiTietResponse hoaDonChiTietResponse = getData();
//        int rowHD = tbHoaDon.getSelectedRow();
//        HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
//        HoaDon idHoaDon = hoaDonService.getByIdHoaDon(hoaDonResponse.getId());
//        List<String> listSeral = new ArrayList<>();
//        for (int i = 0; i < tbSerial.getRowCount(); i++) {
//            Boolean t = (Boolean) tbSerial.getValueAt(i, 0);
//            if (t == true) {
//                listSeral.add(tbSerial.getValueAt(i, 3) + "");
//            }
//        }
//        hoaDonChiTietService.add(listSeral, idHoaDon);
//        showDataTableGioHang(hoaDonResponse.getId());
        

//        showDataTableGioHang(hoaDonResponse.getId());
//        SerialDaBan serialDaBan = new SerialDaBan();
//        serialDaBan.setIdHDCT(hoaDonChiTiet);
//        serialDaBan.setMa(serial);
//        serialDaBan.setCreatedDate(new Date());
//        serialDaBan.setAlstModifiedDate(new Date());
//        serialDaBanService.add(serialDaBan);
    }

    private void fillThanhToan() {
        int row = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(row);
        txtMaHD.setText(hoaDonResponse.getMa());
        txtTenNV.setText(hoaDonResponse.getTenNhanVien());
        txtNgayTao.setText(dateFormat.format(hoaDonResponse.getNgayTao()));
        txtTongTien.setText(String.valueOf(tongTien(listHoaDonChiTiet)));
        btnThanhToan.setEnabled(true);
    }

    private long tongTien(List<HoaDonChiTietResponse> list) {
        long tongTien = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : list) {
            tongTien += hoaDonChiTietResponse.getGia().doubleValue();
        }
        return tongTien;
    }

    public void fillThongTinKH() {
        int row = tbHienThi.getSelectedRow();
        KhachHangReponse kh = listKH.get(row);
        txtSDT.setText(kh.getSdt());
        txtTenKH.setText(kh.getTen());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewKhachHang = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        btnXacNhan1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ViewSerial = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbSerial = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        btnTaoHD = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbHinhThuc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienCK = new javax.swing.JTextField();
        txtTienTraLai = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnlamMoi = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();

        ViewKhachHang.setMinimumSize(new java.awt.Dimension(563, 350));
        ViewKhachHang.setSize(new java.awt.Dimension(563, 350));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(563, 328));
        jPanel6.setMinimumSize(new java.awt.Dimension(563, 328));

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbHienThi);

        btnXacNhan1.setText("Xác nhận");
        btnXacNhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhan1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Danh sách khách hàng");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnXacNhan1)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(185, 185, 185)
                            .addComponent(jLabel12))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXacNhan1)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewKhachHangLayout = new javax.swing.GroupLayout(ViewKhachHang.getContentPane());
        ViewKhachHang.getContentPane().setLayout(ViewKhachHangLayout);
        ViewKhachHangLayout.setHorizontalGroup(
            ViewKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewKhachHangLayout.setVerticalGroup(
            ViewKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ViewSerial.setMinimumSize(new java.awt.Dimension(607, 290));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Chọn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbSerial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã", "Tên", "Serial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbSerial);
        if (tbSerial.getColumnModel().getColumnCount() > 0) {
            tbSerial.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbSerial.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewSerialLayout = new javax.swing.GroupLayout(ViewSerial.getContentPane());
        ViewSerial.getContentPane().setLayout(ViewSerialLayout);
        ViewSerialLayout.setHorizontalGroup(
            ViewSerialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewSerialLayout.setVerticalGroup(
            ViewSerialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1310, 850));
        setMinimumSize(new java.awt.Dimension(1310, 850));
        setPreferredSize(new java.awt.Dimension(1290, 850));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Tên Nhân Viên"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDon);

        btnTaoHD.setBackground(new java.awt.Color(41, 183, 212));
        btnTaoHD.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnTaoHD.setIcon(new ImageIcon("src/main/img/addHD.png"));
        btnTaoHD.setText("Tạo hóa đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá"
            }
        ));
        jScrollPane3.setViewportView(tbGioHang);

        btnXoa.setBackground(new java.awt.Color(41, 183, 212));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnXoa.setIcon(new ImageIcon("src/main/img/delete.png"));
        btnXoa.setText("Xóa tất cả");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel42.setText("Tìm kiếm ");

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbSanPham);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("SĐT:");

        btnXacNhan.setBackground(new java.awt.Color(41, 183, 212));
        btnXacNhan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXacNhan.setIcon(new ImageIcon("src/main/img/xacNhan.png"));
        btnXacNhan.setText("Chọn ");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(51, 51, 51));
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Tên KH:");

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenKH.setForeground(new java.awt.Color(51, 51, 51));
        txtTenKH.setText("Khách mới");
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT)
                    .addComponent(txtTenKH))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Mã hóa đơn:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Tên NV:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Tổng tiền:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Ngày tạo:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Hình thức TT:");

        cbbHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Tiền mặt & Chuyển khoản" }));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Tiền khách đưa:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Tiền khách CK:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Tiền trả lại:");

        txtMaHD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMaHD.setForeground(new java.awt.Color(51, 51, 51));
        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenNV.setForeground(new java.awt.Color(51, 51, 51));
        txtTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        txtTongTien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(51, 51, 51));
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        txtTienKhachDua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTienKhachDua.setForeground(new java.awt.Color(51, 51, 51));
        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        txtTienCK.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTienCK.setForeground(new java.awt.Color(51, 51, 51));
        txtTienCK.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));
        txtTienCK.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienCKCaretUpdate(evt);
            }
        });

        txtTienTraLai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTienTraLai.setForeground(new java.awt.Color(51, 51, 51));
        txtTienTraLai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        btnThanhToan.setBackground(new java.awt.Color(41, 183, 212));
        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThanhToan.setIcon(new ImageIcon("src/main/img/thanhToan.png"));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(41, 183, 212));
        btnHuy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnHuy.setIcon(new ImageIcon("src/main/img/huy.png"));
        btnHuy.setText("Hủy hóa đơn");
        btnHuy.setPreferredSize(new java.awt.Dimension(110, 25));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnlamMoi.setBackground(new java.awt.Color(41, 183, 212));
        btnlamMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnlamMoi.setIcon(new ImageIcon("src/main/img/lamMoi.png"));
        btnlamMoi.setText("Làm mới");
        btnlamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlamMoiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Giảm giá:");

        txtGiamGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtGiamGia.setForeground(new java.awt.Color(51, 51, 51));
        txtGiamGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        txtNgayTao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNgayTao.setForeground(new java.awt.Color(51, 51, 51));
        txtNgayTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNV)
                            .addComponent(txtMaHD)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienKhachDua))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienCK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(32, 32, 32)
                        .addComponent(txtTienTraLai))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnlamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTienCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(txtTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét QR sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = new HoaDon();
        int maHD = hoaDonService.genMaHD();
        hoaDon.setMa("HD" + maHD);
        hoaDon.setNgayTao((new Date()));
        NhanVien nhanVien = nhanVienService.getOne(TenTKNV.tenNV.getEmail());
        hoaDon.setIdNV(nhanVien);
        hoaDon.setTinhTrang(0);
        JOptionPane.showMessageDialog(this, hoaDonService.add(hoaDon));
        listHoaDon = hoaDonService.getAll(TenTKNV.tenNV);
        showDataHoaDonTable(listHoaDon);
        tbHoaDon.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(row);
        showDataTableGioHang(hoaDonResponse.getId());
        fillThanhToan();
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn");
        } else {
            chonSP();
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tbGioHang.getSelectedRow();
        HoaDonChiTietResponse hdct = listHoaDonChiTiet.get(row);
        hoaDonChiTietService.delete(hdct.getIdHD());
        chiTietSPService.updateTTSPDangBan(hdct.getGia());
        int rowHD = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
        showDataTableGioHang(hoaDonResponse.getId());
        listChiTietSP = chiTietSPService.getAll();
        showDataTableSanPham(listChiTietSP);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnlamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlamMoiActionPerformed
        // TODO add your handling code here:
        cbbHinhThuc.setSelectedIndex(0);
        txtTienKhachDua.setText("");
        txtTienCK.setText("");
        txtTienTraLai.setText("");
    }//GEN-LAST:event_btnlamMoiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        String ma = txtSDT.getText();
        KhachHang khachHang = khachHangService.getMa(ma);
        int row = tbHoaDon.getSelectedRow();
        HoaDonResponse hd = listHoaDon.get(row);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(hd.getId());
        hoaDon.setMa(hd.getMa());
        hoaDon.setNgayTao(hd.getNgayTao());
        hoaDon.setIdNV(TenTKNV.tenNV);
        hoaDon.setHinhThuc((int) cbbHinhThuc.getSelectedIndex());
        hoaDon.setTienKhacTra(new BigDecimal(txtTienKhachDua.getText()));
        hoaDon.setTienCK(new BigDecimal(txtTienCK.getText()));
        hoaDon.setTienThua(new BigDecimal(txtTienTraLai.getText()));
        hoaDon.setTongTien(new BigDecimal(txtTongTien.getText()));
        hoaDon.setTinhTrang(1);
        hoaDon.setIdKH(khachHang);
        JOptionPane.showMessageDialog(this, hoaDonService.updateTrangThai(hoaDon));
        listHoaDon = hoaDonService.getAll(TenTKNV.tenNV);
        showDataHoaDonTable(listHoaDon);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(row);
        HoaDon hoaDon = new HoaDon();
        JOptionPane.showMessageDialog(this, hoaDonService.updateTrangThaiHuy(hoaDon, hoaDonResponse.getId()));
        listHoaDon = hoaDonService.getAll(TenTKNV.tenNV);
        showDataHoaDonTable(listHoaDon);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTienCKCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienCKCaretUpdate
        // TODO add your handling code here:
        double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
        double tienCK = Double.valueOf(txtTienCK.getText());
        double tienThua = (tienKhachDua + tienCK) - tongTien(listHoaDonChiTiet);
        txtTienTraLai.setText(String.valueOf(tienThua));
    }//GEN-LAST:event_txtTienCKCaretUpdate

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        ViewKhachHang.setVisible(true);
        ViewKhachHang.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnXacNhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhan1ActionPerformed
        // TODO add your handling code here:
        int row = tbHienThi.getSelectedRow();
        KhachHangReponse khr = listKH.get(row);
        txtSDT.setText(khr.getSdt());
        txtTenKH.setText(khr.getTen());
        ViewKhachHang.dispose();
    }//GEN-LAST:event_btnXacNhan1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addHoaDonCT();
        fillThanhToan();
        ViewSerial.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame ViewKhachHang;
    private javax.swing.JFrame ViewSerial;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXacNhan1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnlamMoi;
    private javax.swing.JComboBox<String> cbbHinhThuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbSerial;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTienCK;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienTraLai;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
