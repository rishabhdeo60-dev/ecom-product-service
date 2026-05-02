package com.rdkp63.ecom_product_services.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ProductDeleteRequest {

    @NonNull
    private Long id;

    private Long createdBy;


}
