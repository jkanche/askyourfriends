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

public class QueList extends Activity {
	private dbcon db_con;
	private JSONArray jArray;
	String user;
	ArrayAdapter<String> aadap;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_f);
		
		ListView lv = (ListView) findViewById(R.id.quest_list);
		Button ask_but = (Button) findViewById(R.id.ask);
		Button groups_but = (Button) findViewById(R.id.groups);
		Button history_but = (Button) findViewById(R.id.history);
		Button settings_but = (Button) findViewById(R.id.settings);
		Button logout_but = (Button) findViewById(R.id.logout);
		TextView tempv = (TextView) findViewById(R.id.textView2);

		// Create an array of Strings, that will be put to our ListActivity
		//String[] names = new String[] { "Linux", "Windows7", "Eclipse", "Suse","Ubuntu", "Solaris", "Android", "iPhone"};
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
		user = extras.getString("sess_user");
        nvp.add(new BasicNameValuePair("username",user));
        //lv.setTextFilterEnabled(true);
        String result = db_con.create_conn("get_questions.php",nvp);
        
        ArrayList<String> tmp_quest = new ArrayList<String>();
        try 
        {
        	 jArray = new JSONArray(result);
        	 int length = jArray.length();
             for(int i=0;i<length;i++)
             {
                     JSONObject json_data = jArray.getJSONObject(i);
                     tmp_quest.add(json_data.getString("username") + " asked " + json_data.getString("quest_body"));
             }
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String[] questions = (String[]) tmp_quest.toArray(new String[0]);
        aadap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, questions);
        if(questions.length != 0)
        	lv.setAdapter(aadap);
        else
        	tempv.setText("No Recent Questions");
        //this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, questions));
        aadap.notifyDataSetChanged();
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
            {
            	//Object tmpo = this.getItem(position);
            	//Object o = parent.getItemAtPosition(position);
            	Object o = null,o1=null;
            	
				try 
				{
					o = jArray.getJSONObject(position).getInt("q_id");
					o1 = jArray.getJSONObject(position).getInt("group_id");
				} 
				catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
            	String keyword_position = o.toString();
            	Intent i = new Intent(getApplicationContext(), QueListSelect.class);
        		i.putExtra("uniq_id", keyword_position);
        		i.putExtra("sess_user", user);
        		i.putExtra("grpid", o1.toString());
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