import java.util.ArrayList;

class PrimeDirective {
  public boolean isPrime(int number){
    int p=0;
    for(int i=1; i<=number; i++){
      if(number%i==0){
        p++;
      }
    }
    if(p==2){
      return true;
    } else {
      return false;
    }
  }
  
  public static void main(String[] args) {

    PrimeDirective pd = new PrimeDirective();
    int[] numbers = {6, 29, 28, 33, 11, 100, 101, 43, 89};
    ArrayList<Integer> onlyPrimes = new ArrayList<Integer> ();
    boolean x;

    for(int i=0; i<numbers.length; i++){
      x=pd.isPrime(numbers[i]);
      System.out.println(numbers[i]+" is prime?: "+x);

      if(x){
        onlyPrimes.add(numbers[i]);
      }
    }
    
    System.out.println(onlyPrimes);

  }  
}
