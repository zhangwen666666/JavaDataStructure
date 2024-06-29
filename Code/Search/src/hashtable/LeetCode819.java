package hashtable;

import java.util.*;
import java.util.function.BiFunction;

public class LeetCode819 {
    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        String s = new LeetCode819().mostCommonWord(paragraph, banned);
        System.out.println(s);
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        //将禁用词添加到set集合中
        Set<String> set = Set.of(banned);

        //将paragraph分割为一个个的单词
//        String paragraphLowerCase = paragraph.toLowerCase();
//        String[] split = paragraphLowerCase.split("[^a-z]+");
//        //System.out.println(Arrays.toString(split));
        HashMap<String, Integer> map = new HashMap<>();
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        String key = null;
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                key = sb.toString();
                //将不是禁用词的单词放入map中
                if (!set.contains(key) && !sb.isEmpty()) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
                sb = new StringBuilder();
            }
        }
        key = sb.toString();
        if (!sb.isEmpty() && !set.contains(key)) {
            map.compute(key, (k, v) -> v == null ? 1 : v + 1);
        }
        System.out.println(map);

//        //将不是禁用词的单词存放到Map集合中
//        HashMap<String, Integer> map = new HashMap<>();
//        for (String word : split) {
//            /*Integer count = map.get(word);
//            if (count == null) {
//                map.put(word, 0);
//            } else {
//                map.put(word, count + 1);
//            }*/
//            if (!set.contains(word)) {
//                map.compute(word, (k, v) -> v == null ? 1 : v + 1);
//            }
//        }

//        Optional<Map.Entry<String, Integer>> max = map.entrySet().stream().max((o1, o2) -> o1.getValue() - o2.getValue());
//        Map.Entry<String, Integer> entry = max.get();
//        return entry.getKey();
//        System.out.println(max.get());
//        return null;
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        int maxCount = 0;
        String ret = null;
        for (Map.Entry<String, Integer> entry : entrySet) {
            Integer value = entry.getValue();
            if (value > maxCount) {
                maxCount = value;
                ret = entry.getKey();
            }
        }
        return ret;
    }
}
