public class Mayan extends Language{
  Mayan(){
    super("Ki'che'",2330000,"Central America","verb-object-subject");
  }

  @Override
  public void getInfo(){
    
    System.out.println(name +" is spoken by "+numSpeakers+" people mainly in "+regionsSpoken+".");
    System.out.println(" The language follows the word order: " +wordOrder+".");
    System.out.println("Fun fact: Ki'che' is an ergative language.");
  }  
}
