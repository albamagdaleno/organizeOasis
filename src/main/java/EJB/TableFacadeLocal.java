/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Table;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface TableFacadeLocal {

    void create(Table table);

    void edit(Table table);

    void remove(Table table);

    Table find(Object id);

    List<Table> findAll();

    List<Table> findRange(int[] range);

    int count();
    
}
