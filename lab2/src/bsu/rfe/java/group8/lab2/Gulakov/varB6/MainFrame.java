package bsu.rfe.java.group8.lab2.Gulakov.varB6;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial") 


public class MainFrame extends JFrame { 


private static final int WIDTH = 600; 
private static final int HEIGHT = 420; 


private JTextField textFieldX; 
private JTextField textFieldY; 
private JTextField textFieldZ; 



  
private JTextField memoryTextField;
private JTextField resultFieldText;


private ButtonGroup radioButtons = new ButtonGroup(); 
private ButtonGroup radioMemoryButtons = new ButtonGroup();


private Box hboxFormulaType = Box.createHorizontalBox(); 
private Box hBoxMemoryType = Box.createHorizontalBox();



private int formulaId = 1;
private int memoryId = 1;


@SuppressWarnings("deprecation")
private Double mem1 = new Double(0);
@SuppressWarnings("deprecation")
private Double mem2 = new Double(0);
@SuppressWarnings("deprecation")
private Double mem3 = new Double(0);
	
	//Формула 1
	public Double calculate1(Double x, Double y, Double z) {
		if (x == 0)	{
			JOptionPane.showMessageDialog(MainFrame.this, 
					"x не может равняться нулю", "" +
							"Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		
		if (y == 0)	{
			JOptionPane.showMessageDialog(MainFrame.this, 
					"y не может равняться нулю", "" +
							"Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		
		if (z == 0)	{
			JOptionPane.showMessageDialog(MainFrame.this, 
					"z не может равняться нулю", "" +
							"Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		
		return Math.pow((Math.log(Math.pow(z + 1, 2)) + Math.cos(Math.PI * Math.pow(y, 3))) 
				/ (Math.pow(Math.cos(Math.pow(Math.E, 3)) + Math.pow(1/x, 0.5) 
				+ Math.pow(Math.E, x*x), Math.sin(x))), 0.25);
	}
	//Формула 2
	public Double calculate2(Double x, Double y, Double z) {
		if (x == 0)	{
			JOptionPane.showMessageDialog(MainFrame.this, 
					"X не может равняться нулю", "" +
							"Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		
		if (y == 0)	{
			JOptionPane.showMessageDialog(MainFrame.this, 
					"Y не может равняться нулю", "" +
							"Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		if (z == 0)	{
			JOptionPane.showMessageDialog(MainFrame.this, 
					"Z не может равняться нулю", "" +
							"Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		
		return (Math.tan(x*x) + Math.sqrt(y))
				/ (z * Math.log(x + y));
	}
	
	
	private void addRadioButton(String buttonName, final int formulaId) {

		
		JRadioButton button = new JRadioButton(buttonName); 
	
		radioButtons.add(button); 
		
		hboxFormulaType.add(button); 
		} 

	private void addMemoryRadioButton (String buttonName, final int memoryId)	{
		JRadioButton button = new JRadioButton(buttonName);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)	{
				MainFrame.this.memoryId = memoryId;
				if (memoryId == 1)	memoryTextField.setText(mem1.toString());
				if (memoryId == 2)	memoryTextField.setText(mem2.toString());
				if (memoryId == 3)	memoryTextField.setText(mem3.toString());
			}
		});
		
		radioMemoryButtons.add(button);
		hBoxMemoryType.add(button);
	}



	
	public MainFrame() { 
	super("Вычисление формулы"); 
	setSize(WIDTH, HEIGHT); 
	Toolkit kit = Toolkit.getDefaultToolkit(); 
	
	
	setLocation((kit.getScreenSize().width - WIDTH)/2, 
	(kit.getScreenSize().height - HEIGHT)/2); 


	
	hboxFormulaType.add(Box.createHorizontalGlue()); 
	
	addRadioButton("Формула 1", 1); 
	
	addRadioButton("Формула 2", 2); 
	 
	radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true); 
	
	hboxFormulaType.add(Box.createHorizontalGlue()); 
	 
	
	JLabel labelForX = new JLabel("X:"); 
	
	textFieldX = new JTextField("0.0", 10); 
	
	textFieldX.setMaximumSize(textFieldX.getPreferredSize()); 
	
	JLabel labelForY = new JLabel("Y:"); 
	
	textFieldY = new JTextField("0.0", 10); 
	
	textFieldY.setMaximumSize(textFieldY.getPreferredSize()); 
	
	JLabel labelForZ = new JLabel("Z:"); 
	
	textFieldZ = new JTextField("0.0", 10); 
	
	textFieldZ.setMaximumSize(textFieldZ.getPreferredSize()); 

	 
	Box hboxVariables = Box.createHorizontalBox(); 
	
	hboxVariables.add(Box.createHorizontalGlue()); 
	
	hboxVariables.add(labelForX); 
	
	hboxVariables.add(Box.createHorizontalStrut(10)); 
	
	hboxVariables.add(textFieldX); 
	
	hboxVariables.add(Box.createHorizontalStrut(50)); 
	 
	hboxVariables.add(labelForY); 
	
	hboxVariables.add(Box.createHorizontalStrut(10)); 
	
	hboxVariables.add(textFieldY);
	
	hboxVariables.add(Box.createHorizontalStrut(50)); 
	
	hboxVariables.add(labelForZ);
	
	hboxVariables.add(Box.createHorizontalStrut(10)); 
	
	hboxVariables.add(textFieldZ); 
	 
	hboxVariables.add(Box.createHorizontalGlue());



	
	JLabel labelForResult = new JLabel("Результат:"); 
	
	resultFieldText = new JTextField("0", 15); 
	
	resultFieldText.setMaximumSize( resultFieldText.getPreferredSize()); 


	
	Box hboxResult = Box.createHorizontalBox(); 
	
	hboxResult.add(Box.createHorizontalGlue()); 
	
	hboxResult.add(labelForResult); 
	 
	hboxResult.add(Box.createHorizontalStrut(10)); 
	
	hboxResult.add(resultFieldText); 
	
	hboxResult.add(Box.createHorizontalGlue()); 
	
	hboxResult.setBorder(BorderFactory.createLineBorder(Color.RED)); 


	
	JButton buttonCalc = new JButton("Вычислить"); 
	 
	buttonCalc.addActionListener(new ActionListener() { 
		 public void actionPerformed(ActionEvent ev) { 
			  
			 try { 
				 
				 Double x = Double.parseDouble(textFieldX.getText()); 
				 Double y = Double.parseDouble(textFieldY.getText()); 
				 Double z = Double.parseDouble(textFieldZ.getText()); 
				 Double result; 

				 if (formulaId == 1) 
					 result = calculate1(x, y, z);
				 else 
					 result = calculate2(x, y, z); 
				 
				 resultFieldText.setText(result.toString()); 
				 } catch (NumberFormatException ex) { 
					 JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в" +
					 		"формате записи числа с плавающей точкой", "Ошибочный формат числа", 
					 		JOptionPane.WARNING_MESSAGE); 
					 } 
			 } 
		 }); 


		hBoxMemoryType.add(Box.createHorizontalGlue());
		addMemoryRadioButton("Память 1", 1);
		addMemoryRadioButton("Память 2", 2);
		addMemoryRadioButton("Память 3", 3);
		
		radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true); 
		 
		hBoxMemoryType.add(Box.createHorizontalGlue()); 
		 
		hBoxMemoryType.setBorder(BorderFactory.createLineBorder(Color.YELLOW)); 

	
	JButton buttonReset = new JButton("Очистить поля");  
	buttonReset.addActionListener(new ActionListener() { 
		 public void actionPerformed(ActionEvent ev) { 
			 textFieldX.setText("0.0"); 
			 textFieldY.setText("0.0"); 
			 textFieldZ.setText("0.0"); 
			 resultFieldText.setText("0.0"); 
			 JOptionPane.showMessageDialog(MainFrame.this, "Поля очищены!","Очистка", JOptionPane.PLAIN_MESSAGE); 
			 } 
		 }); 

		JButton buttonMC = new JButton("MC");
		buttonMC.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent event) {
				
				if (memoryId == 1)	mem1 = (double) 0;
				if (memoryId == 2)	mem2 = (double) 0;
				if (memoryId == 3)	mem3 = (double) 0;
				memoryTextField.setText("0.0");
			}
		});
		
		memoryTextField = new JTextField("0.0", 15);
		memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());
		
		Box hBoxMemoryField = Box.createHorizontalBox();
		hBoxMemoryField.add(Box.createHorizontalGlue());
		hBoxMemoryField.add(memoryTextField);
		hBoxMemoryField.add(Box.createHorizontalGlue());
		
		
		JButton buttonMp = new JButton("M+");
		buttonMp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
				Double result = Double.parseDouble(resultFieldText.getText());
				
				if (memoryId == 1) 	{mem1 += result;memoryTextField.setText(mem1.toString());}
				if (memoryId == 2)	{mem2 += result;memoryTextField.setText(mem2.toString());}
				if (memoryId == 3)	{mem3 += result;memoryTextField.setText(mem3.toString());}
			
				}catch (NumberFormatException ex) 
					{ JOptionPane.showMessageDialog(MainFrame.this, 
						"Ошибка в формате записи числа с плавающей точкой", "" +
								"Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		
		Box hBoxControlButtons = Box.createHorizontalBox();
		hBoxControlButtons.add(Box.createHorizontalGlue());
		hBoxControlButtons.add(buttonMC);
		hBoxControlButtons.add(Box.createHorizontalStrut(30));
		hBoxControlButtons.add(buttonMp);
		hBoxControlButtons.add(Box.createHorizontalGlue());
		 
	
	Box hboxButtons = Box.createHorizontalBox(); 
	
	hboxButtons.add(Box.createHorizontalGlue()); 
	
	hboxButtons.add(buttonCalc); 
	 
	hboxButtons.add(Box.createHorizontalStrut(30)); 
	 
	hboxButtons.add(buttonReset); 
	
	hboxButtons.add(Box.createHorizontalGlue()); 
	 
	hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN)); 


	
	Box contentBox = Box.createVerticalBox(); 
	 
	contentBox.add(Box.createVerticalGlue()); 
	
	contentBox.add(hboxFormulaType); 
	 
	contentBox.add(hboxVariables); 
	 
	contentBox.add(hboxResult); 
	
	contentBox.add(hboxButtons); 
	
	contentBox.add(hBoxMemoryType); 
	
	contentBox.add(hBoxControlButtons); 
	
	contentBox.add(hBoxMemoryField); 
	
	contentBox.add(Box.createVerticalGlue());    
	
	getContentPane().add(contentBox, BorderLayout.CENTER); 

	} 
 
	public static void main(String[] args) { 
		MainFrame frame = new MainFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true); 
		} 
}