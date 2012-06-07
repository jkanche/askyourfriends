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

public class ShareFriend extends Activity {
	
	private dbcon db_con;
	String user,grpid_tmp;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_friend);
		
		
		Button add_but = (Button) findViewById(R.id.fbut);
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		user = extras.getString("sess_user");
		grpid_tmp = extras.getString("grpid");
		
		
		add_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	
            	EditText friend_layout = (EditText)findViewById(R.id.fuser);
            	
            	ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
            	nvp.add(new BasicNameValuePair("username",user));
            	nvp.add(new BasicNameValuePair("friend",friend_layout.getText().toString()));
            	nvp.add(new BasicNameValuePair("grpid",grpid_tmp));
            	
            	
            	String result = db_con.create_conn("share_friend.php",nvp);
            	
            	
            	try 
                {
					//json_data = jArray.getJSONObject(0);
                	JSONObject json_data = new JSONObject(result);
                	//Toast.makeText(getApplicationContext(), Integer.toString(json_data.getInt("reply")), Toast.LENGTH_SHORT).show();
                	
                	if(json_data.getString("reply").equals("true"))
                	{
                		Toast.makeText(getApplicationContext(), "Question shared successfully", Toast.LENGTH_SHORT).show();
                		finish();
                	}
                	else
                		Toast.makeText(getApplicationContext(), "Friend/Username does not exist", Toast.LENGTH_SHORT).show();
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
