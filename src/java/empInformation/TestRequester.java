package empInformation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.capgemini.eis.adapter.framework.common.CacheManager;
import com.capgemini.eis.adapter.framework.config.ConfigConstants;
import com.capgemini.eis.adapter.framework.connection.IConnection;
import com.capgemini.eis.adapter.framework.connection.IConnectionPoolManager;
import com.capgemini.eis.adapter.framework.connection.mqc.MQParameter;
import com.capgemini.eis.adapter.framework.connection.mqcm.PoolableMQConnectionManager;
import com.capgemini.eis.adapter.framework.exception.EisException;
import com.capgemini.eis.adapter.framework.message.IMsgObject;
import com.capgemini.eis.adapter.framework.message.impl.MsgObject;
import com.capgemini.eis.adapter.framework.requester.IServiceRequester;
import com.capgemini.eis.adapter.framework.requester.impl.BaseServiceRequester;
import com.capgemini.eis.adapter.framework.utils.log.ILog;
import com.capgemini.eis.adapter.framework.utils.log.LogUtil;



public class TestRequester {
    public static String encoding = "utf-8";

    //MQ连接池最大连接数
    public static int CONNECTION_MAXNUM = 2;

    //从MQ连接池获取连接的超时时间(秒)
    public static int GET_CONNECTION_TIMEOUT = 10;

    public static void main(String args[]) throws EisException, IOException
    {
        try {
            TestRequester.requester1();
//			TestRequester.requester2();
//			TestRequester.requester3();
//			byte[] grpid = TestRequester.sendBySegment2();
//			TestRequester.receiveBySegment2(grpid);
        } catch (Exception e) {
            LogUtil.getInstance().error(EisException.getTrace(e));
        }
    }

    /**
     * 使用BaseServiceRequester进行服务调用
     * @throws Exception
     */
    public static void requester1() throws Exception {
        //获取服务调用者实例
        IServiceRequester Requester = BaseServiceRequester.getInstance();

        //定义请求报文对象
        IMsgObject reqMo = new MsgObject();

        //对请求报文对象进行设置
        reqMo.setServiceID("00160000000001");

        reqMo.setControlValue("control1", "a1");
        reqMo.setReqValue("req1", "v1");
        reqMo.setResValue("res1", "v1");

        System.out.println(reqMo);
        IMsgObject resMo = Requester.execute(reqMo);

    }

    /**
     * 独立控制服务请求和应答
     * @throws EisException
     * @throws IOException
     */
    public static void requester2()throws EisException, IOException {
        //自定义初始化MQ连接池
        MQParameter req = new MQParameter("localhost",40001,"SVRCONN_GW_IN",1208,"QM_DEMO","EIS.QUEUE.REQUEST.IN");
        MQParameter res = new MQParameter("localhost",40001,"SVRCONN_GW_IN",1208,"QM_DEMO","EIS.QUEUE.REQUEST.OUT.OA");
        IConnectionPoolManager cm = new PoolableMQConnectionManager(req, res, CONNECTION_MAXNUM, GET_CONNECTION_TIMEOUT);

        //从MQ连接池获取一个连接
        IConnection connection = cm.getConnection();

        //通过文件生成请求报文
        InputStream fi = new FileInputStream("data/request.xml");
        byte[] reqMsg = new byte[fi.available()];
        fi.read(reqMsg);
        fi.close();



        //调用ESB服务
        @SuppressWarnings("unused")
        byte[] resMsg = connection.request(reqMsg);

//		LogUtil.getInstance().debug((new String(resMsg,encoding)));

        //将连接归还给MQ连接池 (连接并没有关闭)
        cm.releaseConnection(connection);
        //关闭连接 (最后关闭程序时需要此步骤)
        connection.release();
        //关闭MQ连接池 (最后关闭程序时需要此步骤)
        cm.close();
    }

    /**
     * 独立控制服务请求和应答
     * @throws Exception
     * @throws IOException
     */
    public static void requester3()throws Exception {

        ILog logUtil = CacheManager.getInstance().getLogUtil();
        Properties config = CacheManager.getInstance().getConfig();

        //按配置文件(./config/config.properties)初始化MQ连接池
        MQParameter req = new MQParameter(config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_IP),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_PORT)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_CHANNEL),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_CCSID)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_QMANAGER),
                config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_QUEUE));
        MQParameter res = new MQParameter(config.getProperty(ConfigConstants.MQ_REQUESTER_RES_IP),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_RES_PORT)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_RES_CHANNEL),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_RES_CCSID)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_RES_QMANAGER),
                config.getProperty(ConfigConstants.MQ_REQUESTER_RES_QUEUE));

        IConnectionPoolManager cm = new PoolableMQConnectionManager(req, res,
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_POOL_MAXNUM)),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_GETCONN_TIMEOUT)));


        //从MQ连接池获取一个连接
        IConnection connection = cm.getConnection();

        //定义请求报文对象
        IMsgObject reqMo = new MsgObject();

        //对请求报文对象进行设置
        //设置请求报文头(Service_Header)信息
        //reqMo.setServiceID("00160000000001");
        reqMo.setSourceSysID("01");
        //设置请求报文体信息
        reqMo.setReqValue("TEST1", "test1");
        reqMo.setReqValue("PP0020", "035001005000003588");
        reqMo.setReqValue("PP0621", "PZ00000000");
        reqMo.setReqValue("username", "wang");

        System.out.println(reqMo.toString());

        //调用ESB服务
        byte[] resMsg = connection.request(reqMo.getBytes());

        //返回应答报文对象
        IMsgObject resMo = new MsgObject(resMsg,IMsgObject.MOType.initSP);

        //打印数据域
        logUtil.debug(resMo.getReqValue("TEST1"));
        logUtil.debug(resMo.getReqValue("AAA"));
        logUtil.debug(resMo.getReqValue("greeting"));

        //将连接归还给MQ连接池 (连接并没有关闭)
        cm.releaseConnection(connection);
        //关闭连接 (最后关闭程序时需要此步骤)
        connection.release();
        //关闭MQ连接池 (最后关闭程序时需要此步骤)
        cm.close();
    }

    public static void requester4()throws Exception {

        ILog logUtil = CacheManager.getInstance().getLogUtil();
        Properties config = CacheManager.getInstance().getConfig();

        //按配置文件(./config/config.properties)初始化MQ连接池
        MQParameter req = new MQParameter(config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_IP),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_PORT)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_CHANNEL),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_CCSID)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_QMANAGER),
                config.getProperty(ConfigConstants.MQ_REQUESTER_REQ_QUEUE));
        MQParameter res = new MQParameter(config.getProperty(ConfigConstants.MQ_REQUESTER_RES_IP),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_RES_PORT)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_RES_CHANNEL),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_RES_CCSID)),
                config.getProperty(ConfigConstants.MQ_REQUESTER_RES_QMANAGER),
                config.getProperty(ConfigConstants.MQ_REQUESTER_RES_QUEUE));

        IConnectionPoolManager cm = new PoolableMQConnectionManager(req, res,
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_POOL_MAXNUM)),
                Integer.parseInt(config.getProperty(ConfigConstants.MQ_REQUESTER_GETCONN_TIMEOUT)));


        //从MQ连接池获取一个连接
        IConnection connection = cm.getConnection();

        //通过文件生成请求报文
        InputStream fi = new FileInputStream("d:\\test1.xml");
        byte[] reqMsg = new byte[fi.available()];
        fi.read(reqMsg);
        fi.close();

        //调用ESB服务
        byte[] resMsg = connection.request(reqMsg);

        //返回应答报文对象
        IMsgObject resMo = new MsgObject(resMsg,IMsgObject.MOType.initSP);

        //打印数据域
        logUtil.debug(resMo.getReqValue("PP0390"));

        //将连接归还给MQ连接池 (连接并没有关闭)
        cm.releaseConnection(connection);
        //关闭连接 (最后关闭程序时需要此步骤)
        connection.release();
        //关闭MQ连接池 (最后关闭程序时需要此步骤)
        cm.close();
    }
}
