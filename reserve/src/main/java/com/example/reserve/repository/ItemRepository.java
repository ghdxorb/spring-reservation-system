package com.example.reserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reserve.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}