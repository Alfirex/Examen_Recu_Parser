
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Parser {
	
	private Document dom = null;
	private ArrayList<Asignatura> aAsignaturas = null;

	public Parser() {
		aAsignaturas = new ArrayList<Asignatura>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();
		String textVal = null;
		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("asignatura");
			
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				
				// obtenemos un elemento de la lista (Libro)
				Element el = (Element) nl.item(i);
				
		
		     
				    
				// obtenemos un objeto libro
				Asignatura a = getAsignatura(el);

				// lo añadimos al array
				aAsignaturas.add(a);
				
			}
		}
		  
	}
	
	/**
	 * Esta funcion se encargara de Obtener todas las acciones
	 * @param libro
	 * @return
	 */
	 private Asignatura getAsignatura(Element libro){
		// Llamamos al método para sacar varios valores de ahí:
	    String nombre = getTextValue(libro,"nombre");
	    String horas = getTextValue(libro,"horas");
	   
	    ArrayList<Profesor> oProfesor = getProfesor(libro, "profesor");
	    // Creamos un objeto libro con estos datos y lo devolvemos:
	    Asignatura oAccion = new Asignatura(nombre, horas, oProfesor);
	    return oAccion; 
		 
		 }
	  
	  
	  /**
	   *  Valor dentro de la respectiva etiqueta. 
	   */
	  private String getTextValue(Element ele, String tagName) {
	    String textVal = null;
	    NodeList nodeList = ele.getElementsByTagName(tagName);
	    
	    // Si el nodo tiene nombre o contenido:
	    if(nodeList != null && nodeList.getLength() > 0) {
	      Element element = (Element)nodeList.item(0);
	      textVal = element.getFirstChild().getNodeValue();
	    }  
	    
	    return textVal;
	  }
	  
	
	  /**
	   * Funcion  para obtener los campos de Compras
	   */
	  public ArrayList<Profesor> getProfesor(Element ele, String tagName){
			 ArrayList<Profesor> alProfesor = new ArrayList<>();
			 ArrayList<String> alNombre = new ArrayList<>();
			 ArrayList<String> alDespacho = new ArrayList<>();
			 Profesor oProfesor;
			 String sNombre, sDespacho="";
			 
			 alNombre = getNombreProfesorElemento(ele, tagName);// coge el nombre de cada etiqueta y lo guarda en un ArrayList
			 alDespacho = getDespachoProfesorElemento(ele, tagName);// coge el apellidos de cada etiqueta y lo guarda en un ArrayList
			 
			 for(int i = 0; i < alNombre.size(); i++) {//,size es el .length de array list
				 sNombre = alNombre.get(i);//obtiene el nombre de esa posicion
				 if (alDespacho.size() > 0) {
					 sDespacho = alDespacho.get(i);
				 }else {
					 sDespacho = "vacio";
				 }
				
				 
				 oProfesor = new Profesor(sNombre, sDespacho);//Creamos el objeto autor y lo almacenamos
				
				 alProfesor.add(oProfesor);//  le pasamos el objeto Autor y que lo aguarde en el array List 
			 }
		
			 return alProfesor;
		 }
	
	// Recoge Cantidad. 
		 private ArrayList<String> getNombreProfesorElemento(Element ele, String tagName) {//tagName == <autores>
			
				NodeList nodeList = ele.getElementsByTagName(tagName);
				ArrayList<String> alNombre = new ArrayList<>();
				String sProfesor = "";// para que no sale null  
				
				if (nodeList != null && nodeList.getLength() > 0) {// Comprobar que no esta vacio
					Element el = (Element) nodeList.item(0);// pilla la primera etique de autores
					
					NodeList nlCantidad = el.getElementsByTagName("nombre");// Vamos a por la etiqueta nombre
					
					if (nlCantidad != null && nlCantidad.getLength() > 0) {// si hay algo en la etiqueta nombre que recorra todo lo que hay aen la etiqueta
						for (int i=0; i < nlCantidad.getLength(); i++) {
								Element eNom = (Element) nlCantidad.item(i);
								
								sProfesor = eNom.getFirstChild().getTextContent();//obtenemos su valor
								alNombre.add(sProfesor);// añadimos al array list
							}
					}
				}
				return alNombre;			
		}
		// Recoge Precio. 
				 private ArrayList<String> getDespachoProfesorElemento(Element ele, String tagName) {//tagName == <autores>
					
						NodeList nodeList = ele.getElementsByTagName(tagName);
						ArrayList<String> alDespacho = new ArrayList<>();
						String sProfesor = "";// para que no sale null  
						
						if (nodeList != null && nodeList.getLength() > 0) {// Comprobar que no esta vacio
							Element el = (Element) nodeList.item(0);// pilla la primera etique de autores
							
							NodeList nlCantidad = el.getElementsByTagName("despacho");// Vamos a por la etiqueta nombre
							
							if (nlCantidad != null && nlCantidad.getLength() > 0) {// si hay algo en la etiqueta nombre que recorra todo lo que hay aen la etiqueta
								for (int i=0; i < nlCantidad.getLength(); i++) {
										Element eNom = (Element) nlCantidad.item(i);
										
										sProfesor = eNom.getFirstChild().getTextContent();//obtenemos su valor
										
										
											alDespacho.add(sProfesor);// añadimos al array list
										
										
									}
							}
						}
						return alDespacho;			
				}
	

	  
	
	  /**
	   * Print de libros
	   */
	  public void printLibro() {
		Iterator<Asignatura> it = aAsignaturas.iterator();
		StringBuilder sb = new StringBuilder();
		
		while(it.hasNext()) {
			Asignatura a = it.next();
		 // Llama al método toString() de libro para sacar el formato deseado:
	     sb.append(a.toString() + "\n\n");
	    }
		System.out.println(sb);;	
	  }


}