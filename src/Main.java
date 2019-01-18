public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser=new Parser();
		parser.parseFicheroXml("asignaturas.xml");
		parser.parseDocument();
		parser.printLibro();
	}


}
