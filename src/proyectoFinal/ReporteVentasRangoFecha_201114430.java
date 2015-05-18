package proyectoFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReporteVentasRangoFecha_201114430 extends JFrame implements MouseListener {
	
	NodoVenta_201114430 inicio;

	public javax.swing.JButton reporteFecha;
	public JTextField fechaIni,fechaFin;
	public JLabel barraEstado;

 	public ReporteVentasRangoFecha_201114430() {
		configuracionVentana();
		iniciaComponente();
		
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
	public void abrirArchivo(String archivo){
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
						Reportes_201114430 re = new Reportes_201114430();
						re.cargarDatos(1);
						re.cargarDatos(2);
						re.cargarDatos(3);
						re.cargarDatos(4);
						re.ordenarVenFech();
						re.llenarRepVentFec(fi,ff );
						re.generaRepVenRanFech();
						abrirArchivo("VentasRangoFechas.html");
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
