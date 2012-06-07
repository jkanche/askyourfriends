package info.buzzfriends.friends;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class askyourfriends extends Activity {
    /** Called when the activity is first created. */

		private dbcon db_con;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Toast.makeText(getApplicationContext(), "kevv", Toast.LENGTH_SHORT).show();
        db_con = new dbcon();
        Button but_login = (Button)findViewById(R.id.login);
        Button but_new = (Button)findViewById(R.id.createuser);
       
        but_login.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	EditText user_box = (EditText)findViewById(R.id.username);
                EditText pass_box = (EditText)findViewById(R.id.password);
                //Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_SHORT).show();
            	String user = user_box.getText().toString();
                String pass = pass_box.getText().toString();
                
                ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
                
                nvp.add(new BasicNameValuePair("username",user));
                nvp.add(new BasicNameValuePair("password",pass));
                         
                String result = db_con.create_conn("login.php",nvp);
                
                try 
                {
					//json_data = jArray.getJSONObject(0);
                	JSONObject json_data = new JSONObject(result);
                	//Toast.makeText(getApplicationContext(), Integer.toString(json_data.getInt("reply")), Toast.LENGTH_SHORT).show();
                	
                	if(json_data.getInt("reply") == 1)
                	{
                		Intent i = new Intent(v.getContext(), QueList.class);
                		i.putExtra("sess_user", user);
                		startActivity(i);
                		//finish();
                	}
                	else
                		Toast.makeText(getApplicationContext(), "Password/Username Incorrect", Toast.LENGTH_SHORT).show();
				} 
                catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        
        but_new.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), NewUserActivity.class);
                startActivity(i);      	        	
            }
        });
        
        
    }
}