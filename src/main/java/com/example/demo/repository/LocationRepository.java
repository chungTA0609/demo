package com.example.demo.repository;

import com.example.demo.entity.Location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query(value = """
    select t from Location t
    where t.locationCode = :locationCode
    """)
    Location findByLocationCode(String locationCode);

    @Query(value = """
    select t from Location t
    where t.locationId = :id
    """)
    Location findByLocationId(long id);
}
