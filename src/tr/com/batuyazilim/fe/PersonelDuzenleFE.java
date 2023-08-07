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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.batuyazilim.dal.PersonelDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.PersonelContract;

public class PersonelDuzenleFE extends JDialog implements FeInterfaces{

	public PersonelDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel Düzenle"));
		
		add(panel);
		setTitle("Personel Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
JPanel panel = new JPanel(new GridLayout(4, 2));
		
		JLabel kategoriLabel = new JLabel("Personel: ",JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelBox);
		JLabel adiSoyadiLabel = new JLabel("Adı Soyadı: ",JLabel.RIGHT);
		panel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(10);
		panel.add(adiSoyadiField);
		JLabel eMailLabel = new JLabel("Email: ",JLabel.RIGHT);
		panel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		panel.add(eMailField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		kaydetButton.setEnabled(false);
		
		personelBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = (PersonelContract) personelBox.getSelectedItem();
				adiSoyadiField.setText(contract.getAdiSoyadi());
				eMailField.setText(contract.getEmail());
				kaydetButton.setEnabled(true);
				
			}
		});
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = (PersonelContract) personelBox.getSelectedItem();
				
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setEmail(eMailField.getText());
				
				new PersonelDAL().Update(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlı personel güncellenmiştir.");
				
			}
		});
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
