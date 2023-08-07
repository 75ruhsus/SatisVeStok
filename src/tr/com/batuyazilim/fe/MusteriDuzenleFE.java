package tr.com.batuyazilim.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.batuyazilim.dal.MusteriDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.MusteriContract;

public class MusteriDuzenleFE extends JDialog implements FeInterfaces{

	public MusteriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Düzenle"));
		
		add(panel);
		setTitle("Müşteri Düzenle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(5, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		
		JLabel musteriLabel = new JLabel("Müşteriler: ",JLabel.RIGHT);
		fieldPanel.add(musteriLabel);
		JComboBox musteriBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		fieldPanel.add(musteriBox);
		JLabel adiSoyadiLabel = new JLabel("Adı Soyadı: ",JLabel.RIGHT);
		fieldPanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldPanel.add(adiSoyadiField);
		JLabel telefonLabel = new JLabel("Telefon: ",JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		JLabel sehirSecLabel = new JLabel("Şehir Seç: ",JLabel.RIGHT);
		fieldPanel.add(sehirSecLabel);
		JComboBox sehirlerBox = new JComboBox();
		fieldPanel.add(sehirlerBox);
		sehirlerBox.addItem("-Şehir Seçiniz-");
		sehirlerBox.addItem("Adana");
		sehirlerBox.addItem("Adıyaman");
		sehirlerBox.addItem("Afyon");
		sehirlerBox.addItem("Ağrı");
		sehirlerBox.addItem("Amasya");
		sehirlerBox.addItem("Ankara");
		sehirlerBox.addItem("Antalya");
		sehirlerBox.addItem("Artvin");
		sehirlerBox.addItem("Aydın");
		sehirlerBox.addItem("Balıkesir");
		sehirlerBox.addItem("Bilecik");
		sehirlerBox.addItem("Bingöl");
		sehirlerBox.addItem("Bitlis");
		sehirlerBox.addItem("Bolu");
		sehirlerBox.addItem("Burdur");
		sehirlerBox.addItem("Bursa");
		sehirlerBox.addItem("Çanakkale");
		sehirlerBox.addItem("Çankırı");
		sehirlerBox.addItem("Çorum");
		sehirlerBox.addItem("Denizli");
		sehirlerBox.addItem("Diyarbakır");
		sehirlerBox.addItem("Edirne");
		sehirlerBox.addItem("Elazığ");
		sehirlerBox.addItem("Erzincan");
		sehirlerBox.addItem("Erzurum");
		sehirlerBox.addItem("Eskişehir");
		sehirlerBox.addItem("Gaziantep");
		sehirlerBox.addItem("Giresun");
		sehirlerBox.addItem("Gümüşhane");
		sehirlerBox.addItem("Hakkari");
		sehirlerBox.addItem("Hatay");
		sehirlerBox.addItem("Isparta");
		sehirlerBox.addItem("İçel");
		sehirlerBox.addItem("İstanbul");
		sehirlerBox.addItem("İzmir");
		sehirlerBox.addItem("Kars");
		sehirlerBox.addItem("Kastamonu");
		sehirlerBox.addItem("Kayseri");
		sehirlerBox.addItem("Kırklareli");
		sehirlerBox.addItem("Kırşehir");
		sehirlerBox.addItem("Kocaeli");
		sehirlerBox.addItem("Konya");
		sehirlerBox.addItem("Kütahya");
		sehirlerBox.addItem("Malatya");
		sehirlerBox.addItem("Manisa");
		sehirlerBox.addItem("Kahramanmaraş");
		sehirlerBox.addItem("Mardin");
		sehirlerBox.addItem("Muğla");
		sehirlerBox.addItem("Muş");
		sehirlerBox.addItem("Nevşehir");
		sehirlerBox.addItem("Niğde");
		sehirlerBox.addItem("Ordu");
		sehirlerBox.addItem("Rize");
		sehirlerBox.addItem("Sakarya");
		sehirlerBox.addItem("Samsun");
		sehirlerBox.addItem("Siirt");
		sehirlerBox.addItem("Sinop");
		sehirlerBox.addItem("Sivas");
		sehirlerBox.addItem("Tekirdağ");
		sehirlerBox.addItem("Tokat");
		sehirlerBox.addItem("Trabzon");
		sehirlerBox.addItem("Tunceli");
		sehirlerBox.addItem("Şanlıurfa");
		sehirlerBox.addItem("Uşak");
		sehirlerBox.addItem("Van");
		sehirlerBox.addItem("Yozgat");
		sehirlerBox.addItem("Zonguldak");
		sehirlerBox.addItem("Aksaray");
		sehirlerBox.addItem("Bayburt");
		sehirlerBox.addItem("Karaman");
		sehirlerBox.addItem("Kırıkkale");
		sehirlerBox.addItem("Batman");
		sehirlerBox.addItem("Şırnak");
		sehirlerBox.addItem("Bartın");
		sehirlerBox.addItem("Ardahan");
		sehirlerBox.addItem("Iğdır");
		sehirlerBox.addItem("Yalova");
		sehirlerBox.addItem("Karabük");
		sehirlerBox.addItem("Kilis");
		sehirlerBox.addItem("Osmaniye");
		sehirlerBox.addItem("Düzce");
		
		
		JTextArea adresArea = new JTextArea(5,1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		buttonPanel.add(iptalButton);
		
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		musteriBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract = (MusteriContract) musteriBox.getSelectedItem();
				adiSoyadiField.setText(contract.getAdiSoyadi());
				telefonField.setText(contract.getTelefon());
				sehirlerBox.setSelectedIndex(contract.getSehirId());
				adresArea.setText(contract.getAdres());
			}
		});
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract = (MusteriContract) musteriBox.getSelectedItem();
				
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setSehirId(sehirlerBox.getSelectedIndex());
				contract.setAdres(adresArea.getText());
				
				new MusteriDAL().Update(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlı müşteri başarılı bir şekilde değiştirilmiştir.");

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
