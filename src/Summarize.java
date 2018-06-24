import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Summarize extends JFrame implements ActionListener{

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Summarize window = new Summarize();
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
	public Summarize() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("华文行楷", Font.BOLD, 20));
		frame.setBounds(100, 100, 557, 530);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u8FDB\u5165\u7BA1\u7406\u5458\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 25));
		lblNewLabel.setBounds(153, 10, 314, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("员工信息总表");
		btnNewButton.setFont(new Font("华文行楷", Font.BOLD, 20));
		btnNewButton.setBounds(140, 104, 249, 43);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		
		JButton btnNewButton_1 = new JButton("人事变更记录表");
		btnNewButton_1.setFont(new Font("华文行楷", Font.BOLD, 20));
		btnNewButton_1.setBounds(140, 217, 249, 43);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		JButton btnNewButton_2 = new JButton("部门信息表");
		btnNewButton_2.setFont(new Font("华文行楷", Font.BOLD, 20));
		btnNewButton_2.setBounds(143, 311, 246, 43);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		JButton btnNewButton_4 = new JButton("退出");
		btnNewButton_4.setBounds(393, 439, 93, 29);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_4}));
		btnNewButton_4.addActionListener(this);
	
	}
	PersonalSystem personal = new PersonalSystem();
	PersonChange personchange = new PersonChange();
	Departmentinfo department = new Departmentinfo();
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("员工信息总表")) {
			personal.frame.setVisible(true);
		}
		if(e.getActionCommand().equals("人事变更记录表")) {
			personchange.frame.setVisible(true);
		}
		if(e.getActionCommand().equals("部门信息表")) {
			department.frame.setVisible(true);
		}
		if(e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
		
	}
}
