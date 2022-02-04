//This is a basic calculator program
public class Calculator{

  //constructor
  public Calculator(){

  }
  public int add(int a, int b){
    int r = a+b;
    System.out.println("Add: "+ r);
    return a + b;
  }
  public int subtract(int a, int b){
    int r = a - b;
    System.out.println("Subtract: "+ r);
    return a - b;
  }
  public int multiply (int a, int b){
    int r = a * b;
    System.out.println("Multiply: "+r);
    return a * b;
  }
  public int divide(int a, int b){
    int r = a / b;
    System.out.println("Divide: "+r);
    return a / b;
  }
  public int modulo(int a, int b){
    int r = a % b;
    System.out.println("Modulo: "+r);
    return a % b;
  }
  public static void main(String[] args){
    Calculator myCalculator = new Calculator();
    myCalculator.add(5,7);
    myCalculator.subtract(45,11);
    myCalculator.multiply(4,6);
    myCalculator.divide(2,4);
    myCalculator.modulo(4,3);
  }
}
