package client2;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author: Peony
 * @Date: 2020/6/10 20:00
 */
public class MyListener extends KeyAdapter {

    private TextArea input;
    private Socket socket;
    public MyListener(TextArea input, Socket socket) {
        this.input = input;
        this.socket = socket;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
            String str = input.getText().trim();//获取输入框的内容
            input.setText("");
            try {
                //发送到服务器
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(str);
                out.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

