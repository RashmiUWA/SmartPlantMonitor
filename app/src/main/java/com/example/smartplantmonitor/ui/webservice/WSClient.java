package com.example.smartplantmonitor.ui.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.smartplantmonitor.ui.dto.UserDto;
import com.example.smartplantmonitor.ui.exceptions.ServerNotRespondingException;
import com.example.smartplantmonitor.ui.exceptions.ServerSyncFailedException;
import com.example.smartplantmonitor.ui.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janithag on 11/13/2018.
 */

public class WSClient {

    private Context context;

    public WSClient(Context context){
        this.context = context;

    }

    private String postData(String myurl, String data){

        String responce = null;
        HttpURLConnection con = null;
        if(isNetworkAvailable()) {
            try {

                URL url = new URL(myurl);
                con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                if (data != null) {
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(data);
                    wr.flush();
                }

                int HttpResult = con.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    responce = convertStreamToString(con.getInputStream());
                }
                else
                {
//                    Toast.makeText(context, String.valueOf(HttpResult) +" : "+ con.getResponseMessage(),
//                            Toast.LENGTH_LONG).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //showMessage(context.getString(R.string.alert_nw_error_no_connection_header), context.getString(R.string.alert_nw_error_no_connection));
        }
        return responce;
    }

    private String getData(String URL) throws ServerNotRespondingException {


        String responce = null;
        HttpURLConnection con = null;
        if(isNetworkAvailable()) {
            try {

                URL url = new URL(URL);

                con = (HttpURLConnection) url.openConnection();
                /* use only for post */
//              con.setDoOutput(true);

                /* use for both GET & POST */
                con.setDoInput(true);
                con.setRequestMethod("GET");
                con.setConnectTimeout(5000);

                int HttpResult = con.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    responce = convertStreamToString(con.getInputStream());

                }
                else
                {
                    throw new ServerNotRespondingException("Server Response Error " + HttpResult);
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw new ServerNotRespondingException("Server Not Responding");
            }

        } else {
            //showMessage(context.getString(R.string.alert_nw_error_no_connection_header), context.getString(R.string.alert_nw_error_no_connection));
        }
        return responce;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is),
                8192);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public boolean isJson(String jsonResponse)
    {
        try {
            Object json = new JSONTokener(jsonResponse).nextValue();

            boolean isJson = true;
            if (json instanceof JSONObject || json instanceof JSONArray) {

                isJson = true;

            } else {

                isJson = false;
            }

            return isJson;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return  false;
        }


    }

    public UserDto getUserDataByID() throws ServerSyncFailedException {

        String URL = Constants.USER_DATA_BY_ID;

        String response = "";

        try {
            response = getData(URL);
            response = response == null ? "":response;
        }
        catch (ServerNotRespondingException ex)
        {
            throw new ServerSyncFailedException(ex.getMessage().toString());
        }

        UserDto userDto = new UserDto();

        if(isJson(response)) {
            try {

                Gson gson = new GsonBuilder().create();

                Type listType = new TypeToken<UserDto>() {
                }.getType();

                userDto = gson.fromJson(response, listType);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            throw new ServerSyncFailedException("Server Response : "+response);

        }

        return userDto;
    }


}
