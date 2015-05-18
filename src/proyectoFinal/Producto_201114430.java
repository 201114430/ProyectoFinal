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

public class Producto_201114430 extends JFrame implements MouseListener{
	
	NodoProducto_201114430 inicio;
	Icono_201114430 guardar = new Icono_201114430(350, 50,5);
	Icono_201114430 buscar = new Icono_201114430(350, 180,6);
	Icono_201114430 eliminar = new Icono_201114430(350, 310,7);
	
	public JTextField codigoPro,descripcionPro,precioPro;
	public JLabel barraEstado;
	public final String ruta = System.getProperties().getProperty("user.dir");

 	public Producto_201114430() {
		configuracionVentana();
		iniciaComponente();
	}
	public void configuracionVentana(){
		setLayout(null);
		setSize(500, 470);
		setTitle("Producto");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		eliminar.addMouseListener(this);
		eliminar.setEnabled(false);
		////////////////Caja de texto Nombre del Producto////////////////////////////////
		codigoPro = new JTextField();
		codigoPro.setBounds(50,50,250,50);
		codigoPro.setBorder(javax.swing.BorderFactory.createTitledBorder("Codigo del Producto:"));
		container.add(codigoPro);
		////////////////Caja de texto Fecha de la Venta////////////////////////////////
		descripcionPro = new JTextField();
		descripcionPro.setBounds(50,160,250,50);
		descripcionPro.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripcion del Producto:"));
		container.add(descripcionPro);
		////////////////Caja de texto Nombre del Producto////////////////////////////////
		precioPro = new JTextField();
		precioPro.setBounds(50,270,250,50);
		precioPro.setBorder(javax.swing.BorderFactory.createTitledBorder("Precio:"));
		container.add(precioPro);
		///////////////////Etiqueta Estado///////////////////////
		barraEstado = new JLabel();
		barraEstado.setBounds(30, 410, 470, 30);
		barraEstado.setFont(new java.awt.Font("CONSOLA", 0, 25));
		barraEstado.setForeground( Color.black);
		barraEstado.setText("");
		add(barraEstado);
		//////// Texto info Venta////////
		JLabel datosVenta = new JLabel();
		datosVenta.setBounds(50, 10, 350, 20);
		datosVenta.setFont(new java.awt.Font("CONSOLA", 1,20));
		datosVenta.setForeground( Color.black );
		datosVenta.setText("Ingrese Datos del Producto.");
		add(datosVenta);
		setVisible(true);
	}
	public void agregarPro(int codigoProducto,String descripcionProducto,Double precioProducto){//agrega producto a lista en memoria dinamica
		if(inicio==null){
			inicio = new NodoProducto_201114430(codigoProducto, descripcionProducto, precioProducto);
			System.out.println("ingresando a la lista");
		}
		else{
			NodoProducto_201114430 tmp = inicio;
			inicio = new NodoProducto_201114430(codigoProducto, descripcionProducto, precioProducto);
			inicio.siguiente = tmp;
		}
	}
	public void eliminarPro(int codigoPro){
		NodoProducto_201114430 tmp = inicio;
		NodoProducto_201114430 anterior = null;
		if(tmp == null){
			barraEstado.setText("Base de datos producto vacia.");
			}
		else{
			while(tmp != null){
				if(tmp.codigoProducto == codigoPro){
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
	public void buscarPro(int codigoPr){
		NodoProducto_201114430 tmp = inicio;
		boolean siEsta = false;
		while(tmp != null){
			if(tmp.codigoProducto ==codigoPr){
				codigoPro.setText(Integer.toString(codigoPr));
				descripcionPro.setText(tmp.descripcionProducto);
				precioPro.setText(Double.toString(tmp.precio));
				barraEstado.setText("Busqueda exitosa.");
				siEsta = true;
				break;
			}
			else
				tmp = tmp.siguiente;
		}
		if(!siEsta)
			barraEstado.setText("Producto no existe.");
		
	}
	public void grabarListaEnFichero(){
		EscribirFichero_201114430 es = new EscribirFichero_201114430();
		
		NodoProducto_201114430 tmp = inicio;
		if(tmp == null){
			barraEstado.setText("Agregado a base de datos.");
		}
		else{
			while(tmp != null){
				es.escribeDatoProducto(
						Integer.toString(tmp.codigoProducto), 
						tmp.descripcionProducto, 
						Double.toString(tmp.precio)); 
						//Integer.toString(tmp.edadEmpleado), 
				tmp = tmp.siguiente;
			}
		}
	}
	public void vaciaCajaTexto(){
		codigoPro.setText(null);
		descripcionPro.setText(null);
		precioPro.setText(null);		
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
	////////////Metodo para leer fichero y Cargarlo a Memoria Dinamica///////////
	public void cargarDatos(int archivoEscogido){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
		// Abre fichero y lo carga en bufferedreader
		archivo = new File (ruta+"//PRODUCTO.prt");
		fr = new FileReader (archivo);
		br = new BufferedReader(fr);
		// Lectura del fichero
		String linea;// Guarda de forma dinamica cada linea que se lee en el fichero
			while((linea=br.readLine())!=null){
				String[] argtext = linea.split(",");
				//nombre = argtext[1] + " " + argtext[2];
				agregarPro(Integer.parseInt(argtext[0]), argtext[1], Double.parseDouble(argtext[2]));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			}
		finally{
			// Se cierra el fichero, para evitar corrupcion del fichero.
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
	public void mousePressed(MouseEvent even) {
		if (even.getSource()==guardar) {
			if(esNumero(codigoPro.getText()) && descripcionPro.getText() != "" && esNumero(precioPro.getText())){
				int confirma = JOptionPane.showOptionDialog( null,"Esta seguro de grabar el producto.","Confirma Guardar Datos.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] { "Aceptar", "Cancelar",},null);
				if(confirma == 0){
					EscribirFichero_201114430 es = new EscribirFichero_201114430();
					agregarPro(Integer.parseInt(codigoPro.getText()), descripcionPro.getText(), Double.parseDouble(precioPro.getText()));
					es.escribeDatoProducto(codigoPro.getText(), descripcionPro.getText(), precioPro.getText());
					barraEstado.setText("Producto registrado con exito.");
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
			cargarDatos(1);//////////////verificar el numero
			String buscaCodigoProducto = JOptionPane.showInputDialog("Ingrese codigo del producto.");
			if(buscaCodigoProducto != null){
				if(esNumero(buscaCodigoProducto)){
					buscarPro(Integer.parseInt(buscaCodigoProducto));
					eliminar.setEnabled(true);
				}
			}
			else{
				barraEstado.setText("Parametro de busqueda incorrecto.");
			}
		}
		if(even.getSource() == eliminar){
			if(esNumero(codigoPro.getText()) && campoVacio(descripcionPro.getText()) && esNumero(precioPro.getText())){
				int confirma = JOptionPane.showOptionDialog(null, "Esta seguro de eliminar \n" + "el producto de la base de datos.", "Confirmar Eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"Aceptar", "Cancelar"},null);
				if(confirma == 0){
					EscribirFichero_201114430 es = new EscribirFichero_201114430();
					es.borrarContenidoFichero(3);//numero de fichero que quiero borrar
					
					eliminarPro(Integer.parseInt(codigoPro.getText()));
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
				barraEstado.setText("Relleno todos los campos.");
		}
	}
	@Override
	public void mouseClicked(MouseEvent even) {
	}
	@Override
	public void mouseEntered(MouseEvent even){}
	@Override
	public void mouseExited(MouseEvent even){}
	@Override
	public void mouseReleased(MouseEvent even) {}
}
