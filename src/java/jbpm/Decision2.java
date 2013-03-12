package jbpm;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-8
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class Decision2 implements DecisionHandler {
    @Override
    public String decide(OpenExecution arg0) {
        String str = arg0.getVariable("type").toString();
        if("B".equals(str)){
            return "B";
        }else {
            return "to3";
        }
    }
}
