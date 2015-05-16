package proyectoFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Empleado_201114430 extends JFrame implements MouseListener{
	
	NodoEmpleado_201114430 inicio;
	Icono_201114430 guardar = new Icono_201114430(370, 250,5);
	Icono_201114430 buscar = new Icono_201114430(480, 250,6);
	
	public javax.swing.JButton eliminar,imagenEm,buscarIma;
	public JTextField codigoEm,nombreEm,apellidoEm,edadEm,cargoEm;
	public String nombre;
	public JLabel barraEstado;
	public final String ruta = System.getProperties().getProperty("user.dir");
	
	public void agregarEm(int codigoEmpleado,String nombreEmpleado, String apellidoEmpleado,int edadEmpleado,String cargoEmpleado){
		if(inicio==null){
			inicio = new NodoEmpleado_201114430(codigoEmpleado,nombreEmpleado, apellidoEmpleado,edadEmpleado,cargoEmpleado);
		}
		else{
			NodoEmpleado_201114430 tmp = inicio;
			inicio = new NodoEmpleado_201114430(codigoEmpleado,nombreEmpleado, apellidoEmpleado,edadEmpleado,cargoEmpleado);
			inicio.siguienteEm = tmp;
		}
	}
	public void eliminarEm(int codigoEmp){
		NodoEmpleado_201114430 tmp = inicio;
		NodoEmpleado_201114430 anterior = null;
		if(tmp == null){
			barraEstado.setText("Base de datos empleado vacia.");
			}
		else{
			while(tmp != null){
				if(tmp.codigoEmpleado == codigoEmp){
					if(anterior == null){
						inicio = inicio.siguienteEm;
					}
					else{
						anterior.siguienteEm = tmp.siguienteEm;
					}
					break;
				}
				anterior = tmp;
				tmp = tmp.siguienteEm;
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
		codigoEm.setText(null);
		nombreEm.setText(null);
		apellidoEm.setText(null);
		edadEm.setText(null);
		cargoEm.setText(null);			
	}
	public void buscarEm(int codigoEmp){
		NodoEmpleado_201114430 tmp = inicio;
		boolean siEsta = false;
		while(tmp != null){
			if(tmp.codigoEmpleado ==codigoEmp){
				codigoEm.setText(Integer.toString(codigoEmp));
				nombreEm.setText(tmp.nombreEmpleado);
				apellidoEm.setText(tmp.apellidoEmpleado);
				edadEm.setText((Integer.toString(tmp.edadEmpleado)));
				cargoEm.setText(tmp.cargoEmpleado);
				barraEstado.setText("Busqueda exitosa.");
				siEsta = true;
				break;
			}
			else
				tmp = tmp.siguienteEm;
		}
		if(!siEsta)
			barraEstado.setText("Empleado no existe.");
		
	}
	public void grabarListaEnFichero(){
		EscribirFichero_201114430 es = new EscribirFichero_201114430();
		
		NodoEmpleado_201114430 tmp = inicio;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				es.escribeDatoEmpleado(
						Integer.toString(tmp.codigoEmpleado), 
						tmp.nombreEmpleado, 
						tmp.apellidoEmpleado, 
						Integer.toString(tmp.edadEmpleado), 
						tmp.cargoEmpleado);
				tmp = tmp.siguienteEm;
			}
		}
	}
 	public Empleado_201114430() {
		configuracionVentana();
		iniciaComponente();
	}
	public void configuracionVentana(){
		setLayout(null);
		setTitle("Empleado");
		setSize(650, 470);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
	}
	public void iniciaComponente(){
		setLayout(null);
		Container container = getContentPane();
		/////////////////Boton Buscar/////////////////////
		container.add(buscar);
		buscar.addMouseListener(this);
		///////////////////Boton Guardar////////////////////////
		container.add(guardar);
		guardar.addMouseListener(this);
		///////////////////Boton Eliminarr////////////////////////
		eliminar = new JButton("Eliminar");
		eliminar.setBounds(415, 370, 120, 30);
		eliminar.setEnabled(false);
		container.add(eliminar);
		eliminar.addMouseListener(this);
		///////////////////Boton Buscar Imagen////////////////////////
		buscarIma = new JButton("Seleccionar imagen.");
		buscarIma.setBounds(400, 210, 160, 20);
		buscarIma.setFont(new java.awt.Font("CONSOLA", 0, 10));
		container.add(buscarIma);
		buscarIma.addMouseListener(this);
		///////////////////Boton Imagen Empleado///////////////////////
        imagenEm=new JButton("Imagen:");
        imagenEm.setBounds(400, 50, 160, 160);	
        container.add(imagenEm);
		imagenEm.setVisible(true);
		imagenEm.setBorderPainted(false);
		imagenEm.setFont(new java.awt.Font("CONSOLA", 0, 20));
		imagenEm.addMouseListener(this);
		////////////////Caja de texto Codigo del Empleado////////////////////////////////
		codigoEm = new JTextField();
		codigoEm.setBounds(50,50,250,40);
		codigoEm.setBorder(javax.swing.BorderFactory.createTitledBorder("Codigo del Empleado:"));
		container.add(codigoEm);
		////////////////Caja de texto Nombre del Empleado////////////////////////////////
		nombreEm = new JTextField();
		nombreEm.setBounds(50,130,250,40);
		nombreEm.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre:"));
		container.add(nombreEm);
		////////////////Caja de texto Apellido del Empleado////////////////////////////////
		apellidoEm = new JTextField();
		apellidoEm.setBounds(50,200,250,40);
		apellidoEm.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellido:"));
		container.add(apellidoEm);		
		////////////////Caja de texto Nombre del Empleado////////////////////////////////
		edadEm = new JTextField();
		edadEm.setBounds(50,270,250,40);
		edadEm.setBorder(javax.swing.BorderFactory.createTitledBorder("Edad:"));
		container.add(edadEm);
		////////////////Caja de texto Cargo del Empleado////////////////////////////////
		cargoEm = new JTextField();
		cargoEm.setBounds(50,350,250,40);
		cargoEm.setBorder(javax.swing.BorderFactory.createTitledBorder("Cargo:"));
		container.add(cargoEm);
		///////////////////Etiqueta Estado///////////////////////
		barraEstado = new JLabel();
		barraEstado.setBounds(30, 410, 470, 30);
		barraEstado.setFont(new java.awt.Font("CONSOLA", 0, 25));
		barraEstado.setForeground( Color.black);
		barraEstado.setText("");
		add(barraEstado);
		//////// Texto info Empleado////////
		JLabel datosVenta = new JLabel();
		datosVenta.setBounds(50, 10, 350, 20);
		datosVenta.setFont(new java.awt.Font("CONSOLA", 1,20));
		datosVenta.setForeground( Color.black );
		datosVenta.setText("Ingrese Datos del Empleado.");
		add(datosVenta);
		setVisible(true);
	}
	@Override
	public void mousePressed(MouseEvent even) {
		if (even.getSource()==guardar){
			if(esNumero(codigoEm.getText()) && campoVacio(nombreEm.getText()) && campoVacio(apellidoEm.getText()) && esNumero(edadEm.getText()) && campoVacio(cargoEm.getText())){
				int confirma = JOptionPane.showOptionDialog( null,"Esta seguro de grabar un nuevo empleado.","Confirma Guardar Datos.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Aceptar", "Cancelar",},null);
				if(confirma == 0){
					EscribirFichero_201114430 es = new EscribirFichero_201114430();
					agregarEm(Integer.parseInt(codigoEm.getText()),nombreEm.getText(),apellidoEm.getText(),Integer.parseInt(edadEm.getText()),cargoEm.getText());
					es.escribeDatoEmpleado(codigoEm.getText(), nombreEm.getText(), apellidoEm.getText(), edadEm.getText(), cargoEm.getText());
					barraEstado.setText("Empleado registrado con exito.");
					vaciaCajaTexto();
				}
				else if(confirma == 1){
					barraEstado.setText("No se realizo ningun cambio.");
					}			
			}
			else
				barraEstado.setText("Rellene todos los campos");
		}
		if(even.getSource()==buscar){
			vaciaCajaTexto();
			borrarLista();
			cargarDatos(1);//llama metodo cargar datos a una lista enlazada en memoria dinamica
			String buscaNumeroDeEmpleado = JOptionPane.showInputDialog("Ingrese codigo del empleado.");
			if(buscaNumeroDeEmpleado != null){
				if(esNumero(buscaNumeroDeEmpleado)){
					buscarEm(Integer.parseInt(buscaNumeroDeEmpleado));
					eliminar.setEnabled(true);
				}
			}
			else{
				barraEstado.setText("Parametro de busqueda incorrecto.");
			}
		}
		if(even.getSource()==eliminar){
			if(esNumero(codigoEm.getText()) && campoVacio(nombreEm.getText())){
			int confirma = JOptionPane.showOptionDialog( null,"Esta seguro de elimiar \n" + "el registro del empleado.","Confirma Guardar Datos.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Aceptar", "Cancelar",},null);
			if(confirma == 0){
				EscribirFichero_201114430 es = new EscribirFichero_201114430();
				es.borrarContenidoFichero(2);//numero de fichero que quiero borrar
				
				eliminarEm(Integer.parseInt(codigoEm.getText()));		
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
		if(even.getSource()==buscarIma){
			borrarLista();
		}
	}
	////////////Metodo para leer fichero y Cargarlo a Memoria Dinamica//////////////////
	public void cargarDatos(int archivoEscogido){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
		// Abre fichero y lo carga en bufferedreader
		archivo = new File (ruta+"//EMPLEADO.emp");
		fr = new FileReader (archivo);
		br = new BufferedReader(fr);
		// Lectura del fichero
		String linea;// Guarda de forma dinamica cada linea que se lee en el fichero
			while((linea=br.readLine())!=null){
				String[] argtext = linea.split(",");
				agregarEm(Integer.parseInt(argtext[0]), argtext[1], argtext[2], Integer.parseInt(argtext[3]), argtext[4]);
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
				}catch(Exception error){
					error.printStackTrace();
					}
			}
		}
	//////////////////////////////Finaliza Leer Fichero//////////////////////////
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
	@Override
	public void mouseClicked(MouseEvent even) {}
	@Override
	public void mouseEntered(MouseEvent even) {}
	@Override
	public void mouseExited(MouseEvent even) {}
	@Override
	public void mouseReleased(MouseEvent even) {}
	public static void main(String[] args){
		Empleado_201114430 emp = new Empleado_201114430();

			
	}


}
