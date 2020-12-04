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

//Текстовые поля для считывания значений переменных, 
private JTextField textFieldX; 
private JTextField textFieldY; 
private JTextField textFieldZ; 



//Текстовое поле для отображения результата,  
private JTextField memoryTextField;
private JTextField resultFieldText;

//Группа радио-кнопок для обеспечения уникальности выделения в группе 
private ButtonGroup radioButtons = new ButtonGroup(); 
private ButtonGroup radioMemoryButtons = new ButtonGroup();

//Контейнер для отображения радио-кнопок 
private Box hboxFormulaType = Box.createHorizontalBox(); 
private Box hBoxMemoryType = Box.createHorizontalBox();


//Переменная, указывающая, какая из формул является  активной  в  данный  момент
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
	
	//Вспомогательный метод для добавления кнопок на панель
	//buttonName – текст рядом с кнопкой, formulaId – идентификатор формулы 
	private void addRadioButton(String buttonName, final int formulaId) {

		//Создать экземпляр радио-кнопки с заданным текстом 
		JRadioButton button = new JRadioButton(buttonName); 
		//Добавить радио-кнопку в группу 
		radioButtons.add(button); 
		//Добавить радио-кнопку в 
		//Для этого ссылка на контейнер сделана полем данных класса 
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



	//Конструктор класса 
	public MainFrame() { 
	super("Вычисление формулы"); 
	setSize(WIDTH, HEIGHT); 
	Toolkit kit = Toolkit.getDefaultToolkit(); 
	
	// Отцентрировать окно приложения на экране 
	setLocation((kit.getScreenSize().width - WIDTH)/2, 
	(kit.getScreenSize().height - HEIGHT)/2); 


	//Добавить «клей» C1-H1 с левой стороны 
	hboxFormulaType.add(Box.createHorizontalGlue()); 
	//Создать радио-кнопку для формулы 1 
	addRadioButton("Формула 1", 1); 
	//Создать радио-кнопку для формулы 2 
	addRadioButton("Формула 2", 2); 
	//Установить выделенной 1-ую кнопку из группы 
	radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true); 
	//Добавить «клей» C1-H2 с правой стороны 
	hboxFormulaType.add(Box.createHorizontalGlue()); 
	 
	// Создать область с полями ввода для X и Y 
	//Создать подпись “X:” для переменной X 
	JLabel labelForX = new JLabel("X:"); 
	//Создать текстовое поле для ввода значения переменной X,   
	//(по умолчанию 0) 
	textFieldX = new JTextField("0.0", 10); 
	//Установить макс размер = желаемому для предотвращения масштабирования 
	textFieldX.setMaximumSize(textFieldX.getPreferredSize()); 
	//Создать подпись “Y:” для переменной Y 
	JLabel labelForY = new JLabel("Y:"); 
	//Создать текстовое поле для ввода значения переменной Y, 
	//(по умолчанию 0) 
	textFieldY = new JTextField("0.0", 10); 
	//Установить макс размер = желаемому для предотвращения масштабирования 
	textFieldY.setMaximumSize(textFieldY.getPreferredSize()); 
	//Создать подпись “Z:” для переменной Z 
	JLabel labelForZ = new JLabel("Z:"); 
	//Создать текстовое поле для ввода значения переменной Z, 
	//(по умолчанию 0) 
	textFieldZ = new JTextField("0.0", 10); 
	//Установить макс размер = желаемому для предотвращения масштабирования 
	textFieldZ.setMaximumSize(textFieldZ.getPreferredSize()); 

	//Создать контейнер «коробка с горизонтальной укладкой» 
	Box hboxVariables = Box.createHorizontalBox(); 
	//Задать рамку для коробки с помощью класса BorderFactory 
	//hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));   
	//Добавить в контейнер ряд объектов: 
	//Добавить «клей» C2-H1 – для максимального удаления от левого края 
	hboxVariables.add(Box.createHorizontalGlue()); 
	//Добавить подпись для переменной Х 
	hboxVariables.add(labelForX); 
	//Добавить «распорку» C2-H2 шириной 10 пикселов для отступа между  
	//надписью и текстовым полем для ввода значения X 
	hboxVariables.add(Box.createHorizontalStrut(10)); 
	//Добавить само текстовое поле для ввода Х  
	hboxVariables.add(textFieldX); 
	//Добавить «распорку» C2-H3 шириной 50 пикселов для отступа между  
	//текстовым полем для ввода X и подписью для Y 
	hboxVariables.add(Box.createHorizontalStrut(50)); 
	//Добавить подпись для переменной Y 
	hboxVariables.add(labelForY); 
	//Добавить «распорку» C2-H4 шириной 10 пикселов для отступа между  
	//надписью и текстовым полем для ввода значения Y 
	hboxVariables.add(Box.createHorizontalStrut(10)); 
	//Добавить само текстовое поле для ввода Y 
	hboxVariables.add(textFieldY);
	//Добавить «распорку» C2-H5 шириной 50 пикселов для отступа между  
	//текстовым полем для ввода Y и подписью для Z
	hboxVariables.add(Box.createHorizontalStrut(50)); 
	//Добавить подпись для переменной Z
	hboxVariables.add(labelForZ);
	//Добавить «распорку» C2-H6 шириной 10 пикселов для отступа между  
	//надписью и текстовым полем для ввода значения Z 
	hboxVariables.add(Box.createHorizontalStrut(10)); 
	//Добавить текстовое поле для ввода Z 
	hboxVariables.add(textFieldZ); 
	//Добавить «клей» C2-H5 для максимального удаления от правого края 
	hboxVariables.add(Box.createHorizontalGlue());



	//Создать подпись для поля с результатом 
	JLabel labelForResult = new JLabel("Результат:"); 
	// Создать текстовое поле для вывода результата, начальное значение - 0 
	resultFieldText = new JTextField("0", 15); 
	//Установить макс размер = желаемому для предотвращения масштабирования 
	resultFieldText.setMaximumSize( resultFieldText.getPreferredSize()); 


	// Создать контейнер «коробка с горизонтальной укладкой» 
	Box hboxResult = Box.createHorizontalBox(); 
	// Добавить в контейнер ряд объектов 
	// Добавить «клей» C3-H1 для отступа от левого края 
	hboxResult.add(Box.createHorizontalGlue()); 
	//Добавить подпись для результата 
	hboxResult.add(labelForResult); 
	//Добавить «распорку» C3-H2 в 10 пикселов между подписью и полем результата 
	hboxResult.add(Box.createHorizontalStrut(10)); 
	//Добавить текстовое поле для вывода результата 
	hboxResult.add(resultFieldText); 
	//Добавить «клей» C3-H3 справа 
	hboxResult.add(Box.createHorizontalGlue()); 
	//Задать рамку для контейнера 
	hboxResult.setBorder(BorderFactory.createLineBorder(Color.RED)); 


	// Создать область для кнопок 
	// Создать кнопку «Вычислить» 
	JButton buttonCalc = new JButton("Вычислить"); 
	// Определить и зарегистрировать обработчик нажатия на кнопку 
	buttonCalc.addActionListener(new ActionListener() { 
		 public void actionPerformed(ActionEvent ev) { 
			 //Преобразование введенных строк в числа с плавающей точкой может  
			 //спровоцировать исключительную ситуацию при неправильном формате чисел, 
			 //поэтому необходим блок try-catch 
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
		// по умолчанию на первую память
		radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true); 
		//Добавить «клей» C1-H2 с правой стороны 
		hBoxMemoryType.add(Box.createHorizontalGlue()); 
		//Задать рамку для коробки с помощью класса BorderFactory 
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
		 
	//Создать коробку с горизонтальной укладкой 
	Box hboxButtons = Box.createHorizontalBox(); 
	//Добавить «клей» C4-H1 с левой стороны 
	hboxButtons.add(Box.createHorizontalGlue()); 
	//Добавить кнопку «Вычислить» в компоновку 
	hboxButtons.add(buttonCalc); 
	//Добавить распорку в 30 пикселов C4-H2 между кнопками 
	hboxButtons.add(Box.createHorizontalStrut(30)); 
	//Добавить кнопку «Очистить поля» в компоновку 
	hboxButtons.add(buttonReset); 
	//Добавить «клей» C4-H3 с правой стороны 
	hboxButtons.add(Box.createHorizontalGlue()); 
	//Задать рамку для контейнера 
	hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN)); 


	// Связать области воедино в компоновке BoxLayout 
	//Создать контейнер «коробка с вертикальной укладкой» 
	Box contentBox = Box.createVerticalBox(); 
	//Добавить «клей» V1 сверху 
	contentBox.add(Box.createVerticalGlue()); 
	// Добавить контейнер с выбором формулы 
	contentBox.add(hboxFormulaType); 
	//Добавить контейнер с переменными 
	contentBox.add(hboxVariables); 
	//Добавить контейнер с результатом вычислений 
	contentBox.add(hboxResult); 
	//Добавить контейнер с кнопками 
	contentBox.add(hboxButtons); 
	// Добавить контейнер с выбором памяти
	contentBox.add(hBoxMemoryType); 
	//Добавить резултат 
	contentBox.add(hBoxControlButtons); 
	/// поле
	contentBox.add(hBoxMemoryField); 
	//Добавить «клей» V2 снизу 
	contentBox.add(Box.createVerticalGlue());    
	//Установить «вертикальную коробку» в область содержания главного окна
	getContentPane().add(contentBox, BorderLayout.CENTER); 

	} 
 
	public static void main(String[] args) { 
		MainFrame frame = new MainFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true); 
		} 
}
