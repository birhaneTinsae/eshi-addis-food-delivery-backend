package com.eshi.addis.elastic;

import com.eshi.addis.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ElasticElasticRestaurantServiceImp implements ElasticRestaurantService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final RestaurantRepository restaurantRepository;
    private final String RESTAURANT_INDEX="restaurants";

    @Override
    public List<Restaurant> getNearbyRestaurants(Point point) {
        return null;
    }

    @Override
    public List<Restaurant> search(String query) {
        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("name", query);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Restaurant> searchHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Restaurant.class,
                                IndexCoordinates.of(RESTAURANT_INDEX));
        var restaurants = new ArrayList<Restaurant>();
        searchHits.forEach(searchHit-> restaurants.add(searchHit.getContent()));
        return restaurants;
    }

    @Override
    public void updateRestaurants() {
        var restaurants =List.of();//mapList((List<com.eshi.addis.restaurant.Restaurant>) restaurantRepository.findAll(),Restaurant.class,modelMapper);
        List<IndexQuery> queries = restaurants.stream()
                .map(restaurant->
                        new IndexQueryBuilder()
                                .withId(restaurant.getId())
                                .withObject(restaurant).build())
                .collect(Collectors.toList());

         elasticsearchOperations
                .bulkIndex(queries, IndexCoordinates.of(RESTAURANT_INDEX));
    }
}
