import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ResetPasswd extends JFrame implements ActionListener {

	JFrame frame;
	private JTextField UernameField;
	private JTextField oldPasswdField;
	private JTextField newPasswdField;
	private JTextField renewPasswdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPasswd window = new ResetPasswd();
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
	public ResetPasswd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4FEE\u6539\u5BC6\u7801");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u8FDB\u5165\u91CD\u7F6E\u5BC6\u7801\u9875\u9762");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 20));
		lblNewLabel.setBounds(101, 10, 224, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("请输入用户名");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(74, 53, 96, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		UernameField = new JTextField();
		UernameField.setBounds(195, 53, 165, 20);
		frame.getContentPane().add(UernameField);
		UernameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("请输入旧密码");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(74, 83, 96, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		oldPasswdField = new JTextField();
		oldPasswdField.setBounds(195, 84, 165, 19);
		frame.getContentPane().add(oldPasswdField);
		oldPasswdField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("请输入新密码");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(74, 113, 96, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		newPasswdField = new JTextField();
		newPasswdField.setBounds(195, 114, 165, 19);
		frame.getContentPane().add(newPasswdField);
		newPasswdField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("请再次输入新密码");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(74, 143, 112, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		renewPasswdField = new JTextField();
		renewPasswdField.setBounds(195, 144, 165, 19);
		frame.getContentPane().add(renewPasswdField);
		renewPasswdField.setColumns(10);
		
		JButton ascertainButton = new JButton("确定");
		ascertainButton.setFont(new Font("宋体", Font.PLAIN, 14));
		ascertainButton.setBounds(74, 193, 66, 25);
		frame.getContentPane().add(ascertainButton);
		ascertainButton.addActionListener(this);
		
		
		JButton exitButton = new JButton("退出");
		exitButton.setFont(new Font("宋体", Font.PLAIN, 14));
		exitButton.setBounds(201, 193, 66, 25);
		frame.getContentPane().add(exitButton);
		exitButton.addActionListener(this);
	}
	/*final JLabel Label = new JLabel();
	final JDialog dialog = new JDialog(frame,"提示");//定义一个JDialog对话框
	dialog.setSize(50,50);//设置对话框大小
	dialog.setLocation(350,250);
	dialog.setLayout(new FlowLayout());
	final JButton btn3 = new JButton("确定");*/
	
	
	
	
	Login login = new Login();
	DbProcess dbProcess = new DbProcess();
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("确定")){//获取当前的命令
			String Passwd = login.QueryPasswd(UernameField.getText());//进入数据库查看当前用户的密码
			if(oldPasswdField.getText().equals (Passwd)) {//判断旧密码是否输入正确
				String PASSWD = newPasswdField.getText().trim();
				String sql = "update personinformation set PASSWD = '";
				sql = sql + PASSWD + "' WHERE NAME = '" + UernameField.getText() +"';";
						try{
							if (dbProcess.executeUpdate(sql) < 1) {
								System.out.println("updateProcess(). update database failed.");
							}
						}catch(Exception e1){
							System.out.println("e = " + e1);
							JOptionPane.showMessageDialog(null,
								"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
						}
				System.out.println("修改成功");
			}else {
				JOptionPane.showMessageDialog(null, "密码或用户名错误","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
	}
	
	
}
