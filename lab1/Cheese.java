package bsu.rfe.java.group8.lab1.Gulakov.varB6;

public class Cheese extends Food
{
  
    private Double calories = null;

    public Cheese()      
    {
        super("Сыр");                      
    }

    public Double calculateCalories()       
    {
        calories = 50.0;
        return calories;
    }

    public void consume()       
    {
        System.out.println(this + " съеден");
    }

}
