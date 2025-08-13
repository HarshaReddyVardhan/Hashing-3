// Rolling Hash Solution - Rabin Karp
// Time: O(n)

// Space: O(n) worst case (distinct hashes + results)
  
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('C', 3);
        map.put('G', 5);
        map.put('T', 7);
        int kl = (int) Math.pow(4, 9);
        HashSet<String> result = new HashSet<>();
        HashSet<Integer> set = new HashSet<>();
        int hash = 0;
        for (int i = 0; i < n; ++i) {
            // perform hashout
            if (i >= 10) {
                char out = s.charAt(i - 10);
                hash = hash - kl * map.get(out);
            }

            // in
            char in = s.charAt(i);
            hash = hash * 4 + map.get(in);
            if (set.contains(hash)) {
                result.add(s.substring(i - 9, i + 1));
            } else {
                set.add(hash);
            }
        }
        return new ArrayList<>(result);
    }
}
