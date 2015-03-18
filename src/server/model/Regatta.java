package server.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	
	@Column(name = "name", nullable = false)
	private String name;

	public Regatta() {
	}
	
	
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
	
	@ManyToMany
	@JoinTable(
			name="inscription",
			joinColumns = @JoinColumn( name="Regatta_idRegatta"),
			inverseJoinColumns = @JoinColumn( name="Team_idTeam")
	) 
	private Set<Team> teams;
	
	public Set<Team> getTeam() { return teams; }
    public void setTeam(Set<Team> team) { this.teams = team; }
}