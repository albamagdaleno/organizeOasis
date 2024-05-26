/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Page;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alimb
 */
@Stateless
public class PageFacade extends AbstractFacade<Page> implements PageFacadeLocal {

    @PersistenceContext(unitName = "OrganizeOasisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PageFacade() {
        super(Page.class);
    }
    
    @Override
    public List<Page> findAllPages(Integer id_user) {
        
        // Creamos la consulta para sacar las coincidencias con el id del usuario
        Query query = em.createQuery("SELECT p FROM Page p WHERE p.user.id_user = :id_user");
        query.setParameter("id_user", id_user);

        // Devolvemos la lista resultante
        return query.getResultList();
    }
    
    
    
}
