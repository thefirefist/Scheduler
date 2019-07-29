import javax.swing.*;
import javax.swing.border.Border;

import java.util.Timer; 
import java.util.TimerTask;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;

public class JFrame1 implements ItemListener,ActionListener{
	
	int month,year;
	private static final int CENTRE = 0;
	private Formatter fmt1;
	private Formatter fmt2;
	private Formatter fmt3;
	JFrame f,f2,schedule_frame;
	JPanel jp;
	JPanel jp2;
	JPanel jp3;
	JLabel sun,mond,tue,wed,thu,fri,sat,dialog_label;
	JButton j6[],j5[],save,write_notes,set_schedule;
	Font font1;
	JDialog d1;
	JComboBox m1,y1;
	JFrame1()
	{
		f = new JFrame("Scheduler");
		jp = new JPanel();
		
		jp.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
	
		//GridBagLayout layout = new GridBagLayout();
		f.setLayout(null);
		
		
	    fmt1 = new Formatter();
		fmt2 = new Formatter();
		fmt3 = new Formatter();
		Clock clock = new Clock();
		
	   
	    

 
	    
	
		
		
	     
	    
		Calendar c1 = Calendar.getInstance();
	    
		
		
		
		
		fmt1.format("%tB %tm", c1, c1);
		fmt2.format("%tA", c1);
		fmt3.format("%tY", c1);
		
		JLabel j1 = new JLabel(fmt1.toString()+", "+fmt3.toString());
		JLabel j2 = new JLabel(fmt2.toString());
		
		font1= j1.getFont();
		
		addContent(jp);
		
		j1.setFont(new Font(font1.getName(), Font.BOLD, 54));
		j2.setFont(new Font(font1.getName(),Font.ITALIC,40));
		clock.time.setFont(new Font(font1.getName(),Font.BOLD,40));
		f.setSize(1000,1000);
		f.setVisible(true);
		
		
	    f.add(j1);
	
        f.add(jp);
		f.add(j2);
        f.add( clock.time);
      ;
        
      j1.setBounds(20,-450,1000,1000);
      j2.setBounds(20,-390,1000,1000);
      //jp.setBorder(BorderFactory.createLineBorder(Color.black));
      clock.time.setBounds(800,-450,1000,1000);
      jp.setBounds(80,200 , 800, 650);
        clock.start();
	}

	public void addContent(JPanel panel) {
		
		jp2 = new JPanel();
		jp2.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		panel.setLayout(null);
		
		
		String year[]=new String[100]; 
		String month[]= {"January","February","March","April","May","June","July","August","September","October","November","December"};
		int year1 = Calendar.getInstance().get(Calendar.YEAR);
		int month1 = Calendar.getInstance().get(Calendar.MONTH);
		//year[0]= Integer.toString(year1);
		for(int i=0;i<100;i++) 
        { 
             year[i]=""+(int)(2050-i); 
        } 
		
		
		JLabel j3 = new JLabel("Year :");
		//Font f2 = 
		j3.setFont(new Font(font1.getName(), Font.PLAIN, 30));
		JLabel j4 = new JLabel("Month :");
		j4.setFont(new Font(font1.getName(), Font.PLAIN, 30));
		
		int index1  =  Calendar.getInstance().get(Calendar.MONTH);
		m1 = new JComboBox(month);
		m1.setSelectedIndex(index1);

		int index2 = Calendar.getInstance().get(Calendar.YEAR);
		y1 = new JComboBox(year);
		
		int a = 0;
		//int i = 0;
		while(true)
		{
			if(index2==Integer.parseInt(year[a]))
			{
				break;
			}
			a++;
		}
		
		y1.setSelectedIndex(a);
		
		produceCalendar(jp2,index1+1,index2);
		
		
	  m1.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	            JComboBox comboBox = (JComboBox) e.getSource();
	            int selectedMonth = comboBox.getSelectedIndex();
	            int selectedYear = Integer.valueOf( (String) y1.getSelectedItem());
	            
	            jp2.removeAll();
	           
	            produceCalendar(jp2,selectedMonth+1,selectedYear);
	        }
	        
	        
	    }); 
	  y1.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	            JComboBox comboBox = (JComboBox) e.getSource();
	            int s = comboBox.getSelectedIndex();
	            int selectedYear=Integer.parseInt(year[s]);
	            
	            int selectedMonth = m1.getSelectedIndex();
	            
	            
	            jp2.removeAll();
	           
	            produceCalendar(jp2,selectedMonth+1,selectedYear);
	        }
	        });
					
		
	   // y1.addItemListener(this);
				 
		panel.add(j3);
		panel.add(j4);
		panel.add(y1);
		panel.add(m1);
		panel.add(jp2);
		
		j4.setBounds(30,-450,1000,1000);
		m1.setBounds(140,37,100,30);
		y1.setBounds(490,37,100,30);
		jp2.setBounds(60,100,680,500);
		j3.setBounds(400,-450,1000,1000);
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource()==m1)
		{
		produceCalendar(jp2,m1.getSelectedIndex()+1,(Integer)y1.getSelectedItem());	
		}
		
		if(e.getSource()==y1)
		{
			produceCalendar(jp2,m1.getSelectedIndex()+1,(Integer)y1.getSelectedItem());
		}
		
	}
	
	
	
	public void produceCalendar(JPanel panel1,int mon, int year1)
	{
		
		GridLayout grid = new GridLayout(6,6);
		panel1.setLayout(grid);
		
		month = mon ;   // month (Jan = 1, Dec = 12)
         year = year1;     // year

        // months[i] = name of month i
        String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        };

        // days[i] = number of days in month i
        int[] days = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        // check for leap year
        if (month == 2 && isLeapYear(year)) days[month] = 29;


        // print calendar header
        
        sun = new JLabel("S");
        sun.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        sun.setHorizontalAlignment(CENTRE);
        mond = new JLabel("M");
        mond.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        mond.setHorizontalAlignment(CENTRE);
        tue = new JLabel("T");
        tue.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        tue.setHorizontalAlignment(CENTRE);
        wed = new JLabel("W");
        wed.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        wed.setHorizontalAlignment(CENTRE);
        thu = new JLabel("Th");
        thu.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        thu.setHorizontalAlignment(CENTRE);
        fri = new JLabel("F");
        fri.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        fri.setHorizontalAlignment(CENTRE);
        sat = new JLabel("Sa");
        sat.setFont(new Font(font1.getName(), Font.PLAIN, 30));
        sat.setHorizontalAlignment(CENTRE);
        panel1.add(sun);
        panel1.add(mond);
        panel1.add(tue);
        panel1.add(wed);
        panel1.add(thu);
        panel1.add(fri);
        panel1.add(sat);
        
        // starting day
        int d = day(month, 1, year);

        j5 = new JButton[7];
        // print the calendar
        for (int i = 0; i < d; i++) {
        	j5[i]=new JButton("  ");
        	j5[i].setFont(new Font(font1.getName(), Font.PLAIN, 30));
        //	j5[i].setBorderPainted( false );
        	panel1.add(j5[i]);
        }
            j6 = new JButton[33];
        for (int i = 1; i <= days[month]; i++) {
            
            j6[i]=new JButton(String.valueOf(i));
            panel1.add(j6[i]);
           // j6[i].setBorderPainted( false );
            j6[i].setFont(new Font(font1.getName(), Font.PLAIN, 30));
            j6[i].addActionListener((ActionListener) this);
            	{
            	
            	//panel1.add(j6[i]);
            	
            	}

		
	            }}
        public void actionPerformed(ActionEvent e) {
        	
        	
        	int m1 = month;
        	String y1 = String.valueOf(year);
            String date;
            String[] months = {
                    "",                               // leave empty so that months[1] = "January"
                    "January", "February", "March",
                    "April", "May", "June",
                    "July", "August", "September",
                    "October", "November", "December"
                };
            String month_string = months[m1];
            JLabel day = new JLabel();
            date=e.getActionCommand();
            Formatter f10 = new Formatter();
            Formatter f11 = new Formatter();
            f10.format("%s %s",month_string,date);
        	
       
        	Object[] options = { "Add Notes", "Schedule a task" };
        	Object selectedValue = JOptionPane.showOptionDialog(null, "Choose if you want to 'Add Notes' or 'Schedule a task' for the selected day. ", "Scheduler",
        	             JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
        	             null, options, options[0]);
        
        	if((Integer)selectedValue == 0)
        	{
        	JFrame f2 = new JFrame("Notes");
        	save = new JButton("Save Notes");
        	f2.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Dell\\Downloads\\grey1.jpg")));
        	JTextArea notepad = new JTextArea();
        	
        	
        	f2.setLayout(null);
        	
            day.setText(f10+", "+y1);
            day.setFont(new Font(font1.getName(), Font.ITALIC+Font.BOLD, 50));
            day.setForeground(Color.white);
            f2.getContentPane().add(day);
            
          //  JTextArea notepad = new JTextArea();
            notepad.setOpaque(false);

            JScrollPane scrollPane = new JScrollPane(notepad) {
                @Override
                protected void paintComponent(Graphics g) {
                    try {
                        Composite composite = ((Graphics2D)g).getComposite();

                        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.10f));
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());

                        ((Graphics2D)g).setComposite(composite);
                        paintChildren(g);
                    }
                    catch(IndexOutOfBoundsException e) {
                        super.paintComponent(g);
                    }
                }       
            };
            

            scrollPane.getViewport().setOpaque(false);
            scrollPane.setOpaque(false);
            scrollPane.setBorder(new RoundBorder(20));
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            notepad.setFont(new Font(font1.getName(), Font.PLAIN, 24));
           // notepad.setOpaque(false);
        	scrollPane.setBounds(50, 100, 850, 400);
        	day.setBounds(5,-200,500,500 );
        	save.setBounds(700, 500,80 , 60);
            f2.setVisible(true);
            f2.setSize(1000,600);
            
            f2.getContentPane().add(scrollPane);
            f2.getContentPane().add(save);
			//f2.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Dell\\Downloads\\snow.jpg")));
			
			
    		
        	}
        	
        	else
        	{
        		 schedule_frame = new JFrame("Scheduler");
        		 schedule_frame.setLayout(null);
        		 JLabel schedule_label_date = new JLabel("Date  : "+f10+", "+y1);
        		 schedule_label_date.setBounds(15,-180, 500, 500);
        		 schedule_label_date.setFont(new Font(font1.getName(), Font.ITALIC+Font.BOLD,26));
        		 
        		 JLabel schedule_label_time = new JLabel("Time :");
        		 schedule_label_time.setBounds(15,-100, 500, 500);
        		 schedule_label_time.setFont(new Font(font1.getName(), Font.ITALIC+Font.BOLD,26));
        		 
        		 String hours[]=new String[13];
        		 hours[0]="Hours";
        		 for(int i = 1;i<=12;i++)
        		 {
        			 hours[i] = "" + (int)(i);
        		 }
        		 JComboBox time_hours = new JComboBox(hours);
        		 time_hours.setSelectedItem("Hours");
        		 time_hours.setBounds(120,135, 70, 30);
        		 
        		 String minutes[] = new String[60];
        		 minutes[0] = "Minutes";
        		 for(int i = 1;i<=59;i++)
        		 {
        			 minutes[i] = "" + (int)(i);
        		 }
        		 JComboBox time_minutes = new JComboBox(minutes);
        		 time_minutes.setSelectedItem("Hours");
        		 time_minutes.setBounds(210,135, 70, 30);
        		 
        		 String am_pm[] = new String[2];
        		 am_pm[0] = "AM";
        		 am_pm[1] = "PM";
        		 JComboBox time_minutes_am_pm = new JComboBox(am_pm);
        		 time_minutes_am_pm.setSelectedItem("AM");
        		 time_minutes_am_pm.setBounds(300,135, 70, 30);
        		 
        		 JLabel schedule_label_task = new JLabel("Task: "); 
        		 schedule_label_task.setFont(new Font(font1.getName(), Font.ITALIC+Font.BOLD,26));
        		 schedule_label_task.setBounds(15,-10, 500, 500);
        		 
        		 
        		 JTextArea schedule_textarea =new JTextArea();
        		 JScrollPane schedule_scrollPane = new JScrollPane(schedule_textarea) {
                     protected void paintComponent(Graphics g) {
                         try {
                             Composite composite = ((Graphics2D)g).getComposite();

                             ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.10f));
                             g.setColor(getBackground());
                             g.fillRect(0, 0, getWidth(), getHeight());

                             ((Graphics2D)g).setComposite(composite);
                             paintChildren(g);
                             }
                             catch(IndexOutOfBoundsException e) {
                                 super.paintComponent(g);
                                 }
                         
                     }
                     
        		 };
                         
                         schedule_scrollPane.getViewport().setOpaque(false);
                         schedule_scrollPane.setOpaque(false);
                         schedule_scrollPane.setBorder(new RoundBorder(20));
                         schedule_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                         schedule_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                         schedule_textarea.setFont(new Font(font1.getName(), Font.PLAIN, 24));
                         //schedule_textarea.setAlignment(Component.CENTER);
        		         //schedule_textarea.setCursor(cursor);
        		         schedule_scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                         schedule_scrollPane.setBounds(90,220,310,150);
                         
                         JButton schedule_save = new JButton("Save");
                         schedule_save.setBounds(330,440,80,30);
                         
                         
        		 schedule_frame.add(schedule_save);
        		 schedule_frame.add(schedule_scrollPane);
        		 schedule_frame.add(schedule_label_task);
        		 schedule_frame.add(time_minutes_am_pm);
        		 schedule_frame.add(time_minutes);
        		 schedule_frame.add(time_hours);
        		 schedule_frame.add(schedule_label_time);
        		 schedule_frame.add(schedule_label_date);
        		 schedule_frame.setBounds(100,100,100,100);
        		 schedule_frame.setVisible(true);
        		 schedule_frame.setSize(450, 550);
        	}
    	}
        
        
        
        public static int day(int month, int day, int year) {
            int y = year - (14 - month) / 12;
            int x = y + y/4 - y/100 + y/400;
            int m = month + 12 * ((14 - month) / 12) - 2;
            int d = (day + x + (31*m)/12) % 7;
            return d;
        }
        
        public static boolean isLeapYear(int year) {
            if  ((year % 4 == 0) && (year % 100 != 0)) return true;
            if  (year % 400 == 0) return true;
            return false;
        }
        


	
	
	public class Clock{
		
		JLabel time = new JLabel();
	    SimpleDateFormat sdf  = new SimpleDateFormat("hh:mm");
	    int   currentSecond;
	    Calendar calendar;
	    
	    public void reset() {
	    	 calendar = Calendar.getInstance();
	         currentSecond = calendar.get(Calendar.SECOND);

	    }
	   
	    
	    
	    public void start()
	    {
		
	    	reset();
	    	
	        Timer timer = new Timer();
	        
	        timer.scheduleAtFixedRate( new TimerTask(){
	            public void run(){
	                if( currentSecond == 60 ) {
	                    reset();
	                }
	                time.setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
	                currentSecond++;
	            }
	        }, 0, 1000 );


	    }
	}	
	
	
	public class RoundBorder implements Border {

	    private int radius;

	    public RoundBorder(int radius) {
	        this.radius = radius;
	    }

	    public int getRadius() {
	        return radius;
	    }

	    @Override
	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, getRadius(), getRadius()));
	        g2d.dispose();
	    }

	    @Override
	    public Insets getBorderInsets(Component c) {
	        int value = getRadius() / 2;
	        return new Insets(value, value, value, value);
	    }

	    @Override
	    public boolean isBorderOpaque() {
	        return false;
	    }


	}
	
	public static void main(String args[])
	{
		new JFrame1();
	}

	

	
	
	
}

