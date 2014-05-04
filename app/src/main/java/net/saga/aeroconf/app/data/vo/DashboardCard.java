package net.saga.aeroconf.app.data.vo;

import java.net.MalformedURLException;
import java.net.URL;

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
