package com.company;

import java.util.Stack;

public class Sudoku {
    int sudoku[][];
    boolean[][] posiblesX = new boolean[9][9];
    boolean[][] posiblesY = new boolean[9][9];
    boolean[][] posiblesCubo = new boolean[9][9];
    int[][][] numerosPosibles =  new int[9][9][9];
    public static int nRamas = 1;
    public static int[][] solucion = new int[9][9];

    public Sudoku() {
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
            }
        }
    }

    public Sudoku(int[][] board) {
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = board[i][j];
            }
        }
    }

    public Sudoku(String s) {
        sudoku = new int[9][9];
        if (s.length() == 161) {
            int arrI[] = new int[81];
            int count = 0;
            String arrS[] = s.split(",");
            for (int i = 0; i < 81; i++) {
                arrI[i] = Integer.parseInt(arrS[i]);
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = arrI[count];
                    count++;
                }
            }
        } else {
            System.out.println("Formato incorrecto");
        }
    }

    public Sudoku(char board[][]) {
        sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = (board[i][j] == ',')? 0 : Character.valueOf( board[i][j]);
            }
        }

    }

    public void restaurarValores() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                posiblesX[i][j] = false;
                posiblesY[i][j] = false;
                posiblesCubo[i][j] = false;
                for (int k = 0; k < 9; k++) {
                    numerosPosibles[i][j][k] = 0;
                }
            }
        }
    }

    public void colocacion() {
        for (int i = 0; i < 9; i++) {
            this.espaciosLibresX(i);
            this.espaciosLibresY(i);
            this.espaciosLibresCubo(i);
        }

//        for (int i = 0; i < 9; i++) {
//            if (this.posiblesX[3][i]) {
//                System.out.print(i + 1);
//            }
//        }
//
//        System.out.println();
//
//        for (int i = 0; i < 9; i++) {
//            if (this.posiblesY[1][i]) {
//                System.out.print(i + 1);
//            }
//        }
//
//        System.out.println();
//
//        for (int i = 0; i < 9; i++) {
//            if (this.posiblesCubo[1][i]) {
//                System.out.print(i + 1);
//            }
//        }
//
//        System.out.println();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                espaciosLibres(i, j);
            }
        }

        int[] x = this.lugarMenosOpciones();
        //System.out.println("(" + x[0] + ", " + x[1] + ")--> " + x[2]);

        if (x[2] == 1) {
            int nNumero = 0;
            for (int i = 0; i < 9; i++) {
                if (this.numerosPosibles[x[0]][x[1]][i] != 0) {
                    nNumero = this.numerosPosibles[x[0]][x[1]][i];
                }
            }
            sudoku[x[0]][x[1]] = nNumero;
            this.restaurarValores();
            this.colocacion();
        } else if (x[2] == 9) {
            System.out.println("Listo.");
            solucion = this.sudoku;
            return;
        } else if (x[2] == 0) {
            System.out.println("Una rama termino");
            nRamas --;
            System.out.println("Numero de ramas -> " + nRamas);

        } else {
            System.out.println("Se crearon " + x[2] + " ramas.");
            nRamas += x[2] - 1;
            System.out.println("Numero de ramas -> " + nRamas);

            //Sudoku[] su = new Sudoku[x[2]];
            int[] opciones = new int[x[2]];
            int count = 0;
            for (int i = 0; i < 9; i++) {
                if (numerosPosibles[x[0]][x[1]][i] != 0) {
                    opciones[count] = numerosPosibles[x[0]][x[1]][i];
                    count++;
                }
            }


            for (int i = 0; i < x[2]; i++) {
                int[][] temp = this.sudoku;
                temp[x[0]][x[1]] = opciones[i];
                new Sudoku(temp).colocacion();
            }

            //this.imprimir();
        }

    }

    public void espaciosLibresX(int y) {
//        int count = 0;
//        for (int i = 0; i < 9; i++) {
//            if (sudoku[i][y] == 0) {
//                count++;
//            }
//        }
//        return count;

        for (int i = 0; i < 9; i++) {
            if (sudoku[y][i] != 0) {
                posiblesX[y][sudoku[y][i] - 1] = true;
            }
        }
    }

    public void espaciosLibresY(int x) {
//        int count = 0;
//        for (int i = 0; i < 9; i++) {
//            if (sudoku[x][i] == 0) {
//                count++;
//            }
//        }
//        return count;

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][x] != 0) {
                posiblesY[x][sudoku[i][x] - 1] = true;
            }
        }
    }

    public void espaciosLibresCubo(int n) {
//        int xi = n * 3 % 9;
//        int yi = n / 3 * 3;
//        int count = 0;
//        for (int i = yi; i < yi + 3; i++) {
//            for (int j = xi; j < xi + 3; j++) {
//                if (sudoku[i][j] == 0)
//                    count++;
//            }
//        }
//        return count;

        int xi = n * 3 % 9;
        int yi = n / 3 * 3;
        for (int i = yi; i < yi + 3; i++) {
            for (int j = xi; j < xi + 3; j++) {
                if (sudoku[i][j] != 0) {
                    //System.out.println(n);
                    //System.out.println(sudoku[i][j]);
                    posiblesCubo[n][sudoku[i][j] - 1] = true;
                }
            }
        }

    }

    public void espaciosLibres(int x, int y) {
        int nCubo;
        if (y < 3) {
            if (x < 3) {
                nCubo = 0;
            } else if (x < 6) {
                nCubo = 3;
            } else {
                nCubo = 6;
            }
        } else if (y < 6) {
            if (x < 3) {
                nCubo = 1;
            } else if (x < 6) {
                nCubo = 4;
            } else {
                nCubo = 7;
            }
        } else {
            if (x < 3) {
                nCubo = 2;
            } else if (x < 6) {
                nCubo = 5;
            } else {
                nCubo = 8;
            }
        }
        //System.out.println(x + ", " + y + "-->" + nCubo);

        for (int i = 0; i < 9; i++) {
            //System.out.println(x + ", " + y);
            //System.out.println("x-->" + posiblesX[y][i] + " y-->"
            // + posiblesY[x][i] + " cubo-->" + posiblesCubo[nCubo][i]);
            if (!posiblesX[x][i] && !posiblesY[y][i] && !posiblesCubo[nCubo][i]) {
                //System.out.println("un numero entro");
                numerosPosibles[x][y][i] = i + 1;
            }
        }
    }

    public int[] lugarMenosOpciones() {
        int[] datos = {0, 0, 0};
        int mNumerosPosibles = 9;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int count = 0;
                for (int k = 0; k < 9; k++) {
                    if (numerosPosibles[i][j][k] != 0) {
                        count++;
                    }
                }
                int eLibres = count;
                //System.out.println(i + ", " + j + "= " + this.espaciosLibres(i, j));
                if (sudoku[i][j] == 0 && eLibres < mNumerosPosibles) {
                    mNumerosPosibles = eLibres;
                    datos[0] = i;
                    datos[1] = j;
                }
            }
            datos[2] = mNumerosPosibles;
        }
        return datos;
    }

    public void imprimir() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
                if ((j + 1) % 3 == 0)
                    System.out.print(" ");
            }
            if ((i + 1) % 3 == 0)
                System.out.print("\n");
            System.out.print("\n");
        }
    }
}
