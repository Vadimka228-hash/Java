package bsu.rfe.java.group8.lab1.Gulakov.varB6;

public abstract class Food implements Consumable, Nutritious
{
    
    String name = null;
    String par1 = null;
    String par2 = null;

    public Food(String name)   
    {
        this.name = name;
    }
    public Food(String par1,String par2)
    { 
        this.par1 = par1;
        this.par2 = par2;
    }
    public boolean equals(Object arg0)         
    {
        if (!(arg0 instanceof Food)) return false;
        if (name == null || ((Food) arg0).name == null) return false;
        return name.equals(((Food) arg0).name);
    }

    public String toString()        
    {
        return name;
    }

    public String getName()        
    {
        return name;
    }

    public void setName(String name)        
    {
        this.name = name;
    }
}
