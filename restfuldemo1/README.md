# 课程总结
## 注解

### @RestController
以json格式返回
### @GetMapping(类)

@RequestMapping(method = RequestMethod.GET)的缩写

#### @GetMapping("/{id}")(方法)

请求地址为http://localhost:8080/xxxxx/id

{id}为传递的参数，如下所示

```java
@GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable int id)
    {
    }
```

#### @PostMapping

```java
@PostMapping
public TvSeriesDto insertOne(@RequestBody TvSeriesDto tvSeriesDto)
{
```

#### @PutMapping("/{id}")

```java
@PutMapping("/{id}")
    public TvSeriesDto updateOne(@PathVariable int id, @RequestBody TvSeriesDto tvSeriesDto)
    {
```

#### @DeleteMapping("/{id}")

```java
@DeleteMapping("/{id}")
    public Map<String, String> deleteOne(@PathVariable int id, HttpServletRequest request, @RequestParam(value = "delete_reason", required = false) String deleteReason)
    {
```

#### @RequestParam

@RequestParam(value="delete_reason", required=false) String deleteReason 表示deleteReason参数的值，来自Request的参数delete_reason（等同于request.getParameter("delete_reason")，可以是URL中Querystring，也可以是form post里的值），required=false表示不是必须的。默认是required=true，required=true时，如果请求没有传递这个参数，会被返回400错误。

类似的注解还有@CookieValue @RequestHeader等。

### @PathVariable

参数绑定，获取到@RequestMapping注解传递的参数的值并绑定到方法的参数上

```java
@GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable int id)
    {
        
或者指定@PathVariable使用哪一个URL中的变量
@GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable("id") int id)
    {
```

### @RequestBody

该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区

在异步请求时，返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用；

```java
//将RequestBody中的数据转换为TvSeriesDto格式
public TvSeriesDto insertOne(@RequestBody TvSeriesDto tvSeriesDto)
    {
        if (log.isTraceEnabled())
        {
            log.trace("这里应该写新增tvSeriesDto到数据库的代码，传递进来的参数是:" + tvSeriesDto);
        }
        tvSeriesDto.setId(9999);
        return tvSeriesDto;
    }
```



### @SpringBootApplication

@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。

- @Configuration：提到@Configuration就要提到他的搭档@Bean。使用这两个注解就可以创建一个简单的spring配置类，可以用来替代相应的xml配置文件。

  ```xml
  <beans> 
      <bean id = "car" class="com.test.Car"> 
          <property name="wheel" ref = "wheel"></property> 
      </bean> 
      <bean id = "wheel" class="com.test.Wheel"></bean> 
  </beans> 
  ```

  相当于

  ```java
  @Configuration 
  public class Conf { 
      @Bean 
      public Car car() { 
          Car car = new Car(); 
          car.setWheel(wheel()); 
          return car; 
      } 
      @Bean  
      public Wheel wheel() { 
          return new Wheel(); 
      } 
  }
  ```

  @Configuration的注解类标识这个类可以使用Spring IoC容器作为bean定义的来源。@Bean注解告诉Spring，一个带有@Bean的注解方法将返回一个对象，该对象应该被注册为在Spring应用程序上下文中的bean。

- @EnableAutoConfiguration：能够自动配置spring的上下文，试图猜测和配置你想要的bean类，通常会自动根据你的类路径和你的bean定义自动配置。

- @ComponentScan：会自动扫描指定包下的全部标有@Component的类，并注册成bean，当然包括@Component下的子注解@Service,@Repository,@Controller。

### @RunWith(SpringRunner.class)
让测试运行于Spring测试环境
### @SpringBootTest

SpringBoot的单元测试

### springboot测试步骤

直接在测试类上面加上如下2个注解
@RunWith(SpringRunner.class)
@SpringBootTest
就能取到spring中的容器的实例，如果配置了@Autowired那么就自动将对象注入。

## yml文件与properties文件

- yml是树形结构

- 在properties文件中是以”.”进行分割的， 在yml中是用”:”进行分割; 

- yml的数据格式和json的格式很像，都是K-V格式，并且通过”:”进行赋值； 

- 在yml中缩进一定不能使用TAB，否则会报很奇怪的错误；（缩进特么只能用空格！！！！） 

- 每个k的冒号后面一定都要加一个空格； 

- 使用spring cloud的maven进行构造的项目，在把properties换成yml后，一定要进行mvn clean insatll

  yml

  ```yml
  spring:
    jackson:
      date-format: yyyy-MM-dd
      time-zone: GMT+8
      serialization:
        write-dates-as-timestamps: false
  ```

  properties

  ```properties
  spring.jackson.date-format=yyyy-MM-dd
  spring.jackson.time-zone=GMT+8
  spring.jackson.serialization.write-dates-as-timestamps=false
  ```

## 日期型转json格式

- 可以在属性上增加@JsonFormat(timezone=“GMT+8”,pattern=“yyyy-MM-dd”),或者@JsonFormat(shape = JsonFormat.Shape.NUMBER)

  ```java
  @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
  private Date originRelease;
  ```

- 全局修改:application.yml中

  ```yml
  spring:
    jackson:
      date-format: yyyy-MM-dd  #如果使用字符串表示，用这行设置格式
      time-zone: GMT+8
      serialization:
        write-dates-as-timestamps: false  #使用数值timestamp表示日期
  ```

## 热部署(Hot Swapping)

- 在pom中引入：都需要为true

  ```xml
  <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-devtools</artifactId>
  	<optional>true</optional>
  </dependency>
  ```

  ```xml
  <plugin>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-maven-plugin</artifactId>
     <configuration>
        <fork>true</fork>
     </configuration>
  </plugin>
  ```

- idea编译器设置file→setting→compiler

  ```xml
  build project automatically
  ```

- 快捷键ctrl+alt+shift  /，勾选

  ```
  compiler.automake.allow.when.app.running
  ```

- 重启项目

## [日志记录](https://blog.csdn.net/ztchun/article/details/79804583)

- Commons-logging or SLF4j
  - private static final Log log = LogFactory.getLog(Xxxx.class);
  - private static final Logger logger = LoggerFactory.getLogger(Xxxx.class);

- 日志级别

  TRACE<DEBUG<INFO<WARN<ERROR<FATAL

- application.yml配置日志

  ```yml
  logging:
    file: target/app.log
    level:
      ROOT: WARN
      me.groad: TRACE
      me.groad.restfuldemo1.controller: DEBUG
  ```

- 在方法中引入

  ```java
  import org.apache.commons.logging.Log;
  import org.apache.commons.logging.LogFactory;
  
  public class SampleClass {
      private static final Log log = LogFactory.getLog(SampleClass.class);
      
      public void print(String name) {
          if(log.isTraceEnabled()) {
              log.trace("传入参数是： " + name);
          }
          
          try {
              // do something
          }catch(Exception e) {
              if(log.isErrorEnabled()) {
                  log.error("出错啦", e);
              }
          }
      }
      … …
  }
  ```

## 数据校验(JSR标准)Bean Validation

### @Null   

验证对象是否为空

### @NotNull    

验证对象是否为非空

### @Min    

验证 Number 和 String 对象是否大等于指定的值

### @Max    

验证 Number 和 String 对象是否小等于指定的值

### @Size   

验证对象（Array,Collection,Map,String）长度是否在给定的范围之内

### @Past   

验证 Date 和 Calendar 对象是否在当前时间之前

### @Future 

验证 Date 和 Calendar 对象是否在当前时间之后

### @AssertTrue 

验证 Boolean 对象是否为 true

### @AssertFalse    

验证 Boolean 对象是否为 false

### @Valid 

级联验证注解(加在bean前面，表示这个bean内部的bean也需要验证)

### 按类型划分

|     类型      |                             注解                             |
| :-----------: | :----------------------------------------------------------: |
|   任何类型    |                        Null, NotNull                         |
|    布尔型     |                   AssertTrue, AssertFalse                    |
|    字符串     | NotBlank,   Pattern, Size, Email,DecimalMax, DecimalMin, Digits |
|     数值      | DecimalMax, DecimalMin, Digits, Max, Min,Negative, NegativeOrZero, Positive, PositiveOrZero |
| 集合/Map/List |                       NotEmpty,   Size                       |
|     日期      |        Feature, Past, FeatureOrPresent, PastOrPresent        |

​    ※ 以上注解都指Bean Validation 2.0定义的注解，在javax.validation.constraints包下。

   Hibernate有些非JSR标准注解和上面的同名但package不同，功能会和上表这些有些细微差异

### 注解的位置

- Field level                成员变量

- Property level          get(is) 方法上(验证返回值)

- Class level               类上

```java
//images不可为null，每个元素(Image类型）需要级联验证
@NotNull
private List<@Valid Image> images;

//images长度至少为1，不可为null，每个元素（String类型）不可为null，最短长度10
@NotNull
@Size(min=1)
private List<@Size(min=10)  @NotNull String> images;   

@AssertTrue
public boolean isValid() {
    //这里验证类的各种成员变量组合
    return true;
}
```

### 约束规则对子类依旧有效

### groups参数

适用场景

操作分步进行
第一步，输入名字等基本信息，
第二步，验证手机号码

分角色，不同修改权限
管理员可修改的内容比一般用户多

- 每个约束用注解都有一个groups参数
- 可接收多个class类型（必须是接口）
- 不声明groups参数是默认组javax.validation.groups.Default
- 声明了groups参数的会从Default组移除，如需加入Default组需要显示声明，例如@Null(groups={Default.class, Step1.class})

### @Valid vs. @Validated

- @Valid是JSR标准定义的注解，只验证Default组的约束
- @Validated是spring定义的注解，可以通过参数来指定验证的组，例如：@Validated({Step1.class, Default.class})表示验证Step1和Default两个组的约束
- @Valid可用在成员变量上，进行级联验证；@Validated只能用在参数上，表示这个参数需要验证

### BindingResult

```java
org.springframework.validation.BindingResult
```

```java

public Object doSomething(@Validated @RequestBody OneDto oneDto) {
     // 通不过校验的参数，不会执行这个方法
}
———————————————————————————————————————————————————————————————————————
public Object doSomething(@Validated @RequestBody OneDto oneDto, 
                          BindingResult result) {
    // 参数通不过校验也会进入方法执行，校验结果会通过result参数传递进来
    if (result.hasErrors()){
         // 没通过校验
    }
}

```

### 手动验证

```java
// 装载验证器
@Autowired Validator validator;


// 验证某个类，下面是执行默认的验证组，如果需要指定验证组，多传一个class参数
Set<ConstraintViolation<?>> result = validator.validate(obj);
 

// 通不过校验result的集合会有值，可以通过size()判断

```

### 序列

![序列.png](https://i.loli.net/2019/01/04/5c2f7a64bb0ed.png)

### 约束用注解

![Bean-Validation1.jpg](https://i.loli.net/2019/01/04/5c2f7c97c3230.jpg)

![Bean-Validation2.jpg](https://i.loli.net/2019/01/04/5c2f7c97aceea.jpg)

## Spring-Boot+Mybatis

### 层级注解

![层级注解.png](https://i.loli.net/2019/01/05/5c2f84fd028cc.png)

### PBF vs PBL

![PBFvsPBL.png](https://i.loli.net/2019/01/04/5c2f7f9a01aea.png)

 

### 各种xo

![xo.png](https://i.loli.net/2019/01/05/5c2f873e06b40.png)



### JavaBean

- 有一个public的无参数建构方法
- 属性private，且可以通过get、set、is（可以替代get，用在布尔型属性上）方法或遵循特定命名规范的其他方法访问
- 可序列化，实现Serializable接口

### POJO vs. JavaBean



- POJO比javabean更简单。POJO严格地遵守简单对象的概念，而一些JavaBean中往往会封装一些简单逻辑。
- POJO主要用于数据的临时传递，它只能装载数据， 作为数据存储的载体，不具有业务逻辑处理的能力。
- Javabean虽然数据的获取与POJO一样，但是javabean当中可以有其它的方法

### 数据传输过程

![转换.png](https://i.loli.net/2019/01/05/5c2f87ec89729.png)

### 添加Mybatis支持

1. 修改pom.xml，添加mybatis支持
2. 修改application.yml添加数据库连接
3. 修改启动类，增加@MapperScan(“package-of-mapping”)注解
4. 添加Mybatis Mapping接口
5. 添加Mapping对应的XML（可选）

```xml
<!--mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>

<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>provided</scope>
</dependency>

<!--Mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.0.7</version>
</dependency>

<!--Druid数据源-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.10</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
```

```yaml
spring:
  datasource:
    dbcp2.validation-query: select 1
    driverClassName: org.postgresql.Driver
    url: jdbc:postgresql://127.0.0.1:5432/thedb?stringtype=unspecified
    username: pguser
    password: passwordofpguser
```

