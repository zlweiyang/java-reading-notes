
## Nginx动静分离 ##

**静态资源：**Web服务器对应目录中的资源文件，比如HTML,CSS,JavaScript，图片等文件，这些文件会原样返回给客户端，并不会在Web服务器端有所改变。

**动态资源：**运行Web服务器中的程序，通过与数据库交互及其他逻辑与运算，返回不同的数据资源，这些数据资源为动态资源，这些资源主要包括JSP、Servlet脱离Tomcat等Java应用服务器无法正常工作。

Nginx可以作为HTTP服务器提供Web服务，但只能处理静态资源文件。

使用Nginx+Tomcat实现动静分离：

1.Nginx处理静态资源更优越；

2.请求分流，减轻Tomcat的直接压力；

3.各自完成自己的功能。

Tomcat的端口：8080

Nginx的端口：80

Nginx的吞吐量和响应时间都优于Tomcat。

静态资源的Nginx配置有多种方式，比如以路径为标示的方式和以后缀名称的方式

启动Nginx：start nginx

停止Nginx：nginx -stop

