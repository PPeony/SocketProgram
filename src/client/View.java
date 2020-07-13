package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.TextAreaPeer;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @Author: Peony
 * @Date: 2020/6/10 19:25
 */
public class View extends JFrame implements Runnable {


    private TextArea history =new TextArea();//上面的历史记录
    private JLabel name = new JLabel(Properties.name);
    private JLabel content = new JLabel(Properties.TALKING_HISTORY);//左上角的标签
    private TextArea input=new TextArea();//下面的打字窗口
    private boolean beConnected;
    private Socket socket;

    public View(Socket socket) {
        this.beConnected = true;
        this.socket = socket;
    }

    //启动主窗口
    public void launchFrame() {
        this.setTitle(Properties.MAIN_TITLE);
        setBounds(100,100,650,650);
        this.setLayout(null);
        name.setBounds(20,10,200,20);
        this.add(name);
        content.setBounds(20, 30, 200, 20);
        this.add(content);
        history.setBounds(10, 70, 600, 400);
        this.add(history);
        input.setBounds(10, 480, 600, 100);
        this.add(input);
        setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                try {
                    beConnected = false;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.getWindow().dispose();
                System.exit(0);
            }
        });
        try {
            input.addKeyListener(new MyListener(input,socket));
        } catch (Exception e) {
            this.showReturnMessage(Properties.IOEXCEPTION_MESSAGE);
        }

    }

    public void run() {
        try{
            while (beConnected){
                //读取服务器的信息
                String message = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
                        .readLine();
                history.setText(history.getText()+
                        message.replace("#","\n")+
                        "\n");
            }
        }catch(IOException e){
            this.showReturnMessage(Properties.IOEXCEPTION_MESSAGE);
        }
    }

    //feedback message
    public void showReturnMessage(String message) {
        System.out.println("View.showReturnMessage");
        JOptionPane.showMessageDialog(null, message, Properties.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}

