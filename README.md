# hijdb
这是一个用Java写的ORM程序库.
该程序没有使用任何第三库,完全手工打造.
在Java中,有很多有名的ORM程序,例如Hibernate,MyBatis都很优秀,本来没有必要重新发明轮子.
但是Hibernate感觉比较复杂,MyBatis的类型也有些麻烦,而且还要定义一个与每个XML文件对应的接口(不知道是不是这样),都不大符合自己的要求,
于是就自己写了一个库.
其实,这是我的练手之作,是把写这个库作为深入学习Java的手段.
在这个库中,通过代码考察一下事情:
1: Java中的值类型
2: Java中对泛型如何进行约束和特化,了解什么是Java擦出,Java中Class
3: 在Java中如何使回调简单
4: 匿名类如何访问外部局部变量
5: 如何在Java中使用Junit进行单元测试
6: 如何使用Java的元数据(Meta)进行一些通用处理,例如给对象赋值.
7: 如何在JDBC中给SQL添加含名称的参数
该程序是我的一个C#的移植程序,很多的概念也是在C#中先有想法和实现,再到Java中寻找相似的东西,如果没有则自己实现 
另:该程序的包,方法规则不符合Java编码规范,而是使用C#的规范,而且没有很好的将程序分为多个可以独立运行的库,以后可能会做这些处理