package tr.com.batuyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.batuyazilim.core.ObjectHelper;
import tr.com.batuyazilim.interfaces.DALInterfaces;
import tr.com.batuyazilim.types.SatisContract;
import tr.com.batuyazilim.complex.types.SatisContractComplex;

public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisContract> {

	@Override
	public void Insert(SatisContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Satis(UrunId, MusteriId, Tarih, Adet, PersonelId) VALUES("
					+ entity.getUrunId() + "," + entity.getMusteriId() + ",'" + entity.getTarih() + "',"
					+ entity.getAdet() + "," + entity.getPersonelId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<SatisContractComplex> GetAllSatis() {
		List<SatisContractComplex> dataContract = new ArrayList<SatisContractComplex>();
		Connection connection = getConnection();
		SatisContractComplex contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT satis.Id, personel.AdiSoyadi, musteri.AdiSoyadi, Adi, Adet, satis.Tarih FROM satis LEFT JOIN musteri on satis.MusteriId = musteri.Id LEFT JOIN urunler ON satis.UrunId = urunler.Id LEFT JOIN personel on satis.PersonelId = personel.Id ORDER BY satis.Id DESC");
			
			while(rs.next()) {
				contract = new SatisContractComplex();
				contract.setId(rs.getInt("satis.Id"));
				contract.setMusteriAdi(rs.getString("musteri.AdiSoyadi"));
				contract.setPersonelAdi(rs.getString("personel.AdiSoyadi"));
				contract.setTarih(rs.getString("satis.Tarih"));
				contract.setUrunAdi(rs.getString("Adi"));
				contract.setAdet(rs.getInt("Adet"));
				dataContract.add(contract);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public List<SatisContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisContract Delete(SatisContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SatisContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
