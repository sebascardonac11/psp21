/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author sebascardonac11
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oscarkiyoshigegarcesaparicio
 */
public class MainView {

    public static void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h1>PSP2.0 Program 5</h1>");
        pw.println("<h2>Sebastian Cardona Correa</h2>");
        pw.println("<h2>E:0.00001</h2>");
        pw.write("<form action=\"calc\" method=\"post\"> \n"
                + " <p> x </p>   <input type=\"text\" name=\"lst1\"  value=\"1.1\">\n"
                + " <p> dof </p> <input type=\"text\" name=\"lst2\"  value=\"9\">\n"
                + " <p> Numero de Segmento </p> <input type=\"text\" name=\"lst3\"  value=\"10\">\n"
                + "    <input type=\"submit\" value=\"Calc\">\n"
                + "</form> ");

        pw.write("</html>");
    }

    public static void showResults(HttpServletRequest req, HttpServletResponse resp,Double x, Double dof,Double seg ,Double P)
            throws ServletException, IOException {
        resp.getWriter().println("<b>x:</b> " + x + "<br>");
        resp.getWriter().println("<b>dof:</b> " + dof + "<br>");
        resp.getWriter().println("<b>segmento:</b> " + seg + "<br>");
        resp.getWriter().println("<b>P:</b> " + P + "<br>");

    }

    public static void error(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Error!!!");
    }

}
