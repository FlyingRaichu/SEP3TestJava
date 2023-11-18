package via.sep4.sep4test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication public class Sep4TestApplication
{
  //todo figure out when to use a reference to an object and when to use the object, bc the connections are a nightmare

  //todo issue is that if I create an order, then other elements must be in the database for me to create an order
  public static void main(String[] args) {
    SpringApplication.run(Sep4TestApplication.class, args);
  }

}
