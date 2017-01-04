package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.ensiie.tp3.bo.Address;
import com.ensiie.tp3.bo.Cat;
import com.ensiie.tp3.bo.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice11Activity extends AppCompatActivity {
    private static final String TAG = "Activity5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_json);

        final TextView textView = (TextView) findViewById(R.id.text);

        try {
            final JSONObject personObj = new JSONObject(DataGenerator.getListJsonData(this));
            final String forename = personObj.getString("forename");
            final String name = personObj.getString("name");
            final String website = personObj.getString("website");
            final JSONObject addressObj = personObj.getJSONObject("address");
            final String street = addressObj.getString("street");
            final String city = addressObj.getString("city");
            final ArrayList<Cat> cats = getListOfCat(personObj);
            final Address address = new Address(street, city);
            final Person person = new Person(forename, name, address, website, cats);
            textView.setText(person.toString());
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private ArrayList<Cat> getListOfCat(JSONObject personObj) throws JSONException {
        ArrayList<Cat> cats = new ArrayList<>();
        JSONArray catsArr = personObj.getJSONArray("cats");
        for (int i = 0; i < catsArr.length(); i++) {
            JSONObject catObj = catsArr.getJSONObject(i);
            String name = catObj.getString("name");
            cats.add(new Cat(name));
        }

        return cats;
    }
}
