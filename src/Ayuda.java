/********************************************************************
 *     Lacosoft SUDOKU SOLVER adictive Edition v0.1
 ********************************************************************
 *
 * Intento 1 para resolver el SUDOKU por un minimo intento de mezclar  
 * fuerza bruta con IA
 * @autor : Danilo Lacoste \n
 *	    dalacost@gmail.com \n
 *          http://danilote.blogsite.org
 *   
 * fecha primera version: 14/02/07
 *
 */
import java.io.*;

class Ayuda
{
 	public Ayuda(){}
	public static String getAyuda()
	{
		return ayudaText;	
	}
	public static String getAbout()
	{
		return aboutText;
	}

public static String ayudaText= "AYUDA \n"+
				"=======\n"+
				"\n"+
				" Soduko Solver Adictive Edition es una aplicacion para resolver problemas de Soduko, relativamente complejos. \n"+
				" \n"+
				" Opciones \n"+
				" =========\n\n"+
				" --help\t Muestra esta ayuda.\n"+
				" -m \n"+
				" --matriz\t Cualesquiera de las 2 opciones se pueden usar para indicar la matriz que representa el problema,\n"+
				"         \t donde existan espacios indique un 0,por ejemplo: \n"+
				"	  \t   para resolver el problema  \n"+
				"	  \t                              | | | |  | | | |  | | | |\n"+
				"         \t                              | |5| |  | |2| |  | | | |\n"+
				"         \t                              |8|1|7|  |5| | |  | |2| |\n\n"+
				"         \t                              | | |8|  | | | |  |7| | |\n"+
				"         \t                              | |6| |  |8| | |  |1| | |\n"+
				"         \t                              | |4| |  | | | |  |8| | |\n\n"+
				"         \t                              | |7| |  |2| |4|  |3| |5|\n"+
				"         \t                              | | |4|  | | |7|  | | |1|\n"+
				"         \t                              |6| | |  |3|1| |  | | | |\n\n"+
				"         \t se debe indicar de la siguiente forma:\n"+
				"	  \t -m 000000000050020000817500020008000700060800100240000800070204305004007001600310000\n\n"+
				" -nmt \n"+
				" --NoMostrarTiempo\n"+
				" --NoShowTime \t Cualesquiera de las 3 opciones se puede utilizar para indicar \n"+
				"              \t que no se desea que se informe sobre el tiempo tardado en resolver \n"+
				"              \t el problema.\n"+
				" --about \t Indica algunos datos sobre esta aplicacion y su creador.\n";

public static String aboutText=" Acerca de Soduko Solver Adictive Edition \n"+
			       "==========================================\n\n"+
			       " Agradecimientos especiales al tiempo libre :) \n"+
		 	       "Programador: Danilo Lacoste danilo@lacosox.org  http://www.lacosox.org\n\n"+
			       "Soduko Solver Adictive Edition es una aplicacion para resolver problemas de Soduko, relativamente complejos. \n"+
			       " buscando la solucion en forma no tan rapida y no tan eficientemente.";

}
