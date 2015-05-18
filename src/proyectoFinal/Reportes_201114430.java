package proyectoFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reportes_201114430 {
	
	////////////////////////////////INSTANCIAS DE NODOS/////////////////////////////
	NodoVenta_201114430 inicioVentas;
	NodoEmpleado_201114430 inicioEmpleado;
	NodoProducto_201114430 inicioProducto;
	NodoCliente_201114430 inicioCliente;
	//////////////////////INSTANCIAS DE NODOS SOLO PARA REPORTES////////////////////	
	NodoReporteVentas_201114430 inicioRV;//inicioReporteVentas
	NodoReporteVentasPorEmpleado_201114430 inicioRVE;//inicioReporteVentasporEmpleado
	NodoReporteVentasFechas_201114430 inicioRVF;//inicioReporteVentasporRangodeFecha
	NodoReporteComprasPorCliente_201114430 inicioRCC;//inicioReporteComprasporCliente
	
	public String ruta = System.getProperties().getProperty("user.dir");
	////////////////////////////////LISTA VENTAS////////////////////////////
	public void agregarVentas(String nombreProducto,String fechaProducto,int cantidadProducto,String nombreEmpleado,String nombreClient){
		if(inicioVentas == null){
			inicioVentas = new NodoVenta_201114430(nombreProducto, fechaProducto, cantidadProducto, nombreEmpleado, nombreClient);
		}
		else{
			NodoVenta_201114430 tmp = inicioVentas;
			inicioVentas = new NodoVenta_201114430(nombreProducto, fechaProducto, cantidadProducto, nombreEmpleado, nombreClient);
			inicioVentas.siguienteVe = tmp;
		}
	}
	////////////////////////////////LISTA EMPLEADO////////////////////////////
	public void agregarEmpleado(int codigoEmpleado,String nombreEmpleado, String apellidoEmpleado,int edadEmpleado,String cargoEmpleado){
		if(inicioEmpleado==null){
			inicioEmpleado = new NodoEmpleado_201114430(codigoEmpleado,nombreEmpleado, apellidoEmpleado,edadEmpleado,cargoEmpleado);
		}
		else{
			NodoEmpleado_201114430 tmp = inicioEmpleado;
			inicioEmpleado = new NodoEmpleado_201114430(codigoEmpleado,nombreEmpleado, apellidoEmpleado,edadEmpleado,cargoEmpleado);
			inicioEmpleado.siguienteEm = tmp;
		}
	}
	public String buscarNombreEmpleado(int codigoEm){
		NodoEmpleado_201114430 tmp = inicioEmpleado;
		while(tmp != null){
			if(tmp.codigoEmpleado == codigoEm){
				return tmp.nombreEmpleado + " " + tmp.apellidoEmpleado;
			}
			else
				tmp = tmp.siguienteEm;
		}
		return null;		
	}
	////////////////////////////////LISTA PRODUCTO////////////////////////////
	public void agregarProducto(int codigoProducto,String descripcionProducto,Double precioProducto){//agrega producto a lista en memoria dinamica
		if(inicioProducto==null){
			inicioProducto = new NodoProducto_201114430(codigoProducto, descripcionProducto, precioProducto);
		}
		else{
			NodoProducto_201114430 tmp = inicioProducto;
			inicioProducto = new NodoProducto_201114430(codigoProducto, descripcionProducto, precioProducto);
			inicioProducto.siguiente = tmp;
		}
	}
	public double buscarPrecioProducto(int codigoProducto){
		NodoProducto_201114430 tmp = inicioProducto;
		while(tmp != null){
			if(tmp.codigoProducto == codigoProducto){
				return tmp.precio;
			}
			else
				tmp = tmp.siguiente;
		}
		return 0;		
	}
	public String buscarDescripcionProducto(int codigoProducto){
		NodoProducto_201114430 tmp = inicioProducto;
		while(tmp != null){
			if(tmp.codigoProducto == codigoProducto){
				return tmp.descripcionProducto;
			}
			else
				tmp = tmp.siguiente;
		}
		return null;		
	}	
	////////////////////////////////LISTA CLIENTES////////////////////////////
	public void agregarClientes(int codigoCliente,String nombreCliente, String apellidoCliente,int nitCliente){
		if(inicioCliente==null){
			inicioCliente = new NodoCliente_201114430(codigoCliente,nombreCliente, apellidoCliente,nitCliente);
		}
		else{
			NodoCliente_201114430 tmp = inicioCliente;
			inicioCliente = new NodoCliente_201114430(codigoCliente,nombreCliente, apellidoCliente,nitCliente);
			inicioCliente.siguiente = tmp;
		}
	}	
	////////////////////////////////Reporte Ventas////////////////////////////
	public void agregarListaReporteVentas(String codigoEmpleado,
			String nombreYApellidoEmpleado, String fecha, String codigoProducto,
			String descripcionProducto, String cantidad, String precio,
			String total){
		if(inicioRV == null){
			inicioRV = new NodoReporteVentas_201114430(codigoEmpleado,
					nombreYApellidoEmpleado,fecha,codigoProducto,
					descripcionProducto,cantidad,precio,
					total);
		}
		else{
			NodoReporteVentas_201114430 tmp = inicioRV;
			inicioRV = new NodoReporteVentas_201114430(codigoEmpleado,
					nombreYApellidoEmpleado,fecha,codigoProducto,
					descripcionProducto,cantidad,precio,
					total);
			inicioRV.siguiente = tmp;
		}
	}
	public void llenarListaReporteVentas(){
		NodoVenta_201114430 tmp = inicioVentas;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				//////////////////////llamada a funciones para buscar datos en base al nombre del producto//////
				String nombreEmpleado = buscarNombreEmpleado(Integer.parseInt(tmp.codigoEmpleado));
				String descripcionDelProducto = buscarDescripcionProducto(Integer.parseInt(tmp.codigoProducto));
				double precioPro = buscarPrecioProducto(Integer.parseInt(tmp.codigoProducto));
				double total = tmp.cantidadProducto * precioPro;
				agregarListaReporteVentas(tmp.codigoEmpleado, nombreEmpleado, tmp.fechaProducto,
						tmp.codigoProducto, descripcionDelProducto, Integer.toString((tmp.cantidadProducto)), 
						Double.toString(precioPro), Double.toString(total));
				tmp = tmp.siguienteVe;
			}
		}		
	}
	public void generarReporteVentas(){
		 try{
			 double granTotal = 0;
			 BufferedWriter fichVent = new BufferedWriter(new FileWriter(new File("Ventas.html"),false));
			fichVent.write("<html> \n");
			fichVent.write("<head> \n");
			fichVent.write("<title>Reporte de Facturacion</title> \n");
			fichVent.write("</head> \n");
			fichVent.write("<body> \n");
			fichVent.write("<table border=  \"3\" align = \"center\">");
			fichVent.write("<caption> <em><font color="+"Navy"+">");
			fichVent.write("<b> \"Reporte de Facturacion\" </b></font></em></caption> \n");
			fichVent.write("<tr> \n");
			fichVent.write("<th>Codigo Empleado.");
			fichVent.write("<th>Empleado");
			fichVent.write("<th>Fecha.");
			fichVent.write("<th>Codigo Producto.");
			fichVent.write("<th>Descripcion Producto.");
			fichVent.write("<th>Cantidad.");
			fichVent.write("<th>Precio.");
			fichVent.write("<th>Total. \n");
			NodoReporteVentas_201114430 tmp = inicioRV;
			if(tmp == null){
				System.out.println("Lista Vacia.");
			}
			else{
				while(tmp != null){
					fichVent.write("<tr><td>");
					fichVent.write(tmp.codigoEmpleado + "<th>");
					fichVent.write(tmp.nombreYApellidoEmpleado + "<th>");
					fichVent.write(tmp.fecha + "<th>");
					fichVent.write(tmp.codigoProducto + "<th>");
					fichVent.write(tmp.descripcionProducto + "<th>");
					fichVent.write(tmp.cantidad + "<th>");
					fichVent.write(tmp.precio + "<th>");
					fichVent.write(tmp.total + "\n");
					granTotal = Double.parseDouble(tmp.total) + granTotal;
					tmp = tmp.siguiente;
				}
			}
			fichVent.write("</table> \n");
			fichVent.write("<br> \n<br> \n<br> \n");
			fichVent.write("<table align=right WIDTH=150 HEIGHT=50 border=  \"3\" align = \"left\">");
			fichVent.write("<caption> <em><font color="+"Navy"+">");
			fichVent.write("<b> Gran Total = " + granTotal + "</b></font></em></caption> \n");
			
			fichVent.write("</body> \n");
			fichVent.write("</html> \n");
	        //fichVent.newLine();
	        fichVent.close();
	        //System.out.println("Generado exitosamente");
	        
		 }catch(IOException errorDeFichero){
			 System.out.println(
	            		"No se guardaron los datos: " +
	            errorDeFichero.getMessage());			 
		 }
	}
	public void ordenarVentas(){//ordena las ventas por codigo de empleado
		String codProt;
		String fechaProt;
		int canProt;
		String codEmpt;
		String codClient;
		NodoVenta_201114430 tempi = inicioVentas, tempj = null;
		while(tempi != null){
			tempj = tempi.siguienteVe;
			while(tempj != null){
				if(Integer.parseInt(tempi.codigoEmpleado) < Integer.parseInt(tempj.codigoEmpleado)){
					codProt = tempi.codigoProducto;
					fechaProt = tempi.fechaProducto;
					canProt = tempi.cantidadProducto;
					codEmpt = tempi.codigoEmpleado;
					codClient = tempi.codigoCliente;
					
					tempi.codigoProducto = tempj.codigoProducto;
					tempi.fechaProducto = tempj.fechaProducto;
					tempi.cantidadProducto = tempj.cantidadProducto;
					tempi.codigoEmpleado = tempj.codigoEmpleado;
					tempi.codigoCliente = tempj.codigoCliente;
					
					tempj.codigoProducto = codProt;
					tempj.fechaProducto = fechaProt;
					tempj.cantidadProducto = canProt;
					tempj.codigoEmpleado = codEmpt;
					tempj.codigoCliente = codClient;
				}
				tempj = tempj.siguienteVe;
			}
			tempi = tempi.siguienteVe;
		}
	}
	public void ordenarVenFech(){//ordena las ventas por fechas
		String codProt;
		String fechaProt;
		int canProt;
		String codEmpt;
		String codClient;
		NodoVenta_201114430 tempi = inicioVentas, tempj = null;
		while(tempi != null){
			tempj = tempi.siguienteVe;
			while(tempj != null){
				String[] fech1 = tempi.fechaProducto.split("\\.");
				String[] fech2 = tempj.fechaProducto.split("\\.");
				int diai = (Integer.parseInt(fech1[0]) + Integer.parseInt(fech1[1])*30 + Integer.parseInt(fech1[2])*360);
				int diaj = (Integer.parseInt(fech2[0]) + Integer.parseInt(fech2[1])*30 + Integer.parseInt(fech2[2])*360);
				if(diai > diaj){
					codProt = tempi.codigoProducto;
					fechaProt = tempi.fechaProducto;
					canProt = tempi.cantidadProducto;
					codEmpt = tempi.codigoEmpleado;
					codClient = tempi.codigoCliente;
					
					tempi.codigoProducto = tempj.codigoProducto;
					tempi.fechaProducto = tempj.fechaProducto;
					tempi.cantidadProducto = tempj.cantidadProducto;
					tempi.codigoEmpleado = tempj.codigoEmpleado;
					tempi.codigoCliente = tempj.codigoCliente;
					
					tempj.codigoProducto = codProt;
					tempj.fechaProducto = fechaProt;
					tempj.cantidadProducto = canProt;
					tempj.codigoEmpleado = codEmpt;
					tempj.codigoCliente = codClient;
				}
				tempj = tempj.siguienteVe;
			}
			tempi = tempi.siguienteVe;
		}
	}
	//////////////////////////Reporte Ventas por Empleado/////////////////////
	public boolean siExiste(int codigo){//metodo que verifica si empleado ya existe
		NodoReporteVentasPorEmpleado_201114430 tmp = inicioRVE;
		while(tmp != null){
			if(tmp.codigoEmpleado == codigo){
				inicioRVE.ventas = Integer.toString((Integer.parseInt(tmp.ventas) + 1));
				return true;
			}
			else
				tmp = tmp.siguiente;
		}
		return false;		
	}
	public void agregarListRepVenPorEmp(int codigoEmpleado,
			String nombreYApellidoEmpleado, String fecha, String ventas){
		if(inicioRVE == null){
			inicioRVE = new NodoReporteVentasPorEmpleado_201114430(codigoEmpleado,
					nombreYApellidoEmpleado,fecha,ventas);
		}
		else{
			NodoReporteVentasPorEmpleado_201114430 tmp = inicioRVE;
			inicioRVE = new NodoReporteVentasPorEmpleado_201114430(codigoEmpleado,
					nombreYApellidoEmpleado,fecha,ventas);
			inicioRVE.siguiente = tmp;
		}
	}
	public void llenarListaRepVenPorEmp(String fecha){
		NodoVenta_201114430 tmp = inicioVentas;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				if(fecha.equals("todos")){
					String nombreEmpleado = buscarNombreEmpleado(Integer.parseInt(tmp.codigoEmpleado));
					int codiEm = Integer.parseInt(tmp.codigoEmpleado);
					if(siExiste(codiEm)){
						//System.out.println("Venta agregada a empleado existente");
					}
					else if(!siExiste(codiEm)){
					agregarListRepVenPorEmp(Integer.parseInt(tmp.codigoEmpleado), nombreEmpleado, tmp.fechaProducto,"1");
					}					
				}
			else{
				if(tmp.fechaProducto.equals(fecha)){
					String nombreEmpleado = buscarNombreEmpleado(Integer.parseInt(tmp.codigoEmpleado));
					int codiEm = Integer.parseInt(tmp.codigoEmpleado);
					if(siExiste(codiEm)){
						//System.out.println("Venta agregada a empleado existente");
					}
					else if(!siExiste(codiEm)){
					agregarListRepVenPorEmp(Integer.parseInt(tmp.codigoEmpleado), nombreEmpleado, tmp.fechaProducto,"1");
					}
				}
			}
				tmp = tmp.siguienteVe;
			}
		}
	}
	public void ordenarVenxEmp(){
		int codEmpt;
		String nomApet;
		String fechat;
		String ventast;
		NodoReporteVentasPorEmpleado_201114430 tempi = inicioRVE, tempj = null;
		while(tempi != null){
			tempj = tempi.siguiente;
			while(tempj != null){
				if(Integer.parseInt(tempi.ventas) < Integer.parseInt(tempj.ventas)){
					codEmpt = tempi.codigoEmpleado;
					nomApet = tempi.nombreYApellidoEmpleado;
					fechat = tempi.fecha;
					ventast = tempi.ventas;
					tempi.codigoEmpleado = tempj.codigoEmpleado;
					tempi.nombreYApellidoEmpleado = tempj.nombreYApellidoEmpleado;
					tempi.fecha = tempj.fecha;
					tempi.ventas = tempj.ventas;
					
					tempj.codigoEmpleado = codEmpt;
					tempj.nombreYApellidoEmpleado = nomApet; 
					tempj.fecha = fechat;
					tempj.ventas = ventast;
				}
				tempj = tempj.siguiente;
			}
			tempi = tempi.siguiente;
		}
	}
	public void generarReporteVentasPorEmpleado(){
		 try{
			 BufferedWriter fichVent = new BufferedWriter(new FileWriter(new File("VentasxEmpleado.html"),false));
			fichVent.write("<html> \n");
			fichVent.write("<head> \n");
			fichVent.write("<title>Reporte de Ventas por Empleado.</title> \n");
			fichVent.write("</head> \n");
			fichVent.write("<body> \n");
			fichVent.write("<table border=  \"3\" align = \"center\">");
			fichVent.write("<caption> <em><font color="+"Navy"+">");
			fichVent.write("<b> \"Numero de Ventas por Empleado\" </b></font></em></caption> \n");
			fichVent.write("<tr> \n");
			fichVent.write("<th>Codigo Empleado.");
			fichVent.write("<th>Empleado");
			fichVent.write("<th>Fecha.");
			fichVent.write("<th>Ventas. \n");
			NodoReporteVentasPorEmpleado_201114430 tmp = inicioRVE;
			if(tmp == null){
				System.out.println("Lista Vacia.");
			}
			else{
				while(tmp != null){
					fichVent.write("<tr><td>");
					fichVent.write(tmp.codigoEmpleado + "<th>");
					fichVent.write(tmp.nombreYApellidoEmpleado + "<th>");
					fichVent.write(tmp.fecha + "<th>");
					fichVent.write(tmp.ventas + "\n");
					tmp = tmp.siguiente;
				}
			}			
			fichVent.write("</body> \n");
			fichVent.write("</html> \n");
	        //fichVent.newLine();
	        fichVent.close();
	        System.out.println("Generado exitosamente");
	        
		 }catch(IOException errorDeFichero){
			 System.out.println(
	            		"No se guardaron los datos: " +
	            errorDeFichero.getMessage());			 
		 }
	}
	//////////////////////Reporte Ventas en un Rango de Fechas////////////////
	public void agregarRepVenFec(String fecha, String producto,
			String cantidad, String precio, String total){
		if(inicioRVF == null){
			inicioRVF = new NodoReporteVentasFechas_201114430(fecha, producto, cantidad, precio, total);
		}
		else{
			NodoReporteVentasFechas_201114430 tmp = inicioRVF; 
			inicioRVF = new NodoReporteVentasFechas_201114430(fecha, producto, cantidad, precio, total);
			inicioRVF.siguiente = tmp; 
			
		}
		
	}
	public void llenarRepVentFec(String fechaIni,String fechaFin){
		NodoVenta_201114430 tmp = inicioVentas;
		String[] fech1 = fechaIni.split("\\.");
		int fechInit = (Integer.parseInt(fech1[0]) + Integer.parseInt(fech1[1])*30 + Integer.parseInt(fech1[2])*360);
		
		String[] fech2 = fechaFin.split("\\.");
		int fechEnd = (Integer.parseInt(fech2[0]) + Integer.parseInt(fech2[1])*30 + Integer.parseInt(fech2[2])*360);
		
		String[] fechtmp = tmp.fechaProducto.split("\\.");				
		int fecNtmp = (Integer.parseInt(fechtmp[0]) + Integer.parseInt(fechtmp[1])*30 + Integer.parseInt(fechtmp[2])*360);
		while(tmp != null){
			if(fecNtmp < fechInit){
				tmp = tmp.siguienteVe;
				fechtmp = tmp.fechaProducto.split("\\.");
				fecNtmp = (Integer.parseInt(fechtmp[0]) + Integer.parseInt(fechtmp[1])*30 + Integer.parseInt(fechtmp[2])*360);
			}
			else{
				while(fechInit <= fechEnd && tmp != null){
					double precio = buscarPrecioProducto(Integer.parseInt(tmp.codigoProducto));
					double total = (tmp.cantidadProducto * precio);
					String cant = Integer.toString(tmp.cantidadProducto);
					agregarRepVenFec(tmp.fechaProducto,buscarDescripcionProducto(Integer.parseInt(tmp.codigoProducto)),
							cant, Double.toString(precio),Double.toString(total));
					if(tmp.siguienteVe !=null ){
						tmp = tmp.siguienteVe;
					}
					else{
						break;
					}
					fechtmp = tmp.fechaProducto.split("\\.");
					fechInit = (Integer.parseInt(fechtmp[0]) + Integer.parseInt(fechtmp[1])*30 + Integer.parseInt(fechtmp[2])*360);
				}
				break;
			}
		}
	}
	public void generaRepVenRanFech(){
		 try{
			 double granTotal = 0;
			 BufferedWriter fichVent = new BufferedWriter(new FileWriter(new File("VentasRangoFechas.html"),false));
			fichVent.write("<html> \n");
			fichVent.write("<head> \n");
			fichVent.write("<title>Reporte de Fecha en un Rango de Fecha.</title> \n");
			fichVent.write("</head> \n");
			fichVent.write("<body> \n");
			fichVent.write("<table border=  \"3\" align = \"center\">");
			fichVent.write("<caption> <em><font color="+"Navy"+">");
			fichVent.write("<b> \"Reporte por Intervalo de Fecha\" </b></font></em></caption> \n");
			fichVent.write("<tr> \n");
			fichVent.write("<th>Fecha.");
			fichVent.write("<th>Producto");
			fichVent.write("<th>Cantidad.");
			fichVent.write("<th>Precio.");
			fichVent.write("<th>Total. \n");
			NodoReporteVentasFechas_201114430 tmp = inicioRVF;
			if(tmp == null){
				System.out.println("Lista Vacia.");
			}
			else{
				while(tmp != null){
					fichVent.write("<tr><td>");
					fichVent.write(tmp.fecha + "<th>");
					fichVent.write(tmp.producto + "<th>");
					fichVent.write(tmp.cantidad + "<th>");
					fichVent.write(tmp.precio + "<th>");
					fichVent.write(tmp.total + "\n");
					granTotal = Double.parseDouble(tmp.total) + granTotal;
					tmp = tmp.siguiente;
				}
			}
			
			fichVent.write("</table> \n");
			fichVent.write("<br> \n<br> \n<br> \n");
			fichVent.write("<table align=right WIDTH=150 HEIGHT=50 border=  \"3\" align = \"left\">");
			fichVent.write("<caption> <em><font color="+"Navy"+">");
			fichVent.write("<b> Gran Total = " + granTotal + "</b></font></em></caption> \n");
			
			fichVent.write("</body> \n");
			fichVent.write("</html> \n");
	        //fichVent.newLine();
	        fichVent.close();
	        System.out.println("Generado exitosamente");
	        
		 }catch(IOException errorDeFichero){
			 System.out.println(
	            		"No se guardaron los datos: " +
	            errorDeFichero.getMessage());			 
		 }
	}
	//////////////////////Reporte de Compras Por Cliente////////////////
	public void agregarRepComCli(int cod, String nomCli, int numCom){
		if(inicioRCC == null){
			inicioRCC = new NodoReporteComprasPorCliente_201114430(cod, nomCli, numCom);
		}
		else{
			NodoReporteComprasPorCliente_201114430 tmp = inicioRCC;
			inicioRCC = new NodoReporteComprasPorCliente_201114430(cod, nomCli, numCom);
			inicioRCC.siguiente = tmp;
		}			
	}
	public void llenarRepComCli(){
		NodoCliente_201114430 tmp = inicioCliente;
		if(tmp == null){
			System.out.println("Lista Vacia");
		}
		else{
			while(tmp != null){
				int numerodeCompras = buscaCompra(tmp.codigoCliente);
				agregarRepComCli(tmp.codigoCliente, tmp.nombreCliente + " " + tmp.apellidoCliente, numerodeCompras);
				tmp = tmp.siguiente;
			}
		}		
	}
	private int buscaCompra(int codigoCliente) {
		NodoVenta_201114430 tmp = inicioVentas;
		int codCli = Integer.parseInt(tmp.codigoCliente);
		int contador = 0;
		while(tmp != null){
			codCli = Integer.parseInt(tmp.codigoCliente);
			if(codCli == codigoCliente){
				contador = contador + 1;
				tmp = tmp.siguienteVe;
			}
			else{
				tmp = tmp.siguienteVe;
			}
		}
		return contador;
	}
	public void ordenaCC(){
		NodoReporteComprasPorCliente_201114430 tmpi = inicioRCC;
		NodoReporteComprasPorCliente_201114430 tmpj = null;
		int codt;
		String nomClit;
		int numComt;
		while(tmpi != null){
			tmpj = tmpi.siguiente;
			while(tmpj != null){
				if(tmpi.numdeCom < tmpj.numdeCom){
					codt = tmpi.codigoCli;
					nomClit = tmpi.nombreCli;
					numComt = tmpi.numdeCom;
					tmpi.codigoCli = tmpj.codigoCli;
					tmpi.nombreCli = tmpj.nombreCli;
					tmpi.numdeCom = tmpj.numdeCom;
					tmpj.codigoCli = codt;
					tmpj.nombreCli = nomClit;
					tmpj.numdeCom = numComt;
				}
				tmpj = tmpj.siguiente;
			}
			tmpi = tmpi.siguiente;			
		}
		
	}
	public void generaRepComPorCli(){//genera Reporte de Compras por Cliente
		 try{
			 BufferedWriter fichVent = new BufferedWriter(new FileWriter(new File("ComprasporCliente.html"),false));
			fichVent.write("<html> \n");
			fichVent.write("<head> \n");
			fichVent.write("<title>Reporte de Compras Realizadas por Cliente.</title> \n");
			fichVent.write("</head> \n");
			fichVent.write("<body> \n");
			fichVent.write("<table border=  \"3\" align = \"center\">");
			fichVent.write("<caption> <em><font color="+"Navy"+">");
			fichVent.write("<b> \"Reporte de Compras por Cliente\" </b></font></em></caption> \n");
			fichVent.write("<tr> \n");
			fichVent.write("<th>Codigo Cliente.");
			fichVent.write("<th>Nombre del Cliente");
			fichVent.write("<th>No. de Compras. \n");
			NodoReporteComprasPorCliente_201114430 tmp = inicioRCC;
			if(tmp == null){
				System.out.println("Lista Vacia.");
			}
			else{
				while(tmp != null){
					fichVent.write("<tr><td>");
					fichVent.write(tmp.codigoCli + "<th>");
					fichVent.write(tmp.nombreCli + "<th>");
					fichVent.write(tmp.numdeCom + "\n");
					tmp = tmp.siguiente;
				}
			}			
			fichVent.write("</body> \n");
			fichVent.write("</html> \n");
	        //fichVent.newLine();
	        fichVent.close();
	        System.out.println("Generado exitosamente");
	        
		 }catch(IOException errorDeFichero){
			 System.out.println(
	            		"No se guardaron los datos: " +
	            errorDeFichero.getMessage());			 
		 }
	}
	///////////////////////////METODOS PARA MOSTRAR TODAS LAS LISTA//////////////////////
	public void mostrarVentas(){
		NodoVenta_201114430 tmp = inicioVentas;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.codigoProducto + ",");
				System.out.print(tmp.fechaProducto + ",");
				System.out.print(tmp.cantidadProducto + ",");
				System.out.print(tmp.codigoEmpleado + ",");
				System.out.print(tmp.codigoCliente + ",");
				System.out.println();
				tmp = tmp.siguienteVe;				
			}
		}
	}
	public void mostrarEmpleado(){
		NodoEmpleado_201114430 tmp = inicioEmpleado;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.codigoEmpleado + ",");
				System.out.print(tmp.nombreEmpleado + ",");
				System.out.print(tmp.apellidoEmpleado + ",");
				System.out.print(tmp.edadEmpleado + ",");
				System.out.print(tmp.cargoEmpleado + ",");
				System.out.println();
				tmp = tmp.siguienteEm;				
			}
		}
	}
	public void mostrarProducto(){
		NodoProducto_201114430 tmp = inicioProducto;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.codigoProducto + ",");
				System.out.print(tmp.descripcionProducto + ",");
				System.out.print(tmp.precio + ",");
				System.out.println();
				tmp = tmp.siguiente;				
			}
		}
	}
	public void mostrarCliente(){
		NodoCliente_201114430 tmp = inicioCliente;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.codigoCliente + ",");
				System.out.print(tmp.nombreCliente + ",");
				System.out.print(tmp.apellidoCliente + ",");
				System.out.print(tmp.nitCliente + ",");
				System.out.println();
				tmp = tmp.siguiente;
			}
		}
	}
	public void mostrarListaReposteVentas(){
		NodoReporteVentas_201114430 tmp = inicioRV;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.codigoEmpleado + ",");
				System.out.print(tmp.nombreYApellidoEmpleado + ",");
				System.out.print(tmp.fecha + ",");
				System.out.print(tmp.codigoProducto + ",");
				System.out.print(tmp.descripcionProducto + ",");
				System.out.print(tmp.cantidad + ",");
				System.out.print(tmp.precio + ",");
				System.out.print(tmp.total + ",");
				System.out.println();
				tmp = tmp.siguiente;
			}
		}
	}
	public void mostrarLisRepVenFec(){
		NodoReporteVentasFechas_201114430 tmp = inicioRVF;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.fecha + ",");
				System.out.print(tmp.producto + ",");
				System.out.print(tmp.cantidad + ",");
				System.out.print(tmp.precio + ",");
				System.out.print(tmp.total + ",");
				System.out.println();
				tmp = tmp.siguiente;
			}
		}
	}
	public void mostrarLisRepComCli(){
		NodoReporteComprasPorCliente_201114430 tmp = inicioRCC;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.print(tmp.codigoCli + ",");
				System.out.print(tmp.nombreCli + ",");
				System.out.print(tmp.numdeCom + ",");
				System.out.println();
				tmp = tmp.siguiente;
			}
		}
		
	}
	///////////////////////Dos metodos para leer los ficheros y cargarlos a listas en memoria dinamica//////////////////
	public String urlS(int archivoEscogido){//escoge ruta segun actividad seleccionada
		if(archivoEscogido==1)
			return ruta+"//VENTA.fct";
			else if(archivoEscogido==2)
			return ruta+"//EMPLEADO.emp";
			else if(archivoEscogido==3)
			return ruta+"//PRODUCTO.prt";
			else if(archivoEscogido==4)
			return ruta+"//CLIENTE.clt";
			return null;		
		}
	public void cargarDatos(int archivoEscogido){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
		// Abre fichero y lo carga en bufferedreader
		archivo = new File (urlS(archivoEscogido));
		fr = new FileReader (archivo);
		br = new BufferedReader(fr);
		
		// Lectura del fichero
		String linea;
			while((linea=br.readLine())!=null){
				String[] argtext = linea.split(",");
				switch(archivoEscogido){
				case 1://carga a lista documentos VENTA.fct

					agregarVentas(argtext[0], argtext[1], Integer.parseInt(argtext[2]), argtext[3], argtext[4]);
					break;
				case 2:
					agregarEmpleado(Integer.parseInt(argtext[0]), argtext[1], argtext[2], Integer.parseInt(argtext[3]), argtext[4]);
					break;
				case 3:
					agregarProducto(Integer.parseInt(argtext[0]), argtext[1], Double.parseDouble(argtext[2]));
					break;
				case 4:
					agregarClientes(Integer.parseInt(argtext[0]), argtext[1], argtext[2], Integer.parseInt(argtext[3]));					
					break;				
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			}
		finally{
			// Se cierra el fichero, para asegurar que se cierra todo.
			try{
				if( null != fr ){
					fr.close();
					}
				}catch (Exception e2){
					e2.printStackTrace();
					}
			}
		}
	//////////////////////////////Finaliza Leer Fichero//////////////////////////
}
