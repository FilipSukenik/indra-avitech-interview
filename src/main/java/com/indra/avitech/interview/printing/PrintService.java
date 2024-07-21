package com.indra.avitech.interview.printing;

import com.indra.avitech.interview.database.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintService {

  private static final Logger LOG = LoggerFactory.getLogger(PrintService.class);

  public void printAll(List<User> users) {

    users.forEach((user) -> LOG.info(user.toString()));
  }
}
