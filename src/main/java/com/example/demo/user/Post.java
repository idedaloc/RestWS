package com.example.demo.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;	




@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String descripiton;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;

	/**
	 * @return the descripiton
	 */
	public String getDescripiton() {
		return descripiton;
	}

	/**
	 * @param descripiton the descripiton to set
	 */
	public void setDescripiton(String descripiton) {
		this.descripiton = descripiton;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
