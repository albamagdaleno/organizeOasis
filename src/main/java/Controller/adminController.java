/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UserFacadeLocal;
import Modelo.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author albamagdaleno
 */
@Named
@ViewScoped

public class adminController implements Serializable{ 
    
    private List<User> listUser;
    
    @EJB
    private UserFacadeLocal userEJB;

    @PostConstruct
    public void init() {
        // Asigna la lista al campo
        this.listUser = userEJB.findAll();
    }
    
    public List<User> getListUser(){
        return listUser;
    }
}
