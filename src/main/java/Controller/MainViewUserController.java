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
    private List<Block> blocks; 
    private List<Text> notes;
    private List<Lista> lists;
    private Text noteToDelete;
    private Text noteToChange;
    private String newTextNoteToChange;
    private String titleOfPage;

    public Text getNoteToDelete() {
        return noteToDelete;
    }

    public Text getNoteToChange() {
        return noteToChange;
    }

    public String getNewTextNoteToChange() {
        return newTextNoteToChange;
    }

    public void setNoteToDelete(Text noteToDelete) {
        this.noteToDelete = noteToDelete;
    }

    public void setNoteToChange(Text noteToChange) {
        this.noteToChange = noteToChange;
    }

    public void setNewTextNoteToChange(String newTextNoteToChange) {
        this.newTextNoteToChange = newTextNoteToChange;
    }

    public void setNotes(List<Text> notes) {
        this.notes = notes;
    }

    public void setGlobalPage(Page globalPage) {
        this.globalPage = globalPage;
    }

    public void setBlockEJB(BlockFacadeLocal blockEJB) {
        this.blockEJB = blockEJB;
    }

    public void setTextEJB(TextFacadeLocal textEJB) {
        this.textEJB = textEJB;
    }

    public void setListEJB(ListFacadeLocal listEJB) {
        this.listEJB = listEJB;
    }

    public List<Text> getNotes() {
        return notes;
    }

    public Page getGlobalPage() {
        return globalPage;
    }

    public BlockFacadeLocal getBlockEJB() {
        return blockEJB;
    }

    public TextFacadeLocal getTextEJB() {
        return textEJB;
    }

    public ListFacadeLocal getListEJB() {
        return listEJB;
    }
    private String newTextNote;
    private int numberElementsNewList;
    private String elementsList;
    private Page globalPage;
    

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
    @EJB
    private UserFacadeLocal userEJB;
    @EJB
    private BlockFacadeLocal blockEJB;
    @EJB
    private TextFacadeLocal textEJB;
    @EJB
    private ListFacadeLocal listEJB;
    
    @PostConstruct
    public void init(){
        
        page = new Page();
        newPage = new Page();
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("globalUser");
        titleOfPage = "";
    
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
                    .update("listOfPages")
                    .oncomplete("reloadPage();")
                    .build();
            pagesMenu.getElements().add(item);
            
        }
        
        model.getElements().add(pagesMenu);
        
        /*if(pages.size()!=0){
            selectPage(pages.get(0).getId_page());
        }*/
        
        
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPage")!=null){
            
            globalPage = (Page) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPage");
               
            getBlocksOfAcutalPage(globalPage);
            getNotesOfBlocks();
            titleOfPage=globalPage.getTitle();
            getListsOfBlocks();
                
        }else{
            
            System.out.println("Pagina no cogida!!");
        }
        
    }
    
    public void getNoteToDelete(Text note){
        
        this.noteToDelete = note;
    }
    
    public void getNoteToChange(Text note){
        
        this.noteToChange = note;
        this.newTextNoteToChange = this.noteToChange.getText();
    }
    
    public void deleteNote(){
        
        textEJB.remove(this.noteToDelete);
    }
    
    public void modifyNote(){
        
        textEJB.modifyNote(this.noteToChange, this.newTextNoteToChange);
    }
    
    public void getNotesOfBlocks() {
        notes = new ArrayList<>();
        try {
            for (Block block : this.blocks) {
                int blockId = block.getIdBlock();
                List<Text> blockNotes = this.textEJB.getNotesOfBlocks(blockId);
                if (blockNotes != null) {
                    notes.addAll(blockNotes);
                }
            }
            if(notes.size()!=0){
                System.out.println("Esta llenando las notas");
            }
        } catch (NullPointerException e) {
            System.out.println("textEJB is null");
        }
    }
    
    
    
    public void getListsOfBlocks() {
    lists = new ArrayList<>(); 
    try {
        for (Block block : this.blocks) {
            int blockId = block.getIdBlock();
            List<Lista> blockLists = this.listEJB.getListsOfBlocks(blockId);
            if (blockLists != null) {
                lists.addAll(blockLists);
            }
        }
        
        for (Lista lista : lists) {
            
            String modifiedText = lista.getText().replace(";", "<br /><br /> - ");
            lista.setText(modifiedText);
        }
        
        if(notes.size()!=0){
                System.out.println("Esta llenando las notas");
            }
    } catch (NullPointerException e) {
        System.out.println("listEJB is null");
    }
}

    public void setLists(List<Lista> lists) {
        this.lists = lists;
    }

    public List<Lista> getLists() {
        return lists;
    }

    
    
    public void getBlocksOfAcutalPage(Page actualPage) {
        
        int idPage = actualPage.getId_page();
        
        blocks = new ArrayList<>();
        try {
            blocks = this.blockEJB.getBlocksByPage(idPage);
        } catch (NullPointerException e) {
            System.out.println("blockEJB is null");
        }
    }
    
    public void createList() {
        
        //El user mete los elementos con saltos de linea
        String[] elementsArray = elementsList.split("\n");
        
        //En bbdd los guardamos separados por ; para saber como representarlos
        String transformedList = String.join(";", elementsArray);
    
        Page actualPage = (Page) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPage");
        
        Block newBlock = new Block();
        newBlock.setPage(actualPage);
        blockEJB.create(newBlock);
        
        Modelo.Lista newList = new Modelo.Lista();
        newList.setBlock(newBlock);
        newList.setText(elementsList);
        listEJB.create(newList);
        
        
        
    }
    
    public void createNote(){
        
        //Page actualPage = (Page) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPage");
        
        Block newBlock = new Block();
        newBlock.setPage(this.globalPage);
        blockEJB.create(newBlock);
        
        System.out.println("Bloque creado supuestamente");
        Text newText = new Text();
        newText.setBlock(newBlock);
        newText.setText(newTextNote);
        textEJB.create(newText);
        System.out.println("Nota creado supuestamente");
        
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPage", selectedPage);
    }

    public void setTitleOfPage(String titleOfPage) {
        this.titleOfPage= titleOfPage;
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

    public String getTitleOfPage() {
        return titleOfPage;
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
