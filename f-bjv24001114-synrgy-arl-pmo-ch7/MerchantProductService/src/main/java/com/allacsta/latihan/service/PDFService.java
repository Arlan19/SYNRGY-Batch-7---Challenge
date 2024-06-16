package com.allacsta.latihan.service;


import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.repository.MerchantRepository;
import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;


@Service
public class PDFService {

    @Autowired
    private MerchantRepository merchantRepository;

    public byte[] generatePDF() {
        List<Merchant> merchants = merchantRepository.findAll();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Buat tabel dengan 4 kolom
        float[] columnWidths = {2, 2, 3, 1};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Tambahkan header tabel
        addTableHeader(table);

        // Tambahkan data merchant ke tabel
        for (Merchant merchant : merchants) {
            table.addCell(new Cell().add(String.valueOf(merchant.getId())).setTextAlignment(TextAlignment.LEFT));
            table.addCell(new Cell().add(merchant.getMerchant_name()).setTextAlignment(TextAlignment.LEFT));
            table.addCell(new Cell().add(merchant.getMerchant_location()).setTextAlignment(TextAlignment.LEFT));
            table.addCell(new Cell().add(String.valueOf(merchant.getOpen())).setTextAlignment(TextAlignment.CENTER));
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    private void addTableHeader(Table table) {
        Cell headerCell;

        headerCell = new Cell().add("ID").setBackgroundColor(DeviceGray.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(headerCell);

        headerCell = new Cell().add("Merchant Name").setBackgroundColor(DeviceGray.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(headerCell);

        headerCell = new Cell().add("Merchant Location").setBackgroundColor(DeviceGray.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(headerCell);

        headerCell = new Cell().add("Open").setBackgroundColor(DeviceGray.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(headerCell);
    }
}


//@Service
//public class PDFService {
//
//    @Autowired
//    private MerchantRepository merchantRepository;
//
//    public byte[] generatePDF() {
//        List<Merchant> merchants = merchantRepository.findAll();
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PdfWriter writer = new PdfWriter(baos);
//        PdfDocument pdfDoc = new PdfDocument(writer);
//        Document document = new Document(pdfDoc);
//
//        document.add(new Paragraph("Merchant List"));
//
//        for (Merchant merchant : merchants) {
//            document.add(new Paragraph("Merchant ID: " + merchant.getId()));
//            document.add(new Paragraph("Merchant Name: " + merchant.getMerchant_name()));
//            document.add(new Paragraph("Merchant Location: " + merchant.getMerchant_location()));
//            document.add(new Paragraph("Open: " + merchant.getOpen()));
//            document.add(new Paragraph(" "));
//        }
//
//        document.close();
//        return baos.toByteArray();
//    }
//}