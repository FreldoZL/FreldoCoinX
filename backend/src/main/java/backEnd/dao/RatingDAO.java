package backEnd.dao;

import backEnd.model.Rating;

import java.util.List;

public interface RatingDAO {
    void save(Rating rating);

    Rating getRatingByName(String name);

    List<Rating> getAll();
}
