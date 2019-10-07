package io.stockroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TopSearchQuery2 {

	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		int M = 0;
		List<List<String>> mainList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			line = br.readLine();
			M = Integer.parseInt(line);
			List<String> list = new ArrayList<String>();
			for (int j = 0; j < M; j++) {
				line = br.readLine();
				list.add(line);
			}
			mainList.add(list);
		}

		for (List<String> list : mainList) {
			List<String> sendList = new ArrayList<String>();
			for (String string : list) {
				if (string.contains(" ")) {
					String[] arr = string.split(" ");
					int top = Integer.parseInt(arr[1]);
					calcTop(sendList, top);
				} else {
					sendList.add(string);
				}
			}
		}

	}

	private static void calcTop(List<String> list, int top) {
		Map<String, Query> map = new HashMap<String, Query>();
		for (String string : list) {
			if (map.containsKey(string)) {
				Query query = map.get(string);
				query.setCount(query.getCount() + 1);
			} else {
				map.put(string, new Query(string, 1));
			}
		}
		printTop(map, top);
	}

	private static void printTop(Map<String, Query> map, int top) {

		Set<Query> set = new TreeSet<Query>();

		for (Map.Entry<String, Query> entry : map.entrySet())
			set.add(entry.getValue());

		Iterator<Query> iterator = set.iterator();

		for (int i = 0; i < top - 1; i++) {
			System.out.print(iterator.next().getValue() + " ");
		}

		System.out.println(iterator.next().getValue());
	}
}

class Query implements Comparable<Query> {

	private String value;
	private int count;

	public Query(String value, int count) {
		super();
		this.value = value;
		this.count = count;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(Query arg0) {

		if (this.count > arg0.count)
			return -1;
		else if (this.count < arg0.count)
			return 1;
		else
			return this.value.compareTo(arg0.value);
	}
}