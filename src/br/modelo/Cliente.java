package br.modelo;

public class Cliente {

	public String getCd_contato() {
		return cd_contato;
	}
	public void setCd_contato(String string) {
		this.cd_contato = string;
	}
	public String getNm_contato() {
		return nm_contato;
	}
	public void setNm_contato(String nm_contato) {
		this.nm_contato = nm_contato;
	}
	public String getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	
	public String getCd_ramal() {
		return cd_ramal;
	}
	
	public void setCd_ramal(String cd_ramal) {
		this.cd_ramal = cd_ramal;
		
	}

	
	private String cd_contato;
	private String nm_contato; 
	private String cd_cliente;
	private String cd_ramal;
	@Override
	public String toString() {
		return "Cliente [cd_contato=" + cd_contato + ", nm_contato=" + nm_contato + ", cd_cliente=" + cd_cliente
				+ ", cd_ramal=" + cd_ramal + ", getCd_contato()=" + getCd_contato() + ", getNm_contato()="
				+ getNm_contato() + ", getCd_cliente()=" + getCd_cliente() + ", getCd_ramal()=" + getCd_ramal()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}

