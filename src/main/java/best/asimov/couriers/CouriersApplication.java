package best.asimov.couriers;

import cn.hutool.cron.CronUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CouriersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouriersApplication.class, args);
        CronUtil.start();
    }

}
