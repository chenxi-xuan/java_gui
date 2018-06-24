import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Departmentinfo extends JFrame implements ActionListener {

	JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Departmentinfo window = new Departmentinfo();
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
	public Departmentinfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("华文行楷", Font.BOLD, 25));
		frame.setBounds(100, 100, 559, 463);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u90E8\u95E8\u4FE1\u606F\u8868");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 25));
		lblNewLabel.setBounds(202, 10, 204, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 523, 140);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u90E8\u95E8\u7F16\u53F7", "\u90E8\u95E8\u540D\u79F0", "\u90E8\u95E8\u7ECF\u7406", "\u7B80\u4ECB"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("退出");
		btnNewButton.setBounds(420, 380, 85, 34);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("部门编号");
		lblNewLabel_1.setBounds(10, 239, 58, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(64, 239, 93, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("部门名称");
		lblNewLabel_2.setBounds(173, 241, 58, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(229, 239, 123, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("部门经理");
		lblNewLabel_3.setBounds(370, 239, 63, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(427, 240, 93, 23);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("简介");
		lblNewLabel_4.setBounds(10, 274, 48, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(42, 274, 471, 25);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("添加信息");
		btnNewButton_1.setBounds(64, 332, 99, 34);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		JButton btnNewButton_3 = new JButton("删除信息");
		btnNewButton_3.setBounds(195, 334, 95, 31);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
		JButton btnNewButton_2 = new JButton("查看所有信息");
		btnNewButton_2.setBounds(328, 332, 123, 34);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("添加信息")
				&& !textField.getText().isEmpty()	
				&& !textField_1.getText().isEmpty()
				&& !textField_2.getText().isEmpty()
				&& !textField_3.getText().isEmpty()
				) {
			insert();
		}
		if(e.getActionCommand().equals("删除信息")) {
			deleteCurrentRecordProcess();
		}
		if(e.getActionCommand().equals("查看所有信息")) {
			queryAllProcess();
		}
		if(e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
	}
	
	
	DbProcess dbProcess = new DbProcess();
	public void insert() {
		String ID = textField.getText().trim();
		String NAME = textField_1.getText().trim();
		String MANAGER = textField_2.getText().trim();
		String INTRO = textField_3.getText().trim();
		
		//建立插入
		String sql = "insert into DEPARTMENT values(";
		sql = sql + ID + ",'";
		sql = sql + NAME + "','";
		sql = sql + MANAGER + "','";
		sql = sql + INTRO + "');";
		
		System.out.println("insertProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("insertProcess(). insert database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
	public void queryAllProcess() { 
		try{
			// 建立查询条件
			String sql = "select * from DEPARTMENT;";
			System.out.println("queryAllProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			table.removeAll();
			Vector v = new Vector();
			Vector vname = new Vector();
			Vector vl[] = new Vector[20];
			vname.add("部门编号");
			vname.add("部门名称");
			vname.add("部门经理");
			vname.add("简介");
			int i = 3;
			while(rs.next()) {
				vl[i] = new Vector();
				vl[i].add(Integer.valueOf(rs.getInt("ID")));
				vl[i].add(rs.getString("NAME"));
				vl[i].add(rs.getString("MANAGER"));
				vl[i].add(rs.getString("INTRO"));
				v.add(vl[i++]);
			}
			if(i==3){
				vl[i] = new Vector();
				vl[i].add("暂无数据");
				vl[i].add("");
				vl[i].add("");
				v.add(vl[i]);
	}
			DefaultTableModel model = new DefaultTableModel(v,vname);	
			table.setModel(model);
			table.updateUI();

			dbProcess.disconnect();
}catch(SQLException sqle){
	System.out.println("sqle = " + sqle);
	JOptionPane.showMessageDialog(null,
		"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
}
		}
	//删除当前数据
	public void deleteCurrentRecordProcess()
	{
		String ID = textField.getText().trim();
		
		// 建立删除条件
		String sql = "delete from DEPARTMENT where ID= " + ID + ";";//传入ID即可删除
		System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("deleteCurrentRecordProcess(). delete database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
}
