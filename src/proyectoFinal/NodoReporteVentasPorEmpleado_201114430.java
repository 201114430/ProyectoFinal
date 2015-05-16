package proyectoFinal;

public class NodoReporteVentasPorEmpleado_201114430 {
	NodoReporteVentasPorEmpleado_201114430 siguiente;
	String nombreYApellidoEmpleado;
	String fecha;
	String ventas;
	int codigoEmpleado;
	public NodoReporteVentasPorEmpleado_201114430(int codigoEmpleado,
			String nombreYApellidoEmpleado, String fecha, String ventas) {
		this.codigoEmpleado = codigoEmpleado;
		this.nombreYApellidoEmpleado = nombreYApellidoEmpleado;
		this.fecha = fecha;
		this.ventas = ventas;
		this.siguiente = null;
	}
}