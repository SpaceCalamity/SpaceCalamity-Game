package rocks.spacecalamity.app.ipc;

import java.io.PrintWriter;

public class UsersClient extends Client {
  UsersClient(PrintWriter writer) {
    super(writer, "users");
  }
}
