package com.example.geekpressmobile;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DAOArticle {
    public DAOArticle() {
    }

    private String readJSONFeed(String url) {
        String jsonArticle = new String();
        try {
            URL urlObj = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(urlConnection.getInputStream())
            );
            int statusCode = urlConnection.getResponseCode();
            System.out.println("here");
            if (statusCode == 200) {
                String line;
                while ((line = in.readLine()) != null) {
                    jsonArticle += line;
                }
            } else {
                Log.d("JSON", "Failed to download file" + statusCode);
            }
            in.close();
            urlConnection.disconnect();
            return jsonArticle;
        } catch (MalformedURLException e) {
            Log.d("URL", e.getStackTrace().toString());
        } catch (Exception e) {
            Log.d("JSON", e.toString());
        }
        return jsonArticle;
    }

    public List<ObjectTest> getListArticle() {
        System.out.println("there");
        String str = readJSONFeed("https://chucknorrisfacts.fr/api/get?data=param:value;param2:value2");
        ArrayList<ObjectTest> list = new ArrayList<ObjectTest>();
        try {
            JSONArray array = new JSONArray(str);
            for(int i=0; i<array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                ObjectTest objectTest = new ObjectTest();
                objectTest.setTitre(jsonObject.getString("id"));
                objectTest.setAuteur(jsonObject.getString("points"));
                objectTest.setArticle(jsonObject.getString("fact"));
                list.add(objectTest);
            }
        } catch(Exception e) {

        }
        return list;

    }

    public class ObjectTest {
        public String titre;
        public String auteur;
        public String article;

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public String getAuteur() {
            return auteur;
        }

        public void setAuteur(String auteur) {
            this.auteur = auteur;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }
    }
}
