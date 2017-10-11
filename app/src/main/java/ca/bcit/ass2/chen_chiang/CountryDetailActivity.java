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
    private ImageView flag;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Country country = CountryList.searchCountryByName(getIntent().getStringExtra("name"));
        flag = (ImageView) findViewById(R.id.countryFlag);
        new ImageDownloaderTask(flag).execute(country.getFlag());

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

    class ImageDownloaderTask extends AsyncTask<String, Void, Drawable> {
        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Drawable doInBackground(String... params) {
            try {
                final URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                SVG svg = SVGParser. getSVGFromInputStream(inputStream);
                Drawable drawable = svg.createPictureDrawable();
                return drawable;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if(drawable != null){
                // Try using your library and adding this layer type before switching your SVG parsing
                flag.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                flag.setImageDrawable(drawable);
            }
        }
    }
}
