package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s = "0,0,6,0,0,0,5,0,7," +
                "0,9,0,0,6,8,0,0,0," +
                "7,0,8,0,0,5,0,0,4," +
                "0,0,1,5,0,4,2,9,0," +
                "0,7,0,0,3,0,0,0,0," +
                "3,0,9,0,0,0,7,1,0," +
                "0,5,0,0,4,0,8,0,9," +
                "0,8,2,6,0,0,0,0,0," +
                "0,0,0,0,2,7,4,0,6";
        Sudoku sudoku = new Sudoku(s);
        sudoku.imprimir();
        int[] x = sudoku.lugarMenosOpciones();
        System.out.println(x[0] + ", " + x[1]);
    }

}
