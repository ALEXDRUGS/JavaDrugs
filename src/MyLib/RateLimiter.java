package MyLib;

import java.util.ArrayList;
import java.util.List;

public class RateLimiter {
    private final int maxRequests;
    private int requestCount;
    private long lastRequestTime;
    private final long timePeriodInMillis;

    private final List<Product> productList = new ArrayList<>();

    public RateLimiter(int maxRequests, long timePeriodInMillis) {
        this.maxRequests = maxRequests;
        this.timePeriodInMillis = timePeriodInMillis;
        this.requestCount = 0;
        this.lastRequestTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRequestTime > timePeriodInMillis) {
            // Reset the counter if the time period has passed
            requestCount = 0;
            lastRequestTime = currentTime;
        }
        if (requestCount < maxRequests) {
            requestCount++;
            return true;
        } else {
            return false;
        }
    }

    static class Document {
    }

    record Product(Document document, String sign) {
    }

    public void create(Document document, String sign) {
        RateLimiter limiter = new RateLimiter(100, 1000);
        if (limiter.allowRequest()) {
            productList.add(new Product(document, sign));
        }
    }
}