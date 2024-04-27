/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Page;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
