package view;

import bean.SadVendaProdutos;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import static tools.Sad_Util.dateToStr;
import static tools.Sad_Util.sad_mensagem;

public class Sad_ControllerVendaProdutos extends AbstractTableModel {

    private List<SadVendaProdutos> lstVendaProdutos;

    public void setList(List<SadVendaProdutos> lstVendaProdutos) {
        this.lstVendaProdutos = lstVendaProdutos;
        fireTableDataChanged();
    }

    public List<SadVendaProdutos> getVendas() {
        return lstVendaProdutos;
    }

    public SadVendaProdutos getBean(int rowIndex) {
        return lstVendaProdutos.get(rowIndex);
    }
    public void addBean(SadVendaProdutos sadVendaProdutos){
        this.lstVendaProdutos.add(sadVendaProdutos);
    }

    @Override
    public int getRowCount() {
        return lstVendaProdutos != null ? lstVendaProdutos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadVendaProdutos v = lstVendaProdutos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return v.getSadIdVendaProdutos();
            case 1:
                return (v.getSadProdutos() != null) ? v.getSadProdutos().getSadNome() : "";
            case 2:
                return (v.getSadQuantidade());
            case 3:
                return v.getSadValorUnitario();
            case 4:
                return v.getSadValorUnitario() *  v.getSadQuantidade();
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
                return "Produto";
            case 2:
                return "Quantidade";
            case 3:
                return "Valor Unitário";
                case 4:
                return "Total";
            default:
                return "";
        }
    }

}
