package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity

@NoArgsConstructor 
@AllArgsConstructor
@RequiredArgsConstructor 
@ToString
public class Tournament implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idTournament;

	@NonNull
	private Integer slot;
	
	@NonNull
	private Float prize_pool;
	
	@NonNull
	private Float price;
	
	@OneToOne
	private Activity activity;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="Tournaments")
	private Set<User> Users;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdTournament() {
		return idTournament;
	}

	public Integer getSlot() {
		return slot;
	}

	public Float getPrize_pool() {
		return prize_pool;
	}

	public Float getPrice() {
		return price;
	}

	public Activity getActivity() {
		return activity;
	}

	public Set<User> getUsers() {
		return Users;
	}

	public void setIdTournament(Long idTournament) {
		this.idTournament = idTournament;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public void setPrize_pool(Float prize_pool) {
		this.prize_pool = prize_pool;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setUsers(Set<User> users) {
		Users = users;
	}


 

	
	
}
