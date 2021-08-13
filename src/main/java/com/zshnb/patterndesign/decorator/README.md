### 装饰器模式
定义一个基类容器ArrayListUtil，基类容器为泛型容器，然后定义PositiveNumberArrayListUtil容器，该容器只能存放正整数，如果往里面添加负数
会抛异常， 下面给出代码示例
```java
// 定义一个装饰器类，构造方法里传入基类容器
PositiveNumberArrayListUtil<Integer> positiveNumberArrayListUtil = new PositiveNumberArrayListUtil<>(new ArrayListUti<>());
positiveNumberArrayListUtil.add(1); // 正整数添加正常
positiveNumberArrayListUtil.add(-1); // 负数添加失败，抛异常
```