/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Manel
 */
import java.util.Scanner;

public class Main {

//Primero voy a construir el buscaminas en terminal y luego voy a buscar como implementarlo de forma visual
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Declaracion de variables
        int random = (int) (Math.random() * 17);;
        int[][] tablero = new int[8][8];
        int[][] tableroTapado = new int[8][8];
        int contador1s = 0;
        int numeroBombas = 0;

        //Selecion de cuantas bombas quiere el usuario
        do {
            System.out.println("Inserta el numero de bombas que deseas tener (el limite es 20)");
            numeroBombas = scan.nextInt();
            if (numeroBombas > 21) {
                System.out.println("Inserte un numero valido");
            }
        } while (numeroBombas > 21);

        //Colocando las bombas en un lugar aleatorio
        for (int i = 0; i < 8; i++) {
            for (int u = 0; u < 8; u++) {
                tablero[i][u] = 0;
                tableroTapado[i][u] = 9;
            }
        }
        for (int i = 0; i < numeroBombas; i++) {
            int rando = (int) (Math.random() * 8);
            int randy = (int) (Math.random() * 8);
            if (tablero[rando][randy] == 9) {
                i--;
            } else {
                tablero[rando][randy] = 9;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int u = 0; u < 8; u++) {
                if (tablero[i][u] == 9) {
                    contador1s++;
                }
            }
        }

        //Asignar los numeros a las casillas
        for (int f = 0; f < 8; f++){
            for (int c = 0; c < 8; c++){
                if (tablero[f][c] != 9){
                    int fModLow = -1;
                    int fModTop = 2;
                    int cModLow = -1;
                    int cModTop = 2;
                    
                    int cuentaBomba = 0;
                    
                    if (f == 0) {
                        fModLow = 0;
                    } else if (f == 7) {
                        fModTop = 1;
                    }

                    //Modificador por si el jugador escoje la columna de la izquierda o derecha
                    if (c == 0) {
                        cModLow = 0;
                    } else if (c == 7) {
                        cModTop = 1;
                    }
                    
                    for(int fil = fModLow; fil < fModTop; fil++){
                        for(int col = cModLow; col < cModTop; col++){
                            if (tablero[fil + f][col + c] == 9){
                                cuentaBomba++;
                            }
                        }
                    }
                tablero[f][c] = cuentaBomba;    
                }
            }
        }
        
        
        
        //Enseñando al jugador el tablero y empezando el juego
        boolean muerto = false;
        do {
            System.out.println(contador1s + " Bombas en el Tablero");
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    System.out.print(tableroTapado[i][u]);
                }
                System.out.println(" ");
            }
            System.out.println("Seleciona que casillas destapar del 1 al 8 (primer numero fila, segundo columna)");

            int fila = scan.nextInt();
            int columna = scan.nextInt();
            fila--;
            columna--;

            if (tablero[fila][columna] != 9) {
                int numeroSoy = 0;
                int filaModLow = -1;
                int filaModTop = 2;
                int colModLow = -1;
                int colModTop = 2;

                //Modificador por si el jugador escoje la fila de arriba o la de abajo
                if (fila == 0) {
                    filaModLow = 0;
                } else if (fila == 7) {
                    filaModTop = 1;
                }

                //Modificador por si el jugador escoje la columna de la izquierda o derecha
                if (columna == 0) {
                    colModLow = 0;
                } else if (columna == 7) {
                    colModTop = 1;
                }

                // Calculo de que numero deberia de ser
                for (int f = filaModLow; f < filaModTop; f++) {
                    for (int c = colModLow; c < colModTop; c++) {
                        if (tablero[fila + f][columna + c] == 9) {
                            numeroSoy++;
                        }
                    }
                }

                tableroTapado[fila][columna] = numeroSoy;
                System.out.println(numeroSoy);
                if (numeroSoy == 0) {
                    for (int f = filaModLow; f < filaModTop; f++) {
                        for (int c = colModLow; c < colModTop; c++) {
                            tableroTapado[fila + f][columna + c] = tablero[fila + f][columna + c];
                        }
                    }
                }
            }else if (tablero[fila][columna] == 9) {
                System.out.println("Moriste");
                muerto = true;
                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        System.out.print(tablero[i][u]);
                    }
                    System.out.println(" ");
                }
            }
        } while (muerto == false);
    }

    
}
