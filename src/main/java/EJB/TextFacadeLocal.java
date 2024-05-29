/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Text;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface TextFacadeLocal {

    void create(Text text);

    void edit(Text text);

    void remove(Text text);

    Text find(Object id);

    List<Text> findAll();

    List<Text> findRange(int[] range);

    int count();
    
    List<Text> getNotesOfBlocks(int blockId);
}
