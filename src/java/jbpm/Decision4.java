package jbpm;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-8
 * Time: 上午11:02
 * To change this template use File | Settings | File Templates.
 */
public class Decision4 implements DecisionHandler {
    @Override
    public String decide(OpenExecution arg0) {
        String str = arg0.getVariable("type").toString();
        if("D".equals(str)){
            return "D";
        }else {
            return "to5";
        }
    }
}
