package best.asimov.couriers.ip;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
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
    private static final Logger LOGGER = LoggerFactory.getLogger("IpChangeCouriers.class");
    /**
     * init ip address
     */
    String lastIpAddress = "0.0.0.0";

    public void ipNotification() {
        String nowIpAddress = HttpUtil.createGet("whatismyip.akamai.com").execute().body();
        LOGGER.info("====================");
        LOGGER.info("last ip address: {}; new ip address: {}.", lastIpAddress, nowIpAddress);
        if (!lastIpAddress.equals(nowIpAddress)) {
            sentEmail(lastIpAddress, nowIpAddress);
        }
        lastIpAddress = nowIpAddress;
    }

    void sentEmail(String lastIpAddress, String nowIpAddress) {
        LOGGER.info("start sending email...");
        MailUtil.send("1224513626@qq.com", "IP NOTIFICATION",
                        StrUtil.format("上一个IP地址为：{}，当前IP地址为{}。", lastIpAddress, nowIpAddress), false);
        LOGGER.info("send email success...");
    }
}
