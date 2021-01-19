package rocks.spacecalamity.app.ipc;

import java.io.PrintWriter;

abstract class Client {
  protected PrintWriter writer;

  String type;

  protected Client(PrintWriter writer, String type) {
    this.writer = writer;
    this.type = type;
  }
}
