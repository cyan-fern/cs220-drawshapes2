package knox.drawshapes;

import java.awt.Color;

public class Util
{
	static String hextable = "0123456789abcdef";
	public static String colorToHex(Color color) {
		int bitmask2 = 1|(1<<1);
		int bitmask4 = bitmask2|(bitmask2<<2);
		return String.format("%c%c%c%c%c%c%c%c",
				hextable.charAt(color.getAlpha()>>4&bitmask4),hextable.charAt(color.getAlpha()&bitmask4),
				hextable.charAt(color.getRed()>>4&bitmask4),hextable.charAt(color.getRed()&bitmask4),
				hextable.charAt(color.getGreen()>>4&bitmask4),hextable.charAt(color.getGreen()&bitmask4),
				hextable.charAt(color.getBlue()>>4&bitmask4),hextable.charAt(color.getBlue()&bitmask4));
	}
	
	public static Color hexToColor(String color) {
		if(color.length()!=8) {throw new UnsupportedOperationException("Invalid hex string length");}
		int num = 0;
		for(int i=0;i<color.length();i++) {
			char th = color.charAt(i);
			num<<=4;
			if(th<0x30||th>0x66) {}//invalid
			else if(th>0x60) {num|=(th-0x57);}
			else if(th>0x39) {}
			else if(th>0x2f) {num|=(th-0x30);}
		}
		return new Color(num,true);
	}
}
