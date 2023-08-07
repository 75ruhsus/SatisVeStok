package tr.com.batuyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tr.com.batuyazilim.core.ObjectHelper;
import tr.com.batuyazilim.interfaces.DALInterfaces;
import tr.com.batuyazilim.types.AccountsContact;

public class AccountsDAL extends ObjectHelper implements DALInterfaces<AccountsContact> {

	@Override
	public void Insert(AccountsContact entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Accounts(PersonelId, YetkiId, Sifre) VALUES(" + entity.getPersonelId()
					+ "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public AccountsContact GetPersonelIdVeSifre(int personelId,String sifre){
		
		AccountsContact contract = new AccountsContact();
		List<AccountsContact> listele = new ArrayList<AccountsContact>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId ="+personelId+" && Sifre='"+sifre.trim()+"'");
			
			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return contract;
	}
public AccountsContact GetYetkiId(int personelId){
		
		AccountsContact contract = new AccountsContact();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId ="+personelId+"");
			
			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return contract;
	}


	@Override
	public List<AccountsContact> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountsContact Delete(AccountsContact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsContact entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("UPDATE accounts SET PersonelId="+entity.getPersonelId()+", Sifre='"+entity.getSifre()+"', YetkiId="+ entity.getYetkiId() +" WHERE PersonelId="+entity.getPersonelId()+"");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<AccountsContact> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
