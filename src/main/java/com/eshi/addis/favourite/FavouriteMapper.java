package com.eshi.addis.favourite;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {
    Favourite toFavourite(FavouriteDto favouriteDTO);

    FavouriteDto toFavouriteDTO(Favourite favourite);
}
