package client;
/**
 * @Author: Peony
 * @Date: 2020/6/10 19:25
 */
public class MainClient {

    public static void main(String[] args){
        Client client = Client.getInstance();
        client.setName(Properties.name);
        client.setPort(Properties.port);
        client.startClient();
    }
}
