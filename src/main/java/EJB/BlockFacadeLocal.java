/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Block;
import Modelo.Page;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface BlockFacadeLocal {

    void create(Block block);

    void edit(Block block);

    void remove(Block block);

    Block find(Object id);

    List<Block> findAll();

    List<Block> findRange(int[] range);

    int count();
    
    List<Block> getBlocksByPage(int id_page);
    
}
