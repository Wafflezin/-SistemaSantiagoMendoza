package controller;

import bean.SadProdutos;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import static tools.Sad_Util.sad_mensagem;

public class Sad_ControllerProdutos extends AbstractTableModel {

    private static boolean Novo(String valor) {
        if (valor == null) {
            return false;
        }
        valor = valor.trim().toLowerCase();
        return valor.equals("1") || valor.equals("true") || valor.equals("sim") || valor.equals("s");
    }

    private List<SadProdutos> lstProdutos;

    public void setList(List<SadProdutos> lstProdutos) {
        this.lstProdutos = lstProdutos;
    }

    public List<SadProdutos> getProdutos() {
        return lstProdutos;
    }

    public SadProdutos getBean(int rowIndex) {
        return lstProdutos.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstProdutos != null ? lstProdutos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadProdutos p = lstProdutos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getSadIdProdutos();
            case 1:
                return p.getSadNome();
            case 2:
                return p.getSadPeso();
            case 3:
                return Novo(p.getSadNovo()) ? "Novo" : "Usado";

            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Código";
            case 1:
                return "Nome";
            case 2:
                return "Peso";
            case 3:
                return "Novo?";
            default:
                return "";
        }
    }

    public static void exportar(List<SadProdutos> produtos, File file) {

        try {
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
                pw.println("Código;Nome;Peso;Quantidade;Testado;Novo;Marca");

                for (SadProdutos p : produtos) {
                    String nome = "\"" + p.getSadNome().replace("\"", "\"\"") + "\"";
                    String peso = p.getSadPeso();
                    double quantidade = p.getSadValor();
                    String testado = "1".equals(p.getSadTestado()) ? "Testado" : "Não Testado";
                    String novo = Novo(p.getSadNovo()) ? "Novo" : "Usado";
                    String marca = "\"" + p.getSadMarca().replace("\"", "\"\"") + "\"";

                    pw.printf("%d;%s;%s;%s;%s;%s;%s%n",
                            p.getSadIdProdutos(),
                            nome,
                            peso,
                            quantidade,
                            testado,
                            novo,
                            marca
                    );
                }
            }

            sad_mensagem("CSV de produtos exportado com sucesso!");

        } catch (Exception ex) {
            sad_mensagem("Erro ao exportar CSV de produtos: " + ex.getMessage());
        }
    }
}
