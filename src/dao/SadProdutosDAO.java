/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.SadProdutos;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author u70874542189
 */
public class SadProdutosDAO extends AbstractDAO {

    @Override
    public void insert(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    @Override
    public void update(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.delete(object);
        session.getTransaction().commit();
    }

    @Override
    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadProdutos.class);
        criteria.add(Restrictions.eq("SadIdProdutos", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }
    public Object listNome(String nome) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadProdutos.class);
        criteria.add(Restrictions.like("sadNome", "%" + nome + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }
    
    public Object listValor(double valor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadProdutos.class);
        criteria.add(Restrictions.ge("sadValor", "%" + valor + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }
    
    public Object listNomeValor(String nome, double valor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadProdutos.class);
        criteria.add(Restrictions.like("sadNome", "%" + nome + "%"));
        criteria.add(Restrictions.ge("sadValor", "%" + valor + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadProdutos.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        SadProdutosDAO sadProdutosDAO = new SadProdutosDAO();
        sadProdutosDAO.listAll();
        System.out.println("deu certo");
    }

}
