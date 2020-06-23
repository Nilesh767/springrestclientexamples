package com.n3o.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Card implements Serializable {

    private String type;
    private String number;
    private ExpirationDate expirationDate;
    private String iban;
    private String swift;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 6203456183354582742L;

}
