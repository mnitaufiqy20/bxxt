package empInformation;


import com.capgemini.eis.adapter.framework.message.IMsgObject;
import com.capgemini.eis.adapter.framework.message.impl.GroupRecord;
import com.capgemini.eis.adapter.framework.message.impl.MsgObject;

import java.util.ArrayList;
import java.util.List;

public class MoDemo {

    /**
     * MO对象构建示例类.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MoDemo demo = new MoDemo();
        IMsgObject mo = demo.getRequestMO();
        List<GroupRecord> grs =mo.getReqGroupRecord("test1");
        for(GroupRecord gr : grs){
            System.out.println("GroupRecord 's name is : "+gr.getName());
            System.out.println("test01 's value is :"+gr.getFieldValue("test01"));
            for(int i=0;i<gr.getFieldSize();i++){
                System.out.println(gr.getFieldKey(i));
            }
        }
    }


    public IMsgObject getRequestMO() throws Exception{
        //默认构建请求MO
        IMsgObject mo = new MsgObject();
        //设置业务编号
        mo.setBusinessCode("xxxxxxxx");
        //设置系统id
        mo.setSourceSysID("xxx");
        //设置Control元素内容.
        mo.setControlValue("userid", "xxx");
        //构建GroupRecord
        GroupRecord gr = new GroupRecord();
        gr.setName("test1");
        gr.setFieldValue("test01", "test01");
        gr.setFieldValue("test02", "test02");
        List<GroupRecord> grs = new ArrayList<GroupRecord>();
        grs.add(gr);
        grs.add(gr);
        //将GroupRecord添加到request节点下
        mo.setReqGroupRecord(grs);
        System.out.println(mo.toString());
        return mo;
    }

}
