package ca.bcit.ass2.chen_chiang;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by Jason on 10-Oct-2017.
 */
public class CountryDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Country country = CountryList.searchCountryByName(getIntent().getStringExtra("name"));
        final WebView flag = findViewById(R.id.webView_detail_flag);
        flag.getSettings().setLoadWithOverviewMode(true);
        flag.getSettings().setUseWideViewPort(true);
        flag.setBackgroundColor(Color.TRANSPARENT);
        String flagHtml = "<img src='" + country.getFlag() + "' width='600px' height='400px' />";
        flag.loadDataWithBaseURL(null, flagHtml, "text/html", "utf-8", null);

        final TextView name = findViewById(R.id.textView_detail_name);
        final TextView area = findViewById(R.id.textView_detail_area);
        final TextView capital = findViewById(R.id.textView_detail_capital);
        final TextView region = findViewById(R.id.textView_detail_region);
        final TextView population = findViewById(R.id.textView_detail_population);
        final TextView borders = findViewById(R.id.textView_detail_border);

        String stringBorder = "";
        for(int i = 0; i < country.getBorder().length; i++) {
            if(i == country.getBorder().length -1){
                stringBorder += country.getBorder()[i];
                break;
            }
            stringBorder += country.getBorder()[i] + ", ";
        }

        name.setText(country.getName());
        capital.setText(country.getCapital());
        region.setText(country.getRegion());
        population.setText(Integer.toString(country.getPopulation()));
        borders.setText(stringBorder);
        area.setText(Double.toString(country.getArea()));

    }
}
