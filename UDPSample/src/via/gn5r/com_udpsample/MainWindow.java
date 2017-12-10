package via.gn5r.com_udpsample;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("UDPアプリケーション");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblHelloWorld = new JLabel("接続先IPAddress:");
		lblHelloWorld.setBounds(12, 16, 131, 13);
		frame.getContentPane().add(lblHelloWorld);

		textField = new JTextField();
		textField.setBounds(120, 16, 104, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		final JLabel label = new JLabel("接続先:");
		label.setBounds(12, 41, 212, 13);

		JButton button = new JButton("接続");
		button.setBounds(230, 12, 108, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				label.setText("接続先:" + textField.getText());
				textField.setText("");
			}
		});
		frame.getContentPane().add(button);

		frame.getContentPane().add(label);
	}
}
