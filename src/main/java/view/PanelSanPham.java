/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.CPU;
import domainmodel.CardMH;
import domainmodel.ChiTietSP;
import domainmodel.Hang;
import domainmodel.OCung;
import domainmodel.Ram;
import domainmodel.SanPham;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.impl.ChiTietSPRepository;
import service.CPUService;
import service.CardService;
import service.ChiTietSPService;
import service.HangService;
import service.OCungService;
import service.RamService;
import service.SanPhamService;
import service.impl.CPUServiceImql;
import service.impl.CardServiceImql;
import service.impl.ChiTietSPServiceImql;
import service.impl.HangServiceImql;
import service.impl.OCungServiceImql;
import service.impl.RamServiceImql;
import service.impl.SanPhamServiceImpl;

/**
 *
 * @author huyxo
 */
public class PanelSanPham extends javax.swing.JPanel {

    DefaultComboBoxModel dfcbbSP = new DefaultComboBoxModel();
    DefaultComboBoxModel dfcbbCPU = new DefaultComboBoxModel();
    DefaultComboBoxModel dfcbbHang = new DefaultComboBoxModel();
    DefaultComboBoxModel dfcbbRam = new DefaultComboBoxModel();
    DefaultComboBoxModel dfcbbCard = new DefaultComboBoxModel();
    DefaultComboBoxModel dfcbbOCung = new DefaultComboBoxModel();

    DefaultTableModel modelSP = new DefaultTableModel();
    DefaultTableModel modelSP1 = new DefaultTableModel();
    DefaultTableModel modelCPU = new DefaultTableModel();
    DefaultTableModel modelRam = new DefaultTableModel();
    DefaultTableModel modelCard = new DefaultTableModel();
    DefaultTableModel modelHang = new DefaultTableModel();
    DefaultTableModel modelOCung = new DefaultTableModel();

    SanPhamService service = new SanPhamServiceImpl();
    List<SanPham> listSanPham = service.getAllSanPham();
    CPUService serviceCPU = new CPUServiceImql();
    List<CPU> listCPU = serviceCPU.getAll();
    HangService serviceHang = new HangServiceImql();
    List<Hang> listHang = serviceHang.getAll();
    RamService serviceRam = new RamServiceImql();
    List<Ram> listRam = serviceRam.getAll();
    CardService serviceCard = new CardServiceImql();
    List<CardMH> listCard = serviceCard.getAll();
    OCungService serviceOCung = new OCungServiceImql();
    List<OCung> listOCung = serviceOCung.getAll();

    DefaultTableModel modelCTSP = new DefaultTableModel();
    ChiTietSPService serviceCT = new ChiTietSPServiceImql();
    List<ChiTietSP> listCTsp = serviceCT.getAllChiTietSP();
    ChiTietSPRepository ctspRepo = new ChiTietSPRepository();

    /**
     * Creates new form ViewSanPham
     */
    public PanelSanPham() {
        initComponents();

        cbbCPU.setModel(dfcbbCPU);
        cbbCard.setModel(dfcbbCard);
        cbbHang.setModel(dfcbbHang);
        cbbMaSP.setModel(dfcbbSP);
        cbbOCung.setModel(dfcbbOCung);
        cbbRam.setModel(dfcbbRam);

        showCPU();
        showCard();
        showHang();
        showMaSP();
        showOCung();
        showRam();

        showData(listCTsp);
        showDataTable1(listSanPham);
        showDataTable(listSanPham);
        showDataTableCPU(listCPU);
        showDataTableCard(listCard);
        showDataTableHang(listHang);
        showDataTableOCung(listOCung);
        showDataTableRam(listRam);

        jFrame1.setLocationRelativeTo(null);
        jFrame2.setLocationRelativeTo(null);
        jFrame3.setLocationRelativeTo(null);
        jFrame4.setLocationRelativeTo(null);
        jFrame5.setLocationRelativeTo(null);
        jFrame6.setLocationRelativeTo(null);

        btnTrangThai.setVisible(false);

    }

    public void showMaSP() {
        for (SanPham x : listSanPham) {
            dfcbbSP.addElement(x.getTen());
        }
    }

    public void showCPU() {
        for (CPU x : listCPU) {
            dfcbbCPU.addElement(x.getTen());
        }
    }

    public void showHang() {
        for (Hang x : listHang) {
            dfcbbHang.addElement(x.getTen());
        }
    }

    public void showRam() {
        for (Ram x : listRam) {
            dfcbbRam.addElement(x.getTen());
        }
    }

    public void showCard() {
        for (CardMH x : listCard) {
            dfcbbCard.addElement(x.getTen());
        }
    }

    public void showOCung() {
        for (OCung x : listOCung) {
            dfcbbOCung.addElement(x.getTen());
        }
    }

    public void showData(List<ChiTietSP> ctsp) {
        modelCTSP = (DefaultTableModel) tblBangChiTietSP.getModel();
        modelCTSP.setRowCount(0);
        int stt = 1;
        for (ChiTietSP x : ctsp) {
            modelCTSP.addRow(new Object[]{false, stt, x.getIdSanPham().getTen(), x.getIdCPU().getTen(), x.getIdCard().getTen(), x.getIdHang().getTen(), x.getIdOCung().getTen(), x.getIdRam().getTen(), x.getSerial(), x.getGia(), x.getTinhTrang() == 0 ? "Đang bán" : x.getTinhTrang() == 1 ? "Đã bán" : "Ngừng kinh doanh"});
            stt++;
        }
    }

    public void mountClick(List<ChiTietSP> ctsp, int index) {

        cbbCPU.setSelectedItem(ctsp.get(index).getIdCPU().getTen());
        cbbCard.setSelectedItem(ctsp.get(index).getIdCard().getTen());
        cbbHang.setSelectedItem(ctsp.get(index).getIdHang().getTen());
        cbbMaSP.setSelectedItem(ctsp.get(index).getIdSanPham().getTen());
        cbbOCung.setSelectedItem(ctsp.get(index).getIdOCung().getTen());
        cbbRam.setSelectedItem(ctsp.get(index).getIdRam().getTen());
        txtSerialSP.setText(ctsp.get(index).getSerial());
        txtGia.setText(ctsp.get(index).getGia().toString());
    }

    public void fillSP(int index, List<SanPham> sp) {
        txtTenSP1.setText(sp.get(index).getTen());
    }

    public void fillCPU(int index, List<CPU> cpu) {
        txtTenCPU.setText(cpu.get(index).getTen());
    }

    public void fillHang(int index, List<Hang> hang) {
        txtTenHang.setText(hang.get(index).getTen());
    }

    public void fillRam(int index, List<Ram> ram) {
        txtTenRam.setText(ram.get(index).getTen());
    }

    public void fillOCung(int index, List<OCung> oCung) {
        txtTenOCung.setText(oCung.get(index).getTen());
    }

    public void fillCard(int index, List<CardMH> card) {
        txtTenCard.setText(card.get(index).getTen());
    }

    public void fillSP1(int index, List<SanPham> sp1) {
        txtTenCPU1.setText(sp1.get(index).getTen());
    }

    private void showDataTable(List<SanPham> listss) {
        modelSP = (DefaultTableModel) tblSanPham1.getModel();
        modelSP.setRowCount(0);
        int stt = 1;
        for (SanPham x : listss) {
            modelSP.addRow(new Object[]{stt, x.getMa(), x.getTen()});
            stt++;
        }
    }

    private void showDataTable1(List<SanPham> lists) {
        modelSP1 = (DefaultTableModel) tblSP1.getModel();
        modelSP1.setRowCount(0);
        int stt = 1;
        for (SanPham x : lists) {
            modelSP1.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTableCPU(List<CPU> list) {
        modelCPU = (DefaultTableModel) tblCPU.getModel();
        modelCPU.setRowCount(0);
        int stt = 1;
        for (CPU x : list) {
            modelCPU.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTableCard(List<CardMH> list) {
        modelCard = (DefaultTableModel) tblCard.getModel();
        modelCard.setRowCount(0);
        int stt = 1;
        for (CardMH x : list) {
            modelCard.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTableHang(List<Hang> list) {
        modelHang = (DefaultTableModel) tblHang.getModel();
        modelHang.setRowCount(0);
        int stt = 1;
        for (Hang x : list) {
            modelHang.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTableOCung(List<OCung> list) {
        modelOCung = (DefaultTableModel) tblOCung.getModel();
        modelOCung.setRowCount(0);
        int stt = 1;
        for (OCung x : list) {
            modelOCung.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTableRam(List<Ram> list) {
        modelRam = (DefaultTableModel) tblRam.getModel();
        modelRam.setRowCount(0);
        int stt = 1;
        for (Ram x : list) {
            modelRam.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    SanPham getData() {
        return new SanPham("SP" + (listSanPham.size() + 1), txtTenSP1.getText(), new Date(), new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel20 = new javax.swing.JPanel();
        txtTenCPU1 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP7 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSP1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        jFrame2 = new javax.swing.JFrame();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTenCPU = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnThemcpu = new javax.swing.JButton();
        btnSuacpu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCPU = new javax.swing.JTable();
        btnThoatcpu = new javax.swing.JButton();
        jFrame3 = new javax.swing.JFrame();
        jPanel18 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnThemCard = new javax.swing.JButton();
        btnSuaCard = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCard = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTenCard = new javax.swing.JTextField();
        btnThoatCard = new javax.swing.JButton();
        jFrame4 = new javax.swing.JFrame();
        jPanel17 = new javax.swing.JPanel();
        txtTenHang = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnThemHang = new javax.swing.JButton();
        btnSuaHang = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHang = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnThoatHang = new javax.swing.JButton();
        jFrame5 = new javax.swing.JFrame();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblOCung = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtTenOCung = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btnThemOCung = new javax.swing.JButton();
        btnSuaOCung = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFrame6 = new javax.swing.JFrame();
        jPanel13 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnThemRam = new javax.swing.JButton();
        btnSuaRam = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblRam = new javax.swing.JTable();
        btnThoatRam = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTenRam = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtTenSP1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSanPham1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnThemSP6 = new javax.swing.JButton();
        btnSuaSP6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbbCPU = new javax.swing.JComboBox<>();
        btnCPU = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        cbbHang = new javax.swing.JComboBox<>();
        cbbOCung = new javax.swing.JComboBox<>();
        btnOCung = new javax.swing.JButton();
        cbbCard = new javax.swing.JComboBox<>();
        cbbRam = new javax.swing.JComboBox<>();
        btnRam = new javax.swing.JButton();
        btnCard = new javax.swing.JButton();
        tblChiTietSanPham = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblBangChiTietSP = new javax.swing.JTable();
        txtLoc2 = new javax.swing.JTextField();
        txtLoc1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnXuatFiel = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnTaiXuong = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTrangThai = new javax.swing.JButton();
        txtGia = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtSerialSP = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        cbbMaSP = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        btnThemSanPham = new javax.swing.JButton();

        jFrame1.setSize(new java.awt.Dimension(570, 308));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        txtTenCPU1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenCPU1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP7.setText("Sửa");
        btnSuaSP7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSP1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ));
        tblSP1.setColumnSelectionAllowed(true);
        tblSP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSP1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblSP1);
        tblSP1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Tên SP");

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenCPU1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(btnThoat)
                        .addGap(15, 15, 15)))
                .addGap(33, 33, 33))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTenCPU1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoat)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrame2.setSize(new java.awt.Dimension(570, 308));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tên CPU");

        txtTenCPU.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenCPU.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemcpu.setText("Thêm");
        btnThemcpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemcpuActionPerformed(evt);
            }
        });

        btnSuacpu.setText("Sửa");
        btnSuacpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuacpuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuacpu, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemcpu, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemcpu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuacpu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblCPU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã cpu", "Tên cpu"
            }
        ));
        tblCPU.setColumnSelectionAllowed(true);
        tblCPU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCPUMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCPU);
        tblCPU.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnThoatcpu.setText("Thoát");
        btnThoatcpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatcpuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap(40, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(btnThoatcpu)
                        .addGap(38, 38, 38))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoatcpu)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrame3.setSize(new java.awt.Dimension(570, 308));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemCard.setText("Thêm");
        btnThemCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCardActionPerformed(evt);
            }
        });

        btnSuaCard.setText("Sửa");
        btnSuaCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaCard, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemCard, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemCard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaCard, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblCard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã card", "Tên card"
            }
        ));
        tblCard.setColumnSelectionAllowed(true);
        tblCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCardMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCard);
        tblCard.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Tên Card");

        txtTenCard.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenCard.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        btnThoatCard.setText("Thoát");
        btnThoatCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenCard, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(btnThoatCard)
                        .addGap(10, 10, 10)))
                .addGap(28, 28, 28))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTenCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoatCard)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrame4.setSize(new java.awt.Dimension(570, 308));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        txtTenHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemHang.setText("Thêm");
        btnThemHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHangActionPerformed(evt);
            }
        });

        btnSuaHang.setText("Sửa");
        btnSuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaHang, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemHang, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã hãng", "Tên hãng"
            }
        ));
        tblHang.setColumnSelectionAllowed(true);
        tblHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHang);
        tblHang.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Tên Hãng");

        btnThoatHang.setText("Thoát");
        btnThoatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(btnThoatHang)
                        .addGap(12, 12, 12)))
                .addGap(25, 25, 25))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoatHang)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrame5.setSize(new java.awt.Dimension(570, 308));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        tblOCung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã ổ cứng", "Tên ổ cứng"
            }
        ));
        tblOCung.setColumnSelectionAllowed(true);
        tblOCung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOCungMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblOCung);
        tblOCung.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Tên OCung");

        txtTenOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenOCung.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemOCung.setText("Thêm");
        btnThemOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemOCungActionPerformed(evt);
            }
        });

        btnSuaOCung.setText("Sửa");
        btnSuaOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaOCungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(34, 34, 34))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTenOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame5Layout = new javax.swing.GroupLayout(jFrame5.getContentPane());
        jFrame5.getContentPane().setLayout(jFrame5Layout);
        jFrame5Layout.setHorizontalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame5Layout.setVerticalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrame6.setBackground(new java.awt.Color(255, 255, 255));
        jFrame6.setSize(new java.awt.Dimension(570, 308));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemRam.setText("Thêm");
        btnThemRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemRamActionPerformed(evt);
            }
        });

        btnSuaRam.setText("Sửa");
        btnSuaRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaRamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemRam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemRam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaRam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblRam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã ram", "Tên ram"
            }
        ));
        tblRam.setColumnSelectionAllowed(true);
        tblRam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRamMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblRam);
        tblRam.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnThoatRam.setText("Thoát");
        btnThoatRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatRamActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Tên Ram");

        txtTenRam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenRam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenRam, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(btnThoatRam)
                        .addGap(37, 37, 37))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTenRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoatRam)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame6Layout = new javax.swing.GroupLayout(jFrame6.getContentPane());
        jFrame6.getContentPane().setLayout(jFrame6Layout);
        jFrame6Layout.setHorizontalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame6Layout.setVerticalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame6Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Tên SP");

        txtTenSP1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTenSP1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Tìm kiếm");

        txtTimKiemSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTimKiemSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));
        txtTimKiemSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSPCaretUpdate(evt);
            }
        });

        tblSanPham1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ));
        tblSanPham1.setColumnSelectionAllowed(true);
        tblSanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPham1MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblSanPham1);
        tblSanPham1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoSP.png"))); // NOI18N

        btnThemSP6.setBackground(new java.awt.Color(41, 183, 212));
        btnThemSP6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemSP6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addNV.png"))); // NOI18N
        btnThemSP6.setText("Thêm");
        btnThemSP6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP6ActionPerformed(evt);
            }
        });

        btnSuaSP6.setBackground(new java.awt.Color(41, 183, 212));
        btnSuaSP6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaSP6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/updateNV.png"))); // NOI18N
        btnSuaSP6.setText("Sửa");
        btnSuaSP6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txtTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSuaSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 57, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnThemSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnSuaSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("CPU");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Hãng");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Ram");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Card");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ổ Cứng");

        cbbCPU.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbCPU.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCPUItemStateChanged(evt);
            }
        });

        btnCPU.setBackground(new java.awt.Color(41, 183, 212));
        btnCPU.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCPU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnCPU.setToolTipText("Thêm cpu");
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });

        btnHang.setBackground(new java.awt.Color(41, 183, 212));
        btnHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnHang.setToolTipText("Thêm hãng");
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        cbbHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHangItemStateChanged(evt);
            }
        });

        cbbOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnOCung.setBackground(new java.awt.Color(41, 183, 212));
        btnOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnOCung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnOCung.setToolTipText("Thêm ổ cứng");
        btnOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOCungActionPerformed(evt);
            }
        });

        cbbCard.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbRam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnRam.setBackground(new java.awt.Color(41, 183, 212));
        btnRam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnRam.setToolTipText("Thêm ram");
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });

        btnCard.setBackground(new java.awt.Color(41, 183, 212));
        btnCard.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnCard.setToolTipText("Thêm card");
        btnCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(145, 145, 145)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbRam, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(216, 216, 216))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCPU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbCPU, javax.swing.GroupLayout.Alignment.TRAILING, 0, 152, Short.MAX_VALUE)))
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOCung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbOCung, 0, 152, Short.MAX_VALUE))))
                .addGap(99, 99, 99)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCard, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbCard, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(cbbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRam))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cbbCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOCung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tblChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tblChiTietSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tblBangChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "STT", "Tên SP", "CPU", "Card", "Hãng", "Ổ Cứng", "Ram", "Serial", "Gía", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBangChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblBangChiTietSP);
        if (tblBangChiTietSP.getColumnModel().getColumnCount() > 0) {
            tblBangChiTietSP.getColumnModel().getColumn(0).setMinWidth(20);
            tblBangChiTietSP.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblBangChiTietSP.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        txtLoc2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtLoc2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));
        txtLoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoc2ActionPerformed(evt);
            }
        });

        txtLoc1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtLoc1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));
        txtLoc1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLoc1CaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lọc giá");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("--");

        btnXuatFiel.setBackground(new java.awt.Color(41, 183, 212));
        btnXuatFiel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatFiel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export.png"))); // NOI18N
        btnXuatFiel.setText("EXPORT");
        btnXuatFiel.setToolTipText("Xuất ra file excel");
        btnXuatFiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFielActionPerformed(evt);
            }
        });

        btnImport.setBackground(new java.awt.Color(41, 183, 212));
        btnImport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/import.png"))); // NOI18N
        btnImport.setText("IMPORT");
        btnImport.setToolTipText("Thêm nhiều sản phẩm cùng lúc");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnTaiXuong.setBackground(new java.awt.Color(0, 153, 255));
        btnTaiXuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/taiXuong.jpg"))); // NOI18N
        btnTaiXuong.setToolTipText("Tải file excel mẫu");
        btnTaiXuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiXuongActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Tìm kiếm ");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tblChiTietSanPhamLayout = new javax.swing.GroupLayout(tblChiTietSanPham);
        tblChiTietSanPham.setLayout(tblChiTietSanPhamLayout);
        tblChiTietSanPhamLayout.setHorizontalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTaiXuong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnXuatFiel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        tblChiTietSanPhamLayout.setVerticalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaiXuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXuatFiel)
                        .addComponent(btnImport)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        btnThem.setBackground(new java.awt.Color(41, 183, 212));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addNV.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(41, 183, 212));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/updateNV.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnTrangThai.setBackground(new java.awt.Color(41, 183, 212));
        btnTrangThai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sathai.png"))); // NOI18N
        btnTrangThai.setText("Trạng thái");
        btnTrangThai.setToolTipText("");
        btnTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangThaiActionPerformed(evt);
            }
        });

        txtGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Serial");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Giá");

        txtSerialSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSerialSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(40, 184, 213)));

        btnLamMoi.setBackground(new java.awt.Color(41, 183, 212));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        cbbMaSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Tên sản phẩm");

        btnThemSanPham.setBackground(new java.awt.Color(41, 183, 212));
        btnThemSanPham.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/newNV.png"))); // NOI18N
        btnThemSanPham.setToolTipText("Thêm sản phẩm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSerialSP, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerialSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chi tiết sản phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCPUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCPUMouseClicked
        int indexx = tblCPU.getSelectedRow();
        fillCPU(indexx, listCPU);
    }//GEN-LAST:event_tblCPUMouseClicked

    private void btnThemcpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemcpuActionPerformed
        CPU sp = new CPU("CPU" + (listCPU.size() + 1), txtTenCPU.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceCPU.add(sp));
        listCPU = serviceCPU.getAll();
        showDataTableCPU(listCPU);
        cbbCPU.removeAllItems();
        showCPU();
    }//GEN-LAST:event_btnThemcpuActionPerformed

    private void btnSuacpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuacpuActionPerformed
        CPU sPham = listCPU.get(tblCPU.getSelectedRow());
        CPU sp = new CPU(sPham.getTen(), txtTenCPU.getText(), new Date(), sPham.getLastModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceCPU.upDate(sp, id));
        listCPU = serviceCPU.getAll();
        showDataTableCPU(listCPU);
        cbbCPU.removeAllItems();
        showCPU();
    }//GEN-LAST:event_btnSuacpuActionPerformed

    private void tblCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCardMouseClicked
        int indexx = tblCard.getSelectedRow();
        fillCard(indexx, listCard);
    }//GEN-LAST:event_tblCardMouseClicked

    private void btnThemCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCardActionPerformed
        CardMH sp = new CardMH("Card" + (listCard.size() + 1), txtTenCard.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceCard.add(sp));
        listCard = serviceCard.getAll();
        showDataTableCard(listCard);
        cbbCard.removeAllItems();
        showCard();
    }//GEN-LAST:event_btnThemCardActionPerformed

    private void btnSuaCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCardActionPerformed
        CardMH sPham = listCard.get(tblCard.getSelectedRow());
        CardMH sp = new CardMH(sPham.getMa(), txtTenCard.getText(), new Date(), sPham.getLastModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceCard.upDate(sp, id));
        listCard = serviceCard.getAll();
        showDataTableCard(listCard);
        cbbCard.removeAllItems();
        showCard();
    }//GEN-LAST:event_btnSuaCardActionPerformed

    private void btnThemHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHangActionPerformed
        Hang sp = new Hang("H" + (listHang.size() + 1), txtTenHang.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceHang.add(sp));
        listHang = serviceHang.getAll();
        showDataTableHang(listHang);
        cbbHang.removeAllItems();
        showHang();
    }//GEN-LAST:event_btnThemHangActionPerformed

    private void btnSuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHangActionPerformed
        Hang sPham = listHang.get(tblHang.getSelectedRow());
        Hang sp = new Hang(sPham.getMa(), txtTenHang.getText(), sPham.getCreatedDate(), new Date());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceHang.upDate(sp, id));
        listHang = serviceHang.getAll();
        showDataTableHang(listHang);
        cbbHang.removeAllItems();
        showHang();
    }//GEN-LAST:event_btnSuaHangActionPerformed

    private void tblHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangMouseClicked
        int indexx = tblHang.getSelectedRow();
        fillHang(indexx, listHang);
    }//GEN-LAST:event_tblHangMouseClicked

    private void tblOCungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOCungMouseClicked
        int indexx = tblOCung.getSelectedRow();
        fillOCung(indexx, listOCung);
    }//GEN-LAST:event_tblOCungMouseClicked

    private void btnThemOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemOCungActionPerformed
        OCung sp = new OCung("OCung" + (listOCung.size() + 1), txtTenOCung.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceOCung.add(sp));
        listOCung = serviceOCung.getAll();
        showDataTableOCung(listOCung);
        cbbOCung.removeAllItems();
        showOCung();
    }//GEN-LAST:event_btnThemOCungActionPerformed

    private void btnSuaOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaOCungActionPerformed
        OCung sPham = listOCung.get(tblOCung.getSelectedRow());
        OCung sp = new OCung(sPham.getMa(), txtTenOCung.getText(), sPham.getCreatedDate(), new Date());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceOCung.upDate(sp, id));
        listOCung = serviceOCung.getAll();
        showDataTableOCung(listOCung);
        cbbOCung.removeAllItems();
        showOCung();
    }//GEN-LAST:event_btnSuaOCungActionPerformed

    private void btnThemRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemRamActionPerformed
        Ram sp = new Ram("Ram" + (listRam.size() + 1), txtTenRam.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceRam.add(sp));
        listRam = serviceRam.getAll();
        showDataTableRam(listRam);
        cbbRam.removeAllItems();
        showRam();
    }//GEN-LAST:event_btnThemRamActionPerformed

    private void btnSuaRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaRamActionPerformed
        Ram sPham = listRam.get(tblRam.getSelectedRow());
        Ram sp = new Ram(sPham.getMa(), txtTenRam.getText(), new Date(), sPham.getLastModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceRam.upDate(sp, id));
        listRam = serviceRam.getAll();
        showDataTableRam(listRam);
        cbbRam.removeAllItems();
        showRam();
    }//GEN-LAST:event_btnSuaRamActionPerformed

    private void tblRamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRamMouseClicked
        int indexx = tblRam.getSelectedRow();
        fillRam(indexx, listRam);
    }//GEN-LAST:event_tblRamMouseClicked
    SanPham getData1() {
        return new SanPham("SP" + (listSanPham.size() + 1), txtTenCPU1.getText(), new Date(), new Date());
    }
    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        JOptionPane.showMessageDialog(this, service.addSanPham(getData1()));
        listSanPham = service.getAllSanPham();
        showDataTable1(listSanPham);
        showDataTable(listSanPham);
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP7ActionPerformed
        SanPham sPham = listSanPham.get(tblSP1.getSelectedRow());
        SanPham sp = new SanPham(sPham.getMa(), txtTenCPU1.getText(), sPham.getCreatedDate(), new Date());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, service.upDateSanPham(sp, id));
        listSanPham = service.getAllSanPham();
        showDataTable1(listSanPham);
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnSuaSP7ActionPerformed

    private void tblSP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSP1MouseClicked
        int indexx = tblSP1.getSelectedRow();
        fillSP1(indexx, listSanPham);
    }//GEN-LAST:event_tblSP1MouseClicked

    private void btnThemSP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP6ActionPerformed

        JOptionPane.showMessageDialog(this, service.addSanPham(getData()));
        listSanPham = service.getAllSanPham();
        showDataTable(listSanPham);
        showDataTable1(listSanPham);
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnThemSP6ActionPerformed

    private void btnSuaSP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP6ActionPerformed
        SanPham sPham = listSanPham.get(tblSanPham1.getSelectedRow());
        SanPham sp = new SanPham(sPham.getMa(), txtTenSP1.getText(), sPham.getCreatedDate(), new Date());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, service.upDateSanPham(sp, id));
        listSanPham = service.getAllSanPham();
        showDataTable(listSanPham);
        showDataTable1(listSanPham);
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnSuaSP6ActionPerformed

    private void txtTimKiemSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSPCaretUpdate
        String search = txtTimKiemSP.getText();
        List<SanPham> listsp = service.getAllSanPham();
        if (search.isEmpty()) {
            showDataTable(listsp);
        } else {
            listsp = service.search(search);
            showDataTable(listsp);
        }
    }//GEN-LAST:event_txtTimKiemSPCaretUpdate

    private void tblSanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPham1MouseClicked
        int indexx = tblSanPham1.getSelectedRow();
        fillSP(indexx, listSanPham);
    }//GEN-LAST:event_tblSanPham1MouseClicked

    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        jFrame2.setVisible(true);
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        jFrame4.setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOCungActionPerformed
        jFrame5.setVisible(true);
    }//GEN-LAST:event_btnOCungActionPerformed

    private void btnRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamActionPerformed
        jFrame6.setVisible(true);
    }//GEN-LAST:event_btnRamActionPerformed

    private void btnCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardActionPerformed
        jFrame3.setVisible(true);
    }//GEN-LAST:event_btnCardActionPerformed

    private void tblBangChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangChiTietSPMouseClicked
        txtSerialSP.setEnabled(false);
        btnTrangThai.setVisible(true);
        int indexx = tblBangChiTietSP.getSelectedRow();
        mountClick(listCTsp, indexx);
    }//GEN-LAST:event_tblBangChiTietSPMouseClicked

    private void txtLoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoc2ActionPerformed
        String loc1 = txtLoc2.getText();
        String loc2 = txtLoc1.getText();
        List<ChiTietSP> listCT = serviceCT.getAllChiTietSP();
        if (loc1.isEmpty() && loc2.isEmpty()) {
            showData(listCT);
        } else {
            listCT = serviceCT.getOneGia(loc1, loc2);
            showData(listCT);
        }
    }//GEN-LAST:event_txtLoc2ActionPerformed

    private void txtLoc1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLoc1CaretUpdate
        String loc1 = txtLoc1.getText();
        String loc2 = txtLoc2.getText();
        List<ChiTietSP> list = serviceCT.getAllChiTietSP();
        if (loc1.isEmpty()) {
            showData(list);
        } else {
            list = serviceCT.getOneGia(loc2, loc1);
            showData(list);
        }
    }//GEN-LAST:event_txtLoc1CaretUpdate

    private void btnXuatFielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFielActionPerformed

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("ChiTietSP");

            XSSFRow row = null;
            Cell cell = null;

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Font tieuDe = workbook.createFont();
            tieuDe.setBold(true);
            tieuDe.setFontHeightInPoints((short) 18);
            tieuDe.setColor(IndexedColors.BLACK.getIndex());
            CellStyle tieuDeStyle = workbook.createCellStyle();
            tieuDeStyle.setFont(tieuDe);

            row = spreadsheet.createRow((short) 0);
            row.setHeight((short) 500);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Danh sách chi tiết sản phẩm");
            cell.setCellStyle(tieuDeStyle);
            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên SP");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("CPU");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Card");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Hãng");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ổ Cứng");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ram");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Serial");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Gía");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Tình trạng");
            cell.setCellStyle(headerCellStyle);
            for (int i = 0; i < listCTsp.size(); i++) {
                ChiTietSP ctcp = listCTsp.get(i);
                row = spreadsheet.createRow((short) 2 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(ctcp.getIdSanPham().getTen());
                row.createCell(2).setCellValue(ctcp.getIdCPU().getTen());
                row.createCell(3).setCellValue(ctcp.getIdCard().getTen());
                row.createCell(4).setCellValue(ctcp.getIdHang().getTen());
                row.createCell(5).setCellValue(ctcp.getIdOCung().getTen());
                row.createCell(6).setCellValue(ctcp.getIdRam().getTen());
                row.createCell(7).setCellValue(ctcp.getSerial());
                row.createCell(8).setCellValue(String.valueOf(ctcp.getGia()));
                row.createCell(9).setCellValue(String.valueOf(ctcp.getTinhTrang() == 0 ? "Đang bán" : ctcp.getTinhTrang() == 1 ? "Đã bán" : "Ngừng bán"));
            }
            FileOutputStream out = new FileOutputStream(new File("D:/ExportSanPham .xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất file excel thành công ( file ở ổ D )");
            if (!Desktop.isDesktopSupported()) {
                return ;
            }
            Desktop deskto = Desktop.getDesktop();
            if (new File("D:/ExportSanPham .xlsx").exists()) {
                deskto.open(new File("D:/ExportSanPham .xlsx"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại");
        }
    }//GEN-LAST:event_btnXuatFielActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        File file;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        XSSFWorkbook wb = null;
        String path = "D:";
        JFileChooser exChooser = new JFileChooser(path);
        int results = exChooser.showOpenDialog(null);
        if (results == JFileChooser.APPROVE_OPTION) {
            file = exChooser.getSelectedFile();
            String name = file.getPath();
            ImportFile(name);
        }
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá !");
        } else if (txtGia.getText().matches("^[A-Za-z]$")) {
            JOptionPane.showMessageDialog(this, "Giá không đúng định dạng");
        } else {
            SanPham sp = service.getOneSP(cbbMaSP.getSelectedItem().toString());
            CPU cpu = serviceCPU.getOneCPU(cbbCPU.getSelectedItem().toString());
            Ram ram = serviceRam.getOneRam(cbbRam.getSelectedItem().toString());
            Hang hang = serviceHang.getOneHang(cbbHang.getSelectedItem().toString());
            CardMH card = serviceCard.getOneCard(cbbCard.getSelectedItem().toString());
            OCung oCung = serviceOCung.getOneOCung(cbbOCung.getSelectedItem().toString());
            BigDecimal big = new BigDecimal(txtGia.getText());
            ChiTietSP ctsp = new ChiTietSP("CTSP" + (listCTsp.size() + 1), sp, txtSerialSP.getText(), cpu, hang, ram, card, oCung, big, 0, new Date(), new Date());
            JOptionPane.showMessageDialog(this, serviceCT.add(ctsp));
            listCTsp = serviceCT.getAllChiTietSP();
            showData(listCTsp);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tblBangChiTietSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm !");
        } else {
            ChiTietSP ct = listCTsp.get(tblBangChiTietSP.getSelectedRow());
            SanPham sp = service.getOneSP(cbbMaSP.getSelectedItem().toString());
            CPU cpu = serviceCPU.getOneCPU(cbbCPU.getSelectedItem().toString());
            Ram ram = serviceRam.getOneRam(cbbRam.getSelectedItem().toString());
            Hang hang = serviceHang.getOneHang(cbbHang.getSelectedItem().toString());
            CardMH card = serviceCard.getOneCard(cbbCard.getSelectedItem().toString());
            OCung oCung = serviceOCung.getOneOCung(cbbOCung.getSelectedItem().toString());
            BigDecimal big = new BigDecimal(txtGia.getText());
            ChiTietSP ctsp = new ChiTietSP(sp, txtSerialSP.getText(), cpu, hang, ram, card, oCung, big, 0, new Date(), new Date());
            UUID id = ct.getId();
            JOptionPane.showMessageDialog(this, serviceCT.update(ctsp, id));
            listCTsp = serviceCT.getAllChiTietSP();
            showData(listCTsp);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangThaiActionPerformed

        int row = tblBangChiTietSP.getSelectedRow();
        ChiTietSP chiTietSP = listCTsp.get(row);
        if (chiTietSP.getTinhTrang() == 0) {
            int updateTT = JOptionPane.showConfirmDialog(this, "Bạn có muốn ngừng bán sản phẩm này không ? ", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
            if (updateTT == JOptionPane.YES_OPTION) {
                List<ChiTietSP> listCT = new ArrayList<>();
                for (int i = 0; i < tblBangChiTietSP.getRowCount(); i++) {
                    Boolean check = (Boolean) tblBangChiTietSP.getValueAt(i, 0);
                    ChiTietSP ctSanPham = listCTsp.get(tblBangChiTietSP.getSelectedRow());
                    if (ctSanPham.getTinhTrang() == 1) {
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã bán");
                        return;
                    }
                    if (check == true) {
                        listCT.add(listCTsp.get(i));
                    }
                }

                JOptionPane.showMessageDialog(this, serviceCT.updateTinhTrang(listCT));
                listCTsp = serviceCT.getAllChiTietSP();
                showData(listCTsp);
                btnTrangThai.setVisible(false);
            }
        } else {
            int updateTT = JOptionPane.showConfirmDialog(this, "Bạn có muốn khôi phục sản phẩm này không ? ", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
            if (updateTT == JOptionPane.YES_OPTION) {
                List<ChiTietSP> listCT = new ArrayList<>();
                for (int i = 0; i < tblBangChiTietSP.getRowCount(); i++) {
                    Boolean check = (Boolean) tblBangChiTietSP.getValueAt(i, 0);
                    ChiTietSP ctSanPham = listCTsp.get(tblBangChiTietSP.getSelectedRow());
                    if (ctSanPham.getTinhTrang() == 1) {
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã bán");
                        return;
                    }
                    if (check == true) {
                        listCT.add(listCTsp.get(i));
                    }
                }
                JOptionPane.showMessageDialog(this, serviceCT.updateKhoiPhuc(listCT));
                listCTsp = serviceCT.getAllChiTietSP();
                showData(listCTsp);
                btnTrangThai.setVisible(false);
            }
        }

    }//GEN-LAST:event_btnTrangThaiActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String search = txtTimKiem.getText();
        if (search.isEmpty()) {
            showData(listCTsp);
        } else {
            listCTsp = serviceCT.seatch(search);
            showData(listCTsp);
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtGia.setText("");
        txtSerialSP.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        jFrame1.setVisible(true);
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnThoatRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatRamActionPerformed
        jFrame6.dispose();
    }//GEN-LAST:event_btnThoatRamActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jFrame5.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnThoatCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatCardActionPerformed
        jFrame3.dispose();
    }//GEN-LAST:event_btnThoatCardActionPerformed

    private void btnThoatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatHangActionPerformed
        jFrame4.dispose();
    }//GEN-LAST:event_btnThoatHangActionPerformed

    private void btnThoatcpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatcpuActionPerformed
        jFrame2.dispose();
    }//GEN-LAST:event_btnThoatcpuActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        jFrame1.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTaiXuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiXuongActionPerformed
        Temperate();
    }//GEN-LAST:event_btnTaiXuongActionPerformed

    private void cbbHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHangItemStateChanged

    }//GEN-LAST:event_cbbHangItemStateChanged

    private void cbbCPUItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCPUItemStateChanged

    }//GEN-LAST:event_cbbCPUItemStateChanged
    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }

            }
        } catch (Exception e) {
            return "";
        }
    }

    public void ImportFile(String path) {
        try {
            List<ChiTietSP> listctsp = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                String maCTSP = "CTSP" + (listCTsp.size() + 1);
                String tenSp = String.valueOf(getCellValue(currentRow.getCell(0))).trim();
                String tenCPU = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
                String tenCard = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
                String tenHang = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
                String tenOcung = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
                String tenRam = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
                String soSerial = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
                String gia = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
                int tinhTrang = 0;
                
                if (tenSp.isEmpty() && tenCPU.isEmpty() && tenCard.isEmpty() && tenHang.isEmpty()
                        && tenOcung.isEmpty() && tenRam.isEmpty() && soSerial.isEmpty() && gia.isEmpty()) {
                    continue;
                }
                if (tenSp.isEmpty() || tenCPU.isEmpty() || tenCard.isEmpty() || tenHang.isEmpty()
                        || tenOcung.isEmpty() || tenRam.isEmpty() || soSerial.isEmpty() || gia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }
                SanPham sanPham = ctspRepo.findTenSP(tenSp);
                if (sanPham == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
                    return;
                }
                CPU cpu = ctspRepo.findTenCPU(tenCPU);
                if (cpu == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy CPU");
                    return;
                }
                CardMH card = ctspRepo.findTenCard(tenCard);
                if (card == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy card");
                    return;
                }
                Hang hang = ctspRepo.findTenHang(tenHang);
                if (hang == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy hãng");
                    return;
                }
                OCung oCung = ctspRepo.findTenOCung(tenOcung);
                if (oCung == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy ổ cứng");
                    return;
                }
                Ram ram = ctspRepo.findTenRam(tenRam);
                if (ram == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy ram");
                    return;
                }
                ChiTietSP chiTietSP = new ChiTietSP(maCTSP, sanPham, soSerial, cpu, hang, ram, card, oCung, BigDecimal.ONE, tinhTrang);
                chiTietSP.setMaCTSP(maCTSP);
                chiTietSP.setIdSanPham(sanPham);
                chiTietSP.setIdCPU(cpu);
                chiTietSP.setIdCard(card);
                chiTietSP.setIdHang(hang);
                chiTietSP.setIdOCung(oCung);
                chiTietSP.setIdRam(ram);
                chiTietSP.setSerial(soSerial);
                chiTietSP.setGia(new BigDecimal(gia));
                chiTietSP.setTinhTrang(tinhTrang);
                chiTietSP.setCreatedDate(new Date());
                chiTietSP.setLastModifiedDate(new Date());
                listctsp.add(chiTietSP);
                serviceCT.add(chiTietSP);
                listCTsp = ctspRepo.getAllChiTietSP();
            }
            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            showData(listCTsp);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Temperate() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("ChiTietSP");

            XSSFRow row = null;
            Cell cell = null;

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            row = spreadsheet.createRow((short) 0);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Tên SP");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("CPU");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Card");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Hãng");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ổ Cứng");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ram");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Serial");
            cell.setCellStyle(headerCellStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Gía");
            cell.setCellStyle(headerCellStyle);
            FileOutputStream out = new FileOutputStream(new File("D:/Temperate.xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Thành công (file ở ổ D )");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
    }

    private void createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
    }

    private void createUI(final JFrame frame) {
        final JLabel label = new JLabel();

        Object[] options = {"Import file excel", "Temperlate mẫu", "Thoát"};
        int result = JOptionPane.showOptionDialog(frame,
                "Mời bạn chọn chức năng",
                "Xác nhận",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (result == JOptionPane.YES_OPTION) {
            File file;
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            XSSFWorkbook wb = null;
            String path = "D:";
            JFileChooser exChooser = new JFileChooser(path);
            int results = exChooser.showOpenDialog(null);
            if (results == JFileChooser.APPROVE_OPTION) {
                file = exChooser.getSelectedFile();
                String name = file.getPath();
                ImportFile(name);
            }
        } else if (result == JOptionPane.NO_OPTION) {
            Temperate();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPU;
    private javax.swing.JButton btnCard;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnOCung;
    private javax.swing.JButton btnRam;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaCard;
    private javax.swing.JButton btnSuaHang;
    private javax.swing.JButton btnSuaOCung;
    private javax.swing.JButton btnSuaRam;
    private javax.swing.JButton btnSuaSP6;
    private javax.swing.JButton btnSuaSP7;
    private javax.swing.JButton btnSuacpu;
    private javax.swing.JButton btnTaiXuong;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCard;
    private javax.swing.JButton btnThemHang;
    private javax.swing.JButton btnThemOCung;
    private javax.swing.JButton btnThemRam;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSP6;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThemcpu;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThoatCard;
    private javax.swing.JButton btnThoatHang;
    private javax.swing.JButton btnThoatRam;
    private javax.swing.JButton btnThoatcpu;
    private javax.swing.JButton btnTrangThai;
    private javax.swing.JButton btnXuatFiel;
    private javax.swing.JComboBox<String> cbbCPU;
    private javax.swing.JComboBox<String> cbbCard;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JComboBox<String> cbbOCung;
    private javax.swing.JComboBox<String> cbbRam;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JFrame jFrame5;
    private javax.swing.JFrame jFrame6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblBangChiTietSP;
    private javax.swing.JTable tblCPU;
    private javax.swing.JTable tblCard;
    private javax.swing.JPanel tblChiTietSanPham;
    private javax.swing.JTable tblHang;
    private javax.swing.JTable tblOCung;
    private javax.swing.JTable tblRam;
    private javax.swing.JTable tblSP1;
    private javax.swing.JTable tblSanPham1;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtLoc1;
    private javax.swing.JTextField txtLoc2;
    private javax.swing.JTextField txtSerialSP;
    private javax.swing.JTextField txtTenCPU;
    private javax.swing.JTextField txtTenCPU1;
    private javax.swing.JTextField txtTenCard;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenOCung;
    private javax.swing.JTextField txtTenRam;
    private javax.swing.JTextField txtTenSP1;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemSP;
    // End of variables declaration//GEN-END:variables
}
