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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RepVenRanFechFichExt_201114430 extends JFrame implements MouseListener {
	
	NodoVenta_201114430 inicio;
	Reportes_201114430 re = new Reportes_201114430();

	public javax.swing.JButton reporteFecha;
	public JTextField fechaIni,fechaFin;
	public JLabel barraEstado;

 	public RepVenRanFechFichExt_201114430() {
		seleccionarFichero();
	}
	public void seleccionarFichero() {
		File direccionFichero = null;
		String dvet = null;
		String dprot = null;
		boolean veb = false;
		boolean clib = false;
		String ext = "fct";
		for(int i=1;i<=2;i++){
			////////////////////////////////Abre ventana y selecciona archivo/////////////////////////////////////
	  		try{
	  			JFileChooser selefich = new JFileChooser();
	  		FileNameExtensionFilter filArch = new FileNameExtensionFilter("fct, prt", ext);
	  		selefich.setFileFilter(filArch);
	  		ext = "prt";
	  		
	  		int resultado = selefich.showOpenDialog(null);
	  		if(resultado == JFileChooser.APPROVE_OPTION){
	  			direccionFichero = selefich.getSelectedFile();
	  		}
	  		}catch(Exception error){
	  			System.out.println(error);
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
	  			if((boolean)direccionFichero.getName().equals("PRODUCTO.prt")){
	  				dprot = direccionFichero.getParent();
					clib = true;
	  			}
	  			else
	  				break;
  		
	  		}

		}
  		if(clib && veb){
  			re.ruta = dvet;
  			re.cargarDatos(1);
  			re.ruta = dprot;
  			re.cargarDatos(3);
  			configuracionVentana();
  			iniciaComponente();
  		}
  		else{
			JOptionPane.showMessageDialog( RepVenRanFechFichExt_201114430.this,
		               "Seleccion de Archivos incorrecta",
		               "Error de Seleccion", JOptionPane.ERROR_MESSAGE );
  		}
	
		////////////////////////constructor de la ventana fecha inicial y fecha final/////////////
	}
	public void configuracionVentana(){
		setLayout(null);
		setTitle("Reporte de Ventas por Rango de Fecha.");
		setSize(350, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
	}
	public void iniciaComponente(){
		setLayout(null);
		Container container = getContentPane();
		///////////////////Boton Reporte Fechas///////////////////////
        reporteFecha=new JButton("Generar Reporte:");
        reporteFecha.setBounds(65, 180, 220, 30);	
        container.add(reporteFecha);
        reporteFecha.setVisible(true);
		reporteFecha.setFont(new java.awt.Font("CONSOLA", 0, 20));
		reporteFecha.addMouseListener(this);
		////////////////JComboBox Fecha Inicial////////////////////////////////
		fechaIni = new JTextField();
        fechaIni.setEditable(true);
		fechaIni.setBounds(50,50,250,50);
		fechaIni.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha inicial:"));
		fechaIni.setVisible(true);
		container.add(fechaIni);
		////////////////JComboBox Fecha Final////////////////////////////////
		fechaFin = new JTextField();
        fechaFin.setEditable(true);
		fechaFin.setBounds(50,110,250,50);
		fechaFin.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha final:"));
		fechaFin.setVisible(true);
		container.add(fechaFin);
		///////////////////Etiqueta Estado///////////////////////
		barraEstado = new JLabel();
		barraEstado.setBounds(10, 220, 330, 20);
		barraEstado.setFont(new java.awt.Font("CONSOLA", 0, 15));
		barraEstado.setForeground( Color.black);
		barraEstado.setText("");
		add(barraEstado);
		//////// Texto info Venta////////
		JLabel datosVenta = new JLabel();
		datosVenta.setBounds(50, 10, 350, 20);
		datosVenta.setFont(new java.awt.Font("CONSOLA", 1,20));
		datosVenta.setForeground( Color.black );
		datosVenta.setText("Seleccione fechas.");
		add(datosVenta);
		setVisible(true);
	}
	public boolean esNumero(String numLetras){
		if((numLetras).matches("([0-9]|\\.)+"))
				return true;
		return false;
	}
	public void abrirarchivo(String archivo){
	     try {
	            File reporteSeleccionado = new File (archivo);
	            Desktop.getDesktop().open(reporteSeleccionado);
	     }catch (IOException ex) {
	            System.out.println("Errores encontrados");
	     }
	}
	public void vaciaCajaTexto(){
		fechaIni.setText(null);
		fechaFin.setText(null);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==reporteFecha) {
			String fi = fechaIni.getText();
			String ff = fechaFin.getText();
				if( esNumero(fi) && esNumero(ff)){
					String[] fechaI = fi.split("\\.");
					int diaini = (Integer.parseInt(fechaI[0]) + Integer.parseInt(fechaI[1])*30 + Integer.parseInt(fechaI[2])*360);
					String[] fechaF = ff.split("\\.");
					int diafin = (Integer.parseInt(fechaF[0]) + Integer.parseInt(fechaF[1])*30 + Integer.parseInt(fechaF[2])*360);
					if(diaini <= diafin){
						re.mostrarVentas();
						re.mostrarProducto();
						re.ordenarVenFech();
						re.llenarRepVentFec(fi,ff );
						re.generaRepVenRanFech();
						abrirarchivo("VentasRangoFechas.html");
						vaciaCajaTexto();
					}
					else{
						barraEstado.setText("Fecha incial mayor que fecha final.");
						vaciaCajaTexto();
					}
				}
				else{
					barraEstado.setText("Rango de Fecha incorrecta.");
					vaciaCajaTexto();
				}
		}
		dispose();
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
