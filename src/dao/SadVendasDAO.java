/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.SadVendas;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tools.Sad_Util;

/**
 *
 * @author u70874542189
 */
public class SadVendasDAO extends AbstractDAO {

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
        Criteria criteria = session.createCriteria(SadVendas.class);
        criteria.add(Restrictions.eq("SadIdVendas", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public List<SadVendas> listData(String data) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(SadVendas.class);

        if (data != null && !data.isEmpty()) {
            Date dataConvertida = Sad_Util.strToDate(data);
            criteria.add(Restrictions.ge("sadDataVendas", dataConvertida));
        }

        List<SadVendas> lista = criteria.list();
        sessao.close();
        return lista;

    }

    public Object listTotal(double total) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadVendas.class);
        criteria.add(Restrictions.ge("sadTotal", total));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listDataTotal(String dataVendas, double total) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadVendas.class);

        if (dataVendas != null && !dataVendas.isEmpty()) {
            Date dataConvertida = Sad_Util.strToDate(dataVendas);
            criteria.add(Restrictions.ge("sadDataVendas", dataConvertida)); // ✔ APÓS A DATA
        }

        criteria.add(Restrictions.ge("sadTotal", total));

        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadVendas.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        SadVendasDAO sadVendasDAO = new SadVendasDAO();
        sadVendasDAO.listAll();
    }
}
