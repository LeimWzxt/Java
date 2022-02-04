public class Language{
  protected String name;
  protected int numSpeakers;
  protected String regionsSpoken;
  protected String wordOrder;

  Language(String name,int numSpeakers, String regionsSpoken,String wordOrder) {
    this.name = name;
    this.numSpeakers = numSpeakers;
    this.regionsSpoken = regionsSpoken;
    this.wordOrder = wordOrder;
  }

  public void getInfo(){
    System.out.println(name +" is spoken by "+numSpeakers+" people mainly in "+regionsSpoken+".");
    System.out.println(" The language follows the word order: " +wordOrder+".");
  }

  public static void main(String[] args){
    Language lan = new Language("a",12,"s","f");
    lan.getInfo();

    Mayan man = new Mayan();
    man.getInfo();

    SinoTibetan sin = new SinoTibetan("algo Chinese", 545613);
    sin.getInfo();
    SinoTibetan con = new SinoTibetan("algo pam", 545613);
    con.getInfo();

  }
}
