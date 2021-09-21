### 门面模式
Client类为客户端，持有SDK类，SDK对外提供send方法，作用是发送短信，发送短信前需要校验参数，发起连接，发送，释放连接，send方法对外屏蔽了
内部细节，只提供了一个简单的发送接口。 下面给出代码示例
```java
// 定义一个客户端类，构造方法里传入SDK
Client client = new Client(new SDK());
// 发送成功
client.send("123", "con");
```