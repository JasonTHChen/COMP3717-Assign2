package ca.bcit.ass2.chen_chiang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView continentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continentList = findViewById(R.id.listView_main_continents);

        continentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent countryIntent = new Intent(MainActivity.this, CountriesActivity.class);
                startActivity(countryIntent);
            }
        });
    }


}
