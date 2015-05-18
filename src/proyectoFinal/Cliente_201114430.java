package proyectoFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cliente_201114430 extends JFrame implements MouseListener{
	
	NodoCliente_201114430 inicio;
	Icono_201114430 guardar = new Icono_201114430(350, 50,5);
	Icono_201114430 buscar = new Icono_201114430(350, 180,6);
	Icono_201114430 eliminar = new Icono_201114430(350, 310,7);
	public javax.swing.JButton imagenEm,buscarIma;
	public JTextField codigoCli,nombreCli,apellidoCli,nitCli;
	public JLabel barraEstado;
	public final String ruta = System.getProperties().getProperty("user.dir");
	
	public void mostrarCli(){
		NodoCliente_201114430 tmp = inicio;
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
	public void agregarCli(int codigoCliente,String nombreCliente, String apellidoCliente,int nitCliente){
		if(inicio==null){
			inicio = new NodoCliente_201114430(codigoCliente,nombreCliente, apellidoCliente,nitCliente);
		}
		else{
			NodoCliente_201114430 tmp = inicio;
			inicio = new NodoCliente_201114430(codigoCliente,nombreCliente, apellidoCliente,nitCliente);
			inicio.siguiente = tmp;
		}
	}	
	public void eliminarCli(int codigoCl){
		NodoCliente_201114430 tmp = inicio;
		NodoCliente_201114430 anterior = null;
		if(tmp == null){
			barraEstado.setText("Base de datos empleado vacia.");
			}
		else{
			while(tmp != null){
				if(tmp.codigoCliente == codigoCl){
					if(anterior == null){
						inicio = inicio.siguiente;
					}
					else{
						anterior.siguiente = tmp.siguiente;
					}
					break;
				}
				anterior = tmp;
				tmp = tmp.siguiente;
			}			
		}		
	}
	public void buscarEm(int codigoCl){
		NodoCliente_201114430 tmp = inicio;
		boolean siEsta = false;
		while(tmp != null){
			if(tmp.codigoCliente ==codigoCl){
				codigoCli.setText(Integer.toString(codigoCl));
				nombreCli.setText(tmp.nombreCliente);
				apellidoCli.setText(tmp.apellidoCliente);
				nitCli.setText((Integer.toString(tmp.nitCliente)));
				barraEstado.setText("Busqueda exitosa.");
				siEsta = true;
				break;
			}
			else
				tmp = tmp.siguiente;
		}
		if(!siEsta)
			barraEstado.setText("Cliente no existe.");		
	}
	public void grabarListaEnFichero(){
		EscribirFichero_201114430 es = new EscribirFichero_201114430();
		
		NodoCliente_201114430 tmp = inicio;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				es.escribeDatoCliente(
						Integer.toString(tmp.codigoCliente), 
						tmp.nombreCliente, 
						tmp.apellidoCliente, 
						Integer.toString(tmp.nitCliente)); 
				tmp = tmp.siguiente;
			}
		}
	}
	private void borrarLista() {
		if(inicio == null){
			barraEstado.setText("Realizando busqueda...");
		}
		else{
			while(inicio != null){
				inicio = null;
			}
		}
	}
	public void vaciaCajaTexto(){
		codigoCli.setText(null);
		nombreCli.setText(null);
		apellidoCli.setText(null);
		nitCli.setText(null);			
	}
 	public Cliente_201114430() {
		configuracionVentana();
		iniciaComponente();
	}
	public void configuracionVentana(){
		setLayout(null);
		setTitle("Cliente");
		setSize(500, 470);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
	}
	public void iniciaComponente(){
		setLayout(null);
		Container container = getContentPane();
		/////////////////Boton ver Buscar/////////////////////
		container.add(buscar);
		buscar.addMouseListener(this);
		///////////////////Boton Guardar////////////////////////
		container.add(guardar);
		guardar.addMouseListener(this);
		///////////////////Boton Eliminar////////////////////////
		container.add(eliminar);
		eliminar.setEnabled(false);
		eliminar.addMouseListener(this);
		////////////////Caja de texto Codigo del Cliente////////////////////////////////
		codigoCli = new JTextField();
		codigoCli.setBounds(50,50,250,40);
		codigoCli.setBorder(javax.swing.BorderFactory.createTitledBorder("Codigo del Cliente:"));
		container.add(codigoCli);
		////////////////Caja de texto Nombre del Cliente////////////////////////////////
		nombreCli = new JTextField();
		nombreCli.setBounds(50,130,250,40);
		nombreCli.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre:"));
		container.add(nombreCli);
		////////////////Caja de texto Apellido del Cliente////////////////////////////////
		apellidoCli = new JTextField();
		apellidoCli.setBounds(50,200,250,40);
		apellidoCli.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellido:"));
		container.add(apellidoCli);		
		////////////////Caja de texto Nombre del Cliente////////////////////////////////
		nitCli = new JTextField();
		nitCli.setBounds(50,270,250,40);
		nitCli.setBorder(javax.swing.BorderFactory.createTitledBorder("NIT:"));
		container.add(nitCli);
		///////////////////Etiqueta Estado///////////////////////
		barraEstado = new JLabel();
		barraEstado.setBounds(30, 410, 470, 30);
		barraEstado.setFont(new java.awt.Font("CONSOLA", 0, 25));
		barraEstado.setForeground( Color.black);
		barraEstado.setText("");
		add(barraEstado);
		//////// Texto info Cliente////////
		JLabel datosVenta = new JLabel();
		datosVenta.setBounds(50, 10, 350, 20);
		datosVenta.setFont(new java.awt.Font("CONSOLA", 1,20));
		datosVenta.setForeground( Color.black );
		datosVenta.setText("Ingrese Datos del Cliente.");
		add(datosVenta);
		setVisible(true);
	}
	public boolean campoVacio(String contenidoCaja){
		if(contenidoCaja != null)
			return true;
		return false;
	}
	public boolean esNumero(String numLetras){
		if((numLetras).matches("([0-9]|\\.)+"))
				return true;
		return false;
	}
	////////////Metodo para leer fichero y Cargarlo a Memoria Dinamica//////////////////
	public void cargarDatos(int archivoEscogido){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
		// Abre fichero y lo carga en bufferedreader
		archivo = new File (ruta+"//CLIENTE.clt");
		fr = new FileReader (archivo);
		br = new BufferedReader(fr);
		// Lectura del fichero
		String linea;// Guarda de forma dinamica cada linea que se lee en el fichero
			while((linea=br.readLine())!=null){
				String[] argtext = linea.split(",");
				agregarCli(Integer.parseInt(argtext[0]), argtext[1], argtext[2], Integer.parseInt(argtext[3]));
				// codigoEmpleado, nombreEmpleado, apellidoEmpleado, edadEmpleado, cargoEmpleado);
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
				}catch (Exception error){
					error.printStackTrace();
					}
			}
		}
	//////////////////////////////Finaliza Leer Fichero//////////////////////////
	@Override
	public void mousePressed(MouseEvent even) {
		if (even.getSource()==guardar) {
			if(esNumero(codigoCli.getText()) && campoVacio(nombreCli.getText()) && campoVacio(apellidoCli.getText()) && esNumero(nitCli.getText())){
				int confirma = JOptionPane.showOptionDialog( null,"Esta seguro de grabar un nuevo cliente.","Confirma Guardar Datos.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Aceptar", "Cancelar",},null);
				if(confirma == 0){
					EscribirFichero_201114430 es = new EscribirFichero_201114430();
					agregarCli(Integer.parseInt(codigoCli.getText()),nombreCli.getText(),apellidoCli.getText(),Integer.parseInt(nitCli.getText()));
					es.escribeDatoCliente(codigoCli.getText(),nombreCli.getText(),apellidoCli.getText(),nitCli.getText());
					barraEstado.setText("Cliente registrado con exito.");
					vaciaCajaTexto();
				}
				else if(confirma == 1){
					barraEstado.setText("No se realizo ningun cambio.");
					}	
			}
			else
				barraEstado.setText("Rellene todos los campos");
		}
		if(even.getSource() == buscar){
			vaciaCajaTexto();
			borrarLista();
			cargarDatos(4);//llama metodo que carga datos a una lista enlazada en memoria dinamica
			String buscaNumeroDeCliente = JOptionPane.showInputDialog("Ingrese codigo del Cliente.");
			if(buscaNumeroDeCliente != null){
				if(esNumero(buscaNumeroDeCliente)){
					buscarEm(Integer.parseInt(buscaNumeroDeCliente));
					eliminar.setEnabled(true);
				}
			}
			else{
				barraEstado.setText("Parametro de busqueda incorrecto.");
			}
		}
		if(even.getSource()==eliminar){
			if(esNumero(codigoCli.getText()) && campoVacio(nombreCli.getText())){
			int confirma = JOptionPane.showOptionDialog( null,"Esta seguro de elimiar \n" + "el registro del cliente.","Confirmar Eliminacion.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, new Object[] { "Aceptar", "Cancelar",},null);
			if(confirma == 0){
				EscribirFichero_201114430 es = new EscribirFichero_201114430();
				es.borrarContenidoFichero(4);//numero de fichero que quiero borrar
				
				eliminarCli(Integer.parseInt(codigoCli.getText()));		
				barraEstado.setText("Eliminacion exitosa.");
				grabarListaEnFichero();
				vaciaCajaTexto();
				eliminar.setEnabled(false);
				}
			else if(confirma == 1){
				barraEstado.setText("No se realizaron cambios.");
				vaciaCajaTexto();
				}
			}
			else
				barraEstado.setText("Rellene todos los campos");
		}		
	}
	@Override
	public void mouseClicked(MouseEvent even) {}
	@Override
	public void mouseEntered(MouseEvent even) {}
	@Override
	public void mouseExited(MouseEvent even) {}
	@Override
	public void mouseReleased(MouseEvent even) {}
}