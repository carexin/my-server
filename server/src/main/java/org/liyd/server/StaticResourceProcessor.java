package org.liyd.server;

import java.io.IOException;

/**
 * @author liyudong
 */
public class StaticResourceProcessor {

  public void process(Request request, Response response) {
    try {
      response.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}