import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Employee extends JFrame implements ActionListener{

	protected static final String String = null;
	JFrame frame;
	private JTable table;
	String sname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 931, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 879, 102);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"\u5458\u5DE5\u53F7", "\u7528\u6237\u6743\u9650", "\u5BC6\u7801", "\u59D3\u540D", "\u6027\u522B", "\u751F\u65E5", "\u6240\u5728\u90E8\u95E8", "\u804C\u52A1", "\u53D7\u6559\u80B2\u7A0B\u5EA6", "\u4E13\u4E1A\u6280\u80FD", "\u5BB6\u5EAD\u4F4F\u5740", "\u8054\u7CFB\u7535\u8BDD", "\u7535\u5B50\u90AE\u7BB1", "\u5F53\u524D\u72B6\u6001", "\u5907\u6CE8"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u8FDB\u5165\u5458\u5DE5\u4FE1\u606F\u9875\u9762");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 25));
		lblNewLabel.setBounds(307, 10, 608, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("退出");
		btnNewButton.setBounds(682, 275, 103, 31);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("退出")) {
		System.exit(0);
	}
	}
	
	DbProcess dbProcess = new DbProcess();
	Login login = new Login();
	
	public void queryProcess(String sQueryField)//查询数据库数据
	{
		try{
			// 建立查询条件
			String sql = "select * from personinformation where NAME";
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
			
			System.out.println("queryProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
	
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			table.removeAll();
			
			Vector v = new Vector();
			Vector vname = new Vector();
			Vector vl[] = new Vector[200];
			int i =1;
			while(rs.next()){
				vl[i] = new Vector();
				vl[i].add(Integer.valueOf(rs.getInt("ID")));
				vl[i].add(rs.getString("AUTHORITY"));
				vl[i].add(rs.getString("PASSWD"));
				vl[i].add(rs.getString("NAME"));
				vl[i].add(rs.getString("SEX"));
				vl[i].add(rs.getString("BIRTHDAY"));
				vl[i].add(rs.getString("DEPARTMENT"));
				vl[i].add(rs.getString("JOB"));
				vl[i].add(rs.getString("EDU_LEVEL"));
				vl[i].add(rs.getString("SPCIALTY"));
				vl[i].add(rs.getString("ADDRESS"));
				vl[i].add(rs.getString("TEL"));
				vl[i].add(rs.getString("EMAIL"));
				vl[i].add(rs.getString("STATE"));
				vl[i].add(rs.getString("REMARK"));
				v.add(vl[i++]);
				
			}
			vname.add("员工号");
			vname.add("用户权限");
			vname.add("密码");
			vname.add("姓名");
			vname.add("性别");
			vname.add("生日");
			vname.add("所在部门");
			vname.add("职务");
			vname.add("受教育程度");
			vname.add("专业技能");
			vname.add("家庭住址");
			vname.add("联系电话");
			vname.add("电子邮箱");
			vname.add("当前状态");
			vname.add("备注");	
			DefaultTableModel model = new DefaultTableModel(v,vname);	
			table.setModel(model);
			
			table.updateUI();

			dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
	}

}
