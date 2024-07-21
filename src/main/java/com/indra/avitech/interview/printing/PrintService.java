package com.indra.avitech.interview.printing;

import com.indra.avitech.interview.database.model.User;
import java.util.List;

public class PrintService {

  public void printAll(List<User> users) {

    users.forEach(System.out::println);
  }
}
