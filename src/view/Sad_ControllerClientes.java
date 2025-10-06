/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.SadClientes;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author u1845853
 */
public class Sad_ControllerClientes extends AbstractTableModel {

    private List lstUsuarios;

    public void setList(List lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    
    public SadClientes getBean(int rowIndex) {
        return (SadClientes) lstUsuarios.get(rowIndex);
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
        SadClientes sadClientes = (SadClientes) lstUsuarios.get( rowIndex);
        if ( columnIndex == 0 ){
            return sadClientes.getSadIdClientes();
        } else if (columnIndex ==1) {
            return sadClientes.getSadNome();        
        } else if (columnIndex ==2) {
            return sadClientes.getSadSexo();
        } else if (columnIndex ==3) {
            return sadClientes.getSadCpf();
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
            return "Sexo";
        } else if ( columnIndex == 3) {
            return "Cpf";
        } 
        return "";
    }
    
}
