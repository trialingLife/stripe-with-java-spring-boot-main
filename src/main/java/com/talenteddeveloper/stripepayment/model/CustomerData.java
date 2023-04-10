package com.talenteddeveloper.stripepayment.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {
    private String name;
    private String email;
    private String customerId;

}
