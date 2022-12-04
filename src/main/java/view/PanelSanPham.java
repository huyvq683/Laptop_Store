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
import java.awt.Image;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
        showMa();
        showData(listCTsp);

    }

    public void showMa() {
        for (SanPham x : listSanPham) {
            dfcbbSP.addElement(x.getTen());
        }
        for (CPU x : listCPU) {
            dfcbbCPU.addElement(x.getTen());
        }
        for (Hang x : listHang) {
            dfcbbHang.addElement(x.getTen());
        }
        for (Ram x : listRam) {
            dfcbbRam.addElement(x.getTen());
        }
        for (CardMH x : listCard) {
            dfcbbCard.addElement(x.getTen());
        }
        for (OCung x : listOCung) {
            dfcbbOCung.addElement(x.getTen());
        }
    }

    public void showData(List<ChiTietSP> ctsp) {
        modelCTSP = (DefaultTableModel) tblBangChiTietSP.getModel();
        modelCTSP.setRowCount(0);
        int stt = 1;
        for (ChiTietSP x : ctsp) {
            modelCTSP.addRow(new Object[]{stt, x.getIdSanPham().getTen(), x.getIdCPU().getTen(), x.getIdCard().getTen(), x.getIdHang().getTen(), x.getIdOCung().getTen(), x.getIdRam().getTen(), x.getSerial(), x.getGia()});
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnMa = new javax.swing.JButton();
        cbbCPU = new javax.swing.JComboBox<>();
        btnCPU = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        cbbHang = new javax.swing.JComboBox<>();
        cbbMaSP = new javax.swing.JComboBox<>();
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
        jPanel2 = new javax.swing.JPanel();
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

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Sản phẩm");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("CPU");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Hãng");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Ram");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Card");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ổ Cứng");

        btnMa.setBackground(new java.awt.Color(0, 153, 153));
        btnMa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnMa.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaActionPerformed(evt);
            }
        });

        cbbCPU.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnCPU.setBackground(new java.awt.Color(0, 153, 153));
        btnCPU.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCPU.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });

        btnHang.setBackground(new java.awt.Color(0, 153, 153));
        btnHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHang.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        cbbHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbMaSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnOCung.setBackground(new java.awt.Color(0, 153, 153));
        btnOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnOCung.setIcon(new ImageIcon("src/main/img/newNV.png"));
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
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });

        btnCard.setBackground(new java.awt.Color(0, 153, 153));
        btnCard.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCard.setIcon(new ImageIcon("src/main/img/newNV.png"));
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
                .addGap(77, 77, 77)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbMaSP, javax.swing.GroupLayout.Alignment.TRAILING, 0, 152, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCPU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbCPU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbHang, 0, 152, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOCung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbOCung, 0, 152, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbRam, 0, 152, Short.MAX_VALUE)
                        .addComponent(btnCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbbCard, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(277, 277, 277))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(cbbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel41)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(btnCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCPU, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))))
        );

        tblChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tblChiTietSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tblBangChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "CPU", "Card", "Hãng", "Ổ Cứng", "Ram", "Serial", "Gía"
            }
        ));
        tblBangChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblBangChiTietSP);

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
        jLabel2.setText("Lọc");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("--");

        btnXuatFiel.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatFiel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatFiel.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatFiel.setIcon(new ImageIcon("src/main/img/import.png"));
        btnXuatFiel.setText("EXPORT");
        btnXuatFiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFielActionPerformed(evt);
            }
        });

        btnExport.setBackground(new java.awt.Color(0, 153, 153));
        btnExport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new ImageIcon("src/main/img/export.png"));
        btnExport.setText("IMPORT");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tblChiTietSanPhamLayout = new javax.swing.GroupLayout(tblChiTietSanPham);
        tblChiTietSanPham.setLayout(tblChiTietSanPhamLayout);
        tblChiTietSanPhamLayout.setHorizontalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblChiTietSanPhamLayout.createSequentialGroup()
                        .addComponent(btnXuatFiel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new ImageIcon("src/main/img/addNV.png"));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 153));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new ImageIcon("src/main/img/updateNV.png"));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 153));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new ImageIcon("src/main/img/close.png"));
        btnXoa.setText("Xóa");
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
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new ImageIcon("src/main/img/newNV.png"));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSerialSP)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(txtGia)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSerialSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaActionPerformed
        ViewSP viewSP = new ViewSP();
        viewSP.setVisible(true);
        viewSP.setDefaultCloseOperation(ViewSP.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnMaActionPerformed

    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        ViewCPU viewSP = new ViewCPU();
        viewSP.setVisible(true);
        viewSP.setDefaultCloseOperation(ViewCPU.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        ViewHang viewSP = new ViewHang();
        viewSP.setVisible(true);
        viewSP.setDefaultCloseOperation(ViewHang.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOCungActionPerformed
        ViewOCung viewSP = new ViewOCung();
        viewSP.setVisible(true);
        viewSP.setDefaultCloseOperation(ViewOCung.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnOCungActionPerformed

    private void btnRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamActionPerformed
        ViewRam viewSP = new ViewRam();
        viewSP.setVisible(true);
        viewSP.setDefaultCloseOperation(ViewRam.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnRamActionPerformed

    private void btnCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardActionPerformed
        ViewCard viewSP = new ViewCard();
        viewSP.setVisible(true);
        viewSP.setDefaultCloseOperation(ViewCard.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnCardActionPerformed

    private void tblBangChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangChiTietSPMouseClicked
        int indexx = tblBangChiTietSP.getSelectedRow();
        mountClick(listCTsp, indexx);
    }//GEN-LAST:event_tblBangChiTietSPMouseClicked

    private void txtLoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoc2ActionPerformed
        String loc1 = txtLoc2.getText();
        String loc2 = txtLoc1.getText();
        if (loc1.isEmpty() && loc2.isEmpty()) {
            showData(listCTsp);
        } else {
            listCTsp = serviceCT.getOneGia(loc1, loc2);
            showData(listCTsp);
        }
    }//GEN-LAST:event_txtLoc2ActionPerformed

    private void txtLoc1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLoc1CaretUpdate
        String loc1 = txtLoc1.getText();
        String loc2 = txtLoc2.getText();
        if (loc1.isEmpty() && loc2.isEmpty()) {
            showData(listCTsp);
        } else {
            listCTsp = serviceCT.getOneGia(loc2, loc1);
            showData(listCTsp);
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
                //                row.createCell(8).setCellValue(nv.getChucVu() == 0 ? "Quản lý" : "Nhân viên");
                //                row.createCell(9).setCellValue(nv.getTrangThai() == 0 ? "Đang làm việc" : "Đã nghỉ việc");
            }
            FileOutputStream out = new FileOutputStream(new File("E:/DuAn1/ExportSanPham .xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Export thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Export thất bại");
        }
    }//GEN-LAST:event_btnXuatFielActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        //        doc();
        //        final String excelFilePath = "E:\\DuAn1\\ExportSanPham.xlsx";
        //        final List<ChiTietSP> books;
        //        final List<SanPham> sPham;
        //        try {
        //            books = readExcel(excelFilePath);
        //            sPham = readExcelSP(excelFilePath);
        //            for (SanPham x : sPham) {
        //                JOptionPane.showMessageDialog(this, service.addSanPham(x));
        //                listSanPham = service.getAllSanPham();
        //                System.out.println(sPham);
        //            }
        //            for (ChiTietSP book : books) {
        //                JOptionPane.showMessageDialog(this, serviceCT.add(book));
        //                listCTsp = serviceCT.getAllChiTietSP();
        //                showDataCTSP(listCTsp);
        //                System.out.println(book);
        //            }
        //        } catch (IOException ex) {
        //            Logger.getLogger(PanelSanPham.class.getName()).log(Level.SEVERE, null, ex);
        //        }

        File file;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        XSSFWorkbook wb = null;
        String path = "E:";
        JFileChooser exChooser = new JFileChooser(path);
        int result = exChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                file = exChooser.getSelectedFile();
                String name = file.getPath();
                System.out.println(name);
                List<ChiTietSP> listIm = importExcel(name);
                int i = 0;
                do {
                    for (ChiTietSP nhanVien : listIm) {
                        //                        if (nhanVien.getHoTen().isEmpty()) {
                        //                            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!!");
                        //                            listIm.clear();
                        //                            return;
                        //
                        if (listCTsp.size() < 1) {
                            serviceCT.getAllChiTietSP();
                            i += listIm.size();
                        } else {
                            if (serviceCT.getOne(nhanVien.getSerial()) != null) {
                                JOptionPane.showMessageDialog(this, "Serial đã tồn tại!!");
                                listIm.remove(nhanVien);
                                return;
                            } else {
                                serviceCT.add(nhanVien);
                                System.out.println(nhanVien);
                                i += listIm.size();
                            }
                        }
                    }

                } while (i < listIm.size());
                //                JOptionPane.showMessageDialog(this, "Hệ thống đang gửi email, vui lòng chờ...");
                //                listIm.forEach(s -> guiMail(s.getEmail()));
                //                JOptionPane.showMessageDialog(this, "Gửi email cho nhân viên hoàn tất!!");
                //                JOptionPane.showMessageDialog(this, serviceCT.add());
                listCTsp = serviceCT.getAllChiTietSP();

                //                list = nhanVienServiceImpl.getAllPage(0);
                showData(listCTsp);
            } catch (Exception e) {
                //                e.printStackTrace(System.out);
            }
        }
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
            ChiTietSP ctsp = new ChiTietSP(sp, txtSerialSP.getText(), cpu, hang, ram, card, oCung, big, 0, new Date(), new Date());
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
        JOptionPane.showMessageDialog(this, serviceCT.delete(ctsp, id));
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
    private List<ChiTietSP> importExcel(String fileName) throws IOException {
        List<ChiTietSP> listNVien = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            // Khởi tạo workbook cho tệp xlsx 
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // Lấy worksheet đầu tiên trong workbook
            Sheet worksheet = workbook.getSheetAt(0);
            // Duyệt qua từng row
            for (Row row : worksheet) {
//                if (row.getRowNum() == 0) {
//                    continue;
//                }
//                if (row.getCell(0).getCellType() == CellType.STRING){
//                    continue;
//                }
                listCTsp = serviceCT.getAllChiTietSP();
                ChiTietSP ctsp = new ChiTietSP();
                SanPham sp = new SanPham();
                ctsp.setIdSanPham(service.getOneSP(String.valueOf(getCellValue(row.getCell(1))).trim()));
                ctsp.setIdCPU(serviceCPU.getOneCPU(String.valueOf(getCellValue(row.getCell(2))).trim()));
                ctsp.setIdCard(serviceCard.getOneCard(String.valueOf(getCellValue(row.getCell(3))).trim()));
                ctsp.setIdHang(serviceHang.getOneHang(String.valueOf(getCellValue(row.getCell(4))).trim()));
                ctsp.setIdOCung(serviceOCung.getOneOCung(String.valueOf(getCellValue(row.getCell(5))).trim()));
                ctsp.setIdRam(serviceRam.getOneRam(String.valueOf(getCellValue(row.getCell(6))).trim()));
                ctsp.setSerial(String.valueOf(getCellValue(row.getCell(7))).trim());
                ctsp.setGia(new BigDecimal(String.valueOf(getCellValue(row.getCell(8))).trim()));
//                SimpleDateFormat convertToDate = new SimpleDateFormat("dd-MM-yyyy");
//                boolean gioiTinh;
//                if (String.valueOf(getCellValue(row.getCell(1))).trim().equalsIgnoreCase("Nam")) {
//                    gioiTinh = true;
//                } else {
//                    gioiTinh = false;
//                }
//                nhanVien.setGioiTinh(gioiTinh);
//                SimpleDateFormat convertToDate = new SimpleDateFormat("dd-MM-yyyy");
//                try {
//                    Date date = convertToDate.parse(String.valueOf(getCellValue(row.getCell(2))).trim());
//                    nhanVien.setNgaySinh(date);
//                } catch (Exception e) {
//                    e.printStackTrace(System.out);
//                }
//                nhanVien.setDiaChi(String.valueOf(getCellValue(row.getCell(3))).trim());
//                nhanVien.setSdt(String.valueOf(getCellValue(row.getCell(4))).trim());
//                nhanVien.setEmail(String.valueOf(getCellValue(row.getCell(5))).trim());
//                if (String.valueOf(getCellValue(row.getCell(6))).trim().equalsIgnoreCase("Quản lý")) {
//                    nhanVien.setChucVu(0);
//                } else {
//                    nhanVien.setChucVu(1);
//                }
//                nhanVien.setMatKhau(getMd5("123456"));
//                if (String.valueOf(getCellValue(row.getCell(7))).trim().equalsIgnoreCase("Đang làm việc")) {
//                    nhanVien.setTrangThai(0);
//                } else {
//                    nhanVien.setTrangThai(1);
//                }
//                nhanVien.setCreatedDate(new Date());
//                nhanVien.setLastModifiedDate(new Date());
                listNVien.add(ctsp);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return listNVien;
    }

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPU;
    private javax.swing.JButton btnCard;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnMa;
    private javax.swing.JButton btnOCung;
    private javax.swing.JButton btnRam;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFiel;
    private javax.swing.JComboBox<String> cbbCPU;
    private javax.swing.JComboBox<String> cbbCard;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JComboBox<String> cbbOCung;
    private javax.swing.JComboBox<String> cbbRam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblBangChiTietSP;
    private javax.swing.JPanel tblChiTietSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtLoc1;
    private javax.swing.JTextField txtLoc2;
    private javax.swing.JTextField txtSerialSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
