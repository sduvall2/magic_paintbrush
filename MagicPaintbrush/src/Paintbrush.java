
import java.util.HashMap;

import processing.core.PApplet;

public class Paintbrush extends PApplet {
	
	Palette.Colors currentColor;
	Palette.Mixers currentMix;
	HashMap<Character,Palette.Mixers> keys;
	Palette palette;
	
	double[] rgb = {255,255,255};
	double[] splat = {255,255,255};
	double[] delta = {0,0,0};
	double[] deltaSplat = {0,0,0};
	
	int[][] splatter = new int[1][3];
	
	final double RATE = 80;
	
	public void setup() {
		smooth();
		size(500,500);
		palette = new Palette();
		keys = new HashMap<Character, Palette.Mixers>();
		keys.put('w', Palette.Mixers.WHITE);
		keys.put('d', Palette.Mixers.BLACK);
		keys.put('r', Palette.Mixers.RED);
		keys.put('y', Palette.Mixers.YELLOW);
		keys.put('b', Palette.Mixers.BLUE);
		keys.put('c', Palette.Mixers.CLEAR);
		
		currentColor = Palette.Colors.WHITE;
	}

	public void draw() {
		
		background((int)rgb[0],(int)rgb[1],(int)rgb[2]);
		fill((int)splat[0], (int)splat[1], (int)splat[2]);
		noStroke();
		ellipse(width/2, height/2, 100,100);
		ellipse(width/2+60, height/2-60, 180,180);
		ellipse(width/2-45, height/2-40, 170,170);
		//makeSplatter();
		for(int i = 0; i< splatter.length; i++){
			ellipse(splatter[i][0], splatter[i][1], splatter[i][2], splatter[i][2]);
		}
		
		rgb[0] = rgb[0] + delta[0];
		rgb[1] = rgb[1] + delta[1];
		rgb[2] = rgb[2] + delta[2];
		//System.out.print("RGB: ");
		//for(int i = 0; i< 3; i++){
		//	System.out.print(rgb[i]+" ");
		//}
		//System.out.println();
		//System.out.print("SPLAT ");
		splat[0] = splat[0] + deltaSplat[0];
		splat[1] = splat[1] + deltaSplat[1];
		splat[2] = splat[2] + deltaSplat[2];
		//for(int i = 0; i< 3; i++){
		//	System.out.print(splat[i]+" ");
		//}
		//System.out.println();
		int[] aim = palette.getRGB(currentColor);
		for(int i = 0; i<3; i++){
			if(Math.abs(rgb[i]-aim[i]) < 2) delta[i] = 0;
			if(Math.abs(splat[i]-aim[i]) < 2) deltaSplat[i] = 0;
		}
	}
	
	public void makeSplatter(){
		int dots =(int)(Math.random()*(8-3+1)+3);
		
		splatter = new int[dots][3];
		for(int i = 0; i < dots; i++){
			int x = (int)(Math.random()*(width/2+250-(width/2-250)+1)+width/2-250);
			int y = (int)(Math.random()*(height/2+250-(height/2-250)+1)+height/2-250);
			int size = (int)(Math.random()*100);
			splatter[i][0] = x;
			splatter[i][1] = y;
			splatter[i][2] = size;
		}
	}
	
	public void keyPressed(){
		Palette.Mixers result = keys.get(key);
		if(result != null){
			currentMix = result;
			cueChange(result);
		}
	}
	
	public void cueChange(Palette.Mixers mixIn){
		if(mixIn == Palette.Mixers.CLEAR){
			currentColor = Palette.Colors.WHITE;
			rgb = new double[]{255,255,255};
			splat = new double[]{255,255,255};
			delta = new double[]{0,0,0};
			deltaSplat = new double[]{0,0,0};
		}
		else{
			currentColor = palette.getMix(currentColor, mixIn);	
			int[] aimColor = palette.getRGB(currentColor);
			int[] splatColor = palette.getRGB(mixIn);
			splat = new double[]{splatColor[0], splatColor[1], splatColor[2]};
			for(int i = 0; i<3; i++){
				delta[i] = (aimColor[i]-rgb[i])/RATE;
				deltaSplat[i] = (aimColor[i]-splat[i])/RATE;
				//System.out.print("Delt, DeltSplat: "+delta[i]+" "+deltaSplat[i]+" :: ");
			}
			makeSplatter();
			//System.out.println();
		}
	}
}
