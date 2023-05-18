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
public class Modelo {

	private TreeMap<Localidad, HashSet<Vuelo>> conexiones;
	private TreeSet<LineaAerea> lineas;

	public Modelo() {
		this.conexiones = new TreeMap<Localidad, HashSet<Vuelo>>();
		this.lineas = new TreeSet<LineaAerea>();
	}

	public void addLinea(LineaAerea linea) {
		this.lineas.add(linea);
	}

	/**
	 * 
	 * @param localidad
	 */
	public void addLocalidad(Localidad localidad) {
		conexiones.put(localidad, new HashSet<Vuelo>());
		}
		
	/**
	 * 
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
	 * 
	 * @return
	 */
	public boolean hayErrores() {
		boolean mismodestino = false;
		for (Localidad localidad : conexiones.keySet()) {
			HashSet<Vuelo> vuelos = (conexiones.get(localidad));
			Iterator<Vuelo> it = vuelos.iterator();
			while (it.hasNext()) {
				Vuelo avion = it.next();
				if (avion.getDestino().equals(localidad)) {
					mismodestino = true;
				}
			}
		}

		return mismodestino;
	}
	/**
	 * 
	 * @param loc
	 * @return
	 */
	public int numVuelosALocsMillon(Localidad loc) {
		int vuelosMillon = 0;
		HashSet<Vuelo> vuelos = conexiones.get(loc);
		if (vuelos != null) {
			for (Vuelo vuelo : vuelos) {
				Localidad destino = vuelo.getDestino();
				if (destino.getHabitantes() > 1000000) {
					vuelosMillon++;
				}
			}
		}
		return vuelosMillon;
	}
	/**
	 * 
	 * @param localidad
	 * @return
	 */
	public TreeSet<LineaAerea> lineasHasta(Localidad localidad) {

		TreeSet<LineaAerea> lineasAereas = new TreeSet<>();
		
		for(HashSet<Vuelo> vuelos:conexiones.values()) {
			for (Vuelo vuelo : vuelos) {
				if(vuelo.getDestino().equals(localidad)) {
				lineasAereas.add(vuelo.getLinea());
			}
		}
		}

		return lineasAereas;
	}
	/**
	 * 
	 * @param localidad
	 * @return
	 */
	public int totalAvionesDesde(Localidad localidad) {
		int totalAviones = 0;
		HashSet<Vuelo> vuelos = conexiones.get(localidad);
		if (vuelos != null) {
			for (Vuelo vuelo : vuelos) {
				LineaAerea linea = vuelo.getLinea();
				totalAviones += linea.getNumAviones();
			}
		}
		return totalAviones;
	}
	/**
	 * 
	 * @return
	 */
	public boolean hayVuelosReciprocos() {

		boolean vuelosReciprocos = false;
		for (Localidad localidad : conexiones.keySet()) {
			HashSet<Vuelo> vuelosOrigen = (conexiones.get(localidad));
			HashSet<Vuelo> vuelosDestino = (conexiones.get(localidad));
			Iterator<Vuelo> it = vuelosOrigen.iterator();
			Iterator<Vuelo> it2 = vuelosDestino.iterator();
			while (it.hasNext()) {
				Vuelo avion = it.next();
				while (it2.hasNext()) {
					Vuelo avion2 = it2.next();
					if (avion.getDestino().equals(avion2.getDestino())) {
						vuelosReciprocos = true;
					}
				}
			}

		}

		return vuelosReciprocos;

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
