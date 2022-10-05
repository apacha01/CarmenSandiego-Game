package carmen_sandiegopacheco2;

/**
 *
 * @author Alumno
 */
public class Controlador {

    public static void main(String[] args) {
        Modelo m = new Modelo();
        Vista v = new Vista(m);
        v.mostrar();
    }
    
}
