/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Block;
import Modelo.Page;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author alimb
 */
@Stateless
public class BlockFacade extends AbstractFacade<Block> implements BlockFacadeLocal {

    @PersistenceContext(unitName = "OrganizeOasisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlockFacade() {
        super(Block.class);
    }
    
    /*@Override
    public List<Block> getBlocksByPage(Integer id_page) {
        Query query = em.createQuery("SELECT b FROM Block b WHERE b.page.id_page = :id_page");
        query.setParameter("id_page", id_page);
        return query.getResultList();
    }*/
    
    @Override
    public List<Block> getBlocksByPage(int pageId) {
        TypedQuery<Block> query = em.createQuery("SELECT b FROM Block b WHERE b.page.id_page = :id_page", Block.class);
        query.setParameter("id_page", pageId);
        return query.getResultList();
    }
    
}
