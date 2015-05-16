package proyectoFinal;

public class NodoEmpleado_201114430 {
	
	public NodoEmpleado_201114430 siguienteEm;
	public String nombreEmpleado,apellidoEmpleado,cargoEmpleado;
	public int  codigoEmpleado,edadEmpleado;
	
	public NodoEmpleado_201114430(int codigoEmpleado,String nombreEmpleado, String apellidoEmpleado,int edadEmpleado,String cargoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoEmpleado = apellidoEmpleado;
		this.edadEmpleado = edadEmpleado;
		this.cargoEmpleado = cargoEmpleado;
		this.siguienteEm = null;		
	}
}
