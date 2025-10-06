/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.SadVendas;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author u1845853
 */
public class Sad_ControllerVendas extends AbstractTableModel {

    private List lstUsuarios;

    public void setList(List lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    
    public SadVendas getBean(int rowIndex) {
        return (SadVendas) lstUsuarios.get(rowIndex);
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
        SadVendas sadVendas = (SadVendas) lstUsuarios.get( rowIndex);
        if ( columnIndex == 0 ){
            return sadVendas.getSadIdVendas();
        } else if (columnIndex ==1) {
            return sadVendas.getSadDataVendas();        
        } else if (columnIndex ==2) {
            return sadVendas.getSadVendedor();
        } else if (columnIndex ==3) {
            return sadVendas.getSadTotal();
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
