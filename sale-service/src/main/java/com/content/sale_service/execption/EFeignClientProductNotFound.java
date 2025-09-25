package com.content.sale_service.execption;

public class EFeignClientProductNotFound extends RuntimeException {
    public EFeignClientProductNotFound(String message) {
        super(message);
    }
}
