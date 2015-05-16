package proyectoFinal;

public class NodoProducto_201114430 {
	int codigoProducto;
	String descripcionProducto;
	double precio;
	NodoProducto_201114430 siguiente;

	public NodoProducto_201114430(int codigoProducto,String descripcionProducto, double precio){
		this.codigoProducto = codigoProducto;
		this.descripcionProducto = descripcionProducto;
		this.precio = precio;
		this.siguiente = null;
	}	
}
