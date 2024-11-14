/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ARCHIVOS;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author royum
 */
public class FileTest {

    MyFile archi;

    public static void main(String[] args) throws IOException {
        Scanner lea = new Scanner(System.in).useDelimiter("\n");
        MyFile archi = new MyFile();

        String direccion;
        int opcion = 0;

        while (opcion != 10) {

            System.out.println("\n**Menu**");
            System.out.println("1- set Archivo/Folder");
            System.out.println("2- Ver informacion");
            System.out.println("3- Crear Archivo");
            System.out.println("4- Crear un folder");
            System.out.println("5- borrar");
            System.out.println("6- Dir");
            System.out.println("7- referencia del arbol");
            System.out.println("8- Escribir en un archivo");
            System.out.println("9- Leer archivo");
            System.out.println("10- Salir");
            System.out.println("Escoja una opcion: ");
            opcion = lea.nextInt();

            if (opcion == 1) {

                System.out.println("Ingrese la direccion del archivo o folder:");
                direccion = lea.next();
                archi.setFile(direccion);
                System.out.println("archivo o folder creado correctamente");

            } else if (opcion == 2) {
                archi.info();
            } else if (opcion == 3) {
                try {
                    if (archi.CrearFile()) {
                        System.out.println("\nArchivo creado correctamente");
                    } else {
                        System.out.println("\nERROR: ARCHIVO YA EXISTENTE");
                    }

                } catch (IOException e) {
                    System.out.println("\nerror al crear archivo" + e.getMessage());
                }
            } else if (opcion == 4) {

                if (archi.crearFolder()) {
                    System.out.println("\nFolder creado exitosamente");
                } else {
                    System.out.println("\nel folder ya existe crick");
                }

            } else if (opcion == 5) {

                archi.borrar();

            } else if (opcion == 6) {
                try {
                    archi.dir();
                } catch (Exception e) {
                    System.out.println("Error mi file no seleccionado es  nulo");
                }

            } else if (opcion == 7) {

                archi.tree();

            } else if (opcion == 8) {

                System.out.println("1- Sobreecribir el archivo");
                System.out.println("2- Alladir al archivo");
                int subopcion = lea.nextInt();
                System.out.println("Ingrese el texto a escribir ");
                String texto = lea.next();

                try {
                    if (subopcion == 1) {
                        archi.EscribirArchivoConFileWriter(texto);
                    } else if (subopcion == 2) {
                        archi.escribirArchivoAlladirConFileWriter(texto);
                    }
                } catch (IOException e) {
                    System.out.println("\nerror al sobreescribir el archivo" + e.getMessage());
                }

            } else if (opcion == 9) {
                System.out.println("1- leer archivo (con fileReader)");
                System.out.println("2- leer archivo (con bufferedReader)");
                int subopcion2 = lea.nextInt();

                try {
                    if (subopcion2 == 1) {
                        archi.leerArchivoconfilereader();
                    } else if (subopcion2 == 2) {
                        archi.leerArchivoconbufferReader();
                    }

                } catch (IOException e) {
                    System.out.println("\nError al leer el archivo" + e.getMessage());
                }

            } else if (opcion == 10) {
                System.out.println("Saliendo del programa");
            } else {
                System.out.println("\nerror");
            }

        }

    }

}
