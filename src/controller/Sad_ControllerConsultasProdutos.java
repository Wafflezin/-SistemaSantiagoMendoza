/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.SadProdutos;
import bean.SadUsuarios;
import java.io.File;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import tools.Sad_Util;

/**
 *
 * @author Marcos
 */
public class Sad_ControllerConsultasProdutos extends AbstractTableModel {

    private List lstProdutos;

    public void setList(List lstProdutos) {
        this.lstProdutos = lstProdutos;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstProdutos.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadProdutos produtos = (SadProdutos) lstProdutos.get(rowIndex);
        if (columnIndex == 0) {
            return produtos.getSadIdProdutos();
        } else if (columnIndex == 1) {
            return produtos.getSadNome();
        } else if (columnIndex == 2) {
            return produtos.getSadValor();
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
            return "Valor Unitário";
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
            cs.showText("Lista de Produtos:");
            cs.endText();
            y -= 25;

            for (Object obj : lstProdutos) {
                SadProdutos p = (SadProdutos) obj;

                cs.beginText();
                cs.newLineAtOffset(50, y);
                cs.showText(
                        p.getSadIdProdutos() + " | "
                        + p.getSadNome() + " | "
                        + p.getSadValor()
                );
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
            Sad_Util.sad_mensagem("Erro ao exportar pra PDF");
        }
    }
}
