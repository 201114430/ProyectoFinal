package proyectoFinal;

public class NodoVenta_201114430 {
	
	NodoVenta_201114430 siguienteVe;
	String codigoProducto;
	String codigoEmpleado;
	String codigoCliente;
	String fechaProducto;
	int cantidadProducto;
	
	public NodoVenta_201114430(String nombreProducto,String fechaProducto,int cantidadProducto,String nombreEmpleado,String nombreClient){
		this.codigoProducto = nombreProducto;
		this.fechaProducto = fechaProducto;
		this.cantidadProducto = cantidadProducto;
		this.codigoEmpleado = nombreEmpleado;
		this.codigoCliente = nombreClient;
		this.siguienteVe = null;
	}
}
