package rocks.spacecalamity.app.ipc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionHandler {
  private static ConnectionHandler singleton = null;

  public static ConnectionHandler get() {
    if (ConnectionHandler.singleton == null) {
      ConnectionHandler.singleton = new ConnectionHandler();
    }
    return ConnectionHandler.singleton;
  }

  ConcurrentLinkedQueue<String> queue;

  public UsersClient usersClient;
  public StorageClient storageClient;

  private boolean isListening;

  boolean assignUsersClient(UsersClient client) {
    if (this.usersClient != null) {
      System.out.println("Server Error: Users client already assigned");
      return false;
    }
    if (client == null) {
      System.out.println("Server Error: Provided client is null");
      return false;
    }
    this.usersClient = client;
    return true;
  }

  boolean assignStorageClient(StorageClient client) {
    if (this.storageClient != null) {
      System.out.println("Server Error: Storage client already assigned");
      return false;
    }
    if (client == null) {
      System.out.println("Server Error: Provided client is null");
      return false;
    }
    this.storageClient = client;
    return true;
  }

  void removeUsersClient() {
    this.usersClient = null;
  }

  void removeStorageClient() {
    this.storageClient = null;
  }

  private ConnectionHandler() {
    this.queue = new ConcurrentLinkedQueue<String>();
    this.isListening = false;
  }

  public void listen(int port) throws Exception {
    if (!this.isListening) {
      this.isListening = true; 
      try (ServerSocket serverSocket = new ServerSocket(port)) {
        System.out.printf("Server: Listening on port: %d", port);
        System.out.flush();

        while (isListening) {
          Socket socket = serverSocket.accept();
          ClientThread newClientThread = new ClientThread(socket);
        }
        System.out.println("Server: Stopped listening");
      } catch (Exception e) {
        System.out.printf("Server Error: %s", e.getMessage());
        System.out.flush();
        e.printStackTrace();
      }
      this.isListening = false;
    } else {
      System.out.println("Server Error: Connection handler is already listening");
    }
  }
  
  public String readCommand() {
    return this.queue.poll();
  }
}
