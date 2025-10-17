package com.content.payment_gateway_service.dto.request;

import com.content.payment_gateway_service.model.*;

public class LogTransactionDTO {

    private Integer log_transaction_id;
    private AttemptPayment attempt_id;
    private String end_point;
    private String processor_ip;
    private Long response_time;
    private StateEntity state_entity_id;
}
