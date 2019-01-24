package org.liyd.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author liyudong
 */
public class ExampleServlet implements Servlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("----- ExampleServlet Init -----");
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    System.out.println("----- ExampleServlet service() -----");
    var printWriter = res.getWriter();
    printWriter.println("Hello. Roses are red.");
    printWriter.print("Violets are blue.");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {
    System.out.println("----- ExampleServlet destroy() -----");
  }
}
