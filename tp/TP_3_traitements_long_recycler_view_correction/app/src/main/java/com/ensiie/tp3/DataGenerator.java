package com.ensiie.tp3;

import android.content.Context;
import com.ensiie.tp3.bo.TextDescription;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adrian on 29/11/2016.
 */
public class DataGenerator {

    public static ArrayList<String> getDataForSimpleGrid() {
        final ArrayList<String> list = new ArrayList<>();
        int i;
        for (i = 0; i < 15; i++) {
            list.add(i + "");
        }
        return list;
    }

    public static ArrayList<TextDescription> getDataForSimpleList() {
        final ArrayList<TextDescription> list = new ArrayList<>();
        int i;
        for (i = 0; i < 15; i++) {
            list.add(new TextDescription("text" + i, "description" + i));
        }
        return list;
    }

    /**
     * Retourne une liste avec des élements de type Date et des élements de type String
     *
     * @return
     */
    public static ArrayList<Object> getDataForTwoTypesList() {
        final ArrayList<Object> list = new ArrayList<>();
        int i;
        int j;
        for (i = 0; i < 5; i++) {
            list.add(new Date(System.currentTimeMillis() + i * 100000000));
            for (j = 0; j < 5; j++) {
                list.add("message" + j);
            }
        }
        return list;
    }

    public static String getSingleJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("single_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String getListJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("list_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
