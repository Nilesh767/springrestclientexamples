package com.n3o.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Billing implements Serializable {

    private Card card;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 6577338081290507077L;

}
