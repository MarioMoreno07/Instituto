package Examen;

import java.util.Scanner;

public class Examen {

	public static void main(String[] args) {
		String tipoCuerpoCeleste;
		String nombreCuerpoCeleste;
		String nombreSatelite;
		String nombreMasCercano = "";
		String nombreMasSatelites = "";
		String nombreMasSatelites2 = "";
		double distanciaSol;
		int numSatelites;
		int diametroCuerpoCeleste;
		int diametroSatelite;
		float contadorDiametro = 0;
		int contadorPlanetas = 0;
		int contadorPlanetasSatelites = 0;
		float contadorCuerpoCeleste = 0;
		float contadorSatelites = 0;
		int diametroMasGrande = Integer.MIN_VALUE;
		int maxSatelites = Integer.MIN_VALUE;
		int maxSatelites2 = Integer.MIN_VALUE;
		double masCercano = Double.MAX_VALUE;
		char rotacionSincrona;
		int satelitesRotacionSincrona = 0;
		boolean rotacion = false;

		Scanner sc = new Scanner(System.in);
		System.out.println("De que cuerpo celeste se trata : estrella ,planta o agujero");
		tipoCuerpoCeleste = sc.next();
		while (!tipoCuerpoCeleste.equals("fin")) {
			contadorCuerpoCeleste++;
			System.out.println("Nombre del cuerpo celeste");
			nombreCuerpoCeleste = sc.next();
			System.out.println("Distancia al sol");
			distanciaSol = sc.nextDouble();
			System.out.println("Dime el diametro");
			diametroCuerpoCeleste = sc.nextInt();
			contadorDiametro = contadorDiametro + diametroCuerpoCeleste; // utilizo la variable para almacenar el
																			// diametro
			if (distanciaSol < masCercano) {
				masCercano = distanciaSol;
				nombreMasCercano = nombreCuerpoCeleste;
			}
			if (distanciaSol < 230000000) { // Acoto solo a los que que la distancia al sol es menor que la indicada
				if (diametroCuerpoCeleste > diametroMasGrande) {
					diametroMasGrande = diametroCuerpoCeleste;
				}
			}
			if (tipoCuerpoCeleste.equals("planeta") || tipoCuerpoCeleste.equals("estrella")) {
				if (tipoCuerpoCeleste.equals("planeta")) {
					contadorPlanetas++;
				}
				System.out.println("Cuantos satelites tiene");
				numSatelites = sc.nextInt();
				contadorSatelites = contadorSatelites + numSatelites;
				if (tipoCuerpoCeleste.equals("planeta") && numSatelites > 0) {
					contadorPlanetasSatelites++;
				}
				for (int i = 1; i <= numSatelites; i++) {
					System.out.println("Dime el nombre del satelite");
					nombreSatelite = sc.next();
					System.out.println("Dime el diametro");
					diametroSatelite = sc.nextInt();
					contadorDiametro = contadorDiametro + diametroSatelite;// utilizo la misma variable para tener todos
																			// los diametros en una sola variable
					System.out.println("¿Tiene rotacion sincrona?");
					rotacionSincrona = sc.next().charAt(0);

					if (rotacionSincrona == 'S') {
						satelitesRotacionSincrona++;
						rotacion = true;
					} else { // Utilizo un contador para saber si algun satelite tiene rotacion Sincrona
						rotacion = false;
					}
					if (numSatelites >= 1) {
						if (satelitesRotacionSincrona >= 1) {
							System.out.println("f) " + nombreCuerpoCeleste + " tiene satelites con rotacion sincrona: "
									+ rotacion);
						}
					}
				}
				if (numSatelites > maxSatelites) {
					maxSatelites2 = maxSatelites; // Paso el numero de satelites del max1 al max2 para no perder el dato
					maxSatelites = numSatelites; // Actualizo el dato de max1
					nombreMasSatelites2 = nombreMasSatelites; // Hago los mismo con los nombres que con la cantidad
					nombreMasSatelites = nombreCuerpoCeleste;
				} else if (numSatelites > maxSatelites2) {
					maxSatelites2 = numSatelites; // Actualizo el valor de max2 de nombre y de numero de satelites
					nombreMasSatelites2 = nombreCuerpoCeleste;
				}

			}
			System.out.println("De que cuerpo celeste se trata : estrella ,planta o agujero");
			tipoCuerpoCeleste = sc.next();
		}
		if (contadorPlanetas >= 1) {
			System.out.println("a) El porcentaje de planetas con satelites es: "
					+ (100 * contadorPlanetasSatelites) / contadorPlanetas + " %");
		} else {
			System.out.println("No se han introducido planetas");
		}
		System.out.println("b) El cuerpo celeste mas cercano es: " + nombreMasCercano);
		System.out.println("c) El diametro medio de todos los cuerpos celestes es: "
				+ contadorDiametro / (contadorSatelites + contadorCuerpoCeleste) + " Kms");
		System.out.println("d) Los nombres de los dos cuerpos celestes que mas satelites tienen es: "
				+ nombreMasSatelites + " y " + nombreMasSatelites2);
		System.out.println(
				"e) El diametro mas grande de los que estan a menos de 230000000 del sol es: " + diametroMasGrande);
	}

}
