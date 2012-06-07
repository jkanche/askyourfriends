package info.buzzfriends.friends;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsUser extends Activity {

	private dbcon db_con;
	String user;
	EditText settpass,settname;
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_user);
		TextView settuser = (TextView) findViewById(R.id.sett_user);
		settname = (EditText) findViewById(R.id.sett_name);
		settpass = (EditText) findViewById(R.id.sett_pass);
		Button ask_but = (Button) findViewById(R.id.ask);
		Button groups_but = (Button) findViewById(R.id.groups);
		Button history_but = (Button) findViewById(R.id.history);
		Button settings_but = (Button) findViewById(R.id.settings);
		Button update_but = (Button) findViewById(R.id.update);
		Button logout_but = (Button) findViewById(R.id.logout);
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		user = extras.getString("sess_user");
		
		ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("username",user));
		
		String result = db_con.create_conn("get_settings.php",nvp);
		
		try 
        {
			JSONArray jArray = new JSONArray(result);
			JSONObject json_data = jArray.getJSONObject(0);
			settuser.setText(json_data.getString("username"));        
			settname.setText(json_data.getString("name"));
			settpass.setText(json_data.getString("password"));
        	
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ask_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), GroupQue.class);
            	i.putExtra("sess_user", user);
                startActivity(i);      	  
                finish();
            }
        });
        
        logout_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), askyourfriends.class);
            	//i.putExtra("sess_user", user);
                startActivity(i); 
                finish();
            }
        });
		
        
        groups_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), MyList.class);
            	i.putExtra("sess_user", user);
                startActivity(i);      	 
                finish();
            }
        });
        
        history_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), QueList.class);
        		i.putExtra("sess_user", user);
        		startActivity(i);   
        		finish();
            }
        });
        
        settings_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), SettingsUser.class);
            	i.putExtra("sess_user", user);
                startActivity(i);
                finish();
                
            }
        });
        
        update_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                
            	ArrayList<NameValuePair> nvpup= new ArrayList<NameValuePair>();
        		nvpup.add(new BasicNameValuePair("username",user));
        		nvpup.add(new BasicNameValuePair("name",settname.getText().toString()));
        		nvpup.add(new BasicNameValuePair("password",settpass.getText().toString()));
        		
        		String result = db_con.create_conn("update_settings.php",nvpup);
        		
        		try 
                {
					//json_data = jArray.getJSONObject(0);
                	JSONObject json_data = new JSONObject(result);
                	
                	
                	if(json_data.getString("reply").equals("true"))
                		Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
                	else
                		Toast.makeText(getApplicationContext(), "Technical Issue", Toast.LENGTH_SHORT).show();
                		
				} 
                catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}
}
