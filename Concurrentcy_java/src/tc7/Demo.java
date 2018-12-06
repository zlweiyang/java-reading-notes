package tc7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Demo {
	
	public static void main(String[] args) {
		
		ArrayList<String> s = new ArrayList<>();
		Collections.synchronizedList(s);
		
		HashMap<String, Object> res = new HashMap<>();
		Collections.synchronizedMap(res);
	}

}
