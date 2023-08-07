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

import tr.com.batuyazilim.dal.YetkilerDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.PersonelContract;
import tr.com.batuyazilim.types.YetkilerContract;

public class YetkiDüzenleFE extends JDialog implements FeInterfaces{

	public YetkiDüzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenle"));
		add(panel);
		setTitle("Yetki Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel yetkiLabel = new JLabel("Yetkiler: ",JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkilerBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkilerBox);
		JLabel adiLabel = new JLabel("Yetki Adı: ",JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JButton kaybetButton = new JButton("Kaydet");
		panel.add(kaybetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		yetkilerBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract contract = (YetkilerContract) yetkilerBox.getSelectedItem();
				adiField.setText(contract.getAdi());
				
				
			}
		});
		kaybetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract contract = (YetkilerContract) yetkilerBox.getSelectedItem();
				
				contract.setAdi(adiField.getText());
				new YetkilerDAL().Update(contract);
				JOptionPane.showMessageDialog(null, contract.getAdi()+" adlı yetki başarılı bir şekilde düzenlenmiştir.");
				
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
