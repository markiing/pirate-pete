/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Here is where the magic happens.
 * The abstraction of Grid is formed for a Cell matrix;
 * @see Cell
 */
public class Grid {
    
    private final String[] colLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    final private Cell[][] cells = new Cell[10][10];
    private int totalTresures = 0;
    
    
    public Grid(){
        populateTreasures();
        fillGrid();
    }
    
    private void fillGrid(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10 ; j++){
                if(cells[i][j] == null) { //If the address is Null
                    cells[i][j] = new Cell(i, j, false); // Create a cell in each    
                }
            }
        }
    }
    
    /**
     * Populate Treasures
     */
    private void populateTreasures(){
          //Populate the cell matrix with empty Cells, giving only their address (row and col)
        try{
            InputStream in = getClass().getResourceAsStream("PiratePete.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("|");
                if(!line.startsWith("COL")){
                    try{
                        int row = Integer.parseInt(parts[parts.length - 1]);
                        int col = getColAsInt(parts[0]);
                        cells[row][col] = new Cell(row, col, true);
                        totalTresures++; 
                    }catch(Exception ex){
                        // Do nothing and continue processing PiratePete.txt
                    }
                }
            }
            reader.close();
            in.close();
        }catch(IOException  ex){
            System.err.println("Could not load file."+ ex.getMessage());
        }
    }
    
    /**
     * Convert the string col (A, B, C...) in index using colLabels variable as guide.
     * @param col
     * @return colAsIndex
     */
    private int getColAsInt(String col) {
        int colAsIndex = 0;
        for(int colIndex = 0; colIndex < colLabels.length; colIndex++){
            if(colLabels[colIndex].equals(col)) {
                if(colIndex - 1 < 0){
                    colAsIndex = 0;
                }else{
                    colAsIndex = colIndex;    
                }
                break;
            }
        }
        return colAsIndex;
    }
    
    /**
     * 
     * @param sCol Column as String (A, B, C...)
     * @param row
     * @return a cell in the matrix;
     */
    public Cell getCell(String sCol, Integer row){
         Integer col = getColAsInt(sCol); //Map the input string col (A, B, C...) as col index, which was mapped in Grid instanciation.
         return  cells[row - 1][col];
    }
    
    public void update(Cell cell){
       cells[cell.getRow() - 1][cell.getCol()] =  cell;
    }
 
    /**
     * Render the Grid
     */
    public void paint(){
        System.out.printf("\t");
        for(String col : colLabels) System.out.printf("%s \t", col);
        System.out.printf("\n");
        for(int i = 0; i < 10; i++){
          System.out.printf("%d \t", i+1);
          for(int j = 0; j < 10; j++){
            Cell cell = cells[i][j];
            System.out.printf("%s \t", cell.getDug()? "X":"O");
          }
          System.out.printf("\n");
        }
    }

    public int getTotalTresures() {
        return totalTresures;
    }
    
    
    
}
