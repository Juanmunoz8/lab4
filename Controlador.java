import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Vista vista;
    private Modelo modelo;
    private Usuario usuarioActual;

    public Controlador(Vista vista, Modelo modelo, Usuario usuario) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void ejecutar() {
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenuPrincipal();
            int opcion = vista.obtenerOpcion();

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                        seleccionarModo();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private void registrarUsuario() {
        String username = vista.obtenerTexto("Ingresa un nombre de usuario: ");
        String password = vista.obtenerTexto("Ingresa una contraseña: ");
        String plan = vista.obtenerTexto("Selecciona un plan (gratis/premium): ");

        Usuario nuevoUsuario = new Usuario(username, password, plan);
        modelo.agregarUsuario(nuevoUsuario);

        vista.mostrarMensaje("Usuario registrado exitosamente.");
    }

    private void iniciarSesion() {
        if (modelo.getUsuarios().isEmpty()) {
            vista.mostrarMensaje("No hay usuarios registrados. Registra un usuario primero.");
            return;
        }
    
        String username = vista.obtenerTexto("Ingrese su nombre de usuario: ");
        String password = vista.obtenerTexto("Ingrese su contraseña: ");
    
        Usuario usuarioEncontrado = buscarUsuario(username, password);
    
        if (usuarioEncontrado != null) {
            usuarioActual = usuarioEncontrado;
            vista.mostrarMensaje("Inicio de sesión exitoso. Bienvenido, " + usuarioActual.getUsername() + "!");
        } else {
            vista.mostrarMensaje("Nombre de usuario o contraseña incorrectos. Inténtelo de nuevo.");
        }
    }
    
    private Usuario buscarUsuario(String username, String password) {
        for (Usuario usuario : modelo.getUsuarios()) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }
    
    
    private void seleccionarModo() {
        vista.mostrarMensaje("Selecciona un modo:\n1. Modo selección\n2. Modo prestamo\n3. Modo perfil");
        int modo = vista.obtenerOpcion();
    
        switch (modo) {
            case 1:
                modoSeleccion();
                break;
            case 2:
                modoPrestamo();
                break;
            case 3:
                modoPerfil();
                break;
            default:
                vista.mostrarMensaje("Modo no válido.");
                break;
        }
    }
    

    private void modoSeleccion() {
        boolean salirModoSeleccion = false;
    
        while (!salirModoSeleccion) {
            vista.mostrarMensaje("Modo Selección");
            vista.mostrarMensaje("1. Agregar libro");
            vista.mostrarMensaje("2. Agregar revista");
            vista.mostrarMensaje("3. Vaciar lista");
            vista.mostrarMensaje("4. Volver al menú principal");
            int opcionSeleccion = vista.obtenerOpcion();
    
            switch (opcionSeleccion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    agregarRevista();
                    break;
                case 3:
                    vaciarLista();
                    break;
                case 4:
                    salirModoSeleccion = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
    
    private void agregarLibro() {
 
            if (usuarioActual.getPlan().equals("premium") && usuarioActual.getNumLibrosPrestados() >= 5) {
                vista.mostrarMensaje("Ya has alcanzado el límite de libros prestados.");
            } else if (usuarioActual.getPlan().equals("gratis") && usuarioActual.getNumLibrosPrestados() >= 3) {
                vista.mostrarMensaje("Ya has alcanzado el límite de libros prestados.");
            } else {
                // Obtiene la lista de libros disponibles
                List<Libro> librosDisponibles = obtenerLibrosDisponibles();
            
                if (!librosDisponibles.isEmpty()) {
                    // Mostrar los libros disponibles
                    vista.mostrarMensaje("Libros disponibles:");
                    for (int i = 0; i < librosDisponibles.size(); i++) {
                        vista.mostrarMensaje((i + 1) + ". " + librosDisponibles.get(i).getTitulo());
                    }
            
                    // Solicita al usuario que elija un libro
                    vista.mostrarMensaje("Seleccione el número del libro que desea agregar:");
                    int opcionLibro = vista.obtenerOpcion();
            
                    // Verifica si la opción es válida
                    if (opcionLibro >= 1 && opcionLibro <= librosDisponibles.size()) {
                        Libro libroSeleccionado = librosDisponibles.get(opcionLibro - 1);
            
                        // Agrega el libro seleccionado a la lista de libros prestados del usuario
                        usuarioActual.getLibrosPrestados().add(libroSeleccionado);
            
                        vista.mostrarMensaje("Libro '" + libroSeleccionado.getTitulo() + "' agregado exitosamente.");
                    } else {
                        vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
                    }
                } else {
                    vista.mostrarMensaje("No hay libros disponibles en este momento.");
                }
            }
            
         {
            vista.mostrarMensaje("Debes iniciar sesión primero.");
        }
    }
    
    private List<Libro> obtenerLibrosDisponibles() {
        return null;
    }

    private void agregarRevista() {
    if (usuarioActual != null) {

        // Supongamos que tienes una lista de revistas disponibles
        List<Revista> revistasDisponibles = obtenerRevistasDisponibles();

        if (!revistasDisponibles.isEmpty()) {
            // Mostrar las revistas disponibles
            vista.mostrarMensaje("Revistas disponibles:");
            for (int i = 0; i < revistasDisponibles.size(); i++) {
                vista.mostrarMensaje((i + 1) + ". " + revistasDisponibles.get(i).getTitulo());
            }

            // Solicita al usuario que elija una revista
            vista.mostrarMensaje("Seleccione el número de la revista que desea agregar:");
            int opcionRevista = vista.obtenerOpcion();

            // Verifica si la opción es válida
            if (opcionRevista >= 1 && opcionRevista <= revistasDisponibles.size()) {
                Revista revistaSeleccionada = revistasDisponibles.get(opcionRevista - 1);

                // Agrega la revista seleccionada a la lista de revistas prestadas del usuario
                usuarioActual.getRevistasPrestadas().add(revistaSeleccionada);

                vista.mostrarMensaje("Revista '" + revistaSeleccionada.getTitulo() + "' agregada exitosamente.");
            } else {
                vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } else {
            vista.mostrarMensaje("No hay revistas disponibles en este momento.");
        }
    } else {
        vista.mostrarMensaje("Debes iniciar sesión primero.");
    }
}


private List<Revista> obtenerRevistasDisponibles() {

    return new ArrayList<>();
}

    
    private void vaciarLista() {
        if (usuarioActual != null) {
            // Pregunta al usuario si realmente desea vaciar la lista
            vista.mostrarMensaje("¿Estás seguro de que deseas vaciar la lista de selección? (S/N)");
            String respuesta = vista.obtenerTexto("Respuesta: ");
    
            if (respuesta.equalsIgnoreCase("S")) {
                usuarioActual.vaciarListaSeleccion();
                vista.mostrarMensaje("La lista de selección ha sido vaciada.");
            } else {
                vista.mostrarMensaje("Operación cancelada. La lista de selección no ha sido modificada.");
            }
        } else {
            vista.mostrarMensaje("Debes iniciar sesión primero.");
        }
    }
    
    

    private void modoPrestamo() {
        boolean salirModoPrestamo = false;
    
        while (!salirModoPrestamo) {
            vista.mostrarMensaje("Modo Préstamo");
            vista.mostrarMensaje("1. Definir días de entrega");
            vista.mostrarMensaje("2. Definir horario de entrega (solo para usuarios premium)");
            vista.mostrarMensaje("3. Definir sucursal para recoger el préstamo (solo para usuarios no premium)");
            vista.mostrarMensaje("4. Imprimir listado de préstamo");
            vista.mostrarMensaje("5. Seleccionar dirección de envío (solo para usuarios premium)");
            vista.mostrarMensaje("6. Definir si va a pasar por el préstamo a las 12 o 24 horas de haber realizado la solicitud (solo para usuarios no premium)");
            vista.mostrarMensaje("7. Volver al menú principal");
            
            int opcionPrestamo = vista.obtenerOpcion();
    
            switch (opcionPrestamo) {
                case 1:
                    definirDiasEntrega();
                    break;
                case 2:
                    if (usuarioActual != null && usuarioActual.getPlan().equals("premium")) {
                        definirHorarioEntrega();
                    } else {
                        vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
                    }
                    break;
                case 3:
                    if (usuarioActual != null && usuarioActual.getPlan().equals("base")) {
                        definirSucursalRecoger();
                    } else {
                        vista.mostrarMensaje("Esta opción es solo para usuarios no premium.");
                    }
                    break;
                case 4:
                    imprimirListadoPrestamo();
                    break;
                case 5:
                    if (usuarioActual != null && usuarioActual.getPlan().equals("premium")) {
                        seleccionarDireccionEnvio();
                    } else {
                        vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
                    }
                    break;
                case 6:
                    if (usuarioActual != null && usuarioActual.getPlan().equals("base")) {
                        definirHorarioPasar();
                    } else {
                        vista.mostrarMensaje("Esta opción es solo para usuarios no premium.");
                    }
                    break;
                case 7:
                    salirModoPrestamo = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
    
    private void definirDiasEntrega() {
        if (usuarioActual != null) {
            if (usuarioActual.getPlan().equals("premium")) {
                vista.mostrarMensaje("Ingrese los días de entrega (máximo 50 días):");
                int diasEntrega = vista.obtenerOpcion();
    
                // Verifica que los días ingresados estén dentro del límite
                if (diasEntrega > 0 && diasEntrega <= 50) {
                    // Actualiza la información del préstamo con los días de entrega seleccionados
                    usuarioActual.setDiasEntrega(diasEntrega);
                    vista.mostrarMensaje("Días de entrega definidos exitosamente: " + diasEntrega + " días.");
                } else {
                    vista.mostrarMensaje("Por favor, ingrese un número válido de días dentro del límite (1-50).");
                }
            } else {
                vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
            }
        } else {
            vista.mostrarMensaje("Debes iniciar sesión primero.");
        }
    }
    
    
    private void definirHorarioEntrega() {
        if (usuarioActual != null && usuarioActual.getPlan().equals("premium")) {
           
            vista.mostrarMensaje("Seleccione el horario de entrega:");
            vista.mostrarMensaje("1. AM");
            vista.mostrarMensaje("2. PM");
    
            int opcionHorario = vista.obtenerOpcion();
    
            switch (opcionHorario) {
                case 1:
                    // Lógica para el horario de AM
                    vista.mostrarMensaje("Has seleccionado el horario de entrega AM. Información actualizada.");
                    break;
                case 2:
                    // Lógica para el horario de PM
                    vista.mostrarMensaje("Has seleccionado el horario de entrega PM. Información actualizada.");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } else {
            vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
        }
    }
    
    
    private void definirSucursalRecoger() {
        if (usuarioActual != null && usuarioActual.getPlan().equals("base")) {
            
            vista.mostrarMensaje("Seleccione la sucursal para recoger el préstamo:");
            vista.mostrarMensaje("1. Sucursal A");
            vista.mostrarMensaje("2. Sucursal B");
            
            int opcionSucursal = vista.obtenerOpcion();
    
            switch (opcionSucursal) {
                case 1:
                    // Lógica para la Sucursal A
                    vista.mostrarMensaje("Has seleccionado la Sucursal A. Información actualizada.");
                    break;
                case 2:
                    // Lógica para la Sucursal B
                    vista.mostrarMensaje("Has seleccionado la Sucursal B. Información actualizada.");
                    break;
    
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } else {
            vista.mostrarMensaje("Esta opción es solo para usuarios no premium.");
        }
    }
    
    
    private void imprimirListadoPrestamo() {
        if (usuarioActual != null) {
            if (usuarioActual.getLibrosPrestados().isEmpty() && usuarioActual.getRevistasPrestadas().isEmpty()) {
                vista.mostrarMensaje("No tienes préstamos registrados en este momento.");
            } else {
                vista.mostrarMensaje("Listado de préstamo:");
    
                // Muestra la información de los libros prestados
                for (Libro libro : usuarioActual.getLibrosPrestados()) {
                    vista.mostrarMensaje("Libro: " + libro.getTitulo());
                }
    
                // Mostrar información de revistas prestadas
                for (Revista revista : usuarioActual.getRevistasPrestadas()) {
                    vista.mostrarMensaje("Revista: " + revista.getTitulo());
                }
    
                
            }
    
            vista.mostrarMensaje("Listado de préstamo impreso exitosamente.");
        } else {
            vista.mostrarMensaje("Debes iniciar sesión primero.");
        }
    }
    
    
    
    
    private void seleccionarDireccionEnvio() {
        if (usuarioActual != null && usuarioActual.getPlan().equals("premium")) {
            vista.mostrarMensaje("Ingrese la dirección de envío: ");
            String direccionEnvio = vista.obtenerTexto("");

            usuarioActual.setDireccionEnvio(direccionEnvio);
    
            vista.mostrarMensaje("Dirección de envío seleccionada exitosamente: " + direccionEnvio);
        } else {
            vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
        }
    }
    
    
    private void definirHorarioPasar() {
        if (usuarioActual != null && usuarioActual.getPlan().equals("base")) {
            vista.mostrarMensaje("Seleccione el horario para pasar por el préstamo:");
            vista.mostrarMensaje("1. A las 12 horas");
            vista.mostrarMensaje("2. A las 24 horas");
            
            int opcionHorario = vista.obtenerOpcion();
    
            switch (opcionHorario) {
                case 1:
                    // Lógica para pasar a las 12 horas
                    vista.mostrarMensaje("Ha seleccionado pasar por el préstamo a las 12 horas. Información actualizada.");
                    break;
                case 2:
                    // Lógica para pasar a las 24 horas
                    vista.mostrarMensaje("Ha seleccionado pasar por el préstamo a las 24 horas. Información actualizada.");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } else {
            vista.mostrarMensaje("Esta opción es solo para usuarios no premium.");
        }
    }
    
    
    

    private void modoPerfil() {
        boolean salirModoPerfil = false;
    
        while (!salirModoPerfil) {
            vista.mostrarMensaje("Modo Perfil");
            vista.mostrarMensaje("1. Modificar tipo de cliente");
            vista.mostrarMensaje("2. Aplicar cupón de 15 días adicionales");
            vista.mostrarMensaje("3. Cambiar contraseña");
            vista.mostrarMensaje("4. Volver al menú principal");
    
            int opcionPerfil = vista.obtenerOpcion();
    
            switch (opcionPerfil) {
                case 1:
                    modificarTipoCliente();
                    break;
                case 2:
                    if (usuarioActual != null && usuarioActual.getPlan().equals("premium")) {
                        aplicarCupon();
                    } else {
                        vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
                    }
                    break;
                case 3:
                    cambiarContrasena();
                    break;
                case 4:
                    salirModoPerfil = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
    
    private void modificarTipoCliente() {
        if (usuarioActual != null && usuarioActual.getPlan().equals("base")) {
            vista.mostrarMensaje("Cambiar a tipo de cliente premium (Sí/No): ");
            String respuesta = vista.obtenerTexto("").toLowerCase();
    
            if (respuesta.equals("si")) {
                usuarioActual.setPlan("premium");
                vista.mostrarMensaje("Tipo de cliente cambiado a premium.");
            } else if (respuesta.equals("no")) {
                vista.mostrarMensaje("No se ha cambiado el tipo de cliente.");
            } else {
                vista.mostrarMensaje("Respuesta no válida. Inténtalo de nuevo.");
            }
        } else {
            vista.mostrarMensaje("Esta opción es solo para usuarios no premium.");
        }
    }
    
    private void aplicarCupon() {
        if (usuarioActual != null && usuarioActual.getPlan().equals("premium")) {
            vista.mostrarMensaje("Cupón aplicado. Se han añadido 15 días adicionales.");
        } else {
            vista.mostrarMensaje("Esta opción es solo para usuarios premium.");
        }
    }
    
    
    private void cambiarContrasena() {
        vista.mostrarMensaje("Ingresa la nueva contraseña: ");
        String nuevaContrasena = vista.obtenerTexto("");
        usuarioActual.setContrasena(nuevaContrasena);
        vista.mostrarMensaje("Contraseña cambiada exitosamente.");
    }
    
}


