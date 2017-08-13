package SerialNumbers;

public class SerialNumberGenerator {
    public static volatile int serialNumber = 0;
    public static int nextSerialNumber(){
        return serialNumber++;
    }

}
