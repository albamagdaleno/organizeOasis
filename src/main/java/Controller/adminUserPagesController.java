/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.PageFacadeLocal;
import Modelo.Page;
import Modelo.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author albamagdaleno
 */

@Named
@SessionScoped
public class adminUserPagesController implements Serializable{
    
    private Page selectedPage;
    private User selectedUser;
    private List<Page> listUserPages;
    
    @EJB
    private PageFacadeLocal pageEJB;
    
    @PostConstruct
    public void init() {
       //Rellena la lista de paginas
       
       //this.listUserPages = pageEJB.findAllPages(iduser);
        
    }

    public Page getSelectedPage() {
        return selectedPage;
    }

    public List<Page> getListUserPages() {
        this.listUserPages = pageEJB.findAllPages(selectedUser.getId_user());
        return listUserPages;
    }

    public PageFacadeLocal getPageEJB() {
        return pageEJB;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedPage(Page selectedPage) {
        this.selectedPage = selectedPage;
    }

    public void setListUserPages(List<Page> listUserPages) {
        this.listUserPages = listUserPages;
    }

    public void setPageEJB(PageFacadeLocal pageEJB) {
        this.pageEJB = pageEJB;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public void deletePage(){
        
        if(selectedPage!=null){
            
            pageEJB.remove(selectedPage);
            selectedPage = null;
        }
    }
   
}
