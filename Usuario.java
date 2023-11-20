import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String plan;
    private List<Libro> librosPrestados;
    private List<Revista> revistasPrestadas;
    
    public Usuario(String username, String password, String plan) {
        this.username = username;
        this.password = password;
        this.plan = plan;
        
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPlan() {
        return plan;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public List<Revista> getRevistasPrestadas() {
        return revistasPrestadas;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setContrasena(String password) {
        this.password = password;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setDireccionEnvio(String direccionEnvio) {
    }

    public void setDiasEntrega(int diasEntrega) {
    }

    public int getNumLibrosPrestados() {
        return 0;
    }

    public void vaciarListaSeleccion() {
    }

    
}
