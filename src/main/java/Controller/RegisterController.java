package Controller;

import Modelo.User;

import EJB.UserFacadeLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private User user;

    private String errorRegister;

    @EJB
    private UserFacadeLocal userFacadeLocal;

    @PostConstruct
    public void init(){
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorRegister() {
        return errorRegister;
    }

    public void setErrorRegister(String errorRegister) {
        this.errorRegister = errorRegister;
    }

    public UserFacadeLocal getUserFacadeLocal() {
        return userFacadeLocal;
    }

    public void setUserFacadeLocal(UserFacadeLocal userFacadeLocal) {
        this.userFacadeLocal = userFacadeLocal;
    }

    public String registerUser(){
        User userAlreadyExists = userFacadeLocal.verifyUser(user);

        if(userAlreadyExists == null){
            errorRegister = "El usuario ya existe";
        }else{
            if(!userFacadeLocal.registerUser(user)){
                errorRegister = "No se ha podido registrar el usuario, intendelo de nuevo";
            }
        }

        if(!errorRegister.isEmpty()){
            return "Registro realizado con exito";
        }else{
            return errorRegister;
        }
    }
}
