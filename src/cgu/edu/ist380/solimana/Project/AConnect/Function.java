package cgu.edu.ist380.solimana.Project.AConnect;

public class Function {

	
	private String name;
	private String image; //image file path
	private String sound; //sound file path
	private String cat_tab; //category tab
	private Long date_created; //revisit to check if you can manipulate it
	private int number_of_clicks;
	
	public int getNumber_of_clicks() {
		return number_of_clicks;
	}
	public Function(String cat_tab, Long date_created, int number_of_clicks) {
		super();
		this.cat_tab = "General";//To add it to the General tab as a default option
		this.date_created = System.currentTimeMillis();//current time
		this.number_of_clicks = 0;
	}		
	public void setNumber_of_clicks(int number_of_clicks) {
		this.number_of_clicks = number_of_clicks;
	}
	public String getCat_tab() {
		return cat_tab;
	}
	public void setCat_tab(String cat_tab) {
		this.cat_tab = cat_tab;
	}
	public Long getDate_created() {
		return date_created;
	}
	public void setDate_created(Long date_created) {
		this.date_created = date_created;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
