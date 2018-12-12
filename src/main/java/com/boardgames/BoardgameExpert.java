package com.boardgames;

import com.boardgames.gui.BoardgameExpertGUI;
import com.boardgames.gui.CheckboxPanel;
import com.boardgames.gui.RadioButtonPanel;
import com.boardgames.gui.WelcomePanel;
import org.kie.api.runtime.KieSession;

import javax.swing.*;


public class BoardgameExpert {

  private BoardgameExpertGUI boardgameExpertGUI;
  private JPanel contentPanel;

  private KieSession session;

  void init(KieSession session) {
    this.session = session;

    boardgameExpertGUI = new BoardgameExpertGUI();
    boardgameExpertGUI.createAndShow();

    contentPanel = boardgameExpertGUI.getContentPanel();

    showWelcomeScreen();
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

  private void showWelcomeScreen() {
    boardgameExpertGUI.setTextToTopLabel("How To Choose The Perfect Boardgame");

    WelcomePanel welcomePanel = new WelcomePanel();
    contentPanel.add(welcomePanel.$$$getRootComponent$$$());

    session.insert(this);
    boardgameExpertGUI.getBottomButton().addActionListener(e -> session.fireAllRules());
  }
}
