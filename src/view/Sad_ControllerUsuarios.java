package view;

import bean.SadUsuarios;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tools.Sad_Util;
import static tools.Sad_Util.dateToStr;
import static tools.Sad_Util.sad_mensagem;

public class Sad_ControllerUsuarios extends AbstractTableModel {

    private List<SadUsuarios> lstUsuarios;

    public void setList(List<SadUsuarios> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public List<SadUsuarios> getUsuarios() {
        return lstUsuarios;
    }
    
    public SadUsuarios getBean(int rowIndex) {
        return lstUsuarios.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstUsuarios != null ? lstUsuarios.size() : 0;      
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadUsuarios u = lstUsuarios.get(rowIndex);
        switch(columnIndex) {
            case 0: return u.getSadIdUsuarios();
            case 1: return u.getSadNome();
            case 2: return u.getSadApelido();
            case 3: return u.getSadCpf();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Código";
            case 1: return "Nome";
            case 2: return "Apelido";
            case 3: return "Cpf";
            default: return "";
        }
    }
    
    public static void exportUsuariosToCSV(List<SadUsuarios> usuarios, File file) {
    try {
        if (!file.getName().toLowerCase().endsWith(".csv")) {
            file = new File(file.getAbsolutePath() + ".csv");
        }

        try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
            pw.println("Código;Nome;Apelido;CPF;DataNascimento;Senha;Nivel;Ativo");

            for (SadUsuarios u : usuarios) {
                String dataNasc = (u.getSadDataNascimento() != null) ? Sad_Util.dateToStr(u.getSadDataNascimento()) : "";

                String nome = "\"" + u.getSadNome().replace("\"", "\"\"") + "\"";
                String apelido = "\"" + u.getSadApelido().replace("\"", "\"\"") + "\"";
                String senha = "\"" + u.getSadSenha().replace("\"", "\"\"") + "\"";

                pw.printf("%d;%s;%s;%s;%s;%s;%d;%s%n",
                    u.getSadIdUsuarios(),
                    nome,
                    apelido,
                    u.getSadCpf(),
                    dataNasc,
                    senha,
                    u.getSadNivel(),
                    u.getSadAtivo()
                );
            }
        }

        Sad_Util.sad_mensagem("CSV exportado com sucesso!");

    } catch (Exception ex) {
        Sad_Util.sad_mensagem("Erro ao exportar CSV: " + ex.getMessage());
    }
}

}
