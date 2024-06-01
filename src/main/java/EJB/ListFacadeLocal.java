/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Lista;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface ListFacadeLocal {

    void create(Lista list);

    void edit(Lista list);

    void remove(Lista list);

    Lista find(Object id);

    java.util.List<Lista> findAll();

    java.util.List<Lista> findRange(int[] range);

    int count();
    
    List<Lista> getListsOfBlocks(int blockId);
    
}
