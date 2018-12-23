# 课程总结
## 注解
### @RestController
以json格式返回
### @GetMapping
@RequestMapping(method = RequestMethod.GET)的缩写
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