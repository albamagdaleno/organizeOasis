/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Block;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
