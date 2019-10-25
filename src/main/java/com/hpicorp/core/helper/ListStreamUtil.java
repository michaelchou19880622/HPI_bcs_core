package com.hpicorp.core.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListStreamUtil {

	private ListStreamUtil() {
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static List<Set<String>> splitListBy150(List<String> uidList) {
		List<Set<String>> multiList = new ArrayList<>();
		final int N = uidList.size();
		for (int i = 0; i < N; i += 150) {
			multiList.add(new HashSet<String>(uidList.subList(i, Math.min(N, i + 150))));
		}
		return multiList;
	}
	
}
