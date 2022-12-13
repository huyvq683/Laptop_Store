/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import custommodel.HoaDonCHiTietInResponse;
import custommodel.HoaDonInResponse;

/**
 *
 * @author LiamTrieu
 */
public class InHoaDon {

    public static boolean makePDF(HoaDonInResponse hoaDon, List<HoaDonCHiTietInResponse> gioHang) {
        String desc;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(new Date());
        try {

            OutputStream file = new FileOutputStream(new File("D:/" + hoaDon.getMaHD() + ".pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            PdfPTable billTable = new PdfPTable(6);
            billTable.setWidthPercentage(100);
            billTable.setWidths(new float[]{1, 3, 2, 2, 2, 2});
            billTable.setSpacingBefore(30.0f);
            billTable.addCell(getBillHeaderCell("STT"));
            billTable.addCell(getBillHeaderCell("Tên sản phẩm"));
            billTable.addCell(getBillHeaderCell("Đơn giá"));
            billTable.addCell(getBillHeaderCell("Khuyến mại"));
            billTable.addCell(getBillHeaderCell("Số lượng"));
            billTable.addCell(getBillHeaderCell("Thành tiền"));

            for (int i = 0; i < gioHang.size(); i++) {
                billTable.addCell(getBillRowCell((i + 1) + ""));
                billTable.addCell(getBillRowCell(gioHang.get(i).getTenSP()));
                billTable.addCell(getBillRowCell(gioHang.get(i).getDonGia() + ""));
                billTable.addCell(getBillRowCell(gioHang.get(i).getKhuyenMai() + ""));
                billTable.addCell(getBillRowCell(gioHang.get(i).getSoLuong() + ""));
                billTable.addCell(getBillRowCell(gioHang.get(i).getThanhTien() + ""));
            }
            for (int j = 0; j < gioHang.size() + 10 - gioHang.size(); j++) {
                billTable.addCell(getBillRowCell(" "));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
            }

            PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell(" "));
            PdfPCell summaryL = new PdfPCell(validity);
            summaryL.setColspan(3);
            summaryL.setPadding(1.0f);
            billTable.addCell(summaryL);
            
            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            accounts.addCell(getAccountsCell("Tạm tính"));
            accounts.addCell(getAccountsCellR(hoaDon.getTongTienTam() + ""));
            accounts.addCell(getAccountsCell("Giảm giá"));
            accounts.addCell(getAccountsCellR(hoaDon.getGiamGia() + ""));
            accounts.addCell(getAccountsCell("Tổng tiền thanh toán"));
            accounts.addCell(getAccountsCellR(hoaDon.getTongTien() + ""));
            PdfPCell summaryR = new PdfPCell(accounts);
            summaryR.setColspan(4);
            billTable.addCell(summaryR);

            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescCell(" "));
            describer.addCell(getdescCell("Cảm ơn quý khách đã mua sản phẩm tại của hàng, hẹn gặp lại quý khách"));
            document.open();
            document.add(tieuDe());
            document.add(ttHoaDon(hoaDon));
            document.add(billTable);
            document.add(describer);
            document.close();
            file.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static PdfPCell getBillHeaderCell(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 11);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    public static PdfPCell getBillRowCell(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 10);
        font.setColor(BaseColor.BLACK);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getBillFooterCell(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 10);
        font.setColor(BaseColor.BLACK);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getValidityCell(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getAccountsCell(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding(5.0f);
        return cell;
    }

    public static PdfPCell getAccountsCellR(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    public static PdfPCell getdescCell(String text) throws Exception {
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs = new FontSelector();
        Font font = new Font(bf, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }

    public static PdfPTable tieuDe() throws Exception {
        PdfPTable tieuDe = new PdfPTable(1);
        tieuDe.setWidthPercentage(100);
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs1 = new FontSelector();
        FontSelector fs2 = new FontSelector();
        FontSelector fs3 = new FontSelector();
        FontSelector fs4 = new FontSelector();
        Font font1 = new Font(bf, 30);
        Font font2 = new Font(bf, 15);
        Font font3 = new Font(bf, 25);
        Font font4 = new Font(bf,15);
        font1.setColor(BaseColor.BLACK);
        fs1.addFont(font1);
        fs2.addFont(font2);
        fs3.addFont(font3);
        fs4.addFont(font4);
        
        Phrase p1 = fs1.process("LAPTOP 7H\n\n");
        Phrase p2 = fs2.process("Đ/c: Số nhà 44, đường HP1, Hoằng Phụ, Hoằng Hóa, Thanh Hóa\n"
        + "---------------------------------------------");
        Phrase p3 = fs3.process("HÓA ĐƠN BÁN HÀNG\n");
        Phrase p4 = fs4.process("Ngày tạo: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+ "\n\n");
        PdfPCell c1 = new PdfPCell(p1);
        PdfPCell c2 = new PdfPCell(p2);
        PdfPCell c3 = new PdfPCell(p3);
        PdfPCell c4 = new PdfPCell(p4);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBorder(0);
        c1.setPadding(5.0f);
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        c2.setBorder(0);
        c2.setPadding(5.0f);
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        c3.setBorder(0);
        c3.setPadding(5.0f);
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        c4.setBorder(0);
        c4.setPadding(5.0f);
        tieuDe.addCell(c1);
        tieuDe.addCell(c2);
        tieuDe.addCell(c3);
        tieuDe.addCell(c4);
        return tieuDe;
    }

    public static PdfPTable ttHoaDon(HoaDonInResponse hd) throws Exception {
        PdfPTable tieuDe = new PdfPTable(3);
        tieuDe.setWidthPercentage(100);
        tieuDe.setWidths(new float[]{5, 3, 4});
        BaseFont bf = BaseFont.createFont("src/main/font/unicode.ttf", BaseFont.IDENTITY_H, true);
        FontSelector fs1 = new FontSelector();
        FontSelector fs2 = new FontSelector();
        Font font1 = new Font(bf, 12);
        Font font2 = new Font(bf, 12);
        font1.setColor(BaseColor.BLACK);
        fs1.addFont(font1);
        fs2.addFont(font2);
        Phrase p2 = fs1.process("Mã HD: " + hd.getMaHD() + "\n\n"
                + "Nhân viên: " + hd.getTenNV());
        Phrase p3 = fs2.process("");
        Phrase p1 = fs2.process("Tên khách hàng: " + hd.getTenKH() + "\n\nSố điện thoại: " + hd.getSdtKH() + "\n\nĐịa chỉ: " + hd.getDiaChi() + "\n\n"
                + "Hình thức thanh toán: " + hd.getHinhThucThanhToan());
        PdfPCell c1 = new PdfPCell(p1);
        PdfPCell c2 = new PdfPCell(p2);
        PdfPCell c3 = new PdfPCell(p3);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(0);
        c1.setPadding(5.0f);
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        c2.setBorder(0);
        c3.setBorder(0);
        c2.setPadding(5.0f);
        tieuDe.addCell(c1);
        tieuDe.addCell(c3);
        tieuDe.addCell(c2);
        return tieuDe;
    }
    
}
