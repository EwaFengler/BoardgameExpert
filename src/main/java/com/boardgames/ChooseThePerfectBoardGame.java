package com.boardgames;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ChooseThePerfectBoardGame {

  public static void main(String[] args) {

    KieServices ks = KieServices.Factory.get();
    BasicConfigurator.configure();
    Logger.getLogger(ChooseThePerfectBoardGame.class).setLevel(Level.OFF);

    KieContainer kContainer = ks.getKieClasspathContainer();
    KieSession session = kContainer.newKieSession("ksession-rules");

    BoardgameExpert boardgameExpert = new BoardgameExpert();
    boardgameExpert.init(session);
  }
}