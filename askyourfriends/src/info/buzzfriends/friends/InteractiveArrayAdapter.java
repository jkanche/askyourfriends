package info.buzzfriends.friends;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<Freind_model> {

	private final List<Freind_model> list;
	private final Activity context;

	public InteractiveArrayAdapter(Activity context, List<Freind_model> list) {
		super(context, R.layout.answer_row_layout, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) 
		{
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.answer_row_layout, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.a_text);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.a_check);
			viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
							Freind_model element = (Freind_model) viewHolder.checkbox.getTag();
							element.setSelected(buttonView.isChecked());
						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} 
		else 
		{
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());
		holder.checkbox.setChecked(list.get(position).isSelected());
		return view;
	}
}