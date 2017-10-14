package ca.bcit.ass2.chen_chiang;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private String TAG = MainActivity.class.getSimpleName();

    private static final String SERVICE_URL = "https://restcountries.eu/rest/v2/all";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Contacts().execute();

        final ListView continentList = findViewById(R.id.listView_main_continents);

        continentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent countryIntent = new Intent(MainActivity.this, CountryListActivity.class);
                countryIntent.putExtra("regionName", continentList.getItemAtPosition(i).toString());
                startActivity(countryIntent);
            }
        });
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
                    if (!countryJson.getString("area").equals("null")) {
                        country.setArea(countryJson.getDouble("area"));
                    }
                    JSONArray bordersJson = countryJson.getJSONArray("borders");
                    String[] borders = new String[bordersJson.length()];
                    for (int j = 0; j < bordersJson.length(); j++) {
                        borders[j] = bordersJson.getString(j);
                    }
                    country.setBorder(borders);
                    country.setFlag(countryJson.getString("flag"));

                    CountryList.addCountry(country);
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
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}
