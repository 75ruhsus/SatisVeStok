package tr.com.batuyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageTranscoder;
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

import com.toedter.calendar.JDateChooser;

import tr.com.batuyazilim.dal.KategoriDAL;
import tr.com.batuyazilim.dal.UrunlerDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.KategoriContract;
import tr.com.batuyazilim.types.UrunlerContract;

public class UrunDuzenleFE extends JDialog implements FeInterfaces{

	public UrunDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
	
	panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Düzenleme Alanı"));
	add(panel);
	setTitle("Ürün Düzenle");
	pack();
	setModalityType(DEFAULT_MODALITY_TYPE);
	setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
	JPanel panel = new JPanel(new GridLayout(5, 2));
	
	JLabel urunadiLabel = new JLabel("Ürün Seç ", JLabel.RIGHT);
	panel.add(urunadiLabel);
	JComboBox urunadiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
	panel.add(urunadiBox);
	JLabel adiLabel = new JLabel("Ürün Adı: ", JLabel.RIGHT);
	panel.add(adiLabel);
	JTextField adiField = new JTextField(10);
	panel.add(adiField);
	JLabel kategoriLabel = new JLabel("Kategori Seç: ", JLabel.RIGHT);
	panel.add(kategoriLabel);
	JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
	panel.add(kategoriBox);

	JLabel fiyatLabel = new JLabel("Fiyat: ", JLabel.RIGHT);
	panel.add(fiyatLabel);
	JTextField fiyatField = new JTextField(10);
	panel.add(fiyatField);

	JButton kaydetButton = new JButton("Kaydet");
	panel.add(kaydetButton);
	JButton iptalButton = new JButton("İptal");
	panel.add(iptalButton);
	
	urunadiBox.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
				UrunlerContract casContract = (UrunlerContract) urunadiBox.getSelectedItem();
				int secilenUrunKatId = casContract.getKategoriId();
				
				adiField.setText(casContract.getAdi());
			
				for (int i = 0; i < kategoriBox.getItemCount(); i++) {
					KategoriContract item = (KategoriContract) kategoriBox.getItemAt(i);
					  if(item.getId() == secilenUrunKatId)
						  kategoriBox.setSelectedItem(item);
				}
				fiyatField.setText(String.valueOf(casContract.getFiyat()));
		}
	});
	kaydetButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			UrunlerContract casContract = (UrunlerContract) urunadiBox.getSelectedItem();
			
			casContract.setAdi(adiField.getText());
			casContract.setKategoriId(((KategoriContract) kategoriBox.getSelectedItem()).getId());
			casContract.setFiyat(Float.valueOf(fiyatField.getText()));
			
			new UrunlerDAL().Update(casContract);
			JOptionPane.showMessageDialog(null, casContract.getAdi()+" adlı ürün güncellenmiştir.");
			
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