package Juego;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vida extends JFrame {

	/*
	 * Juego de la vida 
	 * Elaborado por: Cristian R. Gómez González
	 * cristiangom@hotmail.com
	*/
	
	JPanel panel; 
	boolean vida = true; 
	static int[][] arreglo = new int[36][15];
	int[][] temporal = new int[36][15];
	int x, y, cont;
	public static int tamañox = arreglo.length;
	public static int tamañoy = arreglo[1].length;
	JButton Lista[][];
	int Tamaño = 35;

	public Vida() {
		panel = new JPanel(null);

		llenar(arreglo); // Agrega valores a arreglo
		llenar(temporal); // Agrega valores a temporal(arreglo)

		valoresIniciales(); //Agregamos algunos valores definidos

		actualizar(); //Actualizamos nuestro arreglo principal
		// imprimir(arreglo); //Descomentar si queremos que imprima en consola
		interfaz(); // imprime en ventana

		//Creamos configuración de nuestro frame
		setTitle("Juego de la VIDA");
		setContentPane(panel);
		setVisible(true);
		setSize(1260, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Ciclo while para los turnos
		while (vida == true) {

			izquierda(); //Calcula valores de las celdas de la izquierda (x=0,recorre y)
			derecha(); //Calcula valores de las celdas de la derecha (ultima x,recorre y)
			arriba(); //Calcula valores de las celdas de arriba (y=0, recorre x)
			abajo(); //Calcula valores de las celdas de abajo (ultima y, recorre x)
			calculoCentral(); //Calculas todos los demas valores del centro
			actualizar(); // Actualizamos arreglo principal
			// imprimir(arreglo);
			panel.removeAll(); //Remueve el panel
			interfaz(); //imprime en ventana
			panel.repaint(); //pinta el panel
			esperar(1); //espera un tiempo al final antes de repetir
			// vida = false; //descomentar en caso de que se quiera repetir una vez

		}

	}

	//Cuenta las celdas con vida de los lados
	
	public void calculoCentral() {
		//checa las celdas de arriba,abajo y las dos de a lado
		
		for (int y = 1; y < tamañoy - 1; y++) {
			for (int x = 1; x < tamañox - 1; x++) {
				cont = 0;
				for (int i = 0; i < 3; i++) {
					if (arreglo[x - 1][y - 1 + i] == 1)
						cont++;
				}
				for (int i = 0; i < 3; i++) {
					if (arreglo[x + 1][y - 1 + i] == 1)
						cont++;
				}
				for (int i = 0; i < 3; i += 2) {
					if (arreglo[x][y - 1 + i] == 1)
						cont++;
				}
				determinar(x, y);
			}
		}
	}

	public void valoresIniciales() {

		//Aqui declaramos algunos valores iniciales con vida
		//Caso particular: Pistola de planeadores de Gosper (Gosper Glider Gun)
		//Puede modificar con diferentes valores iniciales
		
		
		temporal[0][4] = 1;
		temporal[0][5] = 1;
		temporal[1][4] = 1;
		temporal[1][5] = 1;
		temporal[10][4] = 1;
		temporal[10][5] = 1;
		temporal[10][6] = 1;
		temporal[11][3] = 1;
		temporal[11][7] = 1;
		temporal[12][2] = 1;
		temporal[12][8] = 1;
		temporal[13][2] = 1;
		temporal[13][8] = 1;
		temporal[14][5] = 1;
		temporal[15][3] = 1;
		temporal[15][7] = 1;
		temporal[16][4] = 1;
		temporal[16][5] = 1;
		temporal[16][6] = 1;
		temporal[17][5] = 1;
		temporal[20][2] = 1;
		temporal[20][3] = 1;
		temporal[20][4] = 1;
		temporal[21][2] = 1;
		temporal[21][3] = 1;
		temporal[21][4] = 1;
		temporal[22][1] = 1;
		temporal[22][5] = 1;
		temporal[24][0] = 1;
		temporal[24][1] = 1;
		temporal[24][5] = 1;
		temporal[24][6] = 1;
		temporal[34][2] = 1;
		temporal[34][3] = 1;
		temporal[35][2] = 1;
		temporal[35][3] = 1;

	}

	public void izquierda() {

		for (int y = 0; y < tamañoy; y++) {
			for (int x = 0; x < 1; x++) {
				if (x == 0 && y == 0) {// Esquina izquierda arriba
					cont = 0;
					for (int i = 0; i < 2; i++) {
						if (arreglo[x + 1][y + i] == 1)
							cont++;
					}
					for (int i = 0; i < 1; i++) {
						if (arreglo[x][y + 1] == 1)
							cont++;
					}

					determinar(x, y);
				}
				if (x == 0 && y == tamañoy - 1) {// Esquina izquierda abajo
					cont = 0;
					for (int i = 0; i < 2; i++) {
						if (arreglo[x + 1][y + i - 1] == 1)
							cont++;
					}
					for (int i = 0; i < 1; i++) {
						if (arreglo[x][y - 1] == 1)
							cont++;
					}

					determinar(x, y);
				}
				if (x == 0 && y > 0 && y < tamañoy - 1) {// Borde izquierdo
					cont = 0;
					for (int i = 0; i < 3; i++) {
						if (arreglo[x + 1][y + i - 1] == 1)
							cont++;
					}
					for (int i = 0; i < 3; i += 2) {
						if (arreglo[x][y - 1 + i] == 1)
							cont++;
					}

					determinar(x, y);
				}
			}
		}
	}

	public void derecha() {

		for (int y = 0; y < tamañoy; y++) {
			for (int x = tamañox - 1; x < tamañox; x++) {
				if (x == tamañox - 1 && y == 0) {// Esquina derecha arriba
					cont = 0;
					for (int i = 0; i < 2; i++) {
						if (arreglo[x - 1][y + i] == 1)
							cont++;
					}
					for (int i = 0; i < 1; i++) {
						if (arreglo[x][y + 1] == 1)
							cont++;
					}

					determinar(x, y);
				}
				if (x == tamañox - 1 && y == tamañoy - 1) {// Esquina derecha
															// abajo
					cont = 0;
					for (int i = 0; i < 2; i++) {
						if (arreglo[x - 1][y + i - 1] == 1)
							cont++;
					}
					for (int i = 0; i < 1; i++) {
						if (arreglo[x][y - 1] == 1)
							cont++;
					}

					determinar(x, y);
				}
				if (x == tamañox - 1 && y > 0 && y < tamañoy - 1) {// Borde
																	// derecho
					cont = 0;
					for (int i = 0; i < 3; i++) {
						if (arreglo[x - 1][y + i - 1] == 1)
							cont++;
					}
					for (int i = 0; i < 3; i += 2) {
						if (arreglo[x][y - 1 + i] == 1)
							cont++;
					}

					determinar(x, y);
				}
			}
		}

	}

	public void arriba() {
		for (int x = 0; x < tamañox; x++) {
			for (int y = 0; y < 1; y++) {
				if (y == 0 && x > 0 && x < tamañox - 1) {// Borde arriba
					cont = 0;
					for (int i = 0; i < 3; i++) {
						if (arreglo[x + i - 1][y + 1] == 1)
							cont++;
					}
					for (int i = 0; i < 3; i += 2) {
						if (arreglo[x - 1 + i][y] == 1)
							cont++;
					}

					determinar(x, y);
				}
			}
		}
	}

	public void abajo() {
		for (int x = 1; x < tamañox - 1; x++) {
			for (int y = tamañoy - 1; y < tamañoy; y++) {
				cont = 0;
				for (int i = 0; i < 3; i++) {
					if (arreglo[x + i - 1][y - 1] == 1)
						cont++;
				}
				for (int i = 0; i < 3; i += 2) {
					if (arreglo[x - 1 + i][y] == 1)
						cont++;
				}

				determinar(x, y);

			}
		}
	}

	public void esperar(int cantidad) {
		try {
			Thread.sleep(cantidad * 500);//trabaja con milisegundos
		} catch (Exception e) {
			// Mensaje en caso de que falle
		}
	}

	public void llenar(int[][] arreglos) {
		//Llena el arreglo con valor = 0
		for (int y = 0; y < tamañoy; y++) {
			for (int x = 0; x < tamañox; x++) {
				arreglos[x][y] = 0;

			}
		}
	}

	public void actualizar() {
		//Para pasar los valores de nuestro arreglo temporal a arreglo(principal)
		for (int y = 0; y < tamañoy; y++) {
			for (int x = 0; x < tamañox; x++) {
				arreglo[x][y] = temporal[x][y];

			}
		}
	}

	public void imprimir(int[][] arreglos) {
		//Para imprimir en consola con los valores del arreglo, 0 = muerto, 1 con vida
		for (int y = 0; y < tamañoy; y++) {
			for (int x = 0; x < tamañox; x++) {
				System.out.print(arreglos[x][y] + "  ");
			}
			System.out.println("\n");
		}
	}

	public void determinar(int x, int y) {
		//Le llega la información de las celdas vivas  
		//y determina si esta nace, vive o muere
		
		if (arreglo[x][y] == 0 && cont == 3) {
			temporal[x][y] = 1;
		}
		if (arreglo[x][y] == 1 && (cont < 2 || cont > 3)) {
			temporal[x][y] = 0;
		}
	}

	public void interfaz() {
		//imprime el juego en ventana
		//blanco = vivo, negro = muerta
		Lista = new JButton[36][15];

		for (int x = 0; x < tamañox; x++) {
			for (int y = 0; y < tamañoy; y++) {
				Lista[x][y] = new JButton();
				Lista[x][y].setBounds(x * Tamaño, y * Tamaño, Tamaño, Tamaño);

				if (arreglo[x][y] == 0) {
					Lista[x][y].setBackground(java.awt.Color.black);
				} else {
					Lista[x][y].setBackground(java.awt.Color.white);
				}
				panel.add(Lista[x][y]);

			}
		}
	}

	public static void main(String[] args) {

		new Vida();

	}
}
