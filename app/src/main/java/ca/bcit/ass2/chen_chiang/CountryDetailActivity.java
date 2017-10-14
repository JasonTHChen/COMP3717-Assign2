package ca.bcit.ass2.chen_chiang;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chonjou on 2017-10-11.
 */

public class CountryDetailActivity extends Activity {
    private WebView flag;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Country country = CountryList.searchCountryByName(getIntent().getStringExtra("name"));
        flag = (WebView) findViewById(R.id.countryFlag);
        flag.getSettings().setLoadWithOverviewMode(true);
        flag.getSettings().setUseWideViewPort(true);
        flag.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        String flagHtml = "<img src='" + country.getFlag() + "' width:'200px' height:'100px'/>";
        flag.loadDataWithBaseURL(null, flagHtml, "text/html", "utf-8", null);

        TextView area = (TextView) findViewById(R.id.txtArea);
        TextView name = (TextView) findViewById(R.id.txtName);
        TextView capital = (TextView) findViewById(R.id.txtCapital);
        TextView region = (TextView) findViewById(R.id.txtRegion);
        TextView population = (TextView) findViewById(R.id.txtPopulation);
        TextView borders = (TextView) findViewById(R.id.txtBorders);

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
