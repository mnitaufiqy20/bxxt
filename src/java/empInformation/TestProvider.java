package empInformation;

import com.capgemini.eis.adapter.framework.common.CacheManager;
import com.capgemini.eis.adapter.framework.config.ConfigConstants;
import com.capgemini.eis.adapter.framework.exception.EisException;
import com.capgemini.eis.adapter.framework.provider.IServiceProvider;
import com.capgemini.eis.adapter.framework.provider.impl.BaseServiceProvider;
import com.capgemini.eis.adapter.framework.utils.log.LogUtil;

public class TestProvider {
    // MQ连接池最大连接数
    public static int CONNECTION_MAXNUM = 2;

    // 从MQ连接池获取连接的超时时间(秒)
    public static int GET_CONNECTION_TIMEOUT =10;

    public static void main(String args[]) {
        try {
            test();
        } catch (EisException e) {
            LogUtil.getInstance().error(EisException.getTrace(e));
        }
    }

    private static void test() throws EisException {
        // 获取服务提供方处理对象
        IServiceProvider Provider = BaseServiceProvider.getInstance();

        // 获取提供方处理线程数
        int providerThreadNum = Integer.parseInt(CacheManager.getInstance()
                .getConfig().getProperty(
                        ConfigConstants.PROVIDER_HANDLER_MAXNUM));

        // 生成处理线程
        for (int i = 0; i < providerThreadNum; i++) {
            new Thread(Provider).start();
        }

        // 启动处理线程
        Provider.start();
    }
}
