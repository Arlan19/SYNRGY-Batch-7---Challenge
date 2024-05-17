package com.allacsta.latihan.controller.fileupload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UploadMerchantResponse {
    private UUID id;
    private String merchant_name;
    private String merchant_location;
    private Boolean open;
    private String error;
}
