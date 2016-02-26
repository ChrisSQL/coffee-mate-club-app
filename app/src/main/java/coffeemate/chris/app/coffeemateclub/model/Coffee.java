package coffeemate.chris.app.coffeemateclub.model;

/**
 * Created by Chris on 25/02/2016.
 */
import android.util.Log;

import java.util.ArrayList;

public class Coffee {
    private String title, thumbnailUrl, brand;
    private int year;
    private double rating;
    private ArrayList<String> genre;

    public Coffee() {
    }

    public Coffee(String name, String thumbnailUrl, String brand, int year, double rating,
                  ArrayList<String> genre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {

        if(thumbnailUrl.substring(0,3).equals("./mo") ){

            Log.d("thumbnailUrl ====== ",thumbnailUrl);
            thumbnailUrl = "http://www.coffeemate.club/" + thumbnailUrl.substring(2);
            Log.d("thumbnailUrl ====== ",thumbnailUrl);

        }

        this.thumbnailUrl = thumbnailUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {

        return rating;
    }

    public void setRating(double rating) {



        this.rating = rating;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}
