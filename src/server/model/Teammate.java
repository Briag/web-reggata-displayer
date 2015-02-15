package server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teammate")
public class Teammate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTeammate;
	
	@Column(name = "lastname", nullable = false, insertable = false, updatable = false)
	private String lastname;
	
	@Column(name = "firstname", nullable = false, insertable = false, updatable = false)
	private String firstname;
	
	@Column(name = "description", nullable = false, insertable = false, updatable = false)
	private String description;

	public int getIdTeammate() {
		return idTeammate;
	}

	public void setIdTeammate(int idTeammate) {
		this.idTeammate = idTeammate;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
