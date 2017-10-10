package ca.bcit.ass2.chen_chiang;

/**
 * Created by woody on 10-Oct-2017.
 */

public class Country {
    private String name;
    private String capital;
    private String region;
    private String flag;
    private long population;
    private long area;
    private String[] border;

    public Country(String name, String capital, String region, String flag, long population, long area, String[] border) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.flag = flag;
        this.population = population;
        this.area = area;
        this.border = border;

    }

    public Country() {

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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public String[] getBorder() {
        return border;
    }

    public void setBorder(String[] border) {
        this.border = border;
    }
}
