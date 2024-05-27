/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.BlockFacadeLocal;
import EJB.PageFacadeLocal;
import EJB.TextFacadeLocal;
import EJB.UserFacadeLocal;
import EJB.ListFacadeLocal;
import Modelo.Block;
import Modelo.Page;
import Modelo.Text;
import Modelo.User;
import Modelo.*;
//import Modelo.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuItem.Builder;
import org.primefaces.model.menu.DefaultMenuModel;
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
    private List<Block> blocks; //luego cambiar por bloques objetos
    //END MODIFICAR
    private String newTextNote;
    private int numberElementsNewList;
    private String elementsList;
    

    public void setElementsList(String elementsList) {
        this.elementsList = elementsList;
    }

    public String getElementsList() {
        return elementsList;
    }

    public String getNewTextNote() {
        return newTextNote;
    }

    public int getNumberElementsNewList() {
        return numberElementsNewList;
    }

    public void setNewTextNote(String newTextNote) {
        this.newTextNote = newTextNote;
    }

    public void setNumberElementsNewList(int numberElementsNewList) {
        this.numberElementsNewList = numberElementsNewList;
    }

    
    

    @EJB
    private PageFacadeLocal pageEJB;
    private UserFacadeLocal userEJB;
    private BlockFacadeLocal blockEJB;
    private TextFacadeLocal textEJB;
    private ListFacadeLocal listEJB;
    
    @PostConstruct
    public void init(){
        
        page = new Page();
        newPage = new Page();
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
        
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("actualPage")!=null){
        
            getBlocksOfAcutalPage();
                
        }
        
        
        //Modelo para la lista de paginas del usuario
        model = new DefaultMenuModel();
        
        List<Page> pages = getListUserPages();
        
        for (Page page : pages) {
            DefaultMenuItem menuItem = new DefaultMenuItem();
            menuItem.setTitle(page.getTitle());
            menuItem.setCommand("#{mainViewUserController.selectPage(" + page.getId_page() + ")}");
            model.getElements().add(menuItem);
                    
        }
    }
    
    public void getBlocksOfAcutalPage(){
        
        Page actualPage = (Page) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("actualPage");
                    
        blocks = new ArrayList<>();
        blocks = blockEJB.getBlocksByPage(actualPage);
    }
    
    public void createList() {
        
        //El user mete los elementos con saltos de linea
        String[] elementsArray = elementsList.split("\n");
        
        //En bbdd los guardamos separados por ; para saber como representarlos
        String transformedList = String.join(";", elementsArray);
    
        Page actualPage = (Page) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("actualPage");
        
        Block newBlock = new Block();
        newBlock.setPage(actualPage);
        blockEJB.create(newBlock);
        
        Modelo.List newList = new Modelo.List();
        newList.setBlock(newBlock);
        newList.setText(elementsList);
        listEJB.create(newList);
        
        
        
    }
    
    public void createNote(){
        
        Page actualPage = (Page) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("actualPage");
        
        Block newBlock = new Block();
        newBlock.setPage(actualPage);
        blockEJB.create(newBlock);
        
        Text newText = new Text();
        newText.setBlock(newBlock);
        newText.setText(newTextNote);
        textEJB.create(newText);
        
    }
    

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Block> getBlocks() {
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("globalPage", selectedPage);
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
        System.out.println(newPage.getTitle() +" - "+ newPage.getId_page() +" - "+ newPage.getNum_blocks()+" - "+ newPage.getUser()+" - "+ newPage.getVisits());
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