package sample.service;

import sample.model.Cart;

public class EmailService {

  public void sendEmail(int userId, Cart cart, int transactionId) {
    System.out.println(
        "Sending email to user " + userId + " for transaction " + transactionId + " with cart "
            + cart);
  }
}
