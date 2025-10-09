package view;

import bean.SadVendedor;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import static tools.Sad_Util.dateToStr;
import static tools.Sad_Util.sad_mensagem;

public class Sad_ControllerVendedor extends AbstractTableModel {

    private List<SadVendedor> lstVendedor;

    public void setList(List<SadVendedor> lstVendedor) {
        this.lstVendedor = lstVendedor;
        fireTableDataChanged();
    }

    public List<SadVendedor> getVendedores() {
        return lstVendedor;
    }

    public SadVendedor getBean(int rowIndex) {
        return lstVendedor.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstVendedor != null ? lstVendedor.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadVendedor v = lstVendedor.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return v.getSadIdVendedor();
            case 1:
                return v.getSadNome();
            case 2:
                return v.getSadSenha();
            case 3:
                return v.getSadArrecadado();
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
                return "Senha";
            case 3:
                return "Arrecadado";
            default:
                return "";
        }
    }

    public static void exportar(List<SadVendedor> vendedores, File file) {
        try {
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
                pw.println("Código;Nome;Senha;DataNascimento;QuantidadeVendas;Arrecadado;Celular");

                for (SadVendedor v : vendedores) {
                    String dataNasc = (v.getSadDataNascimento() != null)
                            ? dateToStr(v.getSadDataNascimento()) : "";

                    String nome = "\"" + v.getSadNome().replace("\"", "\"\"") + "\"";
                    String senha = "\"" + v.getSadSenha().replace("\"", "\"\"") + "\"";
                    String qtdVendas = (v.getSadQuantidadeVendas() != null) ? v.getSadQuantidadeVendas() : "";
                    String celular = (v.getSadCelular() != null) ? v.getSadCelular() : "";

                    pw.printf("%d;%s;%s;%s;%s;%.2f;%s%n",
                            v.getSadIdVendedor(),
                            nome,
                            senha,
                            dataNasc,
                            qtdVendas,
                            v.getSadArrecadado(),
                            celular
                    );
                }
            }

            sad_mensagem("Vendedores exportados com sucesso!!");
        } catch (Exception ex) {
            sad_mensagem("Erro ao exportar os Vendedores: " + ex.getMessage());
        }
    }
}
