package proyectoFinal;

public class NodoReporteVentas_201114430 {
	NodoReporteVentas_201114430 siguiente;
	String nombreYApellidoEmpleado;
	String fecha;
	String descripcionProducto;
	String codigoEmpleado;
	String cantidad;
	String codigoProducto;
	String precio;
	String total;
	
	public NodoReporteVentas_201114430(String codigoEmpleado,
			String nombreYApellidoEmpleado, String fecha, String codigoProducto,
			String descripcionProducto, String cantidad, String precio,
			String total) {
		this.codigoEmpleado = codigoEmpleado;
		this.nombreYApellidoEmpleado = nombreYApellidoEmpleado;
		this.fecha = fecha;
		this.codigoProducto = codigoProducto;
		this.descripcionProducto = descripcionProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.siguiente = null;
	}

}
