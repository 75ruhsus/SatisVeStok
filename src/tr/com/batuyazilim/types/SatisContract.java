package tr.com.batuyazilim.types;

import java.util.Date;

public class SatisContract {

	private int id;
	private int musteriId;
	private int personelId;
	private int adet;
	private String tarih;
	private int urunId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMusteriId() {
		return musteriId;
	}

	public void setMusteriId(int musteriId) {
		this.musteriId = musteriId;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public int getUrunId() {
		return urunId;
	}

	public void setUrunId(int urunId) {
		this.urunId = urunId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + musteriId + " " + personelId + " " + urunId + " " + adet + " " + tarih;
	}
}
