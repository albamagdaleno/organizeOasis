/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.PageFacadeLocal;
import Modelo.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alimb
 */
@Named
@ViewScoped
public class MainViewUserController implements Serializable{
    
    private Page page;
    private Page newPage;
    //MODIFICAR
    private List<String> blocks; //luego cambiar por bloques objetos
    //END MODIFICAR

    @EJB
    private PageFacadeLocal pageEJB;
    
    @PostConstruct
    public void init(){
        
        page = new Page();
        newPage = new Page();
        
        //luego rellenar con bloques de verdad MODIFICAR
        blocks = new ArrayList<>();
        // Agregar bloques de ejemplo
        blocks.add("Block 1");
        blocks.add("Block 2");
        blocks.add("Block 3");
        blocks.add("Block 4"); // END MODIFICAR
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

    public Page getPage() {
        return page;
    }

    public PageFacadeLocal getPageEJB() {
        return pageEJB;
    }

    public Page getNewPage() {
        return newPage;
    }
    
    public void addPage(){
        System.out.println(newPage.getTitle() +" - "+ newPage.getId_page() +" - "+ newPage.getNum_blocks()+" - "+ newPage.getUser()+" - "+ newPage.getVisits());
        pageEJB.create(newPage);
    }
}
