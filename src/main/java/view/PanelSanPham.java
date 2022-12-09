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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    List<SanPham> listSP = service.getAllSP();
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

    }

    public void showMaSP() {
        for (SanPham x : listSP) {
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
            modelCTSP.addRow(new Object[]{false, stt, x.getIdSanPham().getTen(), x.getIdCPU().getTen(), x.getIdCard().getTen(), x.getIdHang().getTen(), x.getIdOCung().getTen(), x.getIdRam().getTen(), x.getSerial(), x.getGia(), x.getTinhTrang() == 0 ? "Đang bán" : x.getTinhTrang() == 1 ? "Đã bán bán" : "Ngừng kinh doanh"});
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
            modelSP.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.getTrangThai() == 0 ? "Đang hoạt động" : "Ngừng hoạt động", x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTable1(List<SanPham> lists) {
        modelSP1 = (DefaultTableModel) tblSP1.getModel();
        modelSP1.setRowCount(0);
        int stt = 1;
        for (SanPham x : lists) {
            modelSP1.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.getTrangThai() == 0 ? "Đang hoạt động" : "Ngừng hoạt động", x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate())});
            stt++;
        }
    }

    private void showDataTableCPU(List<CPU> list) {
        modelCPU = (DefaultTableModel) tblCPU.getModel();
        modelCPU.setRowCount(0);
        int stt = 1;
        for (CPU x : list) {
            modelCPU.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getAlstModifiedDate())});
            stt++;
        }
    }

    private void showDataTableCard(List<CardMH> list) {
        modelCard = (DefaultTableModel) tblCard.getModel();
        modelCard.setRowCount(0);
        int stt = 1;
        for (CardMH x : list) {
            modelCard.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getAlstModifiedDate())});
            stt++;
        }
    }

    private void showDataTableHang(List<Hang> list) {
        modelHang = (DefaultTableModel) tblHang.getModel();
        modelHang.setRowCount(0);
        int stt = 1;
        for (Hang x : list) {
            modelHang.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getAlstModifiedDate())});
            stt++;
        }
    }

    private void showDataTableOCung(List<OCung> list) {
        modelOCung = (DefaultTableModel) tblOCung.getModel();
        modelOCung.setRowCount(0);
        int stt = 1;
        for (OCung x : list) {
            modelOCung.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getAlstModifiedDate())});
            stt++;
        }
    }

    private void showDataTableRam(List<Ram> list) {
        modelRam = (DefaultTableModel) tblRam.getModel();
        modelRam.setRowCount(0);
        int stt = 1;
        for (Ram x : list) {
            modelRam.addRow(new Object[]{stt, x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getAlstModifiedDate())});
            stt++;
        }
    }

    SanPham getData() {
        return new SanPham("SP" + (listSanPham.size() + 1), txtTenSP1.getText(), 0, new Date(), new Date());
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
        txtTenCPU1 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        btnThemSP7 = new javax.swing.JButton();
        btnSuaSP7 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSP1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jFrame2 = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCPU = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtTenCPU = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnThemSP1 = new javax.swing.JButton();
        btnSuaSP1 = new javax.swing.JButton();
        jFrame3 = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCard = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTenCard = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnThemSP2 = new javax.swing.JButton();
        btnSuaSP2 = new javax.swing.JButton();
        jFrame4 = new javax.swing.JFrame();
        txtTenHang = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnThemSP3 = new javax.swing.JButton();
        btnSuaSP3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHang = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jFrame5 = new javax.swing.JFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblOCung = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtTenOCung = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btnThemSP4 = new javax.swing.JButton();
        btnSuaSP4 = new javax.swing.JButton();
        jFrame6 = new javax.swing.JFrame();
        jLabel15 = new javax.swing.JLabel();
        txtTenRam = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnThemSP5 = new javax.swing.JButton();
        btnSuaSP5 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblRam = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
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
        btnExport = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtSerialSP = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        cbbMaSP = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();

        jFrame1.setSize(new java.awt.Dimension(570, 308));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP7.setText("Thêm");
        btnThemSP7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP7ActionPerformed(evt);
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
                .addGap(29, 29, 29)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemSP7, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenCPU1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTenCPU1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jFrame2.setSize(new java.awt.Dimension(570, 308));

        tblCPU.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCPU.setColumnSelectionAllowed(true);
        tblCPU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCPUMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCPU);
        tblCPU.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tên CPU");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP1.setText("Thêm");
        btnThemSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP1ActionPerformed(evt);
            }
        });

        btnSuaSP1.setText("Sửa");
        btnSuaSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemSP1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jFrame3.setSize(new java.awt.Dimension(570, 308));

        tblCard.setModel(new javax.swing.table.DefaultTableModel(
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

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP2.setText("Thêm");
        btnThemSP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP2ActionPerformed(evt);
            }
        });

        btnSuaSP2.setText("Sửa");
        btnSuaSP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemSP2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGroup(jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenCard, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGroup(jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTenCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jFrame4.setSize(new java.awt.Dimension(570, 308));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP3.setText("Thêm");
        btnThemSP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP3ActionPerformed(evt);
            }
        });

        btnSuaSP3.setText("Sửa");
        btnSuaSP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP3, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemSP3, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tblHang.setModel(new javax.swing.table.DefaultTableModel(
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

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame4Layout.createSequentialGroup()
                .addGroup(jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jFrame4Layout.createSequentialGroup()
                        .addContainerGap(43, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame4Layout.createSequentialGroup()
                .addGroup(jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jFrame5.setSize(new java.awt.Dimension(570, 308));

        tblOCung.setModel(new javax.swing.table.DefaultTableModel(
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

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP4.setText("Thêm");
        btnThemSP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP4ActionPerformed(evt);
            }
        });

        btnSuaSP4.setText("Sửa");
        btnSuaSP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP4, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemSP4, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame5Layout = new javax.swing.GroupLayout(jFrame5.getContentPane());
        jFrame5.getContentPane().setLayout(jFrame5Layout);
        jFrame5Layout.setHorizontalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame5Layout.createSequentialGroup()
                .addGroup(jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame5Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jFrame5Layout.createSequentialGroup()
                        .addContainerGap(47, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jFrame5Layout.setVerticalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame5Layout.createSequentialGroup()
                .addGroup(jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTenOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jFrame6.setSize(new java.awt.Dimension(570, 308));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Tên Ram");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThemSP5.setText("Thêm");
        btnThemSP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP5ActionPerformed(evt);
            }
        });

        btnSuaSP5.setText("Sửa");
        btnSuaSP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSP5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP5, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnThemSP5, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tblRam.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRam.setColumnSelectionAllowed(true);
        tblRam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRamMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblRam);
        tblRam.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jFrame6Layout = new javax.swing.GroupLayout(jFrame6.getContentPane());
        jFrame6.getContentPane().setLayout(jFrame6Layout);
        jFrame6Layout.setHorizontalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame6Layout.createSequentialGroup()
                .addGroup(jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame6Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenRam, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jFrame6Layout.createSequentialGroup()
                        .addContainerGap(43, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jFrame6Layout.setVerticalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame6Layout.createSequentialGroup()
                .addGroup(jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame6Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTenRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
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

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Tên SP");

        txtTenSP1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Tìm kiếm");

        txtTimKiemSP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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

        jLabel4.setIcon(new ImageIcon("src/main/img/Lap.jpg"));

        btnThemSP6.setBackground(new java.awt.Color(0, 153, 153));
        btnThemSP6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemSP6.setIcon(new ImageIcon("src/main/img/addNV.png"));
        btnThemSP6.setText("Thêm");
        btnThemSP6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSP6ActionPerformed(evt);
            }
        });

        btnSuaSP6.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaSP6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaSP6.setIcon(new ImageIcon("src/main/img/updateNV.png"));
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
                .addGap(28, 28, 28)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnThemSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

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

        btnCPU.setBackground(new java.awt.Color(0, 153, 153));
        btnCPU.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCPU.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnCPU.setToolTipText("Thêm cpu");
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });

        btnHang.setBackground(new java.awt.Color(0, 153, 153));
        btnHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHang.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnHang.setToolTipText("Thêm hãng");
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        cbbHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnOCung.setBackground(new java.awt.Color(0, 153, 153));
        btnOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnOCung.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnOCung.setToolTipText("Thêm ổ cứng");
        btnOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOCungActionPerformed(evt);
            }
        });

        cbbCard.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbRam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnRam.setBackground(new java.awt.Color(0, 153, 153));
        btnRam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnRam.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnRam.setToolTipText("Thêm ram");
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });

        btnCard.setBackground(new java.awt.Color(0, 153, 153));
        btnCard.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCard.setIcon(new ImageIcon("src/main/img/newNV.png"));
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
                .addContainerGap(226, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(cbbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(btnCPU, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
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

        txtLoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoc2ActionPerformed(evt);
            }
        });

        txtLoc1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLoc1CaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lọc giá");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("--");

        btnXuatFiel.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatFiel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatFiel.setIcon(new ImageIcon("src/main/img/import.png"));
        btnXuatFiel.setText("EXPORT");
        btnXuatFiel.setToolTipText("Xuất ra file excel");
        btnXuatFiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFielActionPerformed(evt);
            }
        });

        btnExport.setBackground(new java.awt.Color(0, 153, 153));
        btnExport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExport.setIcon(new ImageIcon("src/main/img/export.png"));
        btnExport.setText("IMPORT");
        btnExport.setToolTipText("Thêm nhiều sản phẩm cùng lúc");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tblChiTietSanPhamLayout = new javax.swing.GroupLayout(tblChiTietSanPham);
        tblChiTietSanPham.setLayout(tblChiTietSanPhamLayout);
        tblChiTietSanPhamLayout.setHorizontalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap(509, Short.MAX_VALUE)
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                        .addComponent(btnXuatFiel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        tblChiTietSanPhamLayout.setVerticalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatFiel)
                    .addComponent(btnExport))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setIcon(new ImageIcon("src/main/img/addNV.png"));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 153));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setIcon(new ImageIcon("src/main/img/updateNV.png"));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 153));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setIcon(new ImageIcon("src/main/img/close.png"));
        btnXoa.setText("Ngừng bán");
        btnXoa.setToolTipText("Ngừng kinh doanh sản phẩm");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Tìm kiếm ");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        txtGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Serial");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Giá");

        txtSerialSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnLamMoi.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoi.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        cbbMaSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Tên sản phẩm");

        jButton6.setBackground(new java.awt.Color(0, 153, 153));
        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton6.setIcon(new ImageIcon("src/main/img/newNV.png"));
        jButton6.setToolTipText("Thêm sản phẩm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnKhoiPhuc.setBackground(new java.awt.Color(0, 153, 153));
        btnKhoiPhuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKhoiPhuc.setIcon(new ImageIcon("src/main/img/boloc.png"));
        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.setToolTipText("Ngừng kinh doanh sản phẩm");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSerialSP, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                            .addComponent(txtGia)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSerialSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
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

    private void btnThemSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP1ActionPerformed
        CPU sp = new CPU("CPU" + (listCPU.size() + 1), txtTenCPU.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceCPU.add(sp));
        listCPU = serviceCPU.getAll();
        showDataTableCPU(listCPU);
        cbbCPU.removeAllItems();
        showCPU();
    }//GEN-LAST:event_btnThemSP1ActionPerformed

    private void btnSuaSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP1ActionPerformed
        CPU sPham = listCPU.get(tblCPU.getSelectedRow());
        CPU sp = new CPU(sPham.getTen(), txtTenCPU.getText(), new Date(), sPham.getAlstModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceCPU.upDate(sp, id));
        listCPU = serviceCPU.getAll();
        showDataTableCPU(listCPU);
        cbbCPU.removeAllItems();
        showCPU();
    }//GEN-LAST:event_btnSuaSP1ActionPerformed

    private void tblCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCardMouseClicked
        int indexx = tblCard.getSelectedRow();
        fillCard(indexx, listCard);
    }//GEN-LAST:event_tblCardMouseClicked

    private void btnThemSP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP2ActionPerformed
        CardMH sp = new CardMH("Card" + (listCard.size() + 1), txtTenCard.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceCard.add(sp));
        listCard = serviceCard.getAll();
        showDataTableCard(listCard);
        cbbCard.removeAllItems();
        showCard();
    }//GEN-LAST:event_btnThemSP2ActionPerformed

    private void btnSuaSP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP2ActionPerformed
        CardMH sPham = listCard.get(tblCard.getSelectedRow());
        CardMH sp = new CardMH(sPham.getMa(), txtTenCard.getText(), new Date(), sPham.getAlstModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceCard.upDate(sp, id));
        listCard = serviceCard.getAll();
        showDataTableCard(listCard);
        cbbCard.removeAllItems();
        showCard();
    }//GEN-LAST:event_btnSuaSP2ActionPerformed

    private void btnThemSP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP3ActionPerformed
        Hang sp = new Hang("H" + (listHang.size() + 1), txtTenHang.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceHang.add(sp));
        listHang = serviceHang.getAll();
        showDataTableHang(listHang);
        cbbHang.removeAllItems();
        showHang();
    }//GEN-LAST:event_btnThemSP3ActionPerformed

    private void btnSuaSP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP3ActionPerformed
        Hang sPham = listHang.get(tblHang.getSelectedRow());
        Hang sp = new Hang(sPham.getMa(), txtTenHang.getText(), new Date(), sPham.getAlstModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceHang.upDate(sp, id));
        listHang = serviceHang.getAll();
        showDataTableHang(listHang);
        cbbHang.removeAllItems();
        showHang();
    }//GEN-LAST:event_btnSuaSP3ActionPerformed

    private void tblHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangMouseClicked
        int indexx = tblHang.getSelectedRow();
        fillHang(indexx, listHang);
    }//GEN-LAST:event_tblHangMouseClicked

    private void tblOCungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOCungMouseClicked
        int indexx = tblOCung.getSelectedRow();
        fillOCung(indexx, listOCung);
    }//GEN-LAST:event_tblOCungMouseClicked

    private void btnThemSP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP4ActionPerformed
        OCung sp = new OCung("OCung" + (listOCung.size() + 1), txtTenOCung.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceOCung.add(sp));
        listOCung = serviceOCung.getAll();
        showDataTableOCung(listOCung);
        cbbOCung.removeAllItems();
        showOCung();
    }//GEN-LAST:event_btnThemSP4ActionPerformed

    private void btnSuaSP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP4ActionPerformed
        OCung sPham = listOCung.get(tblOCung.getSelectedRow());
        OCung sp = new OCung(sPham.getMa(), txtTenOCung.getText(), new Date(), sPham.getAlstModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceOCung.upDate(sp, id));
        listOCung = serviceOCung.getAll();
        showDataTableOCung(listOCung);
        cbbOCung.removeAllItems();
        showOCung();
    }//GEN-LAST:event_btnSuaSP4ActionPerformed

    private void btnThemSP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP5ActionPerformed
        Ram sp = new Ram("Ram" + (listRam.size() + 1), txtTenRam.getText(), new Date(), new Date());
        JOptionPane.showMessageDialog(this, serviceRam.add(sp));
        listRam = serviceRam.getAll();
        showDataTableRam(listRam);
        cbbRam.removeAllItems();
        showRam();
    }//GEN-LAST:event_btnThemSP5ActionPerformed

    private void btnSuaSP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP5ActionPerformed
        Ram sPham = listRam.get(tblRam.getSelectedRow());
        Ram sp = new Ram(sPham.getMa(), txtTenRam.getText(), new Date(), sPham.getAlstModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, serviceRam.upDate(sp, id));
        listRam = serviceRam.getAll();
        showDataTableRam(listRam);
        cbbRam.removeAllItems();
        showRam();
    }//GEN-LAST:event_btnSuaSP5ActionPerformed

    private void tblRamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRamMouseClicked
        int indexx = tblRam.getSelectedRow();
        fillRam(indexx, listRam);
    }//GEN-LAST:event_tblRamMouseClicked
    SanPham getData1() {
        return new SanPham("SP" + (listSanPham.size() + 1), txtTenCPU1.getText(), new Date(), new Date());
    }
    private void btnThemSP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP7ActionPerformed
        JOptionPane.showMessageDialog(this, service.addSanPham(getData1()));
        listSanPham = service.getAllSanPham();
        showDataTable1(listSanPham);
        listSP = service.getAllSP();
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnThemSP7ActionPerformed

    private void btnSuaSP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP7ActionPerformed
        SanPham sPham = listSanPham.get(tblSP1.getSelectedRow());
        SanPham sp = new SanPham(sPham.getMa(), txtTenCPU1.getText(), new Date(), sPham.getLastModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, service.upDateSanPham(sp, id));
        listSanPham = service.getAllSanPham();
        showDataTable1(listSanPham);
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnSuaSP7ActionPerformed

    private void tblSP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSP1MouseClicked
        int indexx = tblSP1.getSelectedRow();
        fillSP1(indexx, listSP);
    }//GEN-LAST:event_tblSP1MouseClicked

    private void btnThemSP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSP6ActionPerformed

        JOptionPane.showMessageDialog(this, service.addSanPham(getData()));
        listSanPham = service.getAllSanPham();
        showDataTable(listSanPham);
        showDataTable1(listSanPham);
        listSP = service.getAllSP();
        cbbMaSP.removeAllItems();
        showMaSP();
    }//GEN-LAST:event_btnThemSP6ActionPerformed

    private void btnSuaSP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSP6ActionPerformed
        SanPham sPham = listSanPham.get(tblSanPham1.getSelectedRow());
        SanPham sp = new SanPham(sPham.getMa(), txtTenSP1.getText(), new Date(), sPham.getLastModifiedDate());
        UUID id = sPham.getId();
        JOptionPane.showMessageDialog(this, service.upDateSanPham(getData(), id));
        listSanPham = service.getAllSanPham();
        showDataTable(listSanPham);
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
        fillSP(indexx, listSP);
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
        File file;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        XSSFWorkbook wb = null;
        String path = "D:";
        JFileChooser exChooser = new JFileChooser(path);
        int results = exChooser.showOpenDialog(null);
        if (results == JFileChooser.APPROVE_OPTION) {
            file = exChooser.getSelectedFile();
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

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Danh sách chi tiết sản phẩm");
            cell.setCellStyle(tieuDeStyle);
            row = spreadsheet.createRow((short) 3);
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
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(ctcp.getIdSanPham().getTen());
                row.createCell(2).setCellValue(ctcp.getIdCPU().getTen());
                row.createCell(3).setCellValue(ctcp.getIdCard().getTen());
                row.createCell(4).setCellValue(ctcp.getIdHang().getTen());
                //                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //                String date = dateFormat.format(nv.getNgaySinh());
                row.createCell(5).setCellValue(ctcp.getIdOCung().getTen());
                row.createCell(6).setCellValue(ctcp.getIdRam().getTen());
                row.createCell(7).setCellValue(ctcp.getSerial());
                row.createCell(8).setCellValue(String.valueOf(ctcp.getGia()));
                row.createCell(9).setCellValue(String.valueOf(ctcp.getTinhTrang()== 0 ? "Đang bán": ctcp.getTinhTrang() == 1 ?"Đã bán":"Ngừng bán"));
            }
            FileOutputStream out = new FileOutputStream(new File("D:/ExportSanPham .xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất file excel thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại");
        }
    }
    }//GEN-LAST:event_btnXuatFielActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed

        createWindow();
    }//GEN-LAST:event_btnExportActionPerformed

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
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
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
        List<ChiTietSP> listCT = new ArrayList<>();
        for (int i = 0; i < tblBangChiTietSP.getRowCount(); i++) {
            Boolean check = (Boolean) tblBangChiTietSP.getValueAt(i, 0);
            if (check == true) {
                listCT.add(listCTsp.get(i));
            }
        }
        JOptionPane.showMessageDialog(this, serviceCT.updateTinhTrang(listCT));
        listCTsp = serviceCT.getAllChiTietSP();
        showData(listCTsp);
    }//GEN-LAST:event_btnXoaActionPerformed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jFrame1.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
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
        List<ChiTietSP> listChiTiet = new ArrayList<>();
        for (int i = 0; i < tblBangChiTietSP.getRowCount(); i++) {
            Boolean check = (Boolean) tblBangChiTietSP.getValueAt(i, 0);
            if (check == true) {
                listChiTiet.add(listCTsp.get(i));
            }
        }
        JOptionPane.showMessageDialog(this, serviceCT.updateKhoiPhuc(listChiTiet));
        listCTsp = serviceCT.getAllChiTietSP();
        showData(listCTsp);
    }//GEN-LAST:event_btnKhoiPhucActionPerformed
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
                ChiTietSP chiTietSP = new ChiTietSP(maCTSP, sanPham, soSerial, cpu, hang, ram, card, oCung, BigDecimal.ONE,tinhTrang);
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
            JOptionPane.showMessageDialog(this, "Thành công");
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
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnOCung;
    private javax.swing.JButton btnRam;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaSP1;
    private javax.swing.JButton btnSuaSP2;
    private javax.swing.JButton btnSuaSP3;
    private javax.swing.JButton btnSuaSP4;
    private javax.swing.JButton btnSuaSP5;
    private javax.swing.JButton btnSuaSP6;
    private javax.swing.JButton btnSuaSP7;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemSP1;
    private javax.swing.JButton btnThemSP2;
    private javax.swing.JButton btnThemSP3;
    private javax.swing.JButton btnThemSP4;
    private javax.swing.JButton btnThemSP5;
    private javax.swing.JButton btnThemSP6;
    private javax.swing.JButton btnThemSP7;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFiel;
    private javax.swing.JComboBox<String> cbbCPU;
    private javax.swing.JComboBox<String> cbbCard;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JComboBox<String> cbbOCung;
    private javax.swing.JComboBox<String> cbbRam;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
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
