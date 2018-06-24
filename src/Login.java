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
		frame.getContentPane().setFont(new Font("宋体", Font.PLAIN, 11));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 554, 317);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u8FDB\u5165\u767B\u5F55\u7CFB\u7EDF\u9875\u9762");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 25));
		lblNewLabel.setBounds(134, 10, 303, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel Usernamelabel = new JLabel("\u7528\u6237\u540D");
		Usernamelabel.setFont(new Font("宋体", Font.BOLD, 15));
		Usernamelabel.setBounds(127, 65, 88, 29);
		frame.getContentPane().add(Usernamelabel);
		
		UserField = new JTextField();
		UserField.setBounds(197, 65, 123, 29);
		frame.getContentPane().add(UserField);
		UserField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_1.setBounds(137, 112, 68, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		PasswdField = new JTextField();
		PasswdField.setBounds(197, 112, 123, 29);
		frame.getContentPane().add(PasswdField);
		PasswdField.setColumns(10);
		
		JButton ResetButton = new JButton("重置密码");
		ResetButton.setForeground(SystemColor.desktop);
		ResetButton.setBackground(SystemColor.activeCaptionBorder);
		ResetButton.setFont(new Font("宋体", Font.PLAIN, 14));
		ResetButton.setBounds(270, 232, 109, 23);
		frame.getContentPane().add(ResetButton);
		ResetButton.addActionListener(this);//为按钮设置监听
		
		JLabel lblNewLabel_2 = new JLabel("\u5982\u60F3\u4FEE\u6539\u5BC6\u7801\uFF0C\u8BF7\u70B9\u51FB\u91CD\u7F6E\u5BC6\u7801\u6309\u94AE");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(39, 232, 234, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton LoginButton = new JButton("\u767B\u5F55");
		LoginButton.setBackground(new Color(105, 105, 105));
		LoginButton.setForeground(SystemColor.activeCaptionText);
		LoginButton.setFont(new Font("宋体", Font.BOLD, 14));
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
		if(e.getActionCommand().equals("登录")){
			System.out.println("actionPerformed(). 登录");
			//判断用户名，密码是否为空
			while(!UserField.getText().isEmpty() || !PasswdField.getText().isEmpty()) {
				String Passwd =  QueryPasswd(UserField.getText());
				String Authority =  QueryAuthority(UserField.getText());
				if(PasswdField.getText().equals (Passwd))//判断密码是否正确
				{
					System.out.println("登录成功");
					if(Authority.equals("管理员")) {
					Summarize summarize = new Summarize();
					summarize.frame.setVisible(true);
					}else {
						Employee employee = new Employee();
						employee.queryProcess(UserField.getText());
						employee.frame.setVisible(true);
					}
				} else {
				System.out.println("用户名或密码错误");
				break;
				}
				break;
			}	
			}
		if(e.getActionCommand().equals("重置密码")) {
			
		}
		
	}
				
	public String QueryPasswd(String sQueryField) {
		String v = null;
		try{
			// 建立查询条件
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
					"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
			}
		return v;
	}

	
	public String QueryAuthority(String sQueryField) {
		String v = null;
		try{
			// 建立查询条件
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
					"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
			}
		return v;
	}
	
		
	}

