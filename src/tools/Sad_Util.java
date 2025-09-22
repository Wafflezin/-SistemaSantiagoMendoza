/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static boolean sad_perguntar(String cad) {
        return JOptionPane.showConfirmDialog(null, cad, "Pergunta", JOptionPane.YES_NO_OPTION) == 0;
    }
    
    public static int sad_strToInt(String str){
        return Integer.valueOf(str);
    }

    public static String sad_intToStr(int num) {
        return String.valueOf(num);
    }

    public static double sad_strToDouble(String cad) {
        try {
            return Double.parseDouble(cad.replace(",", "."));
        } catch (NumberFormatException e) {
            sad_mensagem("Erro");
            return 0.0;
        }
    }

    public static String sad_doubleToStr(double num) {
        return String.valueOf(num);
    }

    public static Date sad_strToDate(String cad) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.parse(cad);
        } catch (ParseException e) {
            sad_mensagem("Erro");
            return null;
        }
    }

    public static String sad_dateToStr(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
};
