package ru.alex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}

