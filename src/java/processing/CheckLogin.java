/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import functions.Users;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.User;

/**
 *
 * @author thuyento
 */
public class CheckLogin extends HttpServlet {

  private final String defaultPage = "login.jsp";
  private final String errorPage = "401.jsp";
  private final String successPage = "index.jsp";

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
    String uname = request.getParameter("txtInputUname");

    String upass = request.getParameter("txtInputUpass");

    HttpSession session = request.getSession();
    session.removeAttribute("USERNAME");
    session.removeAttribute("USERID");
    session.removeAttribute("USERROLE");
    String url = errorPage;
    try {
      if (uname != null && !uname.trim().isEmpty() && upass != null && !upass.trim().isEmpty()) {

        User user = Users.checkUserLogin(uname, upass);
        if (user != null) {
          session.setAttribute("USERNAME", user.getUname());
          session.setAttribute("USERID", user.getUid());
          session.setAttribute("USERROLE", user.getUrole());
          url = successPage;
        }
      } else {
        url = defaultPage;
      }
    } catch (Exception e) {

      url = errorPage;
    } finally {
      RequestDispatcher rd = request.getRequestDispatcher(url);
      rd.forward(request, response);
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
