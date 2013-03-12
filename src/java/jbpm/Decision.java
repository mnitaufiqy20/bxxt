package jbpm;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.pvm.internal.env.ExecutionContext;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-8
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class Decision implements DecisionHandler {
    @Override
    public String decide(OpenExecution arg0) {
        String str = arg0.getVariable("type").toString();
        if("A".equals(str)){
            return "A";
        }else {
            return "to2";
        }
    }
}
