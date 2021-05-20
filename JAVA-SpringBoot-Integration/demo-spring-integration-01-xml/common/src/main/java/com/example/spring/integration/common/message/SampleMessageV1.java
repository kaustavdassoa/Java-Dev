package com.example.spring.integration.common.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleMessageV1 {

    @NotNull
    private String messageID;

    @NotNull
    private String sender;

    @NotNull
    private String cosumer;

    @NotNull
    private String message;

}
