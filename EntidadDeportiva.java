//Clase Padre para ClubesDeportivos y ActividadesDeportivas.
// @viichorm

public abstract class EntidadDeportiva {
    protected String nombre;

    public EntidadDeportiva(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método abstracto a ser sobreescrito
    public abstract void mostrarDetalles();
}
