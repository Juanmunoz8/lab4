import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    private List<Libro> librosDisponibles;

    public Modelo() {
        // Inicializa las listas u otras estructuras de datos necesarias
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.librosDisponibles = new ArrayList<>();
    }

    // Métodos para manipular usuarios
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario obtenerUsuario(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    // Métodos para manipular préstamos
    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    // Métodos para manipular libros disponibles
    public void agregarLibroDisponible(Libro libro) {
        librosDisponibles.add(libro);
    }

    public List<Libro> obtenerLibrosDisponibles() {
        return librosDisponibles;
    }

    public List<Usuario> getUsuarios() {
    
        List<Usuario> usuarios = new ArrayList<>();
    
        
        usuarios.add(new Usuario("Juan", "xd", "gratis"));
        usuarios.add(new Usuario("user2", "password2", "base"));
    
        return usuarios;
    }
    

    
}
