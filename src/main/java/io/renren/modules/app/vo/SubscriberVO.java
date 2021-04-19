package io.renren.modules.app.vo;

import io.renren.modules.app.entity.M4gSubscriberEntity;
import lombok.Data;

import java.util.List;

@Data
public class SubscriberVO extends M4gSubscriberEntity {
    private List<Long> realTags;
}
