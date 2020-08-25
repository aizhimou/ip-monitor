package best.asimov.couriers.ip;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP 地址通知器
 */
@SuppressWarnings("unused")
public class IpChangeCouriers {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IpChangeCouriers.class);
    /**
     * 消息标题
     */
    private static final String title = "IP变更通知";
    /**
     * 自动存档
     */
    private static final String archive = "isArchive=1";
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
    String lastIpAddress = "0.0.0.0";

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
        String content = StrUtil.format("{} -> {}", lastIpAddress, nowIpAddress);
        String finalUrl = StrUtil.format("{}/{}/{}?{}", url, title, content, archive);
        LOGGER.info(finalUrl);
        HttpUtil.get(finalUrl);
        LOGGER.info("send message success...");
    }
}
