public class MadLibs {
  /*
    This program generates a mad libbed story.
    Author: Yomira
    Date: 30/11/2021
  */
  	public static void main(String[] args){
      
      String name1 = "Koa";
      String name2 = "Kim";
      String noun1 = "cat";
      String noun2 = "shark";
      String noun3 = "princess";
      String noun4 = "frog";
      String noun5 = "titan";
      String noun6 = "cocrodile";
      String adjective1 = "fast";
      String adjective2 = "red";
      String adjective3 = "big";
      String verb1 = "is runnig";
      String place1 = "space";
      double number = 251.31;

      
      //The template for the story
      String story = "This morning "+name1+" woke up feeling "+adjective1+". 'It is going to be a "+adjective2+" day!' Outside, a bunch of "+noun1+"s were protesting to keep "+noun2+" in stores. They began to "+verb1+" to the rhythm of the "+noun3+", which made all the "+noun4+"s very "+adjective3+". Concerned, "+name1+" texted "+name2+", who flew "+name1+" to "+place1+" and dropped "+name1+" in a puddle of frozen "+noun5+". "+name1+" woke up in the year "+number+", in a world where "+noun6+"s ruled the world.";

      System.out.println(story);
    }       
}
