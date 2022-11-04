/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laberinto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 *
 * @author lrosellg
 */
public class Laberinto {

    private static String[][] lab;
    private static int contX;
    private static int contY;
    private static ArrayDeque<Position> way = new ArrayDeque();
    private static Position anterior;

    public static void readFile(String path) {
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            int line = 0;
            int flag = 1;
            //Recorro el fichero una primera vez para sacar el tamaño de la matriz con contX y contY
            while (sc.hasNextLine()) {
                String st = sc.nextLine();
                String[] array = st.split("");
                if (flag == 1) {
                    contY = array.length;
                    flag++;
                }
                line++;
            }
            contX = line;
            int it = 0;
            //Inicializo la matriz del laberinto
            lab = new String[contX][contY];
            //Relleno laberinto
            Scanner sca = new Scanner(file);
            while (sca.hasNextLine()) {
                String st = sca.nextLine();

                String[] array = st.split("");

                for (int i = 0; i < contY; i++) {
                    lab[it][i] = array[i];
                }
                it++;
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("ad");
        }
    }

    public static void showLab() {
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                System.out.print(lab[i][j]);

            }
            System.out.println(" ");
        }
    }

    public static void solver(String path) {
        readFile(path);
        int corX = 0;
        int corY = 0;
        while (!lab[corX][corY].equals("M")) {
            //Encontrar entrada
            for (int i = 0; i < lab.length; i++) {
                for (int j = 0; j < lab[i].length; j++) {
                    if (lab[i][j].equals("Q")) {
                        corX = i;
                        corY = j;
                        lab[i][j] = "O";

                    }
                }
            }
            //Relleno posición actual
            lab[corX][corY] = "O";
            //comprobar abajo
            if (lab[corX + 1][corY].equals(" ") || lab[corX + 1][corY].equals("M")) {

                corX = corX + 1;

                Position p = new Position(corX, corY);
                way.push(p);

            } //comprobar derecha
            else if (lab[corX][corY + 1].equals(" ") || lab[corX][corY + 1].equals("M")) {
                corY = corY + 1;

                Position p = new Position(corX, corY);
                way.push(p);

            } //Comprobar arriba
            else if (lab[corX - 1][corY].equals(" ") || lab[corX - 1][corY].equals("M")) {
                corX = corX - 1;

                Position p = new Position(corX, corY);
                way.push(p);

            } //Comprobar izquierda
            else if (lab[corX][corY - 1].equals(" ") || lab[corX][corY - 1].equals("M")) {
                corY = corY - 1;
                Position p = new Position(corX, corY);
                way.push(p);
                
              //Volver hacia atras si no puede avanzar hacia ningun sitio
            } else {
                anterior = way.pop();

                corX = anterior.getX();
                corY = anterior.getY();

            }

        }
        System.out.println(way.toString());
        System.out.println("HAS SALIDO!");
    }
}
