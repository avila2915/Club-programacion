package javaapplication2;

import java.util.*;
public class JavaApplication2 {
   
    static String triqui[][] = new String[3][3];
    static int triquiMinMax[][] = new int[3][3];
    String[][] temp = new String[3][3];
    boolean turnoPc = false;
    int fila, columna = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
       
       int coordenadasFila, coordenadasColumna;
        char vacio = '-';
        char letra1 = 'X';

        char letra2 = 'O';
        String aux = "X";
        String aux2 = "O";
        triqui[0][0] = vacio + "";
        triqui[0][1] = vacio + "";
        triqui[0][2] = vacio + "";
        triqui[1][0] = vacio + "";
        triqui[1][1] = vacio + "";
        triqui[1][2] = vacio + "";
        triqui[2][0] = vacio + "";
        triqui[2][1] = vacio + "";
        triqui[2][2] = vacio + "";

        System.out.println("Ingrese su nombre : ");
        String jugador1 = in.next();

        System.out.println("");

        System.out.println("Las letras que les corresponden son : ");
        System.out.println("X = " + jugador1);
        System.out.println("O = Sotfware");

        System.out.println("");

        for (int i = 0; i < 10; i++) {
            //Jugador 1
            System.out.println(jugador1 + " ingrese una coordenada de la fila y columna: ");
            coordenadasFila = in.nextInt();
            coordenadasColumna = in.nextInt();

            triqui[coordenadasFila][coordenadasColumna] = aux;
            triquiMinMax[coordenadasFila][coordenadasColumna] = -1;

            mostrarTablero();

            if (hallarGanador(aux)) {
                System.out.println("El ganador es: " + jugador1);
                break;
            }

            //Sotfware
            ReiniciarTrquiMinMax();
            marcaSiguiente(triqui, true, true);
            SeleccionarMejorOpcion();
            System.out.println("Sotfware : ");
            mostrarTablero();

            if (hallarGanador(aux2)) {
                System.out.println("El ganador es: " + jugador1);
                break;
            }
        }

    }

    public static void mostrarTablero() {
        for (int j = 0; j < triqui.length; j++) {
            for (int p = 0; p < triqui[j].length; p++) {
                System.out.print(triqui[j][p] + " ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    public static boolean hallarGanador(String auxDefecto) {
        if ((triqui[0][0].compareTo(auxDefecto) == 0 && triqui[0][1].compareTo(auxDefecto) == 0 && triqui[0][2].compareTo(auxDefecto) == 0)
                || (triqui[1][0].compareTo(auxDefecto) == 0 && triqui[1][1].compareTo(auxDefecto) == 0 && triqui[1][2].compareTo(auxDefecto) == 0)
                || (triqui[2][0].compareTo(auxDefecto) == 0 && triqui[2][1].compareTo(auxDefecto) == 0 && triqui[2][2].compareTo(auxDefecto) == 0)
                || (triqui[0][0].compareTo(auxDefecto) == 0 && triqui[1][0].compareTo(auxDefecto) == 0 && triqui[2][0].compareTo(auxDefecto) == 0)
                || (triqui[0][1].compareTo(auxDefecto) == 0 && triqui[1][1].compareTo(auxDefecto) == 0 && triqui[2][1].compareTo(auxDefecto) == 0)
                || (triqui[0][2].compareTo(auxDefecto) == 0 && triqui[1][2].compareTo(auxDefecto) == 0 && triqui[2][2].compareTo(auxDefecto) == 0)
                || (triqui[0][0].compareTo(auxDefecto) == 0 && triqui[1][1].compareTo(auxDefecto) == 0 && triqui[2][2].compareTo(auxDefecto) == 0)
                || (triqui[0][2].compareTo(auxDefecto) == 0 && triqui[1][1].compareTo(auxDefecto) == 0 && triqui[2][0].compareTo(auxDefecto) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void ReiniciarTrquiMinMax() {
        for (int i = 0; i < triquiMinMax.length; i++) {
            for (int j = 0; j < triquiMinMax[0].length; j++) {
                if (triqui[i][j].compareTo("-") == 0) {
                    triquiMinMax[i][j] = 0;
                } else {
                    triquiMinMax[i][j] = -1;
                }
            }
        }
    }

    public static void SeleccionarMejorOpcion() {
        int max = -1, fila = 0, col = 0;
        for (int i = 0; i < triquiMinMax.length; i++) {
            for (int j = 0; j < triquiMinMax[0].length; j++) {
                System.out.print(triquiMinMax[i][j] + " ");
                if (triquiMinMax[i][j] > max) {
                    max = triquiMinMax[i][j];
                    fila = i;
                    col = j;
                }
            }
            System.out.println("");
        }
        triqui[fila][col] = "O";
    }

    public static boolean isEmpate() {
        for (int j = 0; j < triqui.length; j++) {
            for (int p = 0; p < triqui[j].length; p++) {
                if (triqui[j][p].compareTo("-") == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int marcaSiguiente(String[][] temp, boolean turnoPc, boolean primeraVez) {
        if (hallarGanador("O")) {
            return 1;
        }
        if (hallarGanador("X")) {
            return -1;
        }
        if (isEmpate()) {
            return 0;
        }

        int cont = 0;

        if (temp[0][0].compareTo("-") == 0) {
            if (turnoPc) {
                temp[0][0] = "O";
            } else {
                temp[0][0] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[0][0] = minMax;
            }
            temp[0][0] = "-";
        }
        if (temp[0][1].compareTo("-") == 0) {
            if (turnoPc) {
                temp[0][1] = "O";
            } else {
                temp[0][1] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[0][1] = minMax;
            }
            temp[0][1] = "-";
        }
        if (temp[0][2].compareTo("-") == 0) {
            if (turnoPc) {
                temp[0][2] = "O";
            } else {
                temp[0][2] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[0][2] = minMax;
            }
            temp[0][2] = "-";
        }
        if (temp[1][0].compareTo("-") == 0) {
            if (turnoPc) {
                temp[1][0] = "O";
            } else {
                temp[1][0] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[1][0] = minMax;
            }
            temp[1][0] = "-";
        }
        if (temp[1][1].compareTo("-") == 0) {
            if (turnoPc) {
                temp[1][1] = "O";
            } else {
                temp[1][1] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[1][1] = minMax;
            }
            temp[1][1] = "-";
        }
        if (temp[1][2].compareTo("-") == 0) {
            if (turnoPc) {
                temp[1][2] = "O";
            } else {
                temp[1][2] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[1][2] = minMax;
            }
            temp[1][2] = "-";
        }
        if (temp[2][0].compareTo("-") == 0) {
            if (turnoPc) {
                temp[2][0] = "O";
            } else {
                temp[2][0] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[2][0] = minMax;
            }
            temp[2][0] = "-";
        }
        if (temp[2][1].compareTo("-") == 0) {
            if (turnoPc) {
                temp[2][1] = "O";
            } else {
                temp[2][1] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[2][1] = minMax;
            }
            temp[2][1] = "-";
        }
        if (temp[2][2].compareTo("-") == 0) {
            if (turnoPc) {
                temp[2][2] = "O";
            } else {
                temp[2][2] = "X";
            }
            int minMax = marcaSiguiente(temp, !turnoPc, false);
            cont += minMax;
            if (primeraVez) {
                triquiMinMax[2][2] = minMax;
            }
            temp[2][2] = "-";
        }

        return cont;
       
    }
   
}