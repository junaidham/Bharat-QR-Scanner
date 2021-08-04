//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ng.AES;

import com.ng.service.BharatQrGenerator;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestQr {
    public TestQr() {
    }

    public static void main(String[] args) throws IOException {
        Map<String, Object> values = new LinkedHashMap();
        values.put("POI", "QR");
        values.put("NPCI1", "432100754447333");
        values.put("merchantCategoryCode", "5045");
        values.put("transactionCurrencyCode", "356");
        values.put("countryCode", "IN");
        values.put("name", "Nextgentelesolution");
        values.put("city", "Noi5");
        values.put("tip/convenience", "02");
        values.put("convenienceFeeFixed", "10");
        Map<String, String> additionalData = new LinkedHashMap();
        additionalData.put("billNumber", "Bill 098764322");
        additionalData.put("mobileNumber", "9868654189");
        additionalData.put("referenceId", "Reference 6008624688754");
        additionalData.put("consumerId", "Consumer 0874135799535i");
        additionalData.put("purpose", "topupeeeu");
        values.put("additionalData", additionalData);
        BharatQrGenerator b = new BharatQrGenerator();
        String qrString = b.generateQrCodeAsString(values);
        b.generateQrImage(qrString);
    }
}
