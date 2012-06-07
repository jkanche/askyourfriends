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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GroupQue extends Activity {
	
	private dbcon db_con;
	private JSONArray jArray;
	String user;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask_group);
		
		ListView lv = (ListView) findViewById(R.id.group_list);
		Button ask_but = (Button) findViewById(R.id.ask);
		Button groups_but = (Button) findViewById(R.id.groups);
		Button history_but = (Button) findViewById(R.id.history);
		Button settings_but = (Button) findViewById(R.id.settings);
		Button logout_but = (Button) findViewById(R.id.logout);
		TextView tempv = (TextView) findViewById(R.id.textView2);
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
		user = extras.getString("sess_user");
        nvp.add(new BasicNameValuePair("username",user));
        
        String result = db_con.create_conn("get_groups.php",nvp);

        ArrayList<String> tmp_group = new ArrayList<String>();
        try 
        {
        	 jArray = new JSONArray(result);
        	 int length = jArray.length();
             for(int i=0;i<length;i++)
             {
                     JSONObject json_data = jArray.getJSONObject(i);
                     tmp_group.add(json_data.getString("group_name"));
             }
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String[] group_str = (String[]) tmp_group.toArray(new String[0]);
        
        if(group_str.length != 0)
        	lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, group_str));
        else
        	tempv.setText("No Groups");
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
            {
            	//Object tmpo = this.getItem(position);
            	//Object o = parent.getItemAtPosition(position);
            	Object o = null;
            	
				try 
				{
					o = jArray.getJSONObject(position).getInt("group_id");
				} 
				catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
            	String keyword_position = o.toString();
            	Intent i = new Intent(getApplicationContext(), GroupAsk.class);
        		i.putExtra("grp_id", keyword_position);
        		i.putExtra("sess_user", user);
        		startActivity(i);
        		//Toast.makeText(QueList.this, "You selected: " + keyword, Toast.LENGTH_LONG).show();
            }
         });
        
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
            	//Intent i = new Intent(v.getContext(), askyourfriends.class);
            	//i.putExtra("sess_user", user);
                //startActivity(i); 
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
	}

}
