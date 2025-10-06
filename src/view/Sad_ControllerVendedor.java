/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.SadVendedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author u1845853
 */
public class Sad_ControllerVendedor extends AbstractTableModel {

    private List lstUsuarios;

    public void setList(List lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    
    public SadVendedor getBean(int rowIndex) {
        return (SadVendedor) lstUsuarios.get(rowIndex);
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
        SadVendedor sadVendedor = (SadVendedor) lstUsuarios.get( rowIndex);
        if ( columnIndex == 0 ){
            return sadVendedor.getSadIdVendedor();
        } else if (columnIndex ==1) {
            return sadVendedor.getSadNome();        
        } else if (columnIndex ==2) {
            return sadVendedor.getSadSenha();
        } else if (columnIndex ==3) {
            return sadVendedor.getSadArrecadado();
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
            return "Senha";
        } else if ( columnIndex == 3) {
            return "Arrecadado";
        } 
        return "";
    }
    
}
