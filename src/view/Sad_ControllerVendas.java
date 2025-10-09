package view;

import bean.SadVendas;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import static tools.Sad_Util.dateToStr;
import static tools.Sad_Util.sad_mensagem;

public class Sad_ControllerVendas extends AbstractTableModel {

    private List<SadVendas> lstVendas;

    public void setList(List<SadVendas> lstVendas) {
        this.lstVendas = lstVendas;
        fireTableDataChanged();
    }

    public List<SadVendas> getVendas() {
        return lstVendas;
    }

    public SadVendas getBean(int rowIndex) {
        return lstVendas.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstVendas != null ? lstVendas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadVendas v = lstVendas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return v.getSadIdVendas();
            case 1:
                return (v.getSadClientes() != null) ? v.getSadClientes().getSadNome() : "";
            case 2:
                return (v.getSadVendedor() != null) ? v.getSadVendedor().getSadNome() : "";
            case 3:
                return v.getSadTotal();
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
                return "Cliente";
            case 2:
                return "Vendedor";
            case 3:
                return "Total";
            default:
                return "";
        }
    }

    public static void exportar(List<SadVendas> vendas, File file) {
        try {
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
                pw.println("Código;Cliente;Vendedor;Data;Total");

                for (SadVendas v : vendas) {
                    String cliente = (v.getSadClientes() != null) ? v.getSadClientes().getSadNome() : "";
                    String vendedor = (v.getSadVendedor() != null) ? v.getSadVendedor().getSadNome() : "";
                    String data = (v.getSadDataVendas() != null) ? dateToStr(v.getSadDataVendas()) : "";

                    cliente = "\"" + cliente.replace("\"", "\"\"") + "\"";
                    vendedor = "\"" + vendedor.replace("\"", "\"\"") + "\"";

                    pw.printf("%d;%s;%s;%s;%.2f%n",
                        v.getSadIdVendas(),
                        cliente,
                        vendedor,
                        data,
                        v.getSadTotal()
                    );
                }
            }

            sad_mensagem("CSV exportado com sucesso!");
        } catch (Exception ex) {
            sad_mensagem("Erro ao exportar CSV: " + ex.getMessage());
        }
    }
}
