package be.kdg.rummikub.view.spel;

import javafx.scene.image.ImageView;

public class AfbeeldingSteen extends ImageView {
    private int xGrid;
    private int yGrid;
    private String url;


    public AfbeeldingSteen(String url, int x, int y) {
        super(url);
        this.xGrid = x;
        this.yGrid = y;
        String[] laatsteSlash = url.split("/");
        if (laatsteSlash[laatsteSlash.length-1].substring(2, 3).matches("-?\\d+(\\.\\d+)?")) {
            this.url = laatsteSlash[laatsteSlash.length-1].substring(0,3);
        } else {
            this.url = laatsteSlash[laatsteSlash.length-1].substring(0,2);
        }
    }

    public int getXGrid() {
        return xGrid;
    }

    public int getYGrid() {
        return yGrid;
    }

    public String getUrl() {
        return url;
    }
}

