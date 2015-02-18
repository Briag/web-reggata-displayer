package server.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "teammate")
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Teammate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTeammate;
	
	@Column(name = "lastname", nullable = false, insertable = false, updatable = false)
	private String lastname;
	
	@Column(name = "firstname", nullable = false, insertable = false, updatable = false)
	private String firstname;
	
	@Column(name = "description", nullable = false, insertable = false, updatable = false)
	private String description;
	
	

	@ManyToMany(fetch = FetchType.LAZY, mappedBy="teammates")	
	private Set<Team> teams;
	

	public Set<Team> getTeams() { return teams; }
    void setTeams(Set<Team> teams) { this.teams = teams; }
	
	
	
	public Teammate() {
	}
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
