package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "city")
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true, exclude = {"country", "userList"})

public class City extends BaseEntity{

		@Column(name = "name")
		private String name;
		
	    @ManyToOne (fetch = FetchType.LAZY, cascade = {
				CascadeType.DETACH, CascadeType.MERGE, 
				CascadeType.PERSIST, CascadeType.REFRESH})
	    @JoinColumn(name = "country_id")
	    private Country country;

	    @OneToMany(fetch = FetchType.LAZY, cascade = {
				CascadeType.DETACH, CascadeType.MERGE, 
				CascadeType.PERSIST, CascadeType.REFRESH})
	    private List<User> userList = new ArrayList<>();
}
