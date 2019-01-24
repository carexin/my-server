package org.liyd.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 请求http://localhost/servlet/org.liyd.web.ExampleServlet
 * @author liyudong
 */
public class HttpServer {

  public static final String WEB_ROOT = System.getProperty("user.dir")
          + File.separator + "webroot";

  /**
   * 应该存在线程安全问题
   */
  private boolean shutdown = false;

  public static void main(String[] args) {
    var server = new HttpServer();
    server.await();
  }

  public void await() {
    ServerSocket serverSocket = null;
    var port = 80;
    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    // Loop waiting for a request
    while (!shutdown) {
      Socket socket;
      InputStream input;
      OutputStream output;

      try {
        socket = serverSocket.accept();
        input = socket.getInputStream();
        output = socket.getOutputStream();
        /**
         * 每次请求创建request和response
         */
        Request request = new Request(input);
        request.parse();

        Response response = new Response(output);
        response.setRequest(request);
        // response.sendStaticResource(); send静态资源交由静态资源处理器执行.

        /**
         * 此处常会报错
         */
        if (request.getUri().startsWith("/servlet")) {
          new ServletProcessor().process(request, response);
        } else {
          new StaticResourceProcessor().process(request, response);
        }

        // Close the socket
        socket.close();
        // check if the previous URI is a shutdown command
        shutdown = request.getUri().equals(Constants.SHUTDOWN_COMMAND);
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
