/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.SadProdutos;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author u1845853
 */
public class Sad_ControllerProdutos extends AbstractTableModel {

    private List lstUsuarios;

    public void setList(List lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    
    public SadProdutos getBean(int rowIndex) {
        return (SadProdutos) lstUsuarios.get(rowIndex);
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
        SadProdutos sadProdutos = (SadProdutos) lstUsuarios.get( rowIndex);
        if ( columnIndex == 0 ){
            return sadProdutos.getSadIdProdutos();
        } else if (columnIndex ==1) {
            return sadProdutos.getSadNome();        
        } else if (columnIndex ==2) {
            return sadProdutos.getSadPeso();
        } else if (columnIndex ==3) {
            return sadProdutos.getSadNovo();
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
            return "Peso";
        } else if ( columnIndex == 3) {
            return "Novo";
        } 
        return "";
    }
    
}
