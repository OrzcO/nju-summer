package nju.software.service;

import nju.software.data.dataobject.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {


    List<Client> selectAll();


    List<Client> search(String text);
}
