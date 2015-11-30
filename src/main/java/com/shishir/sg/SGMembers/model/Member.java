package com.shishir.sg.SGMembers.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.FloatBridge;
import org.hibernate.search.bridge.builtin.IntegerBridge;


@Entity
@Indexed
@Table(name="SGMember")
public class Member {
	
	@Id
	private long id;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="race_id")
	private Ethnicity race;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@FieldBridge(impl = FloatBridge.class)
	private float weight;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@FieldBridge(impl = IntegerBridge.class)
	private int height;
	
	private int isVeg;

	public Member() {
		super();
	}

	public Member(long id, String status, Ethnicity race, float weight, int height,
			int isVeg) {
		super();
		this.id = id;
		this.status = status;
		this.race = race;
		this.weight = weight;
		this.height = height;
		this.isVeg = isVeg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Ethnicity getRace() {
		return race;
	}

	public void setRace(Ethnicity race) {
		this.race = race;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(int isVeg) {
		this.isVeg = isVeg;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", status=" + status + ", race=" + race
				+ ", weight=" + weight + ", height=" + height + ", isVeg="
				+ isVeg + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + isVeg;
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(weight);
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
		Member other = (Member) obj;
		if (height != other.height)
			return false;
		if (id != other.id)
			return false;
		if (isVeg != other.isVeg)
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}

	
}
