/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import custommodel.ChiTietSPResponse;
import custommodel.HoaDonCHiTietInResponse;
import custommodel.HoaDonChiTietResponse;
import custommodel.HoaDonInResponse;
import custommodel.HoaDonResponse;
import custommodel.KhachHangReponse;
import domainmodel.ChiTietSP;
import domainmodel.HoaDon;
import domainmodel.KhachHang;
import domainmodel.NhanVien;
import domainmodel.Common;
import entity.InHoaDon;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
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
public class PanelBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {
    
    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private List<HoaDonResponse> listHoaDon = hoaDonService.getAllHoaDon(Common.tenNV);
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
    private DefaultTableModel dtmHDCT = new DefaultTableModel();
    private DefaultTableModel dtmMaGG = new DefaultTableModel();
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Webcam webcam = null;

    /**
     * Creates new form ViewBanHang
     */
    public PanelBanHang() {
        initComponents();
        tbHoaDon.setModel(dtmHoaDon);
        String[] headeroaDon = {"STT", "Mã HĐ", "Ngày tạo", "Tên NV", "Tình trạng"};
        dtmHoaDon.setColumnIdentifiers(headeroaDon);
        tbGioHang.setModel(dtmGioHang);
        String[] headerGioHang = {"STT", "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Giảm giá", "Thành tiền"};
        dtmGioHang.setColumnIdentifiers(headerGioHang);
        tbSanPham.setModel(dtmSanPham);
        String[] header = {"STT", "Mã", "Tên", "CPU", "Ram", "Card", "Ổ cứng", "Hãng", "Gia", "Số lượng"};
        dtmSanPham.setColumnIdentifiers(header);
        dtmSerial = (DefaultTableModel) tbSerial.getModel();
        showDataHoaDonTable(listHoaDon);
        showDataTableSanPham(listChiTietSP);
        enabled();
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtmHDCT = (DefaultTableModel) tbHDCT.getModel();
        showDataTable(listKH);
        initWebcam();
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
    
    private void showDataTableHDCT(UUID id) {
        listHoaDonChiTiet = hoaDonChiTietService.getList(id);
        dtmHDCT.setRowCount(0);
        for (HoaDonChiTietResponse hoaDonChiTietResponse : listHoaDonChiTiet) {
            dtmHDCT.addRow(hoaDonChiTietResponse.toDataRowHDCT());
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
    
    private void chonSP() {
        int rowSP = tbSanPham.getSelectedRow();
        ChiTietSPResponse chiTietSPResponse = listChiTietSP.get(rowSP);
        
        String ma = chiTietSPResponse.getMa();
        String ten = chiTietSPResponse.getTen();
        String cPU = chiTietSPResponse.getCPU();
        String hang = chiTietSPResponse.getHang();
        String ram = chiTietSPResponse.getRam();
        String card = chiTietSPResponse.getCard();
        String oCung = chiTietSPResponse.getOCung();
        BigDecimal gia = chiTietSPResponse.getGia();
        
        listChiTietSP = chiTietSPService.getAllCTSP(ma, ten, cPU, card, gia, hang, oCung, ram);
        showDataTableCTSP(listChiTietSP);
        
        ViewSerial.setVisible(true);
        ViewSerial.setLocationRelativeTo(null);
    }
    
    private void addHoaDonCT() {
        int rowHD = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
        HoaDon idHoaDon = hoaDonService.getByIdHoaDon(hoaDonResponse.getId());
        
        List<String> listSeral = new ArrayList<>();
        for (int i = 0; i < tbSerial.getRowCount(); i++) {
            Boolean t = (Boolean) tbSerial.getValueAt(i, 0);
            if (t == true) {
                listSeral.add(tbSerial.getValueAt(i, 3) + "");
            }
        }
        
        hoaDonChiTietService.add(listSeral, idHoaDon);
        showDataTableGioHang(hoaDonResponse.getId());
        
        chiTietSPService.updateTinhTrangSP(listSeral);
        listChiTietSP = chiTietSPService.getAll();
        showDataTableSanPham(listChiTietSP);
        
        serialDaBanService.add(listSeral);
        
    }
    
    private void fillThanhToan() {
        int row = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(row);
        txtMaHD.setText(hoaDonResponse.getMa());
        txtTenNV.setText(hoaDonResponse.getTenNhanVien());
        txtNgayTao.setText(dateFormat.format(hoaDonResponse.getNgayTao()));
        txtGiamGia.setText(String.valueOf(tienGiamGia(listHoaDonChiTiet)));
        txtTongTien.setText(String.valueOf(tongTien(listHoaDonChiTiet).subtract(tienGiamGia(listHoaDonChiTiet))));
    }
    
    private BigDecimal tongTien(List<HoaDonChiTietResponse> list) {
        BigDecimal tongTien = new BigDecimal(0);
        for (HoaDonChiTietResponse hoaDonChiTietResponse : list) {
            tongTien = tongTien.add(hoaDonChiTietResponse.thanhTien());
        }
        return tongTien;
    }
    
    private BigDecimal tienGiamGia(List<HoaDonChiTietResponse> list) {
        BigDecimal tienGiamGia = new BigDecimal(0);
        for (HoaDonChiTietResponse hoaDonChiTietResponse : list) {
            tienGiamGia = tienGiamGia.add((hoaDonChiTietResponse.getTienKM()).multiply(new BigDecimal(hoaDonChiTietResponse.getSoluong())));
        }
        return tienGiamGia;
    }
    
    public void fillThongTinKH() {
        int row = tbHienThi.getSelectedRow();
        KhachHangReponse kh = listKH.get(row);
        txtSDT.setText(kh.getSdt());
        txtTenKH.setText(kh.getTen());
    }
    
    private void initWebcam() {
        WebcamPanel panel = null;
        Executor executor = Executors.newSingleThreadExecutor(this);
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        Common.webcam = webcam;
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panelWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 219, 181));
        executor.execute(this);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        btnXacNhan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSearchKH = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDiaChiKH = new javax.swing.JTextField();
        txtHoTenKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        ViewSerial = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        btnChon = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbSerial = new javax.swing.JTable();
        cbChonTatCa = new javax.swing.JCheckBox();
        ViewHDCT = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        cbXoaHet = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        ViewMaGiamGia = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbMaGG = new javax.swing.JTable();
        btnMaGG = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        btnTaoHD = new javax.swing.JButton();
        radioDaTT = new javax.swing.JRadioButton();
        radioChoTT = new javax.swing.JRadioButton();
        radioHuy = new javax.swing.JRadioButton();
        radioTatCa = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        btnXoaAll = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnChonKH = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblRank = new javax.swing.JLabel();
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
        btnlamMoi = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        panelQR = new javax.swing.JPanel();
        panelWebcam = new javax.swing.JPanel();

        ViewKhachHang.setMinimumSize(new java.awt.Dimension(563, 370));
        ViewKhachHang.setResizable(false);
        ViewKhachHang.setSize(new java.awt.Dimension(563, 370));

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(563, 390));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(563, 390));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(563, 390));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMaximumSize(new java.awt.Dimension(563, 350));
        jPanel5.setMinimumSize(new java.awt.Dimension(563, 350));
        jPanel5.setPreferredSize(new java.awt.Dimension(563, 350));

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "SĐT", "Rank"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbHienThi);

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Danh sách khách hàng");

        jLabel14.setText("Tìm kiếm:");

        txtSearchKH.setForeground(new java.awt.Color(51, 51, 51));
        txtSearchKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));
        txtSearchKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchKHCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(184, 184, 184))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnXacNhan)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXacNhan)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chọn khách hàng", jPanel5);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel29.setText("Họ Tên");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel33.setText("Email");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel35.setText("Địa chỉ");

        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtDiaChiKH.setForeground(new java.awt.Color(51, 51, 51));
        txtDiaChiKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtHoTenKH.setForeground(new java.awt.Color(51, 51, 51));
        txtHoTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtSDTKH.setForeground(new java.awt.Color(51, 51, 51));
        txtSDTKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel36.setText("Số điện thoại");

        btnThem.setBackground(new java.awt.Color(41, 183, 212));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Thêm thông tin khách hàng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel29))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDTKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(159, 159, 159))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm khách hàng", jPanel9);

        javax.swing.GroupLayout ViewKhachHangLayout = new javax.swing.GroupLayout(ViewKhachHang.getContentPane());
        ViewKhachHang.getContentPane().setLayout(ViewKhachHangLayout);
        ViewKhachHangLayout.setHorizontalGroup(
            ViewKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewKhachHangLayout.setVerticalGroup(
            ViewKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ViewSerial.setMinimumSize(new java.awt.Dimension(607, 325));
        ViewSerial.setSize(new java.awt.Dimension(607, 325));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
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

        cbChonTatCa.setText("Chọn tất cả");
        cbChonTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChonTatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cbChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChon))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbChonTatCa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnChon)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewSerialLayout = new javax.swing.GroupLayout(ViewSerial.getContentPane());
        ViewSerial.getContentPane().setLayout(ViewSerialLayout);
        ViewSerialLayout.setHorizontalGroup(
            ViewSerialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewSerialLayout.setVerticalGroup(
            ViewSerialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ViewHDCT.setMinimumSize(new java.awt.Dimension(592, 325));
        ViewHDCT.setSize(new java.awt.Dimension(592, 325));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã", "Tên", "Giá", "Serial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbHDCT);
        if (tbHDCT.getColumnModel().getColumnCount() > 0) {
            tbHDCT.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbHDCT.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        cbXoaHet.setText("Chọn tất cả");
        cbXoaHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbXoaHetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbXoaHet, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnXoa)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cbXoaHet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewHDCTLayout = new javax.swing.GroupLayout(ViewHDCT.getContentPane());
        ViewHDCT.getContentPane().setLayout(ViewHDCTLayout);
        ViewHDCTLayout.setHorizontalGroup(
            ViewHDCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewHDCTLayout.setVerticalGroup(
            ViewHDCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ViewMaGiamGia.setMinimumSize(new java.awt.Dimension(621, 240));
        ViewMaGiamGia.setSize(new java.awt.Dimension(621, 240));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(621, 238));
        jPanel6.setMinimumSize(new java.awt.Dimension(621, 238));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel16.setText("Mã giảm giá");

        tbMaGG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tbMaGG);

        btnMaGG.setText("Chọn");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMaGG)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(244, 244, 244)
                            .addComponent(jLabel16))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMaGG)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewMaGiamGiaLayout = new javax.swing.GroupLayout(ViewMaGiamGia.getContentPane());
        ViewMaGiamGia.getContentPane().setLayout(ViewMaGiamGiaLayout);
        ViewMaGiamGiaLayout.setHorizontalGroup(
            ViewMaGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewMaGiamGiaLayout.setVerticalGroup(
            ViewMaGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        btnTaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/taoHoaDon.png"))); // NOI18N
        btnTaoHD.setToolTipText("Tạo hóa đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioDaTT);
        radioDaTT.setText("Đã thanh toán");
        radioDaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDaTTActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioChoTT);
        radioChoTT.setText("Chờ thanh toán");
        radioChoTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioChoTTActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioHuy);
        radioHuy.setText("Hủy");
        radioHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioHuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioTatCa);
        radioTatCa.setSelected(true);
        radioTatCa.setText("Tất cả");
        radioTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioTatCa)
                        .addGap(18, 18, 18)
                        .addComponent(radioDaTT)
                        .addGap(18, 18, 18)
                        .addComponent(radioChoTT)
                        .addGap(18, 18, 18)
                        .addComponent(radioHuy))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioDaTT)
                        .addComponent(radioChoTT)
                        .addComponent(radioHuy)
                        .addComponent(radioTatCa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
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

        btnXoaAll.setBackground(new java.awt.Color(41, 183, 212));
        btnXoaAll.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnXoaAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(btnXoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        txtSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(51, 51, 51));
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("SĐT:");

        btnChonKH.setBackground(new java.awt.Color(41, 183, 212));
        btnChonKH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnChonKH.setIcon(new ImageIcon("src/main/img/xacNhan.png"));
        btnChonKH.setText("Chọn ");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(51, 51, 51));
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Tên KH:");

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenKH.setForeground(new java.awt.Color(51, 51, 51));
        txtTenKH.setText("Khách lẻ");
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Rank:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT)
                            .addComponent(txtTenKH))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRank, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btnChonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRank, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGap(11, 11, 11))
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
        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenNV.setForeground(new java.awt.Color(51, 51, 51));
        txtTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtTongTien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(51, 51, 51));
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtTienKhachDua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTienKhachDua.setForeground(new java.awt.Color(51, 51, 51));
        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        txtTienCK.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTienCK.setForeground(new java.awt.Color(51, 51, 51));
        txtTienCK.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));
        txtTienCK.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienCKCaretUpdate(evt);
            }
        });
        txtTienCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCKActionPerformed(evt);
            }
        });

        txtTienTraLai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTienTraLai.setForeground(new java.awt.Color(51, 51, 51));
        txtTienTraLai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        btnThanhToan.setBackground(new java.awt.Color(41, 183, 212));
        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/thanhToan.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnlamMoi.setBackground(new java.awt.Color(41, 183, 212));
        btnlamMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnlamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
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
        txtGiamGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        txtNgayTao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNgayTao.setForeground(new java.awt.Color(51, 51, 51));
        txtNgayTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(40, 184, 213)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtGiamGia))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnlamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90))))))
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
                .addComponent(btnlamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelQR.setBackground(new java.awt.Color(255, 255, 255));
        panelQR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét QR sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        panelWebcam.setMaximumSize(new java.awt.Dimension(219, 181));
        panelWebcam.setMinimumSize(new java.awt.Dimension(219, 181));
        panelWebcam.setPreferredSize(new java.awt.Dimension(219, 181));
        panelWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panelQRLayout = new javax.swing.GroupLayout(panelQR);
        panelQR.setLayout(panelQRLayout);
        panelQRLayout.setHorizontalGroup(
            panelQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelQRLayout.setVerticalGroup(
            panelQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
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
                            .addComponent(panelQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = new HoaDon();
        int maHD = hoaDonService.genMaHD();
        hoaDon.setMa("HD" + maHD);
        hoaDon.setNgayTao((new Date()));
        NhanVien nhanVien = nhanVienService.getOne(Common.tenNV.getEmail());
        hoaDon.setIdNV(nhanVien);
        hoaDon.setTinhTrang(0);
        hoaDon.setCreatedDate(new Date());
        hoaDon.setLastModifiedDate(new Date());
        JOptionPane.showMessageDialog(this, hoaDonService.add(hoaDon));
        listHoaDon = hoaDonService.getAllHoaDon(Common.tenNV);
        showDataHoaDonTable(listHoaDon);
        txtTenKH.setText("Khách lẻ");
        tbHoaDon.setRowSelectionInterval(0, 0);
        listHoaDonChiTiet.clear();
        showDataTableGioHang(hoaDon.getId());
        btnThanhToan.setEnabled(true);
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(row);
        if (hoaDonResponse.getTinhTrang() == 1 || hoaDonResponse.getTinhTrang() == 2) {
            btnThanhToan.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
        }
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

    private void btnXoaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllActionPerformed
        // TODO add your handling code here:
        int rowHD = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
        showDataTableHDCT(hoaDonResponse.getId());
        ViewHDCT.setVisible(true);
        ViewHDCT.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnXoaAllActionPerformed

    private void btnlamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlamMoiActionPerformed
        // TODO add your handling code here:
        cbbHinhThuc.setSelectedIndex(0);
        txtTienKhachDua.setText("");
        txtTienCK.setText("");
        txtTienTraLai.setText("");
    }//GEN-LAST:event_btnlamMoiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (txtTienKhachDua.getText().trim().isBlank() && cbbHinhThuc.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa trống");
        } else if (!(txtTienKhachDua.getText().matches("\\d+")) || Integer.valueOf(txtTienKhachDua.getText()) < 0) {
            if (cbbHinhThuc.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số và lớn hơn 0");
            }
        } else if (txtTienCK.getText().trim().isBlank() && cbbHinhThuc.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(this, "Tiền khách chuyển khoản trống");
        } else if (cbbHinhThuc.getSelectedIndex() == 1) {
            if (!(txtTienCK.getText().matches("\\d+")) || Integer.valueOf(txtTienCK.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Tiền khách chuyển khoản phải là số và lớn hơn 0");
            }
        } else {
            int row = tbHoaDon.getSelectedRow();
            HoaDonResponse hd = listHoaDon.get(row);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(hd.getId());
            hoaDon.setMa(hd.getMa());
            hoaDon.setNgayTao(hd.getNgayTao());
            hoaDon.setIdNV(Common.tenNV);
            hoaDon.setHinhThuc((int) cbbHinhThuc.getSelectedIndex());
            switch (cbbHinhThuc.getSelectedIndex()) {
                case 0:
                    hoaDon.setTienKhachTra(new BigDecimal(txtTienKhachDua.getText()));
                    hoaDon.setTienCK(new BigDecimal(0));
                    break;
                case 1:
                    hoaDon.setTienKhachTra(new BigDecimal(0));
                    hoaDon.setTienCK(new BigDecimal(txtTienCK.getText()));
                    break;
                default:
                    hoaDon.setTienKhachTra(new BigDecimal(txtTienKhachDua.getText()));
                    hoaDon.setTienCK(new BigDecimal(txtTienCK.getText()));
                    break;
            }
            hoaDon.setTienThua(new BigDecimal(txtTienTraLai.getText()));
            hoaDon.setTongTien(new BigDecimal(txtTongTien.getText()));
            hoaDon.setTinhTrang(1);
            hoaDon.setCreatedDate(new Date());
            hoaDon.setLastModifiedDate(new Date());
            if (txtTenKH.getText().equalsIgnoreCase("Khách lẻ")) {
                hoaDon.setIdKH(null);
            } else {
                int rowKH = tbHienThi.getSelectedRow();
                KhachHangReponse kh = listKH.get(rowKH);
                KhachHang khachHang = khachHangService.getIdKhachHang(kh.getId());
                hoaDon.setIdKH(khachHang);
            }
            JOptionPane.showMessageDialog(this, hoaDonService.updateTrangThai(hoaDon));
            if (JOptionPane.showConfirmDialog(this, "Bạn muốn in hóa đơn không") == JOptionPane.YES_OPTION) {
                HoaDonInResponse hdin = new HoaDonInResponse();
                hdin.setMaHD(hd.getMa());
                if (txtTenKH.getText().equalsIgnoreCase("Khách lẻ")) {
                    hdin.setTenKH(" Khách lẻ");
                    hdin.setDiaChi(" Không");
                    hdin.setSdtKH(" Không");
                } else {
                    int rowKH = tbHienThi.getSelectedRow();
                    KhachHangReponse kh = listKH.get(rowKH);
                    KhachHang khachHang = khachHangService.getIdKhachHang(kh.getId());
                    hdin.setTenKH(txtTenKH.getText());
                    hdin.setDiaChi(khachHang.getDiaChi());
                    hdin.setSdtKH(txtSDT.getText());
                }
                hdin.setTenNV(Common.tenNV.getHoTen());
                hdin.setTongTienTam(tongTien(listHoaDonChiTiet));
                hdin.setTongTien(new BigDecimal(txtTongTien.getText()));
                hdin.setGiamGia(new BigDecimal(txtGiamGia.getText()));
                hdin.setTienTraLai(new BigDecimal(txtTienTraLai.getText()));
                hdin.setHinhThucThanhToan((String) cbbHinhThuc.getSelectedItem());
                List<HoaDonCHiTietInResponse> ghin = new ArrayList<>();
                for (int i = 0; i < tbGioHang.getRowCount(); i++) {
                    HoaDonCHiTietInResponse gh = new HoaDonCHiTietInResponse();
                    gh.setTenSP(tbGioHang.getValueAt(i, 2).toString());
                    gh.setDonGia(new BigDecimal(tbGioHang.getValueAt(i, 3).toString()));
                    gh.setKhuyenMai(new BigDecimal(tbGioHang.getValueAt(i, 5).toString()));
                    gh.setSoLuong(Integer.parseInt(tbGioHang.getValueAt(i, 4).toString()));
                    gh.setThanhTien(new BigDecimal(Integer.parseInt(tbGioHang.getValueAt(i, 6).toString())));
                    ghin.add(gh);
                }
                if (InHoaDon.makePDF(hdin, ghin)) {
                    JOptionPane.showMessageDialog(this, "In hoá đơn thành công");
                }
            }
            listHoaDon = hoaDonService.getAllHoaDon(Common.tenNV);
            showDataHoaDonTable(listHoaDon);
            clear();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        // TODO add your handling code here:
        ViewKhachHang.setVisible(true);
        ViewKhachHang.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        addHoaDonCT();
        fillThanhToan();
        ViewSerial.dispose();
    }//GEN-LAST:event_btnChonActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int rowHD = tbHoaDon.getSelectedRow();
        HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
        
        List<String> listSeral = new ArrayList<>();
        for (int i = 0; i < tbHDCT.getRowCount(); i++) {
            Boolean t = (Boolean) tbHDCT.getValueAt(i, 0);
            if (t == true) {
                listSeral.add(tbHDCT.getValueAt(i, 4) + "");
            }
        }
        serialDaBanService.delete(listSeral);
        
        hoaDonChiTietService.deleteHDCT(listSeral);
        showDataTableHDCT(hoaDonResponse.getId());
        
        showDataTableGioHang(hoaDonResponse.getId());
        fillThanhToan();
        chiTietSPService.updateTinhTrangChuaBan(listSeral);
        listChiTietSP = chiTietSPService.getAll();
        showDataTableSanPham(listChiTietSP);
        ViewHDCT.dispose();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbChonTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChonTatCaActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tbSerial.getRowCount(); i++) {
            if (cbChonTatCa.isSelected()) {
                tbSerial.setValueAt(true, i, 0);
            } else {
                tbSerial.setValueAt(false, i, 0);
            }
            
        }
    }//GEN-LAST:event_cbChonTatCaActionPerformed

    private void cbXoaHetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbXoaHetActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tbHDCT.getRowCount(); i++) {
            if (cbXoaHet.isSelected()) {
                tbHDCT.setValueAt(true, i, 0);
            } else {
                tbHDCT.setValueAt(false, i, 0);
            }
        }
    }//GEN-LAST:event_cbXoaHetActionPerformed

    private void txtTienCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCKActionPerformed
        // TODO add your handling code here:
        BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDua.getText());
        BigDecimal tienCK = new BigDecimal(txtTienCK.getText());
        BigDecimal tienThua = (tienKhachDua.add(tienCK)).subtract(new BigDecimal(txtTongTien.getText()));
        txtTienTraLai.setText(String.valueOf(tienThua));
    }//GEN-LAST:event_txtTienCKActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        int row = tbHienThi.getSelectedRow();
        KhachHangReponse khr = listKH.get(row);
        txtSDT.setText(khr.getSdt());
        txtTenKH.setText(khr.getTen());
        long tienKM = Long.valueOf(txtTongTien.getText());
        long giamGia = Long.valueOf(txtGiamGia.getText());
        switch (khr.getCapBac()) {
            case 0:
                lblRank.setIcon(new ImageIcon(""));
                lblRank.setText("Chưa có rank");
                break;
            case 1:
                lblRank.setIcon(new ImageIcon(getClass().getResource("/img/dong.png")));
                lblRank.setText("Đồng");
                txtGiamGia.setText(String.valueOf((tienKM * 5 / 100) + giamGia));
                txtTongTien.setText(String.valueOf(tienKM - (tienKM * 5 / 100)));
                break;
            case 2:
                lblRank.setIcon(new ImageIcon(getClass().getResource("/img/bac.png")));
                lblRank.setText("Bạc");
                txtGiamGia.setText(String.valueOf((tienKM * 8 / 100) + giamGia));
                txtTongTien.setText(String.valueOf(tienKM - (tienKM * 8 / 100)));
                break;
            case 3:
                lblRank.setIcon(new ImageIcon(getClass().getResource("/img/vang.png")));
                lblRank.setText("Vàng");
                txtGiamGia.setText(String.valueOf((tienKM * 10 / 100) + giamGia));
                txtTongTien.setText(String.valueOf(tienKM - (tienKM * 10 / 100)));
                break;
            default:
                lblRank.setIcon(new ImageIcon(getClass().getResource("/img/kimcuong.png")));
                lblRank.setText("Kim cương");
                txtGiamGia.setText(String.valueOf((tienKM * 15 / 100) + giamGia));
                txtTongTien.setText(String.valueOf(tienKM - (tienKM * 15 / 100)));
                break;
        }
        ViewKhachHang.dispose();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // Add KhachHang
        KhachHang kh = new KhachHang();
        kh.setMa("KH" + (listKH.size() + 1));
        kh.setHoTen(txtHoTenKH.getText());
        kh.setSdt(txtSDTKH.getText());
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChiKH.getText());
        kh.setCreatedDate(new Date());
        kh.setLastModifiedDate(new Date());
        JOptionPane.showMessageDialog(this, khachHangService.add(kh));
        listKH = khachHangService.getListKH();
        showDataTable(listKH);
    }//GEN-LAST:event_btnThemActionPerformed

    private void radioTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTatCaActionPerformed
        // TODO add your handling code here:
        listHoaDon.removeAll(listHoaDon);
        listHoaDon = hoaDonService.getAll();
        showDataHoaDonTable(listHoaDon);
    }//GEN-LAST:event_radioTatCaActionPerformed

    private void radioDaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDaTTActionPerformed
        // TODO add your handling code here:
        listHoaDon.removeAll(listHoaDon);
        listHoaDon = hoaDonService.getByOne(1);
        showDataHoaDonTable(listHoaDon);
        btnThanhToan.setEnabled(false);
    }//GEN-LAST:event_radioDaTTActionPerformed

    private void radioChoTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioChoTTActionPerformed
        // TODO add your handling code here:
        listHoaDon.removeAll(listHoaDon);
        listHoaDon = hoaDonService.getByOne(0);
        showDataHoaDonTable(listHoaDon);
        btnThanhToan.setEnabled(true);
    }//GEN-LAST:event_radioChoTTActionPerformed

    private void radioHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHuyActionPerformed
        // TODO add your handling code here:
        listHoaDon.removeAll(listHoaDon);
        listHoaDon = hoaDonService.getByOne(2);
        showDataHoaDonTable(listHoaDon);
        btnThanhToan.setEnabled(false);
    }//GEN-LAST:event_radioHuyActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
        String ma = txtSearch.getText();
        List<ChiTietSPResponse> listSearch = chiTietSPService.searchCTSP(ma);
        showDataTableSanPham(listSearch);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        // TODO add your handling code here:
        if (cbbHinhThuc.getSelectedIndex() == 0) {
            if (!txtTienKhachDua.getText().isEmpty()) {
                BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDua.getText());
                BigDecimal tienThua = tienKhachDua.subtract(new BigDecimal(txtTongTien.getText()));
                txtTienTraLai.setText(String.valueOf(tienThua));
            }
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtTienCKCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienCKCaretUpdate
        // TODO add your handling code here:
        if (cbbHinhThuc.getSelectedIndex() == 1) {
            if (!txtTienCK.getText().isEmpty()) {
                BigDecimal tienCK = new BigDecimal(txtTienCK.getText());
                BigDecimal tienThua = tienCK.subtract(new BigDecimal(txtTongTien.getText()));
                txtTienTraLai.setText(String.valueOf(tienThua));
            }
        } else if (cbbHinhThuc.getSelectedIndex() == 2) {
            if (!txtTienCK.getText().isEmpty()) {
                BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDua.getText());
                BigDecimal tienCK = new BigDecimal(txtTienCK.getText());
                BigDecimal tienThua = (tienKhachDua.add(tienCK)).subtract(new BigDecimal(txtTongTien.getText()));
                txtTienTraLai.setText(String.valueOf(tienThua));
            }
        }
    }//GEN-LAST:event_txtTienCKCaretUpdate

    private void txtSearchKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchKHCaretUpdate
        // TODO add your handling code here:
        String sdt = txtSearchKH.getText();
        List<KhachHangReponse>listSearch = khachHangService.searchKH(sdt);
        showDataTable(listSearch);
    }//GEN-LAST:event_txtSearchKHCaretUpdate
    
    private void clear() {
        txtMaHD.setText("");
        txtTenNV.setText("");
        txtGiamGia.setText("");
        txtNgayTao.setText("");
        txtTongTien.setText("");
        txtTienKhachDua.setText("");
        txtTienCK.setText("");
        txtTienTraLai.setText("");
        cbbHinhThuc.setSelectedIndex(0);
        txtSDT.setText("");
        txtTenKH.setText("Khách lẻ");
        lblRank.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame ViewHDCT;
    private javax.swing.JFrame ViewKhachHang;
    private javax.swing.JFrame ViewMaGiamGia;
    private javax.swing.JFrame ViewSerial;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnMaGG;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaAll;
    private javax.swing.JButton btnlamMoi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbChonTatCa;
    private javax.swing.JCheckBox cbXoaHet;
    private javax.swing.JComboBox<String> cbbHinhThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblRank;
    private javax.swing.JPanel panelQR;
    private javax.swing.JPanel panelWebcam;
    private javax.swing.JRadioButton radioChoTT;
    private javax.swing.JRadioButton radioDaTT;
    private javax.swing.JRadioButton radioHuy;
    private javax.swing.JRadioButton radioTatCa;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbMaGG;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbSerial;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtHoTenKH;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTienCK;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienTraLai;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
            }
            
            Result result = null;
            BufferedImage image = null;
            
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }
            if (result != null) {
                try {
                    int rowHD = tbHoaDon.getSelectedRow();
                    HoaDonResponse hoaDonResponse = listHoaDon.get(rowHD);
                    String serial = result.toString();
                    System.out.println(serial);
                    HoaDon idHoaDon = hoaDonService.getByIdHoaDon(hoaDonResponse.getId());
                    ChiTietSP chiTietSP = chiTietSPService.getBySerialChiTietSP(serial);
                    chiTietSP.setTinhTrang(1);
                    chiTietSPService.updateTinhTrang(chiTietSP);
                    listChiTietSP = chiTietSPService.getAll();
                    showDataTableSanPham(listChiTietSP);
                    List<String> listSeral = new ArrayList<>();
                    listSeral.add(serial);
                    hoaDonChiTietService.add(listSeral, idHoaDon);
                    showDataTableGioHang(hoaDonResponse.getId());
                } catch (Exception e) {
                }
            }
        } while (true);
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
