import java.util.ArrayList;

public class Asignatura {
 public String nombre;
 public String horas;
 ArrayList<Profesor> profesor = null;
 


public Asignatura(String nombre, String horas, ArrayList<Profesor> profesor) {
	super();
	this.nombre = nombre;
	this.horas = horas;
	this.profesor = profesor;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getHoras() {
	return horas;
}

public void setHoras(String horas) {
	this.horas = horas;
}

public ArrayList<Profesor> getProfesor() {
	return profesor;
}

public void setProfesor(ArrayList<Profesor> profesor) {
	this.profesor = profesor;
}

@Override
public String toString() {
	return "Asignatura [nombre=" + nombre + ", horas=" + horas + "profesor="+ mostrarArrayList(profesor) + "]";
}

public StringBuilder mostrarArrayList(ArrayList<Profesor> aProfesor) {
	StringBuilder sb = new StringBuilder();
    
    for(Profesor aut : aProfesor) {
    	sb.append(aut.toString());
    }
	
	return sb;
}
 
}
