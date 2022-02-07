import com.qbang.stockpedia.impl.RequestStockService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RequestStockTest {
    @Autowired
    RequestStockService requestStockService = new RequestStockService();

    @Test
    public void testConnect() throws IOException {
        requestStockService.requestItemInfo(requestStockService.getItemCode());
    }
}
