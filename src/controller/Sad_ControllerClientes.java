package controller;

import bean.SadClientes;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import static tools.Sad_Util.dateToStr;
import static tools.Sad_Util.sad_mensagem;

public class Sad_ControllerClientes extends AbstractTableModel {

    private List<SadClientes> lstClientes;

    public void setList(List<SadClientes> lstClientes) {
        this.lstClientes = lstClientes;
        fireTableDataChanged();
    }

    public List<SadClientes> getClientes() {
        return lstClientes;
    }

    public SadClientes getBean(int rowIndex) {
        return lstClientes.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstClientes != null ? lstClientes.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadClientes c = lstClientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getSadIdClientes();
            case 1:
                return c.getSadNome();
            case 2:
                return (c.getSadSexo() == 0) ? "Masculino" : "Feminino";

            case 3:
                return c.getSadCpf();
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
                return "Sexo";
            case 3:
                return "Cpf";
            default:
                return "";
        }
    }

    public static void exportar(List<SadClientes> clientes, File file) {
        try {
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println("Código;Nome;CPF;RG;Sexo;DataNascimento;Email;CEP;Endereço;Bairro;Cidade;TelefoneResidencial;Celular;Senha;Pronomes");

                for (SadClientes c : clientes) {
                    String dataNasc = c.getSadDataNascimento() != null ? dateToStr(c.getSadDataNascimento()) : "";
                    String nome = "\"" + c.getSadNome().replace("\"", "\"\"") + "\"";
                    String cpf = c.getSadCpf() != null ? c.getSadCpf() : "";
                    String rg = c.getSadRg() != null ? c.getSadRg() : "";
                    String sexo = (c.getSadSexo() == 0) ? "Masculino" : "Feminino";
                    String email = c.getSadEmail() != null ? "\"" + c.getSadEmail().replace("\"", "\"\"") + "\"" : "";
                    String cep = c.getSadCep() != null ? c.getSadCep() : "";
                    String endereco = c.getSadEndereco() != null ? "\"" + c.getSadEndereco().replace("\"", "\"\"") + "\"" : "";
                    String bairro = c.getSadBairro() != null ? "\"" + c.getSadBairro().replace("\"", "\"\"") + "\"" : "";
                    String cidade = c.getSadCidade() != null ? "\"" + c.getSadCidade().replace("\"", "\"\"") + "\"" : "";
                    String telRes = c.getSadTelefoneResidencial() != null ? c.getSadTelefoneResidencial() : "";
                    String celular = c.getSadCelular() != null ? c.getSadCelular() : "";
                    String senha = c.getSadSenha() != null ? "\"" + c.getSadSenha().replace("\"", "\"\"") + "\"" : "";
                    String pronomes = c.getSadPronomes() != null ? c.getSadPronomes() : "";

                    pw.printf("%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n",
                            c.getSadIdClientes(),
                            nome,
                            cpf,
                            rg,
                            sexo,
                            dataNasc,
                            email,
                            cep,
                            endereco,
                            bairro,
                            cidade,
                            telRes,
                            celular,
                            senha,
                            pronomes
                    );
                }
            }

            sad_mensagem("CSV de clientes exportado com sucesso!");
        } catch (Exception ex) {
            sad_mensagem("Erro ao exportar CSV de clientes: " + ex.getMessage());
        }
    }
}
