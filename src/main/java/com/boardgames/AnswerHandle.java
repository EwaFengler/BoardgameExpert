package com.boardgames;

import org.kie.api.KieBase;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class AnswerHandle {
  private Object answer;
  private FactHandle factHandle;
  private KieSession session;
  private FactType answerType;

  private static final String ANSWER_TYPE_NAME = "Answer";
  private static final String RULES_PACKAGE_NAME = "com.boardgames";

  AnswerHandle(Object answer, KieSession session) {
    this.answer = answer;
    this.session = session;

    answerType = getAnswerType();
    factHandle = null;
  }

  public void toggleAnswer(){
    if(factHandle == null){
      factHandle = session.insert(answer);
    }
    else {
      session.delete(factHandle);
    }
  }

  public String getAnswerString(){
    return (String) answerType.get(answer, "answer");
  }

  private FactType getAnswerType() {
    KieBase kieBase = session.getKieBase();
    return kieBase.getFactType(RULES_PACKAGE_NAME, ANSWER_TYPE_NAME);
  }
}
