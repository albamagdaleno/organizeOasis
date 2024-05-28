/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Text;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author alimb
 */
@Stateless
public class TextFacade extends AbstractFacade<Text> implements TextFacadeLocal {

    @PersistenceContext(unitName = "OrganizeOasisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TextFacade() {
        super(Text.class);
    }
    

    public List<Text> getNotesOfBlocks(int blockId) {
        
        TypedQuery<Text> query = em.createQuery("SELECT t FROM Text t WHERE t.block.idBlock = :id_block", Text.class);
        query.setParameter("id_block", blockId);
        return query.getResultList();
    }


   
}
