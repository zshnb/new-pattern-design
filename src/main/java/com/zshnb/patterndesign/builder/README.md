### 建造者模式
使用内部类Builder，完成对类对象属性的填充以及返回填充完毕的对象，实体类和Builder类构造器均为私有，通过实体类静态方法newBuilder获取
Builder实例，通过Builder实例的build方法获取实体类实例。通过对象toBuilder返回Builder实例。下面给出代码示例
```java
// newBuilder获取Builder实例，并且设置实体类属性，在Builder上的方法均为链式调用
User user = User.newBuilder()
    .setId(1)
    .setName("name")
    .setAddress("address")
    .setSex("sex").build();
System.out.println(user);

// 把实体对象转为Builder，修改其某个属性再次build
user = user.toBuilder()
    .setSex("sex2").build();
System.out.println(user);

// 删除实体对象某些属性
user = user.toBuilder()
.clearAddress().build();
System.out.println(user);
```