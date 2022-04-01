package es.elorrietaErrekamari.ficheroErreka.vista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import es.elorrietaErrekamari.ficheroErreka.controlador.Gestor;
import es.elorrietaErrekamari.ficheroErreka.modelo.Pedido;

public class Menu {

	Scanner teclado = new Scanner(System.in);
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();;
	public static final String RUTA = "ficheros\\";

	public Menu() {

	}

	public void empezarPrograma() {

		int opcion = 0;
		do {
			opcion = mostrarMenu();
			ejecutarOpcionMenu(opcion);
		} while (opcion != 0);
	}

	private void ejecutarOpcionMenu(int opcion) {

		switch (opcion) {
		case 0:
			System.out.println("Cerrando el programa. Adios");
			teclado.close();
			break;
		case 1:
			añadirPedido();
			break;
		case 2:
			mostrarPedido();
			System.out.println("TODO 2");/*con .dat hace bien, con .txt muestra todos los ficheros,
	 se sigue sin leer cuando arrancas || cambiar a leer .txt SI mete (tipo=.txt)*/
			break;
		case 3:
			/* modificarPedido(); */ System.out.println("TODO 3");
			break;
		case 4:
			 borrarFichero();  System.out.println("TODO 4");
			break;
		case 5:
			nuevoFichero();
			break;
		default:
			System.out.println("Opcion incorrecta.");
			opcion = 99;
		}

	}

	private void borrarFichero() {
		// TODO Auto-generated method stub
		
		Gestor gestor = new Gestor();

		System.out.println("Escriba el nombre del fichero con el que desea trabajar: ");
		String nombreFichero = teclado.nextLine();
		System.out.println("Indica el tipo de fichero: .txt(texto)   .dat(objetos)");
		String tipo = teclado.nextLine();

		if ((null != nombreFichero) && gestor.comprobarExistenciaFichero(nombreFichero, tipo)) {

			File archivo = new File(RUTA + nombreFichero + tipo);
			 archivo.delete();
			 System.out.println("El fichero ha sido borrado");
		}else {
			System.out.println("No existe el fichero seleccionado.");
		}
	}

	/**
	 * Muestra todos los pedidos de un fichero en concreto
	 */
	private void mostrarPedido() {
		// TODO Auto-generated method stub
		Gestor gestor = new Gestor();


		System.out.println("Escriba el nombre del fichero con el que desea trabajar: ");
		String nombreFichero = teclado.nextLine();
		System.out.println("Indica el tipo de fichero: .txt(texto)   .dat(objetos)");
		String tipo = teclado.nextLine();

		if ((null != nombreFichero) && gestor.comprobarExistenciaFichero(nombreFichero, tipo)) {
			
			for(Pedido pedido: gestor.deserializar(nombreFichero, tipo)) {
				System.out.println(pedido);
			}
		}else {
			System.out.println("El fichero no existe......");
		}
//		gestor.listarPedidos(nombreFichero, tipo);
	}

	/**
	 * Añade un Pedido al fichero
	 * 
	 * @param pedido
	 */
	private void añadirPedido() {

		Gestor gestor = new Gestor();
		String nombre = "";

		System.out.println("Escriba el nombre del fichero con el que desea trabajar: ");
		String nombreFichero = teclado.nextLine();
		System.out.println("Indica el tipo de fichero: .txt(texto)   .dat(objetos)");
		String tipo = teclado.nextLine();

		if ((null != nombreFichero) && gestor.comprobarExistenciaFichero(nombreFichero, tipo)) {
			try {

				FileOutputStream fileOutputStream = new FileOutputStream(RUTA + nombreFichero + tipo, true);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

				Pedido pedido = new Pedido();

				System.out.println("Introduzca nombre del producto: ");
				nombre = teclado.nextLine();
				pedido.setNombre(nombre);
				System.out.println("Introduzca la cantidad: ");
				int cantidad = Integer.parseInt(teclado.nextLine());
				pedido.setCantidad(cantidad);

				pedidos.add(pedido);
//				objectOutputStream.writeObject(pedido);

				fileOutputStream.close();
				objectOutputStream.close();

			} catch (NullPointerException npe) {
				npe.printStackTrace();
				npe.getMessage();
				System.out.println("esta vacio");
			}

			catch (IOException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("IO excepcion");
			}

			gestor.serializar(pedidos, nombreFichero, tipo);

		} else {
			System.out.println("El fichero es nulo.................");
		}

	}

	/**
	 * El usuario crea un nuevo fichero y elige la extension del mismo
	 */
	private void nuevoFichero() {

		System.out.println("¿Cómo se llamará el nuevo fichero?");
		String nombre = teclado.nextLine();
		System.out.println("Indica el tipo de fichero: .txt(texto)   .dat(objetos)");
		String tipo = teclado.nextLine();

		try {

			File archivo = new File(RUTA + nombre + tipo);

			if (!archivo.exists()) {
				archivo.createNewFile();
				System.out.println("El fichero se ha creado correctamente");
			} else {
				System.out.println("No hemos creado fichero, porque ya existia");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int mostrarMenu() {
		mostrarNombresFicheros();
		int opcion = 0;

		System.out.println("+-----------------------------+");
		System.out.println("|1- Añadir pedido             |");
		System.out.println("|2- Mostrar todos los pedidos |");
		System.out.println("|3- Modificar pedido actual   |");
		System.out.println("|4- Borrar fichero            |");
		System.out.println("|5- Nuevo fichero             |");
		System.out.println("|0- Salir                     |");
		System.out.println("+-----------------------------+\n");
		System.out.println("Elige una opcion: ");

		opcion = Integer.parseInt(teclado.nextLine());

		return opcion;
	}

	/**
	 * Muestra el nombre de todos los ficheros guardados
	 */
	private void mostrarNombresFicheros() {

		System.out.println("------------------------------");
		new Gestor().listarNombresFicheros();
		System.out.println("-------------------------------\n");
	}
	/*
	 * public String tecladoString() { String ret = ""; boolean error = false; do {
	 * try { ret = teclado.nextLine(); } catch (Exception e) { teclado.nextLine();
	 * teclado.reset(); error = true; } } while (error == true); return ret; }
	 */
	/*
	 * public int tecladoInt() { int retInt = 0; try { retInt = teclado.nextInt(); }
	 * catch (Exception e) { teclado.nextLine(); teclado.reset(); retInt = -1; }
	 * return retInt; }
	 */
}
