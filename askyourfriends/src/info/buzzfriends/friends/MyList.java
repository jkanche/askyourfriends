package info.buzzfriends.friends;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MyList extends Activity {
	
	private dbcon db_con;
	private JSONArray jArray;
	String user;
	List<Freind_model> list;
	ArrayAdapter<Freind_model> adapter ;
	ListView lv;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.make_group);
		
		lv = (ListView) findViewById(R.id.make_g_list);
		Button create_group_but = (Button) findViewById(R.id.create_group);
		Button ask_but = (Button) findViewById(R.id.ask);
		Button groups_but = (Button) findViewById(R.id.groups);
		Button history_but = (Button) findViewById(R.id.history);
		Button settings_but = (Button) findViewById(R.id.settings);
		Button addfrd_but = (Button) findViewById(R.id.addfrd);
		Button logout_but = (Button) findViewById(R.id.logout);
		TextView tempv = (TextView) findViewById(R.id.textView2);
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		//group_id = extras.getString("grp_id");
		user = extras.getString("sess_user");
		
		// Create an array of Strings, that will be put to our ListActivity
		adapter = new InteractiveArrayAdapter(this,getModel());
		
		if(list.size() != 0)
			lv.setAdapter(adapter);
		else
			tempv.setText("No Friends");
		
		//adapter.notifyDataSetChanged();
		create_group_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//Toast.makeText(getApplicationContext(), "pressed create", Toast.LENGTH_SHORT).show();   
            	Intent i = new Intent(v.getContext(), MyList_name.class);
            	i.putExtra("sess_user", user);
            	
            	//ArrayList<String> tmp_friends = new ArrayList<String>();
            	
            	String[] tmp_friends = new String[list.size()];
            	int cot=0;
            	for(int j=0;j<list.size();j++)
            	{
            		Freind_model tmp_mod = list.get(j);
            		
            		if(tmp_mod.isSelected() == true)
            			tmp_friends[cot++]=tmp_mod.getName();
            	}
            	i.putExtra("size",cot);
            	i.putExtra("fnames", tmp_friends);
            	
                startActivity(i); 
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
		
		addfrd_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                // Perform action on click
            	//setContentView(R.layout.user_layout);
            	Intent i = new Intent(v.getContext(), Addfrd.class);
            	i.putExtra("sess_user", user);
                startActivityForResult(i,0);    
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

	private List<Freind_model> getModel() 
	{
		ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("username",user));
		
		String result_friends = db_con.create_conn("get_friends_list.php",nvp);
		
		list = new ArrayList<Freind_model>();
		
		try 
        {
        	 jArray = new JSONArray(result_friends);
        	 int length = jArray.length();
             for(int i=0;i<length;i++)
             {
                     JSONObject json_data = jArray.getJSONObject(i);
                     //tmp_friend.add(json_data.getString("friend_name"));
                     
                     list.add(get(json_data.getString("friend_name")));
             }
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Initially select one of the items
		//list.get(1).setSelected(true);
		return list;
	}
	
	@Override
	public void onResume() {
		adapter = new InteractiveArrayAdapter(this,getModel());
		//adapter.notifyDataSetChanged();
		lv.setAdapter(adapter);
	    super.onResume();
	}

	private Freind_model get(String s) {
		return new Freind_model(s);
	}

}