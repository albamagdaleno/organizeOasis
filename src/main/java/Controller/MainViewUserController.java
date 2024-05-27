/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.PageFacadeLocal;
import EJB.UserFacadeLocal;
import Modelo.Page;
import Modelo.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author alimb
 */
@Named
@ViewScoped
public class MainViewUserController implements Serializable{
    
    private Page page;
    private Page newPage;
    private User user;
    private MenuModel model;
    private Page selectedPage;
    private List<Page> listUserPages;
    //MODIFICAR
    private List<String> blocks; //luego cambiar por bloques objetos
    //END MODIFICAR

    @EJB
    private PageFacadeLocal pageEJB;
    private UserFacadeLocal userEJB;
    
    @PostConstruct
    public void init(){
        
        page = new Page();
        newPage = new Page();
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");

        //luego rellenar con bloques de verdad MODIFICAR
        blocks = new ArrayList<>();
        // Agregar bloques de ejemplo
        blocks.add("Block 1");
        blocks.add("Block 2");
        blocks.add("Block 3");
        blocks.add("Block 4"); // END MODIFICAR
        
        //Modelo para la lista de paginas del usuario
        model = new DefaultMenuModel();
        
        List<Page> pages = getListUserPages();
        
        DefaultSubMenu pagesMenu = DefaultSubMenu.builder()
                .label("Páginas")
                .expanded(true)
                .build();
        
        for (Page page : pages) {
            DefaultMenuItem item;
            item = DefaultMenuItem.builder()
                    .value(page.getTitle())
                    .command("#{mainViewUserController.selectPage(" + page.getId_page() + ")}")
                    .build();
            pagesMenu.getElements().add(item);
            
        }
        
        model.getElements().add(pagesMenu);
    }

    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setPageEJB(PageFacadeLocal pageEJB) {
        this.pageEJB = pageEJB;
    }

    public void setNewPage(Page newPage) {
        this.newPage = newPage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void setSelectedPage(Page selectedPage) {
        this.selectedPage = selectedPage;
    }

    public void setListUserPages(List<Page> listUserPages) {
        this.listUserPages = listUserPages;
    }

    public void setUserEJB(UserFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }
    
    public void selectPage(int pageId) {
        selectedPage = getPageById(pageId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPage", selectedPage);
    }

    public Page getPage() {
        return page;
    }

    public PageFacadeLocal getPageEJB() {
        return pageEJB;
    }

    public Page getNewPage() {
        return newPage;
    }

    public User getUser() {
        return user;
    }

    public Page getSelectedPage() {
        return selectedPage;
    }

    public UserFacadeLocal getUserEJB() {
        return userEJB;
    }
    
    public void addPage(){
        newPage.setUser(user);
        pageEJB.create(newPage);
    }
    
    public MenuModel getModel() {
        return model;
    }
    
    public List<Page> getListUserPages(){
        this.listUserPages = pageEJB.findAllPages(user.getId_user());
        return listUserPages;
    }
    
    public Page getPageById(int id_page){
         List<Page> pages = getListUserPages();
        for (Page page : pages) {
            if (id_page == page.getId_page()) {
                return page;
            }
        }
        return null;
    }
}
