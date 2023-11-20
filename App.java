
public class App {
    public static void main(String[] args) {
        // Crea instancias de las clases necesarias
        Usuario usuario = new Usuario("exampleUsername", "examplePassword", "base");

        Vista vista = new Vista();
        Modelo modelo = new Modelo(); // Agrega la instancia del modelo

        // Crea el controlador y pasa las instancias
        Controlador controlador = new Controlador(vista, modelo, usuario);

        // Inicia la ejecución de la aplicación
        controlador.ejecutar();
    }
}
