package best.asimov.monitor;

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
