package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;



@Entity
@JsonIgnoreProperties({"stadistic"})
@Table(name="stadistic")
@NamedQuery(name="Stadistic.findAll", query="SELECT a FROM Stadistic a")
public class Stadistic{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_stadistic", unique=true, nullable=false)
	private int id_stadistic;

	@Column(name="name_stadistic", nullable=false, length=70)
	private String name_stadistic;

	@Column(name="approval_stadistic", nullable=false)
	private int approval_stadistic;

	@Column(name="date_stadistic", nullable=true)
	private Timestamp date_stadistic;
	
	/*
	@Column(name="id_region", nullable=true)
	private int id_region;
	*/
	
	//luego usaremos las otras partes, por ahora solo analisis
	// de sentimientos.

	
	public Stadistic() {
	}



	public int getId_stadistic() {
		return id_stadistic;
	}



	public void setId_stadistic(int id_stadistic) {
		this.id_stadistic = id_stadistic;
	}



	public String getName_stadistic() {
		return name_stadistic;
	}



	public void setName_stadistic(String name_stadistic) {
		this.name_stadistic = name_stadistic;
	}



	public int getApproval_stadistic() {
		return approval_stadistic;
	}



	public void setApproval_stadistic(int approval_stadistic) {
		this.approval_stadistic = approval_stadistic;
	}



	public Timestamp getDate_stadistic() {
		return date_stadistic;
	}



	public void setDate_stadistic(Timestamp date_stadistic) {
		this.date_stadistic = date_stadistic;
	}


	

	
}