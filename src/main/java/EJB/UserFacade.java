/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.User;
import org.eclipse.persistence.exceptions.QueryException;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alimb
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "OrganizeOasisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    @Override
    public User verifyUser(User user){
        
        User result = null;
        
        //Creamos la consulta para ver si el usuario está en bbdd
        String stringQuery = "FROM User u WHERE u.email=:param1 and u.password=:param2";
        
        Query query = em.createQuery(stringQuery);
        
        query.setParameter("param1", user.getEmail());
        query.setParameter("param2", user.getPassword());
        
        //Devuelve null si no encuentra al usuario o una lista de usuarios en caso de que si
        //(Cuento con que no pueda haber dos usuarios con mismo mail, así que si devuelve devolverá solo 1 user)
        List<User> listUsers = query.getResultList();
        
        if (!listUsers.isEmpty()) {
            result = listUsers.get(0);
            
            //Esto hace que el usuario devuelto esté disponible a nivel global en toda la app, con alias "globalUser"
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalUser", result);
        } 
        
        return result;
    }

    @Override
    public boolean registerUser(User user){
        User userRegistered = new User();

        try {
            String registerQuery = "INSERT INTO users (name, surname, email, user_password, role, visits)" +
                    "VALUES (name, surname, email, passw, rol, 0)";

            Query query = em.createQuery(registerQuery);

            query.setParameter("name",user.getName());
            query.setParameter("surname", user.getSurname());
            query.setParameter("email", user.getEmail());
            query.setParameter("passw",user.getPassword());
            query.setParameter("rol", user.getRol());
        }catch (QueryException e){
            return false;
        }

        return true;

    }
    
    
}
