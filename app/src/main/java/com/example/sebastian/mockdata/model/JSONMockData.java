package com.example.sebastian.mockdata.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2017-01-12.
 */
public class JSONMockData {

    private static String KEY_TITLE = "title";
    private static String KEY_JSONARRAY = "data";
    private static String KEY_NAME = "name";
    private static String KEY_LAST_NAME = "last_name";
    private static String KEY_COUNTRY = "country";

    public String title;
    public String[] name;
    public String[] surname;
    public String[] country;
    private JSONArray usersJsonArray;
    private User user;
    private List<User> userList = new ArrayList<User>();

    public void initializeFromJson(String json){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(json);
            setTitle(jsonObject.getString(KEY_TITLE));
            usersJsonArray = jsonObject.getJSONArray(KEY_JSONARRAY);
            name = new String[usersJsonArray.length()];
            surname = new String[usersJsonArray.length()];
            country = new String[usersJsonArray.length()];
            for(int i=0; i < usersJsonArray.length(); i++){
                JSONObject object = usersJsonArray.getJSONObject(i);
                name[i] = getString(KEY_NAME, object);
                surname[i] = getString(KEY_LAST_NAME, object);
                country[i] = getString(KEY_COUNTRY,object);
                user = new User(name[i],surname[i],country[i]);
                userList.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUserList(){
        return userList;
    }

    private static String getString(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }
}
