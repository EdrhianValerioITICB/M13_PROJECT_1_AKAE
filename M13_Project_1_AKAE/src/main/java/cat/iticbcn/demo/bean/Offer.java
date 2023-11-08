package cat.iticbcn.demo.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {
	private @Id @GeneratedValue Long id;
	private String title;
	private String description;
	
	@ManyToOne(optional = false, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name="company_id")
	private Company company;
	
	public Offer(String title, String description, Company company) {
		super();
		this.title = title;
		this.description = description;
		this.company = company;
	}
	
	public Offer() {}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", company=" + company + "]";
	}
	
	
	
}