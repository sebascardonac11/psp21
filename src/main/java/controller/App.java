/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author sebascardonac11
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Calculos;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import vista.MainView;

/**
 * Main Application
 */
public class App extends HttpServlet {

    public static void main(String[] args) {
        try {
            Server server = new Server(Integer.valueOf(System.getenv("PORT")));
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            context.addServlet(new ServletHolder(new App()), "/*");

            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        MainView.showHome(req, resp);    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MainView.showHome(req, resp);
            consoleInput(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to set the console input for the numbers
     */
    public void consoleInput(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String lst1 = req.getParameter("lst1");
        String lst2 = req.getParameter("lst2");
        String lst3 = req.getParameter("lst3");
        Calculos cal = new Calculos(Double.parseDouble(lst1), Double.parseDouble(lst2),Double.parseDouble(lst3));
        MainView.showResults(req, resp, Double.parseDouble(lst1), Double.parseDouble(lst2), Double.parseDouble(lst3), cal.getP());
    }
}
