package tr.com.batuyazilim.utilities;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import tr.com.batuyazilim.dal.AccountsDAL;
import tr.com.batuyazilim.fe.KategoriDuzenleFE;
import tr.com.batuyazilim.fe.KategoriEkleFE;
import tr.com.batuyazilim.fe.LoginFE;
import tr.com.batuyazilim.fe.MusteriDuzenleFE;
import tr.com.batuyazilim.fe.MusteriEkleFE;
import tr.com.batuyazilim.fe.PersonelDuzenleFE;
import tr.com.batuyazilim.fe.PersonelEkleFE;
import tr.com.batuyazilim.fe.SifreDuzenleFE;
import tr.com.batuyazilim.fe.SifreIslemleriFE;
import tr.com.batuyazilim.fe.UrunDuzenleFE;
import tr.com.batuyazilim.fe.UrunEkleFE;
import tr.com.batuyazilim.fe.YetkiDüzenleFE;
import tr.com.batuyazilim.fe.YetkiEkleFE;
import tr.com.batuyazilim.types.PersonelContract;

public class MenulerCom {
	
	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cikisItem = new JMenuItem("Çıkış");
		dosyaMenu.add(cikisItem);
		
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		JMenuItem urunDuzenleItem = new JMenuItem("Ürünü Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategori Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		
		
		JMenu personellerMenu = new JMenu("Personel");
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem = new JMenuItem("Şifre Belirleme");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();
		
		JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
		personellerMenu.add(personelDuzenleItem);
		JMenuItem yetkiDuzenleItem = new JMenuItem("Yetki Düzenle");
		personellerMenu.add(yetkiDuzenleItem);
		JMenuItem sifreDuzenlemeItem = new JMenuItem("Şifre Düzenle");
		personellerMenu.add(sifreDuzenlemeItem);
		
		JMenu musterilerMenu = new JMenu("Müşteriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("Müşteri Ekle");
		musterilerMenu.add(musteriEkleItem);
		musterilerMenu.addSeparator();
		
		JMenuItem musteriDuzenleItem = new JMenuItem("Müşteri Düzenle");
		musterilerMenu.add(musteriDuzenleItem);
		
		PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
		
		if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()==2) {
			personellerMenu.hide();
		}else if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()==3) {
			musterilerMenu.hide();
			personellerMenu.hide();
			urunlerMenu.hide();
		}
		
		
		cikisItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable () {
					
					@Override
					public void run() {
						new UrunEkleFE();
					}
				});
			}
		});
		
		kategoriEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFE();
				
			}
		});
		
		urunDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new UrunDuzenleFE();
						
					}
				});
				
			}
		});
		
		kategoriDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new KategoriDuzenleFE();
						
					}
				});
				
			}
		});
		
		personelEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new PersonelEkleFE();
						
					}
				});
				
			}
		});
		
		yetkiEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new YetkiEkleFE();
						
					}
				});
				
			}
		});
		
		sifreBelirleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new SifreIslemleriFE();
				
			}
		});
		personelDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PersonelDuzenleFE();
				
			}
		});
		yetkiDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new YetkiDüzenleFE();
			}
		});
		sifreDuzenlemeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SifreDuzenleFE();
				
			}
		});
		
		musteriEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new MusteriEkleFE();
						
					}
				});
				
			}
		});
		musteriDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MusteriDuzenleFE();
				
			}
		});
		
		
		return bar;
	}

}
