package coffeemate.chris.app.coffeemateclub.model;

/**
 * Created by Chris on 25/02/2016.
 */
import android.util.Log;

import java.util.ArrayList;

public class Coffee {
    private String id, title, thumbnailUrl, brand, marketingtext;
    private int votes, price;

    public Coffee() {
    }

    public Coffee(String id, String title, String thumbnailUrl, String brand, String marketingtext, int votes, int price) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.brand = brand;
        this.marketingtext = marketingtext;
        this.votes = votes;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMarketingtext() {
        return marketingtext;
    }

    public void setMarketingtext(String marketingtext) {
        this.marketingtext = marketingtext;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
