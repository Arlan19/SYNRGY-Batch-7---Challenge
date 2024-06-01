package com.allacsta.latihan.controller.fileupload;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
