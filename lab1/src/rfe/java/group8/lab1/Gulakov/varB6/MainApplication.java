package bsu.rfe.java.group8.lab1.Gulakov.varB6;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MainApplication
{
    public static void main(String[] args) throws Exception
    {
        Food[] breakfast = new Food[20];
        int i = 0;
        boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;

        for (String arg : args)
        {
            String[] parts = arg.split(("/")); 
            try {
            	Class<?> myClass = Class.forName("bsu.rfe.java.group8.lab1.Gulakov.varB6." + parts[0]);
            	if(parts.length == 1 ){
					Constructor<?> constructor = myClass.getConstructor();
					breakfast[i] = (Food)constructor.newInstance();
					i++;
				}else
					if(parts.length == 2){
						Constructor<?> constructor = myClass.getConstructor(String.class);
						breakfast[i] = (Food)constructor.newInstance(parts[1]);
						i++;
				}else
					if( parts.length == 3){			
						Constructor<?> constructor = myClass.getConstructor(String.class, String.class);
						breakfast[i] = (Food)constructor.newInstance(parts[1], parts[2]);
						i++;
					}
					}catch (ClassNotFoundException e) { 
						if (parts[0].equals("-sort")){
							flag1=true;
						}else
							if (parts[0].equals("-calories")) {
								flag2=true;
						}else
							if (parts[0].equals("-Apple")) {
								flag3=true;
						}else
							if (parts[0].equals("-Cheese")) {
								flag4=true;
						}else
							if (parts[0].equals("-Coffee")) {
								flag5=true;}
							else
								System.out.println("Класс" + parts[0] + " не найден.");
					}catch (NoSuchMethodException e){
						System.out.println("Метод класса " + parts[0] + " не найден.");
						}
             catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace(System.out);
            }

        }

        System.out.println("Завтрак: "); 
        for (Food item : breakfast) {
            if (item != null) {
                item.consume();
                System.out.println(" " + item.calculateCalories());
            } else {
                break;
            }
        }
        if(flag1){
			Arrays.sort(breakfast,new Comparator<Object>() {
				public int compare(Object f1, Object f2) {
				if (f1 == null) return 1;
				if (f2 == null) return -1;
				return ((Food)f1).calculateCalories().compareTo(((Food)f2).calculateCalories());
				}
				});
			
			System.out.println("Отсортированный вариант:");
			for (Food item: breakfast)
				if (item!=null){
					item.consume();
			System.out.println(" " + item.calculateCalories());}
				else
					break;
	}
	
		if(flag3){
			int apple =0;
			Food Apple = new Apple("Большое");
			Food Apple1 = new Apple("Среднее");
			Food Apple2 = new Apple("Маленькое");
			for (Food item: breakfast){ 
				if (item!=null) {
					if(item.equals(Apple) || item.equals(Apple1) || item.equals(Apple2))
						apple++;  }
				}
			System.out.println("Яблоко размером: " + apple + " съедено.");
			}
		
		if(flag4){
			int cheese = 0;
			Food Cheese = new Cheese();
			for (Food item: breakfast){ 
				if (item!=null){
					if(item.equals(Cheese))
						cheese++;}
				}
			System.out.println("Сыр: " + cheese + " съеден.");
			}
		
		if(flag5){
			int Coffee = 0;
			Food Coffee1 = new Coffee("Горький");
			Food Coffee2 = new Coffee("Насыщенный");
			Food Coffee3 = new Coffee("Восточный");
			for (Food item: breakfast){ 
				if (item!=null) {
					if(item.equals(Coffee1) || item.equals(Coffee2) || item.equals(Coffee3))
						Coffee++;  }
				}
			System.out.println("Кофе: " + Coffee + " выпит.");
			}
		
		
		if(flag2){
			double CaloriesCounter=0;
			for (Food item: breakfast)
			{
				if (item!=null)
					CaloriesCounter += item.calculateCalories();
				else
					break;
				}
			System.out.println("Общая колорийность завтрака =  " + CaloriesCounter + " кал.");
			}

            
      
        System.out.println("Всего хорошего!");
    }
    }
