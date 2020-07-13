# SocketProgram 简易的网络聊天室
计算机网络大作业
> a local program using bio 
## 主要技术
### 服务端
```java
ServerSocket socket = new ServerSocket(port)
Socket socket = socket.accept();//监听端口
//然后开一个线程处理这个socket的信息
//用流读写
```
### 客户端
```java
Socket socket = new Socket(ip,port);
//就一行就可以创建连接，也是用流读写
```
### 缺点
每一个socket都需要新建一个线程，这点在我之后的学习过程中发现可以用nio改进。
