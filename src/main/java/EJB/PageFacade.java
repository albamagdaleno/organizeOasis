/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Page;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    
    @Override
    public void removePage(Page page){
        Page pageToRemove = em.find(Page.class, page.getId_page());
        
        if (pageToRemove != null) {
        // Inicia una transacción
            EntityTransaction transaction = em.getTransaction();
            try {
                // Inicia la transacción
                transaction.begin();

                // Realiza la eliminación del objeto Page
                em.remove(pageToRemove);

                // Confirma la transacción
                transaction.commit();
            } catch (Exception e) {
                // Si ocurre algún error, realiza un rollback de la transacción
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace(); // O maneja la excepción de alguna otra manera
            }
        } else {
            // Maneja el caso en el que el objeto Page no existe en la base de datos
            System.out.println("El objeto Page no existe en la base de datos.");
        }
    
    }
    
}
