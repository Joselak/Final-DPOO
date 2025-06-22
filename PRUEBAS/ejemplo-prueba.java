package PRUEBAS;

import java.util.Scanner;

class Program{

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String name = sc.nextLine();
        System.out.print("Ingrese su edad: ");
        int age = sc.nextInt();
        System.out.println(name+":"+age+" años");
        sc.close();       
    }
}