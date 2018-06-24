import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPanel;

public class Login extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField UserField;
	private JTextField PasswdField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 11));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 554, 317);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u8FDB\u5165\u767B\u5F55\u7CFB\u7EDF\u9875\u9762");
		lblNewLabel.setFont(new Font("�����п�", Font.BOLD, 25));
		lblNewLabel.setBounds(134, 10, 303, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel Usernamelabel = new JLabel("\u7528\u6237\u540D");
		Usernamelabel.setFont(new Font("����", Font.BOLD, 15));
		Usernamelabel.setBounds(127, 65, 88, 29);
		frame.getContentPane().add(Usernamelabel);
		
		UserField = new JTextField();
		UserField.setBounds(197, 65, 123, 29);
		frame.getContentPane().add(UserField);
		UserField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_1.setBounds(137, 112, 68, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		PasswdField = new JTextField();
		PasswdField.setBounds(197, 112, 123, 29);
		frame.getContentPane().add(PasswdField);
		PasswdField.setColumns(10);
		
		JButton ResetButton = new JButton("��������");
		ResetButton.setForeground(SystemColor.desktop);
		ResetButton.setBackground(SystemColor.activeCaptionBorder);
		ResetButton.setFont(new Font("����", Font.PLAIN, 14));
		ResetButton.setBounds(270, 232, 109, 23);
		frame.getContentPane().add(ResetButton);
		ResetButton.addActionListener(this);//Ϊ��ť���ü���
		
		JLabel lblNewLabel_2 = new JLabel("\u5982\u60F3\u4FEE\u6539\u5BC6\u7801\uFF0C\u8BF7\u70B9\u51FB\u91CD\u7F6E\u5BC6\u7801\u6309\u94AE");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(39, 232, 234, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton LoginButton = new JButton("\u767B\u5F55");
		LoginButton.setBackground(new Color(105, 105, 105));
		LoginButton.setForeground(SystemColor.activeCaptionText);
		LoginButton.setFont(new Font("����", Font.BOLD, 14));
		LoginButton.setBounds(163, 165, 157, 29);
		frame.getContentPane().add(LoginButton);
		LoginButton.addActionListener(this);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(522, 274, -515, -265);
		frame.getContentPane().add(panel);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, Usernamelabel, UserField, lblNewLabel_1, PasswdField, LoginButton, lblNewLabel_2}));
	}
	
	
	PersonalSystem personalsystem = new PersonalSystem();
	DbProcess dbProcess = new DbProcess();
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��¼")){
			System.out.println("actionPerformed(). ��¼");
			//�ж��û����������Ƿ�Ϊ��
			while(!UserField.getText().isEmpty() || !PasswdField.getText().isEmpty()) {
				String Passwd =  QueryPasswd(UserField.getText());
				String Authority =  QueryAuthority(UserField.getText());
				if(PasswdField.getText().equals (Passwd))//�ж������Ƿ���ȷ
				{
					System.out.println("��¼�ɹ�");
					if(Authority.equals("����Ա")) {
					Summarize summarize = new Summarize();
					summarize.frame.setVisible(true);
					}else {
						Employee employee = new Employee();
						employee.queryProcess(UserField.getText());
						employee.frame.setVisible(true);
					}
				} else {
				System.out.println("�û������������");
				break;
				}
				break;
			}	
			}
		if(e.getActionCommand().equals("��������")) {
			
		}
		
	}
				
	public String QueryPasswd(String sQueryField) {
		String v = null;
		try{
			// ������ѯ����
			String sql = "select * from personinformation where NAME";
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
				System.out.println("queryProcess(). sql = " + sql);
				
				dbProcess.connect();
				ResultSet rs = dbProcess.executeQuery(sql);
				while(rs.next()){
				v = rs.getString("PASSWD");
				}
			}catch(Exception e){
				System.out.println("e = " + e);
				JOptionPane.showMessageDialog(null,
					"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}
		return v;
	}

	
	public String QueryAuthority(String sQueryField) {
		String v = null;
		try{
			// ������ѯ����
			String sql = "select * from personinformation where NAME";
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
				System.out.println("queryProcess(). sql = " + sql);
				
				dbProcess.connect();
				ResultSet rs = dbProcess.executeQuery(sql);
				while(rs.next()){
				v = rs.getString("AUTHORITY");
				}
			}catch(Exception e){
				System.out.println("e = " + e);
				JOptionPane.showMessageDialog(null,
					"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}
		return v;
	}
	
		
	}

