package com.company;

public class Sudoku {
    int sudoku[][];

    public Sudoku() {
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
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

    public int espaciosLibresX(int y) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][y] == 0) {
                count++;
            }
        }
        return count;
    }

    public int espaciosLibresY(int x) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (sudoku[x][i] == 0) {
                count++;
            }
        }
        return count;
    }

    public int espaciosLibresCubo(int n) {
        int xi = n * 3 % 9;
        int yi = n / 3 * 3;
        int count = 0;
        for (int i = yi; i < yi + 3; i++) {
            for (int j = xi; j < xi + 3; j++) {
                if (sudoku[i][j] == 0)
                    count++;
            }
        };
        return count;
    }

    public int espaciosLibres(int x, int y) {
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
        System.out.println(x + ", " + y + "-->" + nCubo);
        int espaciosL = espaciosLibresX(y) + espaciosLibresY(x) + espaciosLibresCubo(nCubo);
        if (sudoku[x][y] == 0)
            espaciosL-=2;
        return espaciosL;
    }

    public int[] lugarMenosOpciones() {
        int[] coordenadas = {0, 0};
        int mEspaciosLibres = 17;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int eLibres = this.espaciosLibres(i, j);
                //System.out.println(i + ", " + j + "= " + this.espaciosLibres(i, j));
                if (sudoku[i][j] == 0 && eLibres < mEspaciosLibres) {
                    mEspaciosLibres = eLibres;
                    coordenadas[0] = i;
                    coordenadas[1] = j;
                }
            }
        }
        System.out.println(mEspaciosLibres);
        return coordenadas;
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
