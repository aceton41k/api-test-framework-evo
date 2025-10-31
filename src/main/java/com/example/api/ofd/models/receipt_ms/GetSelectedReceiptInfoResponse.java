package com.example.api.ofd.models.receipt_ms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetSelectedReceiptInfoResponse extends ResponseEntity{
    private List<PaymentInfo> responseBody;

    @Getter
    public static class PaymentInfo {
        private Long receiptId;
        private Long receivedCash;
        private Long receivedECash;
        private Long receivedCard;
        private Long totalVAT;
        private Byte isRefund;
        private Byte receiptType;
        private String terminalId;
        private Byte messageType;
        private List<Item> items;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS")
        private LocalDateTime time;

        @Getter
        public static class Item {
            private String name;
            private String barcode;
            private List<String> labels;
            private Long  units;
            private String packageCode;
            private Long  goodPrice;
            private Long  price;
            private Long  amount;
            private Long discount;
            private Long other;
            private CommissionInfo commissionInfo;
            private String spic;
            private Long vat;
            private Byte vatpercent;

            @Getter
            public static class CommissionInfo {
                private String pinfl;
                private String tin;
            }
        }
    }
}
