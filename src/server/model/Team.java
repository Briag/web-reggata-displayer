package server.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Team implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTeam;
	
	@Column(name = "name", nullable = false, insertable = false, updatable = false)
	private String name;
	
	@Column(name = "description", nullable = false, insertable = false, updatable = false)
	private String description;

	public Team() {
	}
	
	public int getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="teamcomposition",
			joinColumns = @JoinColumn( name="Team_idTeam"),
			inverseJoinColumns = @JoinColumn( name="Teammate_idTeammate")
	) 
	private Set<Teammate> teammates;
	
	public Set<Teammate> getTeammates() { return teammates; }
    void setTeammates(Set<Teammate> teammates) { this.teammates = teammates; }

    
}