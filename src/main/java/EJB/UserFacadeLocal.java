/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Page;
import Modelo.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alimb
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);
    
    List<Page> findPages(Integer id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    User verifyUser(User user);

    void changeName(String newName);
    
    void changeSurname(String newSurname);
    
    void changeEmail(String newEmail);
    
    void changePassword(String newPassword);

    
    void changeRol();

}
