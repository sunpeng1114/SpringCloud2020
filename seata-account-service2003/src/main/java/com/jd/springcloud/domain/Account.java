package com.jd.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    private Integer userId;

    private BigDecimal total;

    private BigDecimal used;

    private BigDecimal residue;
}
