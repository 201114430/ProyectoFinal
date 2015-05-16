package proyectoFinal;

public class NodoReporteComprasPorCliente_201114430 {
	int codigoCli;
	String nombreCli;
	int numdeCom;//numero de Compras por cliente
	
	NodoReporteComprasPorCliente_201114430 siguiente;

	public NodoReporteComprasPorCliente_201114430(int codigoCli,
			String nombreCli, int numdeCom){
		this.codigoCli = codigoCli;
		this.nombreCli = nombreCli;
		this.numdeCom = numdeCom;
		this.siguiente = null;
	}
}
