/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaonline;

import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javaio.ReadWriteTextFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
/**
 *
 * @author David
 */
public class GetGeneration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); 
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out = response.getWriter();
        try {
            int m = Integer.parseInt(request.getParameter("M"));
            int n = Integer.parseInt(request.getParameter("N"));
            ArrayList data = new ArrayList<Point>();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String name[] = request.getParameterValues("liveCells[" + i + "][]");
                if (name == null) break;
                data.add(new Point(Integer.parseInt(name[0]), Integer.parseInt(name[1])));
            }
            String jsonpCall = request.getParameter("callback");
            Generation g = new Generation();
            out.printf(jsonpCall + "(" + g.getJSONPNextGeneration(m,n,data) + ")");
            
            ReadWriteTextFile wfile = new ReadWriteTextFile("D:\\Documents\\Dropbox\\SERVER_LOG\\temp.txt");
            wfile.WriteFile("Header:" + request.getHeader("x-forwarded-for")
                    + " - RemoteAddr:" + request.getRemoteAddr() + " - RemoteHost:" + request.getRemoteHost() + "\n", true, true);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
