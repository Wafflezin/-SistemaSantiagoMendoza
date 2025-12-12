/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.SadVendas;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import tools.Sad_Util;

/**
 *
 * @author Marcos
 */
public class Sad_ControllerConsultasVendas extends AbstractTableModel {

    public List lstVendas;

    public void setList(List lstVendas) {
        this.lstVendas = lstVendas;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstVendas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public List getList() {
        return lstVendas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadVendas clientes = (SadVendas) lstVendas.get(rowIndex);
        if (columnIndex == 0) {
            return clientes.getSadIdVendas();
        } else if (columnIndex == 1) {
            return clientes.getSadDataVendas();
        } else if (columnIndex == 2) {
            return clientes.getSadTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Código";
        } else if (columnIndex == 1) {
            return "Data";
        } else if (columnIndex == 2) {
            return "Total";
        }
        return "";
    }

    public void exportarPDF(File file) {
        try {
            if (!file.getName().toLowerCase().endsWith(".pdf")) {
                file = new File(file.getAbsolutePath() + ".pdf");
            }

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream cs = new PDPageContentStream(doc, page);
            cs.setFont(PDType1Font.COURIER, 12);

            float y = 750;

            cs.beginText();
            cs.newLineAtOffset(50, y);
            cs.showText("Lista de Vendas:");
            cs.endText();
            y -= 25;

            for (Object obj : lstVendas) {
                SadVendas v = (SadVendas) obj;

                String dataStr = v.getSadDataVendas() == null
                        ? ""
                        : Sad_Util.dateToStr(v.getSadDataVendas());

                String linha = v.getSadIdVendas()
                        + " | " + dataStr
                        + " | " + v.getSadTotal();

                cs.beginText();
                cs.newLineAtOffset(50, y);
                cs.showText(linha);
                cs.endText();

                y -= 20;

                if (y < 50) {
                    cs.close();
                    page = new PDPage();
                    doc.addPage(page);
                    cs = new PDPageContentStream(doc, page);
                    cs.setFont(PDType1Font.COURIER, 12);
                    y = 750;
                }
            }

            cs.close();
            doc.save(file);
            doc.close();

            Sad_Util.sad_mensagem("Exportado pra PDF");

        } catch (Exception ex) {
            ex.printStackTrace();
            Sad_Util.sad_mensagem("Erro ao exportar pra PDF");
        }
    }

}
