/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ARCHIVOS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author royum
 */
public class MyFile {

    private File mifile = null;
   

    public void setFile(String direccion) {

        mifile = new File(direccion);

    }

    //aqui ver la informacion
    public void info() {
        if (mifile.exists()) {

            System.out.println("\nNombre: " + mifile.getName());
            System.out.println("Path: " + mifile.getPath());
            System.out.println("Absoluta: " + mifile.getAbsolutePath());
            System.out.println("Cantidad de Bytes: " + mifile.length());
            System.out.println("Modificado en: " + new Date(mifile.lastModified()));
            System.out.println("Padre: " + mifile.getAbsoluteFile().getParentFile().getName());//identificar cual es el padre de esa direccion
            if (mifile.isFile()) {
                System.out.println("ES UN ARCHIVO");
            } else if (mifile.isDirectory()) {
                System.out.println("ES UN DIRECTORIO");
            }
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        } else {
            System.out.println("NO EXISTE!");
        }
    }

    public boolean CrearFile() throws IOException {

        return mifile.createNewFile();

    }

    public boolean crearFolder() {

        return mifile.mkdirs();

    }

    public void borrar() {

        if (antidoto(mifile)) {
            System.out.println("Borrado");
        } else {
            System.out.println("No se pudo borrar ");
        }

    }

    private boolean antidoto(File mf) {

        if (mf.isDirectory()) {
            for (File child : mf.listFiles()) {
                antidoto(child);
            }

        }
        return mf.delete();

    }

    //mostrar los bytes, si es dir, pdf docx
    public void dir() {

        if (mifile.isDirectory()) {
            System.out.println("Directorio de: " + mifile.getAbsolutePath());
            System.out.println("");
            //contadores
            int cfiles = 0, cdirs = 0, tbytes = 0;

            for (File child : mifile.listFiles()) {
                if (!child.isHidden()) {
                    //utilizar fecha
                    Date ultima = new Date(child.lastModified());
                    System.out.print(ultima + "\t");
                    //si es file o dir 
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.print("<DIR>\t\t");
                    } else {
                        // si no, es archivo
                        cfiles++;
                        tbytes += child.length();//este es el totalizado, se esta acumulando, imprimiendo por 1
                        System.out.print("    \t" + child.length() + "\t");//cuanto es el espacio de cada archivo
                    }
                    //mostrar el nombre, objetos
                    System.out.println(child.getName());

                }
            }
            System.out.println(cfiles + " archivos\t" + tbytes + " bytes");
            System.out.println(cdirs + "dirs\t");
        }

    }

    //obtener estrucutura
    private void tree(File dir, String tab) {

        if (dir.isDirectory()) {

            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab + "--");//incremento
                }
            }

        }

    }
    //sobrecarga

    void tree() {
        tree(mifile, "-");
    }
    
    public void EscribirArchivoConFileWriter(String texto) throws IOException{
        
        try(FileWriter escribirconfile=new FileWriter(mifile)){
            escribirconfile.write(texto+ "\n");
            System.out.println("Texto sobreescrito correctamente ");
        }
        
    }
    
       public void escribirArchivoAlladirConFileWriter(String texto) throws IOException {
        StringBuilder contenidoExistente = new StringBuilder();

        if (mifile.exists()) {
            try (BufferedReader leerconbuffer = new BufferedReader(new FileReader(mifile))) {
                String linea;
                while ((linea = leerconbuffer.readLine()) != null) {
                    contenidoExistente.append(linea).append("\n");
                }
            }
        }

        try (FileWriter escribirconfile = new FileWriter(mifile)) {
            escribirconfile.write(contenidoExistente.toString());
            escribirconfile.write(texto + "\n");
            System.out.println("Texto añadido correctamente.");
        }

    }

    public void leerArchivoconfilereader() throws IOException{
        
        try(FileReader leerconfile=new FileReader(mifile)){
            int guardar;
            while((guardar =leerconfile.read()) != -1){
                System.out.print((char) guardar);
            }
            System.out.println();
            
        }
        
    }
    
    public void leerArchivoconbufferReader() throws FileNotFoundException, IOException{
        
        try(BufferedReader leerconbuffer=new BufferedReader(new FileReader (mifile))){
            
            String linea;
            while((linea=leerconbuffer.readLine())!=null){
                System.out.println(linea);
            }
            
        }
        
    }

}
