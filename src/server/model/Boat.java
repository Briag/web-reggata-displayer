package server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "boat")
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Boat  implements Serializable {

	private static final long serialVersionUID = 9L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBoat;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "photo")
	private String photo;
	
	
	public int getIdBoat() {
		return idBoat;
	}

	public void setIdBoat(int idBoat) {
		this.idBoat = idBoat;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
