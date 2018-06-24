


import java.awt.event.*;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonalSystem extends JFrame implements ActionListener {
//定义PersonSystem表
	JFrame frame;
	private JTable table;
	private JTextField JID;
	private JTextField JPASSWD;
	private JTextField JNAME;
	private JTextField JBIRTHDAY;
	private JTextField JDEPARTMENT;
	private JTextField JJOB ;
	private JTextField JEDU_LEVEL;
	private JTextField JSPCIALTY;
	private JTextField JADDRESS ;
	private JTextField JTEL;
	private JTextField JEMAIL;
	private JTextField JREMARK;
	private JTextField jTFQueryField;
	private JTextField JSEX;
	private JTextField JSTATE;
	private JTextField JAUTHORITY;
	
	String SelectQueryFieldStr = "员工号";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalSystem window = new PersonalSystem();
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
	public PersonalSystem() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1267, 773);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();//创建滚动面板
		scrollPane.setBounds(10, 37, 1231, 204);
		frame.getContentPane().add(scrollPane);//将带滚动条的面板加到窗口
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, "", null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"员工号","用户权限", "密码", "姓名", "性别", "生日", "所在部门", "职务", "受教育程度", "专业技能", "家庭住址", "联系电话", "电子邮箱", "当前状态(T-员工F-非员工)", "备注"
			}
		));
		table.getColumnModel().getColumn(14).setMinWidth(26);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("PERSON员工信息表");
		lblNewLabel.setBounds(487, 0, 276, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("员工号");//创建一个"员工号"标签
		lblNewLabel_1.setBounds(10, 276, 41, 27);//设置组件大小
		frame.getContentPane().add(lblNewLabel_1);//将组件加入到窗口
		
		JID = new JTextField();
		JID.setBounds(51, 273, 104, 30);
		frame.getContentPane().add(JID);
		JID.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("用户权限");
		lblNewLabel_15.setBounds(10,393,85,27);
		frame.getContentPane().add(lblNewLabel_15);
		
		JAUTHORITY = new JTextField();
		JAUTHORITY.setBounds(72,390,73,27 );
		frame.getContentPane().add(JAUTHORITY);
		JAUTHORITY.setColumns(10);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setBounds(165, 276, 29, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPASSWD = new JTextField();//创建一个文本框
		JPASSWD.setBounds(197, 273, 104, 27);
		frame.getContentPane().add(JPASSWD);
		JPASSWD.setColumns(10);//指定文本框的列
		
		JLabel lblNewLabel_3 = new JLabel("姓名");
		lblNewLabel_3.setBounds(311, 260, 41, 43);
		frame.getContentPane().add(lblNewLabel_3);
		
		JNAME = new JTextField();
		JNAME.setBounds(350, 270, 104, 27);
		frame.getContentPane().add(JNAME);
		JNAME.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("性别");
		lblNewLabel_4.setBounds(464, 268, 29, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("生日");
		lblNewLabel_5.setBounds(560, 276, 29, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JBIRTHDAY = new JTextField();
		JBIRTHDAY.setBounds(592, 270, 85, 27);
		frame.getContentPane().add(JBIRTHDAY);
		JBIRTHDAY.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("所在部门");
		lblNewLabel_6.setBounds(697, 268, 54, 30);
		frame.getContentPane().add(lblNewLabel_6);
		
		JDEPARTMENT = new JTextField();
		JDEPARTMENT.setBounds(749, 269, 129, 27);
		frame.getContentPane().add(JDEPARTMENT);
		JDEPARTMENT.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("职务");
		lblNewLabel_7.setBounds(888, 268, 35, 30);
		frame.getContentPane().add(lblNewLabel_7);
		
		JJOB = new JTextField();
		JJOB .setBounds(919, 270, 94, 25);
		frame.getContentPane().add(JJOB );
		JJOB .setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("受教育程度");
		lblNewLabel_8.setBounds(1023, 268, 68, 23);
		frame.getContentPane().add(lblNewLabel_8);
		
		JEDU_LEVEL = new JTextField();
		JEDU_LEVEL.setBounds(1091, 269, 84, 22);
		frame.getContentPane().add(JEDU_LEVEL);
		JEDU_LEVEL.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("专业技能");
		lblNewLabel_9.setBounds(10, 343, 54, 27);
		frame.getContentPane().add(lblNewLabel_9);
		
		JSPCIALTY = new JTextField();
		JSPCIALTY.setBounds(61, 341, 94, 31);
		frame.getContentPane().add(JSPCIALTY);
		JSPCIALTY.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("家庭住址");
		lblNewLabel_10.setBounds(161, 342, 54, 28);
		frame.getContentPane().add(lblNewLabel_10);
		
		JADDRESS = new JTextField();
		JADDRESS .setBounds(212, 341, 121, 31);
		frame.getContentPane().add(JADDRESS );
		JADDRESS .setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("联系电话");
		lblNewLabel_11.setBounds(350, 342, 54, 28);
		frame.getContentPane().add(lblNewLabel_11);
		
		JTEL = new JTextField();
		JTEL.setBounds(405, 341, 110, 31);
		frame.getContentPane().add(JTEL);
		JTEL.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("电子邮箱");
		lblNewLabel_12.setBounds(525, 342, 54, 28);
		frame.getContentPane().add(lblNewLabel_12);
		
		JEMAIL = new JTextField();
		JEMAIL.setBounds(588, 341, 103, 31);
		frame.getContentPane().add(JEMAIL);
		JEMAIL.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("当前状态(T-员工,F-非员工)");
		lblNewLabel_13.setBounds(697, 343, 150, 27);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("备注");
		lblNewLabel_14.setBounds(908, 343, 41, 27);
		frame.getContentPane().add(lblNewLabel_14);
		
		JREMARK = new JTextField();
		JREMARK.setBounds(938, 338, 181, 32);
		frame.getContentPane().add(JREMARK);
		JREMARK.setColumns(10);
		
		JButton btnNewButton = new JButton("查询员工信息");
		btnNewButton.setBounds(10, 507, 129, 37);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		
		JComboBox comboBox = new JComboBox();//创建组合框
		comboBox.addItem("员工号");//为组合框添加选项
		comboBox.addItem("姓名");
		comboBox.addItem("职务");
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"员工号", "姓名", "职务"}));
		comboBox.setBounds(168, 511, 85, 28);
		frame.getContentPane().add(comboBox);//将组合框加入到窗口
		
		jTFQueryField = new JTextField();//查询员工信息的文本框
		jTFQueryField.setBounds(286, 513, 132, 25);
		frame.getContentPane().add(jTFQueryField);
		jTFQueryField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("更新员工信息");//创建"更新员工信息窗口"按钮
		btnNewButton_1.setBounds(125, 430, 132, 37);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		
		
		//为组合框添加监听事件
		comboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				 switch (event.getStateChange()) {  
	                case ItemEvent.SELECTED:  
	                	SelectQueryFieldStr = (String) event.getItem();  
	                    System.out.println("选中：" + SelectQueryFieldStr);  
	                    break;  
	                case ItemEvent.DESELECTED:  
	                    System.out.println("取消选中：" + event.getItem());  
	                    break;  
	                }  
			}
		});

		table.addMouseListener(new MouseAdapter() //为表格添加监听
		{
			public void mouseClicked(MouseEvent e) 
			{ 
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
				System.out.println("mouseClicked(). row = " + row);
				Vector v = new Vector();
				int i=0;
				for(i=0;i<15;i++) {
				v.add(table.getValueAt(row, i));}

				JID.setText(Integer.toString((int) v.get(0)));
				JAUTHORITY.setText((String) v.get(1));
				JPASSWD.setText((String) v.get(2));// 
				JNAME.setText((String) v.get(3));
				JSEX.setText((String) v.get(4));// 
				JBIRTHDAY.setText((String) v.get(5));
				JDEPARTMENT.setText((String) v.get(6));// 
				JJOB.setText((String) v.get(7));
				JEDU_LEVEL.setText((String) v.get(8));
				JSPCIALTY.setText((String) v.get(9));
				JADDRESS.setText((String) v.get(10));
				JTEL.setText((String) v.get(11));
				JEMAIL.setText((String) v.get(12));
				JSTATE.setText((String) v.get(13));
				JREMARK.setText((String) v.get(14));
			}
			
		});
		
		JButton btnNewButton_2 = new JButton("新增员工");
		btnNewButton_2.setBounds(11, 431, 104, 35);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		JButton btnNewButton_3 = new JButton("删除当前员工信息");
		btnNewButton_3.setBounds(428, 431, 161, 37);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
		JButton btnNewButton_4 = new JButton("删除所有员工信息");
		btnNewButton_4.setBounds(599, 430, 173, 37);
		frame.getContentPane().add(btnNewButton_4);
		btnNewButton_4.addActionListener(this);
		
		JButton btnNewButton_5 = new JButton("查询所有员工信息");
		btnNewButton_5.setBounds(267, 430, 151, 37);
		frame.getContentPane().add(btnNewButton_5);
		btnNewButton_5.addActionListener(this);
		
		JSEX= new JTextField();
		JSEX.setBounds(497, 273, 41, 25);
		frame.getContentPane().add(JSEX);
		JSEX.setColumns(10);
		
		JSTATE = new JTextField();
		JSTATE.setBounds(857, 346, 29, 24);
		frame.getContentPane().add(JSTATE);
		JSTATE.setColumns(10);
		btnNewButton_4.addActionListener(this);
		
		
	}
	public void actionPerformed(ActionEvent e) {//实现actionPerformed（）方法
		if(e.getActionCommand().equals("查询员工信息")  //获取鼠标的命令
			&& !jTFQueryField.getText().isEmpty()){
			System.out.println("actionPerformed(). 查询员工信息");
			String sQueryField = jTFQueryField.getText().trim();
			queryProcess(sQueryField);
			jTFQueryField.setText("");
		}else if(e.getActionCommand().equals("查询所有员工信息")) {
			System.out.println("actionPerformed(). 查询所有员工信息");
			queryAllProcess();
		}else if(e.getActionCommand().equals("新增员工")){
			System.out.println("actionPerformed(). 新增员工");
			insertProcess();
		}else if(e.getActionCommand().equals("更新员工信息")){
			System.out.println("actionPerformed(). 更新员工信息");
			updateProcess();
		}else if(e.getActionCommand().equals("删除当前员工信息")){
			System.out.println("actionPerformed(). 删除当前员工信息");			
			deleteCurrentRecordProcess();
		}else if(e.getActionCommand().equals("删除所有员工信息")){
			System.out.println("actionPerformed(). 删除所有员工信息");
			deleteAllRecordsProcess();
		}
	}
	DbProcess dbProcess = new DbProcess();

	public void queryProcess(String sQueryField)//查询数据库数据
	{
		try{
			// 建立查询条件
			String sql = "select * from personinformation where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
		
			if(queryFieldStr.equals("ID")){//int sAge.
				sql = sql + queryFieldStr;
				sql = sql + " = " + sQueryField;
			}else{
				sql = sql + queryFieldStr;
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
			}
			
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
	
	public void queryAllProcess()
	{
		try{
			// 建立查询条件
			String sql = "select * from personinformation;";
			System.out.println("queryAllProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			table.removeAll();
			Vector v = new Vector();
			Vector vname = new Vector();
			Vector vl[] = new Vector[200];
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
			int i =3;
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
	
	public void insertProcess()
	{	String PASSWD;
		String ID = null;
	if(JID.getText().trim().equals(null)) {
		ID = JID.getText().trim();
	}else {
		try{
			// 建立查询条件
			String sql = "select * from personinformation;";
			System.out.println("queryAllProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			int id=0;
			while(rs.next()) {
				id++;
				String sid = String.valueOf(id);
				JID.setText(sid);
			}
				ID = JID.getText().trim();
			System.out.println(ID);
			dbProcess.disconnect();
	}catch(SQLException sqle){
		System.out.println("sqle = " + sqle);
		JOptionPane.showMessageDialog(null,
			"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	}	
		
		if(JPASSWD.getText().trim().equals(null)) {
			 PASSWD = JPASSWD.getText().trim();
		}else {
			 PASSWD = "123456789";
		}
		String AUTHORITY = JAUTHORITY.getText().trim();
		String NAME = JNAME.getText().trim();
		String SEX = JSEX.getText().trim();
		String BIRTHDAY = JBIRTHDAY.getText().trim();
		String DEPARTMENT = JDEPARTMENT.getText().trim();
		String JOB = JJOB.getText().trim();
		String EDU_LEVEL = JEDU_LEVEL.getText().trim();
		String SPCIALTY = JSPCIALTY.getText().trim();
		String ADDRESS = JADDRESS.getText().trim();
		String TEL = JTEL.getText().trim();
		String EMAIL = JEMAIL.getText().trim();
		String STATE = JSTATE.getText().trim();
		String REMARK = JREMARK.getText().trim();
		
		// 建立插入条件
		String sql = "insert into personinformation values(";
		sql = sql + ID + ",'";
		sql = sql + PASSWD + "','";
		sql = sql + AUTHORITY + "','";
		sql = sql + NAME + "','";
		sql = sql + SEX + "','";
		sql = sql + BIRTHDAY + "','";
		sql = sql +DEPARTMENT+ "','";
		sql = sql +JOB + "','";
		sql = sql +EDU_LEVEL + "','";
		sql = sql +SPCIALTY + "','";
		sql = sql +ADDRESS + "','";
		sql = sql +TEL + "','";
		sql = sql +EMAIL + "','";
		sql = sql +STATE + "','";
		sql = sql + REMARK + "');";

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
	
	
	public void updateProcess()
	{String PASSWD;
		String ID = JID.getText().trim();
		if(JPASSWD.getText().trim().equals(null)) {
			 PASSWD = JPASSWD.getText().trim();
		}else {
			 PASSWD = "123456789";
		}
		String AUTHORITY = JAUTHORITY.getText().trim();
		String NAME = JNAME.getText().trim();
		String SEX = JSEX.getText().trim();
		String BIRTHDAY = JBIRTHDAY.getText().trim();
		String DEPARTMENT = JDEPARTMENT.getText().trim();
		String JOB = JJOB.getText().trim();
		String EDU_LEVEL = JEDU_LEVEL.getText().trim();
		String SPCIALTY = JSPCIALTY.getText().trim();
		String ADDRESS = JADDRESS.getText().trim();
		String TEL = JTEL.getText().trim();
		String EMAIL = JEMAIL.getText().trim();
		String STATE = JSTATE.getText().trim();
		String REMARK = JREMARK.getText().trim();
		// 建立更新条件
		String sql = "update personinformation set PASSWD = '";
		sql = sql + PASSWD + "', NAME = '";
		sql = sql + NAME + "', AUTHORITY = '";
		sql = sql + AUTHORITY + "', SEX = '";
		sql = sql + SEX + "', BIRTHDAY = '";
		sql = sql + BIRTHDAY + "', DEPARTMENT = '";
		sql = sql + DEPARTMENT + "', JOB = '";
		sql = sql + JOB + "', EDU_LEVEL = '";
		sql = sql + EDU_LEVEL + "', SPCIALTY = '";
		sql = sql + SPCIALTY + "', ADDRESS = '";
		sql = sql + ADDRESS + "', TEL = ";
		sql = sql + TEL + ", EMAIL = '";
		sql = sql + EMAIL + "', STATE = '";
		sql = sql + STATE + "', REMARK = '";
		sql = sql + REMARK + "'";
		sql = sql + " WHERE ID = '" + ID + "';";
		System.out.println("updateProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
	
	public void deleteCurrentRecordProcess()
	{
		String ID = JID.getText().trim();
		
		// 建立删除条件
		String sql = "delete from personinformation where ID= '" + ID + "';";
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
	
	
	public void deleteAllRecordsProcess()
	{
		// 建立删除条件
		String sql = "delete from personinformation;";
		System.out.println("deleteAllRecordsProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("deleteAllRecordsProcess(). delete database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
	
	
	
	public String jCBSelectQueryFieldTransfer(String InputStr)
	{
		String outputStr = "";
		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);
		
		if(InputStr.equals("员工号")){
			outputStr = "ID";
		}else if(InputStr.equals("密码")){
			outputStr = "PASSWD";
		}else if(InputStr.equals("用户权限")){
			outputStr = "AUTHORITY";
		}else if(InputStr.equals("姓名")){
			outputStr = "NAME";
		}else if(InputStr.equals("性别")){
			outputStr = "SEX";
		}else if(InputStr.equals("生日")){
			outputStr = "BIRTHDAY";
		}else if(InputStr.equals("所在部门")){
			outputStr = "DEPARTMENT";
		}else if(InputStr.equals("职务")){
			outputStr = "JOB";
		}else if(InputStr.equals("受教育程度")){
			outputStr = "EDU_LEVEL";
		}else if(InputStr.equals("专业技能")){
			outputStr = "SPCIALTY";
		}else if(InputStr.equals("家庭住址")){
			outputStr = "ADDRESS";
		}else if(InputStr.equals("联系电话")){
			outputStr = "TEL";
		}else if(InputStr.equals("电子邮箱")){
			outputStr = "EMAIL";
		}else if(InputStr.equals("当前状态")){
			outputStr = "STATE";
		}else if(InputStr.equals("备注")){
			outputStr = "REMARK";
		}
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
		return outputStr;
	}
}
