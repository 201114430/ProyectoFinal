package proyectoFinal;

public class NodoCliente_201114430 {
	
	int codigoCliente;
	String nombreCliente;
	String apellidoCliente;
	int nitCliente;	
	NodoCliente_201114430 siguiente;

	public NodoCliente_201114430(int codigoCliente, String nombreCliente, String apellido, int nitCliente) {
		this.codigoCliente = codigoCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellido;
		this.nitCliente = nitCliente;
		this.siguiente = null;
	}
}
