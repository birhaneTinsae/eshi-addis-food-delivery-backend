package com.eshi.addis.favourite;

import com.eshi.addis.restaurant.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImp implements FavouriteService {
    private final FavouriteRepository favouriteRepository;

    @Override
    public Favourite createFavourite(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }

    @Override
    public void removeFavourite(long id) {
        favouriteRepository.deleteById(id);
    }

    @Override
    public Page<Favourite> getCustomerFavourites(String customerId, Pageable pageable) {
        return favouriteRepository.findAllByCustomer_Id(customerId, pageable);
    }

    @Override
    public Page<Restaurant> getCustomerFavouriteRestaurants(String customerId, Pageable pageable) {
        return getCustomerFavourites(customerId, pageable).map(cf -> cf.getMenu().getCategory().getRestaurant());
    }
}
