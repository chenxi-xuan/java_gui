import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class PersonChange extends JFrame implements ActionListener {

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
					PersonChange window = new PersonChange();
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
	public PersonChange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 578, 346);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4EBA\u4E8B\u53D8\u66F4\u8BB0\u5F55\u8868");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 20));
		lblNewLabel.setBounds(216, 10, 193, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 47, 414, 107);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u8BB0\u5F55\u7F16\u53F7", "\u5458\u5DE5\u53F7", "\u53D8\u66F4\u4EE3\u7801", "\u8BE6\u7EC6\u8BB0\u5F55"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("记录编号");
		lblNewLabel_1.setBounds(25, 176, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(88, 173, 85, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("员工号");
		lblNewLabel_2.setBounds(216, 176, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(263, 173, 71, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("变更代码");
		lblNewLabel_3.setBounds(370, 176, 54, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(431, 173, 66, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("详细记录");
		lblNewLabel_4.setBounds(25, 216, 54, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(85, 213, 251, 32);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(227, 255, 71, 27);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.setBounds(451, 255, 76, 27);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		JButton btnNewButton_2 = new JButton("查询所有记录");
		btnNewButton_2.setBounds(88, 255, 117, 27);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		JButton btnNewButton_3 = new JButton("删除当前记录");
		btnNewButton_3.setBounds(316, 255, 125, 27);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("确定")
				&& !textField.getText().isEmpty()	
				&& !textField_1.getText().isEmpty()
				&& !textField_2.getText().isEmpty()
				&& !textField_3.getText().isEmpty()
				) {
			insert();	
		}
		if(e.getActionCommand().equals("查询所有记录")) {
			queryAllProcess();
		}
		if(e.getActionCommand().equals("删除当前记录")) {
			deleteCurrentRecordProcess();
		}
		if(e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
	}
	DbProcess dbProcess = new DbProcess();
	public void insert() {
		String ID = textField.getText().trim();
		String PERSON = textField_1.getText().trim();
		String CHANGE = textField_2.getText().trim();
		String DESCRIPTION = textField_3.getText().trim();
		
		//建立插入
		String sql = "insert into PersonChange values(";
		sql = sql + ID + ",'";
		sql = sql + PERSON + "','";
		sql = sql + CHANGE + "','";
		sql = sql + DESCRIPTION + "');";
		
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
			String sql = "select * from PersonChange;";
			System.out.println("queryAllProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			table.removeAll();
			Vector v = new Vector();
			Vector vname = new Vector();
			Vector vl[] = new Vector[20];
			vname.add("记录编号");
			vname.add("员工号");
			vname.add("变更代码");
			vname.add("详细记录");
			int i = 3;
			while(rs.next()) {
				vl[i] = new Vector();
				vl[i].add(Integer.valueOf(rs.getInt("ID")));
				vl[i].add(rs.getString("PERSON"));
				vl[i].add(rs.getString("CHANGE"));
				vl[i].add(rs.getString("DESCRIPTION"));
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
	
	//删除当前记录
	public void deleteCurrentRecordProcess()
	{
		String ID = textField.getText().trim();
		
		// 建立删除条件
		String sql = "delete from PersonChange where ID= " + ID + ";";//传入ID即可删除
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
	
