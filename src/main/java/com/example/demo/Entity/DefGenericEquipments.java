package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="def_generic_equipments")
public class DefGenericEquipments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int genericEquipmentId;
	private int equipmentCategoryId;
	private String genericEquipmentName;
	private String genericEquipmentValue;
}
