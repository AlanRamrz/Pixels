import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		List<String> pixels = new ArrayList<>();
		pixels.add("101111010110011011100100");

		List<String> res = closestColor(pixels);
		
		for (String item : res) {
			System.out.println(item);
		}
	}

	public static List<String> closestColor(List<String> pixels) {
		List<String> res = new ArrayList<>();

		for (String color : pixels) {
			int r = binaryToDecimal(color.substring(0, 8));
			int g = binaryToDecimal(color.substring(8, 16));
			int b = binaryToDecimal(color.substring(16));
			
			double minor = 100000;
			String minorColor = "";

			HashMap<String, Double> map = new HashMap<>();
			map.put("Black", distance(r, g, b, 0, 0, 0));
			map.put("White", distance(r, g, b, 255, 255, 255));
			map.put("Red", distance(r, g, b, 255, 0, 0));
			map.put("Green", distance(r, g, b, 0, 255, 0));
			map.put("Blue", distance(r, g, b, 0, 0, 255));
			
			Iterator<Map.Entry<String, Double>> it = map.entrySet().iterator();
			
			while (it.hasNext()) {
				Map.Entry<String, Double> item = it.next();
				System.out.println(item.getKey() + "->" + item.getValue());
				
				if(item.getValue() < minor) {
					minor = item.getValue();
					minorColor = item.getKey();
				}
			}
			
			res.add(minorColor);
		}

		return res;

	}

	public static int binaryToDecimal(String binary) {
		return Integer.parseInt(binary, 2);
	}

	public static double distance(int r1, int g1, int b1, int r2, int g2, int b2) {
		double r = r1 - r2;
		double g = g1 - g2;
		double b = b1 - b2;
		
		double distance = Math.sqrt((Math.pow(r, 2)) + (Math.pow(g, 2)) + (Math.pow(b, 2)));
		
		return distance;
	}

}
