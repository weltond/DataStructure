## Patterns in Spring
### Static Factory Method
本身不属于GOF的23种设计模式之一。

**实质是由一个工厂类根据传入的参数， 动态决定应该创建哪一个产品类。**

Spring中的BeanFactory就是，根据传入一个唯一的标志来获得bean对象，但是否是在传入参数后创建还是传入参数前创建这个要根据具体情况来定。
- BeanFactory
```xml
<beans>
  <bean id="xxBean" class="com.weltond.Hello">
    <contructor-arg>
      <value>Hello!! This is xxBean value>
    </constructor-arg>
  </bean>
  <bean id="yyBean" class="com.weltond.Hello">
    <constructor-arg>
      <value>Hello!! This is yyBean value>
    </constructor-arg>
  </bean>
</beans>
```
### Factory Pattern
通常由应用程序直接使用new创建新的对象，为了将对象的创建和使用相分离，采用工厂模式，即应用程序将对象的创建及初始化职责交给工厂对象。

一般情况下，应用程序有自己的工厂对象来创建bean。如果将应用程序自己的工厂对象交给Spring管理，那么Spring管理的就不是普通的bean，而是工厂bean。

下面以工厂方法中的静态方法为例：

```java
import java.util.Random;

public static StaticFactoryBean {
    public static Integer createRandom() {
        return new Integer(new Random().nextInt());
    }
}
```
建立一个config.xml配置文件，将其纳入Spring容器来管理，需要通过factory-method指定静态方法名称：
```xml
<bean id="random" 
      class="com.weltond.StaticFactoryBean" 
      factory-method="createRandom"
      scope="prototype"
/>
```
测试：
```java
public static void main(String[] args) {
    //调用getBean()时，返回随机数，如果没有指定factory-method,会返回StaticFactoryBean的实例，即返回工厂Bean的实例.
    XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("config.xml"));
    System.out.println("I am an instance : " + factory.getBean("random").toString());
}
```

###Singleton
**保证一个类仅有一个实例，并提供一个访问它的全局访问点**

Spring的单例模式完成了后半句话，即提供了全局的访问点BeanFactory。但没有从构造器级别去控制单例，这是因为Spring管理的是任意的java对象。

**核心提示点**
- Spring下默认的bean均为singleton，可以通过singleton='true|false' 或者scope="?" 来指定。

### Adapter
在Spring的AOP中，使用Advice(通知)来增强被代理的功能。Spring实现这一AOP功能的原理就是代理模式(1. JDK Dynamic Proxy. 2. CGLib)对类进行方法级别
的切面增强，即，生成被代理类的代理类，并在代理类的方法前，设置拦截器，通过执行拦截器中的内容增强了代理方法的功能，实现面向切面编程。

- Adapter Inteface: Target
```java
public interface AdvisorAdapter {
    boolean supportsAdvice(Advice advice);
    MethodInterceptor getInterceptor(Advisor advisor);
}

// Adapter
class MethodBeforeAdviceAdapter implements AdvisorAdapter, Serializable {
    public boolean supportsAdvice(Advice advice) {
        return (advice instanceof MethodBeforeAdvice);
    }
    
    public MethodInterceptor getInterceptor(Advisor advisor) {
        MethodBeforeAdvice advice = (MethodBeforeAdvice) advisor.getAdvice();
        return new MethodBeforeAdviceInterceptor(advice);
    }
}
```

### Decorator
在项目中我们遇到这样一个问题：我们的项目需要连接多个数据库，而且不同的客户在每次访问中需要去访问不同的数据库。

我们以往Spring和Hibernate框架中总是配置一个数据源，因而sessionFactory和dataSource属性总是指向这个数据源并且恒定不变，所有DAO在使用sessionFactory
的时候都是通过这个数据源访问数据库。

但是现在，由于项目的需要，我们的DAO访问sessionFactory的时候都不得不在多个数据中不断切换，问题就出现了： **如何让sessionFactory在执行数据持久化
的时候，根据客户的需求能够动态切换不同的数据源？**我们能不能在spring的框架下通过少量修改得到解决？是否有什么设计模式可以利用？

- 首先想到Spring的applicationContext中配置所有的dataSource。这些可能是不同的类型比如不同的数据库，也可能是不同数据源：
比如Apache提供的org.apache.commons.dbcp.BasicDataSource或者Spring提供的eorg.springframework.jndi.JndiObjectFactoryBean等。
然后sessionFactory根据客户的每次请求，将dataSource属性设置成不同的数据源，以达到切换数据源的目的。
- Spring中用到的装饰器模式在类名上有两种表现，基本上都是动态的给一个对象添加一些额外的职责：
  - 类名中含有Wrapper
  - 类名中含有Decorator
  
### Observer
定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。

- Spring中的Observer模式常用的地方是listener的实例。比如ApplicationListener。

### Proxy
为其他对象提供一种代理以控制对这个对象的访问。

从结构上来看和Decorator模式类似，但Proxy是控制，更像是一种对功能的限制，而Decorator是增加职责。

- Spring的Proxy模式在AOP中有体现，比如JdkDynamicAopProxy和Cglib2AopProxy。

### Strategy
定义了一系列的算法，把他们一个个封装起来，并且使他们可以互相替换。本模式使得算法可以独立于使用它的客户而变化。

- Spring中在实例化对象的时候用到Strategy模式在SimpleInstantiationStrategy中有如下代码：
```java
//TO DO: add code
```

### Template
定义一个操作中的算法的骨架，而将一些步骤延迟到子类中(之前某个模式是此模式的特殊情况)。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

Template Method模式一般是需要继承的。这里要探讨另一种对Template Method的理解。

Spring中的```JdbcTemplate```，在使用这个类的时候并不想去继承这个类，因为这个类的方法太多，但我们还是想用到```JdbcTemplate```已有的稳定的公用的
数据库连接，该如何做到？我们可以把变化的东西抽出来作为一个参数传入```JdbcTemplate```的方法中。但是变化的东西是一段代码，而且这段代码回用到```JdbcTemplate```
中的变量。怎么办？**回调对象**

在这个回调对象中定义一个操纵```JdbcTemplate```中变量的方法，我们去实现这个方法，就把变化的东西集中到这里了。然后我们再传入这个回调对象到JdbcTemplate
，从而完成了调用。这可能是Template Method不需要继承的另一种实现方式。

