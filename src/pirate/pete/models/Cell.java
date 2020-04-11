package pirate.pete.models;

/**
 *
 * A cell is the minor part of an grid. Each cell has your own address (Row and Col) and some metadatas (if have treasure, dug...)
 */
public class Cell {
    
    private Integer col;
    private Integer row;
    private Boolean haveTreasure;
    private Boolean dug;
    private String whoDug;

    public Cell(Integer row, Integer col, Boolean haveTreasure) {
        this.col = col;
        this.row = row;
        this.haveTreasure = haveTreasure;
        this.dug = false;
    }
    
    public String getWhoDug() {
        return whoDug;
    }
    
    public Integer getCol() {
        return col;
    }

    public Integer getRow() {
        return row + 1;
    }

    public void setDug(Boolean digged) {
        this.dug = digged;
    }

    public Boolean getDug() {
        return dug;
    }

    public void setWhoDug(String whoDug) {
        this.whoDug = whoDug;
    }

    public Boolean haveTreasure() {
        return haveTreasure;
    }
    
    
    
    
    
    
}
