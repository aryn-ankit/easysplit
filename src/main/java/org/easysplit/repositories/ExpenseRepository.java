package org.easysplit.repositories;

import org.easysplit.entitites.Expense;
import org.easysplit.entitites.User; // Import User entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Import List

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByPayee(User payee); // Add method to find expenses by payee
}
