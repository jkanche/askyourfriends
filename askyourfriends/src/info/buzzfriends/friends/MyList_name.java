package info.buzzfriends.friends;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyList_name extends Activity {
	
	private dbcon db_con;
	String user;
	int size;
	String[] tmp_group;
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.make_name_group);
		
		
		Button create_grp_but = (Button) findViewById(R.id.create_grp);
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		//group_id = extras.getString("grp_id");
		user = extras.getString("sess_user");
		size = extras.getInt("size");
		//ArrayList<String> tmp_group = new ArrayList<String>();
		tmp_group = new String[size];
		tmp_group = extras.getStringArray("fnames");
		
		create_grp_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//Toast.makeText(getApplicationContext(), "pressed create", Toast.LENGTH_SHORT).show();   
            	EditText name_grp_layout = (EditText) findViewById(R.id.name_grp);
            	String grpname = name_grp_layout.getText().toString();
            	int grpid = 0;
            	ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
        		nvp.add(new BasicNameValuePair("username",user));
        		nvp.add(new BasicNameValuePair("grp_name",grpname));
        		
        		String result = db_con.create_conn("set_group.php",nvp);
        		
        		try 
                {
        			JSONArray jArray = new JSONArray(result);
					JSONObject json_data = jArray.getJSONObject(0);
                	//Toast.makeText(getApplicationContext(), Integer.toString(json_data.getInt("reply")), Toast.LENGTH_SHORT).show();
                	
                	grpid= json_data.getInt("group_id");
                	
				} 
                catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                ArrayList<NameValuePair> nvp_fr = new ArrayList<NameValuePair>();
                nvp_fr.add(new BasicNameValuePair("grp_id",Integer.toString(grpid)));
                nvp_fr.add(new BasicNameValuePair("username",user));
                
                for(int j=0;j<size;j++)
                {
                	nvp_fr.add(new BasicNameValuePair("fname",tmp_group[j]));
                	
                	String result_fr = db_con.create_conn("set_group_friend.php",nvp_fr);
                	
                	//Toast.makeText(getApplicationContext(), tmp_group[j], Toast.LENGTH_SHORT).show();
                }
                
                Toast.makeText(getApplicationContext(), "Group created Successfully", Toast.LENGTH_SHORT).show();
                finish();
   	
            }
        });
	}

}
