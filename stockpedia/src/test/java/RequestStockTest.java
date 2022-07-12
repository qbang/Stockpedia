import com.qbang.stockpedia.impl.RequestStockService;
import org.junit.Test;

import java.io.IOException;

public class RequestStockTest {
    RequestStockService requestStockService = new RequestStockService();

    @Test
    public void testConnect() throws IOException {
        requestStockService.requestItemInfo(requestStockService.getItemCode());
    }
}
