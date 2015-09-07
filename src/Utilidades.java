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
import java.util.GregorianCalendar;
import java.util.Calendar;

class Utilidades
{
	public Utilidades(){}

	/**
	* Transforma una cadena de 81 numeros en una matriz de 9x9 
	* El orden de los numeros se corresponde con el indice en la matriz
        *
	* forma de la cadena recivida.
	*
	*     123456789....n (81 elementos)
	*  -> ^^^          ^ 
	*  -> 012          81
	*  
	*Forma de la matriz resultado
	*
        *    
     	*  |-1-|-2-|-3-|-4-|-5-|-6-|-7-|-8-|-9-|  
     	*  |-10|11-|- -|- -|- -|- -|- -|- -|- -|  
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|- -|
     	*  |- -|- -|- -|- -|- -|- -|- -|- -|-N-|
	*
	**/
	public static int[][] toMatrix(String cadena)
	{
	  int contador=0;
	  int matrix[][]=new int[9][9];

		//if(cadena.length() == 81){
			for(int j=0; j< 9; j++)
			   for(int i=0; i < 9 ; i++)
				{
				try{
				matrix[j][i]=Integer.parseInt(cadena.charAt(contador++)+"");
				}catch(Exception e){System.out.println("ups. no es numero");}
				}
		//}
	  return matrix;
	}
        /**
	* Entrega una cadena con el tiempo entre los 2 tiempos de los argumentos.
	**/
	public static String DiferenciaDeTiempo(GregorianCalendar antes,GregorianCalendar despues)
	{
	 	int antesEnMili=antes.get(Calendar.MILLISECOND)+
				antes.get(Calendar.SECOND)*1000+
				antes.get(Calendar.MINUTE)*60*1000+
				antes.get(Calendar.HOUR)*3600*1000;

		int despuesEnMili=despues.get(Calendar.MILLISECOND)+
				  despues.get(Calendar.SECOND)*1000+
				  despues.get(Calendar.MINUTE)*60*1000+
				  despues.get(Calendar.HOUR)*3600*1000;
		
		int totalEnMili=despuesEnMili-antesEnMili;
		
		
		if(totalEnMili < 1000){
			return totalEnMili+"ms";
		}
		else if(totalEnMili >= 1000 && totalEnMili < 60*1000)
			{
			return totalEnMili/1000+"."+totalEnMili%1000+" segundos.";
			}
		     else{
			return totalEnMili/(3600*1000)+":"+
			      (totalEnMili-totalEnMili/(3600*1000))/1000+"."+
			      (totalEnMili-(totalEnMili-totalEnMili/(3600*1000))/1000);
			}
	}
}
