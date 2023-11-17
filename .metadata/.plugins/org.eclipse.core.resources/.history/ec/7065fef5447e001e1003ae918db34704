package cat.iticbcn.demo.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Company {

	private @Id @GeneratedValue Long id;
	private String name;
	private int employees;
	private int socialSecurityNumber;
	private String owner;
	private String address;
	private int phoneNumber;
	private String email;
	private String type;
	private List<Offer> offers = new ArrayList<>();

	public Company() {

	}

	public Company(String name, int employees, int socialSecurityNumber, String owner, String address, int phoneNumber,
			String email, String type) {
		super();
		this.name = name;
		this.employees = employees;
		this.socialSecurityNumber = socialSecurityNumber;
		this.owner = owner;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.type = type;
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

	public int getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(int socialSecurityNumber) {
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
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
				&& Objects.equals(owner, other.owner) && phoneNumber == other.phoneNumber
				&& socialSecurityNumber == other.socialSecurityNumber && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", employees=" + employees + ", socialSecurityNumber="
				+ socialSecurityNumber + ", owner=" + owner + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", type=" + type + "]";
	}

}