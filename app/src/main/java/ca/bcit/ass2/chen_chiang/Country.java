package ca.bcit.ass2.chen_chiang;

/**
 * Created by woody on 10-Oct-2017.
 */

public class Country {
    private String name;
    private String capital;
    private String region;
    private String flag;
    private int population;
    private double area;
    private String[] border;

    public Country() {
        name = "";
        capital = "";
        region = "";
        flag = "";
        population = 0;
        area = 0.0;
        border = null;
    }

    public Country(String name, String capital, String region, String flag, int population, double area, String[] border) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.flag = flag;
        this.population = population;
        this.area = area;
        this.border = border;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String[] getBorder() {
        return border;
    }

    public void setBorder(String[] border) {
        this.border = border;
    }
}
