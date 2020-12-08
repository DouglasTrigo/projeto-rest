package com.prova.apirest.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "deck_id")
public class AleatorioResponse {

    private Boolean success;
    private String deck_id;
    private Integer remaining;
    private Boolean shuffled;

}
