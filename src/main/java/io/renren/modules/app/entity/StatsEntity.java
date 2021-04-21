package io.renren.modules.app.entity;

import lombok.Data;

@Data
public class StatsEntity {
    private Integer complaintCount;
    private Integer bounceCount;
    private Integer sentCount;
    private Double complaintRate;
    private Double bounceRate;
    private String days;
}
