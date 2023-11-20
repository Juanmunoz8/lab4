import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("1. Registrar usuario");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Seleccionar modo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int obtenerOpcion() {
        try {
            return scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine(); 
            System.out.println("Por favor, ingrese un número válido.");
            return obtenerOpcion(); 
        }
    }

    public String obtenerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.next(); 
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public char mostrarOpcionesRegistro() {
        System.out.println("a. Crear un usuario nuevo utilizando usuario y contraseña.");
        System.out.println("b. Seleccionar plan (gratis o Premium)");
        System.out.print("Seleccione una opción: ");
        return obtenerOpcionChar();
    }

    public char mostrarOpcionesSeleccion() {
        System.out.println("a. Agregar un libro.");
        System.out.println("b. Agregar revista.");
        System.out.println("c. Vaciar lista.");
        System.out.print("Seleccione una opción: ");
        return obtenerOpcionChar();
    }

    public char mostrarOpcionesPrestamo() {
        System.out.println("a. Definir días de entrega.");
        System.out.println("b. Definir horario de entrega (AM/PM). (Premium)");
        System.out.println("c. Definir sucursal para recoger el préstamo (No premium).");
        System.out.println("d. Imprimir listado de préstamo.");
        System.out.println("e. Seleccionar dirección de envío (Premium).");
        System.out.println("f. Definir si va a pasar por el préstamo a las 12 o 24 horas de haber realizado la solicitud (No premium).");
        System.out.print("Seleccione una opción: ");
        return obtenerOpcionChar();
    }

    public char mostrarOpcionesPerfil() {
        System.out.println("a. Modificar el tipo de cliente. (No premium)");
        System.out.println("b. Aplicar cupón de 15 días adicionales (Premium).");
        System.out.println("c. Cambiar contraseña.");
        System.out.print("Seleccione una opción: ");
        return obtenerOpcionChar();
    }

    private char obtenerOpcionChar() {
        try {
            return scanner.next().charAt(0);
        } catch (java.util.InputMismatchException | StringIndexOutOfBoundsException e) {
            scanner.nextLine(); 
            System.out.println("Por favor, ingrese una opción válida.");
            return obtenerOpcionChar(); 
        }
    }
}