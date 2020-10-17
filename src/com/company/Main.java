package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s = "0,0,9,7,4,8,0,0,0," +
                "7,0,0,0,0,0,0,0,0," +
                "0,2,0,1,0,9,0,0,0," +
                "0,0,7,0,0,0,2,4,0," +
                "0,6,4,0,1,0,5,9,0," +
                "0,9,8,0,0,0,3,0,0," +
                "0,0,0,8,0,3,0,2,0," +
                "0,0,0,0,0,0,0,0,6," +
                "0,0,0,2,7,5,9,0,0";
        Sudoku sudoku = new Sudoku(s);
        sudoku.imprimir();
        long time = System.currentTimeMillis();
        sudoku.colocacion();
        new Sudoku(sudoku.solucion).imprimir();
        System.out.println("En " + (System.currentTimeMillis() - time) + " ms.");

        

    }

}
