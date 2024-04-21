package web.internet.magazin.project2mini.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;
import web.internet.magazin.project2mini.entity.Order;
import web.internet.magazin.project2mini.entity.User;
import web.internet.magazin.project2mini.entity.enums.Status;
import web.internet.magazin.project2mini.repo.OrderRepo;
import web.internet.magazin.project2mini.repo.UserRepo;

import java.util.Random;

@WebListener
public class DBData implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initialData();
        ServletContextListener.super.contextInitialized(sce);
    }

    private void initialData() {
        UserRepo userRepo = new UserRepo();
        OrderRepo orderRepo = new OrderRepo();
        if (userRepo.findAll().isEmpty()) {
            String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
            User user1 = User.builder()
                    .name("Eshmat")
                    .email("eshmat@gmail.com")
                    .password(hashpw)
                    .build();
            User user2 = User.builder()
                    .name("Toshmat")
                    .email("toshmat@gmail.com")
                    .password(hashpw)
                    .build();
            User user3 = User.builder()
                    .name("Qudrat")
                    .email("qudrat@gmail.com")
                    .password(hashpw)
                    .build();
            userRepo.save(user1);
            userRepo.save(user2);
            userRepo.save(user3);
        }
        if (orderRepo.findAll().isEmpty()) {
            int i = 0;
            Random random = new Random();
            while (i < 10) {
                Order order = Order.builder()
                        .status(Status.values()[random.nextInt(0, 3)])
                        .user(userRepo.findAll().get(random.nextInt(0, 3)))
                        .build();
                orderRepo.save(order);
                i++;
            }
        }
    }
}
