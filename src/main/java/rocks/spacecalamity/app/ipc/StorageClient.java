package rocks.spacecalamity.app.ipc;

import java.io.PrintWriter;

public class StorageClient extends Client {
  StorageClient(PrintWriter writer) {
    super(writer, "storage");
  }
}
