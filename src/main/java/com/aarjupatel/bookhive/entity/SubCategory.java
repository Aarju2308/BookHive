package com.aarjupatel.bookhive.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "subCategoty_tbl")
@Data
public class SubCategory {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "subCat_id")
	private int subCatId;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_id")
	@JsonBackReference
	private Category category;
	
	private String name;
	
	private int status;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
	private List<Product> products;

	@Override
	public String toString() {
		return "SubCategory [subCatId=" + subCatId + ", name=" + name + ", status=" + status + "]";
	}
	
	
}
