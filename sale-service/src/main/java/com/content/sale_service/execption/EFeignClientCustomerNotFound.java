package com.content.sale_service.execption;

public class EFeignClientCustomerNotFound extends RuntimeException {
    public EFeignClientCustomerNotFound(String message) {
        super(message);
    }
}
