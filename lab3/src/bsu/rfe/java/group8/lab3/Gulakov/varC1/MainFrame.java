package bsu.rfe.java.group8.lab3.Gulakov.varC1;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 700; 
	private static final int HEIGHT = 500;
	
	// Массив коэффициентов многочлена 
	private Double[] coefficients; 
	
	
	private JFileChooser fileChooser = null; 
	
	// Элементы меню
	private JMenuItem saveToTextMenuItem; 
	private JMenuItem saveToGraphicsMenuItem; 
	private JMenuItem searchValueMenuItem;
	private JMenuItem searchRangeMenuAction;
	
	// Поля ввода для считывания значений переменных 
	private JTextField textFieldFrom; 
	private JTextField textFieldTo; 
	private JTextField textFieldStep;   
	
	private Box hBoxResult; 
	
	private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();  
	
	// Визуализатор ячеек таблицы 
	private GornerTableCellRenderer renderer = new GornerTableCellRenderer(); 
	
	// Модель данных с результатами вычислений 
	private GornerTableModel data;
	
	@SuppressWarnings({ "serial", "deprecation" })
	public MainFrame(Double[] coefficients) 
	{
		super("Табулирование многочлена на отрезке по схеме Горнера"); 
		
		// Запомнить во внутреннем поле переданные коэффициенты 
		this.coefficients = coefficients; 
		// Установить размеры окна 
		setSize(WIDTH, HEIGHT); 
		Toolkit kit = Toolkit.getDefaultToolkit();   
		// Отцентрировать окно приложения на экране 
		setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);
		
		//загрузка изображения и установка его в качестве иконки 
		Image img = kit.getImage("icon.gif");
		setIconImage(img);
		
		// Создать меню 
		JMenuBar menuBar = new JMenuBar(); 
		// Установить меню в качестве главного меню приложения  
		setJMenuBar(menuBar); 
		// Добавить в меню пункт меню "Файл" 
		JMenu fileMenu = new JMenu("Файл");
		// Добавить его в главное меню  
		menuBar.add(fileMenu);  
		// Создать пункт меню "Таблица" 
		JMenu tableMenu = new JMenu("Таблица");
		// Добавить его в главное меню 
		menuBar.add(tableMenu); 
		
		JMenu infMenu = new JMenu("Справка"); 
		menuBar.add(infMenu);
		
		// Создать новое "действие" по сохранению в текстовый файл  
		Action saveToTextAction = new AbstractAction("Сохранить в текстовый файл") 	{
			public void actionPerformed(ActionEvent event)
			{ 
				if (fileChooser == null) 
				{ 
					// Если диалоговое окно "Открыть файл" еще не создано,	
					// то создать его 
					fileChooser = new JFileChooser(); 
					// и инициализировать текущей директорией 
					fileChooser.setCurrentDirectory(new File(".")); 
					} 
				// Показать диалоговое окно 
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) 
					// Если результат его показа успешный, сохранить данные в  
					// текстовый файл 
					saveToTextFile(fileChooser.getSelectedFile()); 
				} 
			};
				
			// Добавить соответствующий пункт подменю в меню "Файл"
			saveToTextMenuItem = fileMenu.add(saveToTextAction);   
			// По умолчанию пункт меню является недоступным (данных еще нет) 
			saveToTextMenuItem.setEnabled(false); 
			
		
				
				
		// Создать новое "действие" по сохранению в текстовый файл 
		Action saveToGraphicsAction = new AbstractAction("Сохранить данные для построения графика")
		{ 
			public void actionPerformed(ActionEvent event)
			{ 
				if (fileChooser==null) 
				{ 
					
					fileChooser = new JFileChooser(); 			
					
					
					fileChooser.setCurrentDirectory(new File(".")); 
					}      
				
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION);  
				
				saveToGraphicsFile(fileChooser.getSelectedFile()); 
				} 
			};
			
			// Добавить соответствующий пункт подменю в меню "Файл"    
			saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);   
			 
			saveToGraphicsMenuItem.setEnabled(false);     
			
		
		Action searchValueAction = new AbstractAction("Найти значение многочлена")
		{ 
			public void actionPerformed(ActionEvent event) 
			{ 
				renderer.setNeedle1(null, null);
				// Запросить пользователя ввести искомую строку 
				String value = JOptionPane.showInputDialog(MainFrame.this,
						"Введите значение для поиска", "Поиск значения", JOptionPane.QUESTION_MESSAGE);
				 		
				renderer.setNeedle(value); 
				getContentPane().repaint(); 
				} 
			};
			
			 
			searchValueMenuItem = tableMenu.add(searchValueAction);  
			searchValueMenuItem.setEnabled(false); 
			
	
		Action searchRangeAction = new AbstractAction("Найти значение многочлена в диопазоне")
		{ 
			public void actionPerformed(ActionEvent event)
			{ 
				renderer.setNeedle(null);
				
				 
				String value1 = JOptionPane.showInputDialog(MainFrame.this,"Введите значение начала отрезка",
						"Поиск значения из диапазона", JOptionPane.QUESTION_MESSAGE);
				
				String value2 = JOptionPane.showInputDialog(MainFrame.this,"Введите значение конца отрезка",
						"Поиск значения из диапазона", JOptionPane.QUESTION_MESSAGE);
				; 
				renderer.setNeedle1(value1, value2); 
				getContentPane().repaint(); 
				} 
			};
			
			 
			searchRangeMenuAction = tableMenu.add(searchRangeAction); 
			
			searchRangeMenuAction.setEnabled(false); 
			
		
		Action information = new AbstractAction("О программе")
		{ 
			
			public void actionPerformed(ActionEvent event) 
			{ 
				JOptionPane.showMessageDialog(MainFrame.this,
						"Автор:\n Гулаков \n 8-я группа ");
				} 
			};
			
			infMenu.add(information);  
			
		JLabel labelForFrom = new JLabel("X изменяется на интервале от:"); 
		textFieldFrom = new JTextField("0.0", 10); 
		textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());       
		JLabel labelForTo = new JLabel("до:"); 
		textFieldTo = new JTextField("1.0", 10); 
		textFieldTo.setMaximumSize(textFieldTo.getPreferredSize()); 
		JLabel labelForStep = new JLabel("с шагом:"); 
		textFieldStep = new JTextField("0.1", 10); 
		textFieldStep.setMaximumSize(textFieldStep.getPreferredSize()); 
		
		Box hboxRange = Box.createHorizontalBox(); 
		// Задать для контейнера тип рамки "объемная" 
		hboxRange.setBorder(BorderFactory.createBevelBorder(1)); 
		hboxRange.add(Box.createHorizontalGlue()); 
		hboxRange.add(labelForFrom); 
		hboxRange.add(Box.createHorizontalStrut(10)); 
		hboxRange.add(textFieldFrom); 
		hboxRange.add(Box.createHorizontalStrut(20)); 
		hboxRange.add(labelForTo); 
		hboxRange.add(Box.createHorizontalStrut(10)); 
		hboxRange.add(textFieldTo); 
		hboxRange.add(Box.createHorizontalStrut(20)); 
		hboxRange.add(labelForStep); 
		hboxRange.add(Box.createHorizontalStrut(10)); 
		hboxRange.add(textFieldStep);    
		hboxRange.add(Box.createHorizontalGlue());   
		
		// Установить предпочтительный размер области равным удвоенному  
		// минимальному, чтобы при  компоновке область совсем не сдавили 
		hboxRange.setPreferredSize(new Dimension(
				new Double(hboxRange.getMaximumSize().getWidth()).intValue(),  
				new Double(hboxRange.getMinimumSize().getHeight()).intValue()*2)); 
		// Установить область в верхнюю (северную) часть компоновки 
		getContentPane().add(hboxRange, BorderLayout.NORTH);    
		
		// Создать кнопку "Вычислить" 
		JButton buttonCalc = new JButton("Вычислить"); 
		// Задать действие на нажатие "Вычислить" и привязать к кнопке 
		buttonCalc.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent ev) 
			{ 
				try { 
					// Считать значения границ отрезка, шага из полей ввода 
					//renderer.setNeedle("0");
					Double from = Double.parseDouble(textFieldFrom.getText()); 
					Double to = Double.parseDouble(textFieldTo.getText()); 
					Double step = Double.parseDouble(textFieldStep.getText()); 
					// На основе считанных данных создать новый экземпляр модели таблицы 
					data = new GornerTableModel(from, to, step, MainFrame.this.coefficients); 
					// Создать новый экземпляр таблицы 
					JTable table = new JTable(data); 
					// Установить в качестве визуализатора ячеек для класса
					//Double разработанный визуализатор 
					table.setDefaultRenderer(Double.class, renderer); 
					// Установить размер строки таблицы в 30 пикселов 
					table.setRowHeight(30); 
					// Удалить все вложенные элементы из контейнера hBoxResult 
					hBoxResult.removeAll(); 
					// Добавить в hBoxResult таблицу, "обернутую" в панель с полосами прокрутки 
					hBoxResult.add(new JScrollPane(table)); 
					// Обновить область содержания главного окна 
					getContentPane().validate(); 
					// Пометить ряд элементов меню как доступных 
					saveToTextMenuItem.setEnabled(true); 
					saveToGraphicsMenuItem.setEnabled(true); 
					searchValueMenuItem.setEnabled(true); 
					searchRangeMenuAction.setEnabled(true);
					} catch (NumberFormatException ex) { 
						// В случае ошибки преобразования чисел показать сообщение об ошибке 
						JOptionPane.showMessageDialog(MainFrame.this, 
								"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 
								JOptionPane.WARNING_MESSAGE); 
						} 
				} 
			}); 
		
			// Создать кнопку "Очистить поля"     
			JButton buttonReset = new JButton("Очистить поля"); 
			// Задать действие на нажатие "Очистить поля" и привязать к кнопке
			buttonReset.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent ev) 
				{ 
					// Установить в полях ввода значения по умолчанию 
					textFieldFrom.setText("0.0"); 
					textFieldTo.setText("1.0"); 
					textFieldStep.setText("0.1"); 
					// Удалить все вложенные элементы контейнера 
					hBoxResult.removeAll(); 
					// Добавить в контейнер пустую панель 
					hBoxResult.add(new JPanel()); 
					// Пометить элементы меню как недоступные 
					saveToTextMenuItem.setEnabled(false); 
					saveToGraphicsMenuItem.setEnabled(false); 
					searchValueMenuItem.setEnabled(false);
					searchRangeMenuAction.setEnabled(false); 
					renderer.setNeedle(null);
					renderer.setNeedle1(null, null);
					// Обновить область содержания главного окна 
					getContentPane().validate(); 
				} 
			}); 
			
			// Поместить созданные кнопки в контейнер 
			Box hboxButtons = Box.createHorizontalBox(); 
			hboxButtons.setBorder(BorderFactory.createBevelBorder(1));    
			hboxButtons.add(Box.createHorizontalGlue()); 
			hboxButtons.add(buttonCalc); 
			hboxButtons.add(Box.createHorizontalStrut(30)); 
			hboxButtons.add(buttonReset); 
			hboxButtons.add(Box.createHorizontalGlue()); 
			// Установить предпочтительный размер области равным удвоенному минимальному, чтобы при  
			// компоновке окна область совсем не сдавили     
			hboxButtons.setPreferredSize(new Dimension(new 
					Double(hboxButtons.getMaximumSize().getWidth()).intValue(), new 
					Double(hboxButtons.getMinimumSize().getHeight()).intValue()*2));    
			// Разместить контейнер с кнопками в нижней (южной) области граничной компоновки 
			getContentPane().add(hboxButtons, BorderLayout.SOUTH); 
			// Область для вывода результата пока что пустая   
			hBoxResult = Box.createHorizontalBox(); 
			hBoxResult.add(new JPanel()); 
			// Установить контейнер hBoxResult в главной (центральной) области граничной компоновки 
			getContentPane().add(hBoxResult, BorderLayout.CENTER);     
		} 

		protected void saveToGraphicsFile(File selectedFile) 
		{ 
			try { 
				// Создать байтовый поток вывода, направленный в указанный файл
				DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile)); 
			
				for (int i = 0; i<data.getRowCount(); i++) 
				{ 
					// Записать в поток вывода значение X в точке
					out.writeDouble((Double)data.getValueAt(i,0)); 
					// значение многочлена в точке 
					out.writeDouble((Double)data.getValueAt(i,1)); 
				} 
				// Закрыть поток вывода 
				out.close();       
			} catch (Exception e) { 
				// Исключительную ситуацию "ФайлНеНайден" в данном случае можно не обрабатывать, 
				// так как мы файл создаем, а не открываем для чтения     
			} 
		} 

		protected void saveToTextFile(File selectedFile) 
		{ 
			try { 
				// Создать новый символьный поток вывода, направленный в указанный файл
				PrintStream out = new PrintStream(selectedFile);  			
				// Записать в поток вывода заголовочные сведения 
				out.println("Результаты табулирования многочлена по схеме Горнера"); 
				out.print("Многочлен: "); 
				for (int i=0; i<coefficients.length; i++) 
				{ 
					out.print(coefficients[i] + "*X^" + 
							(coefficients.length-i-1)); 
					if (i!=coefficients.length-1) 
						out.print(" + "); 
				} 
				out.println(""); 
				out.println("Интервал от " + data.getFrom() + " до " + 
						data.getTo() + " с шагом " + data.getStep()); 
				out.println("===================================================="); 
				// Записать в поток вывода значения в точках 
				for (int i = 0; i<data.getRowCount(); i++)
				{ 
					out.println("Значение в точке " + formatter.format(data.getValueAt(i,0)) 
							+ " равно " + formatter.format(data.getValueAt(i,1))); 
				} 
				// Закрыть поток 
				out.close();     
			} catch (FileNotFoundException e) 
			{ 
				// Исключительную ситуацию "ФайлНеНайден" можно не  
				// обрабатывать, так как мы файл создаем, а не открываем  
			} 
		} 
		
		
		
		
		public static void main(String[] args) 
		{ 
			// Если не задано ни одного аргумента командной строки -  
			// Продолжать вычисления невозможно, коэффиценты неизвестны 
			if (args.length==0) 
			{ 
				System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффициента!"); 
				System.exit(-1); 
			} 
			// Зарезервировать места в массиве коэффициентов столько, сколько аргументов командной строки 
			Double[] coefficients = new Double[args.length]; 
			int i = 0;     
			try { 
				// Перебрать аргументы, пытаясь преобразовать их в Double 
				for (String arg: args) { 
					coefficients[i++] = Double.parseDouble(arg); 
				} 
			} 
			catch (NumberFormatException ex) { 
				
				System.out.println("Ошибка преобразования строки '" + 
						args[i] + "' в число типа Double"); 
				System.exit(-2); 
			} 
			// Создать экземпляр главного окна, передав ему коэффициенты 
			MainFrame frame = new MainFrame(coefficients);     
			// Задать действие, выполняемое при закрытии окна 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			frame.setVisible(true); 
		} 
	}