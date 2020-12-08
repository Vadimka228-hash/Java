package bsu.rfe.java.group8.lab1.Gulakov.varB6;

public class Apple extends Food {
	private String size;             
    private Double calories = null;

    public Apple(String size)     
    {
        super("Яблоко");               
        this.size = size;
        
    }

    public void consume()           
    {
        System.out.println(this + " съедено");
    }

    public Double calculateCalories()       
    {
        if(size.equals("Маленькое"))
        {
            calories = 15.0;
        }
        else if(size.equals("Среднее"))
        {
            calories = 20.0;
        }
        else if(size.equals("Большое"))
        {
            calories = 25.0;
        }
        return calories;
    }

    public String getSize()   
    {
        return size;
    }

    public void setSize(String size)  
    {
        this.size = size;
    }

    public boolean equals(Object arg0)  
    {
        if (super.equals(arg0))
        {
            if (!(arg0 instanceof Apple)) return false;
            return size.equals(((Apple)arg0).size);
        } else
            return false;
    }

    public String toString()     
    {
        return super.toString() + " размера '" + size.toUpperCase() + "'";
    }
}