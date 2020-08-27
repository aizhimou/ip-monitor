package best.asimov.monitor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * IP 地址通知器
 */
@SuppressWarnings("unused")
@Configuration
@EnableScheduling
public class IpChangeCourier {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IpChangeCourier.class);
    /**
     * 消息标题
     */
    private static final String title = "IP变更通知";
    /**
     * 是否自动存档标识
     */
    private static final String archive = "isArchive";
    /**
     * bark base url
     */
    private static final String url = "https://api.day.app/Uo4CwAwHW84Tm5TzXv436C";
    /**
     * 获取 IP 地址
     */
    private static final String getIpUrl = "whatismyip.akamai.com";
    /**
     * 初始 IP 地址
     */
    String lastIpAddress = "127.0.0.1";

    @Scheduled(cron = "0 0/30 * * * ?")
    public void ipNotification() {
        String nowIpAddress = HttpUtil.createGet(getIpUrl).execute().body();
        LOGGER.info("====================");
        LOGGER.info("last ip address: {}; new ip address: {}.", lastIpAddress, nowIpAddress);
        if (!lastIpAddress.equals(nowIpAddress)) {
            sentMessage(lastIpAddress, nowIpAddress);
        }
        lastIpAddress = nowIpAddress;
    }

    void sentMessage(String lastIpAddress, String nowIpAddress) {
        LOGGER.info("start sending message...");
        String content = StrUtil.format("上一个IP地址是:{}\n现在的IP地址是:{}", lastIpAddress, nowIpAddress);
        String finalUrl = StrUtil.format("{}/{}/{}?{}=1", url, title, content, archive);
        HttpUtil.get(finalUrl);
        LOGGER.info("send message success...");
    }
}
