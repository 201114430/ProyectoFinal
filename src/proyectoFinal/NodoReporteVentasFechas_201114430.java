package proyectoFinal;

public class NodoReporteVentasFechas_201114430 {
	String fecha;
	String producto;
	String cantidad;
	String precio;
	String total;
	NodoReporteVentasFechas_201114430 siguiente;
	public NodoReporteVentasFechas_201114430(String fecha, String producto,
			String cantidad, String precio, String total) {
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.siguiente = null;
	}
	
	
}
