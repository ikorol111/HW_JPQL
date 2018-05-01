package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = false, exclude = {"city"})

public class User extends BaseEntity{
	
	@Column(name = "full_name")
	private String fullname;
	
	@Column(name = "age")
	private int age;
	
	@ManyToOne (fetch = FetchType.LAZY, cascade = {
			CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "city_id")
	private City city;
}
