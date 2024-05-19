/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Page;
import Modelo.User;
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
    public void changeName(String newName){
        
        User globalUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
        
        
        if (globalUser != null) {

            User userToUpdate = em.find(User.class, globalUser.getId_user());
            
            if (userToUpdate != null) {

                userToUpdate.setName(newName);
                
                em.merge(userToUpdate);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalUser", userToUpdate);
            }
        }
    }
    
    @Override
    public void changeSurname(String newSurname){
        
        User globalUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");

        if (globalUser != null) {

            User userToUpdate = em.find(User.class, globalUser.getId_user());
            
            if (userToUpdate != null) {

                userToUpdate.setSurname(newSurname);
                
                em.merge(userToUpdate);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalUser", userToUpdate);
            }
        }
        
    }
    
    @Override
    public void changeEmail(String newEmail){
        
        User globalUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");

        if (globalUser != null) {

            User userToUpdate = em.find(User.class, globalUser.getId_user());
            
            if (userToUpdate != null) {

                userToUpdate.setEmail(newEmail);
                
                em.merge(userToUpdate);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalUser", userToUpdate);
            }
        }
        
        
    }
    
    @Override
    public void changePassword(String newPassword){
        
        User globalUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");

        if (globalUser != null) {

            User userToUpdate = em.find(User.class, globalUser.getId_user());
            
            if (userToUpdate != null) {

                userToUpdate.setPassword(newPassword);
                
                em.merge(userToUpdate);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalUser", userToUpdate);
            }
        }
    }
    
    @Override
    public List<Page> findPages(Integer id_user) {
        System.out.println("Dentro de UserFacade"+id_user);
        // Creamos la consulta para sacar las coincidencias con el id del usuario
        Query query = em.createQuery("SELECT p FROM Page p WHERE p.user.id_user = :id_user");
        query.setParameter("id_user", id_user);

        // Devolvemos la lista resultante
        return query.getResultList();
    }

    
    @Override
    public void changeRol(){
        
        User globalUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
        System.out.println(globalUser.getRol().toString());
        if (globalUser != null) {

            User userToUpdate = em.find(User.class, globalUser.getId_user());
            
            if (userToUpdate != null) {
                
                if(globalUser.getRol().toString().equals("Private")){
                    userToUpdate.setRol(User.Rol.Influencer);
                }
                
                if(globalUser.getRol().toString().equals("Influencer")){
                    userToUpdate.setRol(User.Rol.Private);
                }
                
                
                em.merge(userToUpdate);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalUser", userToUpdate);
            }
        }

    }
    
}
