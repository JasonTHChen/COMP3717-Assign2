package ca.bcit.ass2.chen_chiang;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Jason on 10-Oct-2017.
 */

public class CountriesActivity extends ListActivity {

    private static String SERVICE_URL = "http://restcountries.eu/rest/v2/all";
    private List<Country> countryList;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Contacts().execute();
    }

    private class Contacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler http = new HttpHandler();
            String jsonStr = http.makeServiceCall(SERVICE_URL);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr == null) {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

                return null;
            }

            try {
                JSONArray countryJsonArray = new JSONArray(jsonStr);
                for (int i = 0; i < countryJsonArray.length(); i++) {
                    JSONObject countryJson = countryJsonArray.getJSONObject(i);
                    Country country = new Country();
                    country.setName(countryJson.getString("name"));
                    country.setCapital(countryJson.getString("capital"));
                    country.setRegion(countryJson.getString("region"));
                    country.setPopulation(countryJson.getInt("population"));
                    if (countryJson.getString("area") != "null") {
                        country.setArea(countryJson.getDouble("area"));
                    }
                    JSONArray bordersJson = countryJson.getJSONArray("borders");
                    String[] borders = new String[bordersJson.length()];
                    for (int j = 0; j < bordersJson.length(); j++) {
                        borders[j] = bordersJson.getString(j);
                    }
                    country.setBorder(borders);
                    country.setFlag(countryJson.getString("flag"));

                    countryList.add(country);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(CountriesActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dialog.isShowing()) {
                dialog.dismiss();
                
            }
        }
    }
}
