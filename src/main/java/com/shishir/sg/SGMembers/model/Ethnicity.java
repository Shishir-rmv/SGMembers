package com.shishir.sg.SGMembers.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Ethnicity {

	@Id
	private int id;
	private String ethnicGroup;
	
	public Ethnicity() {
		super();
	}

	public Ethnicity(int id, String ethnicGroup) {
		super();
		this.id = id;
		this.ethnicGroup = ethnicGroup;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ethnicity other = (Ethnicity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ethnicity [id=" + id + ", ethnicGroup=" + ethnicGroup + "]";
	}

}
