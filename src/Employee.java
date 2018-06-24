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
		table.setFont(new Font("����", Font.PLAIN, 10));
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
		lblNewLabel.setFont(new Font("�����п�", Font.BOLD, 25));
		lblNewLabel.setBounds(307, 10, 608, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("�˳�");
		btnNewButton.setBounds(682, 275, 103, 31);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("�˳�")) {
		System.exit(0);
	}
	}
	
	DbProcess dbProcess = new DbProcess();
	Login login = new Login();
	
	public void queryProcess(String sQueryField)//��ѯ���ݿ�����
	{
		try{
			// ������ѯ����
			String sql = "select * from personinformation where NAME";
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
			
			System.out.println("queryProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
	
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
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
			vname.add("Ա����");
			vname.add("�û�Ȩ��");
			vname.add("����");
			vname.add("����");
			vname.add("�Ա�");
			vname.add("����");
			vname.add("���ڲ���");
			vname.add("ְ��");
			vname.add("�ܽ����̶�");
			vname.add("רҵ����");
			vname.add("��ͥסַ");
			vname.add("��ϵ�绰");
			vname.add("��������");
			vname.add("��ǰ״̬");
			vname.add("��ע");	
			DefaultTableModel model = new DefaultTableModel(v,vname);	
			table.setModel(model);
			
			table.updateUI();

			dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
	}

}
