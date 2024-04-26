package sample.service;

public class PaymentService {

  public int processPayment(int userId, int total) {
    System.out.println("Processing payment for user " + userId + " and total price " + total);
    return 42;
  }

}
