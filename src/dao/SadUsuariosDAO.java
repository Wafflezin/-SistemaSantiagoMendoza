/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.SadUsuarios;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author u70874542189
 */
public class SadUsuariosDAO extends AbstractDAO {

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
        Criteria criteria = session.createCriteria(SadUsuarios.class);
        criteria.add(Restrictions.eq("SadIdUsuarios", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SadUsuarios.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        SadUsuariosDAO sadUsuariosDAO = new SadUsuariosDAO();
        sadUsuariosDAO.listAll();
    }

    public boolean autenticar(String sad_nome, String sad_senha) {
        try {
            session.beginTransaction();

            String hql = "FROM SadUsuarios WHERE sadNome = :nome AND sadSenha = :senha";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("nome", sad_nome);
            query.setParameter("senha", sad_senha);

            SadUsuarios usuario = (SadUsuarios) query.uniqueResult();

            session.getTransaction().commit();

            if (usuario != null) {
                return true;
            }

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SadUsuariosDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return false;
    }
}
