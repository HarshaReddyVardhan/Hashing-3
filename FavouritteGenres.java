// https://leetcode.com/playground/AJxVhTTq
// https://leetcode.com/discuss/post/373006/amazon-oa-2019-favorite-genres-by-sithis-7x5z/

// TC : M*K + N
//   M = number of users
//   K = average number of songs each user listens
//   N = Total no of songs in Genre songs
// SC : Auxiliary (excluding output): O(N + K)
//   O(N) for songToGenre, plus up to O(K) for a user’s freqCount (rebuilt per user).
//   Including output map: worst case O(N + M*K)
//   If many ties, a user can list up to O(K) favorite genres; across M users that’s O(M*K) stored.

// hashing 3 github -- https://github.com/super30admin/Hashing-3

public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
    HashMap<String, String> songToGenre = new HashMap<>();
    HashMap<String, List<String>> userGenre = new HashMap<>();
    
    for(String genre : genreMap.keySet()){
        for(String songs : genreMap.get(genre)){
            songToGenre.put(songs,genre);
        }
    }
    
    for(String user : userMap.keySet()){
        userGenre.put(user, new ArrayList<>());
        HashMap<String, Integer> freqCount = new HashMap<>();
        List<String> songs = userMap.get(user);
        int max =0;
        for(String song : songs){
            String x = songToGenre.get(song);
            freqCount.put( x, freqCount.getOrDefault(x,0)+1);
            max = Math.max(freqCount.get(x),max);
        }
        
        for(String genre : freqCount.keySet()){
            if(freqCount.get(genre) == max){
                userGenre.get(user).add(genre);
            }
        }
    }
    return userGenre;
}

public static void main(String[] args) {
        HashMap<String, List<String>> userSongs = new HashMap<>();

        userSongs.put("David", Arrays.asList(new String[]{"song1", "song2", "song3", "song4", "song8"}));

        userSongs.put("Emma", Arrays.asList(new String[]{"song5", "song6", "song7"}));

        HashMap<String, List<String>> songGenres = new HashMap<>();

        songGenres.put("Rock", Arrays.asList(new String[]{"song1", "song3"}));

        songGenres.put("Dubstep", Arrays.asList(new String[]{"song7"}));

        songGenres.put("Techno", Arrays.asList(new String[]{"song2", "song4"}));

        songGenres.put("Pop", Arrays.asList(new String[]{"song5", "song6"}));

        songGenres.put("Jazz", Arrays.asList(new String[]{"song8", "song9"}));

        Map<String, List<String>> res = favoritegenre(userSongs, songGenres);

        System.out.println(res);
}
