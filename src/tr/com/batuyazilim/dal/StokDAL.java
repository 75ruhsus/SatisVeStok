package tr.com.batuyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.batuyazilim.complex.types.StokContractComplex;
import tr.com.batuyazilim.core.ObjectHelper;
import tr.com.batuyazilim.interfaces.DALInterfaces;
import tr.com.batuyazilim.types.KategoriContract;
import tr.com.batuyazilim.types.StokContract;
import tr.com.batuyazilim.complex.types.StokContractToplamComplex;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract>{

	@Override
	public void Insert(StokContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Stok(PersonelId,UrunId,Tarih,Adet) VALUES("+entity.getPersonelId()+","+entity.getUrunId()+",'"+entity.getTarih()+"',"+entity.getAdet()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<StokContractComplex> getAllStok(){
		List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stok.Id, AdiSoyadi, Adi, stok.Adet, stok.Tarih FROM stok LEFT JOIN urunler ON stok.UrunId = urunler.Id LEFT JOIN personel ON stok.PersonelId = personel.Id ORDER BY stok.Id DESC");
			while (resultSet.next()) {
				contract = new StokContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("stok.Adet"));
				contract.setTarih(resultSet.getString("stok.Tarih"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	
	public List<StokContractToplamComplex> getToplamStok(){
		List<StokContractToplamComplex> datacontract = new ArrayList<StokContractToplamComplex>();
		Connection connection = getConnection();
		StokContractToplamComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(Adet) as toplam, stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = personel.Id GROUP BY UrunId");
			while (resultSet.next()) {
				contract = new StokContractToplamComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("stok.Adet"));
				contract.setTarih(resultSet.getString("stok.Tarih"));
				contract.setToplam(resultSet.getInt("toplam"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	
	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
