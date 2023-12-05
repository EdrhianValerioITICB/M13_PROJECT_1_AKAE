package cat.iticbcn.demo.bean;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "COMPANIES")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(example= "7", description = "Company's numerical identifier - primary key")
	private Long id;

	@Schema(example= "Amazon", description = "Company full name")
	@Column(name = "company_name")
	private String name;

	@Schema(example= "205", description = "Number of employees of the Company")
	@Column(name = "number_of_employees")
	private int employees;

	@Schema(example= "7748190752", description = "Company's socialSecurityNumber")
	@Column(name = "social_security_number", unique = true)
	private String socialSecurityNumber;

	@Schema(example = "Jeff Bezos", description = "Owner of the company")
	@Column(name = "owner_name")
	private String owner;

	@Schema(example = "410 Terry Ave N, Seattle 98109, WA", description = "Company's address of one of their offices")
	@Column(name = "company_address")
	private String address;

	@Schema(example = "713 443 966", description = "Company's phone number")
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;

	@Schema(example = "clients@amazon.com", description = "Company's email")
	@Column(unique = true)
	private String email;

	@Schema(example = "Tech multinational", description = "Company's Type")
	@Column(name = "company_type")
	private String type;

	@Schema(description = "Company's list of Offers")
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Offer> offers;

	public Company() {
	}

	public Company(Long id, String name, int employees, String socialSecurityNumber, String owner, String address, String phoneNumber, String email, String type, List<Offer> offers) {
		this.id = id;
		this.name = name;
		this.employees = employees;
		this.socialSecurityNumber = socialSecurityNumber;
		this.owner = owner;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.type = type;
		this.offers = offers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployees() {
		return employees;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, employees, id, name, owner, phoneNumber, socialSecurityNumber, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& employees == other.employees && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(owner, other.owner) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(socialSecurityNumber, other.socialSecurityNumber) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", employees=" + employees + ", socialSecurityNumber="
				+ socialSecurityNumber + ", owner=" + owner + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", type=" + type + "]";
	}

}