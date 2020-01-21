package br.modelo;

import java.sql.Date;

/**
 * classe Bean tem os campos da entidade EMPREGADO
 * @author Roberto Silva
 *
 */
public class Empregado {
	private int id;
	private String nome;
	private Date dat_nasc;
	private double salario;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the date
	 */
	public Date getDat_nasc() {
		return dat_nasc;
	}
	/**
	 * @param date the date to set
	 */
	public void setDat_nasc(Date dat_nasc) {
		this.dat_nasc = dat_nasc;
	}
	/**
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}
	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	/* (non-Javadoc) - exibe os atributos de Empregado
	 * @see java.lang.Object#toString()
	 */
	//@Override
	//public String toString() {
	//	return " id=" + id + ", nome=" + nome + ", dat_nasc="
	//			+ dat_nasc + ", salario=" + salario + " \n ";
	//}
}
