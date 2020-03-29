package org.csu.jpetstore.service;

import org.csu.jpetstore.persistence.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private ItemMapper itemMapper;
}
