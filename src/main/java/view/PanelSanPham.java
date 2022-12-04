/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.ChiTietSP;
import domainmodel.SanPham;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.ChiTietSPService;
import service.SanPhamService;
import service.impl.ChiTietSPServiceImql;
import service.impl.SanPhamServiceImpl;

/**
 *
 * @author huyxo
 */
public class PanelSanPham extends javax.swing.JPanel {

    DefaultTableModel modelSP = new DefaultTableModel();
    SanPhamService service = new SanPhamServiceImpl();
    List<SanPham> listSanPham = service.getAllSanPham();

    DefaultTableModel modelCTSP = new DefaultTableModel();
    DefaultComboBoxModel dfCTSP = new DefaultComboBoxModel();
    ChiTietSPService serviceCT = new ChiTietSPServiceImql();
    List<ChiTietSP> listCTsp = serviceCT.getAllChiTietSP();

    /**
     * Creates new form PanelSanPham
     */
    public PanelSanPham() {
        initComponents();
        showDataCTSP(listCTsp);
    }

    String duongDanAnh = "src\\main\\img\\anh1.jpg";

    public ImageIcon getImg(String ImagePath) {
        ImageIcon myImage = new ImageIcon(ImagePath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(lblAnhSP.getWidth(), lblAnhSP.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    ///// San Pham
    private void fill(int index) {
        SanPham sp = listSanPham.get(index);
        txtMaSanPham.setText(sp.getMa());
        txtTenSanPham.setText(sp.getTen());
    }

    private void showDataTable(List<SanPham> listss) {
        modelSP.setRowCount(0);
        for (SanPham x : listss) {
            modelSP.addRow(new Object[]{x.getMa(), x.getTen(), x.conVert(x.getCreatedDate()), x.conVert(x.getAlstModifiedDate())});
        }
    }

    ///// Chi Tiet San Pham
    public void showDataCTSP(List<ChiTietSP> ctsp) {
        modelCTSP = (DefaultTableModel) tblBangChiTietSP.getModel();
        modelCTSP.setRowCount(0);
        int stt = 1;
        for (ChiTietSP x : ctsp) {
            modelCTSP.addRow(new Object[]{stt, x.getIdSanPham().getMa(), x.getSerial(), x.getCPU(), x.getHang(), x.getRam(), x.getCard(), x.getOCung(), x.getGia(), x.conVert(x.getCreatedDate()), x.conVert(x.getLastModifiedDate()), duongDanAnh});
            stt++;
        }
    }

    public void mountClick(List<ChiTietSP> ctsp, int index) {
        txtMaSanPham.setText(ctsp.get(index).getIdSanPham().getMa().toString());
        txtTenSanPham.setText(ctsp.get(index).getIdSanPham().getTen().toString());
        txtCardSP.setText(ctsp.get(index).getCard().toString());
        txtCpuSP.setText(ctsp.get(index).getCPU().toString());
        txtGiaSP.setText(ctsp.get(index).getGia().toString());
        txtHangSP.setText(ctsp.get(index).getHang().toString());
        txtRamSP.setText(ctsp.get(index).getRam().toString());
        txtOCung.setText(ctsp.get(index).getOCung().toString());
        txtSerialSP.setText(ctsp.get(index).getSerial().toString());
//        lblAnhSP.setText(duongDanAnh);
    }

    public SanPham getDataSP() {
        return new SanPham(txtMaSanPham.getText(), txtTenSanPham.getText(), new Date(), new Date());
    }

    public ChiTietSP getDataCTSP() {
        SanPham sanpham = service.getOneSP(txtMaSanPham.getText());
        BigDecimal big = new BigDecimal(txtGiaSP.getText());
        return new ChiTietSP(sanpham, txtSerialSP.getText(), txtCpuSP.getText(), txtHangSP.getText(), txtRamSP.getText(), txtCardSP.getText(), txtOCung.getText(), big, duongDanAnh, 0, new Date(), new Date());
    }

    //// EXPORT excel
    public void ghi() {
        ChiTietSPService serviceCT = new ChiTietSPServiceImql();
        List<ChiTietSP> listCTsp = serviceCT.getAllChiTietSP();
        System.out.println("Create file excel");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Customer_Info");
        int rowNum = 0;
        Row firstRow = sheet.createRow(rowNum++);
        Cell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("List of Customer");
        List<ChiTietSP> listOfCustomer = serviceCT.getAllChiTietSP();
        int stt = 1;
        for (ChiTietSP customer : listOfCustomer) {
            Row row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(customer.getIdSanPham().getTen());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(customer.getSerial());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(customer.getCPU());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(customer.getHang());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(customer.getRam());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(customer.getCard());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(customer.getOCung());
            Cell cell8 = row.createCell(7);
            cell8.setCellValue(String.valueOf(customer.getGia()));
            Cell cell9 = row.createCell(8);
            cell9.setCellValue(customer.conVert(customer.getCreatedDate()));
            Cell cell10 = row.createCell(9);
            cell10.setCellValue(customer.conVert(customer.getLastModifiedDate()));
            Cell cell11 = row.createCell(10);
            cell11.setCellValue(customer.getAnh());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream("E:\\DuAn1\\ImportSanPham.xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Import thành công");
    }

//    public void doc() {
//        try {
//            FileInputStream excelFile = new FileInputStream(new File("E:\\DuAn1\\ExportSanPham.xlsx"));
//            Workbook workbook = new XSSFWorkbook(excelFile);
//            Sheet datatypeSheet = workbook.getSheetAt(0);
//            DataFormatter fmt = new DataFormatter();
//            Iterator<Row> iterator = datatypeSheet.iterator();
//            Row firstRow = iterator.next();
//            Cell firstCell = firstRow.getCell(0);
//            System.out.println(firstCell.getStringCellValue());
//            List<ChiTietSP> listOfCustomer = new ArrayList<>();
//            while (iterator.hasNext()) {
//                Row currentRow = iterator.next();
//                ChiTietSP customer = new ChiTietSP();
//                SanPham sp = new SanPham();
//                sp.setMa(currentRow.getCell(0).getStringCellValue());
//                customer.setSerial(currentRow.getCell(1).getStringCellValue());
//                customer.setCPU(currentRow.getCell(2).getStringCellValue());
//                customer.setHang(currentRow.getCell(3).getStringCellValue());
//                customer.setRam(currentRow.getCell(4).getStringCellValue());
//                customer.setCard(currentRow.getCell(5).getStringCellValue());
//                customer.setOCung(currentRow.getCell(6).getStringCellValue());
//                customer.setGia(new BigDecimal(currentRow.getCell(7).getStringCellValue()));
//                customer.setCreatedDate(new Date(currentRow.getCell(8).getStringCellValue()));
//                customer.setLastModifiedDate(new Date(currentRow.getCell(9).getStringCellValue()));
//                customer.setAnh(currentRow.getCell(10).getStringCellValue());
//                listOfCustomer.add(customer);
//            }
//            for (ChiTietSP customer : listOfCustomer) {
//                JOptionPane.showMessageDialog(this, serviceCT.add(customer));
//                listCTsp = serviceCT.getAllChiTietSP();
//                showDataCTSP(listCTsp);
//            }
//            workbook.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static final int MASP = 0;
    public static final int SERIAL = 1;
    public static final int CPU = 2;
    public static final int HANG = 3;
    public static final int RAM = 4;
    public static final int CARDMH = 5;
    public static final int OCUNG = 6;
    public static final int GIA = 7;

    public static List<ChiTietSP> readExcel(String excelFilePath) throws IOException {
        List<ChiTietSP> listBooks = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            ChiTietSP book = new ChiTietSP();
            SanPhamService service = new SanPhamServiceImpl();
            List<SanPham> listSanPham = service.getAllSanPham();
            SanPham sp = new SanPham();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case MASP:
                        sp.setMa((String) getCellValue(cell));
                        book.setIdSanPham(sp);
                        break;
                    case SERIAL:
                        book.setSerial((String) getCellValue(cell));
                        break;
                    case CPU:
                        book.setCPU((String) getCellValue(cell));
                        break;
                    case HANG:
                        book.setHang((String) getCellValue(cell));
                        break;
                    case RAM:
                        book.setRam((String) getCellValue(cell));
                        break;
                    case CARDMH:
                        book.setCard((String) getCellValue(cell));
                        break;
                    case OCUNG:
                        book.setOCung((String) getCellValue(cell));
                        break;
                    case GIA:
                        book.setGia(new BigDecimal(String.valueOf(getCellValue(cell))));
                        break;
                    default:
                        break;
                }

            }
            listBooks.add(book);
        }

        workbook.close();
        inputStream.close();

        return listBooks;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
    public static final int MASP1 = 0;
    public static final int TEN = 8;

    public static List<SanPham> readExcelSP(String excelFilePath) throws IOException {
        List<SanPham> listBooks = new ArrayList<>();

        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                continue;
            }

            Iterator<Cell> cellIterator = nextRow.cellIterator();

            SanPham sp = new SanPham();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case MASP1:
                        sp.setMa((String) getCellValue(cell));
                        break;
                    case TEN:
                        sp.setTen((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            listBooks.add(sp);
        }

        workbook.close();
        inputStream.close();

        return listBooks;
    }

    // Get Workbook
    private static Workbook getWorkbookSP(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValueSP(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
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
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        lblAnhSP = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtCardSP = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtGiaSP = new javax.swing.JTextField();
        txtSerialSP = new javax.swing.JTextField();
        txtCpuSP = new javax.swing.JTextField();
        txtHangSP = new javax.swing.JTextField();
        txtRamSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtOCung = new javax.swing.JTextField();
        btnXuatFiel = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        tblChiTietSanPham = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblBangChiTietSP = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLoc1 = new javax.swing.JTextField();
        txtLoc2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Mã");

        txtMaSanPham.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTenSanPham.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Tên SP");

        lblAnhSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnChonAnh.setBackground(new java.awt.Color(0, 153, 153));
        btnChonAnh.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnChonAnh.setForeground(new java.awt.Color(255, 255, 255));
        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 153));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 153));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("CPU");

        txtCardSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Hãng");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Ram");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Card");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Serial");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Giá");

        txtGiaSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtSerialSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCpuSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtHangSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtRamSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ổ Cứng");

        txtOCung.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnXuatFiel.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatFiel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatFiel.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatFiel.setText("EXPORT");
        btnXuatFiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFielActionPerformed(evt);
            }
        });

        btnExport.setBackground(new java.awt.Color(0, 153, 153));
        btnExport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setText("IMPORT");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel38)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(37, 37, 37)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHangSP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCpuSP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRamSP, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCardSP, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSerialSP, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(btnChonAnh)
                        .addGap(69, 69, 69)
                        .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnXuatFiel)
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel30))
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel36))
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtCpuSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel38))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtHangSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel39))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtRamSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel40))
                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                    .addGap(44, 44, 44)
                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtCardSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel41))
                                                    .addGap(21, 21, 21)
                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel43)
                                                        .addComponent(txtGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                            .addGap(136, 136, 136)
                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtSerialSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel42)))))
                                .addGap(0, 27, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoa)
                        .addComponent(btnSua)
                        .addComponent(btnThem))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExport)
                        .addComponent(btnXuatFiel)))
                .addGap(24, 24, 24))
        );

        tblChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tblChiTietSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tblBangChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Serial", "CPU", "Hãng", "Ram", "Card", "Ổ Cứng", "Gía"
            }
        ));
        tblBangChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblBangChiTietSP);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Tìm kiếm ");

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lọc");

        txtLoc1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLoc1CaretUpdate(evt);
            }
        });

        txtLoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoc2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("--");

        javax.swing.GroupLayout tblChiTietSanPhamLayout = new javax.swing.GroupLayout(tblChiTietSanPham);
        tblChiTietSanPham.setLayout(tblChiTietSanPhamLayout);
        tblChiTietSanPhamLayout.setHorizontalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tblChiTietSanPhamLayout.createSequentialGroup()
                        .addComponent(jScrollPane6)
                        .addContainerGap())
                    .addGroup(tblChiTietSanPhamLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(445, 445, 445)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        tblChiTietSanPhamLayout.setVerticalGroup(
            tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtLoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tblChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tblChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        try {
            JFileChooser f = new JFileChooser("src\\main\\img\\anh1.jpg");
            f.setDialogTitle("Mở file");
            f.showOpenDialog(null);
            File fileAnh = f.getSelectedFile();
            duongDanAnh = fileAnh.getAbsolutePath();
            if (duongDanAnh != null) {
                Icon ResizeImage;
                lblAnhSP.setIcon(getImg(String.valueOf(duongDanAnh)));
                System.out.println(duongDanAnh);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (txtMaSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Trống");
        } else if (txtTenSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Trống");
        } else {
            service.addSanPham(getDataSP());
            listSanPham = service.getAllSanPham();
            JOptionPane.showMessageDialog(this, serviceCT.add(getDataCTSP()));
            listCTsp = serviceCT.getAllChiTietSP();
            showDataCTSP(listCTsp);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        SanPham sanPham = service.getOneSP(txtMaSanPham.getText());
        ChiTietSP ct = listCTsp.get(tblBangChiTietSP.getSelectedRow());
        if (serviceCT.checkInt(txtGiaSP.getText().trim()) == null) {
            UUID id = ct.getId();
            BigDecimal big = new BigDecimal(txtGiaSP.getText());
            ChiTietSP ctsp = new ChiTietSP(sanPham, txtSerialSP.getText(), txtCpuSP.getText(), txtHangSP.getText(), txtRamSP.getText(), txtCardSP.getText(), txtOCung.getText(), big, duongDanAnh, 0, new Date(), ct.getLastModifiedDate());
            JOptionPane.showMessageDialog(this, serviceCT.update(ctsp, id));
        } else {
            JOptionPane.showMessageDialog(this, serviceCT.checkInt(txtGiaSP.getText()));
        }
        listCTsp = serviceCT.getAllChiTietSP();
        showDataCTSP(listCTsp);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        SanPham sanPham = service.getOneSP(txtMaSanPham.getText());
        BigDecimal big = new BigDecimal(txtGiaSP.getText());
        ChiTietSP ct = listCTsp.get(tblBangChiTietSP.getSelectedRow());
        ChiTietSP ctsp = new ChiTietSP(sanPham, txtSerialSP.getText(), txtCpuSP.getText(), txtHangSP.getText(), txtRamSP.getText(), txtCardSP.getText(), txtOCung.getText(), big, duongDanAnh, 0, new Date(), ct.getLastModifiedDate());
        UUID id = ct.getId();
        JOptionPane.showMessageDialog(this, serviceCT.delete(ctsp, id));
        listCTsp = serviceCT.getAllChiTietSP();
        showDataCTSP(listCTsp);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXuatFielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFielActionPerformed
        ghi();
    }//GEN-LAST:event_btnXuatFielActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        //        doc();
        final String excelFilePath = "E:\\DuAn1\\ExportSanPham.xlsx";
        final List<ChiTietSP> books;
        final List<SanPham> sPham;
        try {
            books = readExcel(excelFilePath);
            sPham = readExcelSP(excelFilePath);
            for (SanPham x : sPham) {
                JOptionPane.showMessageDialog(this, service.addSanPham(x));
                listSanPham = service.getAllSanPham();
                System.out.println(sPham);
            }
            for (ChiTietSP book : books) {
                JOptionPane.showMessageDialog(this, serviceCT.add(book));
                listCTsp = serviceCT.getAllChiTietSP();
                showDataCTSP(listCTsp);
                System.out.println(book);
            }
        } catch (IOException ex) {
            Logger.getLogger(PanelSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void tblBangChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangChiTietSPMouseClicked
        int indexx = tblBangChiTietSP.getSelectedRow();
        mountClick(listCTsp, indexx);
        getImg(duongDanAnh);
    }//GEN-LAST:event_tblBangChiTietSPMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String search = txtTimKiem.getText();
        if (search.isEmpty()) {
            showDataCTSP(listCTsp);
        } else {
            listCTsp = serviceCT.seatch(search);
            //            listSanPham = service.search(search);
            showDataCTSP(listCTsp);
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtLoc1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLoc1CaretUpdate
        String loc1 = txtLoc1.getText();
        String loc2 = txtLoc2.getText();
        if (loc1.isEmpty() && loc2.isEmpty()) {
            showDataCTSP(listCTsp);
        } else {
            listCTsp = serviceCT.getOneGia(loc2, loc1);
            showDataCTSP(listCTsp);
        }
    }//GEN-LAST:event_txtLoc1CaretUpdate

    private void txtLoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoc2ActionPerformed
        String loc1 = txtLoc2.getText();
        String loc2 = txtLoc1.getText();
        if (loc1.isEmpty() && loc2.isEmpty()) {
            showDataCTSP(listCTsp);
        } else {
            listCTsp = serviceCT.getOneGia(loc1, loc2);
            showDataCTSP(listCTsp);
        }
    }//GEN-LAST:event_txtLoc2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFiel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JTable tblBangChiTietSP;
    private javax.swing.JPanel tblChiTietSanPham;
    private javax.swing.JTextField txtCardSP;
    private javax.swing.JTextField txtCpuSP;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtHangSP;
    private javax.swing.JTextField txtLoc1;
    private javax.swing.JTextField txtLoc2;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtOCung;
    private javax.swing.JTextField txtRamSP;
    private javax.swing.JTextField txtSerialSP;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
