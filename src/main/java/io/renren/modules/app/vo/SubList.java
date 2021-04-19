package io.renren.modules.app.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubList {
    private List<Long> categoryIds;
    private List<Long> tagIds;
}
