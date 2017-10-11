package ca.bcit.ass2.chen_chiang;

import java.util.ArrayList;

/**
 * Created by woody on 11-Oct-2017.
 */

public class CountryList {

    public static ArrayList<Country> countries;

    public static void addCountry(Country country) {
        countries.add(country);
    }

    public static ArrayList<String> searchByRegion(String region) {
        ArrayList<String> countryNames = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getRegion().equals(region)) {
                countryNames.add(countries.get(i).getName());
            }
        }

        return countryNames;
    }

    public static int getCountrySize() {
        return countries.size();
    }


}
