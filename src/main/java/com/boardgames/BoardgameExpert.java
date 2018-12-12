package com.boardgames;

import com.boardgames.gui.*;
import org.kie.api.runtime.KieSession;

import javax.swing.*;


public class BoardgameExpert {

  private BoardgameExpertGUI boardgameExpertGUI;
  private JPanel contentPanel;

  private KieSession session;
  private ResultPanel resultPanel = new ResultPanel();

  void init(KieSession session) {
    this.session = session;

    boardgameExpertGUI = new BoardgameExpertGUI();
    boardgameExpertGUI.createAndShow();

    contentPanel = boardgameExpertGUI.getContentPanel();

    showWelcomeScreen();
  }

  public void addResult(String result) {
    resultPanel.addToResultList(result);
  }

  public void showSingleChoiceQuestion(String question, Object firstAnswerObject, Object secondAnswerObject) {
    boardgameExpertGUI.setTextToTopLabel(question);

    AnswerHandle answerHandle1 = new AnswerHandle(firstAnswerObject, session);
    AnswerHandle answerHandle2 = new AnswerHandle(secondAnswerObject, session);
    RadioButtonPanel radioButtonPanel = new RadioButtonPanel(answerHandle1, answerHandle2);

    contentPanel.removeAll();
    contentPanel.add(radioButtonPanel.$$$getRootComponent$$$());
  }

  public void showMultipleChoiceQuestion(String question, Object firstAnswerObject, Object secondAnswerObject) {
    boardgameExpertGUI.setTextToTopLabel(question);

    AnswerHandle answerHandle1 = new AnswerHandle(firstAnswerObject, session);
    AnswerHandle answerHandle2 = new AnswerHandle(secondAnswerObject, session);
    CheckboxPanel checkboxPanel = new CheckboxPanel(answerHandle1, answerHandle2);

    contentPanel.removeAll();
    contentPanel.add(checkboxPanel.$$$getRootComponent$$$());
  }

  public void showResultScreen() {
    boardgameExpertGUI.setTextToTopLabel("You should choose: ");

    contentPanel.removeAll();
    contentPanel.add(resultPanel.$$$getRootComponent$$$());

    boardgameExpertGUI.getBottomButton().setText("Close");
    boardgameExpertGUI.getBottomButton().addActionListener(e -> boardgameExpertGUI.close());
  }

  private void showWelcomeScreen() {
    boardgameExpertGUI.setTextToTopLabel("How To Choose The Perfect Boardgame");

    WelcomePanel welcomePanel = new WelcomePanel();
    contentPanel.add(welcomePanel.$$$getRootComponent$$$());

    session.insert(this);
    boardgameExpertGUI.getBottomButton().addActionListener(e -> session.fireAllRules());
  }
}
