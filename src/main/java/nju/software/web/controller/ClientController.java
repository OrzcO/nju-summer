package nju.software.web.controller;

import nju.software.data.dataobject.Client;
import nju.software.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/client")
@Controller
public class ClientController {

    @Resource(name = "clientService")
    private ClientService clientService;


    @ResponseBody
    @RequestMapping("/getClientList.do")
    public List<Client> getClientList() {
        return clientService.selectAll();
    }

    @ResponseBody
    @RequestMapping("/search.do")
    public List<Client> search(HttpServletRequest httpServletRequest) {
        String text = httpServletRequest.getParameter("text");

        return clientService.search(text);
    }
}
