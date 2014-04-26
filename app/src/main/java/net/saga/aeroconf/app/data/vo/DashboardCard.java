package net.saga.aeroconf.app.data.vo;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by summers on 4/25/14.
 */
public class DashboardCard {

    public final String text;
    public final URL imageUrl;


    public DashboardCard(String text, String imageUrl) {
        this.text = text;
        try {
            this.imageUrl = new URL(imageUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
