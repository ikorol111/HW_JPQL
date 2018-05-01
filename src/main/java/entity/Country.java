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
@Table(name = "country")
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = false, exclude = "userList")

public class Country extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
    @OneToMany (fetch = FetchType.LAZY, cascade = {
			CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH})
        List<User> userList = new ArrayList<>();
}
