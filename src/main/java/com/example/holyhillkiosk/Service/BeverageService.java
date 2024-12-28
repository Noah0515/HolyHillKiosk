package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.Repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeverageService {
    private final BeverageRepository beverageRepository;


    public BeverageService(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }
}
