public class Droid{
  String name;
  int batteryLevel;

  public Droid(String droidName){
    name = droidName;
    batteryLevel = 100;
  }
  public String toString(){
    return "Hello, I'm the droid: "+ name;
  }
  public void performTask(String task){
    System.out.println(name+" is performing task: "+task);
  }
  public void stateBattery(int batt){
    batteryLevel -= batt;
    System.out.println("Actual battery level: "+batteryLevel);
  }

  public static void main(String[] args){
    Droid codey = new Droid("Codey");
    System.out.println(codey);
    codey.performTask("eating");
    codey.stateBattery(20);
    codey.stateBattery(10);
  }
}

