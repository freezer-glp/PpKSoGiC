import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.StyledEditorKit.BoldAction;



public class UI extends JFrame implements ActionListener
{
	
	public JButton search ;
	public JButton reset ;
	public JButton detail;
	public JButton nextRe;
	public JTextField keyInput;
	public String searchInput;
	public Draw draw;
	public keyWin keyWindow;
	public int searchflag;
	//public JFrame keyWindow;
	public UI() 
	{
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		//draw paintPanel = new draw();
		keyWindow = new keyWin();
		
		JPanel pdown = new JPanel(new BorderLayout());
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
		JPanel buttonArea = new JPanel(new FlowLayout(FlowLayout.CENTER,80,5));
		draw = new Draw();
		draw.setBackground(new Color(172,217,248));
		draw.setSize(1024, 500);
		searchPanel.setBackground(new Color(245,229,138));
		buttonArea.setBackground(new Color(245,229,138));
		search = new JButton("Search");
		reset = new JButton("Reset");
		detail = new JButton("Detail");
		nextRe = new JButton("NextResult");
		
		
		keyInput = new JTextField(40);
		//keyInput.setText("-----------------------------------Please input your keywords------------------------");
		//searchPanel.setLayout(new BorderLayout());
		JLabel jlb = new JLabel("Enter your keywords:");
		jlb.setFont(new Font("", 1, 16));
		searchPanel.add(jlb);
		searchPanel.add(keyInput);
		
		search.addActionListener(this);
		reset.addActionListener(this);
		detail.addActionListener(this);
		nextRe.addActionListener(this);
		
		buttonArea.add(detail);
		buttonArea.add(search);
		buttonArea.add(reset);
		buttonArea.add(nextRe);
		
		pdown.setSize(1024,200);
		pdown.add(searchPanel,BorderLayout.CENTER);
		pdown.add(buttonArea,BorderLayout.SOUTH);
		
		container.add(draw,BorderLayout.CENTER);
		container.add(pdown,BorderLayout.SOUTH);
		searchflag = 0;//we have no search before
	}

	public void actionPerformed(ActionEvent e)
	{
		//String buffer = null;
		if(e.getSource() == search)
		{
			draw.curShowNum = 0;
			draw.reset();
			searchInput = keyInput.getText();
			if(!searchInput.equals(""))
			{
				System.out.println("待查关键词为："+searchInput);
				
				long startTime=System.currentTimeMillis();   //获取开始时间  
				try
				{
					client.doSearch(searchInput);
				}
				catch (IOException e1)
				{
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				cloud.cloudSearch(0);
				if(cloud.noResult != 1) //we have result
				{
					client.showResult();
					draw.resultNum = client.resultList.size();
					draw.printResult(client.resultList);
				}
				
				long endTime=System.currentTimeMillis(); //获取结束时间  
				System.out.println("查询时间： "+(endTime-startTime)+"ms"); 
				searchflag = 1;
			}
			
			 
		}
		else if(e.getSource() == reset)
		{
			searchflag = 0;
			draw.reset();
			keyInput.setText("");
		}
		else if(e.getSource() == detail)
		{
			//Window.setVisible(true);
			keyWindow.setTitle("keywords");
			keyWindow.setSize(600, 490);
			keyWindow.setVisible(true);
			//keyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else if(e.getSource() == nextRe)
		{
			if(searchflag == 1)
			{
				draw.curShowNum++;
				draw.reset();
				draw.printResult(client.resultList);
			}
		}
		
	}

	public  void setupUI() 
	{
		UI frame =new UI();
		frame.setTitle("test_UI");
		frame.setSize(1024, 768);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				
	}
	
}
class keyWin extends JFrame
{
	private static final int Bold = 0;
	JTable jtable;
	DefaultTableModel dtm;
	JScrollPane jsp;
	public keyWin()
	{
		String title[] = {"顶点","关键词"};
		String key[] = {"a1","J.Tom","a2","L.Guo","a3","V.Hristids",
						"a4","Y.James","a5","A.Balmin","a6","B.Chin","a7","F.Lee",
						"a8","H.Wang","a9","V.Jagadish","a10","T.Tran",
						"p1","topology search over biological databases",
						"p2","xrank:ranked keyword search over xml documents",
						"p3","bidirectional expansion for keyword search on graphs",
						"p4","finding top-k answer in keyword proximity search",
						"p5","efficient ir-style keyword search over relational databases",
						"p6","keyword proximity search on xml graphs",
						"p7","discover:keyword search in relational databases",
						"p8","ranked keyword serch on graphs",
						"p9","efficient queries from keywords on rdf data",
						"p10","a lightweight keyword interface to semantic search",
						"p11","scalable semantic web data management using vertical partitioning",
						"p12","accurate estimation of the degree distribution of private network",
						"p13","anonyming weighted social network graphs",
						"p14","privacy preserving network publication against structural attacks",
						"p15","anonyming bipartite graphs data using safe grouping"
						};
		
		
		String buffer[] = new String[2];
		
		jtable = new JTable();
		jsp = new JScrollPane();
		dtm = new DefaultTableModel(title, 0);
		jtable.setModel(dtm);
		
		
		jsp.getViewport().setView(jtable);
		
		for(int i = 0; i <= 49; i=i+2)
		{
			buffer[0] = key[i];
			buffer[1] = key[i+1];
			//System.out.println(buffer[0]+"-"+buffer[1]);
			dtm.addRow(buffer);
			jtable.invalidate();
		}
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
	    jtable.getColumn("顶点").setCellRenderer(render);
	    
		TableColumnModel colums = jtable.getColumnModel();
		colums.getColumn(0).setPreferredWidth(5);
		colums.getColumn(1).setPreferredWidth(400);
		jtable.setColumnModel(colums);
		
		jtable.setFont(new Font("",0,15));
		jtable.getTableHeader().setFont(new Font("",1,18));
		this.add(jsp);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	
}
class Draw extends JPanel
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 2647310679166810017L;
	//int x,y,r;
	public int a =100;
	public ArrayList<Integer[][]> resultList;
	public boolean flag[];
	public int resultNum;
	public int curShowNum;
	public Draw()
	{
		flag = new boolean[25];
		for(int i = 0;i <= 24;i++)
			flag[i] = false;
	}
	
	public void reset()
	{
		flag = new boolean[25];
		for(int i = 0;i <= 24;i++)
			flag[i] = false;
		repaint();
	}
	
	public void printResult( ArrayList<Integer[][]> resultList)
	{
		this.resultList = resultList;
		
		Integer re[][] = new Integer[matrix.mSize][matrix.mSize];
		curShowNum = curShowNum % resultNum;
		
//		System.out.println("cur:"+curShowNum+"-resu:"+resultNum	);
		re = resultList.get(curShowNum);
		for (int k = 0; k <= matrix.mSize - 1; k++)
			if(re[k][k] == 1)
				flag[k] = true;
		repaint();		
			
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font("",Font.BOLD,17));
		int r = 27, x = 70,y = 70,dis = 110;
		int p1x = x,          p1y = y + dis,
			a1x = x,          a1y = y + 2*dis,
			p9x = x,          p9y = y + 3*dis,
			p10x = x,         p10y = y +4*dis,
			p2x = x + dis,    p2y = y + dis,
			a2x = x + dis,    a2y = y + 2*dis,
			a8x = x + dis,    a8y = y + 3*dis,
			p11x = x + dis,   p11y = y + 4*dis,
			p3x = x + 2*dis,  p3y = y,
			a6x = x + 2*dis,  a6y = y + 2*dis,
			a9x = x + 2*dis,  a9y = y + 3*dis,
			p4x = x + 3*dis,  p4y = y + dis,
			a3x = x + 3*dis,  a3y = y + 2*dis,
			p12x = x + 3*dis, p12y = y + 3*dis,
			a10x = x + 3*dis, a10y = y + 4*dis,
			p8x = x + 4*dis,  p8y = y,
			p5x = x + 5*dis,  p5y = y + dis,
			a7x = x + 5*dis,  a7y = y + 2*dis,
			p6x = x + 6*dis,  p6y = y + dis,
			a4x = x + 6*dis,  a4y = y + 2*dis,
			p13x = x + 6*dis, p13y = y + 3*dis,
			p15x = x + 7*dis, p15y = y + 4*dis,
			p7x = x + 8*dis,  p7y = y + dis,
			a5x = x + 8*dis,  a5y = y + 2*dis,
			p14x = x + 8*dis, p14y = y + 3*dis;
			
			
		g.setColor(Color.black);
		float lineWidth = 4.0f;
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
	    ((Graphics2D)g).drawLine(p1x, p1y, a1x, a1y);
	    ((Graphics2D)g).drawLine(a1x, a1y, p9x, p9y);
	    ((Graphics2D)g).drawLine(p9x, p9y, p10x, p10y);
	    ((Graphics2D)g).drawLine(p1x, p1y, p2x, p2y);
	    ((Graphics2D)g).drawLine(p1x, p1y, a2x, a2y);
	    ((Graphics2D)g).drawLine(p2x, p2y, a1x, a1y);
	    ((Graphics2D)g).drawLine(a1x, a1y, a8x, a8y);
	    ((Graphics2D)g).drawLine(a8x, a8y, p11x, p11y);
	    ((Graphics2D)g).drawLine(p3x, p3y, p2x, p2y);
	    ((Graphics2D)g).drawLine(a6x, a6y, p3x, p3y);
	    ((Graphics2D)g).drawLine(p3x, p3y, p4x, p4y);
	    ((Graphics2D)g).drawLine(p4x, p4y, a3x, a3y);
	    ((Graphics2D)g).drawLine(a3x, a3y, p12x, p12y);
	    ((Graphics2D)g).drawLine(a9x, a9y, p12x, p12y);
	    ((Graphics2D)g).drawLine(p12x, p12y, a10x, a10y);
	    ((Graphics2D)g).drawLine(p4x, p4y, p8x, p8y);
	    ((Graphics2D)g).drawLine(p8x, p8y, p5x, p5y);
	    ((Graphics2D)g).drawLine(a7x, a7y, p5x, p5y);
	    ((Graphics2D)g).drawLine(p4x, p4y, a7x, a7y);
	    ((Graphics2D)g).drawLine(p5x, p5y, p6x, p6y);
	    ((Graphics2D)g).drawLine(p6x, p6y, a4x, a4y);
	    ((Graphics2D)g).drawLine(a4x, a4y, p13x, p13y);
	    ((Graphics2D)g).drawLine(a4x, a4y, p7x, p7y);
	    ((Graphics2D)g).drawLine(p6x, p6y, a5x, a5y);
	    ((Graphics2D)g).drawLine(a5x, a5y, p14x, p14y);
	    ((Graphics2D)g).drawLine(p13x, p13y, p15x, p15y);
	    ((Graphics2D)g).drawLine(p14x, p14y, p15x, p15y);
	    ((Graphics2D)g).drawLine(p9x, p9y, a8x, a8y);
	    ((Graphics2D)g).drawLine(p4x, p4y, p5x, p5y);
	    ((Graphics2D)g).drawLine(a7x, a7y, p13x, p13y);
		
		
		//g.setColor(Color.black);
		//g.setFont(new Font("",Font.BOLD,16)); 
		
		if(flag[1])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p1x-r, p1y-r	,2*r, 2*r);
		
		if(flag[0])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a1x-r, a1y-r	,2*r, 2*r);
		
		if(flag[15])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p9x-r, p9y-r	,2*r, 2*r);
		if(flag[17])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p10x-r, p10y-r,2*r, 2*r);
		if(flag[3])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p2x-r, p2y-r	,2*r, 2*r);
		if(flag[2])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a2x-r, a2y-r	,2*r, 2*r);
		if(flag[16])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a8x-r, a8y-r	,2*r, 2*r);
		if(flag[18])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p11x-r, p11y-r,2*r, 2*r);
		if(flag[4])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p3x-r, p3y-r	,2*r, 2*r);
		if(flag[12])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a6x-r, a6y-r	,2*r, 2*r);
		if(flag[9])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a9x-r, a9y-r	,2*r, 2*r);
		if(flag[5])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p4x-r, p4y-r	,2*r, 2*r);
		if(flag[6])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a3x-r, a3y-r	,2*r, 2*r);
		if(flag[20])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p12x-r, p12y-r,2*r, 2*r);
		if(flag[21])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a10x-r, a10y-r,2*r, 2*r);
		if(flag[14])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p8x-r, p8y-r	,2*r, 2*r);
		if(flag[7])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p5x-r, p5y-r	,2*r, 2*r);
		if(flag[13])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a7x-r, a7y-r	,2*r, 2*r);
		if(flag[9])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p6x-r, p6y-r	,2*r, 2*r);
		if(flag[8])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a4x-r, a4y-r	,2*r, 2*r);
		if(flag[22])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p13x-r, p13y-r,2*r, 2*r);
		if(flag[24])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p15x-r, p15y-r,2*r, 2*r);
		if(flag[10])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p7x-r, p7y-r	,2*r, 2*r);
		if(flag[11])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(a5x-r, a5y-r	,2*r, 2*r);
		if(flag[23])
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.fillOval(p14x-r, p14y-r,2*r, 2*r);
		
		
		
	    g.setColor(Color.white);
	    int xadj = -10,yadj = +5;
	    g.drawString( "P1", p1x+xadj, p1y+yadj);
	    g.drawString( "P2", p2x+xadj, p2y+yadj);
	    g.drawString( "P3", p3x+xadj, p3y+yadj);
	    g.drawString( "P4", p4x+xadj, p4y+yadj);
	    g.drawString( "P5", p5x+xadj, p5y+yadj);
	    g.drawString( "P6", p6x+xadj, p6y+yadj);
	    g.drawString( "P7", p7x+xadj, p7y+yadj);
	    g.drawString( "P8", p8x+xadj, p8y+yadj);
	    g.drawString( "P9", p9x+xadj, p9y+yadj);
	    g.drawString( "P10", p10x+xadj, p10y+yadj);
	    g.drawString( "P11", p11x+xadj, p11y+yadj);
	    g.drawString( "P12", p12x+xadj, p12y+yadj);
	    g.drawString( "P13", p13x+xadj, p13y+yadj);
	    g.drawString( "P14", p14x+xadj, p14y+yadj);
	    g.drawString( "P15", p15x+xadj, p15y+yadj);
	    
	    g.drawString( "a1", a1x+xadj, a1y+yadj);
	    g.drawString( "a2", a2x+xadj, a2y+yadj);
	    g.drawString( "a3", a3x+xadj, a3y+yadj);
	    g.drawString( "a4", a4x+xadj, a4y+yadj);
		g.drawString( "a5", a5x+xadj, a5y+yadj);
	    g.drawString( "a6", a6x+xadj, a6y+yadj);
	    g.drawString( "a7", a7x+xadj, a7y+yadj);
	    g.drawString( "a8", a8x+xadj, a8y+yadj);
	    g.drawString( "a9", a9x+xadj, a9y+yadj);
	    g.drawString( "a10", a10x+xadj, a10y+yadj);
	    
		
		
		g.setColor(Color.black);
		//g.drawString("P1", x, y);
		//g.drawRect(0, 0, 800, 600);
		 //g.fillRect(0,0, 400, 400);
	}
}
