package MyLib;

import java.time.LocalDate;
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

    record Document(String participantInn, String docId, String docStatus, String docType, boolean importRequest,
                    String ownerInn, String producerInn, LocalDate productionDate, String productionType,
                    List<Product> products, String regDate, String regNumber) {
    }

    public static class Product {
        private Document document;
        private String sign;
        private String certificateDocument;
        private LocalDate certificateDocumentDate;
        private String certificateDocumentNumber;
        private String ownerInn;
        private String producerInn;
        private LocalDate productionDate;
        private String tnvedCode;
        private String uitCode;
        private String uituCode;

        public Product(Document document, String sign, String certificateDocument, LocalDate certificateDocumentDate, String certificateDocumentNumber, String ownerInn, String producerInn, LocalDate productionDate, String tnvedCode, String uitCode, String uituCode) {
            this.document = document;
            this.sign = sign;
            this.certificateDocument = certificateDocument;
            this.certificateDocumentDate = certificateDocumentDate;
            this.certificateDocumentNumber = certificateDocumentNumber;
            this.ownerInn = ownerInn;
            this.producerInn = producerInn;
            this.productionDate = productionDate;
            this.tnvedCode = tnvedCode;
            this.uitCode = uitCode;
            this.uituCode = uituCode;
        }

        public Product(Document document, String sign) {
            this.document = document;
            this.sign = sign;
        }

        public Document getDocument() {
            return document;
        }

        public void setDocument(Document document) {
            this.document = document;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getCertificateDocument() {
            return certificateDocument;
        }

        public void setCertificateDocument(String certificateDocument) {
            this.certificateDocument = certificateDocument;
        }

        public LocalDate getCertificateDocumentDate() {
            return certificateDocumentDate;
        }

        public void setCertificateDocumentDate(LocalDate certificateDocumentDate) {
            this.certificateDocumentDate = certificateDocumentDate;
        }

        public String getCertificateDocumentNumber() {
            return certificateDocumentNumber;
        }

        public void setCertificateDocumentNumber(String certificateDocumentNumber) {
            this.certificateDocumentNumber = certificateDocumentNumber;
        }

        public String getOwnerInn() {
            return ownerInn;
        }

        public void setOwnerInn(String ownerInn) {
            this.ownerInn = ownerInn;
        }

        public String getProducerInn() {
            return producerInn;
        }

        public void setProducerInn(String producerInn) {
            this.producerInn = producerInn;
        }

        public LocalDate getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(LocalDate productionDate) {
            this.productionDate = productionDate;
        }

        public String getTnvedCode() {
            return tnvedCode;
        }

        public void setTnvedCode(String tnvedCode) {
            this.tnvedCode = tnvedCode;
        }

        public String getUitCode() {
            return uitCode;
        }

        public void setUitCode(String uitCode) {
            this.uitCode = uitCode;
        }

        public String getUituCode() {
            return uituCode;
        }

        public void setUituCode(String uituCode) {
            this.uituCode = uituCode;
        }
    }

    public void create(Document document, String sign) {
        RateLimiter limiter = new RateLimiter(100, 1000);
        if (limiter.allowRequest()) {
            productList.add(new Product(document, sign));
        }
    }
}