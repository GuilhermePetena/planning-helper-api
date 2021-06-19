package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, String> {
    List<Story> findAllByUser_Id(String id);
}
