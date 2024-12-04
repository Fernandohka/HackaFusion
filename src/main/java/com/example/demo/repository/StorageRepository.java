package com.example.demo.repository;

import java.util.Optional;

public class StorageRepository {
    Optional<ImageData> findByName(String filename);
}
