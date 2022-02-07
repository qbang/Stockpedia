import com.qbang.stockpedia.impl.RequestStockService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestStockTest {
    @Autowired
    RequestStockService requestStockService = new RequestStockService();

    @Test
    public void testConnect() {
        requestStockService.requestItemInfo();
    }
}
