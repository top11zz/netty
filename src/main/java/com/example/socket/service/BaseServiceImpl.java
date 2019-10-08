package com.example.socket.service;

import org.springframework.stereotype.Service;

@Service("/baseService")
public class BaseServiceImpl implements BaseService {
    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}
