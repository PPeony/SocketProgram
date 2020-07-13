package client2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * @Author: Peony
 * @Date: 2020/6/10 19:25
 */
public class Client {

    private static volatile Client client;

    private Client() {}

    public static Client getInstance() {
        if (client == null) {
            synchronized (Client.class) {
                if (client == null) {
                    client = new Client();
                }
            }
        }
        return client;
    }

    private String name;

    private Integer port;

    public void startClient(){
        try {
            //主动产生连接
            System.out.println("我是客户端 启动了");
            Socket socket = new Socket("127.0.0.1",port);
            //客户端需要将自己的id发送给服务器
            //stream是字节流，writer是字符流
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(name);//客户端的id写给了服务器
            pw.flush();
            View view = new View(socket);
            view.launchFrame();
            new Thread(view).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
