
public class Paint {
	public String name;
	public int[] rgb;
	public Paint[] mixResults;
	
	public Paint(int r, int g, int b, Paint[]mixColors){
		rgb = new int[]{r,g,b};
		mixResults = mixColors;
	}
}
