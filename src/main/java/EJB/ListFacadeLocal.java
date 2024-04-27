/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface ListFacadeLocal {

    void create(List list);

    void edit(List list);

    void remove(List list);

    List find(Object id);

    java.util.List<List> findAll();

    java.util.List<List> findRange(int[] range);

    int count();
    
}
