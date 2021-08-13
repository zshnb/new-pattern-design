### 责任链模式
FilterChain为责任链的主体，持有所有Filter，每个Filter带有Order注解，代表在责任链中的顺序，值越小越前面。
```java
// 使用FilterChain对Request进行校验，分别校验其中的ip和locale字段
Request request = new Request("192.2.1.1", "cn", LocalDateTime.now());
FilterChain filterChain = new FilterChain();
filterChain.handle(request);
```