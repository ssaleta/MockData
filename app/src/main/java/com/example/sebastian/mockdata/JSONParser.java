package com.example.sebastian.mockdata;

import com.example.sebastian.mockdata.model.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2017-01-09.
 */
public class JSONParser {

    private static String TAG = JSONParser.class.toString();
    public String title;
    public String[] name;
    public String[] surname;
    public String[] country;

    private static String KEY_TITLE = "title";
    private static String KEY_JSONARRAY = "data";
    private static String KEY_NAME = "name";
    private static String KEY_LAST_NAME = "last_name";
    private static String KEY_COUNTRY = "country";

    private String json;
    private JSONArray dataJsonArray;
    private Data data;
    private List<Data> dataList = new ArrayList<Data>();

    public JSONParser(String json) {
        this.json = json;
    }

    protected void parse(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(json);
            setTitle(jsonObject.getString(KEY_TITLE));
            dataJsonArray = jsonObject.getJSONArray(KEY_JSONARRAY);
            name = new String[dataJsonArray.length()];
            surname = new String[dataJsonArray.length()];
            country = new String[dataJsonArray.length()];
            for(int i=0; i < dataJsonArray.length(); i++){
                JSONObject object = dataJsonArray.getJSONObject(i);
                name[i] = getString(KEY_NAME, object);
                surname[i] = getString(KEY_LAST_NAME, object);
                country[i] = getString(KEY_COUNTRY,object);
                data = new Data(name[i],surname[i],country[i]);
                dataList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public List<Data> getDataList(){
        return dataList;
    }

    private static String getString(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
