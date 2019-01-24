package org.liyd.server;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * servlet处理器
 * @author liyudong
 */
public class ServletProcessor {

  public void process(Request request, Response response) {

    var uri = request.getUri();
    var servletName = uri.substring(uri.lastIndexOf("/") + 1);
    /**
     * 应在初始化时就把所有的servlet class加载起来
     */



    URLClassLoader loader = null;

    try {
      // create a URLClassLoader
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      File classPath = new File(Constants.WEB_ROOT);

      // the forming of repository is taken from the createClassLoader
      // method in
      // org.apache.catalina.startup.ClassLoaderFactory
      String repository = (new URL("file", null,
              classPath.getCanonicalPath() + File.separator)).toString();

      // the code for forming the URL is taken from the addRepository method in
      // org.apache.catalina.loader.StandardClassLoader class.
      urls[0] = new URL(null, repository, streamHandler);

      loader = new URLClassLoader(urls);
    } catch (IOException e) {
      System.out.println(e.toString());
    }

    Class<?> myClass = null;

    // 获取
    try {
      myClass = loader.loadClass(servletName);
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }

    Servlet servlet;

    try {
      servlet = (Servlet) myClass.getDeclaredConstructor().newInstance();
      servlet.service(request, response);
    } catch (Exception e) {
      System.out.println(e.toString());
    } catch (Throwable e) {
      System.out.println(e.toString());
    }

  }
}
