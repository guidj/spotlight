package dsc.spotlight.utils;

public class Text {
	
	public static String[] joinByToken(String[] a, String[] b, String token) {
		
		if (a.length != b.length) {
			throw new RuntimeException("Length of arrays does not match");
		}
		
		String[] mix = new String[a.length];
		
		for(int i = 0; i < a.length; i++) {
			mix[i] = a[i] + token + b[i];
		}
		
		return mix;
	}

}
