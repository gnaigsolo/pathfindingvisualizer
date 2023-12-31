package models;



public class Cell {
    CellType cellType;

    public Cell() {
        cellType = new Way();
    }
    public String getChar() {
        return this.cellType.getChar();
    }
    public void reset() {
        cellType.reset();
    }
     public void setRout() {
        this.cellType.setRout();
     }
    public void markMove() {
        cellType.markMoved();
    }
    public boolean isMovable() {
        return cellType.isCanMove();
    }
    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}
