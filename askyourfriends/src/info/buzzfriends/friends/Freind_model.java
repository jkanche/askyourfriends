package info.buzzfriends.friends;

public class Freind_model {

	private String name;
	private boolean selected;
	private int friend_id;

	public Freind_model(String name) {
		this.name = name;
		selected = false;
		//this.friend_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getid() {
		return friend_id;
	}

	public void setid(int id) {
		this.friend_id = id;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}