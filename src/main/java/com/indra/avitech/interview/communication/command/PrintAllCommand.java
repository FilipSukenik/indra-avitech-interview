package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.printing.PrintService;

public record PrintAllCommand(PrintService printService) implements Command {

  @Override
  public void execute() {

  }
}
