# spring学习总结 #

IOC:控制反转，典型的工厂模式。传统的Java SE程序设计中，通过new创建对象，是程序主动创建对象；而IOC是有专门一个容器来创建这些对象，即由IOC容器来控制对象的创建，主要控制外部资源获取。

传统应用程序是由我们自己在对象中主动控制去直接获取依赖对象，也就是正转；而反转是由容器来帮忙创建及注入依赖对象。为何是反转？因为由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象。所谓反转是指依赖对象的获取被反转了。

IOC和DI，DI即依赖注入，由容器动态的将某个依赖关系注入到组件中。
所谓依赖是指应用程序依赖于IOC容器。
应用程序需要IOC容器来提供对象需要的外部资源。
依赖注入明确描述了被注入对象依赖IOC容器配置依赖对象。

1.IOC可以通过无参构造构造来创建对象

    <bean id="stu" class="com.zl.springdemo.Student"></bean>
配置一个bean标签：
id：对象名。class：对象的模板类

2.调用API获取对象
spring获取对象的方式有两种:id和运行时类。
1）通过id获取对象
       
     ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    
    Student stu = (Student)applicationContext.getBean("stu");
    System.out.println(stu);
加载spring.xml配置文件，生成applicationContext对象。
调用ApplicationContext的getBean方法获取对象，参数为配置文件中的id值。

**注意：程序在加载spring.xml时创建stu对象，通过反射机制调用无参构造函数，所有要求交给IOC容器管理的类必须有无参构造函数。**

无参构造只能创建对象而不能赋值，所以需要添加property标签：name对应属性名，value是属性的值。
 <!-- 配置student对象 -->
     <bean id="stu" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name">
            <value><![CDATA[<张三>]]></value>
        </property>
        <property name="age" value="23"></property>
     </bean>
**注意:Spring通过调用每个属性的setter方法来完成属性的赋值。所以实体类必须有setter方法。**

2）通过运行时类获取对象

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    //2.通过运行时类获取对象
    Student stu = applicationContext.getBean(Student.class);
    System.out.println(stu);
**注意：IOC容器通过运行时类调用对象时必须指定唯一的bean，多个对象会报错。**

IOC容器通过有参构造创建对象

<!-- 通过有参构造函数创建对象 -->
     <bean id="stu3" class="com.hzit.entity.Student">
        <constructor-arg name="id" value="3"></constructor-arg>
        <constructor-arg name="name" value="小明"></constructor-arg>
        <constructor-arg name="age" value="22"></constructor-arg>
     </bean>

IOC容器会根据constructor-arg标签加载对应的有参构造函数，创建对象并完成属性赋值。
name的值需要与有参构造的形参名对应，value是对应的值。
除了使用name对应的参数外，还可以通过下标index对应。

 <!-- 通过有参构造函数创建对象 -->
     <bean id="stu3" class="com.hzit.entity.Student">
        <constructor-arg index="0" value="3"></constructor-arg>
        <constructor-arg index="1" value="小明"></constructor-arg>
        <constructor-arg index="2" value="22"></constructor-arg>
     </bean>


在spring.xml中,通过ref属性将其他bean赋给当前bean对象

 <!-- 创建classes对象 -->
     <bean id="classes" class="com.hzit.entity.Classes">
        <property name="id" value="1"></property>
        <property name="name" value="Java班"></property>
     </bean>

     <!-- 创建stu对象 -->
     <bean id="stu" class="com.hzit.entity.Student">
        <property name="id" value="1"></property>
        <property name="name">
            <value><![CDATA[<张三>]]></value>
        </property>
        <property name="age" value="23"></property>
        <!-- 将classes对象赋给stu对象 -->
        <property name="classes" ref="classes"></property>

集合属性通过list标签和ref标签完成注入。ref的bean属性指向需要注入的bean对象。

     <property name="students">
    <!-- 注入student对象 -->
    <list>
    <ref bean="stu"/>
    <ref bean="stu2"/>
    </list>
    </property>