public class Libro {
    private String titulo;
    private int cantidadDisponible;

    // Constructor
    public Libro(String titulo, int cantidadDisponible) {
        this.titulo = titulo;
        this.cantidadDisponible = cantidadDisponible;
        
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        if (cantidadDisponible >= 0) {
            this.cantidadDisponible = cantidadDisponible;
        } else {
            System.out.println("La cantidad disponible no puede ser negativa.");
        }
    }

   
}

