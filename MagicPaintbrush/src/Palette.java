import java.util.EnumMap;

public class Palette {
	
	public enum Mixers{
		WHITE, BLACK, RED, BLUE, YELLOW, CLEAR;
		
		private Colors[] myColors;
		
		Mixers (Colors ... c) {
			myColors = c;
		}
		
		public Colors mix (Colors c) {
			return myColors[c.ordinal()];
		}
	}
	
	public enum Colors{
		WHITE(255,255,255),
		GRAY,
		BLACK,
		LGRAY, DGRAY,
		BLUE, DBLUE, LBLUE,
		YELLOW, DYELLOW, LYELLOW,
		RED, DRED, PINK, DPINK,
		PURPLE, LPURPLE, DPURPLE,
		GREEN, LGREEN, DGREEN,
		ORANGE, LORANGE, DORANGE,
		BROWN, LBROWN, DBROWN;	
		
		int r, g, b;
		Colors () {
			this(0, 0, 0);
		}

		Colors (int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		int[] getValues () {
			return  new int[]{ r, g, b };
		}
	}
	
	public EnumMap<Colors, EnumMap<Mixers, Colors>> palette;
	
	public final int NUM_COLORS = 27;
	public final int NUM_MIXERS = 6;
	
	public EnumMap<Colors,int[]> rgbMap = new EnumMap<>(Colors.class);
	public EnumMap<Mixers, Colors> mixToColor = new EnumMap<>(Mixers.class);
	
	//public EnumMap<Colors, EnumMap<Colors, Colors>> palette;
	//public EnumMap<Colors,int[]> rgbMap = new EnumMap<>(Colors.class);
	//public EnumMap<Colors, Colors> mixToColor = new EnumMap<>(Colors.class);

	
	public Palette(){
		makeRGB();
		makePalette();
	}
	
	//public Paint red = new Paint(150,0,0,null);
	//public Paint blue = new Paint(0,0,150,null);
	
	public void makeRGB(){
		rgbMap.put(Colors.WHITE, new int[]{255,255,255});
		rgbMap.put(Colors.GRAY, new int[]{190,190,190});
		rgbMap.put(Colors.DGRAY, new int[]{80,80,80});
		rgbMap.put(Colors.LGRAY, new int[]{220,220,220});
		rgbMap.put(Colors.BLACK, new int[]{0,0,0});
		
		rgbMap.put(Colors.BLUE, new int[]{0,0,255});
		rgbMap.put(Colors.DBLUE, new int[]{0,0,128});
		rgbMap.put(Colors.LBLUE, new int[]{100,149,237});
		
		rgbMap.put(Colors.YELLOW, new int[]{255,255,0});
		rgbMap.put(Colors.DYELLOW, new int[]{255,215,0});
		rgbMap.put(Colors.LYELLOW, new int[]{255,250,205});
		
		rgbMap.put(Colors.RED, new int[]{255,0,0});
		rgbMap.put(Colors.DRED, new int[]{178,34,34});
		rgbMap.put(Colors.PINK, new int[]{255,105,180});
		rgbMap.put(Colors.DPINK, new int[]{176,48,96});
		
		rgbMap.put(Colors.PURPLE, new int[]{160,32,240});
		rgbMap.put(Colors.LPURPLE, new int[]{218,112,214});
		rgbMap.put(Colors.DPURPLE, new int[]{148,0,211});
		
		rgbMap.put(Colors.GREEN, new int[]{0,255,0});
		rgbMap.put(Colors.LGREEN, new int[]{173,255,47});
		rgbMap.put(Colors.DGREEN, new int[]{0,100,0});
		
		rgbMap.put(Colors.ORANGE, new int[]{255,165,0});
		rgbMap.put(Colors.LORANGE, new int[]{255,160,122});
		rgbMap.put(Colors.DORANGE, new int[]{255,140,0});
		
		rgbMap.put(Colors.BROWN, new int[]{160,82,45});
		rgbMap.put(Colors.LBROWN, new int[]{210,180,140});
		rgbMap.put(Colors.DBROWN, new int[]{139,69,19});
		
	}
	
	public Colors getMix(Colors color, Mixers mix){
		return palette.get(color).get(mix);
	}
	
	public int[] getRGB(Colors color){
		return rgbMap.get(color);
	}
	
	public int[] getRGB(Mixers color){
		return rgbMap.get(mixToColor.get(color));
	}
	
	public void makePalette(){
		mixToColor.put(Mixers.BLACK, Colors.BLACK);
		mixToColor.put(Mixers.BLUE, Colors.BLUE);
		mixToColor.put(Mixers.CLEAR, Colors.WHITE);
		mixToColor.put(Mixers.RED, Colors.RED);
		mixToColor.put(Mixers.WHITE, Colors.WHITE);
		mixToColor.put(Mixers.YELLOW, Colors.YELLOW);
		
		// WHITE, BLACK, RED, BLUE, YELLOW
		palette = new EnumMap<>(Colors.class);
		palette.put(Colors.WHITE, makeOneMixer(new Colors[]{
				Colors.WHITE, Colors.BLACK, Colors.RED, Colors.BLUE, Colors.YELLOW 
		}));
		palette.put(Colors.GRAY, makeOneMixer(new Colors[]{
				Colors.LGRAY, Colors.DGRAY, Colors.DRED, Colors.DBLUE, Colors.DYELLOW 
		}));
		palette.put(Colors.DGRAY, makeOneMixer(new Colors[]{
				Colors.GRAY, Colors.BLACK, Colors.DRED, Colors.DBLUE, Colors.DYELLOW 
		}));
		palette.put(Colors.LGRAY, makeOneMixer(new Colors[]{
				Colors.WHITE, Colors.GRAY, Colors.DRED, Colors.DBLUE, Colors.DYELLOW 
		}));
		palette.put(Colors.BLACK, makeOneMixer(new Colors[]{
				Colors.GRAY, Colors.BLACK, Colors.DRED, Colors.DBLUE, Colors.DYELLOW 
		}));
		
		
		palette.put(Colors.BLUE, makeOneMixer(new Colors[]{
				Colors.LBLUE, Colors.DBLUE, Colors.PURPLE, Colors.BLUE, Colors.GREEN
		}));
		palette.put(Colors.DBLUE, makeOneMixer(new Colors[]{
				Colors.BLUE, Colors.DGRAY, Colors.DPURPLE, Colors.BLUE, Colors.DGREEN 
		}));
		palette.put(Colors.LBLUE, makeOneMixer(new Colors[]{
				Colors.LGRAY, Colors.BLUE, Colors.LPURPLE, Colors.BLUE, Colors.LGREEN 
		}));
		
		
		palette.put(Colors.YELLOW, makeOneMixer(
				Colors.LYELLOW, Colors.DYELLOW, Colors.ORANGE, Colors.GREEN, Colors.YELLOW
		));
		palette.put(Colors.DYELLOW, makeOneMixer(new Colors[]{
				Colors.YELLOW, Colors.BROWN, Colors.DORANGE, Colors.DGREEN, Colors.YELLOW
		}));
		palette.put(Colors.LYELLOW, makeOneMixer(new Colors[]{
				Colors.LGRAY, Colors.YELLOW, Colors.LORANGE, Colors.LGREEN, Colors.YELLOW
		}));
		palette.put(Colors.RED, makeOneMixer(new Colors[]{
				Colors.PINK, Colors.DRED, Colors.RED, Colors.PURPLE, Colors.ORANGE
		}));
		palette.put(Colors.DRED, makeOneMixer(new Colors[]{
				Colors.RED, Colors.BROWN, Colors.RED, Colors.DPURPLE, Colors.DORANGE
		}));
		palette.put(Colors.PINK, makeOneMixer(new Colors[]{
				Colors.LGRAY, Colors.DPINK, Colors.RED, Colors.LPURPLE, Colors.LORANGE
		}));
		palette.put(Colors.DPINK, makeOneMixer(new Colors[]{
				Colors.PINK, Colors.LBROWN, Colors.RED, Colors.DPURPLE, Colors.DORANGE
		}));
		
		palette.put(Colors.PURPLE, makeOneMixer(new Colors[]{
				Colors.LPURPLE, Colors.DPURPLE, Colors.DPURPLE, Colors.DPURPLE, Colors.BROWN
		}));
		palette.put(Colors.LPURPLE, makeOneMixer(new Colors[]{
				Colors.GRAY, Colors.PURPLE, Colors.DPURPLE, Colors.DPURPLE, Colors.LBROWN
		}));
		palette.put(Colors.DPURPLE, makeOneMixer(new Colors[]{
				Colors.PURPLE, Colors.DGRAY, Colors.DBROWN, Colors.DBROWN, Colors.BROWN
		}));
		
		// WHITE, BLACK, RED, BLUE, YELLOW
		palette.put(Colors.GREEN, makeOneMixer(new Colors[]{
				Colors.LGREEN, Colors.DGREEN, Colors.BROWN, Colors.DGREEN, Colors.LYELLOW
		}));
		palette.put(Colors.LGREEN, makeOneMixer(new Colors[]{
				Colors.LYELLOW, Colors.GREEN, Colors.LBROWN, Colors.DGREEN, Colors.LYELLOW
		}));
		palette.put(Colors.DGREEN, makeOneMixer(new Colors[]{
				Colors.GREEN, Colors.DGRAY, Colors.DBROWN, Colors.BROWN, Colors.GREEN
		}));
		
		palette.put(Colors.ORANGE, makeOneMixer(new Colors[]{
				Colors.LORANGE, Colors.DORANGE, Colors.DORANGE, Colors.BROWN, Colors.LORANGE
		}));
		palette.put(Colors.LORANGE, makeOneMixer(new Colors[]{
				Colors.GRAY, Colors.ORANGE, Colors.DORANGE, Colors.BROWN, Colors.LYELLOW
		}));
		palette.put(Colors.DORANGE, makeOneMixer(new Colors[]{
				Colors.ORANGE, Colors.BROWN, Colors.DRED, Colors.DBROWN, Colors.ORANGE
		}));
		
		palette.put(Colors.BROWN, makeOneMixer(new Colors[]{
				Colors.LBROWN, Colors.DBROWN, Colors.DBROWN, Colors.DBROWN, Colors.LBROWN
		}));
		palette.put(Colors.LBROWN, makeOneMixer(new Colors[]{
				Colors.LGRAY, Colors.DBROWN, Colors.BROWN, Colors.BROWN, Colors.GRAY
		}));
		palette.put(Colors.DBROWN, makeOneMixer(new Colors[]{
				Colors.BROWN, Colors.BLACK, Colors.BLACK, Colors.BLACK, Colors.BROWN
		}));
	}

	public EnumMap<Mixers, Colors> makeOneMixer(Colors ... colors){
		EnumMap<Mixers, Colors> result = new EnumMap<>(Mixers.class);
		result.put(Mixers.WHITE, colors[0]);
		result.put(Mixers.BLACK, colors[1]);
		result.put(Mixers.RED, colors[2]);
		result.put(Mixers.BLUE, colors[3]);
		result.put(Mixers.YELLOW, colors[4]);
		return result;
	}
}
