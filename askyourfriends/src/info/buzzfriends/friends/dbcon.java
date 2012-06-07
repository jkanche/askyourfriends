package info.buzzfriends.friends;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

import android.util.Log;

public class dbcon {
	
	private final String url_db = "http://www.buzzfriends.info/" ;
	InputStream is;
	String result;
	JSONArray jArray;
	
	public String create_conn(String name_php, ArrayList<NameValuePair> tmp_nvp)
	{
		String u_url = url_db + name_php; 
		try
		{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u_url);
            httppost.setEntity(new UrlEncodedFormEntity(tmp_nvp));
            HttpResponse response = httpclient.execute(httppost); 
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("log_tag", "connection success ");
		}
		catch(Exception e)
		{
            Log.e("log_tag", "Error in http connection "+e.toString());
		}
		
		try
		{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) 
            {
                    sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
            
		}
		catch(Exception e)
		{
           Log.e("log_tag", "Error converting result "+e.toString());
		}
		return result;
/*		try
		{
            jArray = new JSONArray(result);
		}
		catch(JSONException e)
		{
            Log.e("log_tag", "Error parsing data "+e.toString());
		}     */
//		return jArray;
	}
	
}