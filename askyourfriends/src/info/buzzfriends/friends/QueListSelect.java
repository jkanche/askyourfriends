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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QueListSelect extends Activity {
	
	private dbcon db_con;
	private JSONArray jArray;
	private String q_id_tmp,grpid_tmp;
	String user;
	ListView ans_list_layout;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.q_layout);
		
		TextView q_body_layout = (TextView) findViewById(R.id.q_body);
		TextView q_from_layout = (TextView) findViewById(R.id.q_from);
		ans_list_layout = (ListView) findViewById(R.id.answer_list);
		Button but_new_post = (Button)findViewById(R.id.new_post_ans);
		Button share_but = (Button)findViewById(R.id.share);
		
		
		db_con = new dbcon();
		Bundle extras = getIntent().getExtras(); 
		q_id_tmp = extras.getString("uniq_id");
		user = extras.getString("sess_user");
		grpid_tmp = extras.getString("grpid");
		ArrayList<NameValuePair> nvp= new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("q_id",q_id_tmp));
		
		String result_question = db_con.create_conn("get_questions_Id.php",nvp);
		
		try 
        {
			jArray = new JSONArray(result_question);
       	 	JSONObject json_data = jArray.getJSONObject(0);
        	String str_tmp = json_data.getString("quest_body");
        	q_body_layout.setText(str_tmp);
        	str_tmp = json_data.getString("username");
        	q_from_layout.setText(str_tmp);
        	//str_tmp = Integer.toString(json_data.getInt("group_id"));
        	//q_to_layout.setText(str_tmp);     	
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        String result_answer = db_con.create_conn("get_answers.php",nvp);
        
        ArrayList<String> tmp_answer = new ArrayList<String>();
        try 
        {
        	 jArray = new JSONArray(result_answer);
        	 int length = jArray.length();
             for(int i=0;i<length;i++)
             {
                     JSONObject json_data = jArray.getJSONObject(i);
                     tmp_answer.add(json_data.getString("username")+" answered "+json_data.getString("answer"));
             }
		} 
        catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String[] ans_list = (String[]) tmp_answer.toArray(new String[0]);
        ans_list_layout.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ans_list));
        
        but_new_post.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	EditText new_ans_post = (EditText)findViewById(R.id.new_ans);
            	String new_answer = new_ans_post.getText().toString();
            	
            	ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
        		nvp.add(new BasicNameValuePair("q_id",q_id_tmp));
        		nvp.add(new BasicNameValuePair("username",user));
        		nvp.add(new BasicNameValuePair("ans_body",new_answer));
        		
        		String result = db_con.create_conn("post_answer.php",nvp);
        		try 
                {
					//json_data = jArray.getJSONObject(0);
                	JSONObject json_data = new JSONObject(result);
                	
                	
                	if(json_data.getString("reply").equals("true"))
                	{
                		Toast.makeText(getApplicationContext(), "Answer Posted Successfully", Toast.LENGTH_SHORT).show();
                		String result_answer = db_con.create_conn("get_answers.php",nvp);
                        
                        ArrayList<String> tmp_answer = new ArrayList<String>();
                        try 
                        {
                        	 jArray = new JSONArray(result_answer);
                        	 int length = jArray.length();
                             for(int i=0;i<length;i++)
                             {
                                     JSONObject json_data_but = jArray.getJSONObject(i);
                                     tmp_answer.add(json_data_but.getString("username")+" answered "+json_data_but.getString("answer"));
                             }
                		} 
                        catch (JSONException e) 
                		{
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                        
                        String[] ans_list = (String[]) tmp_answer.toArray(new String[0]);
                		ans_list_layout.setAdapter(new ArrayAdapter<String>(QueListSelect.this,android.R.layout.simple_list_item_1, ans_list));
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
        
        share_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	
            	Intent i = new Intent(v.getContext(), ShareFriend.class);
            	i.putExtra("sess_user", user);
            	i.putExtra("grpid",grpid_tmp);
                startActivity(i);
            }
        });
	}
}
