package edu.uniandes.ecos;

import model.Calculos;

/*
 * Copyright (C) 2015 Sebastian Cardona Correa
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
         double x=1.0;
       Calculos cal =new Calculos(x,6,10,0.20);
        System.out.println( "Hello World!" + cal.getX());
        /*
        x =1.1
        dof=9
        E=0.00001
        num_seg=10
        */
    }
}
