package backEnd.service.impl;

import backEnd.dao.RatingDAO;
import backEnd.model.Rating;
import backEnd.response.ApiResponse;
import backEnd.response.RatingsResponse;
import backEnd.response.RatingResponse;
import backEnd.service.RatingService;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDAO ratingDAO;

    @Override
    public RatingResponse getIcobenchRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("https://icobench.com/ico/freldo/ratings", ".//div[@class='rating']/div");

        Rating icobench = ratingDAO.getRatingByName("icobench");
        if (icobench == null) {
            icobench = new Rating.Builder().name("icobench").rating(rating).build();
        } else {
            if (rating != null) {
                icobench.setRating(rating);
            }
        }
        ratingDAO.save(icobench);

        response.setRating(rating);
        response.setName("icobench");
        return response;
    }

    @Override
    public RatingResponse getTrackicoRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("https://www.trackico.io/ico/freldo/", "//div[@class='fs-60 fw-400 text-primary']");

        Rating trackico = ratingDAO.getRatingByName("trackico");
        if (trackico == null) {
            trackico = new Rating.Builder().name("trackico").rating(rating).build();
        } else {
            if (rating != null) {
                trackico.setRating(rating);
            }
        }
        ratingDAO.save(trackico);

        response.setRating(rating);
        response.setName("trackico");
        return response;
    }

    @Override
    public RatingResponse getIcomarksRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("https://icomarks.com/ico/freldo", "//div[@class='ico-rating-overall']");

        Rating icomarks = ratingDAO.getRatingByName("icomarks");
        if (icomarks == null) {
            icomarks = new Rating.Builder().name("icomarks").rating(rating).build();
        } else {
            if (rating != null) {
                icomarks.setRating(rating);
            }
        }
        ratingDAO.save(icomarks);

        response.setRating(rating);
        response.setName("icomarks");
        return response;
    }

    @Override
    public RatingResponse getFoundicoRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("https://foundico.com/ru/ico/freldo.html", "//span[@class='flmf-mark']").replace("?", "");

        Rating foundico = ratingDAO.getRatingByName("foundico");
        if (foundico == null) {
            foundico = new Rating.Builder().name("foundico").rating(rating).build();
        } else {
            if (rating != null) {
                foundico.setRating(rating);
            }
        }
        ratingDAO.save(foundico);

        response.setRating(rating);
        response.setName("foundico");
        return response;
    }

    @Override
    public RatingResponse getAirdropsRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("https://airdrops.ninja/icos/detail/4327", "//span[@class='g-font-size-40 g-color-black']");

        Rating airdrops = ratingDAO.getRatingByName("airdrops");
        if (airdrops == null) {
            airdrops = new Rating.Builder().name("airdrops").rating(rating).build();
        } else {
            if (rating != null) {
                airdrops.setRating(rating);
            }
        }
        ratingDAO.save(airdrops);

        response.setRating(rating);
        response.setName("airdrops");
        return response;
    }

    @Override
    public RatingResponse getCryptoprofyRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("https://cryptoprofy.com/rating-ico/obzor-ico-freldo.html", ".//div[@class='right']/div[@class='raiting']");

        Rating cryptoprofy = ratingDAO.getRatingByName("cryptoprofy");
        if (cryptoprofy == null) {
            cryptoprofy = new Rating.Builder().name("cryptoprofy").rating(rating).build();
        } else {
            if (rating != null) {
                cryptoprofy.setRating(rating);
            }
        }
        ratingDAO.save(cryptoprofy);

        response.setRating(rating);
        response.setName("cryptoprofy");
        return response;
    }

    @Override
    public RatingResponse getCryptonextRating() throws IOException, InterruptedException {
        RatingResponse response = new RatingResponse();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        WebRequest request = new WebRequest(new URL("https://cryptonext.com/ico/freldo"));
        HtmlPage page = webClient.getPage(request);

        int i = webClient.waitForBackgroundJavaScript(1000);
        while (i > 0) {
            i = webClient.waitForBackgroundJavaScript(1000);

            if (i == 0) {
                break;
            }
            synchronized (page) {
                System.out.println("wait");
                page.wait(500);
            }
        }

        webClient.getAjaxController().processSynchron(page, request, false);

        String rating = null;
        if (page != null) {
            List<HtmlElement> items = page.getByXPath("//div[@class='data-v-2f2c9bb3']");
            if (!items.isEmpty()) {
                rating = items.get(0).asText();
            }
        }

        Rating cryptonext = ratingDAO.getRatingByName("cryptonext");
        if (cryptonext == null) {
            cryptonext = new Rating.Builder().name("cryptonext").rating(rating).build();
        } else {
            if (rating != null) {
                cryptonext.setRating(rating);
            }
        }
        ratingDAO.save(cryptonext);

        response.setRating(rating);
        response.setName("cryptonext");
        return response;
    }

    @Override
    public RatingResponse getBetaicoRating() {
        RatingResponse response = new RatingResponse();
        String rating = getRating("http://betaico.com/freldo#tab-analysis", "//div[@class='c100 p75 big center rate4']");

        Rating betaico = ratingDAO.getRatingByName("betaico");
        if (betaico == null) {
            betaico = new Rating.Builder().name("betaico").rating(rating).build();
        } else {
            if (rating != null) {
                betaico.setRating(rating);
            }
        }
        ratingDAO.save(betaico);

        response.setRating(rating);
        response.setName("betaico");
        return response;
    }

    @Override
    public void updateRating() {
        getIcobenchRating();
        getAirdropsRating();
        getFoundicoRating();
        getIcomarksRating();
        getTrackicoRating();
        getCryptoprofyRating();
        try {
            getCryptonextRating();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getBetaicoRating();
    }

    @Override
    public RatingsResponse getRatings() throws IOException, InterruptedException {
        RatingsResponse response = new RatingsResponse();
        List<Rating> ratings = ratingDAO.getAll();
        response.setRatings(ratings);
        response.setStatus(ApiResponse.Status.ok.toString());
        response.setMessage("Ratings");
        return response;
    }

    private String getRating(String url, String className) {
        WebClient client = new WebClient();
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = null;
        String rating = null;
        try {
            page = client.getPage(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (page != null) {
            List<HtmlElement> items = page.getByXPath(className);
            if (!items.isEmpty()) {
                rating = items.get(0).asText();
            }
        }
        return rating;
    }
}
