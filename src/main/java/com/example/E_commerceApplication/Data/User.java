package com.example.E_commerceApplication.Data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	private Long id;
	private String username;

	// new feature
	private String password;

	// new feature
	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<WishListItem> wishlistItems = new ArrayList<>();

	public int getQuantity() {
		return cartItems.size() + wishlistItems.size(); // Assuming quantity is the sum of cart items and wishlist items
	}

}
