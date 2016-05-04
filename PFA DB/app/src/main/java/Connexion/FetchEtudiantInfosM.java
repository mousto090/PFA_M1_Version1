package Connexion;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Etudiant;

/**
 * Created by SIMO on 16/04/2016.
 */
public class FetchEtudiantInfosM extends AsyncTask<String, Void, Etudiant[]> {

    private String TAG = getClass().getSimpleName();

    public AsyncEtudiantData delegateEtudiantData = null;

    public interface AsyncEtudiantData {
        public void onLoadEtudiantDataFinish(Etudiant result[]);
    }

    @Override
    protected Etudiant[] doInBackground(String ... params) {

        ConnectionHttp conn = new ConnectionHttp("http://192.168.56.1:80/PHP-FILES/get_etudiant_m.php?login="+ params[0]+"&classe=" + params[1]);
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

    private Etudiant[] getDataFromJson(String etudiantJsonString) throws JSONException {
        // colonnes a extraire

        final String NOM = "nom";
        final String PRENOM = "prenom";
        final String IMAGE = "image";
        Log.v("TEST",etudiantJsonString);
        JSONObject listEtudiantObject = new JSONObject(etudiantJsonString);
        JSONArray arrayEtudiants = listEtudiantObject.getJSONArray("result");
        int nbEtudiant = arrayEtudiants.length();
        Etudiant result[] = new Etudiant[nbEtudiant];
        for (int i=0; i<nbEtudiant; i++ ) {
            JSONObject etudiant = arrayEtudiants.getJSONObject(i);
            String nom = etudiant.getString(NOM);
            String prenom = etudiant.getString(PRENOM);
            String image = "http://192.168.56.1:80/PHP-FILES"+etudiant.getString(IMAGE);

            result[i] = new Etudiant(nom, prenom, image);
        }
        return result;
    }

    @Override
    protected void onPostExecute(Etudiant[] result) {
        if(null == result){
            return;
        }
        //Log.e(TAG,result.toString());
        delegateEtudiantData.onLoadEtudiantDataFinish(result);
    }

}
