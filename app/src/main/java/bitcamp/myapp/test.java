package bitcamp.myapp;

import java.time.LocalDate;

public class test {

  public static void main(String[] args) {
    LocalDate minusMonths = LocalDate.now().minusMonths(1);
    System.out.println(minusMonths);
  }
}
