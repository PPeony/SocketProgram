package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerThread extends Thread{

    private User user;
    public ServerThread(User user){
        this.user=user;
    }

    public void run(){
        try {
            //先读取消息
            BufferedReader br = new BufferedReader(new InputStreamReader(this.user.getSocket().getInputStream()));
            HashMap<String,User> userMap = Server.getUserMap();
            Set<String> names = userMap.keySet();
            while(true){
                String value = br.readLine();//服务器从某一个客户端读取过来的
                System.out.println(this.user.getId()+":"+value);

                //处理一下发送数据的时间
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                String time = sdf.format(date);

                //转发给别的客户端
                Iterator<String> it = names.iterator();
                while(it.hasNext()){
                    String id = it.next();
                    User user = userMap.get(id);
                    PrintWriter writer= new PrintWriter(user.getSocket().getOutputStream());
                    writer.println(time+"#"+this.user.getId()+":"+value);//转发
                    writer.flush();
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(this.user.getId()+"下线啦");
        }

    }

}
