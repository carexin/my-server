package server;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ResponseFacade implements ServletResponse {

  private ServletResponse response;

  public ResponseFacade(ServletResponse response) {
    this.response = response;
  }

  @Override
  public String getCharacterEncoding() {
    return null;
  }

  @Override
  public String getContentType() {
    return null;
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return response.getOutputStream();
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    return response.getWriter();
  }

  @Override
  public void setCharacterEncoding(String charset) {

  }

  @Override
  public void setContentLength(int len) {

  }

  @Override
  public void setContentLengthLong(long len) {

  }

  @Override
  public void setContentType(String type) {

  }

  @Override
  public void setBufferSize(int size) {

  }

  @Override
  public int getBufferSize() {
    return 0;
  }

  @Override
  public void flushBuffer() throws IOException {
    response.flushBuffer();
  }

  @Override
  public void resetBuffer() {
    response.resetBuffer();
  }

  @Override
  public boolean isCommitted() {
    return false;
  }

  @Override
  public void reset() {
    response.reset();
  }

  @Override
  public void setLocale(Locale loc) {

  }

  @Override
  public Locale getLocale() {
    return null;
  }
}
