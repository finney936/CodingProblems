package io.stockroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* package whatever; // don't place package name! */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* Name of the class has to be "Main" only if the class is public. */
public class TopSearchQuery1
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        int M = 0;
        List<List<String>> mainList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            line = br.readLine();
            M = Integer.parseInt(line);
            List<String> list = new ArrayList<String>();
            for(int j = 0; j < M; j++){
                line = br.readLine();
                list.add(line);
            }
            mainList.add(list);
        }
        
        for(List<String> list: mainList) calcTop(list);
	}
	
	private static void calcTop(List<String> list){
	    
	    Map<String, Integer> map = new HashMap<>();
	    
	    for(String string: list){
	        if(string.contains(" ")){
	            String[] arr = string.split(" ");
	            int top = Integer.parseInt(arr[1]);
	            printTop(map, top);
	        }
	        else{
	            if(map.containsKey(string)){
	                int count = map.get(string);
	                count++;
	                map.put(string, count);
	            }
	            else{
	                map.put(string, 1);
	            }
	        }
	    }
	}
	
	private static void printTop(Map<String, Integer> map, int top){
	    
        map = sortByValue(map);
        int prev = 0;
        int now = 0;
        Set<String> set = new TreeSet<>();
        Set<String> mainSet = new LinkedHashSet<>();
        for(int i = 0; i < top; i++){
            now = map.get(map.keySet().toArray()[i]);
            if(now == prev || i == 0){
                set.add((String)map.keySet().toArray()[i]);
            }
            else{
                mainSet.addAll(set);
                set.clear();
            	set.add((String)map.keySet().toArray()[i]);
            }
            prev = now;
        }
        
        mainSet.addAll(set);
        
        Iterator<String> iterator = mainSet.iterator();
        
        for(int i = 0; i < mainSet.size() - 1; i++) {
        	System.out.print(iterator.next() + " ");
        }
        
        System.out.println(iterator.next());
	}
	
	public static HashMap<String, Integer> sortByValue(Map<String, Integer> hm) 
    { 

        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return -1*(o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        
        return temp; 
    }
}