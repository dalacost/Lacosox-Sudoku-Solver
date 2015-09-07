/********************************************************************
 *     Lacosoft SUDOKU SOLVER adictive Edition v0.1
 ********************************************************************
 *
 * Intento 1 para resolver el SUDOKU por un minimo intento de mezclar  
 * fuerza bruta con IA
 * @autor : Danilo Lacoste \n
 *	    danilo@lacosox.org \n
 *          www.lacosox.org
 *          http://danilote.lacosox.org
 * fecha primera version: 14/02/07
 *
 */

import java.io.*;
import java.util.GregorianCalendar;
import java.util.Calendar;

//class FuerzaBruta1 {
class SudokuSolverAdictiveEdition{

public static int[][] test2={{0,0,2, 7,0,0, 0,6,0},//modo dificil segun diario "La Tercera"
			    {0,0,0, 0,0,4, 0,0,0},
			    {6,7,0, 0,8,0, 4,0,0},

			    {0,2,0, 0,0,0, 0,0,0},
			    {0,0,3, 8,1,0, 5,7,0},
			    {8,0,0, 0,5,3, 0,0,0},

			    {5,0,0, 0,6,0, 0,0,0},
			    {0,0,8, 3,0,0, 0,0,0},
			    {0,0,0, 1,2,0, 0,8,0}};

public static int[][] test={{0,0,0, 0,0,0, 0,0,0},//modo dificil segun diario "La Tercera"
			    {0,5,0, 0,0,0, 0,0,0},
			    {8,1,0, 0,0,0, 0,0,0},

			    {0,0,8, 0,0,0, 0,0,0},
			    {0,6,0, 0,0,0, 0,0,0},
			    {2,4,1, 0,0,0, 0,0,0},

			    {1,7,0, 2,0,4, 0,0,0},
			    {0,3,4, 0,0,7, 0,0,0},
			    {6,2,0, 3,1,0, 0,0,0}};

public static boolean terminado=false;
public static int iteraciones =0;
public static boolean NoMostrarTiempo=false;
	/*
	* Valida Si la Matriz cumple las reglas.
	* Reglas: - El mismo numero no puede repetirse el la misma fila
	*         - ni en la misma columna,
	*	  - ni el mismo grupo de 9 numeros
	*         - solo se pueden usar numeros del 1 al 9
        * @ return 
	*      true: la matriz es valida para poder resolverla
	*      false: no es valida, no se puede resolver, con los elementos indicados.
	*/
	public static boolean validar(int[][] matrix){
            boolean retorno = false;
	 
           if(matrix != null)
	   if(	 matrix.length==9 && //la matriz debe ser de 9x9
		 matrix.length==9 &&
		 VerificarGrupo(matrix,0) && //verificar si matriz esta bien echa
		 VerificarGrupo(matrix,1) && // y no se repite ningun numero en 
		 VerificarGrupo(matrix,2) && // ningun grupo de 3x3
		 VerificarGrupo(matrix,3) &&
		 VerificarGrupo(matrix,4) &&
		 VerificarGrupo(matrix,5) &&
		 VerificarGrupo(matrix,6) &&
		 VerificarGrupo(matrix,7) &&
		 VerificarGrupo(matrix,8) &&
	         !RepetidoEnFilaOColumna(matrix)     //verifica si los elementos no se repiten
					            // en la misma fila ni en la misma columna
              )
	       { 
		       retorno=true;
	       }
	 return retorno;          
	}

	public static void imprimirEstado(int[][] matrix)
	{
 	 for(int t=0; t< matrix.length; t++)
           {
                for(int y=0; y< matrix[t].length; y++)
                   {String agregado=" ";
			if(y== 2 || y== 5)
			  agregado="  ";
                     System.out.print(matrix[t][y]+agregado);
                   }
	         if(t== 2 || t== 5)
		   System.out.println("\n");
		 else 
	           System.out.println("");
           }
	 System.out.println("\n");
	}
     
    /* Verifica si los numeros no se repiten en el mismo grupo de 3x3
     *  ignorando los '0'
     *  Los grupos quedan definidos de la sig forma:
     *  
     *  |---|---|---|
     *  | 0 | 1 | 2 |
     *  |---|---|---|
     *  | 3 | 4 | 5 |
     *  |---|---|---|
     *  | 6 | 7 | 8 |
     *  |---|---|---|	 
     *  Se le debe indicar el grupo a verificar :)
     * @return true:  NO existen elementos repetidos en el grupo
     *         false: existen elementos repetidos en el  grupo
     */
     public static boolean VerificarGrupo(int[][] matrix, int numeroDelGrupo)
    {   int contador=1;
        boolean copia=false;
	int inicialX=0;
	int inicialY=0;

	switch(numeroDelGrupo)
	   { 
	   case 0:   
		inicialX=0;
		inicialY=0;
		break;
	   case 1:
		inicialX=3;
		inicialY=0;
		break;
	   case 2:
		inicialX=6;
		inicialY=0;
		break;
	   case 3:
		inicialX=0;
		inicialY=3;
		break;
	   case 4:
		inicialX=3;
		inicialY=3;
		break;
	   case 5:
		inicialX=6;
		inicialY=3;
		break;
           case 6:
		inicialX=0;
		inicialY=6;
		break;
	   case 7:
		inicialX=3;
		inicialY=6;
		break;
	   case 8 :
		inicialX=6;
		inicialY=6;
		break;
	   }
	
        for(int q=inicialY; ( q < inicialY+3 ) && !copia ; q++)
                for(int w=inicialX; (w< inicialX+3) && !copia ; w++)
                    {
                        for(int e=q; (e < inicialY+3) && !copia ; e++)
			   {
			    int r=inicialX;
			    if(q==e)
				r=w+1;
                            for(;(r< inicialX+3) && !copia; r++)
                                {
                             //   System.out.println("contador("+matrix[q][w]+"/"+matrix[e][r]+"):"+contador++);
                                /*    if((q == e)&& (r == w))
                                        {;}
                                    else{*/
                                    //    System.out.println("contador:"+contador++);
				//System.out.println("contador("+matrix[q][w]+"/"+matrix[e][r]+"):"+contador++);
					// comparacion ignorando los '0'
                                        if((matrix[q][w] == matrix[e][r] ) && matrix[e][r] != 0 )
                                           {  copia=true;
                                                break;
                                           }
                                        //}
                                }
			    }
                     }
        if(copia)
            return false;
        else
            return true;
    }

    /*Verifica si los numeros no se repiten en el mismo grupo cd 3x3
     * se le debe indicar las coordenadas X e Y del elemento que se desea testear
     * y el metodo calculara de forma automatica a que grupo corresponde el elemento
     *
     * Las coordenadas comienzan desde (0,0) siguiendo el eje X e Y y correspodiendo 
     *  a cada uno de los elementos de la matriz de 9x9
     * 
     *  (0,0)
     *    ^
     *    | --> coodenadasX ++
     *  |--|--|--|--|--|--|--|--|--|  
     *  |--|--|--|--|--|--|--|--|--|  
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     *  |--|--|--|--|--|--|--|--|--|
     * @return true:  NO existen elementos repetidos en el grupo
     *         false: existen elementos repetidos en el  grupo
     */
    public static boolean VerificarGrupo(int[][] matrix, int CoordenadaX, int CoordenadaY)
	{
	  int cuadro=0;

	  if(CoordenadaX <= 2 &&
	     CoordenadaY <= 2)
		cuadro=0;
	  
	  else if(CoordenadaX >= 3 && CoordenadaX <= 5 &&
	     	  CoordenadaY <=2)
		   cuadro=1;
	  
 	  else if(CoordenadaX >= 6 && 
	     	  CoordenadaY <= 2)
		    cuadro=2;
	
	  else if(CoordenadaX <= 2 &&
	     	  CoordenadaY >= 3 && CoordenadaY <=5)
		    cuadro=3;
	
	  else if(CoordenadaX >= 3 && CoordenadaX <= 5 &&
             	  CoordenadaY >= 3 && CoordenadaY <=5)
		    cuadro=4;

	  else if(CoordenadaX >= 6 &&
	          CoordenadaY >= 3 && CoordenadaY <=5)
		    cuadro=5;
	  
	  else if(CoordenadaX <= 2 &&
	          CoordenadaY >= 6)
		    cuadro=6;

	  else if(CoordenadaX >= 3 && CoordenadaX <= 5 &&
	          CoordenadaY >= 6)
		    cuadro=7;

	  else if(CoordenadaX >= 6 && 
	          CoordenadaY >= 6)
		    cuadro=8;

	return VerificarGrupo(matrix,cuadro);
	}
    /* Verifica si algun elemento esta repetido en alguna fila o en alguna columna
     * @return 
     *        true: en caso que algun elemento este repetido en la misma fila o columna
     *        false: en caso que NO este repetido en la misma fila o columna.
     */
   public static boolean RepetidoEnFilaOColumna(int[][] matrix)
     {
	boolean retorno=false;
 	for(int a=0; a< matrix.length; a++)
	  for(int b=0; b < matrix[a].length;b++)
		{
			if(ExisteEnFila(matrix,b,a) ||
			   ExisteEnColumna(matrix,b,a))
			 {
			   retorno=true;
			   break;
			 }
		}
	return retorno;
     }
    /*
     *Verifica si el elemento existe o no dentro de la misma fila.
     * @return 
     * 	 true en caso que  exista,
     *   false en caso que NO exista el mismo elemento
     * en la misma fila.
     * - los '0' son ignorados
     */
    public static boolean ExisteEnFila(int[][] matrix,int CoordenadaX, int CoordenadaY)
	{
	 boolean retorno=false;
	   for(int as=0; as < matrix[CoordenadaY].length; as++)
		{
		  if((matrix[CoordenadaY][as]== matrix[CoordenadaY][CoordenadaX])
		      && (matrix[CoordenadaY][as] != 0)
		      && (as != CoordenadaX))
			retorno=true;
		}
	 return retorno;
	}
    /*
     * Verifica si el elemento existe o no dentro de la misma Columna
     *  @return
     * - true en caso que elemento ya exista en la misma columna
     * - false en caso que el elemento NO exista en la columna indicada.
     * - los '0' son ignorados
     */
    public static boolean ExisteEnColumna(int[][] matrix, int CoordenadaX, int CoordenadaY)
	{
	 boolean retorno=false;
	 for(int as=0; as < matrix.length; as++)
	    {
	     if((matrix[as][CoordenadaX]== matrix[CoordenadaY][CoordenadaX])
		      && (matrix[as][CoordenadaX] != 0)
		      && (as != CoordenadaY))
			retorno=true;
	    }
	 return retorno;
	}

	/*
 	*  Intenta Resolver el problema.
        *  @return true: en caso que se agrege un numero satisfactoriamente
        *          false: en caso que no se pueda agregar ningun numero, la matriz ya no se \n
        *                 puede resolver
	*/
    public static boolean resolver(int[][] matrixAResolver)
	{ int[][] resultado=new int[9][9];
	  boolean listaok=false;
	  boolean elementoOk=false;

	 if(((iteraciones++)%100000) == 0)
	  { System.out.println("iteraciones="+iteraciones) ;
		imprimirEstado(matrixAResolver);
		}
	//System("pause");

	for(int i=0; i < matrixAResolver.length && !terminado; i++)
	   for(int j=0; j < matrixAResolver.length && !terminado; j++)
		{
		  if(matrixAResolver[i][j]==0)
		    {// intentar rellenar con un numero 1-9
			for(int k=1; k <= 9 && !terminado; k++)
			  {
			    resultado=matrixAResolver;
			    resultado[i][j]=k;
			    //imprimirEstado(resultado);

			    if(!ExisteEnFila(resultado,j,i)){
				//System.out.println("fila...ok");
			       if(!ExisteEnColumna(resultado,j,i)){
				   //System.out.println("Columna...ok");                        
				  if(VerificarGrupo(resultado,j,i))
				    {/*intentar resolver el siguiente resultado
				     * con un elemento nuevo
				     */
					//System.out.println("grupo...ok");

				        if(resolver(resultado))
						{
						//System.out.println("Resuelto Retornando true...");	 
						return true;
                        
                        }
					else {
					/* si termina sin resultados satisfactorios,
			  		* entonces el nodo no sirve y se debe volver un nodo atras
                         		* e intentar otra opcion */
					//    System.out.println("No resuelto Retornando false...");
					//    return false; 
					  
					  continue;
					    }

				    }
   				   else {continue;}
				   }
			       else {continue;}
			    }
			    else {continue;}
			  }
			 /* si termina el for sin resultados satisfactorios,
			  * entonces el nodo no sirve y se debe volver un nodo atras
                          * e intentar otra opcion */
			
			//System.out.println("Fin del for 1..9 Retornando false...");
			//imprimirEstado(matrixAResolver);
			resultado[i][j]=0;
     		        matrixAResolver[i][j]=0;
			return false;
		     }
		  else
		   { elementoOk=true;}
		}
           int cuenta=0;
	for(int i=0; i < matrixAResolver.length; i++)
	   for(int j=0; j < matrixAResolver.length; j++)
		{if(matrixAResolver[i][j]==0)
		    cuenta++;
                 }
	 if(cuenta == 0)
	  {	terminado=true; return true;}
	else 
	  return false;

//	 imprimirEstado(matrixAResolver);
	//  return resultado;
	}

   public static void main(String arg[]){
	boolean seguir =true;

   System.out.println("********************************************************************\n"+
		      "            Lacosox.org SUDOKU SOLVER ADICTIVE Edition v0.1 \n"+
 		      "********************************************************************\n");

	if(arg.length > 0)
	   {
		for(int argumentos=0; argumentos < arg.length; argumentos ++)
		    { boolean valida=false; //para verificar si existe alguna opcion q no es valida :)
			//mostrar la Ayuda
			if(arg[argumentos].equalsIgnoreCase("--help")){
			   System.out.println(Ayuda.getAyuda());
			   seguir=false;
			   valida=true;
			   break;
			 }
			//Mostrar acerca de..
			if(arg[argumentos].equalsIgnoreCase("--about")){
			   System.out.println(Ayuda.getAbout());
			   seguir=false;
			   valida=true;
			   break;
			}
			//para ingresar la matriz problema
			if(arg[argumentos].equalsIgnoreCase("-m") ||
			   arg[argumentos].equalsIgnoreCase("--matriz")){
			   valida=true;
		           try{
			     if(arg[argumentos+1].length() == 81){ 
				    test=Utilidades.toMatrix(arg[argumentos+1]);
				}
			     else
				{ System.out.println("La matriz no puede tener "+arg[argumentos+1].length()+" elementos, debe tener 81.");
				  test=null;
				 }
			   }catch(java.lang.ArrayIndexOutOfBoundsException aiobe){
					 System.out.println("falta un argumento?");
					 System.out.println(Ayuda.getAyuda());
	   			         seguir=false;
			   		 break;
					}
			   argumentos++; 
			}
			// para No mostrar el tiempo de calculo utilizado
			if(arg[argumentos].equalsIgnoreCase("-nmt") ||
			   arg[argumentos].equalsIgnoreCase("--NoMostrarTiempo") ||
			   arg[argumentos].equalsIgnoreCase("--NoShowTime")){
					valida=true;
					NoMostrarTiempo=true;
					break;
			}

			if(!valida){
			  /*En caso que exista alguna opcion que no es valida
			   * se mostrara la ayuda y no se validara ninguna otra.*/
			   System.out.println("Opcion Desconocida -> \""+arg[argumentos]+"\"");
			   System.out.println(Ayuda.getAyuda());
			   seguir=false;
			   break;		
			}
		    }
	   }
        else{
	    System.out.println(Ayuda.getAyuda());
            seguir=false;
	}

	if(seguir){
	   if(validar(test))
	     {
		System.out.println("La matriz es valida para resolverla...");
		/* Calculando el Tiempo :)*/
		
		GregorianCalendar calendarioAntes= new GregorianCalendar();
		  
		if(resolver(test))
		 {  System.out.println("Resultado :");
		    imprimirEstado(test);
		    System.out.println("Total de Iteraciones:"+iteraciones);
		 }
		else 
		  {
		   System.out.println("No se puede resolver :(");
		   System.out.println("Total de Iteraciones:"+iteraciones);
		   }
		   
		GregorianCalendar calendarioDespues= new GregorianCalendar();
		
		if(!NoMostrarTiempo){
			System.out.println("Tiempo :"+Utilidades.DiferenciaDeTiempo(calendarioAntes,calendarioDespues));
			}
	     }
	    else
	     {
	 	System.out.println("La matriz no es valida para resolverla\n"+
				  "  El problema no se puede resolver si la matriz base no cumple las"+
			          " reglas basicas:\n"+
			          " - Verifique que no existan numeros repetidos en la misma fila\n"+
				  " - Verifique que no existan numeros repetidos en la misma Columna\n"+
				  " -Verifique que no existan numeros repetidos en los mismos grupos de 3x3");
	    }
	  }
	}
}
