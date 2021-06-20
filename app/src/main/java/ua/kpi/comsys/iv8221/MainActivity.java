package ua.kpi.comsys.iv8221;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import ua.kpi.comsys.iv8221.SecondFragment.SecondFragment;
import ua.kpi.comsys.iv8221.ThirdFragment.BookData;
import ua.kpi.comsys.iv8221.ThirdFragment.ThirdFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.mainFrameLayout,
                new FirstFragment()
        ).commit();

        try {
            InputStream is = getAssets().open("BooksList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            BookData.setData(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavMenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selected = null;
                    switch (menuItem.getItemId()) {
                        case R.id.first:
                            selected = new FirstFragment();
                            break;
                        case R.id.second:
                            selected = new SecondFragment();
                            break;
                        case R.id.third:
                            selected = new ThirdFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,
                            selected).commit();
                    return true;
                }

            };
}
