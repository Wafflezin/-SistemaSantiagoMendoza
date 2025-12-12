/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.SadClientes;
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
public class Sad_ControllerConsultasClientes extends AbstractTableModel {

    public List lstClientes;

    public void setList(List lstClientes) {
        this.lstClientes = lstClientes;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstClientes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public List getList() {
        return lstClientes;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadClientes clientes = (SadClientes) lstClientes.get(rowIndex);
        if (columnIndex == 0) {
            return clientes.getSadIdClientes();
        } else if (columnIndex == 1) {
            return clientes.getSadNome();
        } else if (columnIndex == 2) {
            return clientes.getSadEmail();
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
            return "Email";
        }
        return "";
    }

    public void exportarPDF(File file) {
        if (lstClientes == null) {
            System.out.println("Lista vazia. Nada para exportar.");
            return;
        }

        try {
            if (!file.getName().toLowerCase().endsWith(".pdf")) {
                file = new File(file.getAbsolutePath() + ".pdf");
            }

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font;
            try {
                font = PDType1Font.COURIER;
            } catch (Throwable t) {
                font = PDType1Font.HELVETICA;
            }

            PDPageContentStream cs = new PDPageContentStream(doc, page);
            float fontSize = 12;
            float leading = 1.2f * fontSize;
            float margin = 50;
            float y = page.getMediaBox().getHeight() - margin;

            cs.beginText();
            cs.setFont(font, fontSize);
            cs.newLineAtOffset(margin, y);
            cs.showText("Código | Nome | Email");
            cs.endText();
            y -= leading;

            for (int i = 0; i < lstClientes.size(); i++) {
                Object obj = lstClientes.get(i);
                if (obj == null) {
                    continue;
                }

                SadClientes c;
                try {
                    c = (SadClientes) obj;
                } catch (ClassCastException ex) {
                    continue;
                }

                String linha = String.format("%d | %s | %s",
                        c.getSadIdClientes(),
                        c.getSadNome() == null ? "" : c.getSadNome(),
                        c.getSadEmail() == null ? "" : c.getSadEmail());
                if (y < margin + leading) {
                    cs.close();
                    page = new PDPage();
                    doc.addPage(page);
                    cs = new PDPageContentStream(doc, page);
                    y = page.getMediaBox().getHeight() - margin;
                }

                cs.beginText();
                cs.setFont(font, fontSize);
                cs.newLineAtOffset(margin, y);
                cs.showText(linha);
                cs.endText();
                y -= leading;
            }

            cs.close();
            doc.save(file);
            doc.close();

            Sad_Util.sad_mensagem("Exportado pra PDF");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
