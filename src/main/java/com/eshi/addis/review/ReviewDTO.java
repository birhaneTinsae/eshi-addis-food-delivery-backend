package com.eshi.addis.review;

import com.eshi.addis.utils.Auditable;
import lombok.Data;

@Data
public class ReviewDTO extends Auditable {
    private long id;
    private int rating;
    private String comment;
    private boolean updated;
}
