package bsu.rfe.java.group8.lab1.Gulakov.varB6;

public class Coffee extends Food {

	private String aroma ;
	private Double calories = null;

	public Coffee(String aroma) {
		super("Кофе");
		this.setAroma(aroma);
	}
	

	public String getAroma() {
		return aroma;
	}

	public void setAroma(String aroma) {
		this.aroma = aroma;
	}
	public String toString()
    {
		return super.toString() + " '" + aroma.toUpperCase() + "'";
    }
	
	public void consume()
    {                    
        System.out.println( this + " выпит");
    }
	
	
	public Double calculateCalories() {
		if(this.aroma.equals("Горький"))
		{
			calories = 40.0;
			return calories;
			}
		else
			if(this.aroma.equals("Восточный"))
			{
			calories = 40.0;
			return calories;
			}
			else
				return 0.0;	
	}
}


