/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author albamagdaleno
 */
@Entity
@Table(name="tables")
public class Table implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTable;
    
    @Column(name="text_str")
    private String text;
    
    @JoinColumn(name="id_block")
    @ManyToOne
    private Block block;

    public int getIdTable() {
        return idTable;
    }

    public String getText() {
        return text;
    }

    public Block getBlock() {
        return block;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Table other = (Table) obj;
        if (this.idTable != other.idTable) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.block, other.block)) {
            return false;
        }
        return true;
    }
    
    
}

