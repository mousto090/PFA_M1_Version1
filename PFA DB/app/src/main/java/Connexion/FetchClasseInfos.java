package Connexion;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Classe;
import entity.Etudiant;

/**
 * Created by SIMO on 21/04/2016.
 */
public class FetchClasseInfos extends AsyncTask<String, Void, Classe[]> {
    private String TAG = getClass().getSimpleName();

    public AsyncClasseData delegateClassetData = null;

    public interface AsyncClasseData {
        public void onLoadClasseDataFinish(Classe result[]);
    }


    @Override
    protected Classe[] doInBackground(String... params) {
        ConnectionHttp conn = new ConnectionHttp("http://192.168.56.1:80/PHP-FILES/choix_classe.php?login=" + params[0]);
        String jsonStr = conn.readStream(conn.getConnection());

        try {
            return getDataFromJson(jsonStr);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {

            conn.disconnect();
        }

        return null;
    }

    private Classe[] getDataFromJson(String classeJsonString) throws JSONException {
        // colonnes a extraire

        final String NIVEAU = "niveau";


        JSONObject listClasseObject = new JSONObject(classeJsonString);
        JSONArray arrayClasse = listClasseObject.getJSONArray("result");
        int nbClasse = arrayClasse.length();
        Classe result[] = new Classe[nbClasse];
        for (int i=0; i<nbClasse; i++ ) {
            JSONObject classe = arrayClasse.getJSONObject(i);
            String niveau = classe.getString(NIVEAU);

            result[i] = new Classe(niveau);
        }
        return result;
    }

    @Override
    protected void onPostExecute(Classe[] result) {
        if(null == result){
            return;
        }
        //Log.e(TAG,result.toString());
        delegateClassetData.onLoadClasseDataFinish(result);
    }
}
