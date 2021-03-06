package backEnd.service;

import backEnd.response.RatingsResponse;
import backEnd.response.RatingResponse;

import java.io.IOException;

public interface RatingService {

    RatingResponse getIcobenchRating();

    RatingResponse getTrackicoRating();

    RatingResponse getIcomarksRating();

    RatingResponse getFoundicoRating();

    RatingResponse getAirdropsRating();

    RatingResponse getCryptoprofyRating();

    RatingResponse getCryptonextRating() throws IOException, InterruptedException;

    RatingResponse getBetaicoRating();

    void updateRating();

    RatingsResponse getRatings() throws IOException, InterruptedException;
}
