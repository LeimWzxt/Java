public class DNA{
  public void isDNA(String dna){
    int c = 0; //si c es 3, es un dna
    String a = dna.substring(0,3);
    if (a.equals("ATG")){
      c++;
    }

    int l = dna.length();
    String b = dna.substring(l-3);
    if ( b.equals("TGA")){
      c++;
    }

    if(l%3 == 0){
      c++;
    }

    //validar si es una cadena correcta
    System.out.print(dna);
    
    if (c == 3) {
      System.out.println(" is a protein");
    } else{
      System.out.println(" is not a protein");
    }
  }

  public static void main(String[] args){
    DNA dna = new DNA();
    String dna1 = "ATGCGATACGCTTGA";
    String dna2 = "ATGCGATACGTGA";
    String dna3 = "ATTAATATGTACTGA";

    dna.isDNA(dna1);
    dna.isDNA(dna2);
    dna.isDNA(dna3);
  }
}
