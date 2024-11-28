//Autor:    Alejandro Serna Collazos
//Abstract: Informacion de el/los clientes

package tareaa;

public class Cliente {
    private String nombre;
    private boolean esExento4x1000; // Exento del 4x1000

    public Cliente(String nombre, boolean esExento4x1000) {
        this.nombre = nombre;
        this.esExento4x1000 = esExento4x1000;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esExento4x1000() {
        return esExento4x1000;
    }
}