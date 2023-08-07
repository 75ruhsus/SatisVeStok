package tr.com.batuyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

import tr.com.batuyazilim.dal.AccountsDAL;
import tr.com.batuyazilim.dal.PersonelDAL;
import tr.com.batuyazilim.dal.YetkilerDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.AccountsContact;
import tr.com.batuyazilim.types.PersonelContract;
import tr.com.batuyazilim.types.YetkilerContract;

public class SifreDuzenleFE extends JDialog implements FeInterfaces{

	public SifreDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şifre Düzenleme"));
		
		add(panel);
		setTitle("Şifre Düzenleme");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel personelLabel = new JLabel("Personel Seç: ",JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelBox);
		JLabel yetkiLabel = new JLabel("Yetki Seç: ",JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkiBox);
		JLabel passwordLabel = new JLabel("Şifre Belirle: ",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel = new JLabel("Şifre Tekrar: ",JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrar = new JPasswordField(10);
		panel.add(passTekrar);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		personelBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					
			}
		});
		
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AccountsContact contract = new AccountsContact();
				PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
				YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();
				
				if (passField.getText().equals(passTekrar.getText())) {
					
					contract.setPersonelId(pContract.getId());
					contract.setSifre(passField.getText());
					contract.setYetkiId(yContract.getId());
					
					new AccountsDAL().Update(contract);
					JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi()+" adlı kişinin şifresi başarıyla değişmiştir.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Şifreler uyuşmuyor. Tekrar deneyiniz.");
				}
				
				}
			}
		);
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
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
