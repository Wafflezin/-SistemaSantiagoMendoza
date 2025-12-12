/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.SadVendedor;
import java.io.File;
import java.util.List;
import javax.swing.table.AbstractTableModel;
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
public class Sad_ControllerConsultasVendedor extends AbstractTableModel {

    private List lstVendedor;

    public void setList(List lstVendedor) {
        this.lstVendedor = lstVendedor;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstVendedor.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadVendedor clientes = (SadVendedor) lstVendedor.get(rowIndex);
        if (columnIndex == 0) {
            return clientes.getSadIdVendedor();
        } else if (columnIndex == 1) {
            return clientes.getSadNome();
        } else if (columnIndex == 2) {
            return clientes.getSadArrecadado();
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Código";
        } else if (columnIndex == 1) {
            return "Nome";
        } else if (columnIndex == 2) {
            return "Arrecadado";
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

            PDFont font = PDType1Font.COURIER;
            PDPageContentStream cs = new PDPageContentStream(doc, page);
            cs.setFont(font, 12);

            float y = 750;

            cs.beginText();
            cs.newLineAtOffset(50, y);
            cs.showText("Lista de Vendedores:");
            cs.endText();
            y -= 25;

            for (Object obj : lstVendedor) {
                SadVendedor v = (SadVendedor) obj;

                cs.beginText();
                cs.newLineAtOffset(50, y);
                cs.showText(
                        v.getSadIdVendedor() + " | "
                        + v.getSadNome() + " | "
                        + v.getSadArrecadado()
                );
                cs.endText();
                y -= 20;

                if (y < 50) {
                    cs.close();
                    page = new PDPage();
                    doc.addPage(page);
                    cs = new PDPageContentStream(doc, page);
                    cs.setFont(font, 12);
                    y = 750;
                }
            }

            cs.close();
            doc.save(file);
            doc.close();

            Sad_Util.sad_mensagem("Exportado pra PDF");

        } catch (Exception ex) {
            Sad_Util.sad_mensagem("Erro ao exportar pra PDF");
        }
    }
}
