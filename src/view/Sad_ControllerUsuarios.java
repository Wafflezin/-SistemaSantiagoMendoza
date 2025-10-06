/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.SadUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author u1845853
 */
public class Sad_ControllerUsuarios extends AbstractTableModel {

    private List lstUsuarios;

    public void setList(List lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    
    public SadUsuarios getBean(int rowIndex) {
        return (SadUsuarios) lstUsuarios.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstUsuarios.size();
                
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SadUsuarios sadUsuarios = (SadUsuarios) lstUsuarios.get( rowIndex);
        if ( columnIndex == 0 ){
            return sadUsuarios.getSadIdUsuarios();
        } else if (columnIndex ==1) {
            return sadUsuarios.getSadNome();        
        } else if (columnIndex ==2) {
            return sadUsuarios.getSadApelido();
        } else if (columnIndex ==3) {
            return sadUsuarios.getSadCpf();
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código";
        } else if ( columnIndex == 1) {
            return "Nome";         
        } else if ( columnIndex == 2) {
            return "Apelido";
        } else if ( columnIndex == 3) {
            return "Cpf";
        } 
        return "";
    }
    
}
