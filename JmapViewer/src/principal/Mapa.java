package principal;

import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import clustering.Cluster;

public class Mapa {
	private JMapViewer mapa;
	
	public Mapa(){
		mapa = new JMapViewer();
		Coordinate bsAs = new Coordinate (-34.5237,-58.7038);
		mapa.setDisplayPosition(bsAs, 9);
		mapa.setBounds(0, 0, 500, 500);
		mapa.setVisible(true);
	}
	
	public void dibujarLinea(List<Coordinate>coordenadas,int i,int j) {
			mapa.addMapPolygon(
					new MapPolygonImpl( 
							new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon()),
							new Coordinate(coordenadas.get(j).getLat(),coordenadas.get(j).getLon()),
							new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon())));
	}
	
	public void agregarMarcas(List<Coordinate>coordenadas, List<Cluster> clusters) {
		for(Cluster cluster : clusters) {
			for(Integer vertice : cluster.getVertices()) {
				MapMarker marker = new MapMarkerDot(
						cluster.getColor(),
						coordenadas.get(vertice).getLat(),
						coordenadas.get(vertice).getLon());
						mapa.addMapMarker(marker);
			}		
		}
	}
	
	public JMapViewer getMap() {
		return mapa;
	}
	
	public void borrarGrafo() {
		mapa.removeAllMapPolygons();
		mapa.removeAllMapMarkers();
	}
	
	public void agregarMacador(Coordinate b) {
		mapa.addMapMarker(new MapMarkerDot(b.getLat(),b.getLon()));
	}
	
	public void eliminarMarcador(Coordinate b) {
		List<MapMarker> marcas=mapa.getMapMarkerList();
		if(marcas.size()!=0) 
			mapa.removeMapMarker(marcas.get(marcas.size()-1));
	}

	public void agregarMarcas(List<Coordinate> coordenadas) {
		for(Coordinate coordenada : coordenadas) 
			mapa.addMapMarker(new MapMarkerDot(coordenada.getLat(),coordenada.getLon()));
	}	
}
