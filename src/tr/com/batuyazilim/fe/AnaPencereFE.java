package tr.com.batuyazilim.fe;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.batuyazilim.complex.types.SatisContractComplex;
import tr.com.batuyazilim.complex.types.StokContractComplex;
import tr.com.batuyazilim.complex.types.StokContractToplamComplex;
import tr.com.batuyazilim.dal.MusteriDAL;
import tr.com.batuyazilim.dal.SatisDAL;
import tr.com.batuyazilim.dal.StokDAL;
import tr.com.batuyazilim.dal.UrunlerDAL;
import tr.com.batuyazilim.interfaces.FeInterfaces;
import tr.com.batuyazilim.types.MusteriContract;
import tr.com.batuyazilim.types.PersonelContract;
import tr.com.batuyazilim.types.SatisContract;
import tr.com.batuyazilim.types.StokContract;
import tr.com.batuyazilim.types.UrunlerContract;
import tr.com.batuyazilim.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces{

	public AnaPencereFE() {
		initPencere();
	}
	
	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();
		
		add(panel);
		setJMenuBar(bar);
		setTitle("Satış ve Stok Programı");
		setSize(1200, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JTabbedPane pane = initTabs();
		panel.add(pane,BorderLayout.CENTER);
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/stock.png");
		ImageIcon icon2 = new ImageIcon("icons/stock.png");
		
		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		
		/*Stok İtemleri*/
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel stokSolAltPanel = new JPanel();
		
		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
		
		Object [] stokKolonlar = {"Id","Personel Adı","Ürün Adı","Adeti","Tarihi","Toplam"};
		DefaultTableModel model = new DefaultTableModel(stokKolonlar,0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);
		
		for(StokContractComplex contract : new StokDAL().getAllStok()) {
			model.addRow(contract.getVeriler());
		}
		JLabel stokUrunAdiLabel = new JLabel("Ürün Adı: ",JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray()); 
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel = new JLabel("Adet: ",JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi: ",JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolUstPanel.add(stokTarihi);
		
		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokToplamButton = new JButton("Stok Toplam Ürün");
		stokSolUstPanel.add(stokToplamButton);
		
		stokToplamButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for(int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for(StokContractToplamComplex toplam : new StokDAL().getToplamStok()) {
					model.addRow(toplam.getVeriler());
				}
				
			}
		});
		
		stokYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = model.getRowCount();
				for(int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for(StokContractComplex compcontract : new StokDAL().getAllStok()) {
					model.addRow(compcontract.getVeriler());
				}
				
			}
		});
		stokEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(stokTarihi.getDate());
				
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new StokDAL().Insert(contract);
				
				JOptionPane.showMessageDialog(null, uContract.getAdi() +" adlı ürün başarılı bir şekilde eklenmiştir.");
				
				int satir = model.getRowCount();
				for(int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for(StokContractComplex compcontract : new StokDAL().getAllStok()) {
					model.addRow(compcontract.getVeriler());
				}
			}
		});
		
		/*Satış İtemleri*/
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(6, 2));
		JPanel satisSagAltPanel = new JPanel();
		
		satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satış İşlemleri"));
		
		Object [] satisKolonlar = {"Id","Müşteri Adı","Personel Adı","Ürün Adı","Adet","Tarihi"};
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar,0);
		JTable satistable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satistable);
		JLabel musteriLabel = new JLabel("Müşteri Adı: ",JLabel.RIGHT);
		satisSagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		satisSagUstPanel.add(musteriAdiBox);
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adı: ",JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray()); 
		satisSagUstPanel.add(satisUrunAdiBox);
		JLabel satisAdetLabel = new JLabel("Adet: ",JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("Satış Tarihi: ",JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);
		
		JButton satisEkleButton = new JButton("Satış Yap");
		satisSagUstPanel.add(satisEkleButton);
		for(SatisContractComplex yenileContract : new SatisDAL().GetAllSatis()) {
			satisModel.addRow(yenileContract.getVeriler());
		}
		satisEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
				MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();
				SatisContract contract = new SatisContract();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(satisTarihi.getDate());
				
				contract.setMusteriId(mContract.getId());
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				contract.setTarih(date);
				
				new SatisDAL().Insert(contract);
				StokContract stokContract = new StokContract();
				//stokContract.setMusteriId(mContract.getId());
				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				stokContract.setTarih(date);
				
				new StokDAL().Insert(stokContract);
				JOptionPane.showMessageDialog(null, mContract.getAdiSoyadi()+" adlı müşteriye satış gerçekleştirilmiştir ve "+uContract.getAdi()+" adlı üründen stoktan "+contract.getAdet() +" adet çıkarılmıştır.");
				int satir = satisModel.getRowCount();
				for(int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				for(SatisContractComplex yenileContract : new SatisDAL().GetAllSatis()) {
					satisModel.addRow(yenileContract.getVeriler());
				}
			}
		});
		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		satisYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = satisModel.getRowCount();
				for(int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				for(SatisContractComplex compcontract : new SatisDAL().GetAllSatis()) {
					satisModel.addRow(compcontract.getVeriler());
				}
				
			}
		});
		
		
		stokPanel.add(stokSolPanel,BorderLayout.WEST);
		stokPanel.add(stokTablePane,BorderLayout.CENTER);
		
		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);
		
		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);
		
		
		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);
		
		pane.addTab("Stoklar ", icon, stokPanel, "Does nothing");
		pane.addTab("Satışlar ", icon2, satisPanel, "yazı");
		
		return pane;
	}

}
