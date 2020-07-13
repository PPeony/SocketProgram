package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private static volatile Server server;
    private Server() {}
    public static Server getInstance() {
        if (server == null) {
            synchronized (Server.class) {
                if (server == null) {
                    server = new Server();
                }
            }
        }
        return server;
    }

    private Integer port;
    private static HashMap<String,User> userMap = new HashMap<String,User>();

    public static HashMap<String,User> getUserMap(){
        return userMap;
    }
    public User getSocket(String id){
        return userMap.get(id);
    }

    public void startServer(){
        try {
            //自己启动了一个服务器
            System.out.println("我是服务器 启动了");
            ServerSocket server = new ServerSocket(port);
            while(true){
                //等待客户端连接
                Socket socket = server.accept();
                //需要接收客户端发送过来的id
                //对面发送的是字符流，转换成字节流
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String id = br.readLine();
                //将id和socket装到一个User对象里
                User user = new User(id,socket);
                //user对象装到map集合里
                userMap.put(id,user);
                System.out.println("有一个客户端连接我了");
                System.out.println(id);
                System.out.println("此时在线人数为"+userMap.size());
                //线程-->  先读取 转发
                ServerThread thread = new ServerThread(user);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
