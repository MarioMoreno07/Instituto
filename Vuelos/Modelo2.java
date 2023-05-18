package Vuelos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * 
 * @author mario
 *
 */
public class Modelo2 {

	private TreeMap<Localidad, HashSet<Vuelo>> conexiones;
	private TreeSet<LineaAerea> lineas;
	/**
	 * 
	 */

	public Modelo2() {
		this.conexiones = new TreeMap<Localidad, HashSet<Vuelo>>();
		this.lineas = new TreeSet<LineaAerea>();
	}

	public void addLinea(LineaAerea linea) {
		this.lineas.add(linea);
	}
/**
 * Anade una localidad
 * @param localidad
 */
	public void addLocalidad(Localidad localidad) {

		conexiones.put(localidad, new HashSet<Vuelo>());

	}
/**
 *  Anade un vuelo a la localidad
 * @param localidad
 * @param vuelo
 */
	public void addVueloALocalidad(Localidad localidad, Vuelo vuelo) {
		if (conexiones.get(localidad) == (null)) {
			this.addLocalidad(localidad);
		}
		conexiones.get(localidad).add(vuelo);
		

	}
/**
 * Busca si hay errores
 * @return
 */
	public boolean hayErrores() {
		boolean errores = false;

		for (Localidad localidad : conexiones.keySet()) {
			HashSet<Vuelo> vuelos = conexiones.get(localidad);
			Iterator<Vuelo> it = vuelos.iterator();
			while (it.hasNext()) {
				Vuelo avion = it.next();
				if (avion.getDestino().equals(localidad)) {
					errores = true;
				}
			}
		}

		return errores;
		// devuelve un boolean indicando si hay errores en los datos, es decir,
		// si hay algún vuelo con la misma localidad de origen que de destino.

	}
	/**
	 * Mira los vuelos a ciudades con mas de un millon de habitantes
	 * @param loc
	 * @return
	 */
	public int numVuelosALocsMillon(Localidad loc) {
		int numMillon = 0;
		HashSet<Vuelo> vuelos = conexiones.get(loc);
		if (vuelos != null) {
			for (Vuelo vuelo : vuelos) {
				Localidad destino = vuelo.getDestino();
				if (destino.getHabitantes() > 1000000) {
					numMillon++;
				}
			}
		}

		return numMillon;
		// devuelve un entero con el número de vuelos que parten de la localidad
		// pasada como parámetro y llegan a localidades con más de 1.000.000 habs.

	}
	/**
	 * 
	 * @param localidad
	 * @return
	 */
	public TreeSet<LineaAerea> lineasHasta(Localidad localidad) {
		
		TreeSet<LineaAerea> lineasAereas=new TreeSet<>();
		for(HashSet<Vuelo>vuelos:conexiones.values()) {
			for(Vuelo vuelo:vuelos) {
				if(vuelo.getDestino().equals(localidad)) {
					lineasAereas.add(vuelo.getLinea());
				}
			}
			
		}
		
		
		return lineasAereas;
		// devuelve un TreeSet con todas las líneas aéreas que tienen vuelos hacia
		// la localidad pasada como parámetro
	}
	/**
	 * 
	 * @param localidad
	 * @return
	 */
	public int totalAvionesDesde(Localidad localidad) {
		int totalAviones=0;
		HashSet<Vuelo> vuelos=conexiones.get(localidad);
		if(vuelos!=null) {
			for(Vuelo vuelo:vuelos) {
				LineaAerea linea=vuelo.getLinea();
				totalAviones+=linea.getNumAviones();
						
			}
		}
		
		
		
		return totalAviones;
		// devuelve un entero con la suma de todos los aviones que tienen las líneas
		// que hacen vuelos desde la localidad pasada como parámetro.
	}

	public boolean hayVuelosReciprocos() {
		boolean hayVuelosReciprocos=false;
		for(Localidad localidad:conexiones.keySet()) {
		HashSet<Vuelo> vueloOrigen=conexiones.get(localidad);
		HashSet<Vuelo> vueloDestino=conexiones.get(localidad);
		Iterator <Vuelo> it=vueloOrigen.iterator();
		Iterator <Vuelo> it2=vueloDestino.iterator();
		while(it.hasNext()) {
			Vuelo avion1=it.next();
			while(it2.hasNext()) {
				Vuelo avion2=it.next();
				if(avion1.getDestino().equals(avion2.getDestino())) {
					hayVuelosReciprocos=true;
				}
			}
		}
		
		}
		return hayVuelosReciprocos;
		// devuelve un boolean indicando si existen dos ciudades entre las que hay
		// vuelos en los dos sentidos.
	}

	@Override
	public String toString() {
		String res = "";
		for (Localidad l : this.conexiones.keySet()) {
			res += "\nDesde: " + l.getNombre() + " hasta:\n";
			for (Vuelo v : this.conexiones.get(l))
				res += v.getDestino().getNombre() + " con " + v.getLinea() + ", ";
		}

		return res;
	}

	




	
}
