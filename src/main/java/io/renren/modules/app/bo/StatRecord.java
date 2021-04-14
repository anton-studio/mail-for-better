package io.renren.modules.app.bo;

import io.renren.modules.app.entity.M4gCampaignEmailsEntity;
import lombok.Data;

@Data
public class StatRecord extends M4gCampaignEmailsEntity {
    private String email;
    private String name;
}
