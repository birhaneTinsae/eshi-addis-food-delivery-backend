package com.eshi.addis.favourite;

import com.eshi.addis.restaurant.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavouriteService {

    Favourite createFavourite(Favourite favourite);

    void removeFavourite(long id);

    Page<Favourite> getCustomerFavourites(String customerId, Pageable pageable);

    Page<Restaurant> getCustomerFavouriteRestaurants(String customerId, Pageable pageable);

}
