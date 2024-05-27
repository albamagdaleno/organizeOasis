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
    
    public  List<Block> getBlocksByPage(Page page){
        
        Query query = em.createQuery("SELECT p FROM Block p WHERE p.page.id_page = :id_page");
        query.setParameter("id_page", page.getId_page());

        return query.getResultList();
    }
    
}
