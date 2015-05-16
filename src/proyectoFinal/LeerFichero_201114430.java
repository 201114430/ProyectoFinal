package proyectoFinal;

import java.io.*;


public class LeerFichero_201114430{
	/*public String nombreJugador,disc,tiempo;
	public int numeroFichero = 2;
	public final String ruta = System.getProperties().getProperty("user.dir");
	public LeerFichero_201114430(){
		cargarFichero();
		
	}
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
	
	public void cargarFichero(){
		String parametro = null;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
		         // Abre fichero y lo carga en bufferedreader
		         archivo = new File (urlS(numeroFichero));
		         fr = new FileReader (archivo);
		         br = new BufferedReader(fr);
		 
		         // Lectura del fichero
		         String linea;
		         while((linea=br.readLine())!=null){
		        	
		        //////////////////////////tree map//////////
		        	 System.out.println("linea del documento "+linea);
		        	 
		        	 parametro = linea;//linea toma lo que lee y lo graba en parametro
		         String[] argtext = parametro.split(",");
					//System.out.println(linea);
			        //System.out.println("Nombre de fichero  data"+ numeroFichero+"txt");
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
	public static void main(String[] args){
		LeerFichero_201114430 leefic = new LeerFichero_201114430();
	}
	*/
}
