package org.easysplit.repositories;

import org.easysplit.entitites.Split;
import org.easysplit.entitites.User; // Import User entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Import List

@Repository
public interface SplitRepository extends JpaRepository<Split, Long> {
    List<Split> findByUser(User user); // Add method to find splits by user
}
