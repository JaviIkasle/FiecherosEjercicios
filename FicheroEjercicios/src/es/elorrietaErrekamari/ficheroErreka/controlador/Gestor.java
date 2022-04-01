package es.elorrietaErrekamari.ficheroErreka.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import es.elorrietaErrekamari.ficheroErreka.modelo.Pedido;
import es.elorrietaErrekamari.ficheroErreka.vista.Menu;

/**
 * Gestiona todas las operaciones de fichero
 * 
 * @author in1dam-b
 */
public class Gestor {

	private ArrayList<Pedido> listar = new ArrayList<Pedido>();

	/**
	 * Retorna true si el fichero existe, false en cualqueir otro caso
	 * 
	 * @param nombreFichero
	 * @return True o false
	 */
	public boolean comprobarExistenciaFichero(String nombreFichero, String tipo) {

		boolean existe = false;
		try {

			File fichero = new File(Menu.RUTA + nombreFichero + tipo);

			if (fichero.exists()) {
				existe = true;
				System.out.println("Fichero: " + fichero + " existe");
			} else {
				existe = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return existe;
	}

	/**
	 * Se busca dentro de la carpeta ficheros\\ el nombre de los ficheros existentes y los imprime
	 */
	public void listarNombresFicheros() {

		File carpeta = new File(Menu.RUTA);
		String[] listado = carpeta.list();
		if (listado == null || listado.length == 0) {
			System.out.println("No hay elementos dentro de la carpeta actual");
			return;
		} else {
			for (int i = 0; i < listado.length; i++) {
				System.out.println("- " + listado[i]);
			}
		}
	}

//	public void listarPedidos(String nombreFile, String tipo) {
//
//		Pedido pedido = null;
//		File fichero = new File(Menu.RUTA + nombreFile + tipo);
//		String fichero2 = Menu.RUTA + nombreFile + tipo;
//
//		try {
//			FileInputStream fis = new FileInputStream(fichero);
//			ObjectInputStream ois = new ObjectInputStream(fis);
//
////			listar = ( ArrayList <Pedido> )ois.readObject();
////            ois.close();
////            
////            for(int i = 0; i < listar.size(); i++) {
////                System.out.println( "Producto[" + i + "] : " + listar.get(i) );
////            }
//			while (fis.available() > 0) {
//				pedido = new Pedido();
//				pedido = (Pedido) ois.readObject();
//				System.out.println(pedido);
//			}
//
//		} catch (IOException ioe) {
//			System.out.println("Error!!!! gestor.listarProductos");
//			ioe.getMessage();
//		} catch (ClassNotFoundException cc) {
//			System.out.println("No se ha encontrado la clase. gestor.listarPedidos");
//			cc.getMessage();
//
//		}
//
//	}
	
	/**
	 * Se pasa el ArrayList con los datos del objeto Pedido para meterlos al fichero,
	 * esto provocara un salto de linea en el fichero para cada objeto que metamos
	 * 
	 * @param pedidos ArrayList con el objeto para meter al fichero
	 * @param nombreFichero nombre del fichero a insertar datos
	 * @param tipo de fichero a insertar datos
	 */
	public void serializar(ArrayList<Pedido> pedidos, String nombreFichero, String tipo) {

		try {
			FileOutputStream fos = new FileOutputStream(Menu.RUTA + nombreFichero + tipo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pedidos);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Deserializo (paso de bits; datos en el fichero, a datos legibles) los datos de los objetos en el fichero y
	 * se lo traslado al ArrayList
	 * 
	 * @param nombreFichero nombre del fichero a deserializar
	 * @param tipo de fichero
	 * @return ArrayList con los datos del fichero
	 */
	public ArrayList<Pedido> deserializar(String nombreFichero, String tipo) {
		ArrayList<Pedido> pedidosDelFichero = new ArrayList<Pedido>();

		try {
			File fichero =new File(Menu.RUTA + nombreFichero + tipo);
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);

			pedidosDelFichero = (ArrayList<Pedido>) ois.readObject();

			ois.close();
			fis.close();
			
		} catch (NullPointerException npe) {
			System.out.println("NullException gestor mostrar");
			npe.getMessage();
			
		} catch (IOException ioe) {
			System.out.println("IOException gestor mostrar");
			ioe.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();

		}

		return pedidosDelFichero;
	}

}
