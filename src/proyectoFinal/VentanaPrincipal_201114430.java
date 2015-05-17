package proyectoFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class VentanaPrincipal_201114430 extends JFrame implements MouseListener{
	Icono_201114430 botonVenta = new Icono_201114430(30, 100, 220, 220,1);
	Icono_201114430 botonEmpleado = new Icono_201114430(280, 100, 220, 220,2);
	Icono_201114430 botonProducto = new Icono_201114430(530, 100, 220, 220,3);
	Icono_201114430 botonCliente = new Icono_201114430(780, 100, 220, 220,4);
	Icono_201114430 botonVenExt = new Icono_201114430(70, 400,1);
	Icono_201114430 botonEmpExt = new Icono_201114430(340, 400,2);
	Icono_201114430 botonProExt = new Icono_201114430(590, 400,3);
	Icono_201114430 botonCliExt = new Icono_201114430(840, 400,4);
	Ventas_201114430 ventas;
	Empleado_201114430 empleado;
	Producto_201114430 producto;
	Cliente_201114430 cliente;
	
	public javax.swing.JLabel etiquetaIcon,etiquetaIcon1,etiquetaIcon2,barraEstado;
	public javax.swing.JButton ingresar,reporte;
 	public boolean ingresarDatos;
 	public boolean generarReportes;

 	public VentanaPrincipal_201114430(){//constructor de la Ventana Principal 		
 		configuracionVentana();
		iniciaIcono();
		initfondo();
		repaint();
 	}
	public void configuracionVentana(){
		setSize(1030, 580);
		setTitle("Servicio General al Cliente S.A.");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		
	}
 	private void initfondo(){//fondo de la Ventana Principal si funciona
		setLayout(null);
		Container container = getContentPane();
		Icono_201114430 fondo = new Icono_201114430(0,40);
		container.add(fondo);		
	}
 	public void iniciaIcono(){
		setLayout(null);
		Container container = getContentPane();
		container.setLayout(null);
		///////////////////Boton Venta////////////////////////
		container.add(botonVenta);
		//botonVenta.setVisible(true);
		//botonVenta.setEnabled(false);
		botonVenta.addMouseListener(this);
		///////////////////Boton Empleado//////////////////////
		container.add(botonEmpleado);
		//botonEmpleado.setVisible(true);
		//botonEmpleado.setEnabled(false);
		botonEmpleado.addMouseListener(this);
		///////////////////Boton Producto//////////////////////
		container.add(botonProducto);
		//botonProducto.setVisible(true);
		//botonProducto.setEnabled(false);
		botonProducto.addMouseListener(this);
		///////////////////Boton Cliente///////////////////////
		container.add(botonCliente);
		//botonCliente.setVisible(true);
		//botonCliente.setEnabled(false);
		botonCliente.addMouseListener(this);
		///////////////////Boton Ingresar///////////////////////
        ingresar=new JButton("Agregar o Eliminar:");
        ingresar.setBounds(0, 0, 270, 40);	
        container.add(ingresar);
		ingresar.setVisible(true);
		ingresar.setFont(new java.awt.Font("CONSOLA", 0, 20));
		ingresar.addMouseListener(this);
		///////////////////Boton Reporte///////////////////////
        reporte=new JButton("Generar Reportes:");
        reporte.setBounds(270, 0, 250, 40);	
        container.add(reporte);
		reporte.setVisible(true);
		reporte.setFont(new java.awt.Font("CONSOLA", 0, 20));
		reporte.addMouseListener(this);
		////////////////////////Botones para seleccionar Fichero y generar reporte//////////////
		///////////////////Boton Venta Fichero Externo////////////////////////
		container.add(botonVenExt);
		botonVenExt.addMouseListener(this);
		///////////////////Boton Empleado Fichero Externo////////////////////////
		container.add(botonEmpExt);
		botonEmpExt.addMouseListener(this);
		///////////////////Boton Producto Fichero Externo////////////////////////
		container.add(botonProExt);
		botonProExt.addMouseListener(this);
		///////////////////Boton Cliente Fichero Externo////////////////////////
		container.add(botonCliExt);
		botonCliExt.addMouseListener(this);
		///////////////////Etiqueta Iconos///////////////////////
		etiquetaIcon = new JLabel();
		etiquetaIcon.setBounds(100, 70, 850, 30);
		etiquetaIcon.setFont(new java.awt.Font("CONSOLA", 0, 25));
		etiquetaIcon.setForeground( Color.black);
		etiquetaIcon.setText("");
		add(etiquetaIcon);
		///////////////////Etiqueta Iconos///////////////////////
		etiquetaIcon1 = new JLabel();
		etiquetaIcon1.setBounds(100, 40, 950, 30);
		etiquetaIcon1.setFont(new java.awt.Font("CONSOLA", 0, 25));
		etiquetaIcon1.setForeground( Color.black);
		etiquetaIcon1.setText("");
		add(etiquetaIcon1);
		///////////////////Etiqueta Iconos///////////////////////
		etiquetaIcon2 = new JLabel();
		etiquetaIcon2.setBounds(100, 350, 850, 30);
		etiquetaIcon2.setFont(new java.awt.Font("CONSOLA", 0, 25));
		etiquetaIcon2.setForeground( Color.black);
		etiquetaIcon2.setText("Generar Reporte desde archivos.");
		add(etiquetaIcon2);
		///////////////////Etiqueta Estado///////////////////////
		barraEstado = new JLabel();
		barraEstado.setBounds(30, 540, 470, 30);
		barraEstado.setFont(new java.awt.Font("CONSOLA", 0, 25));
		barraEstado.setForeground( Color.black);
		barraEstado.setText("");
		add(barraEstado);
	}
	public boolean esNumero(String numLetras){
		if((numLetras).matches("([0-9]|\\.)+"))
				return true;
		return false;
	}
	public String extencionFichero(int archivoEscogido){//escoge ruta segun actividad seleccionada
		if(archivoEscogido==1)
			return "fct";
		else if(archivoEscogido==2)
			return "emp";
		else if(archivoEscogido==3)
			return "prt";
		else if(archivoEscogido==4)
			return "clt";
		return null;		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==botonVenta && ingresarDatos) {
			ventas = new Ventas_201114430();
		}
		if (e.getSource()==botonEmpleado && ingresarDatos) {
			empleado = new Empleado_201114430();
		}
		if(e.getSource()==botonProducto && ingresarDatos){
			producto = new Producto_201114430();
		}
		if(e.getSource()==botonCliente && ingresarDatos){
			cliente = new Cliente_201114430();
		}
		if(e.getSource() == reporte){
			etiquetaIcon1.setText("Todas las             Ventas por           Venta por rango       Compras por ");
			etiquetaIcon.setText("Ventas                 Empleados               de fecha                  Cliente");
			botonVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/rv.png")));
			botonEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/re.png")));
			botonProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/rf.png")));
			botonCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/ce.png")));
			this.generarReportes = true;
			this.ingresarDatos = false;
			reporte.setEnabled(false);
			ingresar.setEnabled(true);
		}
		if(e.getSource() == ingresar){
			etiquetaIcon1.setText("");
			etiquetaIcon.setText("Ventas                 Empleados                Productos                Clientes");
			botonVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/v1.png")));
			botonEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/e4.png")));
			botonProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/p2.png")));
			botonCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/rc.png")));
			this.generarReportes = false;
			this.ingresarDatos = true;
			ingresar.setEnabled(false);
			reporte.setEnabled(true);
			
			botonVenta.setVisible(true);
			botonEmpleado.setVisible(true);
			botonProducto.setVisible(true);
			botonCliente.setVisible(true);
		}
		if(e.getSource()==botonVenta && generarReportes){
			Reportes_201114430 re = new Reportes_201114430();
			re.cargarDatos(1);
			re.cargarDatos(2);
			re.cargarDatos(3);
			re.cargarDatos(4);
			re.ordenarVentas();
			re.llenarListaReporteVentas();
			re.generarReporteVentas();
			abrirarchivo("Ventas.html");
		}
		if(e.getSource()==botonEmpleado && generarReportes){
			String fecha = JOptionPane.showInputDialog("Ingrese fecha para generar el reporte. \n" + "ó escriba \" todos\".");
			if(fecha != null){
				if(fecha.equals("todos")){
					Reportes_201114430 re = new Reportes_201114430();
					re.cargarDatos(1);
					re.cargarDatos(2);
					re.cargarDatos(3);
					re.cargarDatos(4);
					re.ordenarVentas();
					re.llenarListaRepVenPorEmp("todos");
					re.ordenarVenxEmp();
					re.generarReporteVentasPorEmpleado();
					abrirarchivo("VentasxEmpleado.html");
				}
				else if(esNumero(fecha)){
					Reportes_201114430 re = new Reportes_201114430();
					re.cargarDatos(1);
					re.cargarDatos(2);
					re.cargarDatos(3);
					re.cargarDatos(4);
					re.ordenarVentas();
					re.llenarListaRepVenPorEmp(fecha);
					re.ordenarVenxEmp();
					re.generarReporteVentasPorEmpleado();
					abrirarchivo("VentasxEmpleado.html");
				}
			}
			else{
				barraEstado.setText("Parametro de busqueda incorrecto.");
			}

		}
		if(e.getSource()==botonProducto && generarReportes){
			ReporteVentasRangoFecha_201114430 re = new ReporteVentasRangoFecha_201114430();
			re.setVisible(true);
		}
		if(e.getSource()==botonCliente && generarReportes){
			Reportes_201114430 rcc = new Reportes_201114430();
			rcc.cargarDatos(1);
			rcc.cargarDatos(2);
			rcc.cargarDatos(3);
			rcc.cargarDatos(4);
			rcc.llenarRepComCli();
			rcc.ordenaCC();
			rcc.generaRepComPorCli();
			abrirarchivo("ComprasporCliente.html");
		}
		if(e.getSource() == botonVenExt){
			File direccionFichero = null;
			String dvet = null;
			String dempt = null;
			String dprot = null;
			boolean veb = false;
			boolean emb = false;
			boolean prb = false;
			for(int i=1;i<=3;i++){
				////////////////////////////////Abre ventana y selecciona archivo/////////////////////////////////////
		  		JFileChooser seleccionarFichero = new JFileChooser();
		  		FileNameExtensionFilter filtraArchivo = new FileNameExtensionFilter("fct, emp, prt", extencionFichero(i));
		  		seleccionarFichero.setFileFilter(filtraArchivo);
		  		
		  		int resultado = seleccionarFichero.showOpenDialog(null);
		  		if(resultado == JFileChooser.APPROVE_OPTION){
		  			direccionFichero = seleccionarFichero.getSelectedFile();
		  		}
				////////////////////////////////Fin de selecciona archivo/////////////////////////////////////		  		
		  		switch(i){
		  		case 1:
		  			if((boolean)direccionFichero.getName().equals("VENTA.fct")){
						dvet = direccionFichero.getParent();
						veb = true;
		  			}
		  			else
		  				break;
		  		case 2:
		  			if((boolean)direccionFichero.getName().equals("EMPLEADO.emp")){
			  			dempt = direccionFichero.getParent();
						emb = true;
		  			}
		  			else
		  				break;
		  		case 3:
		  			if((boolean)direccionFichero.getName().equals("PRODUCTO.prt")){
		  				dprot = direccionFichero.getParent();
		  				prb = true;
		  			}
		  			else
		  				break;		  		
		  		}

			}
	  		if(veb && emb && prb){
	  			Reportes_201114430 re = new Reportes_201114430();
	  			re.ruta = dvet;
	  			re.cargarDatos(1);
	  			re.ruta = dempt;
	  			re.cargarDatos(2);
	  			re.ruta = dprot;
	  			re.cargarDatos(3);
				re.llenarListaReporteVentas();
				re.generarReporteVentas();
				abrirarchivo("Ventas.html");
	  			System.out.println("Seleccion de archivos correcta");
	  		}
	  		else{
				JOptionPane.showMessageDialog( VentanaPrincipal_201114430.this,
			               "Seleccion de Archivos incorrecta",
			               "Error de Seleccion", JOptionPane.ERROR_MESSAGE );
	  		}
			
		}
		if(e.getSource() == botonEmpExt) {
			File direccionFichero = null;
			String dvet = null;
			String dempt = null;
			boolean veb = false;
			boolean emb = false;
			for(int i=1;i<=2;i++){
				////////////////////////////////Abre ventana y selecciona archivo/////////////////////////////////////
		  		JFileChooser seleccionarFichero = new JFileChooser();
		  		FileNameExtensionFilter filtraArchivo = new FileNameExtensionFilter("fct, emp", extencionFichero(i));
		  		seleccionarFichero.setFileFilter(filtraArchivo);
		  		
		  		int resultado = seleccionarFichero.showOpenDialog(null);
		  		if(resultado == JFileChooser.APPROVE_OPTION){
		  			direccionFichero = seleccionarFichero.getSelectedFile();
		  		}
				////////////////////////////////Fin de selecciona archivo/////////////////////////////////////		  		
		  		switch(i){
		  		case 1:
		  			if((boolean)direccionFichero.getName().equals("VENTA.fct")){
						dvet = direccionFichero.getParent();
						veb = true;
		  			}
		  			else
		  				break;
		  		case 2:
		  			if((boolean)direccionFichero.getName().equals("EMPLEADO.emp")){
			  			dempt = direccionFichero.getParent();
						emb = true;
		  			}
		  			else
		  				break;	  		
		  		}
			}
	  		if(veb && emb){
				String fecha = JOptionPane.showInputDialog("Ingrese fecha para generar el reporte. \n" + "ó escriba \" todos\".");
				if(fecha != null){
					if(fecha.equals("todos")){
						Reportes_201114430 re = new Reportes_201114430();
		  				re.ruta = dvet;
		  				re.cargarDatos(1);
		  				re.ruta = dempt;
		  				re.cargarDatos(2);
						re.ordenarVentas();
						re.llenarListaRepVenPorEmp("todos");
						re.ordenarVenxEmp();
						re.generarReporteVentasPorEmpleado();
						abrirarchivo("VentasxEmpleado.html");
					}
					else if(esNumero(fecha)){
						Reportes_201114430 re = new Reportes_201114430();
		  				re.ruta = dvet;
		  				re.cargarDatos(1);
		  				re.ruta = dempt;
		  				re.cargarDatos(2);
						re.ordenarVentas();
						re.llenarListaRepVenPorEmp(fecha);
						re.ordenarVenxEmp();
						re.generarReporteVentasPorEmpleado();
						abrirarchivo("VentasxEmpleado.html");
					}
				}
				else{
					barraEstado.setText("Parametro de busqueda incorrecto.");
				}
	  		}
	  		else{
				JOptionPane.showMessageDialog( VentanaPrincipal_201114430.this,
			               "Seleccion de Archivos incorrecta",
			               "Error de Seleccion", JOptionPane.ERROR_MESSAGE );
	  		}
		}
		if(e.getSource() == botonProExt){
			RepVenRanFechFichExt_201114430 re = new RepVenRanFechFichExt_201114430();
			//re.setVisible(true);
		}
		if(e.getSource() == botonCliExt){
			File direccionFichero = null;
			String dclit = null;
			String dvet = null;
			boolean veb = false;
			boolean clib = false;
			String ext = "clt";
			for(int i=1;i<=2;i++){
				////////////////////////////////Abre ventana y selecciona archivo/////////////////////////////////////
		  		JFileChooser selefich = new JFileChooser();
		  		FileNameExtensionFilter filArch = new FileNameExtensionFilter("clt, fct", ext);
		  		selefich.setFileFilter(filArch);
		  		ext = "fct";
		  		
		  		int resultado = selefich.showOpenDialog(null);
		  		if(resultado == JFileChooser.APPROVE_OPTION){
		  			direccionFichero = selefich.getSelectedFile();
		  		}
				////////////////////////////////Fin de selecciona archivo/////////////////////////////////////		  		
		  		switch(i){
		  		case 1:
		  			if((boolean)direccionFichero.getName().equals("CLIENTE.clt")){
			  			dclit = direccionFichero.getParent();
						clib = true;
		  			}
		  			else
		  				break;
		  		case 2:
		  			if((boolean)direccionFichero.getName().equals("VENTA.fct")){
						dvet = direccionFichero.getParent();
						veb = true;
		  			}
		  			else
		  				break;	  		
		  		}

			}
	  		if(clib && veb){
	  			Reportes_201114430 re = new Reportes_201114430();
	  			re.ruta = dvet;
	  			re.cargarDatos(1);
	  			re.ruta = dclit;
	  			re.cargarDatos(4);
				re.llenarRepComCli();
				re.ordenaCC();
				re.generaRepComPorCli();
				abrirarchivo("ComprasporCliente.html");
	  			System.out.println("Seleccion de archivos correcta");
	  		}
	  		else{
				JOptionPane.showMessageDialog( VentanaPrincipal_201114430.this,
			               "Seleccion de Archivos incorrecta",
			               "Error de Seleccion", JOptionPane.ERROR_MESSAGE );
	  		}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	public void abrirarchivo(String archivo){
	     try {
	            File reporteSeleccionado = new File (archivo);
	            Desktop.getDesktop().open(reporteSeleccionado);

	     }catch (IOException ex) {

	            System.out.println("Errores encontrados");
	     }
	} 
	@Override
	public void mouseExited(MouseEvent e) {}
	public static void main(String[] args){
		VentanaPrincipal_201114430 ventanaprincipal = new VentanaPrincipal_201114430();
	}
}