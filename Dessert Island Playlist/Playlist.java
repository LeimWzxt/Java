import java.util.ArrayList;

class Playlist {
  public static void main(String[] args) {
    ArrayList<String> desertIslandPlaylist = new ArrayList<String>();
    desertIslandPlaylist.add("s1");
    desertIslandPlaylist.add("s2");
    desertIslandPlaylist.add("s3");
    desertIslandPlaylist.add("s4");
    desertIslandPlaylist.add("s5");
    desertIslandPlaylist.add("s6");

    System.out.println("---lvl1");
    System.out.println(desertIslandPlaylist);
    System.out.println(desertIslandPlaylist.size());

    System.out.println("---lvl2");
    desertIslandPlaylist.remove(5);
    System.out.println(desertIslandPlaylist.size());
    System.out.println(desertIslandPlaylist);
    
    System.out.println("---lvl3");
    String alter = desertIslandPlaylist.get(2);
    desertIslandPlaylist.set(2, desertIslandPlaylist.get(0));
    desertIslandPlaylist.set(0, alter);
    System.out.println(desertIslandPlaylist);
  }
}
