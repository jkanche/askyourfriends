package info.buzzfriends.friends;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserActivity extends Activity {
	
	private dbcon db_con;
	
	public void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.user_layout);
	        db_con = new dbcon();
	        Button but_new_user = (Button)findViewById(R.id.create_user_new);
	
	        but_new_user.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v) 
	        	{
		        	EditText user_new = (EditText)findViewById(R.id.username_new);
		            EditText pass_new = (EditText)findViewById(R.id.password_new);
		            EditText name_user = (EditText)findViewById(R.id.name_user_new);
		            //Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_SHORT).show();
	            
		        	String user = user_new.getText().toString();
		            String pass = pass_new.getText().toString();
		            String name = name_user.getText().toString();
		            
		            ArrayList<NameValuePair> nvp_new= new ArrayList<NameValuePair>();
		            
		            nvp_new.add(new BasicNameValuePair("username",user));
		            nvp_new.add(new BasicNameValuePair("password",pass));
		            nvp_new.add(new BasicNameValuePair("name",name));
		            
		            String result = db_con.create_conn("register.php",nvp_new);
		            
		            try 
		            {
						//json_data = jArray.getJSONObject(0);
		            	JSONObject json_data = new JSONObject(result);
		            	//Toast.makeText(getApplicationContext(), json_data.getString("reply"), Toast.LENGTH_SHORT).show();
		            	if(json_data.getString("reply") == "true")
		            	{
		            		setContentView(R.layout.main);
		            		Toast.makeText(getApplicationContext(), "Login with your username", Toast.LENGTH_SHORT).show();
		            		 setResult(RESULT_OK);
		                     finish();
		            	}
		            	else
		            		Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
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
