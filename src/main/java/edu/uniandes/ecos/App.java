package edu.uniandes.ecos;

import model.Calculos;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
         double x=1.1;
       Calculos cal =new Calculos(x,9,10);
        System.out.println( "Hello World!" + cal.getP());
        /*
        x =1.1
        dof=9
        E=0.00001
        num_seg=10
        */
    }
}
