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
package model;

/**
 * Numerical integration with Simpson’s rule
 *
 * @author sebascardonac11
 */
public class Calculos {

    private double x;
    private double dof;
    private static double E = 0.00001;
    private double num_seg;
    private double w;

    private int r;
    private double f1;
    private double p1;
    private double p2;
    private double parametroD = 0.5;

    /**
     *
     * @param x Rango final para calcular la ecuacion.
     * @param dof Cantidad de grados de libertad.
     * @param num_seg CAntidad de veces a iterar.
     */
    public Calculos(double x, double dof, double num_seg) {
        this.x = x;
        this.dof = dof;
        this.num_seg = num_seg;
        this.calF1();
        this.p1 = calcP(this.num_seg);
        this.p2 = calcP(this.num_seg * 2);
        if (Math.abs(this.p1 - this.p2) < this.E) {
            this.p1 = this.p2;
        } else {
            this.p1 = new Calculos(this.getX(), this.dof, this.p2).getP();
        }

    }

    /**
     * Constructor usado para hallar X
     *
     * @param xi Rango inicial para calcular la ecuacion.
     * @param dof Cantidad de grados de libertad.
     * @param num_seg CAntidad de veces a iterar.
     * @param p Valor dado
     */
    public Calculos(double xi, double dof, double num_seg, double p) {
        this.dof = dof;
        this.p1 = 0;
        this.p2 = 0;
        this.w = 0;
        this.num_seg = num_seg;
        this.x = xi;
        double diferencia = 0;
        double p1 = 1;
         this.calF1();
        this.p1 = this.calcP(xi, this.num_seg);
        diferencia = Math.abs(this.p1 - p);
        if (diferencia > this.E) {
            this.x = xi;
        }
        double integralComparar = this.p1;

        if (this.p1 < p) {
            this.x = this.x + parametroD;
        } else {
            this.x = this.x - parametroD;
        }
        while (diferencia > this.E) {
            this.p2 = this.calcP(this.x, num_seg);
            diferencia = Math.abs(this.p2 - p);
            /*PENSAR*/
            if (this.p2 < p) {
                parametroD = adjustD(this.parametroD, this.p2, p);
                this.x = this.x + parametroD;
            } else {
                parametroD = adjustD(this.parametroD, this.p2, p);
                this.x = this.x - parametroD;
            }
            integralComparar = this.p2;
            if (diferencia < this.E) {
                xi = this.x;
            }
        }

        /**/
    }

    /**
     * Metodo que ajusta el valor de D
     *
     * @param adjustDP valor de D
     * @param parametroIntegral valor de P en la integral
     * @param parametroBuscadoP valor de P que estamos buscando
     * @return adjustDP ajustado
     */
    public double adjustD(double adjustDP, double parametroIntegral, double parametroBuscadoP) {
        if (parametroIntegral < parametroBuscadoP) {
            return adjustDP;
        }
        return adjustDP / 2;
    }

    private double calcP(double num_seg) {
        this.w = this.getX() / num_seg;
        double sumatorias = 0.0;
        for (int i = 0; i <= num_seg; i++) {
            sumatorias += this.getMultiplicador(getF(this.w * i), i, num_seg);
        }
        return (this.w / 3) * sumatorias;

    }

    private double calcP(double xi, double num_seg1) {
        this.w = xi / num_seg1;
        double sumatorias = 0.0;
        for (int i = 0; i <= num_seg1; i++) {
            sumatorias += this.getMultiplicador(getF(this.w * i), i, num_seg1);
        }
        return (this.w / 3) * sumatorias;

    }

    /**
     * Resultado de la formula de simpson.
     *
     * @return variable con el resultado de los calculos.
     */
    public double getP() {
        return this.p1;
    }

    /**
     * Calcula la constante de la formula.
     */
    private void calF1() {
        double r1 = calR((this.dof + 1) / 2);
        double r2 = calR(this.dof / 2);
        this.f1 = r1 / ((Math.sqrt(this.dof * 3.1416)) * (r2));
    }

    /**
     * Ejecuta la multiplicacion de F(x), donde para el primero y ultimo, es el
     * mismo F(x), para los pares, se multiplica por 4 y los impares por 2.
     *
     * @param f valor f a multiplicar.
     * @param i valor de la sumatoria.
     * @return el valor esperado con el multiplicador.
     */
    private double getMultiplicador(double f, int i, double num_seg) {
        int ispar = i / 2;
        ispar = ispar * 2;
        if (i == 0 || i == num_seg) {
            return f;
        } else {
            if (ispar == i) {
                /*impar*/
                return f * 2;
            } else {
                return f * 4;
            }
        }
    }

    /**
     * Calcula F dado una variable x.
     *
     * @param x variable a calcular la funcion
     * @return F(x)
     */
    private double getF(double x) {
        if (x == 0) {
            return this.f1;
        } else {
            double exp = -1 * ((this.dof + 1) / (2));
            double base = (1 + ((x * x) / this.dof));
            double f2;
            if (exp < 0) {
                f2 = Math.exp(exp * Math.log(base));
            } else {
                f2 = Math.pow(exp, base);
            }

            return f1 * f2;
        }
    }

    /**
     * Metodo publico para calcular R.
     *
     * @param x calcula R de la variable que se ingresa
     * @return R(x)
     */
    private double calR(double x) {
        return this.factorial(x - 1);
    }

    /**
     * Calcula el factoriar para numero enteros y fraccionarios de forma
     * recursiva, asumiendo que el fraccionario al final da 0,5.
     *
     * @param n el numero a calcular factorial.
     * @return factorial del numero ingresado.
     */
    private double factorial(double n) {
        if (n == 0) {
            return 1;
        } else {
            if (n == 0.5) {
                return (0.5 * Math.sqrt(3.1416));
            } else {
                return n * factorial(n - 1);
            }
        }
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

}
