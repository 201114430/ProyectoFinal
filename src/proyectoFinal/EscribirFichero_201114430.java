package proyectoFinal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFichero_201114430 {
	
	public final String ruta = System.getProperties().getProperty("user.dir");
	
	public EscribirFichero_201114430(){}
	
	public void borrarContenidoFichero(int archivoEscogido){
		System.out.println("Grabando fichero de texto a disco...");
		try{
			BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(urlS(archivoEscogido)),false));
			ficheroSalida.write("");
            ficheroSalida.close();
        }
        catch (IOException errorDeFichero){
            System.out.println(
            		"Datos de fichero borrados: " +
            errorDeFichero.getMessage() );
        }		
	}
	
	public String urlS(int archivoEscogido){//escoge fichero que se escribira segun actividad seleccionada
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

	public void escribeDatoVenta(String codigoProducto,String fechaProducto,String cantidadProducto,String codigoEmpleado,String codigoCliente){
		System.out.println("Grabando fichero de texto a disco...");
		try{
			BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(urlS(1)),true));
			ficheroSalida.write(codigoProducto + "," + fechaProducto + "," + cantidadProducto + "," + codigoEmpleado + "," + codigoCliente);
            ficheroSalida.newLine();
            ficheroSalida.close();
        }
        catch (IOException errorDeFichero){
            System.out.println(
            		"No se guardaron los datos: " +
            errorDeFichero.getMessage() );
        }
	}
	
	public void escribeDatoEmpleado(String codigoEmpleado,String nombreEmpleado, String apellidoEmpleado,String edadEmpleado,String cargoEmpleado){
		System.out.println("Grabando fichero de texto a disco...");
		try{
			BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(urlS(2)),true));
			ficheroSalida.write(codigoEmpleado + "," + nombreEmpleado + "," + apellidoEmpleado + "," + edadEmpleado + "," + cargoEmpleado);
            ficheroSalida.newLine();
            ficheroSalida.close();
        }
        catch (IOException errorDeFichero){
            System.out.println(
            		"No se guardaron los datos: " +
            errorDeFichero.getMessage() );
        }
	}
	
	public void escribeDatoProducto(String codigoProducto,String descripcionProducto, String precio){
		System.out.println("Grabando fichero de texto a disco...");
		try{
			BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(urlS(3)),true));
			ficheroSalida.write(codigoProducto + "," + descripcionProducto + "," + precio);
            ficheroSalida.newLine();
            ficheroSalida.close();
        }
        catch (IOException errorDeFichero){
            System.out.println(
            		"No se guardaron los datos: " +
            errorDeFichero.getMessage() );
        }
		
	}
	
	public void escribeDatoCliente(String codigoCliente,String nombreCliente, String apellidoCliente,String nitCliente){
		System.out.println("Grabando fichero de texto a disco...");
		try
		{
			BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(urlS(4)),true));
			ficheroSalida.write(codigoCliente + "," + nombreCliente + "," + apellidoCliente + "," + nitCliente);
            ficheroSalida.newLine();
            ficheroSalida.close();
        }
        catch (IOException errorDeFichero){
            System.out.println(
            		"No se guardaron los datos: " +
            errorDeFichero.getMessage());
        }
		
	}
}