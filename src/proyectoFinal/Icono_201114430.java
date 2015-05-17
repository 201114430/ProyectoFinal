package proyectoFinal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.sun.javafx.geom.RoundRectangle2D;

public class Icono_201114430 extends JButton{
	int x;
	int y;
	int ancho;
	int alto;
	public final String ruta = System.getProperties().getProperty("user.dir");
	public Icono_201114430(int x,int y, int ancho, int alto,int a){//iconos grandes
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		setBorderPainted(true);
	    setContentAreaFilled(false);
	    setBounds(x,y,ancho,alto);
	    setVisible(false);
		String urlIconoGrande = imagenIcon(a);
	    ImageIcon icon = new ImageIcon(getClass().getResource(urlIconoGrande));
	    Image img = icon.getImage();
	    Image otraimg = img.getScaledInstance(220,220,java.awt.Image.SCALE_SMOOTH);
	    ImageIcon otroicon = new ImageIcon(otraimg);
	    setIcon(otroicon);
	}
	public Icono_201114430(int x,int y,int a){//iconos pequeños
		this.x = x;
		this.y = y;
		this.ancho = 100;
		this.alto = 100;
		//setText("Guardar");
		setBorderPainted(true);
	    setContentAreaFilled(true);
	    setBounds(x,y,ancho,alto);
	    setVisible(true);
		String urlIconPeque = imagenIcon(a);
	    ImageIcon icon = new ImageIcon(getClass().getResource(urlIconPeque));
	    Image img = icon.getImage();
	    Image otraimg = img.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
	    ImageIcon otroicon = new ImageIcon(otraimg);
	    setIcon(otroicon);
	}
	public Icono_201114430(int x,int y){//fondo
		this.x = x;
		this.y = y;
		this.ancho = 1030;
		this.alto = 580;
		//setText("Guardar");
		setBorderPainted(false);
	    setContentAreaFilled(false);
	    setBounds(x,y,ancho,alto);
	    setVisible(true);
		String urlIconPeque = imagenIcon(9);
	    ImageIcon icon = new ImageIcon(getClass().getResource(urlIconPeque));
	    Image img = icon.getImage();
	    Image otraimg = img.getScaledInstance(1030,580,java.awt.Image.SCALE_SMOOTH);
	    ImageIcon otroicon = new ImageIcon(otraimg);
	    setIcon(otroicon);
	}
	public String imagenIcon(int i) {//directorio de imagenes
		int figura = i;
		if(figura==1)
			return "/imagen/v1.png";
		else if(figura==2)
			return "/imagen/e4.png";
		else if(figura==3)
			return "/imagen/p2.png";
		else if(figura==4)
			return "/imagen/rc.png";
		else if(figura==5)
			return "/imagen/g1.png";
		else if(figura==6)
			return "/imagen/b.png";
		else if(figura==7)
			return "/imagen/e0.png";
		else if(figura==8)
			return "/imagen/rv.png";
		else if(figura==9)
			return "/imagen/f.jpg";
		return ruta;	
	}
}
