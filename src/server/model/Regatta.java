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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "regatta")
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Regatta implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRegatta;
	
	@Column(name = "name", nullable = false, insertable = false, updatable = false)
	private String name;

	public Regatta() { }
	
	
	public int getIdRegatta() {
		return idRegatta;
	}

	public void setIdRegatta(int idRegatta) {
		this.idRegatta = idRegatta;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
}