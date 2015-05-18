package proyectoFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ventas_201114430 extends JFrame implements MouseListener {
	Icono_201114430 guardar = new Icono_201114430(350, 50,5);
	Icono_201114430 buscar = new Icono_201114430(350, 180,6);
	NodoVenta_201114430 inicio;

	public javax.swing.JButton ingresarNuevoCliente;
	public JTextField fecha,cantidad;
	public JComboBox producto,empleado,cliente;
	public JLabel barraEstado;
	public final String ruta = System.getProperties().getProperty("user.dir");

 	public Ventas_201114430(){
		configuracionVentana();
		iniciaComponente();
		cargarDatos(2);
		cargarDatos(3);
		cargarDatos(4);
		
	}
	public void configuracionVentana(){
		setLayout(null);
		setTitle("Ventas");
		setSize(500, 470);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		////////////////Caja de texto Nombre del Producto////////////////////////////////
		producto = new JComboBox();
		producto.setEditable(true);
		producto.setBounds(50,50,250,50);
		producto.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripcion del Producto:"));
		container.add(producto);
		////////////////Caja de texto Fecha de la Venta////////////////////////////////
		fecha = new JTextField();
		fecha.setBounds(50,130,250,40);
		fecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha:  dia.mes.año"));
		container.add(fecha);
		////////////////Caja de texto Nombre del Producto////////////////////////////////
		cantidad = new JTextField();
		cantidad.setBounds(50,200,250,40);
		cantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad:"));
		container.add(cantidad);
		////////////////JComboBoxNombre del Empleado////////////////////////////////
		empleado = new JComboBox();
        empleado.setEditable(true);
		empleado.setBounds(50,270,250,50);
		empleado.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione vendedor:"));
		//AutoCompleteDecorator.decorate(this.empleado);
		container.add(empleado);		
		///////////////////Boton Ingresar Nuevo Cliente////////////////////////
		ingresarNuevoCliente = new JButton("Ingrear nuevo cliente.");
		ingresarNuevoCliente.setBounds(50, 350, 250, 20);
		ingresarNuevoCliente.setFont(new java.awt.Font("CONSOLA", 0, 10));
		container.add(ingresarNuevoCliente);
		ingresarNuevoCliente.addMouseListener(this);		
		////////////////Caja de texto Nombre del Cliente////////////////////////////////
		cliente = new JComboBox();
        cliente.setEditable(true);
		cliente.setBounds(50,370,250,50);
		cliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione Cliente:"));
		container.add(cliente);
		///////////////////Etiqueta Estado///////////////////////
		barraEstado = new JLabel();
		barraEstado.setBounds(30, 415, 410, 30);
		barraEstado.setFont(new java.awt.Font("CONSOLA", 0, 25));
		barraEstado.setForeground( Color.black);
		barraEstado.setText("");
		add(barraEstado);
		//////// Texto info Venta////////
		JLabel datosVenta = new JLabel();
		datosVenta.setBounds(50, 10, 350, 20);
		datosVenta.setFont(new java.awt.Font("CONSOLA", 1,20));
		datosVenta.setForeground( Color.black );
		datosVenta.setText("Ingrese Datos de la Venta.");
		add(datosVenta);
		setVisible(true);
	}
	public void mostrarVe(){
		NodoVenta_201114430 tmp = inicio;
		if(tmp == null){
			System.out.println("Lista Vacia.");
		}
		else{
			while(tmp != null){
				System.out.println(tmp.codigoProducto + ",");
				System.out.println(tmp.fechaProducto + ",");
				System.out.println(tmp.cantidadProducto + ",");
				System.out.println(tmp.codigoEmpleado + ",");
				System.out.println(tmp.codigoCliente + ",");
				System.out.println();
				tmp = tmp.siguienteVe;				
			}
		}
	}	
	public boolean esNumero(String numLetras){
		if((numLetras).matches("([0-9]|\\.)+"))
				return true;
		return false;
	}
	//////////////////////////////Dos metodos para leer fichero//////////////////
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
				case 2:
					empleado.addItem(argtext[0]+ ", " + argtext[1] + " " + argtext[2]);
					break;
				case 3:
					producto.addItem(argtext[0]+ ", " + argtext[1]);
					break;
				case 4:
					cliente.addItem(argtext[0]+ ", " + argtext[1] + " " + argtext[2]);
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
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==guardar) {
			if(esNumero(fecha.getText()) && esNumero(cantidad.getText()) && producto.getSelectedItem() != ""){
				int confirma = JOptionPane.showOptionDialog( null,"Esta seguro de grabar la venta.","Confirma Guardar Datos.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Aceptar", "Cancelar",},null);
				if(confirma == 0){
					////////////////////Extraigo el codigo de cliente, producto, empleado para guardar///////////
					String codigoPr = (String) producto.getSelectedItem();
					String[] mcodigoPr = codigoPr.split(",");
					String codigoEm = (String) empleado.getSelectedItem();
					String[] mcodigoEm = codigoEm.split(",");
					String codigoCl = (String)cliente.getSelectedItem();
					String[] mcodigoCl = codigoCl.split(",");
					////////////////////finaliza spliteo de codigos ///////////
					EscribirFichero_201114430 es = new EscribirFichero_201114430();
					es.escribeDatoVenta(mcodigoPr[0], fecha.getText(), cantidad.getText(), mcodigoEm[0], mcodigoCl[0]);
					barraEstado.setText("Venta registrada con exito.");
					fecha.setText(null);
					cantidad.setText(null);
					}
				else if(confirma == 1){
					barraEstado.setText("No se guardaron los datos.");
					}
			}
			else
				barraEstado.setText("Rellene todos los campos");
		}
		if(e.getSource()==ingresarNuevoCliente){
			Cliente_201114430 cliente = new Cliente_201114430();
			cliente.setVisible(true);
			dispose();
		}
		if(e.getSource()==buscar){
			//generarReporteVenta();

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
