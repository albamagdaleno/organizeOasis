package Controller;


import EJB.UserFacadeLocal;
import Modelo.User;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alimb
 */
@Named
@ViewScoped
public class settingsController implements Serializable{
    
    private User user;
    private User userToDelete;
    private String newName;
    private String newSurname;
    private String newEmail;
    private String newPassword;
    private String newUsername;
    private String errorMessage;

    public void setUserToDelete(User userToDelete) {
        this.userToDelete = userToDelete;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public User getUserToDelete() {
        return userToDelete;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }
    
    
    @EJB
    private UserFacadeLocal userEJB;
    
    @PostConstruct
    public void init(){
        
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserEJB(UserFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }

    public User getUser() {
        return user;
    }

    public UserFacadeLocal getUserEJB() {
        return userEJB;
    }
    
    public String nameGlobalUser(){
    
    return user.getName();
    }
    
    public List<ParametroUsuario> getUserInformation() {
        List<ParametroUsuario> parameters = new ArrayList<>();

        parameters.add(new ParametroUsuario("Nombre", user.getName()));
        parameters.add(new ParametroUsuario("Apellidos", user.getSurname()));
        parameters.add(new ParametroUsuario("Email", user.getEmail()));
        parameters.add(new ParametroUsuario("Rol", user.getRol().toString()));
        parameters.add(new ParametroUsuario("Username", user.getUsername()));

        return parameters;
    }
    
    public String getStatisticsBasedOnRole(){
        
        if(user.getRol().toString().equals("Private")){
            
            return "navigatioinBean.redirectToStatisticsPrivate()";
        }else{
            
            return "navigatioinBean.redirectToStatisticsPrivate()";
        }
        
    }
    
    public void changeName(){
        
        userEJB.changeName(this.newName);
    }
    
    public void changeSurname(){
        
        userEJB.changeSurname(this.newSurname);
    }
    
    public void changeEmail(){
        Boolean error = userEJB.changeEmail(this.newEmail);

        if(error){
            errorMessage = "El correo ya existe.";
            PrimeFaces.current().ajax().update("errorDialog");
            PrimeFaces.current().executeScript("PF('errorDialog').show()");
        }
    }
    
    public void changePassword(){
        
        userEJB.changePassword(this.newPassword);
    }
    
    public void changeRol(){
        
        userEJB.changeRol();
    }
    
    public void changeUsername(){
        Boolean error = userEJB.changeUsername(this.newUsername);

        if(error){
            errorMessage = "El nombre de usuario ya existe";
            PrimeFaces.current().ajax().update("errorDialog");
            PrimeFaces.current().executeScript("PF('errorDialog').show()");
        }
    }
    
    public void deleteUser(){
        
        this.userToDelete = this.user;
        userEJB.remove(this.userToDelete);
    }
    
    public String getMessageDialogRol(){
        
        if(this.user.getRol().toString().equals("Private")){
            
            return "¿Cambiar rol a público?";
        }else{
            return "¿Cambiar rol a privado?";
        }
    }
    
    public class ParametroUsuario implements Serializable {
        private String name;
        private String value;

        public ParametroUsuario(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setame(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValor(String value) {
            this.value = value;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

