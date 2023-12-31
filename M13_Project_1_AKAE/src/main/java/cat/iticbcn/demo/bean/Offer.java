package cat.iticbcn.demo.bean;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class Offer {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(example = "3", description = "Offer numerical identifier, primary key")
	private Long id;

	@Schema(example = "Senior Developer", description = "Offer title")
	private String title;

	@Schema(example = "Editor", description = "Offer tescription")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name="company_id",nullable=false)
	private Company company;

	@ManyToMany(mappedBy = "offers")
	@JsonIgnore
	private List<UserStudent> students;

	public Offer(Long id, String title, String description, Company company) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.company = company;
	}

	public Offer(Long id, String title, String description, Company company, List<UserStudent> students) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.company = company;
		this.students = students;
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

	public List<UserStudent> getStudents() {
		return students;
	}

	public void setStudents(List<UserStudent> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, description, id, title);
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
		return Objects.equals(company, other.company) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", company=" + company + "]";
	}



}
