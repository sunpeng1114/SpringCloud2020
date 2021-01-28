package com.jd.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;

    private Integer productId;

    private Integer total;

    private Integer used;

    private Integer residue;
}
