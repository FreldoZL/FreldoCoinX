package backEnd.response;

import backEnd.model.Rating;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.List;

@ApiObject(name = "RatingsResponse")
public class RatingsResponse extends ApiResponse {

    @ApiObjectField(name = "ratings")
    private List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
