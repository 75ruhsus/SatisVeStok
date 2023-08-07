package tr.com.batuyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;

import tr.com.batuyazilim.dal.AccountsDAL;
import tr.com.batuyazilim.dal.PersonelDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.PersonelContract;

public class LoginFE extends JDialog implements FeInterfaces{

	public static JComboBox emailBox;
	public LoginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Lütfen sisteme giriş yapmak için bilgilerinizi giriniz."));
		add(panel);
		setTitle("Lütfen Giriş Yapınız.");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel emailLabel = new JLabel("Email: ",JLabel.RIGHT);
		panel.add(emailLabel);
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		JLabel passwordLabel = new JLabel("Şifre: ",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Giriş Yap");
		panel.add(loginButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
				String sifre = passwordField.getText();
				
				if (new AccountsDAL().GetPersonelIdVeSifre(contract.getId(), sifre).getId()!=0) {
					
					new AnaPencereFE();
					
					hide();
					
				}else {
					JOptionPane.showMessageDialog(null, "Giriş Başarısız.");
				}
				
			}
		});
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});

		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
