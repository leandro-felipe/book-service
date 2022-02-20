package br.com.bookservice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"} )
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "author", nullable = false, length = 180)
	private String author;
	@Column(name = "launch_date", nullable = false)
	private Date launchDate;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false, length = 250)
	private String title;
	@Transient
	private String currence;
	@Transient
	private String environment;

	public Book() {
	}

	public Book(Long id, String author, Date launchDate, Double price, String title, String currence,
			String environment) {
		this.id = id;
		this.author = author;
		this.launchDate = launchDate;
		this.price = price;
		this.title = title;
		this.currence = currence;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurrence() {
		return currence;
	}

	public void setCurrence(String currence) {
		this.currence = currence;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
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
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}

}
