/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import bean.SadUsuarios;
import java.io.File;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author u70874542189
 */
public class Sad_Util {

    public static void sad_habilitar(boolean valor, JComponent... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].setEnabled(valor);

        }
    }

    public static void sad_limpar(JComponent... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            JComponent componente = componentes[i];

            if (componente instanceof JTextComponent) {
                ((JTextComponent) componente).setText("");

            } else if (componente instanceof JComboBox) {
                ((JComboBox<?>) componente).setSelectedIndex(-1);

            } else if (componente instanceof JCheckBox) {
                ((JCheckBox) componente).setSelected(false);
            }

        }
    }

    public static void sad_mensagem(String cad) {
        JOptionPane.showMessageDialog(null, cad);
    }

    public static boolean sad_perguntar(String mes) {
        int resp = JOptionPane.showConfirmDialog(null,mes,
                "Perguntar", JOptionPane.YES_NO_OPTION);
        return resp == JOptionPane.YES_NO_OPTION;
    }
    
    public static int strToInt(String str){
        return Integer.valueOf(str);
    }
    
    public static String intToStr(int num) {
        return String.valueOf(num);
    }

    public static double strToDouble(String cad) {
        return Double.valueOf(cad);
    }

    
    public static Date strToDate(String str) {
        SimpleDateFormat dateNascFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateNascFormat.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(Sad_Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String dateToStr(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
    public static String doubleToStr(double num) {
        return String.valueOf(num);
    }
    public static void exportUsuariosToCSV(List<SadUsuarios> usuarios, File file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println("Código,Nome,Apelido,CPF,DataNascimento,Senha,Nivel,Ativo");

            for (SadUsuarios u : usuarios) {
                String dataNasc = (u.getSadDataNascimento() != null) ? dateToStr(u.getSadDataNascimento()) : "";

                String nome = "\"" + u.getSadNome().replace("\"", "\"\"") + "\"";
                String apelido = "\"" + u.getSadApelido().replace("\"", "\"\"") + "\"";
                String senha = "\"" + u.getSadSenha().replace("\"", "\"\"") + "\"";
                
                pw.printf("%d,%s,%s,%s,%s,%s,%d,%s%n",
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

            sad_mensagem("CSV exportado com sucesso!");
        } catch (Exception ex) {
            sad_mensagem("Erro ao exportar CSV: " + ex.getMessage());
        }
    }
};
