package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      user5.addCar(new Car("Vaz", 2105, "x005xx"));
      userService.add(user5);
      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      user6.addCar(new Car("Vaz", 2106, "x006xx"));
      userService.add(user6);
      User user7 = new User("User7", "Lastname7", "user7@mail.ru");
      user7.addCar(new Car("Vaz", 2107, "x007xx"));
      userService.add(user7);
      userService.listUsers().forEach(System.out::println);
      Map.of("x007xx", 2107, "x006xx", 2106)
              .entrySet().stream()
              .map(x -> userService.getUserByCarNumberAndCarSeries(x.getKey(), x.getValue()))
              .forEach(System.out::println);
      context.close();
   }
}
