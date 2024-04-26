package sample;

import sample.model.Item;
import sample.model.User;
import sample.service.CartService;
import sample.service.EmailService;
import sample.service.PaymentService;
import sample.service.UserService;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class Straight {

  static final UserService userService = new UserService();
  static final CartService cartService = new CartService();
  static final PaymentService paymentService = new PaymentService();
  static final EmailService emailService = new EmailService();

  public static void main(String[] args) {
    Thread.ofVirtual().start(Straight::userFlow);
    userFlow();
    userFlowAsync();
  }

  private static void userFlow() {
    String name = "Alice";
    User user = userService.findUserByName(name);
    if (user == null) {
      user = userService.saveUser(name, 33);
    }

    var cart = cartService.findCartByUserId(user.id());
    var total = cart.items().stream().mapToInt(Item::price).sum();
    var transactionId = paymentService.processPayment(user.id(), total);
    emailService.sendEmail(user.id(), cart, transactionId);
  }

  private static void userFlowAsync() {
    String name = "Alice";
    var future = supplyAsync(() -> userService.findUserByName(name))
        .thenCompose(user -> allOf(
            supplyAsync(() -> user == null).thenAccept(doesNotExist -> {
              if (Boolean.TRUE.equals(doesNotExist)) {
                userService.saveUser(name, 33);
              }
            }),
            supplyAsync(() -> cartService.findCartByUserId(user.id())).thenApply(
                cart -> supplyAsync(() -> cart.items().stream().mapToInt(Item::price).sum())
                    .thenAccept(
                        transactionId -> emailService.sendEmail(user.id(), cart, transactionId))))
        );
    System.out.printf("User flow async started: %s%n", future);
  }
}
