package best.asimov.couriers;

import best.asimov.couriers.ip.IpChangeCourier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouriersApplicationTests {

    @Test
    void ipChangeNotificationTest() {
        IpChangeCourier ipChangeCourier = new IpChangeCourier();
        ipChangeCourier.ipNotification();
    }

}
