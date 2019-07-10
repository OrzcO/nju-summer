package nju.software.service.implement;

import nju.software.data.dao.ClientMapper;
import nju.software.data.dataobject.Client;
import nju.software.service.ClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientMapper clientMapper;

    @Override
    public List<Client> selectAll() {
        return clientMapper.selectAll();
    }


    @Override
    public List<Client> search(String text) {
        return clientMapper.search(text);
    }
}
