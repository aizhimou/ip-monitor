package best.asimov.couriers;

import best.asimov.couriers.ip.IpChangeCouriers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouriersApplicationTests {

    @Test
    void ipChangeNotificationTest() {
        IpChangeCouriers ipChangeCouriers = new IpChangeCouriers();
        ipChangeCouriers.ipNotification();
    }

}
