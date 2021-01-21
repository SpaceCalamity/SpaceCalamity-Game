package rocks.spacecalamity.app.ipc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

class ClientThread extends Thread {
  private static final String QUIT = "quit";  

  private Client client;

  private Socket socket;
  private BufferedReader reader; 
  private PrintWriter writer;
  
  private boolean running;

  ClientThread(Socket socket) throws Exception {
    this.socket = socket;
    InputStream input = socket.getInputStream();
    this.reader = new BufferedReader(new InputStreamReader(input));
    
    OutputStream output = socket.getOutputStream();
    this.writer = new PrintWriter(output);
    
    InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();
    InetSocketAddress local = (InetSocketAddress)socket.getLocalSocketAddress();
    boolean isIPC = local.getAddress().toString().equals(remote.getAddress().toString());
    
    if (isIPC) {
      String msg = this.reader.readLine();
      if (!selectClient(msg)) {
        this.socket.close();
      } else {
        this.running = true;
        this.start();
      }
    } else {
      System.out.println("Server: Not localhost, disconnecting");
      this.socket.close();
    }
  }

  @Override
  public void run() {
    if (this.client == null) {
      System.out.println("Server Error: Assertion error (client is null on thread start)");
      return;
    }
    try {
      this.writer.println("connected");
      this.writer.flush();
      String msg;
      while(this.running) {
        msg = this.reader.readLine();
        if (msg.equals(ClientThread.QUIT)) {
          this.disconnect();
        } else {
          ConnectionHandler.get().queue.add(this.client.type + " " + msg);
        }
      }
    } catch (Exception e) {
      System.out.printf("Server: Connection failure from %s client", this.client.type);
      System.out.flush();
      e.printStackTrace();
    }
    System.out.printf("Server: Closing connection with %s client", this.client.type);
    System.out.flush();
    try {
      this.socket.close();
    } catch (Exception e) {
      System.out.println("Server Error: Failed to close socket (already closed)");
    }
  }
  
  private boolean selectClient(String command) {
    if (command.equals("users")) {
      this.client = new UsersClient(this.writer);
      return ConnectionHandler.get().assignUsersClient((UsersClient)this.client);
    } else if (command.equals("storage")) {
      this.client = new StorageClient(this.writer);
      return ConnectionHandler.get().assignStorageClient((StorageClient)this.client);
    }
    System.out.printf("Server Error: Unknown client type %s", command);
    System.out.flush();
    this.writer.println("failed");
    this.writer.flush();
    return false;
  }

  private void disconnect() {
    if (this.client == null) {
      System.out.println("Server Error: Assertion error (client is null)");
      return;
    }
    if (this.client.type.equals("users")) {
      ConnectionHandler.get().removeUsersClient();
    } else if (this.client.type.equals("storage")) {
      ConnectionHandler.get().removeStorageClient();
    } else {
      System.out.printf("Server Error: Assertion error (unknown type %s)", this.client.type);
    }
    this.running = false;
  }
}
