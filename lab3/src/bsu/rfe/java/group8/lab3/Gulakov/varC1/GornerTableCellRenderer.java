package bsu.rfe.java.group8.lab3.Gulakov.varC1;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer
{
	private JPanel panel = new JPanel(); 
	private JLabel label = new JLabel(); 
	// Ищем ячейки, строковое представление которых совпадает с needle  
	private String needle = null; 
	private String needleX = null;
	private String needleY = null; 
	

	private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();     

	public GornerTableCellRenderer() 
	{ 
		// Показывать только 5 знаков после запятой 
		formatter.setMaximumFractionDigits(5); 
		// Не использовать группировку
		formatter.setGroupingUsed(false);
		// Установить в качестве разделителя дробной части точку, а не запятую 
		// По умолчанию, в региональных настройках Россия/Беларусь дробная часть  
		// отделяется запятой 
		DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols(); 
		dottedDouble.setDecimalSeparator('.'); 
		formatter.setDecimalFormatSymbols(dottedDouble); 
		// Разместить надпись внутри панели    
		panel.add(label);
		// Установить выравнивание надписи по левому краю панели 
		panel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		 
	} 
	
	
	  public Component getTableCellRendererComponent(JTable table, Object 
			  value, boolean isSelected, boolean hasFocus, int row, int col) { 
		  // Преобразовать число в строку с помощью форматировщика 
		  String formattedDouble = formatter.format(value);
		  
		  // Установить текст надписи равным строковому представлению числа 
		  label.setText(formattedDouble); 
		  //шахматы
		  if ((col+row)%2 == 0){
	        	panel.setBackground(Color.BLACK);
	        	label.setForeground(Color.WHITE);
		  }else {
			  // Иначе - в обычный белый 
			 panel.setBackground(Color.WHITE);
			 label.setForeground(Color.BLACK);
		  }
		  
		if (col >= 1 && needle!=null && needle.equals(formattedDouble)) {
			  // Номер столбца = 1 (т.е. второй столбец) 
			  // + иголка не null (т.е. мы что-то ищем) 
			  // + значение иголки совпадает со значением ячейки таблицы -			  
			  // окрасить задний фон панели в красный цвет 
			  panel.setBackground(Color.green); 
			  label.setForeground(Color.BLACK);
			  } 
	 
		
		if(needleX!=null && needleY!=null){
		
		Double coefficients1 =null;
		Double coefficients2 = null;
		Double coefficients3 = null;
		try{
			coefficients1 = Double.parseDouble(needleX);
			coefficients2 = Double.parseDouble(needleY);
			coefficients3 = Double.parseDouble(formattedDouble);
		}catch(NullPointerException e){
			
		}
		
		if(col >= 1 && (coefficients3 <= coefficients2) && (coefficients3 >= coefficients1))
		{
			  panel.setBackground(Color.green); 
			  label.setForeground(Color.BLACK);
		}
		}
		  return panel; 
		  
		  } 

	public String getNeedleX() {
		return needleX;
	}


	public void setNeedleX(String needleX) {
		this.needleX = needleX;
	}


	public String getNeedleY() {
		return needleY;
	}


	public void setNeedleY(String needleY) {
		this.needleY = needleY;
	}


	public void setNeedle(String needle) 
	{ 
		this.needle = needle; 
	}   
	
	public void setNeedle1(String needleX, String needleY) 
	{ 
		this.needleX = needleX; 
		this.needleY = needleY; 
	} 
}