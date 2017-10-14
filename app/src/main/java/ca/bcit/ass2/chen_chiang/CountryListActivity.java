package ca.bcit.ass2.chen_chiang;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jason on 10-Oct-2017.
 */

public class CountryListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String region = i.getStringExtra("regionName");

        ArrayList<String> countryNames = CountryList.searchByRegion(region);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryNames);
        ListView listCountries = getListView();
        listCountries.setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent countryDetail = new Intent(CountryListActivity.this, CountryDetailActivity.class);
        countryDetail.putExtra("name", l.getItemAtPosition(position).toString());
        startActivity(countryDetail);
    }
}
