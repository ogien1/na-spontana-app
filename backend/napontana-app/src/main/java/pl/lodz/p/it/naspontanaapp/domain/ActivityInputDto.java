package pl.lodz.p.it.naspontanaapp.domain;

import org.joda.time.LocalDateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by piotr on 30.11.16.
 */
public class ActivityInputDto extends BaseActivityDto {

    @NotNull
    private String facebookId;


    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

}
