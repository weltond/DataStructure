import java.util.*;

public class test {
    class Solution {
        public List<String> letterCombinations(String digits) {
            Map<Character, List<Character>> map = new HashMap();
            map.put('2', new ArrayList<Character>(Arrays.asList('a','b','c')));
            map.put('3', new ArrayList<Character>(Arrays.asList('d','e','f')));
            map.put('4', new ArrayList<Character>(Arrays.asList('g','h','i')));
            map.put('5', new ArrayList<Character>(Arrays.asList('j','k','l')));
            map.put('6', new ArrayList<Character>(Arrays.asList('m','n','o')));
            map.put('7', new ArrayList<Character>(Arrays.asList('p','q','r','s')));
            map.put('8', new ArrayList<Character>(Arrays.asList('t','u','v')));
            map.put('9', new ArrayList<Character>(Arrays.asList('w','x','y','z')));
            List<String> list = new ArrayList();
            if (digits == null || digits.length() == 0) return list;

            //int size = digits.length() - 1;
            dfs(digits, 0, new StringBuilder(), list, map);
            return list;
        }

        public void dfs(String digits, int lvl, StringBuilder sb, List<String> list, Map map) {
            if (lvl == digits.length() - 1) {
                list.add(sb.toString());
                return;
            }
            ArrayList<Character> l= (ArrayList) map.get(digits.charAt(lvl));
            for (Character c: l) {
                sb.append(c);
                //Thread
                dfs(digits, lvl + 1, sb, list, map);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
