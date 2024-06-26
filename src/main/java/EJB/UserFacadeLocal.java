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

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    User verifyUser(User user);

    /**
     * Registra un usuario en la DB
     * true: registro correcto
     * false: no se ha podido completar el registro
     * @param user
     * @return
     */
    boolean registerUser(User user);

    /**
     * Comprueba si existe un usuario con el username
     * devuelve true si existe ya un usurio con ese username, false si no
     *
     * @param username
     * @return true or false
     */
    boolean existsUsername(String username);

    void changeName(String newName);

    void changeSurname(String newSurname);

    void changeEmail(String newEmail);

    void changePassword(String newPassword);
    
    void changeUsername(String newUsername);

    List<Integer> getVisitsUsers();

    void changeRol();

    /**
     * Busca en la tabla users todos los username que comiencen por el parametro searchTerm
     * @param searchTerm
     * @return Devuelve un listado que coincida con el campo searchTerm
     */
    List<String> getUsersStartingWith(String searchTerm);

    /**
     * Busca un usuario por su nombre de usuario
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * Actualiza las visitas de un usuario
     *
     * @param user
     */
    void updateUserVisits(User user);

    /**
     * Obtiene las paginas de un usuario
     *
     * @param userId
     * @return
     */
    List<Page> findPagesByUserId(Integer userId);

    /**
     * Verifica si un usuario existe a través de su email.
     * @param user
     * @return
     */
    User verifyUserWithoutGlobal(User user);
}
