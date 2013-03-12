package jbpm

import org.jbpm.api.task.AssignmentHandler

import org.jbpm.api.task.Assignable
import org.jbpm.api.model.OpenExecution
import org.apache.commons.logging.LogFactory
import org.jbpm.pvm.internal.model.ExecutionImpl
import com.chd.bx.login.UserLogin
import com.chd.bx.login.UserLoginService

/**
 * jBPM4.4任务分配器
 * User:
 * Date: 12-12-19
 * Time: 下午7:30
 */
class AssignTask implements AssignmentHandler{

    //对应流程包中 AssignmentHandler角色参数（角色ID）
    String roleId;

    public void assign(Assignable assignable, OpenExecution execution) {
        ExecutionImpl executionImpl = (ExecutionImpl) execution;
        String id = (String)executionImpl.getVariable("userId");
        String first = (String)executionImpl.getVariable("first");
        String second = (String)executionImpl.getVariable("second");
        String third = (String)executionImpl.getVariable("third");
        String fourth = (String)executionImpl.getVariable("fourth");
        String fifth = (String)executionImpl.getVariable("fifth");

        //办理人
        String userId = "";
       // UserLoginService userService = SpringUtil.getBean("userService");
        if(roleId==""){
            userId = id;
        }else if(roleId.equals("firstExamAppName")){
            userId = first;
        }else if(roleId.equals("secondExamAppName")){
            userId = second;
        }else if(roleId.equals("thirdExamAppName")){
            userId = third;
        }else if(roleId.equals("fourthExamAppName")){
            userId = fourth;
        }else if(roleId.equals("fifthExamAppName")){
            userId = fifth;
        }
//        if(roleId==""){
//            userId = id;
//        }else if(first.equals("") && roleId.equals("firstExamAppName")){
//            userId = first;
//        }else if(second.equals("") && roleId.equals("secondExamAppName")){
//            userId = second;
//        }else if(third.equals("") && roleId.equals("thirdExamAppName")){
//            userId = third;
//        }else if(fourth.equals("") && roleId.equals("fourthExamAppName")){
//            userId = fourth;
//        }else if(fifth.equals("") && roleId.equals("fifthExamAppName")){
//            userId = fifth;
//        }

        if(userId!=null && !"".equals(userId)){
            String[] assigns = userId.split(",");
            assignUsers(assignable,assigns);
        }else{

        }
    }

    /**
     * 给任务分配用户
     * @param assignable
     *        Assignable对象
     * @param users
     *      待分配的用户
     */
    public void assignUsers(Assignable assignable, String[] users){
        if(users!=null && users.length==1){
            assignable.setAssignee(users[0]);
        }else{
            for(int i=0;i<users.length;i++){
                assignable.addCandidateUser(users[i]);
            }
        }
    }


}
