package com.fcg.genericlib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains common utility methods which can be used in writing automation test cases.
 * 
 * @since July 2018
 * @author pankhurisharma
 *
 */
public class CommonMethods {
	
	/**
	 * This is a common method used to Compare List of Strings.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public static boolean compareList(List<String> list1, List<String> list2) {
		System.out.println(list1);
		System.out.println(list2);
		if (list1 == null && list2 == null){
			return true;
		}

		if((list1 == null && list2 != null) 
				|| list1 != null && list2 == null
				|| list1.size() != list2.size()){
			return false;
		}

		list1 = new ArrayList<String>(list1); 
		list2 = new ArrayList<String>(list2);   

		Collections.sort(list1);
		Collections.sort(list2);      
		return list1.equals(list2);

	}

}
