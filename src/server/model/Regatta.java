package server.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "regatta")
public class Regatta implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRegatta;
	
	@Column(name = "name", nullable = false, insertable = false, updatable = false)
	private String name;
	
	public Regatta() { }

	public int getregattaId() {
		return idRegatta;
	}

	public void setregattaId(int regattaId) {
		this.idRegatta = regattaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
}