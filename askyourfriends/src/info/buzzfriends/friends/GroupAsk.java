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
import android.widget.TextView;
import android.widget.Toast;

public class GroupAsk extends Activity {
	
	private dbcon db_con;
	private JSONArray jArray;
	private String group_id;
	String user;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_question_group);
		
		TextView grpname_layout = (TextView) findViewById(R.id.grp_name);
		TextView friend_name_layout = (TextView) findViewById(R.id.friend_name_list);
		Button submit_layout = (Button)findViewById(R.id.submit);
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		group_id = extras.getString("grp_id");
		user = extras.getString("sess_user");
		
		ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("grp_id",group_id));
		
		String result_group = db_con.create_conn("get_group_id.php",nvp);
		
		try 
        {
			jArray = new JSONArray(result_group);
       	 	JSONObject json_data = jArray.getJSONObject(0);
        	String str_tmp = json_data.getString("group_name");
        	grpname_layout.setText(str_tmp);   	
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        String result_friends = db_con.create_conn("get_friends_groupid.php",nvp);
        
        ArrayList<String> tmp_friend = new ArrayList<String>();
        try 
        {
        	 jArray = new JSONArray(result_friends);
        	 int length = jArray.length();
             for(int i=0;i<length;i++)
             {
                     JSONObject json_data = jArray.getJSONObject(i);
                     tmp_friend.add(json_data.getString("friend_name"));
             }
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        friend_name_layout.setText(tmp_friend.toString());
        
        submit_layout.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	EditText new_ques = (EditText)findViewById(R.id.grp_quest_body);
            	String new_q = new_ques.getText().toString();
            	
            	ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
        		nvp.add(new BasicNameValuePair("grp_id",group_id));
        		nvp.add(new BasicNameValuePair("username",user));
        		nvp.add(new BasicNameValuePair("grp_quest",new_q));
        		
        		String result = db_con.create_conn("post_question.php",nvp);
        		try 
                {
					//json_data = jArray.getJSONObject(0);
                	JSONObject json_data = new JSONObject(result);
                	
                	
                	if(json_data.getString("reply").equals("true"))
                	{
                		Toast.makeText(getApplicationContext(), "Answer Posted Successfully", Toast.LENGTH_SHORT).show();
                		finish();
                	}
                	else
                		Toast.makeText(getApplicationContext(), "Technical Issue in posting answer", Toast.LENGTH_SHORT).show();
                		
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
