/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Page;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface PageFacadeLocal {

    void create(Page page);

    void edit(Page page);

    void remove(Page page);
    
    void removePage(Page page);
    
    Page find(Object id);

    List<Page> findAll();

    List<Page> findRange(int[] range);

    int count();
    
}
