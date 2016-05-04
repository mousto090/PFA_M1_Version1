package Connexion;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Professeur;

/**
 * Created by SIMO on 10/04/2016.
 */
public class FetchProfInfosAsyncTask extends AsyncTask<String, Void, Professeur>{

    private Professeur getDataFromJson(String profJsonString) throws JSONException {
        // colonnes a extraire
        final String LOGIN = "login";
        final String PASS = "pass";

        Professeur result;

        JSONObject professeursObject = new JSONObject(profJsonString);
        JSONArray professeursJsonArray = professeursObject.getJSONArray("professeurs");
        JSONObject professeur = professeursJsonArray.getJSONObject(0);
        String login = professeur.getString(LOGIN);
        String password = professeur.getString(PASS);

        result = new Professeur(login,password);
        return result;
    }


    @Override
    protected Professeur doInBackground(String... urls) {
        ConnectionHttp conn = new ConnectionHttp(urls[0]);
        String jsonStr = conn.readStream(conn.getConnection());
        Log.i("doInBackground: ", jsonStr);
        try {
            return getDataFromJson(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Professeur professeur) {
        if(null == professeur){
            return;
        }
        Log.i("onPostExecute: ", professeur.toString());
    }
}
