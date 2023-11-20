import java.util.List;

public class Prestamo {
    private Usuario usuario;
    private List<Libro> librosPrestados;
    

    // Constructor
    public Prestamo(Usuario usuario, List<Libro> librosPrestados) {
        this.usuario = usuario;
        this.librosPrestados = librosPrestados;
        
    }

    // Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    // Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    
}
